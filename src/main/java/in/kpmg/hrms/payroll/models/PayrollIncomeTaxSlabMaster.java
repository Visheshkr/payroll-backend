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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_income_tax_slab_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollIncomeTaxSlabMaster {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "it_slab_id")
	    private Integer itSlabId;
	    
	    @ManyToOne
	    @JoinColumn(name = "regime_type", referencedColumnName = "type_id")
	    private GeneralMst regimeType;
	    
	    @Column(name = "sequence_no")
	    private Integer sequenceNo;
	    
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "fy_id", referencedColumnName = "type_id")
	    private GeneralMst fyId;
	    
	    @Column(name = "income_from")
	    private Long incomeFrom;
	    
	    @Column(name = "income_to")
	    private Long incomeTo;
	    
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "tax_sub_category", referencedColumnName = "type_id")
	    private GeneralMst taxSubCategory;
	    
	    @OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "gender", referencedColumnName = "type_id")
	    private GeneralMst gender;
	    
	    @Column(name = "income_tax_rate")
	    private Long incomeTaxRate;
	    
	    @Column(name = "cess_percent")
	    private Long cessPercent;
	    
	    @Column(name = "effective_from")
	    private Date effectiveFrom;

	    @Column(name = "display_order")
	    private Integer displayOrder;

	    @Column(name="is_max_limit")
	    private Boolean isMaxLimit;
	    
	    @Column(name="is_active")
	    private Boolean isActive;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
	    private UserMst crtBy;

	    @Column(name = "crt_on", updatable = false)
	    @CreationTimestamp
	    private Timestamp crtOn;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
	    private UserMst updBy;

	    @Column(name = "upd_on")
	    @UpdateTimestamp
	    private Timestamp updOn;
	    
	    
	    
	    
}
