package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_fy_paymonth_mst")
public class PayrollFyPaymonthMst {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "fy_if", referencedColumnName = "type_id") 
	private GeneralMst fyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "month_id", referencedColumnName = "month_id")
	private PayrollPaymonthMst monthId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "current_year", referencedColumnName = "type_id")
	private GeneralMst currentYear;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name="display_order")
	private Integer displayOrder;

}
