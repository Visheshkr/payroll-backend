package in.kpmg.hrms.payroll.dtos;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeInputDto {


    private Integer id;
    private String serviceType;
    private String serviceTypeRegLang;
    private Integer employeeTypeCode;
    private Integer employeeTypeName;
    private String serviceTypeCode;
    private String description;
    private String descRegLang;
    private String remarksEng;
    private String remarksRegLang;
    private Timestamp effectiveFrom;
    private Timestamp effectiveTo;
    private Integer displayOrder;
    private Boolean isActive;
}
