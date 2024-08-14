package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfficeDropdownReqDto {

    @NotNull(message = "Department ID is mandatory")
    private Integer deptId;
    private Long stateId;
    private Long districtId;
    private String officeName;

}
