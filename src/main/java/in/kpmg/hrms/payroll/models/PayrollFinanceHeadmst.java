package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name="payroll.payroll_finance_head_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollFinanceHeadmst {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="type_id")
	private Integer typeId;

	@Column(name="type_name")
	private String typeName;
	
	@Column(name="type_code")
	private Integer typeCode;

	@Column(name="is_active")
    private Boolean isActive;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "type_name_reg_lang")
	private String typeNameRegLang;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_type_id",  referencedColumnName = "type_id")
	private PayrollFinanceHeadmst parentId;
	
	
	@OneToOne
	@JoinColumn(name = "f_type_id",  referencedColumnName = "f_type_id")
	private PayrollFinanceHeadTypeMst ftypeId;
	
	@OneToOne
	@JoinColumn(name = "crt_by", referencedColumnName = "user_id")
	private UserMst crtBy;
	
	@CreationTimestamp
	@Column(name = "crt_On")
	private Timestamp crtOn;
	
	@OneToOne
	@JoinColumn(name = "upd_by", referencedColumnName = "user_id")
	private UserMst updBy;
	
	@UpdateTimestamp
	@Column(name = "upd_On")
	private Timestamp updOn;
		

}
