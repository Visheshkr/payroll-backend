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

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_menu_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Integer menuId;

	@Column(name = "link")
	private String link;

	@Column(name = "icon")
	private String icon;

	@Column(name = "menu_name")
	private String name;

	@Column(name = "menu_telugu_name")
	private String nameTelugu;

	@Column(name = "parent_menu_id")
	private Integer parentMenuId;

	@Column(name = "is_default")
	private Boolean isDefault;

	@Column(name = "crt_by", updatable = false)
	private Integer createdBy;

	@Column(name = "crt_on", updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	@CreationTimestamp
	private Timestamp createdOn;

	@Column(name = "upd_by")
	private Integer updatedBy;

	@Column(name = "upd_on")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
	// @UpdateTimestamp
	private Timestamp updatedOn;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "display_order")
	private Integer displayOrder;

	@OneToOne
	@JoinColumn(name = "parent_menu_id", nullable = true, insertable = false, updatable = false)
	private MenuMst mn;

}
