package in.kpmg.hrms.payroll.dtos.RequestDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import in.kpmg.hrms.payroll.services.MasterConfigService.DA;
import in.kpmg.hrms.payroll.services.MasterConfigService.FixedAmt;
import in.kpmg.hrms.payroll.services.MasterConfigService.NonfixedAmt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayHeadConfigDto {
	
	private Integer payheadConfigId;

	
	@NotNull(message = "Pay head is mandatory")
    private Integer payHeadId;

    @NotNull(message = "payCommissionId is mandatory")
    private Long payCommissionId;

    private List<Integer> serviceTypes;

    private List<Integer> deptDsgnIds;

    private List<Integer> payLevelIds;
    
    @NotNull(message = "isFixedAmt is mandatory")
    private Boolean isFixedAmt;

    @NotNull(message = "maxAmt is mandatory", groups = {FixedAmt.class})
    private Integer maxAmt;

    @NotNull(message = "effectiveFrom is mandatory")
    private Timestamp effectiveFrom;
    
   @NotNull(message = "formula is mandatory", groups = {NonfixedAmt.class})
    private String formula;
    

    private Integer tierId;

    @NotNull(message = "circulatedDate is mandatory", groups = {DA.class})
    private Date circulatedDate;
    
    @NotNull(message = "isActive is mandatory")
    private Boolean isActive;

    private Integer ctaEntitlementId;

}
