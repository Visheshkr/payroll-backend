package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpMstResDto {

    private Long empId;
    private String refNo;
    private CommonDropdownResDto taskTypeId;
    private CommonDropdownResDto prefix;
    private String firstName;
    private String middleName;
    private String lastName;
    private CommonDropdownResDto gender;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date dob;
    private CommonDropdownResDto heightMeasure;
    private Integer heightCmOrFeet;
    private Integer heightInch;
    private String identifcnMark;
    private String fatherName;
    private String motherName;
    private CommonDropdownResDto maritalStatus;
    private String spouseName;
    private Boolean isDisabled;
    private CommonDropdownResDto religion;
    private CommonDropdownResDto bloodGroup;
    private String personalEmail;
    private String personalMobileNo;
    private CommonDropdownResDto nationality;
    private CommonDropdownResDto socialCategory;
    private CommonDropdownResDto gpfPranType;
    private String gpfPranNo;
    private String panNo;
    private Long aadhaarRefNo;
    private CommonDropdownResDto employeeType;
    private CommonDropdownResDto serviceType;
    private CommonDropdownResDto cadreId;
    private Boolean isGovtQuarterOccupied;
    private CommonDropdownResDto quarterType;
    private CommonDropdownResDto parentDeptId;
    private CommonDropdownResDto currentDeptId;
    private CommonDropdownResDto currentDsgnId;
    private OfficeDropdownResDto currentOffice;
    private OfficeDropdownResDto ordIssuingOffice;
    private String appointOrdNo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date appointOrdDate;
    private Integer srcRecruitment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date joiningDate;
    private CommonDropdownResDto joiningTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date superannuationDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date confirmationDate;
    private Boolean isPayslip;
    private CommonDropdownResDto payslipAuthority;
    private CommonDropdownResDto status;
    private String remarks;
    private CommonDropdownResDto grpId;
    private String addressLine1;
    private String addressLine2;
    private CommonDropdownResDto stateId;
    private CommonDropdownResDto distId;
    private Integer pincode;
    private CommonDropdownResDto crtBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp crtOn;
    private CommonDropdownResDto updBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updOn;
    List<EmpDisabilityResDto> empDisabilityList;
}
