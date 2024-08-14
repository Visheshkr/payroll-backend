package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_general_type_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollGeneralTypeMaster {

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
