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
@Table(name = "payroll.payroll_income_tax_section_ded_head_mapping")
public class ItSectionDedHeadMapping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Integer id;
	
	@OneToOne
	@JoinColumn(name = "it_section_id", referencedColumnName = "it_section_id")
	private PayrollIncomeTaxSectionMst itSectionId;
	
	@OneToOne
	@JoinColumn(name = "deduction_head_id", referencedColumnName = "id")
	private PayrollIncomeTaxDeductionHeadMaster deductionHeadId;
	
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
