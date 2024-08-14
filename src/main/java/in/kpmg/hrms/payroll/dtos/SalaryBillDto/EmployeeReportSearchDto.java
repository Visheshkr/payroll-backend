package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReportSearchDto {
	
	@NotNull(message = "dept Id is mandatory")
	private List<Integer> deptId;
	
	@NotNull(message = "officeIds is mandatory")
	private List<Integer> officeIds;
	
	@NotNull(message = "payMontId is mandatory")
	private Long payMontId;
	
	private List<Integer> empIds;
	
	

}
