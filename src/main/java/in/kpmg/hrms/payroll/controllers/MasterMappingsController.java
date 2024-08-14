package in.kpmg.hrms.payroll.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.PayCommisionServTypeInputDto;
import in.kpmg.hrms.payroll.dtos.PayrollPayHeadDto;
import in.kpmg.hrms.payroll.dtos.ServiceTypeInputDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmployeeGroupCreationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.FinacialYearSearchDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.FyPaymonthSaveDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.GetEmployeeGroupDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.OfficeMasterDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayBandServiceTypeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadServiceTypeMappingDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayMatrixSaveDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.SaveHraRateDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.GroupCreationDto;
import in.kpmg.hrms.payroll.services.MasterMappingService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Validated
public class MasterMappingsController {
	
	@Autowired
	MasterMappingService masterMappingService;
	
	@PostMapping("/savePayheads")
	public ApiResponse2<?> savePayheads(@RequestBody @Valid PayrollPayHeadDto savePayheads){
		return this.masterMappingService.savePayheads(savePayheads);
		
	}
	
	@GetMapping("/getPayheads")
	public ApiResponse2<?> getPayheads(){
		return this.masterMappingService.getPayheads();
		
	}


	@PostMapping("/service-type-save")
	public ApiResponse2<?> saveServiceType(@RequestBody ServiceTypeInputDto serviceTypeInputDto){
		return masterMappingService.saveServiceTypeData(serviceTypeInputDto);
	}

	@GetMapping("/fetch-service-type")
	public ApiResponse2<?> fetchServiceTypeMaster(){
		return  masterMappingService.getServiceTypeMaster();
	}


	@PostMapping("/pay-commission-service-save")
	public ApiResponse2<?> savePayCommisionServiceType(@RequestBody PayCommisionServTypeInputDto commisionServTypeInputDto) throws Exception{
		return masterMappingService.savePayCommisionServiceType(commisionServTypeInputDto);
	}


	@GetMapping("/fetch-pay-commission-service")
	public ApiResponse2<?> fetchPayCommisionServiceType(){
		return  masterMappingService.getPayCommisionServiceType();
	}
	
	
	
	@PostMapping("/saveHraRates")
	public ApiResponse2<?> saveHraRates(@Valid @RequestBody SaveHraRateDto saveHraRateDto){
		return this.masterMappingService.saveHraRates(saveHraRateDto);
		
	}
	@GetMapping("/getHraRates")
	public ApiResponse2<?> getHraRates(){
		return this.masterMappingService.getHraRates();
		
	}
	
	@GetMapping("/getHraRateHistory")
	public ApiResponse2<?> getHraRateHistory(){
		return this.masterMappingService.getHraRateHistory();
		
	}
	
	@PostMapping("/savePayBandService")
	public ApiResponse2<?> savePayBandService(@Valid @RequestBody PayBandServiceTypeDto payBandServiceTypeDto){
		return masterMappingService.savePayBandService(payBandServiceTypeDto);
	}
	
	@GetMapping("/getPayBandService")
	public ApiResponse2<?> getPayBandService(){
		return masterMappingService.getPayBandService();
	}
	
	@GetMapping("/getPayCommisionByService/{sId}")
	public ApiResponse2<?> getPayCommisionByService(@PathVariable Integer sId){
		return masterMappingService.getPayCommisionByService(sId);
	}
	
	@GetMapping("/getDesgByDept/{deptId}")
	public ApiResponse2<?> getDesgnByDept(@PathVariable Integer deptId){
		return masterMappingService.getDesgnByDept(deptId);
	}
	
	@GetMapping("/getPayheadByPc/{pcId}")
	public ApiResponse2<?> getPayheadByPc(@PathVariable Long pcId){
		return masterMappingService.getPayheadByPc(pcId);
	}
	
	@PostMapping("/saveGroups")
	public ApiResponse2<?> saveGroups(@Valid @RequestBody GroupCreationDto groupCreationDto){
		return masterMappingService.saveGroups(groupCreationDto);
	}
	
	@GetMapping("/getGroups/{officeId}")
	public ApiResponse2<?> getGroups(@PathVariable Long officeId){
		return masterMappingService.getGroups(officeId);
	}
	
	@GetMapping("/getGroupsListByOffice/{officeId}")
	public ApiResponse2<?> getGroupsByOffice(@PathVariable Long officeId){
		return masterMappingService.getGroupsByOffice(officeId);
	}
	
	@GetMapping("/getHoaByOfficeId/{officeId}")
	public ApiResponse2<?> getHoaByOfficeId(@PathVariable(name = "officeId") Long officeId){
		return masterMappingService.getHoaByOfficeId(officeId);
	}
	
	@PostMapping("/saveEmpGrpMapping")
	public ApiResponse2<?> saveEmpGrpMapping(@Valid @RequestBody EmployeeGroupCreationDto empGroupCreationDto){
		return masterMappingService.saveEmpGrpMapping(empGroupCreationDto);
	}
	
	@PostMapping("/getEmpGrpMapping")
	public ApiResponse2<?> getEmpGrpMapping(@Valid @RequestBody GetEmployeeGroupDto empGroupCreationDto){
		return masterMappingService.getEmpGrpMapping(empGroupCreationDto);
	}
	
	
	@GetMapping("/getEmpCountByGrpId/{grpId}")
	public ApiResponse2<?> getEmpCountByGrpId(@PathVariable(name = "grpId") Long grpId){
		return masterMappingService.getEmpCountByGrpId(grpId);
	}
	
	@PostMapping("/saveFyPaymonth")
	public ApiResponse2<?> saveFyPaymonth(@Valid @RequestBody FyPaymonthSaveDto fyPaymonthSaveDto){
		return masterMappingService.saveFyPaymonth(fyPaymonthSaveDto);
	}
	
	@PostMapping("/getFyPaymonth")
	public ApiResponse2<?> getFyPaymonth(@RequestBody FinacialYearSearchDto finacialYearSearchDto){
		return masterMappingService.getFyPaymonth(finacialYearSearchDto.getFyId());
	}
	
	@PostMapping("/saveOfficeMaster")
	public ApiResponse2<?> saveOfficeMaster(@Valid @RequestBody OfficeMasterDto officeMasterDto){
		return masterMappingService.saveOfficeMaster(officeMasterDto);
	}
	

	@GetMapping("/getOfficeMaster")
	public ApiResponse2<?> getOfficeMaster(){
		return masterMappingService.getOfficeMaster();
	}
	
	@GetMapping("/getCurrentYearByFyId/{fyId}")
	public ApiResponse2<?> getCurrentYearByFyId(@PathVariable(name = "fyId") Long fyId){
		return masterMappingService.getCurrentYearByFyId(fyId);
	}
	
	@PostMapping("/savePayMatrix")
	public ApiResponse2<?> savePayMatrix(@Valid @RequestBody PayMatrixSaveDto payMatrixSaveDto){
		return masterMappingService.savePayMatrix(payMatrixSaveDto);
	}
	
	@GetMapping("/getPayMatrix")
	public ApiResponse2<?> getPayMatrix(){
		return masterMappingService.getPayMatrix();
	}
	
	@PostMapping("/getPayHeadServTypeMapping")
	public ApiResponse2<?> getPayHeadServTypeMapping(@RequestBody PayHeadServiceTypeMappingDto payHeadServiceTypeMappingDto){
		return masterMappingService.getPayHeadServTypeMapping(payHeadServiceTypeMappingDto.getServiceTypeId());
	}
	
	@PostMapping("/savePayHeadServTypeMapping")
	public ApiResponse2<?> savePayHeadServTypeMapping(@Valid @RequestBody PayHeadServiceTypeMappingDto payHeadServiceTypeMappingDto){
		return masterMappingService.savePayHeadServTypeMapping(payHeadServiceTypeMappingDto);
	}
	
	@GetMapping("/getPayHeadDropdown/{payHeadType}")
	public ApiResponse2<?> getPayHeadDropdown(@PathVariable("payHeadType") Long payHeadType){
		return masterMappingService.getPayHeadDropdown(payHeadType);
	}
	
	@GetMapping("/getRangeByPayLevel/{payBandId}")
	public ApiResponse2<?> getRangeByPayLevel(@PathVariable("payBandId") Long payBandId){
		return masterMappingService.getRangeByPayLevel(payBandId);
	}
	
	
	@GetMapping("/getPayBandsByServiceTypePc/{serTypePcId}")
	public ApiResponse2<?> getPayBandsByServiceTypePc(@PathVariable("serTypePcId") Integer serTypePcId){
		return masterMappingService.getPayBandsByServiceTypePc(serTypePcId);
	}
	
	@GetMapping("/getFyMonth")
	public ApiResponse2<?> getFyMonth(){
		return masterMappingService.getFyMonth();
	}
	
	
	
	
}
