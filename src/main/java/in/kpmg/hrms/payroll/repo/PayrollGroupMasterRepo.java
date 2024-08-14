package in.kpmg.hrms.payroll.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.responseDto.DropdownDataDto;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;

@Repository
public interface PayrollGroupMasterRepo extends JpaRepository<PayrollGroupMaster, Long> {

    List<PayrollGroupMaster> findAllByIsActiveIsTrue();
    List<PayrollGroupMaster> findAllByOfficeId_OfcIdAndIsActiveIsTrue(Long officeId);

    PayrollGroupMaster findByGrpCode( String grpCode);

    
    @Query(value = "select count(*) as AssignedEmp \r\n"
    		+ "from payroll.payroll_employee_mst em \r\n"
    		+ " join payroll.payroll_group_mst gm on em.grp_id = gm.grp_id\r\n"
    		+ "where gm.grp_id =:grpId ", nativeQuery = true)
	Integer findEmpByGrpId(Long grpId);
    
    @Query("select gm from PayrollGroupMaster gm where gm.officeId.ofcId =:officeId order by gm.grpId desc ")
	List<PayrollGroupMaster> findByOfficeId(Long officeId);
    
    
    @Query("select gm.grpId as typeId, gm.grpCode as typeCode , gm.grpName as typeName from PayrollGroupMaster gm where gm.officeId.ofcId =:officeId and gm.isActive =:b")
	List<DropdownDataDto> findByOfficeIdAndIsActive(Long officeId, boolean b);

}
