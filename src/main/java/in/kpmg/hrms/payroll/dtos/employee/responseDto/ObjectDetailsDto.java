package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectDetailsDto {
	
	private Integer typeId;
	
	private String typeName;
	
	private String typeCode;

}
