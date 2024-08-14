package in.kpmg.hrms.payroll.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpDisabilityReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeReqDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpBankDtlsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpDisabilityResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpMstResDto;
import in.kpmg.hrms.payroll.models.Department;
import in.kpmg.hrms.payroll.models.DesignationMst;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollCadreMaster;
import in.kpmg.hrms.payroll.models.PayrollEmployeeDisability;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;
import in.kpmg.hrms.payroll.models.PayrollLocationMaster;
import in.kpmg.hrms.payroll.models.PayrollOfficeMaster;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;
import in.kpmg.hrms.payroll.models.StepRoleMapping;

@Mapper(componentModel = "spring")
public interface EmpMstMapper {
    EmpMstMapper INSTANCE = Mappers.getMapper(EmpMstMapper.class);

    @Mapping(source = "prefix", target = "prefix", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "taskTypeId", target = "taskTypeId", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "gender", target = "gender", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "heightMeasure", target = "heightMeasure", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "religion", target = "religion", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "bloodGroup", target = "bloodGroup", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "nationality", target = "nationality", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "socialCategory", target = "socialCategory", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "gpfPranType", target = "gpfPranType", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "employeeType", target = "employeeType", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "serviceType", target = "serviceType", qualifiedByName = "integerToServiceType")
    @Mapping(source = "cadreId", target = "cadreId", qualifiedByName = "longToCadreMaster")
    @Mapping(source = "quarterType", target = "quarterType", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "parentDeptId", target = "parentDeptId", qualifiedByName = "integerToDepartment")
    @Mapping(source = "currentDeptId", target = "currentDeptId", qualifiedByName = "integerToDepartment")
    @Mapping(source = "currentDsgnId", target = "currentDsgnId", qualifiedByName = "integerToDesignation")
    @Mapping(source = "currentOffice", target = "currentOffice", qualifiedByName = "longToOffice")
    @Mapping(source = "ordIssuingOffice", target = "ordIssuingOffice", qualifiedByName = "longToOffice")
    @Mapping(source = "joiningTime", target = "joiningTime", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "payslipAuthority", target = "payslipAuthority", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "grpId", target = "grpId", qualifiedByName = "longToGroupMaster")
    @Mapping(source = "personalMobileNo", target = "personalMobileNo", qualifiedByName = "stringToLong")
    @Mapping(source = "stateId", target="stateId", qualifiedByName = "longToLocationMaster")
    @Mapping(source = "distId", target="distId", qualifiedByName = "longToLocationMaster")
    PayrollEmployeeMaster toEntity(EmployeeReqDto dto);

    @Mapping(source = "taskTypeId.typeId", target = "taskTypeId.id")
    @Mapping(source = "taskTypeId.typeName", target = "taskTypeId.label")
    @Mapping(source = "prefix.typeId", target = "prefix.id")
    @Mapping(source = "prefix.typeName", target = "prefix.label")
    @Mapping(source = "gender.typeId", target = "gender.id")
    @Mapping(source = "gender.typeName", target = "gender.label")
    @Mapping(source = "heightMeasure.typeId", target = "heightMeasure.id")
    @Mapping(source = "heightMeasure.typeName", target = "heightMeasure.label")
    @Mapping(source = "maritalStatus.typeId", target = "maritalStatus.id")
    @Mapping(source = "maritalStatus.typeName", target = "maritalStatus.label")
    @Mapping(source = "religion.typeId", target = "religion.id")
    @Mapping(source = "religion.typeName", target = "religion.label")
    @Mapping(source = "bloodGroup.typeId", target = "bloodGroup.id")
    @Mapping(source = "bloodGroup.typeName", target = "bloodGroup.label")
    @Mapping(source = "nationality.typeId", target = "nationality.id")
    @Mapping(source = "nationality.typeName", target = "nationality.label")
    @Mapping(source = "socialCategory.typeId", target = "socialCategory.id")
    @Mapping(source = "socialCategory.typeName", target = "socialCategory.label")
    @Mapping(source = "gpfPranType.typeId", target = "gpfPranType.id")
    @Mapping(source = "gpfPranType.typeName", target = "gpfPranType.label")
    @Mapping(source = "employeeType.typeId", target = "employeeType.id")
    @Mapping(source = "employeeType.typeName", target = "employeeType.label")
    @Mapping(source = "serviceType.id", target = "serviceType.id")
    @Mapping(source = "serviceType.serviceType", target = "serviceType.label")
    @Mapping(source = "cadreId.id", target = "cadreId.id")
    @Mapping(source = "cadreId.cadreName", target = "cadreId.label")
    @Mapping(source = "quarterType.typeId", target = "quarterType.id")
    @Mapping(source = "quarterType.typeName", target = "quarterType.label")
    @Mapping(source = "parentDeptId.deptId", target = "parentDeptId.id")
    @Mapping(source = "parentDeptId.name", target = "parentDeptId.label")
    @Mapping(source = "currentDeptId.deptId", target = "currentDeptId.id")
    @Mapping(source = "currentDeptId.name", target = "currentDeptId.label")
    @Mapping(source = "currentDsgnId.id", target = "currentDsgnId.id")
    @Mapping(source = "currentDsgnId.name", target = "currentDsgnId.label")
    @Mapping(source = "currentOffice.ofcId", target = "currentOffice.id")
    @Mapping(source = "currentOffice.officeName", target = "currentOffice.officeName")
    @Mapping(source = "currentOffice.addressLine1", target = "currentOffice.address")
    @Mapping(source = "ordIssuingOffice.ofcId", target = "ordIssuingOffice.id")
    @Mapping(source = "ordIssuingOffice.officeName", target = "ordIssuingOffice.officeName")
    @Mapping(source = "ordIssuingOffice.addressLine1", target = "ordIssuingOffice.address")
    @Mapping(source = "joiningTime.typeId", target = "joiningTime.id")
    @Mapping(source = "joiningTime.typeName", target = "joiningTime.label")
    @Mapping(source = "payslipAuthority.typeId", target = "payslipAuthority.id")
    @Mapping(source = "payslipAuthority.typeName", target = "payslipAuthority.label")
    @Mapping(source = "status.stepRoleId", target = "status.id")
    @Mapping(source = "status.caseStatusName", target = "status.label")
    @Mapping(source = "grpId.grpId", target = "grpId.id")
    @Mapping(source = "grpId.grpName", target = "grpId.label")
    @Mapping(source = "stateId.locId", target = "stateId.id")
    @Mapping(source = "stateId.locName", target = "stateId.label")
    @Mapping(source = "distId.locId", target = "distId.id")
    @Mapping(source = "distId.locName", target = "distId.label")
    @Mapping(source = "crtBy.userId", target = "crtBy.id")
    @Mapping(source = "crtBy.userName", target = "crtBy.label")
    @Mapping(source = "updBy.userId", target = "updBy.id")
    @Mapping(source = "updBy.userName", target = "updBy.label")
    @Mapping(source = "personalMobileNo", target = "personalMobileNo", qualifiedByName = "longToString")
    EmpMstResDto toDto(PayrollEmployeeMaster entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "ifscCode", target = "ifscCode")
    @Mapping(source = "bankAccNo", target = "bankAccNo")
    @Mapping(source = "payeeBenefId", target = "payeeBenefId")
    void toEntityBankDetails(EmployeeReqDto dto, @MappingTarget PayrollEmployeeMaster entity);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "ifscCode", target = "ifscCode")
    @Mapping(source = "bankAccNo", target = "bankAccNo")
    @Mapping(source = "payeeBenefId", target = "payeeBenefId")
    EmpBankDtlsResDto toBankDetailsDto(PayrollEmployeeMaster payrollEmployeeMaster);

    @Mapping(source = "disabilityType", target = "disabilityType", qualifiedByName = "longToGeneralMst")
    @Mapping(source = "disabilityPercent", target = "percentage")
    PayrollEmployeeDisability toEmpDisability(EmpDisabilityReqDto empDisabilityReqDto);

    @Mapping(source = "disabilityType.typeId", target = "disabilityType.id")
    @Mapping(source = "disabilityType.typeName", target = "disabilityType.label")
    @Mapping(source = "percentage", target = "disabilityPercent")
    EmpDisabilityResDto toEmpDisabilityDto(PayrollEmployeeDisability payrollEmployeeDisability);

    @Named("stringToLong")
    default Long stringToLong(String mobileNo) {
        if (mobileNo == null || mobileNo.isEmpty()) {
            return null;
        }
        return Long.parseLong(mobileNo);
    }

    @Named("longToString")
    default String longToString(Long mobileNo) {
        if (mobileNo == null) {
            return null;
        }
        return String.valueOf(mobileNo);
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

    @Named("integerToServiceType")
    default PayrollServiceTypeMaster integerToServiceType(Integer id) {
        if (id == null) {
            return null;
        }
        PayrollServiceTypeMaster serviceType = new PayrollServiceTypeMaster();
        serviceType.setId(id);
        return serviceType;
    }

    @Named("longToCadreMaster")
    default PayrollCadreMaster longToCadreMaster(Long id) {
        if (id == null) {
            return null;
        }
        PayrollCadreMaster cadre = new PayrollCadreMaster();
        cadre.setId(id);
        return cadre;
    }

    @Named("integerToDepartment")
    default Department integerToDepartment(Integer id) {
        if (id == null) {
            return null;
        }
        Department department = new Department();
        department.setDeptId(id);
        return department;
    }

    @Named("integerToDesignation")
    default DesignationMst integerToDesignation(Integer id) {
        if (id == null) {
            return null;
        }
        DesignationMst designation = new DesignationMst();
        designation.setId(id);
        return designation;
    }

    @Named("longToOffice")
    default PayrollOfficeMaster longToOffice(Long id) {
        if (id == null) {
            return null;
        }
        PayrollOfficeMaster office = new PayrollOfficeMaster();
        office.setOfcId(id);
        return office;
    }

    @Named("longToStepRoleMapping")
    default StepRoleMapping longToStepRoleMapping(Long id) {
        if (id == null) {
            return null;
        }
        StepRoleMapping stepRole = new StepRoleMapping();
        stepRole.setStepRoleId(id);
        return stepRole;
    }

    @Named("longToGroupMaster")
    default PayrollGroupMaster longToGroupMaster(Long id) {
        if (id == null) {
            return null;
        }
        PayrollGroupMaster group = new PayrollGroupMaster();
        group.setGrpId(id);
        return group;
    }

    @Named("longToLocationMaster")
    default PayrollLocationMaster longToLocationMaster(Long id) {
        if (id == null) {
            return null;
        }
        PayrollLocationMaster payrollLocationMaster = new PayrollLocationMaster();
        payrollLocationMaster.setLocId(id);
        return payrollLocationMaster;
    }

    default Long generalMstToLong(GeneralMst generalMst) {
        return generalMst != null ? generalMst.getTypeId() : null;
    }

    default Integer serviceTypeToInteger(PayrollServiceTypeMaster serviceType) {
        return serviceType != null ? serviceType.getId() : null;
    }

    default Long cadreMasterToLong(PayrollCadreMaster cadre) {
        return cadre != null ? cadre.getId() : null;
    }

    default Integer departmentToInteger(Department department) {
        return department != null ? department.getDeptId() : null;
    }

    default Integer designationToInteger(DesignationMst designation) {
        return designation != null ? designation.getId() : null;
    }

    default Long officeToLong(PayrollOfficeMaster office) {
        return office != null ? office.getOfcId() : null;
    }

    default Long stepRoleMappingToLong(StepRoleMapping stepRole) {
        return stepRole != null ? stepRole.getStepRoleId() : null;
    }

    default Long groupMasterToLong(PayrollGroupMaster group) {
        return group != null ? group.getGrpId() : null;
    }


}
