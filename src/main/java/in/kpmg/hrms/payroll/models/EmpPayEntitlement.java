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
@Table(name = "payroll_emp_pay_entitlement")
public class EmpPayEntitlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payhead_entitlement_id", referencedColumnName = "id")
    private EmpPayEntitlementOptions payheadEntitlementId;

    @Column(name = "payhead_value")
    private Integer payheadValue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payhead_id", referencedColumnName = "payhead_id")
    private PayrollPayHeadMaster payheadId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="payhead_config_id", referencedColumnName = "payhead_config_id")
    private PayrollPayHeadConfigMaster payheadConfigId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;

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
