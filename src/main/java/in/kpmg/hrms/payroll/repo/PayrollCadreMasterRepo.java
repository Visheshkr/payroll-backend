package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollCadreMaster;

@Repository
public interface PayrollCadreMasterRepo extends JpaRepository<PayrollCadreMaster, Long> {

    List<PayrollCadreMaster> findAllByServiceTypeId_IdAndIsActiveIsTrue(Integer serviceTypeId);

    @Query(value = "Select u.retirementAge from PayrollCadreMaster u where u.id=:cadreId")
    Integer getRetirementAge(Long cadreId);

}
