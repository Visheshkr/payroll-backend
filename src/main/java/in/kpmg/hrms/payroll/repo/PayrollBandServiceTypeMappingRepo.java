package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayLevelRangeDto;
import in.kpmg.hrms.payroll.models.PayrollBandServiceTypeMapping;

@Repository
public interface PayrollBandServiceTypeMappingRepo extends JpaRepository<PayrollBandServiceTypeMapping, Integer>{

    List<PayrollBandServiceTypeMapping> findAllByOrderByCreatedOnDesc();

    List<PayrollBandServiceTypeMapping> findAllByPcServTypeId_IdAndPcServTypeId_IsActiveIsTrueAndIsActiveIsTrue(Integer id);

    Optional<PayrollBandServiceTypeMapping> findByPayBandId_TypeIdAndPayBandId_IsActiveIsTrueAndPcServTypeId_ServTypeId_IdAndPcServTypeId_ServTypeId_IsActiveIsTrueAndPcServTypeId_PcId_TypeIdAndPcServTypeId_PcId_IsActiveIsTrueAndPcServTypeId_IsActiveIsTrueAndIsActiveIsTrue(Long payLevelId, Integer serviceTypeId, Long pcId);

    Optional<PayrollBandServiceTypeMapping> findAllByIdAndIsActiveIsTrue(Integer id);

    @Query("SELECT pbstm.pcServTypeId.id as pcServTypeId, pbstm.minValue as minValue, pbstm.maxValue as maxValue  FROM PayrollBandServiceTypeMapping pbstm WHERE pbstm.payBandId.typeId=:payBandId AND pbstm.isActive=:b")
    List<PayLevelRangeDto> findRangeByPayBandIdAndIsActive(Long payBandId, Boolean b);

    
    @Query("select pbstm from PayrollBandServiceTypeMapping pbstm where pbstm.payBandId.typeId=:payBandId and pbstm.pcServTypeId.id =:pcServTypeId ")
    PayrollBandServiceTypeMapping findByPayBandIdAndPcServTypeId( Long payBandId,Integer pcServTypeId);

    
    @Query("select pbstm.payBandId.typeId as typeId, pbstm.payBandId.typeName as typeName, pbstm.id as pbServTypeId from PayrollBandServiceTypeMapping pbstm where pbstm.pcServTypeId.id =:serTypePcId and pbstm.isActive = true order by pbstm.payBandId.typeName asc ")
	List<DropdownResponse> findBySerTypeAndPc(Integer serTypePcId);

}
