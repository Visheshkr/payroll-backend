package in.kpmg.hrms.payroll.models;
import java.sql.Timestamp;
import java.util.Date;

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
@Table(name="payroll.payroll_it_declaration_setup_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollTaxDeclarationMaster {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @OneToOne
    @JoinColumn(name = "fy_id", referencedColumnName = "type_id")
    private GeneralMst fyId;
    
    @Column(name = "it_dec_sub_from")
    private Date itDecSubFrom;
    
    @Column(name = "it_dec_sub_to")
    private Date itDecSubTo;
    
    @Column(name = "proof_inv_sub_from")
    private Date proofInvSubFrom;
    
    @Column(name = "proof_inv_sub_to")
    private Date proofInvSubTo;
    
    
    
	@Column(name = "is_active")
	private Boolean isActive;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on", updatable = false)
    @CreationTimestamp
    private Timestamp crtOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;
    

}
