package in.kpmg.hrms.payroll.services;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.RoleListDropDownDto;
import in.kpmg.hrms.payroll.dtos.RoleMenuRightProj;
import in.kpmg.hrms.payroll.dtos.UserDto;
import in.kpmg.hrms.payroll.mapper.UserMapper;
import in.kpmg.hrms.payroll.models.UserLogin;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.repo.LoginRepo;
import in.kpmg.hrms.payroll.repo.RoleMenuRightMapRepo;
import in.kpmg.hrms.payroll.repo.UserRepo;
import in.kpmg.hrms.payroll.repo.UserRoleMapRepo;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    private UserMapper userMapper;

    @Autowired
    private UserRoleMapRepo userRoleRepo;

    @Autowired
    private RoleMenuRightMapRepo menuRightMapRepo;

    @Autowired
    private LoginRepo loginRepo;



    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMst userdata = userRepo.getUserDataByUserName(username.toLowerCase());
        if (userdata == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userdata.getUserName(), userdata.getPassword(),
                new ArrayList<>());
    }


    public Boolean checkPasswordHash(String username, String password) {
        Integer hashtimes = loginRepo.checkPasswordHash(username, password);
//        System.out.println(hashtimes+"--------------------------------");
        return hashtimes > 0 ? false : true;

    }


    @Transactional
    public Integer saveUserLogin(String username, Integer status, String passowrd, String ipAddress) {
        UserLogin loginDetails = new UserLogin();
        int k = 0;
        try {
            loginDetails.setUserName(username);
            loginDetails.setStatus(status);
            loginDetails.setPassword_hash(passowrd);
            loginDetails.setIpAddress(ipAddress);
            loginRepo.save(loginDetails);


        } catch (Exception ex) {
            ex.getMessage();
        }

        return k;
    }

    public Map<String, Object> getLoginUserData(String username) {
        Map<String, Object> response = new HashMap<>();
        UserMst userData = userRepo.getUserDataByUserName(username);
//        UserDto useDto = userMapper.modelToDto(userData);
        UserDto userDto= new UserDto();
        userDto.setUserId(userData.getUserId());
        userDto.setEmail(userData.getEmail());
        userDto.setUserName(userData.getUserName());
        userDto.setMobileNo(userData.getMobileNo());
        userDto.setFullname(userData.getFullname());
        userDto.setEmpId(userData.getEmpCode());



        List<RoleListDropDownDto> roleMaps = userRoleRepo.getRoleDtls(userData.getUserId());
        List<RoleMenuRightProj> parentMenu = menuRightMapRepo.getMainMenuList(userData.getUserId());

        Set<Integer> parentMenuSetIds = new HashSet<>();
        List<RoleMenuRightProj> parentMenuWOduplicate1 = parentMenu.stream()
                .filter(e -> parentMenuSetIds.add(e.getMenuId()))
                .collect(Collectors.toList());
        List<RoleMenuRightProj> subMenu = menuRightMapRepo.getSubMenuList(userData.getUserId());
        List<RoleMenuRightProj> subMenuWoDuplicate = subMenu.stream().collect(collectingAndThen(
                toCollection(() -> new TreeSet<>(comparingInt(RoleMenuRightProj::getMenuId))), ArrayList::new));
//        String lastLoggedInTime = loginRepo.getlastLoggedInTime(username);
        response.put("user", userDto);

        response.put("parentMenuList", parentMenuWOduplicate1);
        response.put("subMenuList", subMenuWoDuplicate);
//        response.put("lastLoggedInTime", lastLoggedInTime);
        response.put("roleMap", roleMaps);
        return response;
    }

    public UserMst getUserDetailsByUserName(String username) {
        return userRepo.getUserDataByUserName(username);
    }

    public ResponseEntity<?> validateTokenUsername() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {

                return new ResponseEntity<>(
                        new ApiResponse2<>(true, "Validation Successful", null, HttpStatus.OK.value()), HttpStatus.OK);

            } else
                throw new SecurityException("Unauthorized Access!");
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse2<>(false, e.getMessage(), null, HttpStatus.UNAUTHORIZED.value()),
                    HttpStatus.UNAUTHORIZED);
        }
    }


    public Map<String, Object> getDynamicMenu(Integer userId) {
        Map<String, Object> response = new HashMap<>();

        UserMst user;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {

            Optional<UserMst> optionalAuthenticationUser = this.userRepo.findByUserName(authentication.getName());

            if (!optionalAuthenticationUser.isPresent())
                throw new SecurityException("Unauthorized Access!");

            user = optionalAuthenticationUser.get();

        } else {
            throw new SecurityException("Unauthorized Access!");
        }

        List<RoleMenuRightProj> parentMenu = menuRightMapRepo.getMainMenuList(user.getUserId());

        List<RoleMenuRightProj> parentMenuWOduplicate = parentMenu.stream().collect(
                collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(RoleMenuRightProj::getMenuId))),
                        ArrayList::new));
        List<RoleMenuRightProj> subMenu = menuRightMapRepo.getSubMenuList(user.getUserId());
        List<RoleMenuRightProj> subMenuWoDuplicate = subMenu.stream().collect(collectingAndThen(
                toCollection(() -> new TreeSet<>(comparingInt(RoleMenuRightProj::getMenuId))), ArrayList::new));
        response.put("parentMenuList", parentMenuWOduplicate);
        response.put("subMenuList", subMenuWoDuplicate);

        return response;
    }
}
