package in.kpmg.hrms.payroll.dtos;

import lombok.Data;

@Data
public class ForgetPassRequestDto {
    private String username;
    private String password;
    private Integer otp;
}
