package in.kpmg.hrms.payroll.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.responseDto.SalarySlipResponseDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingDetails;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;

@Mapper(componentModel = "spring")

public interface SalarySlipMapper {

    SalarySlipMapper INSTANCE = Mappers.getMapper(SalarySlipMapper.class);

    @Mapping(source = "empId", target = "employeeCode")
    @Mapping(target = "employeeName", expression = "java(combineName(payrollEmployeeMaster.getFirstName(), payrollEmployeeMaster.getMiddleName(), payrollEmployeeMaster.getLastName()))")
    @Mapping(source = "currentOffice.officeName", target = "currentOffice")
    @Mapping(source = "currentDsgnId.name", target = "designation")
    @Mapping(source = "joiningDate", target = "doj")
    @Mapping(source = "bankAccNo", target = "bankAccountNumber")
    @Mapping(source = "grpId.grpName", target = "grade")
    @Mapping(source = "superannuationDate", target = "dateOfRetirement")
    @Mapping(source = "serviceType.serviceType", target = "serviceType")
//    @Mapping(source = "ifscCode", target = "ifscCode")
//    @Mapping(source = "gpfPranNo", target = "gpfPranNo")
//    @Mapping(source = "panNo", target = "panNo")
//    @Mapping(source = "bankName", target = "bankName")
    SalarySlipResponseDto toDto(PayrollEmployeeMaster payrollEmployeeMaster);


    @Mapping(source = "payheadId.payheadId", target = "payheadId")
    @Mapping(source = "payheadId.payheadName", target = "payheadName")
    @Mapping(source = "payheadConfigId.payheadConfigId", target = "payheadConfigId")
    @Mapping(source = "payheadValue", target = "payheadValue")
    @Mapping(source = "payheadId.payHeadType.typeId", target = "payheadType.id")
    @Mapping(source = "payheadId.payHeadType.typeName", target = "payheadType.label")
    PayheadResDto payheadEntityToDto(EmpSalaryProcessingDetails empSalaryProcessingDetails);

    @Named("mapPayheadResDtoList")
    default List<PayheadResDto> mapPayheadResDtoList(List<EmpSalaryProcessingDetails> entityList) {
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
