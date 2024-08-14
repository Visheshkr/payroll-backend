package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import lombok.Data;

@Data
public class EmployeesSalaryReport {
	
	private Integer totalCount;
	
	private Integer initiatedCount;
	
	private Integer notInitiatedCount;
	
	private Integer delayedCount;
	
	private List<EmployeeSalaryReportResponse> report;
	

}
