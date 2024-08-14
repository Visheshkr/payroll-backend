package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Date;

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

import lombok.Data;

@Entity
@Table(name = "payroll_employee_family_details")
@Data
public class PayrollEmpFamily {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;

    @ManyToOne
    @JoinColumn(name = "relationship_id", referencedColumnName = "type_id")
    private GeneralMst relationshipId;

    private String name;

    private Date dob;

    @ManyToOne
    @JoinColumn(name = "gender", referencedColumnName = "type_id")
    private GeneralMst gender;

    @ManyToOne
    @JoinColumn(name = "marital_status", referencedColumnName = "type_id")
    private GeneralMst maritalStatus;

    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @Column(name = "disability_percent")
    private Integer disabilityPercent;

    @Column(name = "is_dependant")
    private Boolean isDependant;

    @Column(name = "dependant_income")
    private Integer dependantIncome;

    @Column(name = "is_employed")
    private Boolean isEmployed;

    @Column(name = "is_nominee")
    private Boolean isNominee;

    @Column(name = "nomination_percent")
    private Integer nominationPercent;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "step_role_id")
    private StepRoleMapping status;

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
