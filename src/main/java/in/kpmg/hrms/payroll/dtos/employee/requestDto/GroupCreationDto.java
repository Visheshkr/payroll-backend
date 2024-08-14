package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupCreationDto {
	
	private Long grpId;

	@NotNull(message = "grpCode is mandatory")
    private String grpCode;
	
	@NotNull(message = "grpName is mandatory")
    private String grpName;

	@NotNull(message = "hoaId is mandatory")
    private Long hoaId;
	private Long hoa;
	private String hoaName;
	
	@NotNull(message = "officeId is mandatory")
    private Long officeId;	
	private String officeCode;
	private String officeName;

    private String description;
    
    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

    private Integer displayOrder;

  
    private Integer status;
    private String statusVal;

}
