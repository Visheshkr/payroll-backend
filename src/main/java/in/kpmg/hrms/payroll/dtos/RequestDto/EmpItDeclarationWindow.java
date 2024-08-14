package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpItDeclarationWindow {
	
	private Integer id;
	
	private Long fyId;
	private String fy;
	
	private Date itDecSubFrom;
    
	private Date itDecSubTo;
    
	private Date proofInvSubFrom;
   
	private Date proofInvSubTo;
	
	private Boolean isActive;
	
	private Long totalInvestedAmt;
	
	private Long otherIncome;
	
	private Long totalDeduction;
	
	private Long salaryIncome;
	
	private Long netIncome;
	
	private Long netTaxableIncome;
	
	private Long totalTaxPayable;
	
	

}
