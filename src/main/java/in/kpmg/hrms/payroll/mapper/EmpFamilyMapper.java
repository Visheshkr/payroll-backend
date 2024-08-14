package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpFamilyDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpFamilyResDto;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollEmpFamily;

@Mapper(componentModel = "spring")
public interface EmpFamilyMapper {

    EmpFamilyMapper INSTANCE = Mappers.getMapper(EmpFamilyMapper.class);

    @Mapping(source = "relationshipId", target = "relationshipId", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "longToGeneralMst")
    PayrollEmpFamily dtoToEntity(EmpFamilyDto empFamilyDto);

    @Mapping(source = "empId.empId", target="empId")
    @Mapping(source = "relationshipId.typeId", target="relationshipId.id")
    @Mapping(source = "relationshipId.typeName", target="relationshipId.label")
    @Mapping(source = "gender.typeId", target = "gender.id")
    @Mapping(source = "gender.typeName", target = "gender.label")
    @Mapping(source = "maritalStatus.typeId", target = "maritalStatus.id")
    @Mapping(source = "maritalStatus.typeName", target = "maritalStatus.label")
    @Mapping(source = "status.stepRoleId", target = "status.id")
    @Mapping(source = "status.caseStatusName", target = "status.label")
    @Mapping(source = "crtBy.userId", target = "crtBy.id")
    @Mapping(source = "crtBy.userName", target = "crtBy.label")
    @Mapping(source = "updBy.userId", target = "updBy.id")
    @Mapping(source = "updBy.userName", target = "updBy.label")
    EmpFamilyResDto EntityToDto(PayrollEmpFamily payrollEmpFamily);

    @Named("longToGeneralMst")
    default GeneralMst longToGeneralMst(Long id) {
        if (id == null) {
            return null;
        }
        GeneralMst generalMst = new GeneralMst();
        generalMst.setTypeId(id);
        return generalMst;
    }

}
