package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

public interface GroupWiseSalaryStatus {

	 String getGroupName();
	 String getGroupCode();
	 Integer getCycleNo();
	 Integer getEmpCount();
	 Integer getEligibleCount();
	 Integer getExcludeCount();
	 Integer getNonEligibleCount();
	 String getPayrollStatus();
	 String getTempRefNo();
	 String getProcessTime();
	 String getAllocatedYN();
	
	

}
