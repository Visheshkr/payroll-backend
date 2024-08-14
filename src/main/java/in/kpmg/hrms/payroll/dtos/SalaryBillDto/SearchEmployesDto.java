package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchEmployesDto {
	
	@NotNull(message = "officeId is mandatory")
	private Integer officeId;
	
	@NotNull(message = "status is mandatory")
	private Character status;
	
	@NotNull(message = "fyMonId is mandatory")
	private Long fyMonId;

}
