package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionSchemeDto;
import in.kpmg.hrms.payroll.models.PayrollITSectionSchemeMst;

@Mapper(componentModel="spring")
public interface PayrollITSectionSchemeMstMapper {
	
	PayrollITSectionSchemeMstMapper INSTANCE = Mappers.getMapper(PayrollITSectionSchemeMstMapper.class);
	
	@Mapping(source="sectionName", target="sectionId.sectionName")
	@Mapping(source="sectionId", target="sectionId.id")
	@Mapping(source="crtBy", target="crtBy.userId")
	PayrollITSectionSchemeMst dtoToModel(ItSectionSchemeDto itSectionSchemeDto);
	
	@Mapping(source="sectionId.sectionName", target="sectionName")
	@Mapping(source="sectionId.id", target="sectionId")
	@Mapping(target="crtBy", source="crtBy.userId")
	ItSectionSchemeDto modelToDto(PayrollITSectionSchemeMst payrollITSectionSchemeMst);
	
	default List<ItSectionSchemeDto> modelToDtos(List<PayrollITSectionSchemeMst> payrollITSectionSchemeMst){
		List<ItSectionSchemeDto> response = new ArrayList<>();
		for(PayrollITSectionSchemeMst iter : payrollITSectionSchemeMst) {
			response.add(modelToDto(iter));
		}
		return response;		
	}
}
