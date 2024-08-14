package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollLocationMaster;

@Repository
public interface PayrollLocationMasterRepo extends JpaRepository<PayrollLocationMaster, Long> {

    List<PayrollLocationMaster> findAllByLocTypeId_TypeIdAndLocTypeId_IsActiveIsTrueAndIsActiveIsTrue(Long typeId);
    List<PayrollLocationMaster> findAllByLocParentId_LocIdAndLocParentId_IsActiveIsTrueAndAndIsActiveIsTrue(Long typeId);

}
