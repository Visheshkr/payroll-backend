package in.kpmg.hrms.payroll.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionSchemeDropdownDto;
import in.kpmg.hrms.payroll.dtos.responseDto.ItDeclarationSchemesResponse;
import in.kpmg.hrms.payroll.models.PayrollITSectionSchemeMst;

@Repository
public interface PayrollItSectionSchemeMstRepo extends JpaRepository<PayrollITSectionSchemeMst, Integer> {

	List<PayrollITSectionSchemeMst> findAllByOrderByCrtOnDesc();

	List<PayrollITSectionSchemeMst> findBySectionIdAndSectionId_SectionName(Integer sectionId, @NotNull(message = "Section Name is mandatory") String sectionName);

	PayrollITSectionSchemeMst findBySchemeNameAndSectionId_Id(@NotNull(message = "Scheme Name is mandatory") String schemeName,
			    										   @NotNull(message = "Section Id is mandatory") Integer sectionId);

	@Query("SELECT pits.sectionName as sectionName, pits.id as sectionId FROM PayrollIncomeTaxSectionMst pits WHERE pits.isActive=:b")
	List<ItSectionSchemeDropdownDto> findByIsActive(Boolean b);

	@Query("select itssm.schemeName as schemeName, itssm.id as schemeId    from PayrollITSectionSchemeMst itssm where itssm.sectionId.id =:sectionId and itssm.isActive = true ")
	List<ItDeclarationSchemesResponse> findBySectionId(Integer sectionId);

}
