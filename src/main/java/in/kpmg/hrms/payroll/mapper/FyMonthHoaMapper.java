package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.FyMonthStatusDto;
import in.kpmg.hrms.payroll.models.PayrollFyMonthStatusMst;

@Mapper(componentModel = "spring")
public interface FyMonthHoaMapper {
	
	FyMonthHoaMapper mapper = Mappers.getMapper(FyMonthHoaMapper.class);
	
	
	
	@Mapping(source = "fyMonthId", target = "fyMonthId.id")
	@Mapping(source = "hoaId", target = "hoaId.id")
	@Mapping(source = "status", target = "status.typeId")
	@Mapping(source = "crtBy", target = "crtBy.userId")
	PayrollFyMonthStatusMst ftoToEntity(FyMonthStatusDto fyMonthStatusDto);
	
	
	@Mapping(source = "fyMonthId.fyId.typeId", target = "fyMonthId")
	@Mapping(source = "fyMonthId.fyId.typeName", target = "fy")
	@Mapping(source = "fyMonthId.monthId.monthId", target = "monthId")
	@Mapping(source = "fyMonthId.monthId.monthDesc", target = "monthName")
	@Mapping(source = "fyMonthId.currentYear.typeName", target = "currentYear")
	@Mapping(source = "hoaId.id", target = "hoaId")
	@Mapping(source = "hoaId.hoa", target = "hoa")
	@Mapping(source = "status.typeId", target = "status")
	@Mapping(source = "status.typeName", target = "statusVal")
	@Mapping(source = "crtBy", target = "crtBy", ignore = true)
	FyMonthStatusDto mstToDto(PayrollFyMonthStatusMst mst);
	
	
	default List<FyMonthStatusDto> mstToDtoList(List<PayrollFyMonthStatusMst> fyMonHoas){
		List<FyMonthStatusDto> res = new ArrayList<>();
		for(PayrollFyMonthStatusMst mst : fyMonHoas) {
			res.add(mstToDto(mst));
		}
		return res;
	}
	

}
