package in.kpmg.hrms.payroll.controllers;


import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.config.AesUtil;
import in.kpmg.hrms.payroll.config.JwtTokenUtil;
import in.kpmg.hrms.payroll.dtos.JwtRequest;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.repo.UserRepo;
import in.kpmg.hrms.payroll.services.JwtUserDetailsService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/testing")
    public ResponseEntity<?> testFunction(){
        return ResponseEntity.ok(userRepo.findAll());
    }


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest,
                                                       HttpServletRequest req) throws Exception {
        try {
            if (Boolean.FALSE.equals(userDetailsService.checkPasswordHash(authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()))) {
                throw new Exception("Invalid Username or Password");
            }

            authenticate(authenticationRequest.getUsername(),authenticationRequest.getPassword(),authenticationRequest.getPassword2());
            final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
            HashMap<String, Object> result = new HashMap<String, Object>();

            userDetailsService.saveUserLogin(authenticationRequest.getUsername(), 1,authenticationRequest.getPassword(), req.getRemoteAddr());
            result.put("token", token);
            result.put("userdetails", userDetailsService.getLoginUserData(authenticationRequest.getUsername()));
            result.put("status", true);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {

            ex.printStackTrace();
            int k = userDetailsService.saveUserLogin(authenticationRequest.getUsername(), 0,
                    authenticationRequest.getPassword(), req.getRemoteAddr());

            HashMap<String, Object> result = new HashMap<String, Object>();
            result.put("status", false);
            result.put("message", "Invalid Username or Password");
            if (k > 2) {
                result.put("message", "Inactive User");
                return ResponseEntity.status(400).body(result);

            } else {
                return ResponseEntity.status(201).body(result).ok(result);
            }
        }
    }


    @GetMapping("/menu/{userId}")
    public ResponseEntity<?> getDynamicMenu(@PathVariable Integer userId) throws Exception {
        try {
            Map<String, Object> dynamicMenu = this.userDetailsService.getDynamicMenu(userId);

            if (dynamicMenu.isEmpty())
                throw new Exception("User Id doesn't exists!");

            return ResponseEntity.ok(dynamicMenu);
        } catch (Exception e) {
            // return ResponseEntity.status(500).body(new HashMap<String, Object>());
            return new ResponseEntity<>(new HashMap<String, Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/checklogin")
    public ResponseEntity<?> checklogin() throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        result.put("status", true);
        result.put("message", "Session continued...");
        return ResponseEntity.ok(result);
    }

    private void authenticate(String username, String password, String salt) throws Exception {
        try {
            UserMst userData = userDetailsService.getUserDetailsByUserName(username);
            if (userData == null) {
                throw new Exception("INVALID_CREDENTIALS");
            }
            AesUtil aes = new AesUtil();
            String atucalPass = aes.decrypt(userData.getPassword());
            String hashedPassword = getSecurePassword(atucalPass, salt);
            if (!hashedPassword.equals(password)) {
                throw new Exception("INVALID_CREDENTIALS");
            }

        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    public static String getSecurePassword(String password, String salt) throws InvalidKeyException {

        String generatedPassword = null;
        try {
            Mac sha256HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretkey = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256HMAC.init(secretkey);

            generatedPassword = Hex.encodeHexString(sha256HMAC.doFinal(password.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    @GetMapping("/validate-token")
    public ResponseEntity<?> validateTokenUsername() {
        return this.userDetailsService.validateTokenUsername();
    }


}
