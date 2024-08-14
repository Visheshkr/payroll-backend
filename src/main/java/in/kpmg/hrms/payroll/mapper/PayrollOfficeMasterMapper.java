package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.OfficeMasterDto;
import in.kpmg.hrms.payroll.models.PayrollOfficeMaster;

@Mapper(componentModel = "spring")
public interface PayrollOfficeMasterMapper {

	PayrollOfficeMasterMapper INSTANCE = Mappers.getMapper(PayrollOfficeMasterMapper.class);

	@Mapping(source = "ofcTypeId", target = "ofcTypeId.typeId")
	@Mapping(source = "ofcLevelId", target = "ofcLevelId.typeId")
	@Mapping(source = "deptCode", target = "deptCode.deptId")
	@Mapping(source = "stateId", target = "stateId.locId")
	@Mapping(source = "divisionId", target = "divisionId.locId")
	@Mapping(source = "districtId", target = "districtId.locId")
	@Mapping(source = "blockId", target = "blockId.locId")
	@Mapping(source = "subDivisionId", target = "subDivisionId.locId")
	@Mapping(source = "userId", target = "crtBy.userId")
	PayrollOfficeMaster officeMasterDtoToModel(OfficeMasterDto officeMasterDto);
	
	@Mapping(source = "ofcTypeId.typeId", target = "ofcTypeId")
	@Mapping(source = "ofcLevelId.typeId", target = "ofcLevelId")
	@Mapping(source = "deptCode.deptId", target = "deptCode")
	@Mapping(source = "stateId.locId", target = "stateId")
	@Mapping(source = "divisionId.locId", target = "divisionId")
	@Mapping(source = "districtId.locId", target = "districtId")
	@Mapping(source = "blockId.locId", target = "blockId")
	@Mapping(source = "subDivisionId.locId", target = "subDivisionId")
	@Mapping(source = "crtBy.userId", target = "userId")
	OfficeMasterDto officeMasterModelToDto(PayrollOfficeMaster payrollOfficeMaster);
	
	default List<OfficeMasterDto> officeMasterModelToDtos(List<PayrollOfficeMaster> ofcMstList){
		
		List<OfficeMasterDto> result = new ArrayList<>();
		for(PayrollOfficeMaster iter : ofcMstList) {
			result.add(officeMasterModelToDto(iter));
		}
		return result;		
	}
}
