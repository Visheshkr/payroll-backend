package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SaveRoleMenuRightMapListDto;
import in.kpmg.hrms.payroll.models.RoleMenuMappingMst;

@Mapper(componentModel = "spring")

public interface RoleMenuMap {

	RoleMenuMappingMst MAPPER = Mappers.getMapper(RoleMenuMappingMst.class);

	RoleMenuMappingMst mapToModel(SaveRoleMenuRightMapListDto userRoleMaps);

}
