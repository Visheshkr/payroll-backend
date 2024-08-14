package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTaxSlabDto {

	private Integer itSlabId;

	@NotNull(message = "RegimeType is mandatory")
	private Long regimeType;

	@NotNull(message = "Sequence Number is mandatory")
	private Integer sequenceNo;
    
	@NotNull(message = "Fy Id is mandatory")
	private Long fyId;
	
	@NotNull(message = "Income from is mandatory")
	private Long incomeFrom;
	
	private Long incomeTo;
	
	@NotNull(message = "Tax sub category is mandatory")
	private Long taxSubCategory;
    
	private Long gender;
	
	private Long incomeTaxRate;
	
	private Long cessPercent;
	
	@NotNull(message = "effectiveFrom is mandatory")
	private Date effectiveFrom;
	
	private Integer displayOrder;
	
	@NotNull(message = "isMaxLimit is mandatory")
	private Boolean isMaxLimit;
	
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Integer userId;
}
