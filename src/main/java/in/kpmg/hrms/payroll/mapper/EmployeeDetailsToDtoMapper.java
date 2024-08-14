package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpPayHeadDetailsResponse;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpSalaryProcess;


@Mapper(componentModel = "spring")
public interface EmployeeDetailsToDtoMapper {
	
	EmployeeDetailsToDtoMapper mapper = Mappers.getMapper(EmployeeDetailsToDtoMapper.class);
	
	
	
	
	
	
	EmpPayHeadDetailsResponse interfaceToDto(EmpSalaryProcess employee);

}
