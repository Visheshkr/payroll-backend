package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpEducationalDtlsResDto {

    private Long eduId;

    private Long empId;
    private CommonDropdownResDto qualificationId;
    private String instituteName;
    private String boardOrUniversity;
    private String course;
    private CommonDropdownResDto marksCgpaId;
    private Integer marksSecured;
    private Integer totalMarks;
    private Integer cgpa;
    private Integer startingYear;
    private Integer passingYear;
    private String filePath;
    private String fileName;
    private CommonDropdownResDto crtBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp crtOn;
    private CommonDropdownResDto updBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updOn;
}
