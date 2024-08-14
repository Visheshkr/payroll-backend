package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayEntitlementReqDto {

    @NotNull(message = "Pay Commission ID is mandatory")
    private Long payCommissionId;
    @NotNull(message = "Group  ID is mandatory")
    private Long grpId;
    @NotNull(message = "Basic Pay is mandatory")
    private Integer basicPay;
    private Long serviceTypeId;
    private Integer payLevelId;
    private Integer departmentId;
    private Integer designationId;

    private Integer hraTier;
    private Integer ctaEntitlement;

    private Boolean isDaStop;
    private Boolean isNPS;
}
