package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayEntitlementOptionsResDto {

    private Long id;
    private Long empId;
    private CommonDropdownResDto hraTier;
    private Integer basicPay;
    private Boolean isCtaApplicable;
    private CommonDropdownResDto ctaEntitlement;
    private CommonDropdownResDto paylevel;
    private CommonDropdownResDto payCommission;
    private Boolean isNpsOpted;
    private Boolean isMedicalStop;
    private Boolean isDaStop;
    private CommonDropdownResDto crtBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp crtOn;
    private CommonDropdownResDto updBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updOn;
    private List<PayheadResDto> paymentPayhead;
    private List<PayheadResDto> recoveryPayhead;
    private List<PayheadResDto> deductionPayhead;

}
