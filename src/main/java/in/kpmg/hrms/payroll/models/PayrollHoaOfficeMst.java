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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_office_hoa_mapping")
public class PayrollHoaOfficeMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "hoa_id", referencedColumnName = "id")
	private PayrollHoaMaster hoa;
	
	@OneToOne
	@JoinColumn(name = "office_id", referencedColumnName = "ofc_id")
	private PayrollOfficeMaster office;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "crt_on")
	@CreationTimestamp
	private Timestamp crtOn;
	
	@OneToOne
	@JoinColumn(name = "crt_by", referencedColumnName = "user_id")
	private UserMst crtBy;
	
}
