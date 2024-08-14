package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayHeadServiceTypeMappingDto {

	private Integer id;
	
//	@NotNull(message = "Service Type is mandatory")
	private String serviceType;
	
	@NotNull(message = "Service Type Id is mandatory")
	private Integer serviceTypeId;
	
	@NotNull(message = "Pay Head Type is mandatory")
	private Integer payHeadType;
	
	private String payHeadTypeValue;
	
	@NotNull(message = "Pay Head Id is mandatory")
	private Integer payheadId;
	
//	@NotNull(message = "Pay Head Name is mandatory")
	private String payheadName;
	
//	@NotNull(message = "Pay Head Code is mandatory")
	private String payheadCode;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Date effectiveFrom;
}
