package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayheadPcMapping;

@Repository
public interface PayheadPcMappingRepo extends JpaRepository<PayheadPcMapping, Integer> {

	
	@Query("select phpc.payheadId.payheadId as typeId, phpc.payheadId.payheadCode as typeCode,"
			+ " phpc.payheadId.payheadName as typeName from PayheadPcMapping phpc where phpc.pcId.typeId=:pcId and phpc.isActive =:b ")
	List<DropdownResponse> findByPcIdAndIsActive(Long pcId, boolean b);

}
