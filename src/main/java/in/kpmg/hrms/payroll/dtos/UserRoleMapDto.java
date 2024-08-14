package in.kpmg.hrms.payroll.dtos;

import lombok.Data;

@Data
public class UserRoleMapDto {

    private Integer roleMapId;


    private RoleDto role;

    private Boolean isActive;
}
