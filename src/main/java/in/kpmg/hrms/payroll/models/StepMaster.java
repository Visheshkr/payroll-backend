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
@Table(name = "payroll_step_mst")
public class StepMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private Long stepId;

    @Column(name = "step_name")
    private String stepName;

    @OneToOne
    @JoinColumn(name = "workflow_id", referencedColumnName = "workflow_id")
    private WorkflowMaster workflowId;
}
