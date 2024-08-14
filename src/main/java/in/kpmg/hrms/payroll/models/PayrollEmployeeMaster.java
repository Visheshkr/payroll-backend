package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "payroll_employee_mst")
public class PayrollEmployeeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @Column(name = "ref_no")
    private String refNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_type_id", referencedColumnName = "type_id")
    private GeneralMst taskTypeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prefix", referencedColumnName = "type_id")
    private GeneralMst prefix;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "type_id")
    private GeneralMst gender;

    private Date dob;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "height_measure", referencedColumnName = "type_id")
    private GeneralMst heightMeasure;

    @Column(name = "height_cm_or_feet")
    private Integer heightCmOrFeet;

    @Column(name = "height_inch")
    private Integer heightInch;

    @Column(name = "identifcn_mark")
    private String identifcnMark;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "marital_status", referencedColumnName = "type_id")
    private GeneralMst maritalStatus;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "religion", referencedColumnName = "type_id")
    private GeneralMst religion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blood_group", referencedColumnName = "type_id")
    private GeneralMst bloodGroup;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "personal_mobile_no")
    private Long personalMobileNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality", referencedColumnName = "type_id")
    private GeneralMst nationality;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_category", referencedColumnName = "type_id")
    private GeneralMst socialCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gpf_pran_type", referencedColumnName = "type_id")
    private GeneralMst gpfPranType;

    @Column(name = "gpf_pran_no")
    private String gpfPranNo;

    @Column(name = "pan_no")
    private String panNo;

    @Column(name = "aadhar_ref_no")
    private Long aadhaarRefNo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_type", referencedColumnName = "type_id")
    private GeneralMst employeeType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_type", referencedColumnName = "id")
    private PayrollServiceTypeMaster serviceType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cadre_id", referencedColumnName = "id")
    private PayrollCadreMaster cadreId;

    @Column(name = "is_govt_quarter_occupied")
    private Boolean isGovtQuarterOccupied;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quarter_type", referencedColumnName = "type_id")
    private GeneralMst quarterType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_dept_id", referencedColumnName = "dept_id")
    private Department parentDeptId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_dept_id", referencedColumnName = "dept_id")
    private Department currentDeptId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_dsgn_id", referencedColumnName = "desgn_id")
    private DesignationMst currentDsgnId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_office", referencedColumnName = "ofc_id")
    private PayrollOfficeMaster currentOffice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ord_issuing_office", referencedColumnName = "ofc_id")
    private PayrollOfficeMaster ordIssuingOffice;

    @Column(name = "appoint_ord_no")
    private String appointOrdNo;

    @Column(name = "appoint_ord_dt")
    private Date appointOrdDate;

    @Column(name = "src_recruitment")
    private Integer srcRecruitment;

    @Column(name = "joining_dt")
    private Date joiningDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "joining_time", referencedColumnName = "type_id")
    private GeneralMst joiningTime;

    @Column(name = "superannuation_dt")
    private Date superannuationDate;

    @Column(name = "confirmation_dt")
    private Date confirmationDate;

    @Column(name = "is_payslip")
    private Boolean isPayslip;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payslip_authority", referencedColumnName = "type_id")
    private GeneralMst payslipAuthority;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status", referencedColumnName = "step_role_id")
    private StepRoleMapping status;

    private String remarks;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grp_id", referencedColumnName = "grp_id")
    private PayrollGroupMaster grpId;

    @Column(name = "p_address_line_1")
    private String addressLine1;

    @Column(name = "p_address_line_2")
    private String addressLine2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_state_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster stateId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "p_dist_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster distId;

    @Column(name = "p_pincode")
    private Integer pincode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp crtOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "bank_acc_no")
    private Long bankAccNo;

    @Column(name = "payee_benef_id")
    private String payeeBenefId;

}
