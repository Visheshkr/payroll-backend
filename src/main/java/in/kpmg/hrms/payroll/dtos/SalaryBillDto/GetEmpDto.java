package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmpDto {
	
	@NotNull(message = "grpCode is mandatory")
	private String grpCode;
	
	@NotNull(message = "fyMonHoa is mandatory")
	private Long fyMonHoa;

}
