package in.kpmg.hrms.payroll.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.FinanceHeadSaveDto;
import in.kpmg.hrms.payroll.dtos.FinanceHeadSaveInputDto;
import in.kpmg.hrms.payroll.dtos.GeneralMasterSaveDto;
import in.kpmg.hrms.payroll.dtos.GeneralMstInputDto;
import in.kpmg.hrms.payroll.services.MainServices;
import in.kpmg.hrms.payroll.services.MasterMappingService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {

	@Autowired
	private MainServices mainService;


	@Autowired
	private MasterMappingService masterMappingService;


	@PostMapping("/master-fetch")
	public ApiResponse2<?> getMasterDetails(@RequestBody GeneralMstInputDto inputDto){
		return mainService.getMasterBygType(inputDto);
	}
	
	@PostMapping("/getGeneralMstByParentId")
	public ApiResponse2<?> getGeneralMstByParentId(@RequestBody GeneralMstInputDto inputDto){
		return mainService.getGeneralMstByParentId(inputDto);
	}

	@PostMapping("/save-master-data")
	public ApiResponse2<?> saveMasterDetails(@RequestBody GeneralMasterSaveDto saveDto){
		return mainService.saveMasterTypesDetails(saveDto);
	}


	@GetMapping("/getIndependentdropdown")
	public ApiResponse2<?> getIndependentdropdown(){
		return  masterMappingService.getIndependentdropdown();
	}



	@GetMapping("/getSubHead/{minorId}")
	public ApiResponse2<?> getSubHead(@PathVariable (name = "minorId") Integer minorId ){
		return mainService.getSubHead(minorId);
	}



	@PostMapping("/save-finance-head-mst")
	public ApiResponse2<?> saveFinanceHeadMst(@RequestBody FinanceHeadSaveInputDto saveDto, Authentication authentication) {
		return mainService.saveFinanceHeadMst(saveDto, authentication);
	}

	@PostMapping("/finance-head-fetch")
	public ApiResponse2<?> fetchFinanceheadDetails(@RequestBody FinanceHeadSaveDto saveDto){

		return mainService.getFinanceMasterDetails(saveDto);
	}

	@GetMapping("/getPaybands/{pcId}")
	public ApiResponse2<?> paybands(@PathVariable (name = "pcId") Long pcId ){
		return mainService.paybands(pcId);
	}
	





}
