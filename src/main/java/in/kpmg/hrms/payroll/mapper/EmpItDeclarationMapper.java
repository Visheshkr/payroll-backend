package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationSchemeDto;
import in.kpmg.hrms.payroll.models.PayrollEmpItDeclarationMst;
import in.kpmg.hrms.payroll.models.PayrollEmpItDeclarationSchemeMst;

@Mapper(componentModel = "spring")
public interface EmpItDeclarationMapper {
	
	EmpItDeclarationMapper mapper =  Mappers.getMapper(EmpItDeclarationMapper.class);
	
	
	
	@Mapping(source = "empId", target = "empId.empId")
	@Mapping(source = "itDec", target = "itDec.id")
	@Mapping(source = "regimeType", target = "regimeType.typeId")
	@Mapping(source = "crtBy", target = "crtBy.userId")
//	@Mapping(source = "empItDecSchemes", target = "empItDecSchemes")
	PayrollEmpItDeclarationMst dtoToMst(EmpItDeclarationDto empItDeclarationDto);
	
	
	@Mapping(source = "sectionSchemeId", target = "sectionSchemeId.id")
	@Mapping(source = "empItDec", target = "empItDec.id")
	PayrollEmpItDeclarationSchemeMst schemeDtoToMst (EmpItDeclarationSchemeDto decScheme);
	
	default List<PayrollEmpItDeclarationSchemeMst> schemeDtosToMst(List<EmpItDeclarationSchemeDto> empItDecSchemeDtos){
		List<PayrollEmpItDeclarationSchemeMst> empItDecSchemes = new ArrayList<>();
		for(EmpItDeclarationSchemeDto dto : empItDecSchemeDtos) {
			empItDecSchemes.add(schemeDtoToMst(dto));
		}
		return empItDecSchemes;
		
	}

}
