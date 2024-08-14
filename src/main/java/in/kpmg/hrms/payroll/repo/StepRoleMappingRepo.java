package in.kpmg.hrms.payroll.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.StepRoleMapping;

@Repository
public interface StepRoleMappingRepo extends JpaRepository<StepRoleMapping, Long> {
}
