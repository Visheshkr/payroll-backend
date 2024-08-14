package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTaxDeducHeadDto {
	
	private Integer id;

	@NotNull(message = "Deduction head name is mandatory")
	private String deductionHeadName;
	
	@NotNull(message = "Tax effect is mandatory")
	private Long taxEffectId;

	private Integer maxAmtLimit;
    
	@NotNull(message = "Payhead effect is mandatory")
	private Long payheadEffect;
	
	@NotNull(message = "underTaxLimit is mandatory")
	private Boolean underTaxLimit;
	
	private Integer displayOrder;
	
	private Integer userId;
	

}
