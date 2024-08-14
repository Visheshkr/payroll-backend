package in.kpmg.hrms.payroll.dtos.SalaryBillDto.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalarySlipReqDto {

    @NotNull(message = "Employee Code is mandatory")
    private Long empId;
    @NotNull(message = "Financial Year Pay Month ID is mandatory")
    private Long fyPaymonthId;

}
