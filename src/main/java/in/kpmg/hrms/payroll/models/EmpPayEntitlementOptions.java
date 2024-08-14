package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

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

@Data
@Entity
@Table(name = "payroll_emp_pay_entitlement_options")
public class EmpPayEntitlementOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hra_tier", referencedColumnName = "id")
    private PayrollHRARateMst hraTier;

    @Column(name = "basic_pay")
    private Integer basicPay;

    @Column(name = "is_cta_applicable")
    private Boolean isCtaApplicable;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cta_entitlement", referencedColumnName = "type_id")
    private GeneralMst ctaEntitlement;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pay_level", referencedColumnName = "type_id")
    private GeneralMst paylevel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pc_id", referencedColumnName = "type_id")
    private GeneralMst pcId;

    @Column(name = "is_nps_opted")
    private Boolean isNpsOpted;

    @Column(name = "is_medical_stop")
    private Boolean isMedicalStop;

    @Column(name = "is_da_stop")
    private Boolean isDaStop;

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
}
