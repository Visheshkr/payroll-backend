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

@Entity
@Table(name = "payroll.payroll_role_menu_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleMenuMappingMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mapping_id")
	private Integer rightId;

	@OneToOne
	@JoinColumn(name = "role_id", nullable = false)
	private RoleMst roleDtls;

	@OneToOne
	@JoinColumn(name = "menu_id", nullable = false)
	private MenuMst menuDtl;

	@Column(name = "is_viewable")
	private Boolean isView;

	@Column(name = "is_editable")
	private Boolean isEdit;


	@Column(name = "is_downloadable")
	private Boolean isDownload;


	@Column(name = "crt_by", updatable = false)
	private Integer createdBy;

	@Column(name = "crt_on", updatable = false)
	@CreationTimestamp
	private Timestamp createdOn;

	@Column(name = "upd_by")
	private Integer updatedBy;

	@Column(name = "upd_on")
	private Timestamp updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;



}
