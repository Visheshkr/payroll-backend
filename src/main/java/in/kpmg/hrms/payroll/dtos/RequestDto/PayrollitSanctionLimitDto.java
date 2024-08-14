package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollitSanctionLimitDto {

	private Integer id;	
	
	@NotNull(message = "iTsectionId is mandatory")
	private Integer itsectionId;

	@NotNull(message = "max Deducted Amt is mandatory")
	private Long maxDedAmt;

	private Date effectiveFrom;

	private Date effectiveTo;

	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	@NotNull(message = "userId is mandatory")
	private Integer userId;

}
