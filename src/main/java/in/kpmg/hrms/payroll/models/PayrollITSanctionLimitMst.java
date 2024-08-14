package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Date;

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
@Table(name = "payroll.payroll_income_tax_sanctioned_limit_mst")
public class PayrollITSanctionLimitMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;	
	
	@OneToOne
	@JoinColumn(name = "it_section_id", referencedColumnName = "it_section_id")
	private PayrollIncomeTaxSectionMst itsectionId;
	

    @Column(name = "maximum_ded_amt")
    private Long maxDedAmt;
    
    @Column(name = "effective_from")
    private Date effectiveFrom;
    
    @Column(name = "effective_to")
    private Date effectiveTo;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
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
