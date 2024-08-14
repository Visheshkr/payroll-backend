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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_pay_commission_serv_type_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollPayCommisionServTypeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "serv_type_id" , referencedColumnName = "id")
    private PayrollServiceTypeMaster servTypeId;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pc_id" , referencedColumnName = "type_id")
    private GeneralMst pcId;

    @Column(name = "effective_from")
    private Timestamp effectiveFrom;
    
    @Column(name = "pc_Desc")
    private String pcDesc;

    @Column(name = "effective_to")
    private Timestamp effectiveTo;

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
    private Integer  updatedBy;

    @UpdateTimestamp
    @Column(name = "upd_on")
    private Timestamp updatedOn;




}
