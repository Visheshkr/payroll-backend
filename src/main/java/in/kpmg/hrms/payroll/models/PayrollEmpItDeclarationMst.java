package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "payroll.payroll_emp_it_declaration")
@Entity
public class PayrollEmpItDeclarationMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;
	
    
    @OneToOne
    @JoinColumn(name = "it_declaration_id", referencedColumnName = "id")
    private PayrollTaxDeclarationMaster itDec;
    
    @OneToOne
    @JoinColumn(name = "regime_id", referencedColumnName = "type_id")
    private GeneralMst regimeType;
    
    @OneToMany(mappedBy = "empItDec",cascade = CascadeType.ALL)
    private List<PayrollEmpItDeclarationSchemeMst> empItDecSchemes;
    
    @Column(name = "other_income")
    private Long otherIncome;
    
    
    @OneToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @CreationTimestamp
    @Column(name = "crt_on")
    private Timestamp crtOn;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @UpdateTimestamp
    @Column(name = "upd_on")
    private Timestamp updOn;
    
    
    
    

}
