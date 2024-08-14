package in.kpmg.hrms.payroll.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadConfigDto;
import in.kpmg.hrms.payroll.services.MasterConfigService;

@RestController
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MasterConfigController {
	
	@Autowired
	MasterConfigService masterConfigService;
	
	
	@PostMapping("/savePayHeadConfig")
	public ApiResponse2<?> savePayHeadConfig(@Valid @RequestBody PayHeadConfigDto payHeadConfigDto){
		return masterConfigService.savePayHeadConfig(payHeadConfigDto);
	}
	
	@PostMapping("/getPayHeadConfig")
	public ApiResponse2<?> getPayHeadConfig(@RequestBody PayHeadConfigDto payHeadConfigDto ){
		return masterConfigService.getPayHeadConfig(payHeadConfigDto);
	}

}
