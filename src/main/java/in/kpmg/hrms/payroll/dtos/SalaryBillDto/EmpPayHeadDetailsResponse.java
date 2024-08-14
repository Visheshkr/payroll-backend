package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpPayHeadDetailsResponse {
	
	private String serviceType;   
    private String empName;
    
    private String gpfPranNo; 
    private String payheadRefNo;
    private String designation;
    
    private Double actualEarning;
    private Double grossEarning;
    
    private Double actualGrossDeduction;
    private Double actualGrossRecovery;
    private Double actualGrossEarning;
    
    private Double prevDeduction;
    private Double grossDeduction;
    
    private Double prevRecovery;  
    private Double grossRecovery;
    
    
    private Double actualNetSalary;
    private Double netSalary;
    
    private Integer noOfDays;
    private Boolean isSuspended;
    
    private Integer empId;
    private List<PayHeadDetailsByEmp> payHeads;

}
