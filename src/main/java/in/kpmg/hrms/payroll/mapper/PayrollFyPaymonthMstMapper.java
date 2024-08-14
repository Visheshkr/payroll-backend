package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.FyPaymonthSaveDto;
import in.kpmg.hrms.payroll.models.PayrollFyPaymonthMst;

@Mapper(componentModel = "spring")
public interface PayrollFyPaymonthMstMapper {
	
	PayrollFyPaymonthMstMapper INSTANCE = Mappers.getMapper(PayrollFyPaymonthMstMapper.class);
	
	@Mapping(source="fyId", target="fyId.typeId")
	@Mapping(source="monthId", target="monthId.monthId")
	@Mapping(source="currentYear", target="currentYear.typeId")
	PayrollFyPaymonthMst fyPaymonthDtoToModel(FyPaymonthSaveDto dto);
						 
	
	@Mapping(source = "fyId.typeId", target = "fyId")
	@Mapping(source = "monthId.monthId", target = "monthId")
	@Mapping(source = "currentYear.typeId", target = "currentYear")
	@Mapping(source = "fyId.typeName", target = "fyName") 
	@Mapping(source = "monthId.monthDesc", target = "monthName") 
	@Mapping(source = "currentYear.typeName", target = "currentYearName")
	FyPaymonthSaveDto fyPaymonthModelToDto(PayrollFyPaymonthMst model);
	
	default List<FyPaymonthSaveDto> fyPaymonthModelToDtos(List<PayrollFyPaymonthMst> payrollFyPaymonthMst){
		
		List<FyPaymonthSaveDto> response = new ArrayList<>();
		for(PayrollFyPaymonthMst iter : payrollFyPaymonthMst) {
			response.add(fyPaymonthModelToDto(iter));
		}
		return response;		
	}
}
