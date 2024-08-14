package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.FinanceHeadSaveDto;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadmst;

@Mapper(componentModel = "spring")
public interface FinanceHeadMapper {
	FinanceHeadMapper mapper = Mappers.getMapper(FinanceHeadMapper.class);
	
	
	
	@Mapping(source = "parentId.typeId", target = "parentId")
	@Mapping(source = "parentId.typeCode", target = "parentCode")
	@Mapping(source = "parentId.typeName", target = "parentName")
	@Mapping(source = "ftypeId.ftypeId", target = "ftypeId")
//	@Mapping(source = "crtBy.userId", target = "crtBy")
	FinanceHeadSaveDto finMstToDto(PayrollFinanceHeadmst payrollFinanceHeadmst);
	
	
	default List<FinanceHeadSaveDto> financeMstToDto(List<PayrollFinanceHeadmst> payrollMst){
		List<FinanceHeadSaveDto> response = new ArrayList<>();
		for(PayrollFinanceHeadmst p  : payrollMst) {
			response.add(finMstToDto(p));
		}
		return response;
		
		
	}

}
