package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="payroll.payroll_general_type_mst")

public class GeneralTypeMst {
	@Id 
    @Column(name="g_type_id")
	private Integer typeId;

	@Column(name="g_type_desc")
	private String typeDesc;

	@Column(name="is_active")
    private Boolean isActive;

	@Column(name = "display_order")
	private Integer displayOrder;

	@Column(name = "desc_in_reg_lang")
	private String descRegLang;

	@Column(name = "is_master_required")
	private Boolean isMasterRequired;
}
