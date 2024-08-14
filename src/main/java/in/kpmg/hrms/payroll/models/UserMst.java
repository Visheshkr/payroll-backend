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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_user_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "login_name")
	private String userName;

	@Column(name = "emp_code")
	private String empCode;

	@Column(name = "pswd")
	// @JsonIgnore
	private String password;

	@Column(name = "user_name")
	private String fullname;

	@Column(name = "email_id")
	private String email;

	@Column(name = "mobile_no")
	private Long mobileNo;


	@Column(name = "crt_by")
	private Integer createdBy;

	@Column(name = "crt_on", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	@CreationTimestamp
	private Timestamp createdOn;

	@Column(name = "upd_by")
	private Integer updatedBy;

	@Column(name = "upd_on")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	@UpdateTimestamp
	private Timestamp updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "reporting_person_id")
	private Integer managerId;

	@Column(name = "cfms_id")
	private String cfmsId;

	@Column(name = "pan_card")
	private String pancard;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	@Where(clause = "is_active='true'")
	private List<UserDeptDesgMapMst> schmDeptDsgMaps;
}