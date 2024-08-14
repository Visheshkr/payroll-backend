package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import lombok.Data;

@Data
public class ProcessEmployee {
	
	private Long officeId;
	private String officeName;
	private String fy;
	private String payPeriod;
	private String currentYear;
	private String month;
	private Long hoa;
	private Integer unAllocatedEmp;

	
	private String grpName;	
	private String payMonth;
	private String status;
	private String payBillNo;
	private Integer workingDays;	
	private Integer include;	
	private Integer exclude;	
	private Integer totalEmp;	
	private Integer cycle;	
	private List<EmpPayHeadDetailsResponse> employees;
	

}
