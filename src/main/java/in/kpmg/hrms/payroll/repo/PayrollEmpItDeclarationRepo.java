package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollEmpItDeclarationMst;

@Repository
public interface PayrollEmpItDeclarationRepo extends JpaRepository<PayrollEmpItDeclarationMst, Integer> {

	List<PayrollEmpItDeclarationMst> findAllByEmpId_EmpId(Long empId);

	PayrollEmpItDeclarationMst findAllByEmpId_EmpIdAndItDec_Id(Long empId, Integer fyId);

}
