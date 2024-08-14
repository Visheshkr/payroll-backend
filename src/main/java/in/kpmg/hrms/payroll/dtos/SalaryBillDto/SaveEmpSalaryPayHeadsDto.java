package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveEmpSalaryPayHeadsDto {
	
	@NotNull(message = "payheadId is mandatory")
	private Integer payheadId;

	@NotNull(message = "payheadValue is mandatory")
	private Integer payheadValue;

	private Integer payheadConfigId;

}
