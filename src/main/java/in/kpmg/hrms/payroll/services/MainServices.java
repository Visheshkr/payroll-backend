package in.kpmg.hrms.payroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.FinanceHeadSaveDto;
import in.kpmg.hrms.payroll.dtos.FinanceHeadSaveInputDto;
import in.kpmg.hrms.payroll.dtos.FinanceHeadTypeDto;
import in.kpmg.hrms.payroll.dtos.GeneralMasterSaveDto;
import in.kpmg.hrms.payroll.dtos.GeneralMstInputDto;
import in.kpmg.hrms.payroll.dtos.GeneralTypeMasterDto;
import in.kpmg.hrms.payroll.mapper.FinanceHeadMapper;
import in.kpmg.hrms.payroll.mapper.GeneralMasterMapper;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadTypeMst;
import in.kpmg.hrms.payroll.models.PayrollFinanceHeadmst;
import in.kpmg.hrms.payroll.models.PayrollGeneralTypeMaster;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.GeneralTypeMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollFinanceHeadTypeMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollFinanceHeadmstRepo;
import in.kpmg.hrms.payroll.repo.PayrollServiceTypeRepo;
import in.kpmg.hrms.payroll.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MainServices {

	@Autowired
	private GeneralMstRepo generalMstRepo;

	@Autowired
	private GeneralTypeMstRepo generalTypeMstRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PayrollServiceTypeRepo serviceTypeRepo;

	@Autowired
	PayrollFinanceHeadmstRepo payrollFinanceHeadmstRepo;

	@Autowired
	private PayrollFinanceHeadTypeMstRepo financeHeadTypeMstRepo;

	@Autowired
	private PayrollFinanceHeadmstRepo financeHeadmstRepo;


	@Autowired
	GeneralMasterMapper generalMasterMapper;

	@Autowired
	FinanceHeadMapper financeHeadMapper;

	@Autowired
	UserRepo userRepo;


	public ApiResponse2<?> getMasterBygType(GeneralMstInputDto inputDto) {

		List<GeneralMstInputDto> generalMstList = new ArrayList<>();

		Optional<PayrollGeneralTypeMaster> generalTypeMaster = generalTypeMstRepo.findById(inputDto.getParentId());
		if (generalTypeMaster.isPresent()) {
			GeneralMstInputDto dto = new GeneralMstInputDto();
			dto.setParentId(generalTypeMaster.get().getTypeId());
			dto.setParentName(generalTypeMaster.get().getTypeDesc());
			List<GeneralTypeMasterDto> typeMasterDtoList = new ArrayList<>();
			List<GeneralMst> generalMsts = generalMstRepo.findByGeneralTypeIdOrderByCreatedOnDesc(generalTypeMaster.get());
			if (!generalMsts.isEmpty()) {

				for (GeneralMst data : generalMsts) {
					typeMasterDtoList.add(generalMasterMapper.generalMstToDto(data));
				}
			}
			dto.setGeneralMst(typeMasterDtoList);
			generalMstList.add(dto);
		}


		return new ApiResponse2<>(true, "Master Fetch ", generalMstList, HttpStatus.OK.value());

	}

	@Transactional
	public ApiResponse2<?> saveMasterTypesDetails(GeneralMasterSaveDto saveDto) {
		if (saveDto.getTypeId() != null) {

			Optional<GeneralMst> existingType = generalMstRepo.findByTypeIdAndGeneralId(saveDto.getTypeId(), saveDto.getGeneralTypeId());
			if (existingType.isPresent()) {
				GeneralMst existingData = existingType.get();
				existingData = generalMasterMapper.dtoToGeneralMst(saveDto);
				generalMstRepo.save(existingData);
				return new ApiResponse2<>(true, ApiResponseStatus.updated, existingData, HttpStatus.OK.value());

			}
		}


		Optional<PayrollGeneralTypeMaster> generalTypeMaster = generalTypeMstRepo.findById(saveDto.getGeneralTypeId());
		if (generalTypeMaster.isPresent()) {

			Optional<GeneralMst> existingType = generalMstRepo.findByTypeNameAndGeneralId(saveDto.getTypeName().toUpperCase(), saveDto.getGeneralTypeId());
			if (existingType.isPresent()) {

				return new ApiResponse2<>(true, "Master data Already Present", "", HttpStatus.OK.value());
			}
			saveDto.setTypeName(saveDto.getTypeName().toUpperCase());
			GeneralMst generalMst = generalMasterMapper.dtoToGeneralMst(saveDto);
//			if(saveDto.getParentTypeId()!= null) {
//				Optional<GeneralMst> parentId = generalMstRepo.findById(saveDto.getParentTypeId());
//				if(parentId.isPresent()) {
//					generalMst.setParentTypeId(parentId.get());
//				}		
//			}
			generalMstRepo.save(generalMst);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, generalMst, HttpStatus.OK.value());


		} else {
			return new ApiResponse2<>(false, "Unable to Add Master Data, No Parent Master Present", "", HttpStatus.NOT_IMPLEMENTED.ordinal());

		}

	}



	public ApiResponse2<?> saveFinanceHeadMst(FinanceHeadSaveInputDto saveDto, Authentication authentication) {
		String userName = authentication.getName();
		UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(userName).orElseThrow(() -> new EntityNotFoundException("User not found"));

		if (saveDto.getTypeId() != null) {
			Optional<PayrollFinanceHeadmst> isExistingType = financeHeadmstRepo.findById(saveDto.getTypeId());
			if (isExistingType.isPresent()) {
//				System.out.println(saveDto);
				PayrollFinanceHeadmst existingData = isExistingType.get();
//				System.out.println(existingData);
				modelMapper.map(saveDto, existingData);
//				System.out.println(existingData);
				if (saveDto.getParentId() != null) {
					Optional<PayrollFinanceHeadmst> parentId = financeHeadmstRepo.findById(saveDto.getParentId());
					parentId.ifPresent(existingData::setParentId);
				}
				existingData.setUpdBy(userMst);
				PayrollFinanceHeadmst save = financeHeadmstRepo.save(existingData);

				return new ApiResponse2<>(true, ApiResponseStatus.saved, save, HttpStatus.OK.value());

			}
		}

		Optional<PayrollFinanceHeadTypeMst> financeHeadTypeMst = financeHeadTypeMstRepo.findById(saveDto.getFtypeId());
		if (financeHeadTypeMst.isPresent()) {

			PayrollFinanceHeadmst existingSameCode = financeHeadmstRepo.findByTypeCodeAndFTypeId(saveDto.getTypeCode(), saveDto.getFtypeId());
			if (existingSameCode != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existingCode, null, HttpStatus.OK.value());
			}

			saveDto.setTypeName(saveDto.getTypeName().toUpperCase());
			PayrollFinanceHeadmst financeHeadmst = modelMapper.map(saveDto, PayrollFinanceHeadmst.class);
			if (saveDto.getParentId() != null) {
				Optional<PayrollFinanceHeadmst> parentId = financeHeadmstRepo.findById(saveDto.getParentId());
				if (parentId.isPresent()) {
					financeHeadmst.setParentId(parentId.get());
				}
			}
			financeHeadmst.setCrtBy(userMst);
			PayrollFinanceHeadmst save = financeHeadmstRepo.save(financeHeadmst);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, save, HttpStatus.OK.value());
		} else {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}


	}

	public ApiResponse2<?> getFinanceMasterDetails(FinanceHeadSaveDto saveDto) {
		List<FinanceHeadTypeDto> headTypeDtoList = new ArrayList<>();
		Optional<PayrollFinanceHeadTypeMst> financeHeadTypeMst = financeHeadTypeMstRepo.findById(saveDto.getFtypeId());
		if (financeHeadTypeMst.isPresent()) {
			FinanceHeadTypeDto headTypeDto = new FinanceHeadTypeDto();
			headTypeDto.setParentId(financeHeadTypeMst.get().getFtypeId());
			headTypeDto.setParentName(financeHeadTypeMst.get().getFTypeName());
			List<FinanceHeadSaveDto> headMstDto= new ArrayList<>();
			List<PayrollFinanceHeadmst> financeHeadmsts= financeHeadmstRepo.findByFtypeIdOrderByCrtOnDesc(financeHeadTypeMst.get());
			if (!financeHeadmsts.isEmpty()){
				for (PayrollFinanceHeadmst data: financeHeadmsts){
					headMstDto.add(financeHeadMapper.finMstToDto(data));
				}

				headTypeDto.setFinanceHeadDto(headMstDto);
				headTypeDtoList.add(headTypeDto);
			}
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, headTypeDtoList, HttpStatus.OK.value());
	}



	public ApiResponse2<?> getSubHead(Integer minorId) {
		List<DropdownResponse> subHeads = payrollFinanceHeadmstRepo.findByParentTypeIdAndIsActive(minorId, true);
		if (subHeads.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, subHeads, HttpStatus.OK.value());
	}

	public ApiResponse2<?> paybands(Long pcId) {
		List<DropdownResponse> payHeads = generalMstRepo.findByParentTypeIdAndIsActive(pcId, true);
		if (payHeads.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payHeads, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getGeneralMstByParentId(GeneralMstInputDto inputDto) {
		List<GeneralMasterSaveDto> typeMasterDtoList = new ArrayList<>();
		
		List<GeneralMst> generalMsts = generalMstRepo.findByParentTypeId( inputDto.getParentId().longValue());
		
		if (generalMsts.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, typeMasterDtoList, HttpStatus.OK.value());
		}
		for (GeneralMst data : generalMsts) {
			typeMasterDtoList.add(generalMasterMapper.generalMstToSaveDto(data));
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, typeMasterDtoList, HttpStatus.OK.value());
	}


}
