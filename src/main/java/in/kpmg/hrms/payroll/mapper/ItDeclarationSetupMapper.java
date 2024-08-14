package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationWindow;
import in.kpmg.hrms.payroll.models.PayrollTaxDeclarationMaster;

@Mapper(componentModel = "spring")
public interface ItDeclarationSetupMapper {
	
	ItDeclarationSetupMapper mapper = Mappers.getMapper(ItDeclarationSetupMapper.class);
	
	
	@Mapping(source = "fyId.typeId", target = "fyId")
	@Mapping(source = "fyId.typeName", target = "fy")
	EmpItDeclarationWindow dtoToMst(PayrollTaxDeclarationMaster payrollTaxDeclarationMaster);

}
