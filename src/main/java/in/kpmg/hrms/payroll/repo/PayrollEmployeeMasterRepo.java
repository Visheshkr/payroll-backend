package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpCountDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpSalaryProcess;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmployeeMstRes;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EntitlementEmpRes;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.SavedEmployeeResDto;
import in.kpmg.hrms.payroll.dtos.responseDto.EmpGroupCreationDto;
import in.kpmg.hrms.payroll.dtos.responseDto.EmployeeDropdownDto;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingMst;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;

@Repository
public interface PayrollEmployeeMasterRepo extends JpaRepository<PayrollEmployeeMaster, Long> {

    @Query(nativeQuery = true, value = "select payroll.emp_ref_no_generation()")
    String refNoGeneration();

    Optional<PayrollEmployeeMaster> findByRefNo(String refNo);

    Optional<PayrollEmployeeMaster> findByPanNo(String panNo);

    @Query(value = "select em.empId as empId, em.gpfPranNo as gpfPranNo, em.refNo as refNo, em.firstName as firstName, em.middleName as middleName, em.lastName as lastName, em.currentDsgnId.id as designationId, em.currentDsgnId.name as designationName, em.currentDeptId.deptId as deptId, em.currentDeptId.name as deptName, em.currentOffice.ofcId as ofcId, em.currentOffice.officeName as officeName, em.grpId.grpId as grpId, em.grpId.grpName as grpName, em.isGovtQuarterOccupied as isGovtQuarterOccupied, em.serviceType.id as serviceId,  em.serviceType.serviceType as serviceName, em.gpfPranType.typeId as gpfPranTypeId, em.gpfPranType.typeName as gpfPranTypeName, em.personalMobileNo as personalMobileNo, em.dob as dob from PayrollEmployeeMaster em where em.refNo=:refNo")
    EntitlementEmpRes findByRefNoForEntitlement(String refNo);

    @Query(value = " select em.first_Name as empName,em.emp_Id as empCode, em.ref_no as empRefNumber, gm.grp_Name as groupName,  \r\n"
            + "gm.grp_Code as groupCode , stm.service_type as serviceType, dm.desgn_name as designation,\r\n"
            + "om.ofc_name as office , (case when em.grp_id is not null then true else false end) as assignedStatus,\r\n"
            + "gm.grp_id as groupId , om.is_active\r\n"
            + "from  payroll.payroll_employee_mst em\r\n"
            + "left join payroll.payroll_group_mst gm on gm.grp_Id = em.grp_Id \r\n"
            + "left join  payroll.payroll_service_type_mst stm on stm.id = em.service_Type \r\n"
            + "left join payroll.payroll_designation_mst dm on dm.desgn_id = em.current_dsgn_id \r\n"
            + "left join payroll.payroll_office_mst om on (om.ofc_id = em.current_office )\r\n"
            + "where om.ofc_id = 1 and om.is_active = true and stm.is_active = true  "
            + " and (:empId  is null or em.ref_no =CAST(:empId AS TEXT)) "
            + " and (:grpId is null or gm.grp_Id=CAST(CAST(:grpId AS TEXT) AS integer) )"
            + " and  (:serviceType  is null or stm.id=CAST(CAST(:serviceType AS TEXT) AS integer) ) "
            + " and (:dsgnId is null or dm.desgn_id=CAST(CAST(:dsgnId AS TEXT) AS integer)  ) "
            + " order by emp_id desc ", nativeQuery = true)
    List<EmpGroupCreationDto> findEmployee(String empId, Long grpId, Integer serviceType, Integer dsgnId);

    @Query(value = "select \r\n"
            + "sum(case when grp_id is null then 1 else 0 end  ) as unAllocatedEmp,\r\n"
            + "sum(case when grp_id is not null then 1 else 0 end  ) as allocatedEmp \r\n"
            + "from payroll.payroll_employee_mst em\r\n"
            + " where em.current_office =:officeId", nativeQuery = true)
    EmpCountDto findAllocatedEmp(Integer officeId);

    @Query(value = "select em from PayrollEmployeeMaster em where em.grpId.grpCode=:grpCode ")
    List<PayrollEmployeeMaster> findByGrpId(String grpCode);


    @Query(value = " with mq as (select (emp.first_name || ' ' || emp.last_name) empName,  \r\n"
            + "      gpf_pran_no gpfPranNo, sm.service_type ServiceType,emp.emp_id empId , dsm.desgn_name designation ,   \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 26 THEN cSal.payhead_value ELSE 0 END)  grossEarning,  \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 27 THEN cSal.payhead_value ELSE 0 END)  grossRecovery,  \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 28 THEN cSal.payhead_value ELSE 0 END)  grossDeduction,  \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 26 THEN cSal.payhead_value ELSE 0 END)  -  \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 27 THEN cSal.payhead_value ELSE 0 END)  -  \r\n"
            + "      SUM(CASE WHEN  cSal.typeId = 28 THEN cSal.payhead_value ELSE 0 END)  netSalary,  \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 26 THEN pSal.payhead_value ELSE 0 END)  actualGrossEarning,  \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 27 THEN pSal.payhead_value ELSE 0 END)  actualGrossRecovery,  \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 28 THEN pSal.payhead_value ELSE 0 END)  actualGrossDeduction,  \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 26 THEN pSal.payhead_value ELSE 0 END) -  \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 27 THEN pSal.payhead_value ELSE 0 END) -   \r\n"
            + "      SUM(CASE WHEN  pSal.typeId = 28 THEN pSal.payhead_value ELSE 0 END) actualNetSalary ,\r\n"
            + "  cSal.payhead_ref_no payheadRefNo\r\n"
            + "      from payroll.payroll_employee_mst emp  \r\n"
            + "      left join payroll.payroll_service_type_mst sm on emp.service_type = sm.id   \r\n"
            + "       left join payroll.payroll_designation_mst dsm on emp.current_dsgn_id = dsm.desgn_id  \r\n"
            + "      left join payroll.payroll_emp_pay_entitlement_options ent on emp.emp_id = ent.emp_id  \r\n"
            + "      left join (select emp_id, sdtl.payhead_id, sdtl.payhead_value, gm.type_id typeId , payhead_ref_no  \r\n"
            + "        from payroll.payroll_emp_salary_processing sal   \r\n"
            + "      left join payroll.payroll_emp_salary_processing_details sdtl on sdtl.emp_salary_id = sal.id  \r\n"
            + "      left join payroll.payroll_payhead_mst ph on ph.payhead_id = sdtl.payhead_id  \r\n"
            + "      left join payroll.payroll_general_mst gm on gm.type_id = ph.payhead_type  \r\n"
            + "      where sal.fy_paymonth_id =:fyMonId ) cSal on emp.emp_id = cSal.emp_id  \r\n"
            + "      left join (select  emp_id, ent.payhead_id, payhead_value, gm.type_id typeId  \r\n"
            + "        from payroll.payroll_emp_pay_entitlement ent  \r\n"
            + "      left join payroll.payroll_payhead_mst ph on ph.payhead_id = ent.payhead_id  \r\n"
            + "      left join payroll.payroll_general_mst gm on gm.type_id = ph.payhead_type  \r\n"
            + "      ) pSal on emp.emp_id = pSal.emp_id   \r\n"
            + "      where emp.grp_id =:grpId \r\n"
            + "      group by (emp.first_name || ' ' || emp.last_name) ,  \r\n"
            + "      gpf_pran_no,emp.emp_id , sm.service_type , dsm.desgn_name, cSal.payhead_ref_no )  \r\n"
            + "      select gpfPranNo, serviceType, actualGrossEarning, actualGrossRecovery, actualGrossDeduction,  \r\n"
            + "      actualNetSalary, empId, empName , designation,    \r\n"
            + "      case when grossEarning = 0 then actualGrossEarning end  grossEarning,  \r\n"
            + "      case when grossEarning = 0 then actualGrossRecovery end grossRecovery,  \r\n"
            + "      case when grossEarning = 0 then actualGrossDeduction end grossDeduction,  \r\n"
            + "      case when grossEarning = 0 then actualNetSalary end netSalary  , payheadRefNo\r\n"
            + "      from mq ", nativeQuery = true)
    List<EmpSalaryProcess> findByGrpIdAndFyMon(Long fyMonId, Long grpId);


    @Query(nativeQuery = true, value = "SELECT pem.emp_id as empId, pem.ref_no as refNo, CONCAT(pem.first_name, ' ', \n" +
            "           CASE \n" +
            "               WHEN pem.middle_name IS NULL OR pem.middle_name = '' THEN '' \n" +
            "               ELSE CONCAT(pem.middle_name, ' ') \n" +
            "           END, \n" +
            "     pem.last_name) AS fullName  FROM  payroll.payroll_employee_mst pem \n" +
            "INNER JOIN payroll.payroll_user_mst um ON CAST(pem.emp_id AS VARCHAR) = um.emp_code")
    List<SavedEmployeeResDto> findAllBySavedEmployee();

    
    @Query("select em.empId as empId ,concat(firstName || ' ' || lastName) as empName, em.refNo as empRefNo from PayrollEmployeeMaster em where em.grpId.grpId=:grpCode ")
	List<EmployeeDropdownDto> findEmpListByGrpId(Long grpCode);

    

    @Query(value = "select em.emp_Id as empId, concat(em.first_Name || ' ' || em.last_Name) as empName,\r\n"
    		+ "em.ref_No as empRefNo, em.gpf_Pran_No as pranNo , dsg.desgn_name as designation ,\r\n"
    		+ "stm.service_Type as serviceType , pcgm.type_name as payCommision ,plgm.type_name as payLevel \r\n"
    		+ "from payroll.payroll_employee_mst em\r\n"
    		+ "join  payroll.payroll_emp_pay_entitlement_options entop on entop.emp_Id = em.emp_Id \r\n"
    		+ "left join payroll.payroll_general_mst pcgm on pcgm.type_id = entop.pc_id\r\n"
    		+ "left join payroll.payroll_general_mst plgm on plgm.type_id = entop.pay_level\r\n"
    		+ "left join payroll.payroll_service_type_mst stm on stm.id = em.service_Type\r\n"
    		+ "left join payroll.payroll_designation_mst dsg on dsg.desgn_id = em.current_Dsgn_Id\r\n"
    		+ "where em.current_Office=:officeId \r\n"
    		+ "order by em.emp_id asc", nativeQuery = true)
	List<EmployeeMstRes> findTotalEmployees(Integer officeId);

    @Query(value = "select em.emp_Id as empId, concat(em.first_Name || ' ' || em.last_Name) as empName,\r\n"
    		+ "em.ref_No as empRefNo, em.gpf_Pran_No as pranNo , dsg.desgn_name as designation ,\r\n"
    		+ "stm.service_Type as serviceType , pcgm.type_name as payCommision ,plgm.type_name as payLevel \r\n"
    		+ "from payroll.payroll_employee_mst em\r\n"
    		+ " join payroll.payroll_emp_salary_processing esp on esp.emp_Id= em.emp_Id and esp.fy_paymonth_id =:fyMonId \r\n"
    		+ " join payroll.payroll_emp_pay_entitlement_options entop on entop.emp_Id = em.emp_Id \r\n"
    		+ "left join payroll.payroll_general_mst pcgm on pcgm.type_id = entop.pc_id\r\n"
    		+ "left join payroll.payroll_general_mst plgm on plgm.type_id = entop.pay_level\r\n"
    		+ "left join payroll.payroll_service_type_mst stm on stm.id = em.service_Type\r\n"
    		+ "left join payroll.payroll_designation_mst dsg on dsg.desgn_id = em.current_Dsgn_Id\r\n"
    		+ "where em.current_Office=:officeId \r\n"
    		+ "order by em.emp_id asc", nativeQuery = true)
	List<EmployeeMstRes> findProccessedEmployees(Integer officeId,Long fyMonId);

    @Query(value = "select em.emp_Id as empId, concat(em.first_Name || ' ' || em.last_Name) as empName,\r\n"
    		+ "em.ref_No as empRefNo, em.gpf_Pran_No as pranNo , dsg.desgn_name as designation ,\r\n"
    		+ "stm.service_Type as serviceType , pcgm.type_name as payCommision ,plgm.type_name as payLevel \r\n"
    		+ "from payroll.payroll_employee_mst em\r\n"
    		+ " join payroll.payroll_emp_pay_entitlement_options entop on entop.emp_Id = em.emp_Id \r\n"
    		+ "left join payroll.payroll_general_mst pcgm on pcgm.type_id = entop.pc_id\r\n"
    		+ "left join payroll.payroll_general_mst plgm on plgm.type_id = entop.pay_level\r\n"
    		+ "left join payroll.payroll_service_type_mst stm on stm.id = em.service_Type\r\n"
    		+ "left join payroll.payroll_designation_mst dsg on dsg.desgn_id = em.current_Dsgn_Id\r\n"
    		+ "where  em.current_Office=:officeId and em.emp_id not in (select emp_id from payroll.payroll_emp_salary_processing where fy_paymonth_id =:fyMonId )\r\n"
    		+ "order by em.emp_Id asc ", nativeQuery = true)
	List<EmployeeMstRes> findNotProccessedEmployees( Integer officeId, Long fyMonId);

    @Query(value = "select em.emp_Id as empId, concat(em.first_Name || ' ' || em.last_Name) as empName,  \r\n"
    		+ "      em.ref_No as empRefNo, em.gpf_Pran_No as pranNo , dsg.desgn_name as designation ,  \r\n"
    		+ "      stm.service_Type as serviceType , pcgm.type_name as payCommision ,plgm.type_name as payLevel \r\n"
    		+ "      from payroll.payroll_employee_mst em  \r\n"
    		+ "      JOIN payroll.payroll_emp_pay_entitlement_options entop on entop.emp_Id = em.emp_Id   \r\n"
    		+ "      LEFT JOIN payroll.payroll_general_mst pcgm on pcgm.type_id = entop.pc_id  \r\n"
    		+ "      LEFT JOIN payroll.payroll_general_mst plgm on plgm.type_id = entop.pay_level  \r\n"
    		+ "      LEFT JOIN payroll.payroll_service_type_mst stm on stm.id = em.service_Type  \r\n"
    		+ "      LEFT JOIN payroll.payroll_designation_mst dsg on dsg.desgn_id = em.current_Dsgn_Id\r\n"
    		+ "  JOIN (SELECT emp_id, fy_paymonth_id, crt_on, upd_on \r\n"
    		+ "FROM payroll.payroll_emp_salary_processing WHERE fy_paymonth_id =:fyMonId) sp\r\n"
    		+ "ON sp.emp_id = em.emp_id\r\n"
    		+ "  left join payroll.payroll_fy_paymonth_mst pm on pm.id = sp.fy_paymonth_id\r\n"
    		+ "      WHERE  em.current_office=:officeId  \r\n"
    		+ "AND pm.month_id <> EXTRACT(MONTH FROM Date(COALESCE(sp.upd_on, sp.crt_on)))\r\n"
    		+ "      ORDER BY em.emp_Id ASC  " , nativeQuery = true)
	List<EmployeeMstRes> findDelayedEmployees(Integer officeId, Long fyMonId);

    
    @Query(value = "select em.emp_Id as empId, concat(em.first_Name || ' ' || em.last_Name) as empName,\r\n"
    		+ "em.ref_No as empRefNo, em.gpf_Pran_No as pranNo , dsg.desgn_name as designation ,\r\n"
    		+ "stm.service_Type as serviceType , pcgm.type_name as payCommision ,plgm.type_name as payLevel,\r\n"
    		+ "entop.basic_pay as basicPay, em.is_govt_quarter_occupied as IsGovtQtrOccupied,\r\n"
    		+ "sum(pe.payhead_value) * 12 as netEarning ,\r\n"
    		+ "sum (case when pe.payhead_id in (180,26) then pe.payhead_value else 0 end ) as deduction \r\n"
    		+ "from payroll.payroll_employee_mst em\r\n"
    		+ " join payroll.payroll_emp_pay_entitlement_options entop on entop.emp_Id = em.emp_Id \r\n"
    		+ " left join payroll.payroll_emp_pay_entitlement pe on pe.emp_id = em.emp_Id \r\n"
    		+ " left join payroll.payroll_payhead_mst phm on phm.payhead_id = pe.payhead_id \r\n"
    		+ "left join payroll.payroll_general_mst pcgm on pcgm.type_id = entop.pc_id\r\n"
    		+ "left join payroll.payroll_general_mst plgm on plgm.type_id = entop.pay_level\r\n"
    		+ "left join payroll.payroll_service_type_mst stm on stm.id = em.service_Type\r\n"
    		+ "left join payroll.payroll_designation_mst dsg on dsg.desgn_id = em.current_Dsgn_Id\r\n"
    		+ "where em.emp_Id =:empId  \r\n"
    		+ "group by em.emp_Id,  dsg.desgn_name, stm.service_Type , pcgm.type_name, plgm.type_name, entop.basic_pay,\r\n"
    		+ "em.is_govt_quarter_occupied\r\n"
    		+ "order by em.emp_Id asc ", nativeQuery = true)
	EmployeeMstRes findByEmpId(Long empId);
    


}
