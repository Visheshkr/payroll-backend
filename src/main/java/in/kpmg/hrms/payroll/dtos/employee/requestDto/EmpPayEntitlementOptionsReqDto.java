package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpPayEntitlementOptionsReqDto {

    @NotBlank(message = "Employee Reference Number is mandatory")
    private String refNo;
    private Integer hraTier;
    @NotNull(message = "Basic Pay is mandatory")
    private Integer basicPay;
    @NotNull(message = "Is CTA Applicable is mandatory")
    private Boolean isCtaApplicable;
    private Long ctaEntitlement;
    @NotNull(message = "Pay Level is mandatory")
    private Long paylevel;
    @NotNull(message = "Pay Commission ID is mandatory")
    private Long pcId;
    @NotNull(message = "Is NPS opted is mandatory")
    private Boolean isNpsOpted;
    @NotNull(message = "Is Medical Stop is mandatory")
    private Boolean isMedicalStop;
    @NotNull(message = "Is DA Stop is mandatory")
    private Boolean isDaStop;
    @JsonProperty("payEntitlementList")
    @NotNull(message = "Pay Entitlement List is mandatory")
    @NotEmpty(message = "Pay Entitlement List is mandatory")
    private List<@Valid EmpPayEntitlementReqDto> empPayEntitlementReqDtoList;

}
