package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpFamilyResDto {

    private Long id;
    private Long empId;
    private CommonDropdownResDto relationshipId;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date dob;
    private CommonDropdownResDto gender;
    private CommonDropdownResDto maritalStatus;
    private Boolean isDisabled;
    private Integer disabilityPercent;
    private Boolean isDependant;
    private Integer dependantIncome;
    private Boolean isEmployed;
    private Boolean isNominee;
    private Integer nominationPercent;
    private Boolean isActive;
    private CommonDropdownResDto status;
    private CommonDropdownResDto crtBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp crtOn;
    private CommonDropdownResDto updBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updOn;

}
