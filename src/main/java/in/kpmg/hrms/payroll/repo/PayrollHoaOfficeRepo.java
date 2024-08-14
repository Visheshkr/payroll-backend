package in.kpmg.hrms.payroll.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmployeeSalaryReportResponse;
import in.kpmg.hrms.payroll.dtos.responseDto.DropdownDataDto;
import in.kpmg.hrms.payroll.models.PayrollHoaOfficeMst;

@Repository
public interface PayrollHoaOfficeRepo extends JpaRepository<PayrollHoaOfficeMst, Integer> {

	@Query("select ho.id as typeId, ho.hoa.hoa as hoa from PayrollHoaOfficeMst ho where ho.office.ofcId =:officeId  ")
	List<DropdownDataDto> getHoaLists(Long officeId);
	
	
	@Query(value = "select count(*) as totalEmp\r\n"
			+ "from payroll.payroll_employee_mst em \r\n"
			+ " join payroll.payroll_group_mst gm on em.grp_id = gm.grp_id\r\n"
			+ "where gm.office_id =:officeId  ",nativeQuery = true)
	List<DropdownDataDto> getEmployeesLists(Long officeId);


	@Query("select hom from PayrollHoaOfficeMst hom where hom.hoa.hoa=:hoa ")
	PayrollHoaOfficeMst findByHoa(Long hoa);


	@Query(value = "select om.ofc_id officeId, om.ofc_name officeName, count(em.emp_id) totalEmp, count(esp.emp_id) processedCount,  \r\n"
			+ "  count(em.emp_id) - count(esp.emp_id) notProcessedCount, dm.dept_name deptName,\r\n"
			+ "  SUM(CASE WHEN pm.month_id <> EXTRACT(MONTH FROM DATE(COALESCE(esp.upd_on, esp.crt_on))) \r\n"
			+ "  THEN 1 ELSE 0 END) delayedCount,\r\n"
			+ "  SUM(CASE WHEN pm.month_id = EXTRACT(MONTH FROM DATE(COALESCE(esp.upd_on, esp.crt_on))) \r\n"
			+ "  THEN 1 ELSE 0 END) notDelayedCount\r\n"
			+ "  from payroll.payroll_office_mst om   \r\n"
			+ "  left join payroll.payroll_employee_mst em on em.current_office = om.ofc_id  \r\n"
			+ "  left join (select emp_id, fy_paymonth_id, crt_on, upd_on from payroll.payroll_emp_salary_processing where fy_paymonth_id =:payMontId ) esp on esp.emp_id = em.emp_id  \r\n"
			+ "  left join payroll.payroll_department_mst dm on dm.dept_id  = om.dept_code  \r\n"
			+ "  left join payroll.payroll_fy_paymonth_mst pm on pm.id =:payMontId \r\n"
			+ "  where  om.is_active = true  \r\n"
			+ "  and  dm.dept_id in :deptId  \r\n"
			+ "   and  om.ofc_id in :officeIds \r\n"
			+ "  group by om.ofc_id, om.ofc_name, dm.dept_name  \r\n"
			+ "  order by om.ofc_name \r\n"
			+ "", nativeQuery = true)
	List<EmployeeSalaryReportResponse> getReport( List<Integer> deptId,List<Integer> officeIds, Long payMontId );

}
