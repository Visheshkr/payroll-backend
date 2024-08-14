package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpBankDtlsResDto {
    private String bankName;
    private String branchName;
    private String ifscCode;
    private Long bankAccNo;
    private String payeeBenefId;
}
