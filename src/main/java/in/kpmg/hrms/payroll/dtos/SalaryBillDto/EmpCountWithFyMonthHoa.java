package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import lombok.Data;

@Data
public class EmpCountWithFyMonthHoa {

	private Integer totalEmp;
	private Integer allocatedEmp;
	private Integer unAllocatedEmp;
	
	private List<FyMonthStatusDto> fyMonthHoa;
}
