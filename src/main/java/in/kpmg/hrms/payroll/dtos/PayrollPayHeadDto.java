package in.kpmg.hrms.payroll.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import in.kpmg.hrms.payroll.services.MasterMappingService.NonPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayrollPayHeadDto {
	
	
    private Integer payheadId;
    
    @NotNull(message = "payheadName is mandatory")
    private String payheadName;

    private String payheadNameRegLang;

    @NotBlank(message = "payheadCode is mandatory")
    private String payheadCode;
    
    @NotNull(message = "payheadGrpId is mandatory")
    private Long payheadGrpId;
    private String payheadGrpName;
    private String payheadGrpCode;
 
    private Integer displayOrder;

    @NotNull(message = "payHeadType is mandatory")
    private Long payHeadType;   
    private String payHeadTypeName;
    private String payHeadTypeCode;

//    @NotNull(message = "btDescId is mandatory", groups = {NonPayment.class})
//    private Long btDescId;
//    private String btDescName;
//    private String btDescCode;


    @NotNull(message = "majorHead is mandatory", groups = {NonPayment.class})
    private Integer majorHead;
    private String majorHeadName;
    private String majorHeadCode;

    @NotNull(message = "subMajorHead is mandatory", groups = {NonPayment.class})
    private Integer subMajorHead;
    private String subMajorHeadName;
    private String subMajorHeadCode;

    @NotNull(message = "minorHead is mandatory", groups = {NonPayment.class})
    private Integer minorHead;
    private String minorHeadName;
    private String minorHeadCode;

    @NotNull(message = "subHead is mandatory", groups = {NonPayment.class})
    private Integer subHead;
    private String subHeadName;
    private String subHeadCode;

    @NotNull(message = "detailsHead is mandatory")
    private Integer detailsHead;
    private String detailsHeadName;
    private String detailsHeadCode;
 
    @NotNull(message = "subDetailsHead is mandatory")
    private Integer subDetailsHead;
    private String subDetailsHeadName;
    private String subDetailsHeadCode;

//    @NotNull(message = "grpType is mandatory", groups = {NonPayment.class})
//    private Long grpType;
//    private String grpTypeName;
//    private String grpTypeCode;

    private Timestamp effectiveFrom;
    
    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

   
    private Timestamp effectiveTo;
   
    private String remarks;
    
    @NotNull(message = "userId is mandatory")
    private Integer userId;

}
