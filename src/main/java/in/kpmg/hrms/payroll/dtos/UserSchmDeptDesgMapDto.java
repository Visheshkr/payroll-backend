package in.kpmg.hrms.payroll.dtos;


import java.sql.Date;

import lombok.Data;

@Data
public class UserSchmDeptDesgMapDto {
    private Integer id;

    private DesgnDto designation;
    private Boolean isActive;

    private SchmDeptDesgMapDto mapping;
    private Date startDate;
    private Date endDate;
    private GeneralTypeMasterDto isRegular;
}
