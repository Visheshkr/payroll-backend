package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.GroupCreationDto;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;

@Mapper(componentModel = "spring")
public interface PayrollGroupCreationMapper {
	
	PayrollGroupCreationMapper mapper = Mappers.getMapper(PayrollGroupCreationMapper.class);
	
	
	@Mapping(source = "hoaId", target = "hoaId.id")
	@Mapping(source = "officeId", target = "officeId.ofcId")
	@Mapping(source = "status", target = "status.stepRoleId")
	PayrollGroupMaster dtoToGroupMst(GroupCreationDto groupDto);
	
	
	
	@Mapping(target = "hoaId", source = "hoaId.id")
	@Mapping(target = "hoaName", source = "hoaId.demandNo")
	@Mapping(target = "hoa", source = "hoaId.hoa")
	@Mapping(target = "officeId", source = "officeId.ofcId")
	@Mapping(target = "officeName", source = "officeId.officeName")
	@Mapping(target = "officeCode", source = "officeId.ofcCode")
	@Mapping(target = "statusVal", source = "status.caseStatusName")
	@Mapping(target = "status", source = "status.stepRoleId")
	GroupCreationDto groupMstToDto(PayrollGroupMaster group);
	
	
	default List<GroupCreationDto> groupMstToDtos(List<PayrollGroupMaster> groups){
		List<GroupCreationDto> response = new ArrayList<>();
		for(PayrollGroupMaster group : groups ) {
			response.add(groupMstToDto(group));
		}
		return response;
		
	}

}
