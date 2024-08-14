package in.kpmg.hrms.payroll.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.UserRoleMapDto;
import in.kpmg.hrms.payroll.models.UserRoleMappingMst;

@Mapper(componentModel = "spring")

public interface UserRoleMapper {

	UserRoleMapper MAPPER = Mappers.getMapper(UserRoleMapper.class);

	List<UserRoleMappingMst> mapToModel(List<UserRoleMapDto> userRoleMaps);

	List<UserRoleMapDto> modelToDto(List<UserRoleMappingMst> userRoleMaps);

}
