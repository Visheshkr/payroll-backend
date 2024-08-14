package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "payroll.payroll_emp_salary_processing_details")
@Entity
public class EmpSalaryProcessingDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="emp_salary_id", referencedColumnName = "id")
	private EmpSalaryProcessingMst empSalaryId;

	@OneToOne
	@JoinColumn(name="payhead_id", referencedColumnName = "payhead_id")
	private PayrollPayHeadMaster payheadId;

	@Column(name = "payhead_value")
	private Integer payheadValue;

	@OneToOne
	@JoinColumn(name="payhead_config_id", referencedColumnName = "payhead_config_id")
	private PayrollPayHeadConfigMaster payheadConfigId;
	

    @Column(name = "crt_by")
    private Integer crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp crtOn;

    @Column(name = "upd_by")
    private Integer updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;

}
