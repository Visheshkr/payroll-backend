package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class GetEmployeeGroupDto {

	private Integer serviceType;

	private Integer dsgnId;

	@NotNull(message = "officeId is mandatory")
	private Integer officeId;
	
	private Long grpId;
	
	private String empId;


	private Integer assignedStatus;

	private String empName;

}
