package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollBandServiceTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollPayMatrix;

@Repository
public interface PayMatrixRepo extends JpaRepository<PayrollPayMatrix, Long> {

	@Query("select pm from PayrollPayMatrix pm where pm.payBandId.id =:payBandId ")
	PayrollPayMatrix findByPayBandId(@NotNull(message = "Pay Band Id is mandatory") Integer payBandId);

	List<PayrollPayMatrix> findAllByPayBandId(@NotNull(message = "Pay Band Id is mandatory") Integer payBandId);

	List<PayrollPayMatrix> findByPayBandId(Optional<PayrollBandServiceTypeMapping> payBand);

	List<PayrollPayMatrix> findAllByPayBandIdOrderByValueAsc(PayrollBandServiceTypeMapping payrollBandServiceTypeMapping);

	List<PayrollPayMatrix> findAllByOrderByCrtOnDesc();

}

