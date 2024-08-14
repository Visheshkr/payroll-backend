package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTaxSectionDto {

	private Integer id;

	@NotNull(message = "RegimeType is mandatory")
	private Long regimeType;

	@NotNull(message = "section Name is mandatory")
	private String sectionName;

	private String description;

	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	@NotNull(message = "max Deducted Amt is mandatory")
	private Long maxDedAmt;
	
    private Date effectiveFrom;
    
    private Date effectiveTo;
	
	private Integer userId;
}
