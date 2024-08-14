package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

public interface EmployeeSalaryReportResponse {
	
	 Integer getOfficeId();
	
	 String getOfficeName();
	
	 Integer getTotalEmp();
	
	 Integer getNotProcessedCount();
	
	 Integer getProcessedCount();
	 
	 Integer getDelayedCount();
	 
	 Integer getNotDelayedCount();
	 
	 String getDeptName();

	
	

}
