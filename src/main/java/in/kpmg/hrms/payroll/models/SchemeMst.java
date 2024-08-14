package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payroll.payroll_scheme_mst")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchemeMst {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "scheme_id")
	private Integer schemeId;

	@Column(name = "scheme_name")
	private String schemeName;

	@Column(name = "is_active")
	private Boolean isActive;
}
