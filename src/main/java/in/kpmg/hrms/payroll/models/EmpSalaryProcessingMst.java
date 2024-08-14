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
@Entity
@Table(name = "payroll.payroll_emp_salary_processing")
public class EmpSalaryProcessingMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fy_paymonth_id", referencedColumnName = "id")
    private PayrollFyPaymonthMst fyPayMonthId;
	
	@OneToMany(mappedBy = "empSalaryId",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EmpSalaryProcessingDetails> empSalary;


    @Column(name = "paid_days")
    private Integer paidDays;
    
    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "type_id")
    private GeneralMst status;

    
    @Column(name = "payhead_ref_no")
    private String tempRefNo;
    
    
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
