package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeDocumentsDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpDocumentsResDto;
import in.kpmg.hrms.payroll.models.EmployeeDocuments;
import in.kpmg.hrms.payroll.models.GeneralMst;

@Mapper(componentModel = "spring")
public interface EmployeeDocumentsMapper {
    EmployeeDocumentsMapper INSTANCE = Mappers.getMapper(EmployeeDocumentsMapper.class);


    @Mapping(source = "documentId", target = "documentId", qualifiedByName = "longToGeneralMst")
    EmployeeDocuments employeeDocumentsDtoToEntity(EmployeeDocumentsDto employeeDocumentsDto);

    @Mapping(source = "empId.empId", target = "empId")
    @Mapping(source = "documentId.typeId", target = "documentId.id")
    @Mapping(source = "documentId.typeName", target = "documentId.label")
    @Mapping(source = "crtBy.userId", target = "crtBy.id")
    @Mapping(source = "crtBy.userName", target = "crtBy.label")
    @Mapping(source = "updBy.userId", target = "updBy.id")
    @Mapping(source = "updBy.userName", target = "updBy.label")
    EmpDocumentsResDto entityToDto(EmployeeDocuments employeeDocuments);

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
