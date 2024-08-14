package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItSectionDedHeadMappingDto {
	
    private Integer id;
	
    @NotNull(message = "iTsectionId is mandatory")
	private Integer itSectionId;
    
	private String itSectionName;
	
	@NotNull(message = "deductionHeadId is mandatory")
	private Integer deductionHeadId;
	
	@NotNull(message = "isActive is mandatory")
    private Boolean isActive;
	
	@NotNull(message = "userId is mandatory")
	private Integer userId;

}
