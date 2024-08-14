package in.kpmg.hrms.payroll.repo;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.models.PayrollPayHeadConfigMaster;
import in.kpmg.hrms.payroll.services.MasterConfigService.NonfixedAmt;

@Repository
public interface PayrollPayHeadConfigRepo extends JpaRepository<PayrollPayHeadConfigMaster, Integer> {

	@Query(value = "select payroll.payroll_formula_validation(:formula)", nativeQuery = true)
	Boolean validateFormula(@NotNull(message = "formula is mandatory", groups = NonfixedAmt.class) String formula);

	@Query("select phm from PayrollPayHeadConfigMaster phm where phm.payHeadId is not null "
			+ " and (:payHeadId is null or phm.payHeadId.payheadId=:payHeadId) "
			+ " and (:pcId is null or phm.payCommissionId.typeId =:pcId) "
			+ " and (:entId is null or phm.ctaEntitlementId =:entId) "
			+ " and (:isActive is null or phm.isActive =:isActive) "
			+ " order by phm.payheadConfigId desc")
	List<PayrollPayHeadConfigMaster> findAllOrderByPayheadConfigIdDesc( Integer payHeadId,  Long pcId, Integer entId, Boolean isActive);

	@Query("select phcm from PayrollPayHeadConfigMaster phcm where phcm.serviceType.id=:serviceType and phcm.deptDegnId.id=:deptDsgn "
			+ "and phcm.payHeadId.payheadId=:payHeadId and phcm.payCommissionId.typeId=:payCommissionId "
			+ " and phcm.payLevelId.typeId=:pLevel"
			+ " and (:sqlStr is null or phcm.circulatedDate=:circulatedDate )"
			+ " and (:ctaEntitlementId is null or phcm.ctaEntitlementId=:ctaEntitlementId) "
			+ " and (:tierId is null or phcm.tierId=:tierId )")
	PayrollPayHeadConfigMaster existsConfig(Integer serviceType, Integer deptDsgn, Long pLevel,
			Integer payHeadId, Integer ctaEntitlementId,
			Date circulatedDate, Integer tierId,
			 Long payCommissionId, String sqlStr);

}
