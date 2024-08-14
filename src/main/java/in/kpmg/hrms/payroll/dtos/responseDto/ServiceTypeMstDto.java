package in.kpmg.hrms.payroll.dtos.responseDto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ServiceTypeMstDto {

	
	private Integer id;
    private String serviceType;

    private String serviceTypeRegLang;

    private Integer employeeTypeId;
    private Integer employeeTypeName;

    private String serviceTypeCode;

    private String description;

    private String descRegLang;

    private String remarksEng;

    private String remarksRegLang;

    private Timestamp effectiveFrom;

    private Timestamp effectiveTo;

    private Integer displayOrder;
}
