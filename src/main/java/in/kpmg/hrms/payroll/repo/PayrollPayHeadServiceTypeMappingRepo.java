package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollPayHeadServiceTypeMapping;

@Repository
public interface PayrollPayHeadServiceTypeMappingRepo extends JpaRepository<PayrollPayHeadServiceTypeMapping, Integer> {

	List<PayrollPayHeadServiceTypeMapping> findByServiceTypeId_ServiceType(String serviceType);

	List<PayrollPayHeadServiceTypeMapping> findByServiceTypeId_IdOrderByIdDesc(Integer serviceTypeId);
	
	PayrollPayHeadServiceTypeMapping findByPayheadId_PayheadNameAndServiceTypeId_ServiceType(@NotNull(message = "Pay Head Name is mandatory") String payheadName,
			@NotNull(message = "Service Type is mandatory") String serviceType);

	Optional<PayrollPayHeadServiceTypeMapping> findByPayheadId_PayheadIdAndServiceTypeId_Id(Integer payheadId, Integer serviceTypeId);

	List<PayrollPayHeadServiceTypeMapping> findAllByOrderByCrtOnDesc();

}
