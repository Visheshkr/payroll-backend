package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.DeptDto;
import in.kpmg.hrms.payroll.models.Department;

@Mapper(componentModel = "spring")
public interface DeptMapper {

	DeptMapper MAPPER = Mappers.getMapper(DeptMapper.class);

	Department mapToModel(DeptDto deptDto);

}
