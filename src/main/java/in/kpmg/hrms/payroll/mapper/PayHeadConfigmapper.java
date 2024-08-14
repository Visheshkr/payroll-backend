package in.kpmg.hrms.payroll.mapper;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadConfigDto;
import in.kpmg.hrms.payroll.dtos.responseDto.PayHeadConfigResponse;
import in.kpmg.hrms.payroll.models.DesignationMst;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollDesignationDeptMapping;
import in.kpmg.hrms.payroll.models.PayrollPayHeadConfigMaster;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;

@Mapper(componentModel = "spring")
public interface PayHeadConfigmapper {

	PayHeadConfigmapper mapper = Mappers.getMapper(PayHeadConfigmapper.class);



	@Mapping(source = "payHeadId", target = "payHeadId.payheadId")
	@Mapping(source = "payCommissionId", target = "payCommissionId.typeId")
	PayrollPayHeadConfigMaster dtoToConfigMst(PayHeadConfigDto payHeadConfigDto);

	default PayrollServiceTypeMaster idToServicemst(Integer id) {
		if(id == null) {
			return null;
		}
		PayrollServiceTypeMaster serviceType = new PayrollServiceTypeMaster();
		serviceType.setId(id);
		return serviceType;
	}


	@Mapping(source = "payHeadId.payheadId", target = "payHeadId")
	@Mapping(source = "payHeadId.payheadName", target = "payHeadName")
	@Mapping(source = "payHeadId.payheadCode", target = "payHeadCode")
	@Mapping(source = "payCommissionId.typeId", target = "payCommissionId")
	@Mapping(source = "payCommissionId.typeName", target = "payCommissionName")
	@Mapping(source = "payCommissionId.typeCode", target = "payCommissionCode")
	
	@Mapping(source = "serviceType.id", target = "serviceType")
	@Mapping(source = "serviceType.serviceType", target = "serviceTypeName")
	@Mapping(source = "config.deptDegnId", target = "deptDegnId" )
	@Mapping(source = "payLevelId", target = "payLevelId" )
	@Mapping(source = "payLevelId", target = "payLevelName" )
	@Mapping(source = "deptDegnId.dsgnId.id" ,target = "dsgnId")
	@Mapping(source = "deptDegnId.dsgnId.name", target = "dsgnName" )
	@Mapping(source = "deptDegnId.deptId.deptId", target = "deptId" )
	@Mapping(source = "deptDegnId.deptId.name", target = "deptName" )

	PayHeadConfigResponse configModelToDto(PayrollPayHeadConfigMaster config);

	default PayHeadConfigResponse modelToDto(PayrollPayHeadConfigMaster configs, HashMap<Integer, String> hm){

		PayHeadConfigResponse configRes = configModelToDto(configs);
		if(configRes.getFormula()!=null) {
			configRes.setFormula(getLabelById(configs.getFormula(),hm));
		}
		
		return configRes;
	}



	default String getLabelById (String formula, HashMap<Integer, String> payHeads) {
		StringBuffer response = new StringBuffer();
		Pattern pattern = Pattern.compile("\\$(\\d+)\\$");
		Matcher matcher = pattern.matcher(formula);

		while(matcher.find()) {
			Integer id = Integer.parseInt(matcher.group(1)) ;
			String replacement = payHeads.get(id);
			if(replacement != null) {
				matcher.appendReplacement(response, replacement);
			}
			else {
				matcher.appendReplacement(response, matcher.group());
			}
		}
		matcher.appendTail(response);
		return response.toString();

	}


	default Integer servieTypeMstToId(PayrollServiceTypeMaster id) {
		return id == null ?  null :id.getId();
	}
	default String servieTypeMstToName(PayrollServiceTypeMaster id) {
		return id == null ?  null :id.getDescription();
	}
	default Integer deptDsgnMppingToInt(PayrollDesignationDeptMapping ddm) {
		return ddm == null ? null : ddm.getId();
	}
	default Integer dsgnMppingToInt(DesignationMst ddm) {
		return ddm == null ? null : ddm.getId();
	}
	default Integer dsgnMppingToName(DesignationMst ddm) {
		return ddm == null ? null : ddm.getId();
	}

	default Long GeneralMstToId(GeneralMst gm) {
		return gm == null ? null : gm.getTypeId();
	}

	default String GeneralMstToName(GeneralMst gm) {
		return gm == null ? null : gm.getTypeName();
	}







}
