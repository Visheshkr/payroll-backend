package in.kpmg.hrms.payroll.models;

import java.sql.Date;
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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_payhead_config_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollPayHeadConfigMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payhead_config_id")
    private Integer payheadConfigId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payhead_id" , referencedColumnName = "payhead_id")
    private PayrollPayHeadMaster payHeadId;

    @OneToOne
    @JoinColumn(name = "pay_commission_id", referencedColumnName = "type_id")
    private GeneralMst payCommissionId;

    @OneToOne
    @JoinColumn(name = "service_type", referencedColumnName = "id")
    private PayrollServiceTypeMaster serviceType;

    @OneToOne
    @JoinColumn(name = "pay_level_id", referencedColumnName = "type_id")
    private GeneralMst payLevelId;

    @Column(name = "is_fixed_amt")
    private Boolean isFixedAmt;

    @Column(name = "max_amt")
    private Integer maxAmt;

    @Column(name = "effective_from")
    private Timestamp effectiveFrom;

    @Column(name = "formula")
    private String formula;
    
    @Column(name = "tier_id")
    private Integer tierId;

    @Column(name = "circulated_date")
    private Date circulatedDate;

    @Column(name = "cta_entitlement_id")
    private Integer ctaEntitlementId;
    
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_dsgn_id" , referencedColumnName = "id")
    private PayrollDesignationDeptMapping deptDegnId;
    
    @Column(name = "is_active")
    private Boolean isActive;






}
