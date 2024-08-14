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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_emp_it_declaration_schemes")
public class PayrollEmpItDeclarationSchemeMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "emp_it_dec_id", referencedColumnName = "id")
    private PayrollEmpItDeclarationMst empItDec;
	
    
    @OneToOne
    @JoinColumn(name = "section_scheme_id", referencedColumnName = "id")
    private PayrollITSectionSchemeMst sectionSchemeId;
    
    
    @Column(name = "value")
    private Long schemeVal;
    
    @OneToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @CreationTimestamp
    @Column(name = "crt_on")
    private Timestamp crtOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;

}
