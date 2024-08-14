package in.kpmg.hrms.payroll.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Table(name = "payroll_employee_disability")
@Data
public class PayrollEmployeeDisability
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="emp_id", referencedColumnName = "emp_id")
    private PayrollEmployeeMaster empId;

    @OneToOne
    @JoinColumn(name = "disability_type", referencedColumnName = "type_id")
    private GeneralMst disabilityType;

    @Min(0)
    @Max(100)
    private Integer percentage;

    @Column(name = "is_active")
    private Boolean isActive;

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
