package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FyPaymonthSaveDto {
	
	private Long id;
	 
	@NotNull(message = "Financial Year ID is mandatory")
	private Long fyId;

	private String fyName;
	
	@NotNull(message = "Month ID is mandatory")
	private Integer monthId;
	
	private String monthName;

	@NotNull(message = "Current Year is mandatory")
	private Long currentYear;
	
	private String currentYearName;

	@NotNull(message = "Is Active is mandatory")
	private Boolean isActive;

	private Integer displayOrder;


}
