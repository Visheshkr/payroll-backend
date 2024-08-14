package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Data
@Table(name = "payroll.payroll_hra_rate_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollHRARateMst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer tierId;

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

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst createdBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp createdOn;


    @Column(name = "upd_by")
    private Integer updatedBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updatedOn;


}
