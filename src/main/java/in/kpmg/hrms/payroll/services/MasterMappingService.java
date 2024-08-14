package in.kpmg.hrms.payroll.services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import in.kpmg.hrms.payroll.constants.GlobalEnumConstants.FinanceheadTypeMst;
import in.kpmg.hrms.payroll.constants.GlobalEnumConstants.GeneralTypeMst;
import in.kpmg.hrms.payroll.constants.GlobalEnumConstants.PayHeadType;
import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.PayCommisionServTypeInputDto;
import in.kpmg.hrms.payroll.dtos.PayrollPayHeadDto;
import in.kpmg.hrms.payroll.dtos.ServiceTypeInputDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmployeeGroupCreationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.FyPaymonthSaveDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.GetEmployeeGroupDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.OfficeMasterDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayBandServiceTypeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadServiceTypeMappingDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayLevelRangeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayMatrixSaveDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.SaveHraRateDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.GroupCreationDto;
import in.kpmg.hrms.payroll.dtos.responseDto.DropdownDataDto;
import in.kpmg.hrms.payroll.dtos.responseDto.EmpGroupCreationDto;
import in.kpmg.hrms.payroll.exceptions.NoRecordFoundException;
import in.kpmg.hrms.payroll.mapper.HraRateMapper;
import in.kpmg.hrms.payroll.mapper.PayCommissionServiceTypeMapper;
import in.kpmg.hrms.payroll.mapper.PayHeadServTypeMappingMapper;
import in.kpmg.hrms.payroll.mapper.PayMatrixMapper;
import in.kpmg.hrms.payroll.mapper.PayheadMapper;
import in.kpmg.hrms.payroll.mapper.PayrollBandServiceTypeMapper;
import in.kpmg.hrms.payroll.mapper.PayrollFyPaymonthMstMapper;
import in.kpmg.hrms.payroll.mapper.PayrollGroupCreationMapper;
import in.kpmg.hrms.payroll.mapper.PayrollOfficeMasterMapper;
import in.kpmg.hrms.payroll.mapper.ServiceTypeMstMapper;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollBandServiceTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadmst;
import in.kpmg.hrms.payroll.models.PayrollFyPaymonthMst;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;
import in.kpmg.hrms.payroll.models.PayrollHRARateMstAudit;
import in.kpmg.hrms.payroll.models.PayrollHoaMaster;
import in.kpmg.hrms.payroll.models.PayrollOfficeMaster;
import in.kpmg.hrms.payroll.models.PayrollPayCommisionServTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollPayHeadMaster;
import in.kpmg.hrms.payroll.models.PayrollPayHeadServiceTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollPayMatrix;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;
import in.kpmg.hrms.payroll.repo.DepartmentRepo;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.IncomeTaxDeductionHeadRepo;
import in.kpmg.hrms.payroll.repo.IncomeTaxSectionRepo;
import in.kpmg.hrms.payroll.repo.PayMatrixRepo;
import in.kpmg.hrms.payroll.repo.PayheadPcMappingRepo;
import in.kpmg.hrms.payroll.repo.PayrollBandServiceTypeMappingRepo;
import in.kpmg.hrms.payroll.repo.PayrollDesignationDeptRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmployeeMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollFinanceHeadTypeMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollFinanceHeadmstRepo;
import in.kpmg.hrms.payroll.repo.PayrollFyPaymonthMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollGroupMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollHoaMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollHoaOfficeRepo;
import in.kpmg.hrms.payroll.repo.PayrollHraRateAuditRepo;
import in.kpmg.hrms.payroll.repo.PayrollHraRateRepo;
import in.kpmg.hrms.payroll.repo.PayrollOfficeMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayCommisionServTypeRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayHeadServiceTypeMappingRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayheadMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollPaymonthMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollServiceTypeRepo;
import in.kpmg.hrms.payroll.repo.UserRepo;

@Service
public class MasterMappingService {

	@Autowired
	private GeneralMstRepo generalMstRepo;



	@Autowired
	ModelMapper modelMapper;

	@Autowired
	HraRateMapper hraRateMapper;

	@Autowired
	PayrollHraRateRepo payrollHraRateRepo;

	@Autowired
	PayheadMapper pHeadmapper;

	@Autowired
	private PayrollServiceTypeRepo serviceTypeRepo;


	@Autowired
	PayrollPayheadMstRepo payrollPayheadMstRepo;

	@Autowired
	PayrollFinanceHeadmstRepo payrollFinanceHeadmstRepo;

	@Autowired
	Validator validator;

	@Autowired
	PayrollPaymonthMstRepo payrollPaymonthMstRepo;

	@Autowired
	PayrollFinanceHeadTypeMstRepo payrollFinanceHeadTypeMstRepo;

	@Autowired
	PayCommissionServiceTypeMapper payCommissionServiceTypeMapper;

	@Autowired
	PayrollBandServiceTypeMappingRepo payrollBandServiceTypeMappingRepo;

	@Autowired
	PayrollBandServiceTypeMapper payrollBandServiceTypeMapper;

	@Autowired
	ServiceTypeMstMapper serviceTypeMstMapper;


	@Autowired
	PayrollDesignationDeptRepo payrollDesignationDeptRepo;

	@Autowired
	PayrollHraRateAuditRepo payrollHraRateAuditRepo;

	@Autowired
	IncomeTaxSectionRepo incomeTaxSectionRepo;


	@Autowired
	PayheadPcMappingRepo payheadPcMappingRepo;


	@Autowired
	DepartmentRepo departmentRepo;

	@Autowired
	PayrollGroupMasterRepo payrollGroupMasterRepo;

	@Autowired
	PayrollHoaMasterRepo payrollHoaMasterRepo;

	@Autowired
	PayrollOfficeMasterRepo payrollOfficeMasterRepo;

	@Autowired
	PayrollGroupCreationMapper payrollGroupCreationMapper;

	@Autowired
	PayrollHoaOfficeRepo payrollHoaOfficeRepo;

	@Autowired
	PayrollEmployeeMasterRepo payrollEmployeeMasterRepo;

	@Autowired
	IncomeTaxDeductionHeadRepo deductionHeadrepo;

	@Autowired
	PayrollFyPaymonthMstMapper payrollFyPaymonthMstMapper;

	@Autowired
	private PayrollFyPaymonthMstRepo payrollFyPaymonthMstRepo;

	@Autowired
	private PayrollOfficeMasterMapper payrollOfficeMasterMapper;

	@Autowired
	private PayMatrixRepo payMatrixRepo;

	@Autowired
	private UserRepo userMstRepo;

	@Autowired
	private PayMatrixMapper payMatrixMapper;

	@Autowired
	private PayrollPayCommisionServTypeRepo payrollPayCommisionServTypeMappingRepo;

	@Autowired
	private PayHeadServTypeMappingMapper payHeadServTypeMappingMapper;
	
	@Autowired
	private PayrollPayHeadServiceTypeMappingRepo payrollPayHeadServiceTypeMappingRepo;

	public interface NonPayment{
	}
	public interface Assign{
	}

	public interface Operational{
	}
	public interface NonOperational{
	}


	@Autowired
	private PayrollPayCommisionServTypeRepo payCommisionServTypeRepo;


	public ApiResponse2<Object> sendErrors(Errors errors) {
		List<String> errorString= new ArrayList<>();
		for(FieldError  fieldError : errors.getFieldErrors()) {
			errorString.add(fieldError.getDefaultMessage());

		}
		return new ApiResponse2<>(false, ApiResponseStatus.validationErrors,errorString, HttpStatus.BAD_REQUEST.value());

	}

	@Transactional
	public ApiResponse2<?> saveServiceTypeData(ServiceTypeInputDto serviceTypeInputDto) {

		try {
			//  UPDATE
			if (serviceTypeInputDto.getId() != null) {
				Optional<PayrollServiceTypeMaster> existingTypeMaster = serviceTypeRepo.findById(serviceTypeInputDto.getId());
				if (existingTypeMaster.isPresent()) {				
					serviceTypeInputDto.setServiceType(serviceTypeInputDto.getServiceType().toUpperCase());
					PayrollServiceTypeMaster typeMater = existingTypeMaster.get();
					typeMater = serviceTypeMstMapper.dtoToEntity(serviceTypeInputDto);
					serviceTypeRepo.save(typeMater);
					return new ApiResponse2<>(true, ApiResponseStatus.updated, typeMater, HttpStatus.OK.value());
				}

			}
			Optional<PayrollServiceTypeMaster> existingTypeMaster = serviceTypeRepo.findByServiceType(serviceTypeInputDto.getServiceType().toUpperCase());
			if (existingTypeMaster.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.existing, null, HttpStatus.OK.value());
			}

			serviceTypeInputDto.setServiceType(serviceTypeInputDto.getServiceType().toUpperCase());
			PayrollServiceTypeMaster typeMater = serviceTypeMstMapper.dtoToEntity(serviceTypeInputDto);
			serviceTypeRepo.save(typeMater);
			return new ApiResponse2<>(true, ApiResponseStatus.saved,typeMater, HttpStatus.OK.value());



		} catch (Exception e) {
			e.printStackTrace();
			return new ApiResponse2<>(false, ApiResponseStatus.exception, "", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}




	}

	public ApiResponse2<?> getServiceTypeMaster() {

		List<PayrollServiceTypeMaster> serviceTypeMasters= serviceTypeRepo.findAllByOrderByCreatedOnDesc();
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, serviceTypeMasters, HttpStatus.OK.value());
	}


	@Transactional
	public ApiResponse2<?> savePayCommisionServiceType(PayCommisionServTypeInputDto commisionServTypeInputDto) throws Exception {


		if (commisionServTypeInputDto.getId() != null){
			Optional<PayrollPayCommisionServTypeMapping> typeExists= payCommisionServTypeRepo.findById(commisionServTypeInputDto.getId());
			if (typeExists.isPresent()){

				PayrollPayCommisionServTypeMapping existingData= payCommissionServiceTypeMapper.dtoToEntity(commisionServTypeInputDto);
				existingData.setId(commisionServTypeInputDto.getId());
				Optional<PayrollServiceTypeMaster> service = serviceTypeRepo.findById(commisionServTypeInputDto.getServTypeId());
				if(!service.isPresent()) {
					throw new NoRecordFoundException("Service type not found");
				}
				existingData.setServTypeId(service.get());
				Optional<GeneralMst> pc = generalMstRepo.findById(commisionServTypeInputDto.getPcId());
				if(!pc.isPresent()) {
					throw new NoRecordFoundException("pay commision not found");
				}
				existingData.setPcId(pc.get());
				payCommisionServTypeRepo.save(existingData);
				return new ApiResponse2<>(true, ApiResponseStatus.updated, existingData, HttpStatus.OK.value());
			}
		}
		Optional<GeneralMst> existingType = generalMstRepo.findById(commisionServTypeInputDto.getPcId());
		if (existingType.isPresent()){
			Optional<PayrollPayCommisionServTypeMapping> isExistingData= payCommisionServTypeRepo.findByServTypeId_IdAndPcId_TypeId(commisionServTypeInputDto.getServTypeId(), commisionServTypeInputDto.getPcId());
			if (isExistingData.isPresent()){
				return new ApiResponse2<>(false,ApiResponseStatus.existing , "", HttpStatus.OK.value());
			}
			PayrollPayCommisionServTypeMapping typeMapping= payCommissionServiceTypeMapper.dtoToEntity(commisionServTypeInputDto);
			Optional<PayrollServiceTypeMaster> service = serviceTypeRepo.findById(commisionServTypeInputDto.getServTypeId());
			if(!service.isPresent()) {
				throw new NoRecordFoundException("Service type not found");
			}
			typeMapping.setServTypeId(service.get());
			Optional<GeneralMst> pc = generalMstRepo.findById(commisionServTypeInputDto.getPcId());
			if(!pc.isPresent()) {
				throw new NoRecordFoundException("pay commision not found");
			}
			typeMapping.setPcId(pc.get());
			payCommisionServTypeRepo.save(typeMapping);
			return new ApiResponse2<>(true, ApiResponseStatus.saved,typeMapping , HttpStatus.OK.value());
		}
		else {
			throw new NoRecordFoundException();
		}

	}

	public ApiResponse2<?> getPayCommisionServiceType() {

		List<PayrollPayCommisionServTypeMapping> fetchData = payCommisionServTypeRepo.findByIsActiveTrueOrderByCreatedOnDesc();
		return new ApiResponse2<>(true, "Pay Commission Service Type Fetch", fetchData, HttpStatus.OK.value());


	}


	public ApiResponse2<Object> savePayheads(@Valid PayrollPayHeadDto savePayheads) {
		Errors errors = new BeanPropertyBindingResult(savePayheads, "savePayheads");
		ValidationUtils.invokeValidator( validator, savePayheads, errors);
		if(savePayheads.getPayHeadType()== PayHeadType.Recovery.getCode() || savePayheads.getPayHeadType()== PayHeadType.Deduction.getCode() ) {
			validatePayload(savePayheads, errors);
		}
		if(errors.hasFieldErrors()) {
			return sendErrors(errors);
		}
		PayrollPayHeadMaster payhead = pHeadmapper.payHeadDtoToModel(savePayheads);

		if(savePayheads.getPayheadGrpId()!= null) {
			Optional<GeneralMst> existingPayHeadGrpId = generalMstRepo.findById(savePayheads.getPayheadGrpId());
			if(existingPayHeadGrpId.isPresent())
				payhead.setPayheadGrpId(existingPayHeadGrpId.get());
		}
		
		if(savePayheads.getPayHeadType()!= null) {
			Optional<GeneralMst> existingPayHeadType = generalMstRepo.findById(savePayheads.getPayHeadType());
			if(existingPayHeadType.isPresent())
				payhead.setPayHeadType(existingPayHeadType.get());
		}
		
		if(savePayheads.getDetailsHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingDetHead = payrollFinanceHeadmstRepo.findById(savePayheads.getDetailsHead());
			if(existingDetHead.isPresent())
				payhead.setDetailsHead(existingDetHead.get());
		}
		
		if(savePayheads.getSubDetailsHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingSubDetHEad = payrollFinanceHeadmstRepo.findById(savePayheads.getSubDetailsHead());
			if(existingSubDetHEad.isPresent())
				payhead.setSubDetailsHead(existingSubDetHEad.get());
		}
		
		if(savePayheads.getMajorHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingMajHead = payrollFinanceHeadmstRepo.findById(savePayheads.getMajorHead());
			if(existingMajHead.isPresent())
				payhead.setMajorHead(existingMajHead.get());
		}
		
		if(savePayheads.getSubMajorHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingSubMajHead = payrollFinanceHeadmstRepo.findById(savePayheads.getSubMajorHead());
			if(existingSubMajHead.isPresent())
				payhead.setSubMajorHead(existingSubMajHead.get());
		}
		
		if(savePayheads.getMinorHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingMinHead = payrollFinanceHeadmstRepo.findById(savePayheads.getMinorHead());
			if(existingMinHead.isPresent())
				payhead.setMinorHead(existingMinHead.get());
		}
		
		if(savePayheads.getSubHead()!= null) {
			Optional<PayrollFinanceHeadmst> existingSubHead = payrollFinanceHeadmstRepo.findById(savePayheads.getSubHead());
			if(existingSubHead.isPresent())
				payhead.setSubHead(existingSubHead.get());
		}
		
		if(savePayheads.getPayheadId() != null) {
			Optional<PayrollPayHeadMaster> existingPayHead = payrollPayheadMstRepo.findById(savePayheads.getPayheadId());
			if(existingPayHead.isPresent()) {			
				payhead.setPayheadId(savePayheads.getPayheadId());			
				return new ApiResponse2<>(true, ApiResponseStatus.updated, payrollPayheadMstRepo.save(payhead), HttpStatus.OK.value()); 				
			}
			else {
				throw new NoRecordFoundException();
			}
		}
		else {
			PayrollPayHeadMaster ph = payrollPayheadMstRepo.findByPayheadCode(savePayheads.getPayheadCode());
			if(ph != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existingCode + savePayheads.getPayheadCode() , null, HttpStatus.OK.value()); 				
			}
		}

		return new ApiResponse2<>(true, ApiResponseStatus.saved, payrollPayheadMstRepo.save(payhead), HttpStatus.OK.value()); 

	}

	private void validatePayload(@Valid PayrollPayHeadDto savePayheads, Errors errors) {
		ValidationUtils.invokeValidator(validator,savePayheads, errors,NonPayment.class);

	}

	public ApiResponse2<?> getIndependentdropdown() {
		HashMap<String, Object> response = new HashMap<>();

		response.put("PayHeadType", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.payHeadMst.getCode(), true) );
		response.put("financeHead", payrollFinanceHeadTypeMstRepo.findAll()  );
		response.put("financeParentHead", payrollFinanceHeadmstRepo.findParentHeads()  );
		response.put("groupTypeId", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.GroupTypeId.getCode(), true));	
		response.put("major-submajor-minor", payrollFinanceHeadmstRepo.findByMajorSubMajorMinor());
		response.put("major", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.Major.getCode(), true));
		response.put("sub-major", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.SubMajor.getCode(), true));
		response.put("minor", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.Minor.getCode(), true));
		response.put("detail", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.Detail.getCode(), true));
		response.put("sub-detail", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.SubDetail.getCode(), true));
		response.put("sub-head", payrollFinanceHeadmstRepo.findByFtypeIdAndIsActive(FinanceheadTypeMst.SubHead.getCode(), true));
		response.put("detail-subdetail", payrollFinanceHeadmstRepo.findByDetailAndSubDetail());	
		response.put("btDesc", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.BtDesc.getCode(), true));
		response.put("groupType", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.GroupType.getCode(), true));
		response.put("payCommission", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.payCommissionMst.getCode(), true));
		response.put("CtaEntitlement", generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.ctaEntitlement.getCode(), true));
		response.put("serviceType", serviceTypeRepo.findActiveServiceTypes());
		response.put("financialYear",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.FinancialYear.getCode(), true));
		response.put("regimeType",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.taxRegimeType.getCode(), true));
		response.put("taxSubCategory",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.taxSubCategory.getCode(), true));
		response.put("gender",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.gender.getCode(), true));
		response.put("employeeType",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.employeeType.getCode(), true));
		response.put("taxEffect",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.taxEffect.getCode(), true));
		response.put("payHeadEffect",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.payHeadEffect.getCode(), true));
		response.put("status",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.Status.getCode(), true));
		response.put("departments",departmentRepo.findAllByIsActiveIsTrue());
		response.put("payHeads",payrollPayheadMstRepo.findAllActivePayHeads());
		response.put("sections",incomeTaxSectionRepo.findAllActiveSections());
		response.put("groupCodes",generalMstRepo.findByGeneralTypeIdAndIsActive(GeneralTypeMst.groupCodes.getCode(), true));
		response.put("dedcutionHead",deductionHeadrepo.findDedcutionHead());
		response.put("offices",payrollOfficeMasterRepo.findActiveOffice());
		response.put("tiers",payrollHraRateRepo.findAllActiveTiers());
		response.put("months",payrollPaymonthMstRepo.findAllActiveMonths());

		return new ApiResponse2<>(true, ApiResponseStatus.fetch, response, HttpStatus.OK.value()); 
	}

	public ApiResponse2<?> getPayheads() {

		List<PayrollPayHeadMaster> payHeads = payrollPayheadMstRepo.findAllByOrderByCrtOnDesc();
		if(payHeads.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.fetch, null, HttpStatus.OK.value()); 
		}
		List<PayrollPayHeadDto> payHeadResponse = pHeadmapper.payHeadModelToDto(payHeads);

		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payHeadResponse, HttpStatus.OK.value());  
	}


	public ApiResponse2<?> saveHraRates(SaveHraRateDto saveHraRateDto) {

		if(saveHraRateDto.getTierId()!= null) {
			Optional<PayrollHRARateMst> existedHra = payrollHraRateRepo.findById(saveHraRateDto.getTierId());
			if(existedHra.isPresent()) {
				modelMapper.map( saveHraRateDto, existedHra.get());
				payrollHraRateRepo.save(existedHra.get());
				return new ApiResponse2<>(true, ApiResponseStatus.updated, existedHra, HttpStatus.OK.value());  


			}
		}
		PayrollHRARateMst hra = hraRateMapper.dtoToHramSt(saveHraRateDto);
		payrollHraRateRepo.save(hra);
		return new ApiResponse2<>(true, ApiResponseStatus.saved, hra, HttpStatus.OK.value());  

	}

	public ApiResponse2<?> getHraRates() {

		List<PayrollHRARateMst> hraRates = payrollHraRateRepo.findAllByOrderByCreatedOnDesc();
		if(hraRates.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, hraRateMapper.HraRatesToDtos(hraRates), HttpStatus.OK.value());
	}

	public ApiResponse2<?> getHraRateHistory() {
		List<PayrollHRARateMstAudit>hraAudit = payrollHraRateAuditRepo.findAll();
		if(hraAudit.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, hraRateMapper.HraRatesAuditToDtos(hraAudit), HttpStatus.OK.value());
	}

	public ApiResponse2<?> savePayBandService(@Valid PayBandServiceTypeDto payBandServiceTypeDto) {
		if(payBandServiceTypeDto.getId() != null) {
			Optional<PayrollBandServiceTypeMapping> existingPaybandService = payrollBandServiceTypeMappingRepo.findById(payBandServiceTypeDto.getId());
			if(existingPaybandService.isPresent()) {
				PayrollBandServiceTypeMapping paybandService = payrollBandServiceTypeMapper.dtoToPayrollBandServiceMst(payBandServiceTypeDto);
				if(payBandServiceTypeDto.getPayBandId() != null) {
					Optional<GeneralMst> existingPayBand = generalMstRepo.findById(payBandServiceTypeDto.getPayBandId());
					if(existingPayBand.isPresent()) {
						paybandService.setPayBandId(existingPayBand.get());							
					}
				}
				if(payBandServiceTypeDto.getPcServTypeId()!= null) {
					Optional<PayrollPayCommisionServTypeMapping> exsitingPcSerTypeId = payCommisionServTypeRepo.findById(payBandServiceTypeDto.getPcServTypeId());
					if(exsitingPcSerTypeId.isPresent()) {
						paybandService.setPcServTypeId(exsitingPcSerTypeId.get());						
					}
				}
				paybandService.setId(existingPaybandService.get().getId());		
				payrollBandServiceTypeMappingRepo.save(paybandService);
				return new ApiResponse2<>(true, ApiResponseStatus.updated, existingPaybandService.get(), HttpStatus.OK.value());  
			}
			else {
				return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
			}
		}
		else {
			if(payrollBandServiceTypeMappingRepo.findByPayBandIdAndPcServTypeId(payBandServiceTypeDto.getPayBandId(), payBandServiceTypeDto.getPcServTypeId())!= null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existing, null, HttpStatus.OK.value());  
			}
			PayrollBandServiceTypeMapping paybandService = payrollBandServiceTypeMapper.dtoToPayrollBandServiceMst(payBandServiceTypeDto);
			if(payBandServiceTypeDto.getPayBandId() != null) {
				Optional<GeneralMst> existingPayBand = generalMstRepo.findById(payBandServiceTypeDto.getPayBandId());
				if(existingPayBand.isPresent()) {
					paybandService.setPayBandId(existingPayBand.get());							
				}	
			}
			if(payBandServiceTypeDto.getPcServTypeId()!= null) {
				Optional<PayrollPayCommisionServTypeMapping> exsitingPcSerTypeId = payCommisionServTypeRepo.findById(payBandServiceTypeDto.getPcServTypeId());
				if(exsitingPcSerTypeId.isPresent()) {
					paybandService.setPcServTypeId(exsitingPcSerTypeId.get());						
				}
			}
			payrollBandServiceTypeMappingRepo.save(paybandService);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, paybandService, HttpStatus.OK.value());
		}
		
	}

	public ApiResponse2<?> getPayBandService() {

		List<PayrollBandServiceTypeMapping> payBandsServices = payrollBandServiceTypeMappingRepo.findAllByOrderByCreatedOnDesc();
		if(payBandsServices.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payrollBandServiceTypeMapper.mstToPayBandServiceTypeDtos(payBandsServices), HttpStatus.OK.value());
	}

	public ApiResponse2<?> getPayCommisionByService(Integer sId) {
		List<DropdownResponse> payCommisions = payCommisionServTypeRepo.findByServTypeIdAndIsActive(sId, true);
		if(payCommisions.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payCommisions, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getDesgnByDept(Integer deptId) {
		List<DropdownResponse> designations = payrollDesignationDeptRepo.findByDeptIdAndisActive(deptId, true);
		if(designations.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, designations, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getPayheadByPc(Long pcId) {
		List<DropdownResponse> componsetionPayheads = payheadPcMappingRepo.findByPcIdAndIsActive(pcId, true);
		if(componsetionPayheads.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, componsetionPayheads, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveGroups(@Valid GroupCreationDto groupCreationDto) {
		Optional<PayrollHoaMaster> hoa = payrollHoaMasterRepo.findById(groupCreationDto.getHoaId());
		if(!hoa.isPresent()) {
			return new ApiResponse2<>(false, ApiResponseStatus.nohoa, null, HttpStatus.OK.value());
		}
		Optional<PayrollOfficeMaster> office = payrollOfficeMasterRepo.findById(groupCreationDto.getOfficeId());
		if(!office.isPresent()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noOffice, null, HttpStatus.OK.value());
		}
		if(groupCreationDto.getGrpId() != null) {
			if(payrollGroupMasterRepo.findById(groupCreationDto.getGrpId()).isPresent()) {
				PayrollGroupMaster group = payrollGroupCreationMapper.dtoToGroupMst(groupCreationDto);
				group.setHoaId(hoa.get());
				group.setOfficeId(office.get());
				payrollGroupMasterRepo.save(group);	
				return new ApiResponse2<>(true, ApiResponseStatus.updated, payrollGroupCreationMapper.groupMstToDto(group), HttpStatus.OK.value());  
			}
			else {
				return new ApiResponse2<>(false, ApiResponseStatus.NotExstedgroup, null, HttpStatus.OK.value()); 
			}
		}
		else {
			if(payrollGroupMasterRepo.findByGrpCode(groupCreationDto.getGrpCode()) != null ) {
				return new ApiResponse2<>(false, ApiResponseStatus.groupsExisted, null, HttpStatus.OK.value()); 
			}
			PayrollGroupMaster group = payrollGroupCreationMapper.dtoToGroupMst(groupCreationDto);
			group.setHoaId(hoa.get());
			group.setOfficeId(office.get());
			payrollGroupMasterRepo.save(group);	
			return new ApiResponse2<>(true, ApiResponseStatus.saved ,payrollGroupCreationMapper.groupMstToDto(group) , HttpStatus.OK.value());
		}	
	}

	public ApiResponse2<?> getGroups(Long officeId) {
		List<PayrollGroupMaster> groups = payrollGroupMasterRepo.findByOfficeId(officeId);
		if(groups.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payrollGroupCreationMapper.groupMstToDtos(groups), HttpStatus.OK.value());
	}

	public ApiResponse2<?> getGroupsByOffice(Long officeId) {
		List<DropdownDataDto> groups = payrollGroupMasterRepo.findByOfficeIdAndIsActive(officeId, true);
		if(groups.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, groups, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getHoaByOfficeId(Long officeId) {
		List<DropdownDataDto> hoaLists = payrollHoaOfficeRepo.getHoaLists(officeId);
		if(hoaLists.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch,hoaLists , HttpStatus.OK.value());

	}

	public ApiResponse2<?> getEmpGrpMapping(GetEmployeeGroupDto empGroupCreationDto) {
		List<EmpGroupCreationDto> employees = payrollEmployeeMasterRepo.findEmployee(empGroupCreationDto.getEmpId(),
				empGroupCreationDto.getGrpId() , 
				empGroupCreationDto.getServiceType(), empGroupCreationDto.getDsgnId());
		if(employees.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch,employees , HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveEmpGrpMapping(@Valid EmployeeGroupCreationDto empGroupCreationDto) {
		Errors errors = new BeanPropertyBindingResult(empGroupCreationDto, "empGroupCreationDto");
		ValidationUtils.invokeValidator( validator, empGroupCreationDto, errors);
		Optional<PayrollEmployeeMaster> emp = payrollEmployeeMasterRepo.findById(empGroupCreationDto.getEmpId());
		if(!emp.isPresent()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noEmp, null, HttpStatus.OK.value());
		}

		if(empGroupCreationDto.getIsActive()) {
			validateAssignEmp(empGroupCreationDto, errors);
			if(errors.hasErrors()) {
				return sendErrors(errors);
			}

			if(emp.get().getGrpId() != null) {
				return new ApiResponse2<>(true, ApiResponseStatus.alreadyEmpGrp,null , HttpStatus.OK.value());
			}
			Optional<PayrollGroupMaster> grp = payrollGroupMasterRepo.findById(empGroupCreationDto.getGrpId());
			if(!grp.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.noGroup, null, HttpStatus.OK.value());
			}
			emp.get().setGrpId(grp.get());
		}
		else {
			emp.get().setGrpId(null);

		}

		payrollEmployeeMasterRepo.save(emp.get());
		return new ApiResponse2<>(true, ApiResponseStatus.saved,null , HttpStatus.OK.value());
	}

	private void validateAssignEmp(@Valid EmployeeGroupCreationDto empGroupCreationDto, Errors errors) {
		ValidationUtils.invokeValidator(validator,empGroupCreationDto, errors,Assign.class);	
	}

	public ApiResponse2<?> getEmpCountByGrpId(Long grpId) {
		Integer groups = payrollGroupMasterRepo.findEmpByGrpId(grpId);
		if(groups == null) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, groups, HttpStatus.OK.value());

	}

	public ApiResponse2<?> saveFyPaymonth(@Valid FyPaymonthSaveDto fyPaymonthSaveDto) {
		if(fyPaymonthSaveDto.getId() != null) {
			Optional<PayrollFyPaymonthMst> fyPayMonth = payrollFyPaymonthMstRepo.findById(fyPaymonthSaveDto.getId());
			if(!fyPayMonth.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.NoFyId+fyPaymonthSaveDto.getFyId() , null, HttpStatus.OK.value()); 
			}
			PayrollFyPaymonthMst payrollFyPaymonthMst = payrollFyPaymonthMstMapper.fyPaymonthDtoToModel(fyPaymonthSaveDto);
			payrollFyPaymonthMst.setId(fyPaymonthSaveDto.getId());
			payrollFyPaymonthMstRepo.save(payrollFyPaymonthMst);
			return new ApiResponse2<>(true, ApiResponseStatus.updated ,payrollFyPaymonthMst, HttpStatus.OK.value());
		}
		else {
			if(payrollFyPaymonthMstRepo.findByCurrentYearAndMonthId(fyPaymonthSaveDto.getCurrentYear(), fyPaymonthSaveDto.getMonthId()) != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existedFYMON , null, HttpStatus.OK.value()); 
			}
			PayrollFyPaymonthMst payrollFyPaymonthMst = payrollFyPaymonthMstMapper.fyPaymonthDtoToModel(fyPaymonthSaveDto);
			payrollFyPaymonthMstRepo.save(payrollFyPaymonthMst);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, payrollFyPaymonthMst, HttpStatus.OK.value());
		}

	}

	public ApiResponse2<?> getFyPaymonth(Long fyId) {

		List<PayrollFyPaymonthMst> payrollFyPaymonthMst = payrollFyPaymonthMstRepo.findAllByFyIdOrderByIdDesc(fyId);
		if(payrollFyPaymonthMst.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payrollFyPaymonthMstMapper.fyPaymonthModelToDtos(payrollFyPaymonthMst), HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveOfficeMaster(@Valid OfficeMasterDto officeMasterDto) {
		Errors errors = new BeanPropertyBindingResult(officeMasterDto, "officeMasterDto");
		ValidationUtils.invokeValidator( validator, officeMasterDto, errors);
		if(officeMasterDto.getStatus()) {
			validateOperationalfields(officeMasterDto, errors);
			if(errors.hasErrors()) {
				return sendErrors(errors);
			}
		}
		if (officeMasterDto.getOfcId() != null) {
			Optional<PayrollOfficeMaster> existingOfficeMaster = payrollOfficeMasterRepo.findById(officeMasterDto.getOfcId());
			if (!existingOfficeMaster.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.noOffice , null, HttpStatus.OK.value()); 
			}
			PayrollOfficeMaster payrollOfficeMaster = payrollOfficeMasterMapper.officeMasterDtoToModel(officeMasterDto);
			payrollOfficeMaster.setOfcId(officeMasterDto.getOfcId());
			payrollOfficeMasterRepo.save(payrollOfficeMaster);
			return new ApiResponse2<>(true, ApiResponseStatus.updated ,payrollOfficeMaster, HttpStatus.OK.value());
		} else {
			if (payrollOfficeMasterRepo.findByOfficeNameAndOfcCode(officeMasterDto.getOfficeName(), officeMasterDto.getOfcCode()) != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existedFY , null, HttpStatus.OK.value());
			}
			PayrollOfficeMaster payrollOfficeMaster = payrollOfficeMasterMapper.officeMasterDtoToModel(officeMasterDto);
			payrollOfficeMasterRepo.save(payrollOfficeMaster);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, payrollOfficeMaster, HttpStatus.OK.value());
		}
	}

	private void validateOperationalfields(OfficeMasterDto officeMasterDto, Errors errors) {
		ValidationUtils.invokeValidator(validator,officeMasterDto, errors,Operational.class);

	}

	public ApiResponse2<?> getCurrentYearByFyId(Long fyId) {

		List<DropdownResponse> currentYear = generalMstRepo.findByParentTypeIdAndIsActive(fyId, true);
		if(currentYear.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch,currentYear , HttpStatus.OK.value());
	}

	public ApiResponse2<?> getOfficeMaster() {
		List<PayrollOfficeMaster> payrollOfficeMaster = payrollOfficeMasterRepo.findAllByOrderByOfficeIdDesc();
		if(payrollOfficeMaster.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payrollOfficeMasterMapper.officeMasterModelToDtos(payrollOfficeMaster), HttpStatus.OK.value());
	}

	public ApiResponse2<?> savePayMatrix(@Valid PayMatrixSaveDto payMatrixSaveDto) {

		if (payMatrixSaveDto.getId() != null) {
			Optional<PayrollPayMatrix> existingPayMatrix = payMatrixRepo.findById(payMatrixSaveDto.getId());
			if (!existingPayMatrix.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
			}

			PayrollPayMatrix payrollPayMatrix = existingPayMatrix.get();
			
			Optional<PayrollBandServiceTypeMapping> payband = payrollBandServiceTypeMappingRepo.findById(payMatrixSaveDto.getPayBandId());
			if(!payband.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.invalidBandID, null, HttpStatus.OK.value());
			}	
			payrollPayMatrix.setPayBandId(payband.get());
			payrollPayMatrix.setValue(payMatrixSaveDto.getValue());
			payrollPayMatrix.setIsActive(payMatrixSaveDto.getIsActive());

			payMatrixRepo.save(payrollPayMatrix);
			return new ApiResponse2<>(true, ApiResponseStatus.updated, payrollPayMatrix, HttpStatus.OK.value());
		} 
		else {
			if (payMatrixRepo.findByPayBandId(payMatrixSaveDto.getPayBandId()) != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existing, null, HttpStatus.OK.value());
			}
			
			Optional<PayrollBandServiceTypeMapping> payband = payrollBandServiceTypeMappingRepo.findById(payMatrixSaveDto.getPayBandId());
			if(!payband.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.invalidBandID, null, HttpStatus.OK.value());
			}	
			PayrollPayMatrix payrollPayMatrix = new PayrollPayMatrix();
			payrollPayMatrix.setPayBandId(payband.get());
			payrollPayMatrix.setValue(payMatrixSaveDto.getValue());
			payrollPayMatrix.setIsActive(payMatrixSaveDto.getIsActive());
			payMatrixRepo.save(payrollPayMatrix);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, payrollPayMatrix, HttpStatus.OK.value());
		}
	}

	public ApiResponse2<?> getPayMatrix() {
		List<PayrollPayMatrix> payrollPayMatrixList = payMatrixRepo.findAllByOrderByCrtOnDesc();
		if (payrollPayMatrixList.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		List<PayMatrixSaveDto> payMatrixSaveDtoList = new ArrayList<>();
		for (PayrollPayMatrix payrollPayMatrix : payrollPayMatrixList) {
			PayMatrixSaveDto payMatrixSaveDto = new PayMatrixSaveDto();
			
			payMatrixSaveDto.setId(payrollPayMatrix.getId());
			
			payMatrixSaveDto.setPayBandId(payrollPayMatrix.getPayBandId().getPayBandId().getTypeId().intValue());
			payMatrixSaveDto.setPayBand(payrollPayMatrix.getPayBandId().getPayBandId().getTypeName());
			
			payMatrixSaveDto.setValue(payrollPayMatrix.getValue());
			payMatrixSaveDto.setIsActive(payrollPayMatrix.getIsActive());
			
			payMatrixSaveDto.setMinValue(payrollPayMatrix.getPayBandId().getMinValue());
			payMatrixSaveDto.setMaxValue(payrollPayMatrix.getPayBandId().getMaxValue());
			
			payMatrixSaveDto.setEffectiveFrom(payrollPayMatrix.getPayBandId().getPcServTypeId().getEffectiveFrom());
					
			payMatrixSaveDto.setServiceType(payrollPayMatrix.getPayBandId()	.getPcServTypeId().getServTypeId().getServiceType());
			payMatrixSaveDto.setServiceTypeId(payrollPayMatrix.getPayBandId().getPcServTypeId().getServTypeId().getId());
			
			payMatrixSaveDto.setPcSerTypeId(payrollPayMatrix.getPayBandId().getPcServTypeId().getId());			
			payMatrixSaveDto.setPayCommision(payrollPayMatrix.getPayBandId().getPcServTypeId().getPcId().getTypeName());	
			payMatrixSaveDto.setPayCommisionId(payrollPayMatrix.getPayBandId().getPcServTypeId().getPcId().getTypeId());
			
			payMatrixSaveDto.setPbServTypeId(payrollPayMatrix.getPayBandId().getId());		
			
			payMatrixSaveDtoList.add(payMatrixSaveDto);
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payMatrixSaveDtoList, HttpStatus.OK.value());
	}
	
	public ApiResponse2<?> getPayHeadServTypeMapping(Integer serviceTypeId) {
		if(serviceTypeId == null) {
			List<PayrollPayHeadServiceTypeMapping> payrollPayHeadServiceTypeMapping = payrollPayHeadServiceTypeMappingRepo.findAllByOrderByCrtOnDesc();
			if(payrollPayHeadServiceTypeMapping.isEmpty()) {
				return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
			}		
			return new ApiResponse2<>(true, ApiResponseStatus.fetch, payHeadServTypeMappingMapper.modelToDtos(payrollPayHeadServiceTypeMapping), HttpStatus.OK.value());
		}
		List<PayrollPayHeadServiceTypeMapping> payrollPayHeadServiceTypeMapping = payrollPayHeadServiceTypeMappingRepo.findByServiceTypeId_IdOrderByIdDesc(serviceTypeId);
		if(payrollPayHeadServiceTypeMapping.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payHeadServTypeMappingMapper.modelToDtos(payrollPayHeadServiceTypeMapping), HttpStatus.OK.value());
	}
	
	public ApiResponse2<?> savePayHeadServTypeMapping(@Valid PayHeadServiceTypeMappingDto payHeadServiceTypeMappingDto) {
		if (payHeadServiceTypeMappingDto.getId() != null) {
		    Optional<PayrollPayHeadServiceTypeMapping> typeExists = payrollPayHeadServiceTypeMappingRepo.findById(payHeadServiceTypeMappingDto.getId());
		    if (typeExists.isPresent()) {
		        PayrollPayHeadServiceTypeMapping existingData = payHeadServTypeMappingMapper.dtoToModel(payHeadServiceTypeMappingDto);
		        existingData.setId(payHeadServiceTypeMappingDto.getId());
		        Optional<PayrollPayHeadMaster> payHead = payrollPayheadMstRepo.findById(payHeadServiceTypeMappingDto.getPayheadId());
		        if (!payHead.isPresent()) {
		            throw new NoRecordFoundException("Pay Head not found");
		        }
		        existingData.setPayheadId(payHead.get());
		        Optional<PayrollServiceTypeMaster> service = serviceTypeRepo.findById(payHeadServiceTypeMappingDto.getServiceTypeId());
		        if (!service.isPresent()) {
		            throw new NoRecordFoundException("Service type not found");
		        }
		        existingData.setServiceTypeId(service.get());
		        payrollPayHeadServiceTypeMappingRepo.save(existingData);
		        return new ApiResponse2<>(true, ApiResponseStatus.updated, existingData, HttpStatus.OK.value());
		    }
		}
		Optional<PayrollPayHeadMaster> existingPayHead = payrollPayheadMstRepo.findById(payHeadServiceTypeMappingDto.getPayheadId());
		if (existingPayHead.isPresent()) {
		    Optional<PayrollPayHeadServiceTypeMapping> isExistingData = payrollPayHeadServiceTypeMappingRepo.findByPayheadId_PayheadIdAndServiceTypeId_Id(payHeadServiceTypeMappingDto.getPayheadId(), payHeadServiceTypeMappingDto.getServiceTypeId());
		    if (isExistingData.isPresent()) {
		        return new ApiResponse2<>(false, ApiResponseStatus.existing, "", HttpStatus.OK.value());
		    }
		    PayrollPayHeadServiceTypeMapping typeMapping = payHeadServTypeMappingMapper.dtoToModel(payHeadServiceTypeMappingDto);
		    Optional<PayrollPayHeadMaster> payHead = payrollPayheadMstRepo.findById(payHeadServiceTypeMappingDto.getPayheadId());
		    if (!payHead.isPresent()) {
		        throw new NoRecordFoundException("Pay Head not found");
		    }
		    typeMapping.setPayheadId(payHead.get());
		    Optional<PayrollServiceTypeMaster> service = serviceTypeRepo.findById(payHeadServiceTypeMappingDto.getServiceTypeId());
		    if (!service.isPresent()) {
		        throw new NoRecordFoundException("Service type not found");
		    }
		    typeMapping.setServiceTypeId(service.get());
		    payrollPayHeadServiceTypeMappingRepo.save(typeMapping);
		    return new ApiResponse2<>(true, ApiResponseStatus.saved, typeMapping, HttpStatus.OK.value());
		} 
		else {
		    throw new NoRecordFoundException();
		}
	}

	public ApiResponse2<?> getPayHeadDropdown(Long payHeadType) {
		HashMap<String, Object> response = new HashMap<>();
		response.put("payHeadDropdown", payrollPayheadMstRepo.findByPayHeadTypeAndIsActive(payHeadType,true));
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, response, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getRangeByPayLevel(Long payBandId) {
		List<PayLevelRangeDto> payLevelRange = payrollBandServiceTypeMappingRepo.findRangeByPayBandIdAndIsActive(payBandId, true);
		if(payLevelRange.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payLevelRange, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getPayBandsByServiceTypePc(Integer serTypePcId) {
		List<DropdownResponse> payBandsServices = payrollBandServiceTypeMappingRepo.findBySerTypeAndPc(serTypePcId);
		if(payBandsServices.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payBandsServices , HttpStatus.OK.value());
		
	}

	public ApiResponse2<?> getFyMonth() {
	    List<DropdownDataDto> response = payrollFyPaymonthMstRepo.getFyMonth();
		if(response == null) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, response , HttpStatus.OK.value());
	}
}
