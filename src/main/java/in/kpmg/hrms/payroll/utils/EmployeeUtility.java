package in.kpmg.hrms.payroll.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpDisabilityReqDto;
import in.kpmg.hrms.payroll.mapper.EmpMstMapper;
import in.kpmg.hrms.payroll.models.PayrollEmployeeDisability;

public class EmployeeUtility {
    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date calculateSuperannuationDate(Date dob, Integer retirementAge) {
        LocalDate localDob = convertToLocalDate(dob);
        LocalDate retirementDate;
        if (localDob.getDayOfMonth() == 1) {
            // Birthday is on the 1st of the month
            retirementDate = localDob.plusYears(retirementAge).minusMonths(1).withDayOfMonth(localDob.minusMonths(1).lengthOfMonth());
        } else {
            // Birthday is on any other day of the month
            retirementDate = localDob.plusYears(retirementAge).withDayOfMonth(localDob.plusYears(retirementAge).lengthOfMonth());
        }
        return convertToDate(retirementDate);
    }

    public static Date convertToDate(LocalDate localDateToConvert) {
        return Date.from(localDateToConvert.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static List<PayrollEmployeeDisability> getEmpDisabilityDtoToEntityList(List<EmpDisabilityReqDto> empDisabilityReqDtoList) {
        return empDisabilityReqDtoList.stream().map(EmpMstMapper.INSTANCE::toEmpDisability).collect(Collectors.toList());
    }
}
