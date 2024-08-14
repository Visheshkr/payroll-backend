package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.util.Date;

public interface EntitlementEmpRes {
    Long getEmpId();

    String getRefNo();

    String getFirstName();

    String getMiddleName();

    String getLastName();

    Long getDesignationId();

    String getDesignationName();

    Long getDeptId();

    String getDeptName();

    Long getOfcId();

    String getOfficeName();

    Long getGrpId();

    String getGrpName();

    Boolean getIsGovtQuarterOccupied();

    Long getServiceId();

    String getServiceName();

    Long getGpfPranTypeId();

    String getGpfPranTypeName();

    String getGpfPranNo();

    String getPersonalMobileNo();

    Date getDob();
}
