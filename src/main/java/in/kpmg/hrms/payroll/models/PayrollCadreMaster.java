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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payroll_cadre_mst")
public class PayrollCadreMaster {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cadre_name")
    private String cadreName;

    @Column(name = "cadre_name_reg_lang")
    private String cadreNameRegLang;

    @Column(name = "retirement_age")
    private Integer retirementAge;

    @OneToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Department deptId;

    @Column(name = "commencement_id")
    private Date commencementDate;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "display_order")
    private Integer displayOrder;

    @OneToOne
    @JoinColumn(name = "service_type_id",referencedColumnName = "id")
    private PayrollServiceTypeMaster serviceTypeId;

    @ManyToOne
    @JoinColumn(name = "crt_by", referencedColumnName = "user_id")
    private UserMst crtBy;

    @Column(name = "crt_on")
    @CreationTimestamp
    private Timestamp crtOn;

    @ManyToOne
    @JoinColumn(name = "upd_by", referencedColumnName = "user_id")
    private UserMst updBy;

    @Column(name = "upd_on")
    @UpdateTimestamp
    private Timestamp updOn;


}
