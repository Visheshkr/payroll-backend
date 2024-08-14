package in.kpmg.hrms.payroll.repo;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.models.PayrollOfficeMaster;

@Repository
public interface PayrollOfficeMasterRepo extends JpaRepository<PayrollOfficeMaster, Long> {

    @Query("SELECT p FROM PayrollOfficeMaster p " +
            "WHERE (:deptCode IS NULL OR p.deptCode.deptId = :deptCode) " +
            "AND (:stateId IS NULL OR p.stateId.locId = :stateId) " +
            "AND (:districtId IS NULL OR p.districtId.locId = :districtId) " +
            "AND (:ofcName IS NULL OR p.officeName LIKE %:ofcName%)")
    List<PayrollOfficeMaster> findByConditions(@Param("deptCode") Integer deptCode,
                                               @Param("stateId") Long stateId,
                                               @Param("districtId") Long districtId,
                                               @Param("ofcName") String ofcName);

    @Query("select om.ofcId as typeId, om.officeName as typeName, om.ofcCode as typeCode  from PayrollOfficeMaster om where om.isActive = true  ")
    List<DropdownResponse> findActiveOffice();

    PayrollOfficeMaster findByOfficeNameAndOfcCode(@NotNull(message = "Office Name is mandatory") String officeName, 
    											   @NotNull(message = "Office Code is mandatory") String ofcCode);

    @Query("select om from PayrollOfficeMaster om order by om.ofcId desc")
	List<PayrollOfficeMaster> findAllByOrderByOfficeIdDesc();
	

}

