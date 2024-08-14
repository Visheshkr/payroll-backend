package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollFyMonthStatusMst;

@Repository
public interface PayrollFyMonthStatusRepo extends JpaRepository<PayrollFyMonthStatusMst, Long> {

	
	@Query("select fym from PayrollFyMonthStatusMst fym where fym.fyMonthId.id =:fyMonthId and fym.hoaId.id=:hoaId ")
	PayrollFyMonthStatusMst findByFyMonthId( Long fyMonthId, Long hoaId);

	
	@Query("select fyh from PayrollFyMonthStatusMst fyh where fyh.isActive = true "
			+ " and (:fyId is null or fyh.fyMonthId.fyId.typeId =:fyId) "
			+ " and (:hoaId is null or fyh.hoaId.id=:hoaId ) ")
	List<PayrollFyMonthStatusMst> findByHoaOrFy( Long hoaId, Long fyId);

}
