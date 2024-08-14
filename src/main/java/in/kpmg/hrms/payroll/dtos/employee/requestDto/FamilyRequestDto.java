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
public class FamilyRequestDto {

    @NotBlank(message = "Reference Number is mandatory")
    String refNo;

    @JsonProperty("familyDetailsList")
    @NotEmpty(message = "Family Details is/are mandatory")
    @NotNull(message = "Family Details is/are mandatory")
    List<@Valid EmpFamilyDto> empFamilyDtoList;

}
