package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import lombok.Data;

@Data
public class SalaryProcessResponse {
	
	private Long officeId;
	private String officeName;
	private String fy;
	private String payPeriod;
	private String currentYear;
	private String month;
	private Long hoa;
	private Integer unAllocatedEmp;
	private Long fyMonId;

	private List<GroupWiseSalaryStatus> proccesedEmps;
	private List<GroupWiseSalaryStatus> notProccessedEmps;
}
