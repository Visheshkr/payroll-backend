package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollEmployeeDisability;

@Repository
public interface PayrollEmployeeDisabilityRepo extends JpaRepository<PayrollEmployeeDisability, Long> {

    List<PayrollEmployeeDisability> findAllByEmpId_EmpIdAndIsActiveIsTrue(Long empId);

}
