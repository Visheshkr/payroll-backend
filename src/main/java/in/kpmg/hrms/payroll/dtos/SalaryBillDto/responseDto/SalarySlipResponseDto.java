package in.kpmg.hrms.payroll.dtos.SalaryBillDto.responseDto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalarySlipResponseDto {

    private Long employeeCode;
    private String employeeName;
    private String currentOffice;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date doj;
    private String bankName;
    private Long bankAccountNumber;
    private String ifscCode;
    private Long gpfPranNo;
    private String panNo;
    private Long basicRate;
    private String grade;
    private String serviceType;
    private String designation;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date dateOfRetirement;
    private List<PayheadResDto> paymentPayhead;
    private List<PayheadResDto> recoveryPayhead;
    private List<PayheadResDto> deductionPayhead;
    private Long grossEarning;
    private Long grossDeduction;
    private String netPayInWords;
    private Long netPay;
}
