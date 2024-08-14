package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_hra_rate_mst_audit")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollHRARateMstAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "audit_id")
    private Integer auditId;

    @Column(name = "tier_name")
    private String tierName;

    @Column(name = "tier_name_reg_lang")
    private String tierNameRegLang;

    @Column(name = "rate_percentage")
    private Integer ratePercentage;

    @Column(name = "description")
    private String description;

    @Column(name = "effective_from")
    private Date effectiveFrom;

    @Column(name = "effective_to")
    private Date effectiveTo;

    @Column(name = "display_order")
    private Integer displayOrder;

//    crt_by -> user_id of user_mst
    @Column(name = "crt_by")
    private Integer createdBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;

//    upd_by -> user_id of user_mst
    @Column(name = "upd_by")
    private Integer updatedBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updatedOn;

    @Column(name = "audited_on", updatable = false)
    @CreationTimestamp
    private Timestamp auditedOn;

}
