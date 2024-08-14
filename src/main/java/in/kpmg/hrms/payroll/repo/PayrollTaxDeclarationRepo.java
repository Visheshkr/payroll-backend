package in.kpmg.hrms.payroll.repo;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationWindow;
import in.kpmg.hrms.payroll.models.PayrollTaxDeclarationMaster;

@Repository
public interface PayrollTaxDeclarationRepo extends JpaRepository<PayrollTaxDeclarationMaster, Integer>{

	@Query("select itd from PayrollTaxDeclarationMaster itd order by itd.id desc ")
	List<PayrollTaxDeclarationMaster> findAllOrderByIdDesc();

	@Query("select itd from PayrollTaxDeclarationMaster itd where itd.isActive = true order by itd.id desc ")
	List<PayrollTaxDeclarationMaster> findAllActiveWindowOrderByIdDesc();

}