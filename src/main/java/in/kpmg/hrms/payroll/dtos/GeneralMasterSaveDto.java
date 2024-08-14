package in.kpmg.hrms.payroll.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralMasterSaveDto {

    private Long typeId;
    private String typeName;
    private String typeNameRegLang;
    private String typeCode;
    private Integer generalTypeId;
    private Long parentTypeId;
    private Boolean isActive;
    private Integer createdBy;
    private Integer updatedBy;
}
