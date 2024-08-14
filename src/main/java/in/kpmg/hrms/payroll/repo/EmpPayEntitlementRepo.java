package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.PayHeadDetailsByEmp;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;

@Repository
public interface EmpPayEntitlementRepo extends JpaRepository<EmpPayEntitlement, Long> {

	@Query(value= "select payheadId, payheadValue, payHeadConfigId, empId from (\r\n"
			+ "select payhead_id payheadId, payhead_value payheadValue, payhead_config_id payHeadConfigId , emp_id empId\r\n"
			+ "from payroll.payroll_emp_pay_entitlement pent\r\n"
			+ "union\r\n"
			+ "select spd.payhead_id payheadId, spd.payhead_value payheadValue, spd.payhead_config_id payHeadConfigId, sp.emp_id empId\r\n"
			+ "from payroll.payroll_emp_salary_processing_details spd\r\n"
			+ "join payroll.payroll_emp_salary_processing sp on spd.emp_salary_id = sp.id\r\n"
			+ " where sp.status = 403 and sp.fy_paymonth_id =:fyMonId \r\n"
			+ ") inn where inn.empId =:empId ", nativeQuery = true)
	List<PayHeadDetailsByEmp> findPayHeadsByEmpId(Integer empId, Long fyMonId);

    List<EmpPayEntitlement> findAllByPayheadEntitlementId_IdOrderByPayheadId_PayheadName(Long id);

    @Query( "select empt from EmpPayEntitlement empt  \r\n"
    		+ " where empt.empId.empId =:emp ")
	List<EmpPayEntitlement> findPayHeadsByEmpId(Long emp);

	List<EmpPayEntitlement> findAllByEmpId_EmpId(Long empId);

}
