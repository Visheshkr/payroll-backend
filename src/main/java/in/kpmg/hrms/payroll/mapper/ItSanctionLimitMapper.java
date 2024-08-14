package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.PayrollitSanctionLimitDto;
import in.kpmg.hrms.payroll.models.PayrollITSanctionLimitMst;

@Mapper(componentModel = "spring")
public interface ItSanctionLimitMapper {
	
	ItSanctionLimitMapper mapper = Mappers.getMapper(ItSanctionLimitMapper.class);
	
	
	@Mapping(source = "itsectionId", target = "itsectionId.id", ignore = true)
	@Mapping(source = "userId", target = "createdBy.userId")
	PayrollITSanctionLimitMst dtoToSanctionLimit(PayrollitSanctionLimitDto payrollitSanctionLimitDto);
	
	

}
