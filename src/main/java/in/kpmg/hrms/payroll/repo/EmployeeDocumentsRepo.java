package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.EmployeeDocuments;

@Repository
public interface EmployeeDocumentsRepo extends JpaRepository<EmployeeDocuments, Long> {

    List<EmployeeDocuments> findAllByEmpId_EmpIdAndIsActiveIsTrue(Long empId);

}
