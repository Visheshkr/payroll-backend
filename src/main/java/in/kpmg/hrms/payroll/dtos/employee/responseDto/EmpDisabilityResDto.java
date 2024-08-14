package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDisabilityResDto {
    private CommonDropdownResDto disabilityType;
    private Integer disabilityPercent;
}
