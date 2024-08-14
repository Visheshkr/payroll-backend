package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayMatrixSaveDto {

	private Long id;
	
	@NotNull(message = "Pay Band Id is mandatory")
	private Integer payBandId;
	
	private String payBand;
	
	@NotNull(message = "Value is mandatory")
	private Integer value;
	
	@NotNull(message = "Is Active is mandatory")
	private Boolean isActive;
	
	private Integer displayOrder;
	
	private Integer userId;
	
	private String payCommision;
	
	private Long payCommisionId;
	
	private String serviceType;
	
	private Integer serviceTypeId;
	
	private Long minValue;
	
	private Long maxValue;
	
	private Date effectiveFrom;
	
	private Integer pbServTypeId;
	
	private Integer pcSerTypeId;
	
}
