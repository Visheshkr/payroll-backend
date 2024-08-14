package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NotNull
public class SalaryStructureResponseDto {

    private Long employeeCode;
    private String employeeName;
    private String currentOffice;
    private String bankName;
    private Long bankAccountNumber;
    private String ifscCode;
    private String payeeBenefId;
    private Long gpfPranNo;
    private String group;
    private String serviceType;
    private String designation;

    private List<PayheadResDto> paymentPayhead;
    private List<PayheadResDto> recoveryPayhead;
    private List<PayheadResDto> deductionPayhead;

    private Long grossEarning;
    private Long grossDeduction;
    private String netPayInWords;
    private Long netPay;

}
