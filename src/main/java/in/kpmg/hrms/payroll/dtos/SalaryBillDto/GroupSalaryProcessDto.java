package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSalaryProcessDto {
	
	@NotNull(message = "grpCode is mandatory")
	private String grpCode;
	
	@NotNull(message = "fyMonId is mandatory")
	private Long fyMonId;
	
	@NotNull(message = "crtBy is mandatory")
	private Integer crtBy;

}
