package in.kpmg.hrms.payroll.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SchmDeptDesgMapDto;
import in.kpmg.hrms.payroll.models.SchemeDeptDesignMappingModal;

@Mapper(componentModel = "spring")
public interface SchmDeptDsgMapper {

	SchmDeptDsgMapper MAPPER = Mappers.getMapper(SchmDeptDsgMapper.class);

	SchemeDeptDesignMappingModal mapToModel(SchmDeptDesgMapDto schDptDsgDto);

	List<SchmDeptDesgMapDto> mapToDTO(List<SchemeDeptDesignMappingModal> schDptDsgDto);

}
