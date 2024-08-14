package in.kpmg.hrms.payroll.dtos.SalaryBillDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FyMonthStatusDto {
	
	private Long id;
	
	private Long fyId;
	
	private Integer officeId;
	
	@NotNull(message = "fyMonthId is mandatory")
	private Long fyMonthId;
	private String fy;
	private Integer monthId;
	private String monthName;
	
	private String currentYear;
	
	@NotNull(message = "hoaId is mandatory")
	private Long hoaId;
	private Long hoa;
	
	@NotNull(message = "status is mandatory")
	private Long status;
	private String statusVal;
	
	@NotNull(message = "crtBy is mandatory")
	private Integer crtBy;
	
	private Boolean isActive;
	

}
