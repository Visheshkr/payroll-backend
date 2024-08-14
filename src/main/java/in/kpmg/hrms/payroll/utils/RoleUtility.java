package in.kpmg.hrms.payroll.utils;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.kpmg.hrms.payroll.constants.EmployeeConstants;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.models.UserRoleMappingMst;
import in.kpmg.hrms.payroll.repo.UserRoleMapRepo;

@Component
public class RoleUtility {

    @Autowired
    private UserRoleMapRepo userRoleMapRepo;

    public Boolean isAdmin(UserMst user)
    {
        List<UserRoleMappingMst> userRoleMappingMstList = this.userRoleMapRepo.findAllByUser(user);

        AtomicBoolean isAdmin = new AtomicBoolean(false);
        userRoleMappingMstList.forEach(value -> {
            if (value.getRole().getRoleId()== EmployeeConstants.SUPER_ADMIN_ROLE_ID)
                isAdmin.set(true);
        });

        return isAdmin.get();
    }

}
