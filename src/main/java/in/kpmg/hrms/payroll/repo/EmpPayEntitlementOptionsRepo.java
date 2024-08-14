package in.kpmg.hrms.payroll.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.EmpPayEntitlementOptions;

@Repository
public interface EmpPayEntitlementOptionsRepo extends JpaRepository<EmpPayEntitlementOptions, Long> {

    Optional<EmpPayEntitlementOptions> findByEmpId_EmpId(Long empId);

}
