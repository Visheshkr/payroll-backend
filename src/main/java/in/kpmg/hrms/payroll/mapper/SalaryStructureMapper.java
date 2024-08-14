package in.kpmg.hrms.payroll.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.SalaryStructureResponseDto;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;

@Mapper(componentModel = "spring")
public interface SalaryStructureMapper {

    SalaryStructureMapper INSTANCE = Mappers.getMapper(SalaryStructureMapper.class);

    @Mapping(source = "empId", target = "employeeCode")
    @Mapping(target = "employeeName", expression = "java(combineName(payrollEmployeeMaster.getFirstName(), payrollEmployeeMaster.getMiddleName(), payrollEmployeeMaster.getLastName()))")
    @Mapping(source = "currentOffice.officeName", target = "currentOffice")
    @Mapping(source = "currentDsgnId.name", target = "designation")
    @Mapping(source = "bankAccNo", target = "bankAccountNumber")
    @Mapping(source = "grpId.grpName", target = "group")
    @Mapping(source = "serviceType.serviceType", target = "serviceType")
//    @Mapping(source = "ifscCode", target = "ifscCode")
//    @Mapping(source = "gpfPranNo", target = "gpfPranNo")
//    @Mapping(source = "panNo", target = "panNo")
//    @Mapping(source = "bankName", target = "bankName")
    SalaryStructureResponseDto toDto(PayrollEmployeeMaster payrollEmployeeMaster);

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


    default String combineName(String firstName, String middleName, String lastName) {
        return (firstName != null ? firstName : "") +
                (middleName != null && !middleName.isEmpty() ? " " + middleName : "") +
                (lastName != null ? " " + lastName : "");
    }
}
