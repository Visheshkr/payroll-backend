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
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDocumentsReqDto {
    @NotBlank(message = "Employee Reference Number is mandatory")
    private String refNo;
    @NotNull(message = "Employee Documents list is mandatory")
    @NotEmpty(message = "Employee Documents list is mandatory")
    private List<@Valid EmployeeDocumentsDto> employeeDocumentsList;

}
