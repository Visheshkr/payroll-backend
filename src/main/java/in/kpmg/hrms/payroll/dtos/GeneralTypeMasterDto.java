package in.kpmg.hrms.payroll.dtos;
import java.sql.Timestamp;

import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollGeneralTypeMaster;
import lombok.Data;

@Data
public class GeneralTypeMasterDto {
	private Integer typeId;
    private String typeName;
    private String typeNameRegLang;
    private PayrollGeneralTypeMaster generalTypeId;
    private GeneralMst parentTypeId;
	private String typeDesc;

    private Boolean isActive;
    private String typeCode ;
    private Integer createdBy;
    private Timestamp createdOn;
    private Integer updatedBy;
    private Timestamp updatedOn;
}
