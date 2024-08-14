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
@Table(name = "payroll_location_mst")
public class PayrollLocationMaster
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private Long locId;

    @Column(name = "loc_name")
    private String locName;

    @OneToOne
    @JoinColumn(name = "loc_type_id",referencedColumnName = "type_id")
    private GeneralMst locTypeId;

    @Column(name = "loc_code")
    private String locCode;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "display_order")
    private Integer displayOrder;

    @OneToOne
    @JoinColumn(name = "loc_parent_id", referencedColumnName = "loc_id")
    private PayrollLocationMaster locParentId;


}
