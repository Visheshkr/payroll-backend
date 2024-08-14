package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_income_tax_deduction_head_mst")
public class PayrollIncomeTaxDeductionHeadMaster {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@Column(name = "deduction_head_name")
    private String deductionHeadName;
	
	@OneToOne
	@JoinColumn(name = "tax_effect_id", referencedColumnName = "type_id")
	private GeneralMst taxEffectId;

	
	@Column(name = "max_amt_limit")
    private Integer maxAmtLimit;
	
	@OneToOne
	@JoinColumn(name = "payhead_effect", referencedColumnName = "type_id")
	private GeneralMst payheadEffect;
	
	@Column(name = "under_tax_limit")
    private Boolean underTaxLimit;
	
	@Column(name = "display_order")
	private Integer displayOrder;
    
    @OneToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst createdBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;


    @Column(name = "upd_by")
    private Integer  updatedBy;

    @UpdateTimestamp
    @Column(name = "upd_on")
    private Timestamp updatedOn;

	
	
}
