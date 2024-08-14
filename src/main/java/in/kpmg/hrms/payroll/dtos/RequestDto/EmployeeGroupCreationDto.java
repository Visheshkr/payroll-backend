package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import in.kpmg.hrms.payroll.services.MasterMappingService.Assign;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeGroupCreationDto {
	
	@NotNull(message = "empId is mandatory")
	private Long empId;
	
	@NotNull(message = "grpId is mandatory", groups = {Assign.class})
	private Long grpId;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Integer serviceType;
	
	private Integer dsgnId;
	
	private Integer officeId;
	
	
	private Integer assignedStatus;
	
	private String empName;
	
	

}
