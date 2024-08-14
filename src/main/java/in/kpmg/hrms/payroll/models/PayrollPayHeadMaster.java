package in.kpmg.hrms.payroll.models;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_payhead_mst")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollPayHeadMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payhead_id")
    private Integer payheadId;

    @Column(name = "payhead_name")
    private String payheadName;

    @Column(name = "payhead_name_reg_lang")
    private String payheadNameRegLang;

    @Column(name = "payhead_code")
    private String payheadCode;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payhead_grp_id" , referencedColumnName = "type_id")
    private GeneralMst payheadGrpId;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payhead_type" , referencedColumnName = "type_id")
    private GeneralMst payHeadType;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "major_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst majorHead;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_major_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subMajorHead;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "minor_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst minorHead;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subHead;



    @OneToOne
    @JoinColumn(name = "detail_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst detailsHead;



    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_detail_head" , referencedColumnName = "type_id")
    private PayrollFinanceHeadmst subDetailsHead;


    @Column(name = "effective_from")
    private Timestamp effectiveFrom;

    @Column(name = "effective_to")
    private Timestamp effectiveTo;
    
    @Column(name = "is_active")
    private Boolean isActive;
    
    @OneToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;
    
    @CreationTimestamp
    @Column(name = "crt_on")
    private Timestamp crtOn;




}
