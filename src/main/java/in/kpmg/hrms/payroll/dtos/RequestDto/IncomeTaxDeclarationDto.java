package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeTaxDeclarationDto {
	
	private Integer id;

	@NotNull(message = "Fy Id is mandatory")
	private Long fyId;
	
	@NotNull(message = "IT declaration sub from is mandatory")
	private Date itDecSubFrom;
    
	@NotNull(message = "IT declaration sub to is mandatory")
	private Date itDecSubTo;
    
	@NotNull(message = "Proof inv sub from is mandatory")
	private Date proofInvSubFrom;
    
	@NotNull(message = "proof inv sub to is mandatory")
	private Date proofInvSubTo;
	
	@NotNull(message = "Is active is mandatory")
	private Boolean isActive;
	
	private Integer userId;

}
