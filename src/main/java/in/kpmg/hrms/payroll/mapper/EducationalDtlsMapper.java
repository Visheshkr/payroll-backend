package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EducationalDtlsDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpEducationalDtlsResDto;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollEducationalDetails;

@Mapper(componentModel = "spring")
public interface EducationalDtlsMapper {

    EducationalDtlsMapper INSTANCE = Mappers.getMapper(EducationalDtlsMapper.class);

    @Mapping(source = "qualificationId", target = "qualificationId", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "marksCgpaId", target = "marksCgpaId", qualifiedByName = "longToGeneralMst")
    PayrollEducationalDetails dtoToEntity(EducationalDtlsDto educationalDtlsDto);

    @Mapping(source = "empId.empId", target = "empId")
    @Mapping(source = "qualificationId.typeId", target = "qualificationId.id")
    @Mapping(source = "qualificationId.typeName", target = "qualificationId.label")
    @Mapping(source = "marksCgpaId.typeId", target = "marksCgpaId.id")
    @Mapping(source = "marksCgpaId.typeName", target = "marksCgpaId.label")
    @Mapping(source = "crtBy.userId", target = "crtBy.id")
    @Mapping(source = "crtBy.userName", target = "crtBy.label")
    @Mapping(source = "updBy.userId", target = "updBy.id")
    @Mapping(source = "updBy.userName", target = "updBy.label")
    EmpEducationalDtlsResDto EntityToDto(PayrollEducationalDetails payrollEducationalDetails);

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
