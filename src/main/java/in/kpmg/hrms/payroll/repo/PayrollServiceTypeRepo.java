package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;

@Repository
public interface PayrollServiceTypeRepo extends JpaRepository<PayrollServiceTypeMaster,Integer> {


//    Optional<PayrollServiceTypeMater> findByServiceTypeAndEmployeeTypeId(String upperCase, Integer employeeTypeId);

    Optional<PayrollServiceTypeMaster> findByServiceType(String upperCase);

//    List<PayrollServiceTypeMaster> findAllByEmployeeTypeId_TypeIdAndEmployeeTypeId_IsActiveIsTrueAndIsActiveIsTrue(Long employeeType);

    List<PayrollServiceTypeMaster> findAllByIsActiveIsTrueOrderByServiceType();

    @Query("select sm.serviceType as typeName, sm.serviceTypeCode as typeCode, sm.id as typeId from PayrollServiceTypeMaster sm where sm.isActive = true ")
	List<DropdownResponse> findActiveServiceTypes();

    List<PayrollServiceTypeMaster> findAllByOrderByCreatedOnDesc();
}
