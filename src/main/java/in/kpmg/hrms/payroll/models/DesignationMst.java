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
@Table(name = "payroll.payroll_designation_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DesignationMst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "desgn_id")
	private Integer id;

	@Column(name = "desgn_name")
	private String name;


	@Column(name = "desgn_telugu_name")
	private String nameTelugu;


	@Column(name = "desgn_code")
	private String code;

	@Column(name = "is_active")
	private Boolean isActive;


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
	// @UpdateTimestamp
	private Timestamp updatedOn;

}