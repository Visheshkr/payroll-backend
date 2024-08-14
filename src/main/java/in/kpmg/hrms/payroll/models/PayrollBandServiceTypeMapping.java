package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name="payroll.payroll_pay_band_service_type_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollBandServiceTypeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pay_band_id",referencedColumnName = "type_id")
    private GeneralMst payBandId;

    @ManyToOne
    @JoinColumn(name = "pc_serv_type_id", referencedColumnName = "id")
    private PayrollPayCommisionServTypeMapping pcServTypeId;

    @Column(name = "min_value")
    private Long minValue;

    @Column(name = "max_value")
    private Long maxValue;

    @Column(name = "grade_pay")
    private Long gradePay;

    @Column(name = "matrix_index")
    private Integer matrixIndex;

    @Column(name = "description")
    private String description;

    @Column(name = "min_serv_years")
    private Integer minServYears;

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
