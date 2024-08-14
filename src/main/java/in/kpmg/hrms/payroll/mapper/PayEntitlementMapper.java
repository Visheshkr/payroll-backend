package in.kpmg.hrms.payroll.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpPayEntitlementOptionsReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpPayEntitlementReqDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayEntitlementOptionsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;
import in.kpmg.hrms.payroll.models.EmpPayEntitlementOptions;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;
import in.kpmg.hrms.payroll.models.PayrollPayHeadConfigMaster;
import in.kpmg.hrms.payroll.models.PayrollPayHeadMaster;

@Mapper(componentModel = "spring")
public interface PayEntitlementMapper {

    PayEntitlementMapper INSTANCE = Mappers.getMapper(PayEntitlementMapper.class);

    @Mapping(source = "pcId", target = "pcId", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "paylevel", target = "paylevel", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "ctaEntitlement", target = "ctaEntitlement", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "hraTier", target = "hraTier", qualifiedByName = "integerToHraTier")
    EmpPayEntitlementOptions payEntitlementOptionsDtoToEntity(EmpPayEntitlementOptionsReqDto empPayEntitlementOptionsReqDto);

    @Mapping(source = "payheadId", target = "payheadId", qualifiedByName = "integerToPayHead")
    @Mapping(source = "payheadConfigId", target = "payheadConfigId", qualifiedByName = "integerToPayHeadConfig")
    EmpPayEntitlement payEntitlementDtoToEntity(EmpPayEntitlementReqDto empPayEntitlementReqDto);

    @Mapping(source = "empId.empId", target = "empId")
    @Mapping(source = "hraTier.tierId", target = "hraTier.id")
    @Mapping(source = "hraTier.tierName", target = "hraTier.label")
    @Mapping(source = "ctaEntitlement.typeId", target = "ctaEntitlement.id")
    @Mapping(source = "ctaEntitlement.typeName", target = "ctaEntitlement.label")
    @Mapping(source = "paylevel.typeId", target = "paylevel.id")
    @Mapping(source = "paylevel.typeName", target = "paylevel.label")
    @Mapping(source = "pcId.typeId", target = "payCommission.id")
    @Mapping(source = "pcId.typeName", target = "payCommission.label")
    @Mapping(source = "crtBy.userId", target = "crtBy.id")
    @Mapping(source = "crtBy.userName", target = "crtBy.label")
    @Mapping(source = "updBy.userId", target = "updBy.id")
    @Mapping(source = "updBy.userName", target = "updBy.label")
    PayEntitlementOptionsResDto entityToDto(EmpPayEntitlementOptions empPayEntitlementOptions);

    @Mapping(source = "payheadId.payheadId", target = "payheadId")
    @Mapping(source = "payheadId.payheadName", target = "payheadName")
    @Mapping(source = "payheadConfigId.payheadConfigId", target = "payheadConfigId")
    @Mapping(source = "payheadValue", target = "payheadValue")
    @Mapping(source = "payheadId.payHeadType.typeId", target = "payheadType.id")
    @Mapping(source = "payheadId.payHeadType.typeName", target = "payheadType.label")
    PayheadResDto payheadEntityToDto(EmpPayEntitlement empPayEntitlement);

    @Named("mapPayheadResDtoList")
    default List<PayheadResDto> mapPayheadResDtoList(List<EmpPayEntitlement> entityList) {
        if (entityList == null) {
            return Collections.emptyList();
        }
        return entityList.stream()
                .map(this::payheadEntityToDto)
                .collect(Collectors.toList());
    }

    @Named("mapEmpPayEntitlementReqDtoList")
    default List<EmpPayEntitlement> mapEmpPayEntitlementReqDtoList(List<EmpPayEntitlementReqDto> dtoList) {
        if (dtoList == null) {
            return Collections.emptyList();
        }
        return dtoList.stream()
                .map(this::payEntitlementDtoToEntity)
                .collect(Collectors.toList());
    }

    @Named("longToGeneralMst")
    default GeneralMst longToGeneralMst(Long id) {
        if (id == null) {
            return null;
        }
        GeneralMst generalMst = new GeneralMst();
        generalMst.setTypeId(id);
        return generalMst;
    }

    @Named("integerToHraTier")
    default PayrollHRARateMst integerToHraTier(Integer id) {
        if (id == null) {
            return null;
        }
        PayrollHRARateMst payrollHRARateMst = new PayrollHRARateMst();
        payrollHRARateMst.setTierId(id);
        return payrollHRARateMst;
    }

    @Named("integerToPayHead")
    default PayrollPayHeadMaster integerToPayHead(Integer id) {
        if (id == null) {
            return null;
        }
        PayrollPayHeadMaster payHeadMaster = new PayrollPayHeadMaster();
        payHeadMaster.setPayheadId(id);
        return payHeadMaster;
    }

    @Named("integerToPayHeadConfig")
    default PayrollPayHeadConfigMaster integerToPayHeadConfig(Integer id) {
        if (id == null) {
            return null;
        }
        PayrollPayHeadConfigMaster payrollPayHeadConfigMaster = new PayrollPayHeadConfigMaster();
        payrollPayHeadConfigMaster.setPayheadConfigId(id);
        return payrollPayHeadConfigMaster;
    }


}
