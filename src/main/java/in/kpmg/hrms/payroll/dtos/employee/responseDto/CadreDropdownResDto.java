package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadreDropdownResDto {
    private Long id;
    private String label;
    private Integer retirementAge;
}
