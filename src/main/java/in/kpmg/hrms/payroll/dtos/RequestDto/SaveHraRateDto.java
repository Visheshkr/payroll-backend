package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveHraRateDto {
	
	private Integer tierId;
	
	@NotNull(message = "tierName is mandatory ")
	private String tierName;

    private String tierNameRegLang;

    @NotNull(message = "ratePercentage is mandatory ")
    private Integer ratePercentage;

    private String description;

    @NotNull(message = "effectiveFrom is mandatory ")
    private Date effectiveFrom;

    private Date effectiveTo;

    private Integer displayOrder;

    
    private Integer userId;
    
    @NotNull(message = "isActive is mandatory ")
    private Boolean isActive;

}
