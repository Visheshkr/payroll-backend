package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadServiceTypeMappingDto;
import in.kpmg.hrms.payroll.models.PayrollPayHeadServiceTypeMapping;

@Mapper(componentModel="spring")
public interface PayHeadServTypeMappingMapper {
	PayHeadServTypeMappingMapper INSTANCE = Mappers.getMapper(PayHeadServTypeMappingMapper.class);
	
	@Mapping(source="serviceTypeId", target="serviceTypeId.id")
	@Mapping(source="payheadId", target="payheadId.payheadId")
	PayrollPayHeadServiceTypeMapping dtoToModel(PayHeadServiceTypeMappingDto payHeadServiceTypeMappingDto);

	@Mapping(source="payheadId.payheadName", target="payheadName")
	@Mapping(source="payheadId.payheadCode", target="payheadCode")
	@Mapping(source="payheadId.payHeadType.typeName", target="payHeadTypeValue")
	@Mapping(source="serviceTypeId.serviceType", target="serviceType")
	@Mapping(source="payheadId.payheadId", target="payheadId")
	@Mapping(source="serviceTypeId.id", target="serviceTypeId")
	@Mapping(source="payheadId.payHeadType.typeId", target="payHeadType")
	PayHeadServiceTypeMappingDto modelToDto(PayrollPayHeadServiceTypeMapping payrollPayHeadServiceTypeMapping);

	default List<PayHeadServiceTypeMappingDto> modelToDtos(List<PayrollPayHeadServiceTypeMapping> payrollPayHeadServiceTypeMapping){
		List<PayHeadServiceTypeMappingDto> response = new ArrayList<>();
		for(PayrollPayHeadServiceTypeMapping iter : payrollPayHeadServiceTypeMapping) {
			response.add(modelToDto(iter));
		}
		return response;		
	}
}
