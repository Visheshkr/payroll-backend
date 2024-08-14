package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasicPayReqDto {

    @NotNull(message = "Pay level ID is mandatory")
    private Long payLevelId;
    @NotNull(message = "Service Type ID is mandatory")
    private Integer serviceTypeId;
    @NotNull(message = "Pay Commission ID is mandatory")
    private Long payCommissionId;
}
