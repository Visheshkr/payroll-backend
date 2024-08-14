package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollPaymonthMst;


@Repository
public interface PayrollPaymonthMstRepo extends JpaRepository<PayrollPaymonthMst, Long> {

	 @Query("select pm.monthId as typeId, pm.monthDesc as typeName from PayrollPaymonthMst pm where pm.isActive = true  ")
    List<DropdownResponse> findAllActiveMonths();
	
	

}
