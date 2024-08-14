package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollEmpFamily;

@Repository
public interface PayrollEmpFamilyRepo extends JpaRepository<PayrollEmpFamily, Long> {

    List<PayrollEmpFamily> findAllByEmpId_EmpIdAndIsActiveIsTrue(Long empId);
}
