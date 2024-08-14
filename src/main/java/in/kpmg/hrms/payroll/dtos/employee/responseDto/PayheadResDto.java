package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayheadResDto {
    private Integer payheadId;
    private Integer payheadConfigId;
    private String payheadName;
    private Long payheadValue;
    private CommonDropdownResDto payheadType;

    @JsonIgnore
    public Long getPayheadTypeId() {
        return this.getPayheadType().getId();
    }
}
