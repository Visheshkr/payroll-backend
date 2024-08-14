package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationRequestDto {
    @NotBlank(message = "Reference Number is mandatory")
    String refNo;

    @NotEmpty(message = "Educational Details is/are mandatory")
    @NotNull(message = "Educational Details is/are mandatory")
    List<@Valid EducationalDtlsDto> educationalDetailsList;
}
