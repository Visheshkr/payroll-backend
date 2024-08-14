package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDocumentsDto {
    @NotNull(message = "Documents ID is mandatory")
    private Long documentId;
    @NotBlank(message = "File Path is mandatory")
    private String filePath;

}
