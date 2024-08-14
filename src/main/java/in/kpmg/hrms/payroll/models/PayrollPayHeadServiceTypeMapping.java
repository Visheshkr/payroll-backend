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
@AllArgsConstructor
@NoArgsConstructor
@Table(name="payroll_payhead_service_type_mapping")
public class PayrollPayHeadServiceTypeMapping {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name="payhead_id", referencedColumnName="payhead_id")
    private PayrollPayHeadMaster payheadId;
    
    @ManyToOne
    @JoinColumn(name="service_type_id", referencedColumnName="id")
    private PayrollServiceTypeMaster serviceTypeId;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @Column(name = "effective_from")
    private Date effectiveFrom;
    
    @Column(name = "effective_to")
    private Date effectiveTo;
    
    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp crtOn;

    @Column(name = "upd_by")
    private Integer  updBy;

    @UpdateTimestamp
    @Column(name = "upd_on")
    private Timestamp updOn;
}
