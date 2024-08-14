package in.kpmg.hrms.payroll.dtos.responseDto;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayHeadConfigResponse {
	
private Integer payheadConfigId;

	
    private Integer payHeadId;
    private String payHeadName;
    private String payHeadCode;
    
    private Integer payCommissionId;
    private String payCommissionName;
    private String payCommissionCode;

    private Integer serviceType;
    private String serviceTypeName;
    
 

    private Integer deptDegnId;
    private String dsgnName;
    private Integer dsgnId;
    private Integer deptId;
    private String deptName;

    private Long payLevelId;
    private String payLevelName;
    
    private Boolean isFixedAmt;

    private Integer maxAmt;

  
    private Timestamp effectiveFrom;
    
  
    private String formula;
    

    private Integer tierId;
    private String tier;

    private Date circulatedDate;
    
    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

    private Integer ctaEntitlementId;
    private String ctaEntitlement;

}
