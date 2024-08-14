package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadTypeMst;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadmst;

@Repository
public interface PayrollFinanceHeadmstRepo extends JpaRepository<PayrollFinanceHeadmst, Integer> {

	
	@Query("select fhm.typeId as typeId, fhm.typeCode as typeCode , fhm.typeName as typeName  from PayrollFinanceHeadmst fhm where fhm.parentId.typeId is null ")
	List<DropdownResponse> findParentHeads();


	
	@Query("select fhm.typeId as typeId, fhm.typeCode as typeCode , fhm.typeName as typeName  from PayrollFinanceHeadmst fhm where fhm.ftypeId.ftypeId =:ftypeId and fhm.isActive =:b  ")
	List<DropdownResponse> findByFtypeIdAndIsActive(Integer ftypeId, boolean b);


	@Query("select fhm.typeId as typeId, fhm.typeCode as typeCode , fhm.typeName as typeName  from PayrollFinanceHeadmst fhm where fhm.parentId.typeId =:minorId and fhm.isActive =:b ")
	List<DropdownResponse> findByParentTypeIdAndIsActive(Integer minorId, boolean b);


	@Query(value = "select maj.type_code || ' - ' || s.type_code || ' - ' || m.type_code typeCodeStr,\r\n"
			+ "maj.type_id MajorId, s.type_id subMajorId, m.type_id minorId \r\n"
			+ "from payroll.payroll_finance_head_mst m\r\n"
			+ "left join payroll.payroll_finance_head_mst s on m.parent_type_id = s.type_id\r\n"
			+ "left join payroll.payroll_finance_head_mst maj on s.parent_type_id = maj.type_id\r\n"
			+ "where m.f_type_id = 3 ", nativeQuery = true)
	List<DropdownResponse> findByMajorSubMajorMinor();


	@Query(value = "select d.type_code || ' - ' || sd.type_code typeCodeStr,sd.type_id subDetailId, d.type_id detailId\r\n"
			+ "from payroll.payroll_finance_head_mst d\r\n"
			+ "left join payroll.payroll_finance_head_mst sd on d.parent_type_id = sd.type_id\r\n"
			+ "where d.f_type_id = 6 ", nativeQuery = true)
	List<DropdownResponse> findByDetailAndSubDetail();
	

	Optional<PayrollFinanceHeadmst> findByTypeNameAndFtypeId_FtypeId(String upperCase, Integer ftypeId);

	Optional<PayrollFinanceHeadmst> findByTypeIdAndFtypeId_FtypeIdAndParentId_TypeId(Integer typeId, Integer ftypeId, Integer parentId);

	List<PayrollFinanceHeadmst> findByFtypeIdOrderByCrtOnDesc(PayrollFinanceHeadTypeMst payrollFinanceHeadTypeMst);



	@Query("select pfhm from PayrollFinanceHeadmst pfhm where pfhm.typeCode =:typeCode and pfhm.ftypeId.ftypeId=:ftypeId ")
	PayrollFinanceHeadmst findByTypeCodeAndFTypeId(Integer typeCode, Integer ftypeId);
}
