package in.kpmg.hrms.payroll.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.constants.EmployeeConstants;
import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SearchEmployesDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EducationRequestDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpPayEntitlementOptionsReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeDocumentsReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.FamilyRequestDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.OfficeDropdownReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.PayEntitlementReqDto;
import in.kpmg.hrms.payroll.services.EmployeeServices;
import in.kpmg.hrms.payroll.validation.employeeReqDto.BankDetailsGroup;
import in.kpmg.hrms.payroll.validation.employeeReqDto.PersonalDetailsGroup;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @GetMapping("/dropdown-init")
    public ResponseEntity<?> getDropdownInit() {
        return this.employeeServices.dropdownInit();
    }

    @GetMapping("/dropdown/group/{officeId}")
    public ResponseEntity<?> getGroupByOffice(@PathVariable Long officeId) {
        return this.employeeServices.groupByOffice(officeId);
    }


    @GetMapping("/educational-details/dropdown-init")
    public ResponseEntity<?> getEducationalDetailsDropdownInit() {
        return this.employeeServices.educationalDtlsDropdownInit();
    }

    @GetMapping("/entitlement/dropdown-init")
    public ResponseEntity<?> getEntitlementDropdownInit() {
        return this.employeeServices.entitlementDropdownInit();
    }

    @PostMapping("/dropdown/office")
    public ResponseEntity<?> getState(@RequestBody @Validated OfficeDropdownReqDto officeDropdownReqDto) {
        return this.employeeServices.officeDropdown(officeDropdownReqDto);
    }

    @GetMapping("/dropdown/state")
    public ResponseEntity<?> getState() {
        return this.employeeServices.stateDropdownInit();
    }

    @GetMapping("/dropdown/district/{stateId}")
    public ResponseEntity<?> getDistrictByState(@PathVariable Long stateId) {
        return this.employeeServices.districtDropdownByState(stateId);
    }

    @GetMapping("/dropdown/designation/{deptId}")
    public ResponseEntity<?> getDesignationByDepartment(@PathVariable Integer deptId) {
        return this.employeeServices.designationByDepartmentDropdown(deptId);
    }

   @GetMapping("/dropdown/service-type")
   public ResponseEntity<?> getServiceTypeByEmployeeType() {
       return this.employeeServices.serviceTypeByEmployeeType();
   }

    @GetMapping("/dropdown/cadre/{serviceTypeId}")
    public ResponseEntity<?> getCadreByServiceType(@PathVariable Integer serviceTypeId) {
        return this.employeeServices.cadreByServiceType(serviceTypeId);
    }

    @GetMapping("/dropdown/pay-commission/{serviceTypeId}")
    public ResponseEntity<?> getPayCommission(@PathVariable Integer serviceTypeId) {
        return this.employeeServices.payCommissionServiceDropdown(serviceTypeId);
    }

    @GetMapping("/dropdown/pay-level/{pcServMappingTypeId}")
    public ResponseEntity<?> getPayLevelByPayCommission(@PathVariable Integer pcServMappingTypeId) {
        return this.employeeServices.payLevelByPayCommissionServiceDropdown(pcServMappingTypeId);
    }

//    @PostMapping("/dropdown/basic-pay")
//    public ResponseEntity<?> getBasicPayByPayLevel(@RequestBody @Validated BasicPayReqDto basicPayReqDto) {
//        return this.employeeServices.basicPayByPayLevel(basicPayReqDto);
//    }

    @GetMapping("/dropdown/basic-pay/{payBandMappingId}")
    public ResponseEntity<?> getBasicPayByPayLevelNew(@PathVariable Integer payBandMappingId) {
        return this.employeeServices.basicPayByPayLevelNew(payBandMappingId);
    }

    @GetMapping("/dropdown/hra-tier")
    public ResponseEntity<?> getHraTier() {
        return this.employeeServices.hraTierDropdown();
    }

    @GetMapping("/dropdown/cta-entitlement")
    public ResponseEntity<?> getCtaEntitlement() {
        return this.employeeServices.ctaEntitlementDropdown();
    }

    @GetMapping("/dropdown/document-types")
    public ResponseEntity<?> getDocumentTypes() {
        return this.employeeServices.documentTypeDropdown();
    }

//    @PostMapping("/save/{saveType}")
//    public ResponseEntity<?> saveEmployee(@RequestBody EmployeeReqDto employeeReqDto, @PathVariable String saveType , Authentication authentication) {
//        return this.employeeServices.saveEmployeeMaster(employeeReqDto, authentication, saveType);
//    }

    @PostMapping("/personal-details")
    public ResponseEntity<?> savePersonalDetails(
            @Validated(PersonalDetailsGroup.class) @RequestBody EmployeeReqDto employeeReqDto, Authentication authentication) {
        return this.employeeServices.saveEmployeeMaster(employeeReqDto, authentication, EmployeeConstants.PERSONAL_DETAILS);
    }

//    @PostMapping("/address-details")
//    public ResponseEntity<?> saveAddressDetails(
//            @Validated(AddressDetailsGroup.class) @RequestBody EmployeeReqDto employeeReqDto, Authentication authentication) {
//        return ResponseEntity.ok("Address details saved successfully.");
//    }

    @PostMapping("/bank-details")
    public ResponseEntity<?> saveBankDetails(
            @Validated(BankDetailsGroup.class) @RequestBody EmployeeReqDto employeeReqDto, Authentication authentication) {
        return this.employeeServices.saveEmployeeMaster(employeeReqDto, authentication, EmployeeConstants.BANK_DETAILS);
    }

    @PostMapping("/family-details")
    public ResponseEntity<?> saveFamilyDetails(@Validated @RequestBody FamilyRequestDto familyRequestDto, Authentication authentication) {
        return this.employeeServices.saveEmployeeFamilyDetails(familyRequestDto, authentication);
    }

    @PostMapping("/educational-details")
    public ResponseEntity<?> saveEducationalDetails(@Validated @RequestBody EducationRequestDto educationRequestDto, Authentication authentication) {
        return this.employeeServices.saveEducationalDetails(educationRequestDto, authentication);
    }

    @PostMapping("/entitlement-details/payment")
    public ResponseEntity<?> getEntitlementDetailsPayment(@Validated @RequestBody PayEntitlementReqDto payEntitlementReqDto) {
        return this.employeeServices.getPayEntitlementList(payEntitlementReqDto, EmployeeConstants.PAYHEAD_TYPE_PAYMENT);
    }

    @PostMapping("/entitlement-details/deduction")
    public ResponseEntity<?> getEntitlementDetailsDeduction(@Validated @RequestBody PayEntitlementReqDto payEntitlementReqDto) {
        return this.employeeServices.getPayEntitlementList(payEntitlementReqDto, EmployeeConstants.PAYHEAD_TYPE_DEDUCTION);
    }

    @PostMapping("/entitlement-details")
    public ResponseEntity<?> getEntitlementDetailsPaymentNew(@Validated @RequestBody PayEntitlementReqDto payEntitlementReqDto) {
        return this.employeeServices.getPayEntitlementListNew(payEntitlementReqDto);
    }

    @PostMapping("/entitlement-data")
    public ResponseEntity<?> getEntitlementEmployeeData(@RequestBody HashMap<String, String> req) {
        return this.employeeServices.getPayEntitlementEmployeeData(req);
    }

    @PostMapping("/pay-entitlement")
    public ResponseEntity<?> savePayEntitlement(@Validated @RequestBody EmpPayEntitlementOptionsReqDto empPayEntitlementOptionsReqDto, Authentication authentication) {
        return this.employeeServices.saveEntitlement(empPayEntitlementOptionsReqDto, authentication);
    }

    @PostMapping("/documents")
    public ResponseEntity<?> saveDocuments(@Validated @RequestBody EmployeeDocumentsReqDto employeeDocumentsReqDto, Authentication authentication) {
        return this.employeeServices.saveDocuments(employeeDocumentsReqDto, authentication);
    }

    @GetMapping("/personal-details/{refNo}")
    public ResponseEntity<?> getPersonalDetails(@PathVariable String refNo)
    {
        return this.employeeServices.getEmployeePersonalDetails(refNo);
    }

    @GetMapping("/bank-details/{refNo}")
    public ResponseEntity<?> getBankDetails(@PathVariable String refNo)
    {
        return this.employeeServices.getEmployeeBankDetails(refNo);
    }

    @GetMapping("/family-details/{refNo}")
    public ResponseEntity<?> getFamilyDetails(@PathVariable String refNo)
    {
        return this.employeeServices.getEmpFamilyDetails(refNo);
    }

    @GetMapping("/educational-details/{refNo}")
    public ResponseEntity<?> getEducationalDetails(@PathVariable String refNo)
    {
        return this.employeeServices.getEmpEducationalDetails(refNo);
    }

    @GetMapping("/documents/{refNo}")
    public ResponseEntity<?> getDocuments(@PathVariable String refNo)
    {
        return this.employeeServices.getEmpDocuments(refNo);
    }

    @GetMapping("/pay-entitlement/{refNo}")
    public ResponseEntity<?> getPayEntitlement(@PathVariable String refNo)
    {
        return this.employeeServices.getEmpPayEntitlementDetails(refNo);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getEmployeeList(Authentication authentication)
    {
        return this.employeeServices.getAllEmployeeListAdmin(authentication);
    }
    
    @GetMapping("/getEmpByGrpCode/{grpCode}")
  	public ApiResponse2<?> getEmpByGrpCode(@PathVariable (name = "grpCode") Long grpCode ){
  		return employeeServices.getEmpByGrpCode(grpCode);
  	}
    
    @PostMapping("/getEmpForReport")
  	public ApiResponse2<?> getEmpForReport( @RequestBody SearchEmployesDto searchEmployesDto ){
  		return employeeServices.getEmpForReport(searchEmployesDto);
  	}
    
    @GetMapping("/getEmpDetailsByEmpId/{empId}")
  	public ApiResponse2<?> getEmpDetailsByEmpId( @PathVariable Long empId){
  		return employeeServices.getEmpDetailsByEmpId(empId);
  	}

    
}
