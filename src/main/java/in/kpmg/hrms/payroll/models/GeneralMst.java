package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payroll.payroll_general_mst")
public class GeneralMst {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long typeId;

    @Column(name="type_name")
    private String typeName;

    @Column(name="type_name_in_reg_lang")
    private String typeNameRegLang;

    @Column(name="type_code")
    private String typeCode;

    @Column(name = "g_type_id", insertable = false, updatable = false )
    private Integer generalId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "g_type_id" , referencedColumnName = "g_type_id")
    private PayrollGeneralTypeMaster generalTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_type_id" , referencedColumnName = "type_id")
    private GeneralMst parentTypeId;

    @Column(name="is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst createdBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;


    @Column(name = "upd_by")
    private Integer  updatedBy;

    @UpdateTimestamp
    @Column(name = "upd_on")
    private Timestamp updatedOn;

    @Column(name = "display_order")
    private Integer displayOrder;


}
