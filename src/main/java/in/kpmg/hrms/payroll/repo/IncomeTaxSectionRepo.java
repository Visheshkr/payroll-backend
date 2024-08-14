package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.responseDto.ItDeclarationSectionResponse;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxSectionMst;

@Repository
public interface IncomeTaxSectionRepo extends JpaRepository<PayrollIncomeTaxSectionMst, Integer>{

	@Query("select phm.id as typeId, phm.sectionName as typeName from PayrollIncomeTaxSectionMst phm where phm.isActive = true ")
	List<DropdownResponse> findAllActiveSections();

	
	@Query("select its.sectionName as sectionName, its.id as sectionId, its.maxDedAmt as maxLimit   from PayrollIncomeTaxSectionMst its where its.regimeType.typeId=:regType and its.isActive = true order by its.id asc ")
	List<ItDeclarationSectionResponse> findSectionsByRegime(Long regType);

}
