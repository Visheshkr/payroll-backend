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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name= "payroll.payroll_payhead_pc_mapping")
public class PayheadPcMapping {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "payhead_id",referencedColumnName = "payhead_id")
    private PayrollPayHeadMaster payheadId;

    @OneToOne
    @JoinColumn(name = "pc_id", referencedColumnName = "type_id")
    private GeneralMst pcId;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name = "display_order")
    private Integer displayOrder;
    
    @OneToOne
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
