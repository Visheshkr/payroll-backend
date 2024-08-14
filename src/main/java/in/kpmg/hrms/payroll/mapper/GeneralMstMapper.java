package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.GeneralMasterSaveDto;
import in.kpmg.hrms.payroll.dtos.GeneralTypeMasterDto;
import in.kpmg.hrms.payroll.models.GeneralMst;

@Mapper(componentModel = "spring")
public interface GeneralMstMapper {

    GeneralMstMapper mapper = Mappers.getMapper(GeneralMstMapper.class);


    @Mapping(source = "generalTypeId.typeId", target = "generalTypeId")
    @Mapping(source = "parentTypeId", target = "parentTypeId")
    @Mapping(source = "createdBy.userId", target = "createdBy")
    GeneralMasterSaveDto entityToDto(GeneralMst generalMst);



    @Mapping(source = "generalTypeId", target = "generalTypeId.typeId")
    @Mapping(source = "parentTypeId", target = "parentTypeId.typeId")
    @Mapping(source = "createdBy", target = "createdBy.userId")
    GeneralMst dtoToEntity(GeneralMasterSaveDto dto);


    @Mapping(source = "createdBy.userId", target = "createdBy")
    GeneralTypeMasterDto toDto(GeneralMst generalMst);
    
    
    default GeneralMst idGeneralMst(Long id) {
		if(id == null) {
			return null;
		}
		GeneralMst gm = new GeneralMst();
		gm.setTypeId(id);
		return gm;
	}
    
    default Long generalMstToId(GeneralMst gm) {
    	return gm.getParentTypeId()!= null ? gm.getParentTypeId().getTypeId() : null;
		
	}
	
}
