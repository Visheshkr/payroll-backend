package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class EmpItDeclarationDto {

	private Integer id;

	@NotNull(message = "empId is mandatory")
	private Long empId;

	@NotNull(message = "itDec is mandatory")
	private Integer itDec;

	@NotNull(message = "regimeType is mandatory")
	private Long regimeType;
	
	private Long otherIncome;

	@NotEmpty
	@Valid
	@NotNull(message = "empItDecSchemes is mandatory")
	private List<EmpItDeclarationSchemeDto> empItDecSchemes;

	@NotNull(message = "crtBy is mandatory")
	private Integer crtBy;

}
