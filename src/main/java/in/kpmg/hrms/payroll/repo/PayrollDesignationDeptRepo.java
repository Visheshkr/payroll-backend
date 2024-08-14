package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollDesignationDeptMapping;

@Repository
public interface PayrollDesignationDeptRepo extends JpaRepository<PayrollDesignationDeptMapping, Integer> {


    @Query("select ddm.dsgnId.id as typeId, ddm.dsgnId.name as typeName , ddm.dsgnId.code as typeCode, ddm.id as deptDsgnId from PayrollDesignationDeptMapping ddm where ddm.deptId.deptId =:deptId and ddm.isActive =:b ")
    List<DropdownResponse> findByDeptIdAndisActive(Integer deptId, Boolean b);

    List<PayrollDesignationDeptMapping> findAllByDeptId_DeptIdAndDeptId_IsActiveIsTrueAndIsActiveIsTrue(Integer deptId);
    Optional<PayrollDesignationDeptMapping> findByDeptId_DeptIdAndDsgnId_IdAndDeptId_IsActiveIsTrueAndDsgnId_IsActiveIsTrueAndIsActiveIsTrue(Integer deptId, Integer desgnId);


}
