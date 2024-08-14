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
@Table(name = "payroll_hoa_mst")
public class PayrollHoaMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exported_hoa_id")
    private Long exportedHoaId;

    @Column(name = "demand_no")
    private Long demandNo;

    @Column(name = "demand_desc")
    private String demandDesc;

    @Column(name = "demand_desc_reg_lang")
    private String demandDescRegLang;

    @OneToOne
    @JoinColumn(name = "major_head_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst majorHeadId;

    @OneToOne
    @JoinColumn(name = "sub_major_head_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subMajorHeadId;

    @OneToOne
    @JoinColumn(name = "minor_head_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst minorHeadId;

    @OneToOne
    @JoinColumn(name = "detail_head_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst detailHeadId;

    @OneToOne
    @JoinColumn(name = "sub_detail_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subDetailId;

    @OneToOne
    @JoinColumn(name = "sub_head_id", referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subHeadId;

    @Column(name = "charged_voted")
    private String chargedVoted;

    private Long hoa;
}
