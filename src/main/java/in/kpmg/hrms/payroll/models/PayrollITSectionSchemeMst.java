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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="payroll.payroll_it_section_scheme_mst")
public class PayrollITSectionSchemeMst {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
	@Column(name = "scheme_name")
	private String schemeName;
	
	@Column(name = "scheme_name_reg_lang")
	private String schemeNameRegLang;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "section_id" , referencedColumnName = "it_section_id")
	private PayrollIncomeTaxSectionMst sectionId;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name="display_order")
	private Integer displayOrder;
	
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
    
    @Column(name = "effective_from")
    private Date effectiveFrom;
	
}
