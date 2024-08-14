package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxDeductionHeadMaster;

@Repository
public interface IncomeTaxDeductionHeadRepo extends JpaRepository<PayrollIncomeTaxDeductionHeadMaster, Integer>{

	@Query("select itdh.id as typeId, itdh.deductionHeadName as typeName  from PayrollIncomeTaxDeductionHeadMaster itdh  ")
	List<DropdownResponse> findDedcutionHead();
	
	@Query("SELECT p FROM PayrollIncomeTaxDeductionHeadMaster p " +
		       "WHERE (:deductionHeadName IS NULL OR LOWER(p.deductionHeadName) LIKE LOWER(CONCAT('%', :deductionHeadName, '%'))) " +
		       "AND (:taxEffectId IS NULL OR p.taxEffectId.typeId = :taxEffectId) ORDER BY p.createdOn ASC")
	    List<PayrollIncomeTaxDeductionHeadMaster> findByDeductionHeadNameAndTaxEffectId( String deductionHeadName, Long taxEffectId );
}
