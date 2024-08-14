package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GroupWiseSalaryStatus;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingMst;

@Repository
public interface EmpSalaryProcessingRepo extends JpaRepository<EmpSalaryProcessingMst, Long> {

	@Query("select esp from EmpSalaryProcessingMst esp where esp.empId.empId=:empId and esp.fyPayMonthId.id =:fyPayMonthId ")
	EmpSalaryProcessingMst findByEmpIdAndFyMonthId(Long empId,Long fyPayMonthId);

	@Query(value = "WITH mq AS (SELECT DISTINCT emp_id FROM payroll.payroll_emp_pay_entitlement)\r\n"
			+ "SELECT grp.grp_id, grp_code groupCode, grp_name groupName,\r\n"
			+ "gm.type_name payrollStatus,\r\n"
			+ "COALESCE(COUNT(emp.emp_id), 0) empCount,\r\n"
			+ "SUM(CASE WHEN emp.emp_id in (SELECT * FROM mq) THEN 1 ELSE 0 END ) eligibleCount,\r\n"
			+ "SUM(CASE WHEN emp.emp_id not in (SELECT * FROM mq) THEN 1 ELSE 0 END ) NonEligibleCount,\r\n"
			+ "SUM(CASE WHEN SP.emp_id IS NOT NULL THEN 1 ELSE 0 END ) excludeCount,\r\n"
			+ "case when COALESCE(COUNT(emp.emp_id), 0) = COALESCE(COUNT(sp.emp_id), 0) THEN 'Y' ELSE 'N' END allocatedYN,\r\n"
			+ "COALESCE(MAX(sp.cycle)+1, 1) cycleNo,\r\n"
			+ "sp.payhead_ref_no tempRefNo, pm.hoa_id hoaId\r\n"
			+ "FROM payroll.payroll_fy_wise_hoa_payroll_status pm\r\n"
			+ "JOIN payroll.payroll_group_mst grp on pm.hoa_id = grp.hoa_id\r\n"
			+ "LEFT JOIN payroll.payroll_employee_mst emp on grp.grp_id = emp.grp_id\r\n"
			+ "LEFT JOIN payroll.payroll_general_mst gm on gm.type_id = pm.status\r\n"
			+ "left join payroll.payroll_emp_salary_processing sp on (emp.emp_id = sp.emp_id and sp.fy_paymonth_id = pm.fy_month_id)\r\n"
			+ "WHERE fy_month_id =:payMonthId and pm.hoa_id =:hoaId\r\n"
			+ "GROUP BY grp.grp_id, grp_code , grp_name , gm.type_name,sp.payhead_ref_no, pm.hoa_id "
			+ " HAVING COALESCE(COUNT(emp.emp_id), 0) > 0 \r\n"
			+ "ORDER BY grp.grp_id\r\n"
			+ "", nativeQuery = true)
	List<GroupWiseSalaryStatus> getProccedGrps(Long payMonthId, Long hoaId);

	@Query(value = "select payroll.payroll_emp_regular_salary_processing_seq() ", nativeQuery = true)
	String generateRefNo();

	Optional<EmpSalaryProcessingMst> findByEmpId_EmpIdAndFyPayMonthId_IdAndFyPayMonthId_IsActiveIsTrue(Long empId, Long fyMonthId);

	
	@Query("select esp.empId.empId from EmpSalaryProcessingMst esp where esp.fyPayMonthId.id =:fyMonId and esp.empId.empId in(:empIds)  ")
	List<Long> findUnproccesedEmp(Long fyMonId, List<Long> empIds);

}
