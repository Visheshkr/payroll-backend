package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.PayBandServiceTypeDto;
import in.kpmg.hrms.payroll.models.PayrollBandServiceTypeMapping;

@Mapper(componentModel = "spring")
public interface PayrollBandServiceTypeMapper {
	
	PayrollBandServiceTypeMapper mapper = Mappers.getMapper(PayrollBandServiceTypeMapper.class);
	
	
	
	@Mapping(source = "payBandId", target = "payBandId.typeId", ignore = true)
	@Mapping(source = "pcServTypeId", target = "pcServTypeId.id", ignore = true)
	PayrollBandServiceTypeMapping dtoToPayrollBandServiceMst(PayBandServiceTypeDto payBandServiceTypeDto);
	
	
	
	@Mapping(target = "payBandId", source = "payBandId.typeId")
	@Mapping(target = "payBandCode", source = "payBandId.typeCode")
	@Mapping(target = "payBandName", source = "payBandId.typeName")
	@Mapping(target = "pcServTypeId", source = "pcServTypeId.id")
	@Mapping(target = "serviceTypeId", source = "pcServTypeId.servTypeId.id")
	@Mapping(target = "pcServTypeName", source = "pcServTypeId.servTypeId.serviceType")
	@Mapping(target = "payCommision", source = "pcServTypeId.pcId.typeName")
	@Mapping(target = "pcId", source = "pcServTypeId.pcId.typeId")
	@Mapping(target = "serviceTypeCode", source = "pcServTypeId.servTypeId.serviceTypeCode")
	@Mapping(target = "serviceTypeName", source = "pcServTypeId.servTypeId.serviceType")
	PayBandServiceTypeDto mstToPayBandServiceTypeDto(PayrollBandServiceTypeMapping payrollBandServiceTypeMapping);
	
	
	default List<PayBandServiceTypeDto> mstToPayBandServiceTypeDtos(List<PayrollBandServiceTypeMapping> payrollBandServiceTypeMappings){
		
		List<PayBandServiceTypeDto> response = new ArrayList<>();
		for(PayrollBandServiceTypeMapping map : payrollBandServiceTypeMappings) {
			response.add(mstToPayBandServiceTypeDto(map));
		}
		return response;		
	}

}
