package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationalDtlsDto
{
    @NotNull(message = "Qualification ID is mandatory")
    private Integer qualificationId;
    @NotBlank(message = "Institute Name is mandatory")
    private String instituteName;
    @NotBlank(message = "Board or University Name is mandatory")
    private String boardOrUniversity;
    @NotBlank(message = "Course Name is mandatory")
    private String course;
    @NotNull(message = "Qualification ID is mandatory")
    private Integer marksCgpaId;
    @Min(0)
    private Integer marksSecured;
    @Min(1)
    private Integer totalMarks;
    private Integer cgpa;
    @NotNull(message = "Starting Year is mandatory")
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Starting Year must be in the format YYYY")
    @Size(min = 4, max = 4, message = "Starting Year must be exactly 4 digits")
    private String startingYear;
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Passing Year must be in the format YYYY")
    @Size(min = 4, max = 4, message = "Passing Year must be exactly 4 digits")
    @NotNull(message = "Passing Year is mandatory")
    private String passingYear;
//   @NotNull(message = "File Path is mandatory")
    private String filePath;

}
