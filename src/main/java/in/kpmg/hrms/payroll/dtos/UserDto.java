package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Integer userId;

    private String userName;

    private String password;
    private String email;

    private Long mobileNo;

    private Integer createdBy;
    private Integer updatedBy;

    private String fullname;

    private String empId;

//    List<UserSchmDeptDesgMapDto> schmDeptDsgMaps;

}
