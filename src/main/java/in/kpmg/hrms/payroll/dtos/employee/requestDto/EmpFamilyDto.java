package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpFamilyDto {

    @NotNull(message = "Relationship ID is mandatory")
    private Integer relationshipId;

    @NotBlank(message = "Family member name is mandatory")
    private String name;

    @NotNull(message = "DOB is mandatory")
    private Date dob;

    @NotNull(message = "Gender is mandatory")
    private Integer gender;

    @NotNull(message = "Marital Status is mandatory")
    private Integer maritalStatus;

    @NotNull(message = "Is Disabled is mandatory")
    private Boolean isDisabled;

    @Min(0)
    @Max(100)
    private Integer disabilityPercent;

    @NotNull(message = "Is Dependant is mandatory")
    private Boolean isDependant;

    private Integer dependantIncome;

    @NotNull(message = "Is Employed is mandatory")
    private Boolean isEmployed;

    @NotNull(message = "Is Nominee is mandatory")
    private Boolean isNominee;

    @Min(0)
    @Max(100)
    private Integer nominationPercent;



}
