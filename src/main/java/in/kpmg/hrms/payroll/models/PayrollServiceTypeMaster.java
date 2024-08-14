package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="payroll.payroll_service_type_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollServiceTypeMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_type_reg_lang")
    private String serviceTypeRegLang;


    @Column(name = "service_type_code")
    private String serviceTypeCode;

    @Column(name = "description")
    private String description;

    @Column(name = "effective_from")
    private Timestamp effectiveFrom;

    @Column(name = "effective_to")
    private Timestamp effectiveTo;

    @Column(name = "display_order")
    private Integer displayOrder;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToOne
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




}
