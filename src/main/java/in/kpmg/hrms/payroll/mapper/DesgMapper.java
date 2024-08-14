package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.DesgnDto;
import in.kpmg.hrms.payroll.models.DesignationMst;

@Mapper(componentModel = "spring")
public interface DesgMapper {

	DesgMapper MAPPER = Mappers.getMapper(DesgMapper.class);

	DesignationMst mapToModel(DesgnDto desgnDto);

}
