package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollPayCommisionServTypeMapping;

@Repository
public interface PayrollPayCommisionServTypeRepo extends JpaRepository<PayrollPayCommisionServTypeMapping,Integer> {


    Optional<PayrollPayCommisionServTypeMapping> findByServTypeId_IdAndPcId_TypeId(Integer servTypeId, Long pcId);

    List<PayrollPayCommisionServTypeMapping> findByIsActiveTrueOrderByCreatedOnDesc();

    List<PayrollPayCommisionServTypeMapping> findAllByServTypeId_IdAndServTypeId_IsActiveIsTrueAndPcId_IsActiveIsTrueAndIsActiveIsTrue(Integer serviceTypeId);

    
    @Query("select pcsm.pcId.typeId as typeId, pcsm.pcId.typeName as typeName , pcsm.pcId.typeCode as typeCode , pcsm.id as pcServiceTypeId from PayrollPayCommisionServTypeMapping pcsm where pcsm.servTypeId.id =:sId and pcsm.isActive =:b  ")
	List<DropdownResponse> findByServTypeIdAndIsActive(Integer sId, boolean b);
}
