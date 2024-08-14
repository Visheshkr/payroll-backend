package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payroll_group_mst")
public class PayrollGroupMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grp_id")
    private Long grpId;

    @Column(name = "grp_code")
    private String grpCode;

    @Column(name = "grp_name")
    private String grpName;

    @OneToOne
    @JoinColumn(name = "hoa_id", referencedColumnName = "id")
    private PayrollHoaMaster hoaId;

    @OneToOne
    @JoinColumn(name = "office_id", referencedColumnName = "ofc_id")
    private PayrollOfficeMaster officeId;

    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "display_order")
    private Integer displayOrder;

    @OneToOne
    @JoinColumn(name = "status", referencedColumnName = "step_role_id")
    private StepRoleMapping status;

}
