package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceHeadSaveDto {

    private Integer typeId;
    private String typeName;
    private String typeCode;
    private Boolean isActive;
    private Integer displayOrder;
    private String typeNameRegLang;
    private Integer parentId;
    private String parentCode;
    private String parentName;
    private Integer ftypeId;


//    private Integer crtBy;

}
