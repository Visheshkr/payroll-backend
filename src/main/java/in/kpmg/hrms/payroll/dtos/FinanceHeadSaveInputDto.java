package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceHeadSaveInputDto {

    private Integer typeId;
    private String typeName;
    private Integer typeCode;
    private Boolean isActive;
    private Integer displayOrder;
    private String typeNameRegLang;
    private Integer parentId;
    private Integer ftypeId;
    private Integer crtBy;
    private Integer updBy;
}
