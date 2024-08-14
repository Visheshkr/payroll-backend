package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxDeducHeadDto;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxDeductionHeadMaster;

@Mapper(componentModel = "spring")
public interface PayrollTaxDeducHeadMapper {
	
	PayrollTaxDeducHeadMapper mapper = Mappers.getMapper(PayrollTaxDeducHeadMapper.class);


    @Mapping(source = "taxEffectId", target = "taxEffectId.typeId")
    @Mapping(source = "payheadEffect", target = "payheadEffect.typeId")
    @Mapping(source = "userId", target = "createdBy.userId")
    
    PayrollIncomeTaxDeductionHeadMaster dtoToTaxDeduc(IncomeTaxDeducHeadDto incomeTaxDeducHeadDto);


}
