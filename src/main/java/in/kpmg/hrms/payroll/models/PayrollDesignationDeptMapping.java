package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="payroll.payroll_designation_dept_mapping")
@AllArgsConstructor
@NoArgsConstructor
public class PayrollDesignationDeptMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "dsgn_id",referencedColumnName = "desgn_id")
    private DesignationMst dsgnId;

    @OneToOne
    @JoinColumn(name = "dept_id", referencedColumnName = "dept_id")
    private Department deptId;

    @Column(name = "is_active")
    private Boolean isActive;

}
