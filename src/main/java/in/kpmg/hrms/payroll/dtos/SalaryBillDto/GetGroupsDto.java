package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetGroupsDto {
	
	//@NotNull(message = "officeId is mandatory")
	private Integer officeId;
	
	@NotNull(message = "fyMonHoa is mandatory")
	private Long fyMonHoa;

}
