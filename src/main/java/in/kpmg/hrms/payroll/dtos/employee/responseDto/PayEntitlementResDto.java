package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface PayEntitlementResDto {

    @JsonIgnore()
    Long getGroupId();
//    @JsonIgnore()
    Long getPayheadId();
    String getPayheadName();
//    @JsonIgnore()
    Long getPayheadConfigId();
    @JsonIgnore()
    String getFormula();
    String getAmount();

    Integer getPayheadTypeId();
    String getPayheadType();

}
