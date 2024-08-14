package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payroll_office_mst")
public class PayrollOfficeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ofc_id")
    private Long ofcId;

    private Boolean status;

    @Column(name = "ofc_name")
    private String officeName;

    @Column(name = "ofc_code")
    private String ofcCode;

    @Column(name = "ofc_name_reg_lang")
    private String officeNameRegLang;

    @OneToOne
    @JoinColumn(name = "ofc_type_id", referencedColumnName = "type_id")
    private GeneralMst ofcTypeId;

    @OneToOne
    @JoinColumn(name = "ofc_level_id", referencedColumnName = "type_id")
    private GeneralMst ofcLevelId;

    @Column(name = "ofc_phone_no")
    private Long ofcPhoneNumber;

    @OneToOne
    @JoinColumn(name = "dept_code", referencedColumnName = "dept_id")
    private Department deptCode;

    @Column(name = "reporting_ofc_id")
    private Long reportingOfcId;

    @Column(name = "treasure_code")
    private String treasureCode;

    @Column(name = "addr_line1")
    private String addressLine1;

    @OneToOne
    @JoinColumn(name = "state_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster stateId;

    @OneToOne
    @JoinColumn(name = "division_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster divisionId;

    @OneToOne
    @JoinColumn(name = "district_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster districtId;

    @OneToOne
    @JoinColumn(name = "block_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster blockId;

    @OneToOne
    @JoinColumn(name = "sub_division_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster subDivisionId;

    private Integer pincode;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "display_code")
    private Integer displayCode;

    private String tan;

    @Column(name = "nsdl_ddo_cose")
    private String nsdlDdoCose;

    @Column(name = "intbenf_id")
    private String intbenfId;

    @Column(name = "bank_acc_status")
    private String bankAccStatus;

    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp crtOn;

    @ManyToOne
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;
}
