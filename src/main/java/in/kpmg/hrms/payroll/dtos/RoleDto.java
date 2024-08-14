package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Integer roleId;
    private String name;
    private String roleCode;
    private Integer createdBy;
    private String roledesc;
    private Integer displayOrder;

}
