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
@Table(name = "payroll.payroll_fy_wise_hoa_payroll_status")
public class PayrollFyMonthStatusMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "fy_month_id", referencedColumnName = "id")
	private PayrollFyPaymonthMst fyMonthId;

	
	@OneToOne
	@JoinColumn(name = "hoa_id", referencedColumnName = "id")
	private PayrollHoaMaster hoaId;

	@OneToOne
	@JoinColumn(name = "status", referencedColumnName = "type_id")
	private GeneralMst status;

	@OneToOne
	@JoinColumn(name = "crt_by", referencedColumnName = "user_id")
	private UserMst crtBy;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "crt_on")
	@CreationTimestamp
	private Timestamp crtOn;

	@OneToOne
	@JoinColumn(name = "upd_by", referencedColumnName = "user_id")
	private UserMst updBy;

	@Column(name = "upd_on")
	@UpdateTimestamp
	private Timestamp updOn;

}
