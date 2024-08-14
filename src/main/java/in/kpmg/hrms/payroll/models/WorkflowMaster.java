package in.kpmg.hrms.payroll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payroll_workflow_mst")
public class WorkflowMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workflow_id")
    private Long workflowId;

    @Column(name = "workflow_name")
    private String workflowName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "scheme_name")
    private String schemeName;
}
