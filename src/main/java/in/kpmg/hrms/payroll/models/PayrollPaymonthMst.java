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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_paymonth_mst")
public class PayrollPaymonthMst {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "month_id")
	private Integer monthId;
	
	@Column(name="month_desc")
	private String monthDesc;
	
	@Column(name="paymonth_no")
	private Integer paymonthNo;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name="display_order")
	private Integer displayOrder;
}
