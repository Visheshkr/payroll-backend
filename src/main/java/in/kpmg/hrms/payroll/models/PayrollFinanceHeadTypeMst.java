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
@Data
@Table(name="payroll.payroll_finance_head_type_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollFinanceHeadTypeMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_type_id")
	private Integer ftypeId;

	@Column(name = "f_type_name")
	private String fTypeName;

	@Column(name = "display_order")
	private Integer order;

	@Column(name = "is_active")
	private Boolean isActive;



}
