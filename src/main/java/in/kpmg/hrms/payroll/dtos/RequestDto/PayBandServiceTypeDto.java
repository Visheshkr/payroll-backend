package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayBandServiceTypeDto {
	
	private Integer id;

	@NotNull(message = "payBandId is mandatory")
    private Long payBandId;
    private String payBandName;
    private String payBandCode;
    

    @NotNull(message = "pcServTypeId is mandatory")
    private Integer pcServTypeId;
    private String pcServTypeName;
    private String pcServTypeCode;
    
    private Integer serviceTypeId;
    private String serviceTypeCode;
    private String serviceTypeName;
    
    
    private String payCommision;
    private long pcId;
    

    @NotNull(message = "minValue is mandatory")
    private Long minValue;

    @NotNull(message = "maxValue is mandatory")
    private Long maxValue;

    private Long gradePay;

    private Integer matrixIndex;

    private String description;

    private Integer minServYears;

    private Integer displayOrder;

    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

}
