package in.kpmg.hrms.payroll.dtos;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayCommisionServTypeInputDto {

    private Integer id;
    private Integer servTypeId;
    private String serviceTypeName;
    private Long pcId;
    private String pcIdTypeName;
    private String pcDesc;
    private String pcDescInRegLang;
    private Timestamp effectiveFrom;
    private Timestamp effectiveTo;
    private Integer displayOrder;
    private Boolean isActive;

}
