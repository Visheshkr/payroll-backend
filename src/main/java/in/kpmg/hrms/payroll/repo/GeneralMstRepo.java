package in.kpmg.hrms.payroll.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.responseDto.CommonDropdownProjection;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollGeneralTypeMaster;

@Repository
public interface GeneralMstRepo extends JpaRepository<GeneralMst, Long> {


//    List<GeneralMst> findByGeneralMst_TypeId(Integer typeId);

    List<GeneralMst> findByGeneralTypeIdOrderByCreatedOnDesc(PayrollGeneralTypeMaster typeId);

    List<GeneralMst> findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue(String typeDesc);

    Optional<GeneralMst> findByTypeName(String typeName);

    Optional<GeneralMst> findByTypeNameAndGeneralId(String upperCase, Integer generalId);

    Optional<GeneralMst> findByTypeIdAndGeneralId(Long typeId, Integer generalId);

    @Query("select gm.typeId as typeId, gm.typeCode as typeCode, gm.typeName as typeName from GeneralMst gm where gm.generalTypeId.typeId =:typeId and isActive =:b order by gm.typeName desc  ")
    List<DropdownResponse> findByGeneralTypeIdAndIsActive(Integer typeId, Boolean b);


    @Query("select gm.typeId as typeId, gm.typeCode as typeCode, gm.typeName as typeName from GeneralMst gm where gm.parentTypeId.typeId =:pcId and gm.isActive =:b ")
    List<DropdownResponse> findByParentTypeIdAndIsActive(Long pcId, boolean b);

    @Query(nativeQuery = true, value = "select pgm.type_id as id, pgm.type_name as label, pgtm.g_type_id as generalType from payroll.payroll_general_mst pgm \n" +
            "join\n" +
            "payroll.payroll_general_type_mst pgtm\n" +
            "ON pgm.g_type_id=pgtm.g_type_id\n" +
            "where \n" +
            "pgtm.g_type_id IN :typeIdList\n" +
            "and pgm.is_active=true and pgtm.is_active=true\n" +
            "order by pgm.type_name")
    List<CommonDropdownProjection> findByTypeIds(List<Long> typeIdList);

    @Query(nativeQuery = true, value = "select pgm.type_id as id, pgm.type_name as label, pgtm.g_type_id as generalType from payroll.payroll_general_mst pgm \n" +
            "join\n" +
            "payroll.payroll_general_type_mst pgtm\n" +
            "ON pgm.g_type_id=pgtm.g_type_id\n" +
            "where \n" +
            "pgtm.g_type_id=:typeId\n" +
            "and pgm.is_active=true and pgtm.is_active=true\n" +
            "order by pgm.type_name")
    List<CommonDropdownProjection> findByTypeId(Long typeId);

    @Query(nativeQuery = true, value = "select pgm.type_id as id, pgm.type_name as label, pgtm.g_type_id as generalType from payroll.payroll_general_mst pgm \n" +
            "join\n" +
            "payroll.payroll_general_type_mst pgtm\n" +
            "ON pgm.g_type_id=pgtm.g_type_id\n" +
            "where \n" +
            "pgtm.g_type_id=:typeId\n" +
            "and pgm.is_active=true and pgtm.is_active=true\n" +
            "order by pgm.type_name desc")
    List<CommonDropdownProjection> findByTypeIdDesc(Long typeId);

    @Query(nativeQuery = true, value = "select pgm.type_id as id, pgm.type_name as label, pgtm.g_type_id as generalType from payroll.payroll_general_mst pgm \n" +
            "join\n" +
            "payroll.payroll_general_type_mst pgtm\n" +
            "ON pgm.g_type_id=pgtm.g_type_id\n" +
            "where \n" +
            "pgtm.g_type_id=:typeId and pgm.parent_type_id=:parentId\n" +
            "and pgm.is_active=true and pgtm.is_active=true\n" +
            "order by pgm.type_name")
    List<CommonDropdownProjection> findByTypeIdAndParentId(Long typeId, Long parentId);

    List<GeneralMst> findByGeneralTypeId_TypeDescInAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue(List<String> typeDescList);

    @Query("select gm from GeneralMst gm where gm.parentTypeId.typeId =:parentId ")
	List<GeneralMst> findByParentTypeId(Long parentId);
}
