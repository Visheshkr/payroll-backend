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
@Table(name = "payroll_step_role_mapping")
public class StepRoleMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_role_id")
    private Long stepRoleId;

    @OneToOne
    @JoinColumn(name = "step_id", referencedColumnName = "step_id")
    private StepMaster stepId;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RoleMst roleId;

    @Column(name = "case_status_name")
    private String caseStatusName;
}
