package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalTaxDto {
	
	private Integer id;

	@NotNull(message = "State is mandatory")
	private Long stateId;
	
	@NotNull(message = "Min. Income is mandatory")
	private Long minIncome;
	
	private Long maxIncome;
	
	@NotNull(message = "Effective from date is mandatory")
	private Date effectiveFrom;
    
	@NotNull(message = "Effective to date is mandatory")
	private Date effectiveTo;
	
	private Long professionalTaxAmount;
	
	private Integer userId;

}
