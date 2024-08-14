package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollEducationalDetails;

@Repository
public interface PayrollEducationalDtlsRepo extends JpaRepository<PayrollEducationalDetails, Long> {

    List<PayrollEducationalDetails> findAllByEmpId_EmpId(Long empId);

}
