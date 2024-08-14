package in.kpmg.hrms.payroll.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmployeeReportSearchDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.FyMonthStatusDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GetEmpDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GetGroupsDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GroupSalaryProcessDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SaveEmpSalaryBillDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.requestDto.RegularSalaryProcessReqDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.requestDto.SalarySlipReqDto;
import in.kpmg.hrms.payroll.services.SalaryBillService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SalaryBillController {

    @Autowired
    SalaryBillService salaryBillService;


    @PostMapping("/saveFyMonthHoa")
    public ApiResponse2<?> saveFyMonthHoa(@RequestBody @Valid FyMonthStatusDto fyMonthStatusDto) {
        return salaryBillService.saveFyMonthHoa(fyMonthStatusDto);

    }

    @PostMapping("/getFyMonthsByHoa")
    public ApiResponse2<?> getFyMonthsByHoa(@RequestBody FyMonthStatusDto fyMonthStatusDto) {
        return salaryBillService.getFyMonthsByHoa(fyMonthStatusDto);
    }


    @PostMapping("/getGroups")
    public ApiResponse2<?> getGrpWiseCounts(@Valid @RequestBody GetGroupsDto getGroupsDto) {
        return salaryBillService.getGrpWiseCounts(getGroupsDto);
    }


    @PostMapping("/saveSalaryBill")
    public ApiResponse2<?> saveSalaryBill(@RequestBody @Valid @NotEmpty(message = "saveEmpSalaryBillDto is mandatory") List<SaveEmpSalaryBillDto> saveEmpSalaryBillDto) {
        return salaryBillService.saveSalaryBill(saveEmpSalaryBillDto);
    }

    @PostMapping("/findEmpByGrpCode")
    public ApiResponse2<?> findEmpByGrpCode(@Valid @RequestBody GetEmpDto getEmp) {
        return salaryBillService.findEmpByGrpCode(getEmp);
    }

    @GetMapping("/salary/dropdown/financial-year")
    public ResponseEntity<?> fyDropdown() {
        return this.salaryBillService.financialYearDropdown();
    }

    @GetMapping("/salary/dropdown/financial-pay-month/{financialYearId}")
    public ResponseEntity<?> fyDropdown(@PathVariable Long financialYearId) {
        return this.salaryBillService.fyMonthDropdown(financialYearId);
    }

    @PostMapping("/salary/slip")
    public ResponseEntity<?> fetchSalarySlipDetails(@RequestBody @Validated SalarySlipReqDto salarySlipReqDto)
    {
        return this.salaryBillService.fetchSalaryBillDetails(salarySlipReqDto);
    }

    @GetMapping("/salary/structure/{empId}")
    public ResponseEntity<?> fetchSalaryStructureDetails(@PathVariable Long empId)
    {
        return this.salaryBillService.fetchEmployeePayEntitlementDetails(empId);
    }

    @PostMapping("/salary/calculate")
    public ResponseEntity<?> regularSalaryProcessing(@RequestBody @Validated RegularSalaryProcessReqDto regularSalaryProcessReqDto)
    {
        return this.salaryBillService.fetchRegularSalaryProcessing(regularSalaryProcessReqDto);
    }
    
    @PostMapping("/processGroupSalary")
    public ApiResponse2<?> processGroupSalary(@Valid @RequestBody GroupSalaryProcessDto groupSalaryProcessDto)
    {
        return salaryBillService.processGroupSalary(groupSalaryProcessDto);
    }
    
    @PostMapping("/getEmployeeReport")
    public ApiResponse2<?> getEmployeeReport(@Valid @RequestBody EmployeeReportSearchDto employeeReportSearchDto)
    {
        return salaryBillService.getEmployeeReport(employeeReportSearchDto);
    }
}
