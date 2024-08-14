package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItSectionSchemeDto {

	private Integer id;

	@NotNull(message = "Scheme Name is mandatory")
	private String schemeName;
	
	private String schemeNameRegLang;

	private String sectionName;
	
	@NotNull(message = "Section Id is mandatory")
	private Integer sectionId;
	
	@NotNull(message = "Effective From Id is mandatory")
	private Date effectiveFrom;
	
	@NotNull(message = "Is Active is mandatory")
	private Boolean isActive;
	
	@NotNull(message = "crtBy is mandatory")
	private Integer crtBy;
}
