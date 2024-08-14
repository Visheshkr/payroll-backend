package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SaveEmpSalaryBillDto {

	private Long id;

	@NotNull(message = "empId is mandatory")
	private Long empId;

	@NotNull(message = "fyPayMonthId is mandatory")
	private Long fyPayMonthId;

	@Valid
	@NotEmpty(message = "empSalary is mandatory")
	private List<SaveEmpSalaryPayHeadsDto> empSalary;

	@NotNull(message = "paidDays is mandatory")
	private Integer paidDays;


	@NotNull(message = "crtBy is mandatory")
	private Integer crtBy;

}
