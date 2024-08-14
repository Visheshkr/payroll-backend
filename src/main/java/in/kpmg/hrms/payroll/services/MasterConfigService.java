package in.kpmg.hrms.payroll.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayHeadConfigDto;
import in.kpmg.hrms.payroll.dtos.responseDto.PayHeadConfigResponse;
import in.kpmg.hrms.payroll.mapper.PayHeadConfigmapper;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollDesignationDeptMapping;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;
import in.kpmg.hrms.payroll.models.PayrollPayHeadConfigMaster;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollDesignationDeptRepo;
import in.kpmg.hrms.payroll.repo.PayrollHraRateRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayHeadConfigRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayheadMstRepo;

@Service
public class MasterConfigService {

	@Autowired
	PayHeadConfigmapper payHeadConfigmapper;


	@Autowired
	PayrollPayHeadConfigRepo payrollPayHeadConfigRepo;
	
	@Autowired
	Validator validator;
	
	@Autowired
	PayrollDesignationDeptRepo payrollDesignationDeptRepo;
	
	@Autowired
	PayrollPayheadMstRepo payrollPayheadMstRepo;
	
	@Autowired
	PayrollHraRateRepo payrollHraRateRepo;
	
	@Autowired
	GeneralMstRepo generalMstRepo;


	public interface FixedAmt{

	}

	public interface NonfixedAmt{

	}
	public interface DA{
		
	}



	public ApiResponse2<?> savePayHeadConfig(PayHeadConfigDto payHeadConfigDto)  {
		Errors errors = new BeanPropertyBindingResult(payHeadConfigDto, "payHeadConfigDto");
		ValidationUtils.invokeValidator( validator, payHeadConfigDto, errors);
		Date sqlDate = null;
		String sqlStr = null;
		if(payHeadConfigDto.getIsFixedAmt()) {
			validateFixedAmoutfields(payHeadConfigDto, errors);
			if(errors.hasErrors()) {
				return sendErrors(errors);
			}
		}
		else if(payHeadConfigDto.getPayHeadId() == 13) {
			validateDA(payHeadConfigDto, errors);
			if(errors.hasErrors()) {
				return sendErrors(errors);
			}
			 sqlDate = new Date(payHeadConfigDto.getCirculatedDate()!=null ? payHeadConfigDto.getCirculatedDate().getTime(): null);
			 sqlStr = sqlDate.toString();
		}
		else {
			validateNonFixedAmount(payHeadConfigDto, errors);
			if(errors.hasErrors()) {
				return sendErrors(errors);
			}
			if(!payrollPayHeadConfigRepo.validateFormula(payHeadConfigDto.getFormula())) {
				return new ApiResponse2<>(false, ApiResponseStatus.inValidFormula ,null , HttpStatus.BAD_REQUEST.value());
			}
			
			
		}
		if(payHeadConfigDto.getServiceTypes().isEmpty() && payHeadConfigDto.getPayLevelIds().isEmpty() && payHeadConfigDto.getDeptDsgnIds().isEmpty()) {
			PayrollPayHeadConfigMaster payHeadConfig = payHeadConfigmapper.dtoToConfigMst(payHeadConfigDto);
			payrollPayHeadConfigRepo.save(payHeadConfig);
			return new ApiResponse2<>(true, ApiResponseStatus.saved ,payHeadConfig , HttpStatus.OK.value());

		}
		List<List<Integer>> combinations = getCombinations(payHeadConfigDto.getServiceTypes(),
				payHeadConfigDto.getDeptDsgnIds(), payHeadConfigDto.getPayLevelIds());
		List<PayrollPayHeadConfigMaster> payConfigs = new ArrayList<>();
		
		
		for (List<Integer> combination : combinations) {
			if(payrollPayHeadConfigRepo.existsConfig(combination.get(0),combination.get(1),
					combination.get(2).longValue(),payHeadConfigDto.getPayHeadId(), payHeadConfigDto.getCtaEntitlementId()
					,sqlDate,payHeadConfigDto.getTierId(),payHeadConfigDto.getPayCommissionId(), sqlStr)!= null ) {
				return new ApiResponse2<>(false, ApiResponseStatus.configCombinationExistes ,null , HttpStatus.OK.value());
			}
			PayrollPayHeadConfigMaster config = payHeadConfigmapper.dtoToConfigMst(payHeadConfigDto);    	
			if(combination.get(0)!= null) {
				PayrollServiceTypeMaster service = new PayrollServiceTypeMaster();
				service.setId(combination.get(0));
				config.setServiceType(service);       	
			}
			if(combination.get(1)!= null) {
				Optional<PayrollDesignationDeptMapping> deptDsgn = payrollDesignationDeptRepo.findById(combination.get(1));
				config.setDeptDegnId(deptDsgn.get());	
			}
			if(combination.get(2)!= null) {
				GeneralMst payLevel = new GeneralMst();
				payLevel.setTypeId((long)(combination.get(2)));
				config.setPayLevelId(payLevel);       	
			}
			payConfigs.add(config);
		}

		payrollPayHeadConfigRepo.saveAll(payConfigs);
		return new ApiResponse2<>(true, ApiResponseStatus.saved ,payConfigs , HttpStatus.OK.value());
	}

	private void validateDA(PayHeadConfigDto payHeadConfigDto, Errors errors) {
		ValidationUtils.invokeValidator(validator,payHeadConfigDto, errors,DA.class);
	}

	private void validateNonFixedAmount(PayHeadConfigDto payHeadConfigDto, Errors errors) {
		ValidationUtils.invokeValidator(validator,payHeadConfigDto, errors,NonfixedAmt.class);
		
	}

	private void validateFixedAmoutfields(PayHeadConfigDto payHeadConfigDto, Errors errors) {
		ValidationUtils.invokeValidator(validator,payHeadConfigDto, errors,FixedAmt.class);
		
	}

	public static List<List<Integer>> getCombinations(List<Integer> a, List<Integer> b, List<Integer> c) {
		List<List<Integer>> combinations = new ArrayList<>();
		generateCombinations(combinations, new ArrayList<>(), a, b, c, 0);
		return combinations;
	}

	private static void generateCombinations(List<List<Integer>> combinations, List<Integer> current, List<Integer> a, List<Integer> b, List<Integer> c, int depth) {
		if (depth == 3) {
			combinations.add(new ArrayList<>(current));
			return;
		}

		List<Integer> currentList;
		switch (depth) {
		case 0:
			currentList = a;
			break;
		case 1:
			currentList = b;
			break;
		case 2:
			currentList = c;
			break;
		default:
			throw new IllegalStateException("Unexpected value: " + depth);
		}

		if (currentList.isEmpty()) {
			current.add(null);
			generateCombinations(combinations, current, a, b, c, depth + 1);
			current.remove(current.size() - 1);
		} else {
			for (Integer value : currentList) {
				current.add(value);
				generateCombinations(combinations, current, a, b, c, depth + 1);
				current.remove(current.size() - 1);
			}
		}
	}
	
	public ApiResponse2<Object> sendErrors(Errors errors) {
		List<String> errorString= new ArrayList<>();
		for(FieldError  fieldError : errors.getFieldErrors()) {
			errorString.add(fieldError.getDefaultMessage());

		}
		return new ApiResponse2<>(false, ApiResponseStatus.validationErrors,errorString, HttpStatus.BAD_REQUEST.value());

	}

	public ApiResponse2<?> getPayHeadConfig(PayHeadConfigDto payHeadConfigDto) {
		
		List<PayrollPayHeadConfigMaster> configs = payrollPayHeadConfigRepo.findAllOrderByPayheadConfigIdDesc(payHeadConfigDto.getPayHeadId(), payHeadConfigDto.getPayCommissionId(), payHeadConfigDto.getCtaEntitlementId(), payHeadConfigDto.getIsActive());
		if(configs.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords ,configs , HttpStatus.OK.value());
		}
		HashMap<Integer, String> hm = new HashMap<>();
		List<DropdownResponse> payHeads = payrollPayheadMstRepo.findAllActivePayHeads();
		for(DropdownResponse res : payHeads) {
			hm.put(res.getTypeId(), res.getTypeName());
		}
		
		List<PayHeadConfigResponse> response = new ArrayList<>();
		for(PayrollPayHeadConfigMaster config : configs) {
			PayHeadConfigResponse res = 	payHeadConfigmapper.modelToDto(config, hm);
			if(res.getTierId() != null) {
				Optional<PayrollHRARateMst> hraRate = payrollHraRateRepo.findById(res.getTierId());
				res.setTier(hraRate.get().getTierName());
			}
			if(res.getCtaEntitlementId()!=null) {
				Optional<GeneralMst> ctaEnt = generalMstRepo.findById(res.getCtaEntitlementId().longValue());
				res.setCtaEntitlement(ctaEnt.get().getTypeName());
			}
			response.add(res);	
		}
		
		
		return new ApiResponse2<>(true, ApiResponseStatus.saved ,response , HttpStatus.OK.value());
	}




}
