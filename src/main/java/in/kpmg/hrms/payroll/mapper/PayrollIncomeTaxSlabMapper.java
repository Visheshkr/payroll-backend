package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxSlabDto;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxSlabMaster;

@Mapper(componentModel = "spring")

public interface PayrollIncomeTaxSlabMapper {

	PayrollIncomeTaxSlabMapper mapper = Mappers.getMapper(PayrollIncomeTaxSlabMapper.class);


	    @Mapping(source = "regimeType", target = "regimeType.typeId")
	    @Mapping(source = "fyId", target = "fyId.typeId")
	    @Mapping(source = "taxSubCategory", target = "taxSubCategory.typeId")
	    @Mapping(source = "gender", target = "gender.typeId")
	    @Mapping(source = "userId", target = "crtBy.userId")
	    
	    PayrollIncomeTaxSlabMaster dtoToEntity(IncomeTaxSlabDto incomeTaxSlabDto);


}
