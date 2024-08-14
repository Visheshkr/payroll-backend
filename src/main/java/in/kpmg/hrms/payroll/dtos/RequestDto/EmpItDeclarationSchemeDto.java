package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpItDeclarationSchemeDto {

	private Integer id;

//	@NotNull(message = "empItDec is mandatory")
	private Integer empItDec;

	@NotNull(message = "sectionSchemeId is mandatory")
	private Integer sectionSchemeId;

	@NotNull(message = "schemeVal is mandatory")
	private Long schemeVal;

}
