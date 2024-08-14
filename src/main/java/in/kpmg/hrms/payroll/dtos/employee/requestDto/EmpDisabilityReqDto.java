package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import in.kpmg.hrms.payroll.validation.employeeReqDto.PersonalDetailsGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpDisabilityReqDto {
    //disability details
    @NotNull(message = "Disability Type is mandatory", groups = PersonalDetailsGroup.class)
    private Integer disabilityType;

    @Min(0)
    @Max(100)
    @NotNull(message = "Percentage is mandatory", groups = PersonalDetailsGroup.class)
    private Integer disabilityPercent;
}
