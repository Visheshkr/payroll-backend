package in.kpmg.hrms.payroll.dtos.employee.requestDto;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

import in.kpmg.hrms.payroll.validation.employeeReqDto.BankDetailsGroup;
import in.kpmg.hrms.payroll.validation.employeeReqDto.PersonalDetailsGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeReqDto {

//    @NotNull(message = "empId is mandatory", groups = BankDetailsGroup.class)
//    private Long empId;

    // Personal details
    @NotBlank(message = "Reference number is mandatory", groups = BankDetailsGroup.class)
    private String refNo;

//    @NotNull(message = "Task type ID is mandatory", groups = PersonalDetailsGroup.class)
    private Long taskTypeId;

    @NotNull(message = "Prefix is mandatory", groups = PersonalDetailsGroup.class)
    private Long prefix;

    @NotBlank(message = "First name is mandatory", groups = PersonalDetailsGroup.class)
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is mandatory", groups = PersonalDetailsGroup.class)
    private String lastName;

    @NotNull(message = "Gender is mandatory", groups = PersonalDetailsGroup.class)
    private Long gender;

    @NotNull(message = "Date of birth is mandatory", groups = PersonalDetailsGroup.class)
    private Date dob;

    @NotNull(message = "Height measure is mandatory", groups = PersonalDetailsGroup.class)
    private Long heightMeasure;

    @NotNull(message = "Height (cm or feet) is mandatory", groups = PersonalDetailsGroup.class)
    private Integer heightCmOrFeet;

//    @NotNull(message = "Height (inch) is mandatory", groups = PersonalDetailsGroup.class)
    private Integer heightInch;

    @NotBlank(message = "Identification mark is mandatory", groups = PersonalDetailsGroup.class)
    private String identifcnMark;

    @NotBlank(message = "Father's name is mandatory", groups = PersonalDetailsGroup.class)
    private String fatherName;

    @NotBlank(message = "Mother's name is mandatory", groups = PersonalDetailsGroup.class)
    private String motherName;

    @NotNull(message = "Marital status is mandatory", groups = PersonalDetailsGroup.class)
    private Long maritalStatus;

    private String spouseName;

    @NotNull(message = "Disability status is mandatory", groups = PersonalDetailsGroup.class)
    private Boolean isDisabled;

    @NotNull(message = "Religion is mandatory", groups = PersonalDetailsGroup.class)
    private Long religion;

    @NotNull(message = "Blood group is mandatory", groups = PersonalDetailsGroup.class)
    private Long bloodGroup;

    @NotBlank(message = "Personal email is mandatory", groups = PersonalDetailsGroup.class)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
//    @Email
    private String personalEmail;

    @NotNull(message = "Personal mobile number is mandatory", groups = PersonalDetailsGroup.class)
    @Pattern(regexp = "^[789]\\d{9}$", message = "Invalid mobile number", groups = PersonalDetailsGroup.class)
    private String personalMobileNo;

    @NotNull(message = "Nationality is mandatory", groups = PersonalDetailsGroup.class)
    private Long nationality;

    @NotNull(message = "Social category is mandatory", groups = PersonalDetailsGroup.class)
    private Long socialCategory;

    @NotNull(message = "GPF/PRAN type is mandatory", groups = PersonalDetailsGroup.class)
    private Long gpfPranType;

    @NotBlank(message = "GPF/PRAN Number is mandatory", groups = PersonalDetailsGroup.class)
    private String gpfPranNo;

    @NotBlank(message = "PAN number is mandatory", groups = PersonalDetailsGroup.class)
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$")
    private String panNo;

    @NotNull(message = "Aadhaar reference number is mandatory", groups = PersonalDetailsGroup.class)
    private Long aadhaarRefNo;

    @NotNull(message = "Employee type is mandatory", groups = PersonalDetailsGroup.class)
    private Long employeeType;

    @NotNull(message = "Service type is mandatory", groups = PersonalDetailsGroup.class)
    private Integer serviceType;

    @NotNull(message = "Cadre ID is mandatory", groups = PersonalDetailsGroup.class)
    private Long cadreId;

    @NotNull(message = "Government quarter occupancy status is mandatory", groups = PersonalDetailsGroup.class)
    private Boolean isGovtQuarterOccupied;

//    @NotNull(message = "Quarter type is mandatory", groups = PersonalDetailsGroup.class)
    private Long quarterType;

    @NotNull(message = "Parent department ID is mandatory", groups = PersonalDetailsGroup.class)
    private Integer parentDeptId;

    @NotNull(message = "Current department ID is mandatory", groups = PersonalDetailsGroup.class)
    private Integer currentDeptId;

    @NotNull(message = "Current designation ID is mandatory", groups = PersonalDetailsGroup.class)
    private Integer currentDsgnId;

    @NotNull(message = "Current office is mandatory", groups = PersonalDetailsGroup.class)
    private Long currentOffice;

    @NotNull(message = "Order Issuing office is mandatory", groups = PersonalDetailsGroup.class)
    private Long ordIssuingOffice;

    @NotBlank(message = "Appointment order number is mandatory", groups = PersonalDetailsGroup.class)
    private String appointOrdNo;

    @NotNull(message = "Appointment order date is mandatory", groups = PersonalDetailsGroup.class)
    private Date appointOrdDate;

    @NotNull(message = "Source recruitment is mandatory", groups = PersonalDetailsGroup.class)
    private Integer srcRecruitment;

    @NotNull(message = "Joining date is mandatory", groups = PersonalDetailsGroup.class)
    private Date joiningDate;

    @NotNull(message = "Joining time is mandatory", groups = PersonalDetailsGroup.class)
    private Long joiningTime;

//    @NotNull(message = "Superannuation date is mandatory", groups = PersonalDetailsGroup.class)
//    private Date superannuationDate;

    @NotNull(message = "Confirmation date is mandatory", groups = PersonalDetailsGroup.class)
    private Date confirmationDate;

    @NotNull(message = "Payslip status is mandatory", groups = PersonalDetailsGroup.class)
    private Boolean isPayslip;

//    @NotNull(message = "Payslip authority is mandatory", groups = PersonalDetailsGroup.class)
    private Long payslipAuthority;

//    @NotNull(message = "Status is mandatory", groups = PersonalDetailsGroup.class)
//    private Long status;

//    private String remarks;

    @NotNull(message = "Group ID is mandatory", groups = PersonalDetailsGroup.class)
    private Long grpId;


    // Address details
    @NotBlank(message = "Address line 1 is mandatory", groups = PersonalDetailsGroup.class)
    private String addressLine1;

    private String addressLine2;

    @NotNull(message = "State ID is mandatory", groups = PersonalDetailsGroup.class)
    private Long stateId;

    @NotNull(message = "District ID is mandatory", groups = PersonalDetailsGroup.class)
    private Long distId;

    @NotNull(message = "Pincode is mandatory", groups = PersonalDetailsGroup.class)
    private Integer pincode;


    // Bank details
    @NotBlank(message = "Bank name is mandatory", groups = BankDetailsGroup.class)
    private String bankName;

    private String branchName;

    @NotBlank(message = "IFSC code is mandatory", groups = BankDetailsGroup.class)
    private String ifscCode;

    @NotNull(message = "Bank account number is mandatory", groups = BankDetailsGroup.class)
    private Long bankAccNo;

    private String payeeBenefId;

    //disability details
    @JsonProperty("disabilityDetails")
    List<@Valid EmpDisabilityReqDto> empDisabilityReqDtoList;
}
