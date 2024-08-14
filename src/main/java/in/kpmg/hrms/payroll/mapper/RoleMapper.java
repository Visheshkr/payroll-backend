package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RoleDto;
import in.kpmg.hrms.payroll.models.RoleMst;

@Mapper(componentModel = "spring")
public interface RoleMapper {

	RoleMapper MAPPER = Mappers.getMapper(RoleMapper.class);

	RoleMst mapToModel(RoleDto roleDto);

}
