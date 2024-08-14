package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.PayrollPayHeadDto;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadmst;
import in.kpmg.hrms.payroll.models.PayrollPayHeadMaster;
import in.kpmg.hrms.payroll.models.UserMst;

@Mapper(componentModel = "spring")
public interface PayheadMapper {
	
	PayheadMapper mapper = Mappers.getMapper(PayheadMapper.class);
	
	
	PayrollPayHeadMaster payHeadDtoToModel(PayrollPayHeadDto payrollPayHeadDto);
	
	
	@Mapping(source = "payheadGrpId.typeName", target = "payheadGrpName")
	@Mapping(source = "payheadGrpId.typeCode", target = "payheadGrpCode")
	
	@Mapping(source = "payHeadType.typeCode", target = "payHeadTypeCode")
	@Mapping(source = "payHeadType.typeName", target = "payHeadTypeName")

	@Mapping(source = "majorHead.typeId", target = "majorHead")
	@Mapping(source = "majorHead.typeCode", target = "majorHeadCode")
	@Mapping(source = "majorHead.typeName", target = "majorHeadName")
	
	@Mapping(source = "subMajorHead.typeId", target = "subMajorHead")
	@Mapping(source = "subMajorHead.typeCode", target = "subMajorHeadCode")
	@Mapping(source = "subMajorHead.typeName", target = "subMajorHeadName")
	
	@Mapping(source = "minorHead.typeId", target = "minorHead")
	@Mapping(source = "minorHead.typeCode", target = "minorHeadCode")
	@Mapping(source = "minorHead.typeName", target = "minorHeadName")
	
	@Mapping(source = "subHead.typeId", target = "subHead")
	@Mapping(source = "subHead.typeCode", target = "subHeadCode")
	@Mapping(source = "subHead.typeName", target = "subHeadName")
	
	@Mapping(source = "detailsHead.typeId", target = "detailsHead")
	@Mapping(source = "detailsHead.typeCode", target = "detailsHeadCode")
	@Mapping(source = "detailsHead.typeName", target = "detailsHeadName")
	
	@Mapping(source = "subDetailsHead.typeId", target = "subDetailsHead")
	@Mapping(source = "subDetailsHead.typeCode", target = "subDetailsHeadCode")
	@Mapping(source = "subDetailsHead.typeName", target = "subDetailsHeadName")

	PayrollPayHeadDto payHeadModelToDto(PayrollPayHeadMaster payHead);
	
	default List<PayrollPayHeadDto> payHeadModelToDto(List<PayrollPayHeadMaster> payHeads){
		List<PayrollPayHeadDto> response  =new ArrayList<>();
		
		for(PayrollPayHeadMaster payHead  : payHeads) {
			response.add(payHeadModelToDto(payHead));
		}
		return response;
	}
	

	default GeneralMst generalIdToMst(Long id) {
		if(id == null) {
			return null;
		}
		GeneralMst mst= new GeneralMst();
		mst.setTypeId(id);
		return mst;
	}
	
	default Long generalIdToMst(GeneralMst gm) {
		return gm == null ? null : gm.getTypeId();
	}
	
	default PayrollFinanceHeadmst idToModel(Integer id) {
		if(id == null) {
			return null;
		}
		PayrollFinanceHeadmst mst= new PayrollFinanceHeadmst();
		mst.setTypeId(id);
		return mst;
	}
	
	default Integer idToModel(PayrollFinanceHeadmst payrollFinanceHeadmst) {
		return payrollFinanceHeadmst == null ? null : payrollFinanceHeadmst.getTypeId();
	}
	
	default UserMst idToUserMst(Integer id) {
		if(id == null) {
			return null;
		}
		UserMst mst= new UserMst();
		mst.setUserId(id);
		return mst;
	}
	
	
}
