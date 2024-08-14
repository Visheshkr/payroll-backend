package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;

@Repository
public interface PayrollHraRateRepo extends JpaRepository<PayrollHRARateMst, Integer> {

    List<PayrollHRARateMst> findAllByIsActiveIsTrue();

    @Query("select hra.tierId as typeId, hra.tierName as typeName from PayrollHRARateMst hra where hra.isActive = true  ")
    List<DropdownResponse> findAllActiveTiers();

    List<PayrollHRARateMst> findAllByOrderByCreatedOnDesc();

}
