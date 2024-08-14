package in.kpmg.hrms.payroll.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.responseDto.DropdownDataDto;
import in.kpmg.hrms.payroll.models.PayrollFyPaymonthMst;

@Repository
public interface PayrollFyPaymonthMstRepo extends JpaRepository<PayrollFyPaymonthMst, Long> {

	@Query("select fym from PayrollFyPaymonthMst fym where fym.currentYear.typeId=:currentYear and fym.monthId.monthId=:monthId ")
	PayrollFyPaymonthMst findByCurrentYearAndMonthId(@NotNull(message = "Current Year is mandatory") Long currentYear,
			@NotNull(message = "Month ID is mandatory") Integer monthId);

	@Query("select fym from PayrollFyPaymonthMst fym where fym.id is not null and (:fyId is null or fym.fyId.typeId=:fyId) order by fym.id desc")
	List<PayrollFyPaymonthMst> findAllByFyIdOrderByIdDesc(Long fyId);

	List<PayrollFyPaymonthMst> findAllByFyId_TypeIdAndFyId_IsActiveIsTrueAndMonthId_IsActiveIsTrueAndCurrentYear_IsActiveIsTrueAndIsActiveIsTrueOrderByCurrentYear_TypeNameAscMonthId_PaymonthNoAsc(Long fy);

	Optional<PayrollFyPaymonthMst> findByIdAndIsActiveIsTrue(Long id);

	@Query("select fym.id as typeId,  concat(fym.monthId.monthDesc || ' ' || fym.currentYear.typeName) as typeName   from PayrollFyPaymonthMst fym where fym.isActive = true order by fym.currentYear.typeName desc ")
	List<DropdownDataDto> getFyMonth();
}
