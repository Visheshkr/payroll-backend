package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpPayEntitlementReqDto {
    @NotNull(message = "Payhead Value is mandatory")
    private Integer payheadValue;
    @NotNull(message = "Payhead ID is mandatory")
    private Integer payheadId;
//    @NotNull(message = "Payhead Config ID is mandatory")
    private Integer payheadConfigId;

}
