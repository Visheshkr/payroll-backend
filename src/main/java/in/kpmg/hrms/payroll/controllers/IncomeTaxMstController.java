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
import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxDeclarationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxDeducHeadDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxSectionDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxSlabDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionDedHeadMappingDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionSchemeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayrollitSanctionLimitDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ProfessionalTaxDto;
import in.kpmg.hrms.payroll.services.IncomeTaxService;

@RestController
@Validated
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class IncomeTaxMstController {
	
	@Autowired
	IncomeTaxService incomeTaxService;
	
	
	@PostMapping("/saveITaxSection")
	public ApiResponse2<?> saveITaxSection(@Valid @RequestBody IncomeTaxSectionDto  saveDto){
		return incomeTaxService.saveITaxSection(saveDto);
	}
	
	
	@GetMapping("/getITaxSections")
	public ApiResponse2<?> getITaxSection(){
		return incomeTaxService.getITaxSection();
	}
	
	@PostMapping("/saveITaxSlab")
	public ApiResponse2<?> saveITaxSlab(@Valid @RequestBody IncomeTaxSlabDto  saveDto){
		return incomeTaxService.saveITaxSlab(saveDto);
	}
	
	@GetMapping("/getITaxSlab")
	public ApiResponse2<?> getITaxSlab(){
		return incomeTaxService.getITaxSlab();
	}
	
	@PostMapping("/saveITaxDeducHead")
	public ApiResponse2<?> saveITaxDeducHead(@Valid @RequestBody IncomeTaxDeducHeadDto  saveDto){
		return incomeTaxService.saveITaxDeducHead(saveDto);
	}
	
	@PostMapping("/getITaxDeducHead")
	public ApiResponse2<?> getITaxDeducHead(@RequestBody IncomeTaxDeducHeadDto fetchDto ){
		return incomeTaxService.getITaxDeducHead(fetchDto);
	}
	
	@PostMapping("/saveITSanctionLimit")
	public ApiResponse2<?> saveITSanctionLimit(@Valid @RequestBody PayrollitSanctionLimitDto  saveDto){
		return incomeTaxService.saveITSanctionLimit(saveDto);
	}
	
	@GetMapping("/getITSanctionLimit")
	public ApiResponse2<?> getITSanctionLimit(){
		return incomeTaxService.getITSanctionLimit();
	}
	
	@PostMapping("/saveItSectionDedHeadMapping")
	public ApiResponse2<?> saveItSectionDedHeadMapping(@Valid @RequestBody ItSectionDedHeadMappingDto  saveDto){
		return incomeTaxService.saveItSectionDedHeadMapping(saveDto);
	}
	
	@GetMapping("/getItSectionDedHeadMapping")
	public ApiResponse2<?> getItSectionDedHeadMapping(){
		return incomeTaxService.getItSectionDedHeadMapping();
	}
	
	@PostMapping("/saveITDeclaration")
	public ApiResponse2<?> saveITaxDeclaration(@Valid @RequestBody IncomeTaxDeclarationDto  saveDto){
		return incomeTaxService.saveITaxDeclaration(saveDto);
	}
	
	@GetMapping("/getITDeclaration")
	public ApiResponse2<?> getITaxDeclaration(){
		return incomeTaxService.getITaxDeclaration();
	}
	
	@PostMapping("/saveProfessionalTax")
	public ApiResponse2<?> savePfTax(@Valid @RequestBody ProfessionalTaxDto  saveDto){
		return incomeTaxService.savePfTax(saveDto);
	}
	
	@GetMapping("/getProfessionalTax")
	public ApiResponse2<?> getPfTax(){
		return incomeTaxService.getPfTax();
	}
	
	@GetMapping("/getITSectionScheme")
	public ApiResponse2<?> getITSectionScheme(){
		return incomeTaxService.getITSectionScheme();
	}
	
	@PostMapping("/saveITSectionScheme")
	public ApiResponse2<?> saveITSectionScheme(@Valid @RequestBody ItSectionSchemeDto itSectionSchemeDto){
		return incomeTaxService.saveITSectionScheme(itSectionSchemeDto);
	}

	@GetMapping("/getITSectionSchemeDropdown")
	public ApiResponse2<?> getITSectionSchemeDropdown(){
		return incomeTaxService.getITSectionSchemeDropdown();
	}
	
	@GetMapping("/getItDecWindow/{empId}")
	public ApiResponse2<?> getItDecWindow(@PathVariable Long empId){
		return incomeTaxService.getItDecWindow(empId);
	}
	
	@GetMapping("/getItSectionsByRegimeType/{regType}")
	public ApiResponse2<?> getItSectionsByRegimeType(@PathVariable Long regType){
		return incomeTaxService.getItSectionsByRegimeType(regType);
	}
	
	@GetMapping("/getSchemeBySection/{sectionId}")
	public ApiResponse2<?> getSchemeBySection(@PathVariable Integer sectionId){
		return incomeTaxService.getSchemeBySection(sectionId);
	}
	
	@PostMapping("/saveItDeclaration")
	public ApiResponse2<?> saveItDeclaration(@Valid @RequestBody EmpItDeclarationDto empItDeclarationDto){
		return incomeTaxService.saveItDeclaration(empItDeclarationDto);
	}
}
