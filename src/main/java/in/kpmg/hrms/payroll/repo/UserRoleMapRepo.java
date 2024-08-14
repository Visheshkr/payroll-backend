package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.RoleListDropDownDto;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.models.UserRoleMappingMst;

@Repository
public interface UserRoleMapRepo extends JpaRepository<UserRoleMappingMst, Integer> {

    @Query("SELECT r.role.roleId as roleId,r.role.name as roleName FROM UserRoleMappingMst r WHERE r.user.userId=:uid and r.isActive = true order by roleMapId desc")
    List<RoleListDropDownDto> getRoleDtls(Integer uid);

    List<UserRoleMappingMst> findAllByUser(UserMst user);

}
