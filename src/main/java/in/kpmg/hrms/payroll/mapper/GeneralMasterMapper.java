package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.GeneralMasterSaveDto;
import in.kpmg.hrms.payroll.dtos.GeneralTypeMasterDto;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.UserMst;

@Mapper(componentModel = "spring")
public interface GeneralMasterMapper {

	GeneralMasterMapper mapper = Mappers.getMapper(GeneralMasterMapper.class);


	@Mapping(source = "createdBy.userId", target = "createdBy")
	GeneralTypeMasterDto generalMstToDto(GeneralMst gm);

	@Mapping(source = "generalTypeId.typeId", target = "generalTypeId")
	@Mapping(source = "parentTypeId.typeId", target = "parentTypeId")
	@Mapping(source = "createdBy.userId", target = "createdBy")
	GeneralMasterSaveDto generalMstToSaveDto(GeneralMst gm);


	@Mapping(source = "parentTypeId", target = "parentTypeId")
	@Mapping(source = "generalTypeId", target = "generalTypeId.typeId")
	@Mapping(source = "createdBy", target = "createdBy")
	GeneralMst dtoToGeneralMst(GeneralMasterSaveDto saveDto);


	default GeneralMst idGeneralMst(Long id) {
		if(id == null) {
			return null;
		}
		GeneralMst gm = new GeneralMst();
		gm.setTypeId(id);
		return gm;
	}
	
	default UserMst userToUserMst(Integer userId) {
		if(userId == null) {
			return null;
		}
		UserMst um = new UserMst();
		um.setUserId(userId);
		return um;
	}
	
	default Long generalMstToId(GeneralMst gm) {
    	return gm.getParentTypeId()!= null ? gm.getParentTypeId().getTypeId() : null;	
	}

}
