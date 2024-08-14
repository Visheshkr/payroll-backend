package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.MenuDto;
import in.kpmg.hrms.payroll.models.MenuMst;

@Mapper(componentModel = "spring")
public interface MenuMap {

	MenuMap MAPPER = Mappers.getMapper(MenuMap.class);

	MenuMst mapToModel(MenuDto menuDto);

}
