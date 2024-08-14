package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

public interface EmpSalaryProcess {
	
      String getServiceType();   
      String getEmpName();
      
      String getGpfPranNo();  
      String getPayheadRefNo();
      String getDesignation();
      

      Double getGrossEarning();
   
      Double getGrossDeduction();
      
      Double getActualGrossDeduction();
      Double getActualGrossRecovery();
      Double getActualGrossEarning();
     
      Double getGrossRecovery();
      
      
      Double getActualNetSalary();
      Double getNetSalary();
      
      Integer getNoOfDays();
      Boolean getIsSuspended();
      
      Integer getEmpId();
      

}
