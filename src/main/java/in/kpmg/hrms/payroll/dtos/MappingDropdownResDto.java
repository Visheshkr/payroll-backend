package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MappingDropdownResDto {
    private Long id;
    private String label;
    private Integer mappingId;
}
