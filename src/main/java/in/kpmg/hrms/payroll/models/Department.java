package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_department_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dept_id")
	private Integer deptId;


	@Column(name = "dept_name")
	private String name;


	@Column(name = "dept_telugu_name")
	private String deptNameTelugu;


	@Column(name = "dept_code")
	private String code;


	@Column(name = "crt_by")
	private Integer createdBy;

	@Column(name = "crt_on")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	@CreationTimestamp
	private Timestamp createdOn;

	@Column(name = "upd_by")
	private Integer updatedBy;

	@Column(name = "upd_on")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	private Timestamp updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "immdte_parnt_dept_id")
	private Integer orgId;

	@Column(name = "dept_order")
	private Integer deptOrder;

	@Column(name = "is_enrollment_applicable")
	private Boolean isEnrollmentApplicable;

	@Column(name = "is_cms_applicable")
	private Boolean isCmsApplicable;

}
