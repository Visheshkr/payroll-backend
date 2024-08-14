package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.PayMatrixSaveDto;
import in.kpmg.hrms.payroll.models.PayrollPayMatrix;

@Mapper(componentModel = "spring")
public interface PayMatrixMapper {
	
	PayMatrixMapper INSTANCE = Mappers.getMapper(PayMatrixMapper.class);
	
	@Mapping(source="payBandId", target="payBandId.id")
	PayrollPayMatrix dtoToModel(PayMatrixSaveDto dto);

}
