package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayEntitlementResDto;
import in.kpmg.hrms.payroll.models.PayrollPayHeadMaster;

@Repository
public interface PayrollPayheadMstRepo extends JpaRepository<PayrollPayHeadMaster, Integer> {

    PayrollPayHeadMaster findByPayheadCode(String payheadCode);

    List<PayrollPayHeadMaster> findAllByOrderByCrtOnDesc();

    @Query("select phm.payheadId as typeId, phm.payheadName as typeName, phm.payheadCode as typeCode from PayrollPayHeadMaster phm where phm.isActive = true ")
    List<DropdownResponse> findAllActivePayHeads();


    //    @Query(nativeQuery = true, value = "select grp.grp_id as groupId, head.payhead_id as payheadId, payhead_name as payheadName, \n" +
//            "payhead_config_id as payheadConfigId, formula,\n" +
//            "CASE WHEN head.payhead_id = 1 then CAST(:basicPay AS TEXT) when con.max_amt is not null then CAST(con.max_amt AS TEXT)\n" +
//            "when formula is not null then payroll.employee_payhead_value_determination(con.formula, CAST(CAST(:basicPay AS TEXT) AS INTEGER), CAST(CAST(:payCommissionId AS TEXT) AS INTEGER),CAST(CAST(:serviceType AS TEXT) AS INTEGER),CAST(CAST(:payLevelId AS TEXT) AS INTEGER), CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER))\n" +
//            "else '0' END AS amount\n" +
//            "from payroll.payroll_group_mst grp --on grp.grp_id = emp.grp_id\n" +
//            "left join payroll.payroll_hoa_mst hoa on\n" +
//            "hoa.id\n" +
//            "= grp.hoa_id\n" +
//            "left join payroll.payroll_payhead_mst head on (head.detail_head = hoa.detail_head_id \n" +
//            "and hoa.sub_detail_id = head.sub_detail_head)\n" +
//            "LEFT join payroll.payroll_payhead_config_mst con on\n" +
//            "( con.payhead_id = head.payhead_id and \n" +
//            "pay_commission_id = CAST(CAST(:payCommissionId AS TEXT) AS INTEGER) and \n" +
//            "( service_type is NULL or service_type=CAST(CAST(:serviceType AS TEXT) AS INTEGER))and\n" +
//            "( pay_level_id is NULL or pay_level_id=CAST(CAST(:payLevelId AS TEXT) AS INTEGER)) and \n" +
//            "( dept_dsgn_id is NULL or dept_dsgn_id=CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER)) \n" +
//            ")\n" +
//            "WHERE grp.grp_id=CAST(CAST(:grpId AS TEXT) AS INTEGER) and head.payhead_type=CAST(CAST(:payheadType AS TEXT) AS INTEGER)")
    @Query(nativeQuery = true, value = "select grp.grp_id as groupId, head.payhead_id as payheadId, payhead_name as payheadName, payhead_config_id as payheadConfigId, formula as formula,\n" +
            "\tCASE WHEN head.payhead_id = 1 then CAST(:basicPay AS TEXT)\n" +
            "\tWHEN is_fixed_amt = '1' then   CAST(con.max_amt AS TEXT)\n" +
            "\twhen formula is not null then CAST(least(CAST(CAST(payroll.employee_payhead_value_determination(con.formula, \n" +
            "CAST(CAST(:basicPay AS TEXT) AS INTEGER), CAST(CAST(:payCommissionId AS TEXT) AS INTEGER),\n" +
            "CAST(CAST(:serviceType AS TEXT) AS INTEGER),CAST(CAST(:payLevelId AS TEXT) AS INTEGER), \n" +
            "CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER))AS TEXT)AS INTEGER), con.max_amt ) AS TEXT)\n" +
            "else '0' END AS amount, head.payhead_type as payheadType,gm.type_name as typeName\n" +
            "from payroll.payroll_group_mst grp --on grp.grp_id = emp.grp_id\n" +
            "left join payroll.payroll_hoa_mst hoa on hoa.id = grp.hoa_id\n" +
            "left join payroll.payroll_payhead_mst head on (head.detail_head = hoa.detail_head_id\n" +
            "and hoa.sub_detail_id = head.sub_detail_head)\n" +
            "left join payroll.payroll_general_mst gm on gm.type_id = head.payhead_type\n" +
            "LEFT join payroll.payroll_payhead_config_mst con on\n" +
            "(con.payhead_id = head.payhead_id and\n" +
            "pay_commission_id = CAST(CAST(:payCommissionId AS TEXT) AS INTEGER) and\n" +
            "(service_type is null or service_type = CAST(CAST(:serviceType AS TEXT) AS INTEGER))and\n" +
            "(pay_level_id is null or pay_level_id = CAST(CAST(:payLevelId AS TEXT) AS INTEGER)) and\n" +
            "(dept_dsgn_id is null or dept_dsgn_id = CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER))\n" +
            ")\n" +
            "WHERE grp.grp_id = CAST(CAST(:grpId AS TEXT) AS INTEGER) and head.payhead_type=CAST(CAST(:payheadType AS TEXT) AS INTEGER) \n" +
            "and head.is_active = '1' and con.is_active = '1'")
    List<PayEntitlementResDto> getEntitlementList(Integer basicPay, Long payCommissionId, Long serviceType, Integer payLevelId, Integer deptDesgnId, Long grpId, Long payheadType);

    @Query(nativeQuery = true, value = "\n" +
            "select grp.grp_id as groupId, head.payhead_id as payheadId, payhead_name as payheadName, payhead_config_id as payheadConfigId, formula as formula,\n" +
            "CASE WHEN head.payhead_id = 1 then CAST(:basicPay AS TEXT) \n" +
            "when con.is_fixed_amt = '1' then CAST(con.max_amt AS TEXT)\n" +
            "when formula is not null then CAST(least(CAST(CAST(payroll.employee_payhead_value_determination_dummy(con.formula, \n" +
            "CAST(CAST(:basicPay AS TEXT) AS INTEGER), CAST(CAST(:payCommissionId AS TEXT) AS INTEGER),\n" +
            "CAST(CAST(:serviceType AS TEXT) AS INTEGER),CAST(CAST(:payLevelId AS TEXT) AS INTEGER), \n" +
            "CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER), coalesce(0, CAST(CAST(:hraTier AS TEXT) AS INTEGER)), coalesce(0, CAST(CAST(:ctaEntitlement AS TEXT) AS INTEGER)), coalesce(true, true), coalesce(true, true))AS TEXT)AS INTEGER), con.max_amt ) AS TEXT)\n" +
            "else '0' END AS amount, head.payhead_type,gm.type_name\n" +
            "from payroll.payroll_group_mst grp --on grp.grp_id = emp.grp_id\n" +
            "left join payroll.payroll_hoa_mst hoa on hoa.id = grp.hoa_id\n" +
            "left join payroll.payroll_payhead_mst head on (head.detail_head = hoa.detail_head_id\n" +
            "and hoa.sub_detail_id = head.sub_detail_head)\n" +
            "left join payroll.payroll_general_mst gm on gm.type_id = head.payhead_type\n" +
            "LEFT join payroll.payroll_payhead_config_mst con on\n" +
            "(con.payhead_id = head.payhead_id and\n" +
            "pay_commission_id = CAST(CAST(:payCommissionId AS TEXT) AS INTEGER) and\n" +
            "(service_type =  CAST(CAST(:serviceType AS TEXT) AS INTEGER) )and\n" +
            "(pay_level_id = CAST(CAST(:payLevelId AS TEXT) AS INTEGER) ) and\n" +
            "(dept_dsgn_id = CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER))\n" +
            ")\n" +
            "WHERE grp.grp_id = CAST(CAST(:grpId AS TEXT) AS INTEGER) and head.payhead_type=CAST(CAST(:payheadType AS TEXT) AS INTEGER)  and head.is_active = '1' \n" +
            "order by head.payhead_id;\n")
    List<PayEntitlementResDto> getEntitlementListDummy(Integer basicPay, Long payCommissionId, Long serviceType, Integer payLevelId, Integer deptDesgnId, Long grpId, Long payheadType, Integer hraTier, Integer ctaEntitlement);

    @Query(nativeQuery = true, value = "select grp_id as grpId, payhead_id as payheadId, payhead_name as payheadName, payhead_config_id as payheadConfigId, formula as formula, amount as amount, payhead_type_id as payheadTypeId, payhead_type as payheadType from payroll.payroll_employee_pay_entitlement(" +
            "CAST(CAST(:grpId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:basicPay AS TEXT) AS INTEGER), " +
            "CAST(CAST(:payCommissionId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:serviceType AS TEXT) AS INTEGER), " +
            "CAST(CAST(:payLevelId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:deptDesgnId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:ctaEntitlement AS TEXT) AS INTEGER), " +
            "CAST(CAST(:hraTier AS TEXT) AS INTEGER), " +
            "CAST(CAST(:isDaStop AS TEXT) AS BOOLEAN), CAST(CAST(:isNPS AS TEXT) AS BOOLEAN))")
    List<PayEntitlementResDto> getEntitlementListTest(Integer basicPay, Long payCommissionId, Long serviceType, Integer payLevelId, Integer deptDesgnId, Long grpId, Integer hraTier, Integer ctaEntitlement, Boolean isDaStop, Boolean isNPS);


    @Query(nativeQuery = true, value = "select grp_id as grpId, payhead_id as payheadId, payhead_name as payheadName, payhead_config_id as payheadConfigId, formula as formula, amount as amount, payhead_type_id as payheadTypeId, payhead_type as payheadType from payroll.payroll_regular_salary_processing(" +
            "CAST(CAST(:empId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:fyMonthId AS TEXT) AS INTEGER), " +
            "CAST(CAST(:lossOfPayInDays AS TEXT) AS INTEGER))")
    List<PayEntitlementResDto> getRegularSalaryProcessing(Long empId, Long fyMonthId, Integer lossOfPayInDays);


    @Query("SELECT ppm.payheadName as typeName, ppm.payheadCode as typeCode, ppm.payheadId as typeId FROM PayrollPayHeadMaster ppm WHERE ppm.payHeadType.typeId = :payHeadType and ppm.isActive =:b")
    List<DropdownResponse> findByPayHeadTypeAndIsActive(Long payHeadType, boolean b);


}
