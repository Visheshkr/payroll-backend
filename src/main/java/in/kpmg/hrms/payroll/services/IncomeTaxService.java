package in.kpmg.hrms.payroll.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationSchemeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.EmpItDeclarationWindow;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxDeclarationDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxDeducHeadDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxSectionDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.IncomeTaxSlabDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionDedHeadMappingDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionSchemeDropdownDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ItSectionSchemeDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.PayrollitSanctionLimitDto;
import in.kpmg.hrms.payroll.dtos.RequestDto.ProfessionalTaxDto;
import in.kpmg.hrms.payroll.dtos.responseDto.ItDeclarationSchemesResponse;
import in.kpmg.hrms.payroll.dtos.responseDto.ItDeclarationSectionResponse;
import in.kpmg.hrms.payroll.exceptions.NoRecordFoundException;
import in.kpmg.hrms.payroll.mapper.EmpItDeclarationMapper;
import in.kpmg.hrms.payroll.mapper.ItDeclarationSetupMapper;
import in.kpmg.hrms.payroll.mapper.ItSanctionLimitMapper;

import in.kpmg.hrms.payroll.mapper.PayrollTaxDeducHeadMapper;
import in.kpmg.hrms.payroll.mapper.PayrollITSectionSchemeMstMapper;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.ItSectionDedHeadMapping;
import in.kpmg.hrms.payroll.models.PayrollEmpItDeclarationMst;
import in.kpmg.hrms.payroll.models.PayrollEmpItDeclarationSchemeMst;
import in.kpmg.hrms.payroll.models.PayrollITSanctionLimitMst;
import in.kpmg.hrms.payroll.models.PayrollITSectionSchemeMst;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxDeductionHeadMaster;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxSectionMst;
import in.kpmg.hrms.payroll.models.PayrollIncomeTaxSlabMaster;
import in.kpmg.hrms.payroll.models.PayrollLocationMaster;
import in.kpmg.hrms.payroll.models.PayrollProfessionalTaxMaster;
import in.kpmg.hrms.payroll.models.PayrollTaxDeclarationMaster;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.repo.EmpPayEntitlementRepo;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.IncomeTaxDeductionHeadRepo;
import in.kpmg.hrms.payroll.repo.IncomeTaxSectionRepo;
import in.kpmg.hrms.payroll.repo.IncomeTaxSlabRepo;
import in.kpmg.hrms.payroll.repo.ItSectionDedHeadMappingRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmpItDeclarationRepo;
import in.kpmg.hrms.payroll.repo.PayrollITSanctionLimitMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollItSectionSchemeMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollLocationMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollProfessionalTaxRepo;
import in.kpmg.hrms.payroll.repo.PayrollTaxDeclarationRepo;

@Service
public class IncomeTaxService {

	@Autowired
	IncomeTaxSectionRepo incomeTaxSectionRepo;


	@Autowired
	PayrollITSanctionLimitMstRepo payrollITSanctionLimitMstRepo;


	@Autowired
	ItSectionDedHeadMappingRepo itSectionDedHeadMappingRepo;

	@Autowired
	ItSanctionLimitMapper itSanctionLimitMapper;

	@Autowired
	IncomeTaxDeductionHeadRepo incomeTaxDeductionHeadRepo;

	@Autowired
	GeneralMstRepo generalMstRepo;

	@Autowired
	IncomeTaxSlabRepo incomeTaxSlabRepo;

	@Autowired
	PayrollTaxDeducHeadMapper payrollTaxDeducHeadMapper;

	@Autowired
	PayrollTaxDeclarationRepo payrollTaxDeclarationRepo;

	@Autowired
	PayrollProfessionalTaxRepo payrollProfessionalTaxRepo;

	@Autowired
	PayrollLocationMasterRepo payrollLocationMasterRepo;

	@Autowired
	PayrollItSectionSchemeMstRepo payrollItSectionSchemeMstRepo;

	@Autowired
	PayrollITSectionSchemeMstMapper payrollITSectionSchemeMstMapper;

	@Autowired
	EmpItDeclarationMapper empItDeclarationMapper;

	@Autowired
	PayrollEmpItDeclarationRepo payrollEmpItDeclarationRepo;

	@Autowired
	ItDeclarationSetupMapper itDeclarationSetupMapper;

	@Autowired
	EmpPayEntitlementRepo empPayEntitlementRepo;

	public ApiResponse2<?> saveITaxSection(@Valid IncomeTaxSectionDto saveDto) {
		if(saveDto.getId() != null) {
			Optional<PayrollIncomeTaxSectionMst> itSection = incomeTaxSectionRepo.findById(saveDto.getId());
			if(!itSection.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.NoIncometaxSectionFound + saveDto.getId() );
			}
			PayrollIncomeTaxSectionMst section = new PayrollIncomeTaxSectionMst();
			section.setSectionName(saveDto.getSectionName());
			section.setId(saveDto.getId());
			section.setDescription(saveDto.getDescription());
			section.setIsActive(saveDto.getIsActive());
			section.setMaxDedAmt(saveDto.getMaxDedAmt());
			section.setEffectiveFrom(saveDto.getEffectiveFrom());
			section.setEffectiveTo(saveDto.getEffectiveTo());


			Optional<GeneralMst> regimeType = generalMstRepo.findById(saveDto.getRegimeType());
			regimeType.get().setTypeId(saveDto.getRegimeType());
			section.setRegimeType(regimeType.get());



			UserMst user = new UserMst();
			user.setUserId(saveDto.getUserId());
			section.setCreatedBy(user);
			PayrollIncomeTaxSectionMst savedSection = incomeTaxSectionRepo.save(section);

			return new ApiResponse2<>(true,ApiResponseStatus.saved, savedSection, HttpStatus.OK.value());

		}
		else {
			PayrollIncomeTaxSectionMst section = new PayrollIncomeTaxSectionMst();
			section.setSectionName(saveDto.getSectionName());
			section.setDescription(saveDto.getDescription());
			section.setIsActive(saveDto.getIsActive());

			Optional<GeneralMst> regimeType = generalMstRepo.findById(saveDto.getRegimeType());
			regimeType.get().setTypeId(saveDto.getRegimeType());
			section.setRegimeType(regimeType.get());

			section.setMaxDedAmt(saveDto.getMaxDedAmt());
			section.setEffectiveFrom(saveDto.getEffectiveFrom());
			section.setEffectiveTo(saveDto.getEffectiveTo());

			UserMst user = new UserMst();
			user.setUserId(saveDto.getUserId());
			section.setCreatedBy(user);

			PayrollIncomeTaxSectionMst savedSection = incomeTaxSectionRepo.save(section);
			return new ApiResponse2<>(true,ApiResponseStatus.saved, savedSection, HttpStatus.OK.value());
		}

	}

	public ApiResponse2<?> getITaxSection() {
		List<PayrollIncomeTaxSectionMst> itSections= incomeTaxSectionRepo.findAll();
		if(itSections.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, itSections, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveITaxSlab(@Valid IncomeTaxSlabDto saveDto) {
		PayrollIncomeTaxSlabMaster slab = new PayrollIncomeTaxSlabMaster();
		if (saveDto.getItSlabId() != null) {
			Optional<PayrollIncomeTaxSlabMaster> itSlab = incomeTaxSlabRepo.findById(saveDto.getItSlabId());
			if (!itSlab.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.NoIncometaxSlabFound + saveDto.getItSlabId());
			}
			slab.setItSlabId(saveDto.getItSlabId());
		}
		slab.setSequenceNo(saveDto.getSequenceNo());
		slab.setIncomeFrom(saveDto.getIncomeFrom());
		slab.setIncomeTo(saveDto.getIncomeTo());
		slab.setIncomeTaxRate(saveDto.getIncomeTaxRate());
		slab.setCessPercent(saveDto.getCessPercent());
		slab.setEffectiveFrom(saveDto.getEffectiveFrom());
		slab.setDisplayOrder(saveDto.getDisplayOrder());
		slab.setIsMaxLimit(saveDto.getIsMaxLimit());
		slab.setIsActive(saveDto.getIsActive());
		if (saveDto.getRegimeType() != null) {
			Optional<GeneralMst> regimeType = generalMstRepo.findById(saveDto.getRegimeType());
			regimeType.ifPresent(slab::setRegimeType);
		}
		if (saveDto.getFyId() != null) {
			Optional<GeneralMst> fyId = generalMstRepo.findById(saveDto.getFyId());
			fyId.ifPresent(slab::setFyId);
		}
		if (saveDto.getTaxSubCategory() != null) {
			Optional<GeneralMst> taxSubCategory = generalMstRepo.findById(saveDto.getTaxSubCategory());
			taxSubCategory.ifPresent(slab::setTaxSubCategory);
		}
		if (saveDto.getGender() != null) {
			Optional<GeneralMst> gender = generalMstRepo.findById(saveDto.getGender());
			gender.ifPresent(slab::setGender);
		}
		if (saveDto.getUserId() != null) {
			UserMst crtBy = new UserMst();
			crtBy.setUserId(saveDto.getUserId());
			slab.setCrtBy(crtBy);
		}
		PayrollIncomeTaxSlabMaster savedSlab = incomeTaxSlabRepo.save(slab);
		return new ApiResponse2<>(true, ApiResponseStatus.saved, savedSlab, HttpStatus.OK.value());
	}


	public ApiResponse2<?> getITaxSlab() {
		List<PayrollIncomeTaxSlabMaster> itSlabs = incomeTaxSlabRepo.findAll();
		if(itSlabs.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, itSlabs, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveITaxDeducHead(@Valid IncomeTaxDeducHeadDto saveDto) {
		PayrollIncomeTaxDeductionHeadMaster deducHead = new PayrollIncomeTaxDeductionHeadMaster();

		if (saveDto.getId() != null) {
			Optional<PayrollIncomeTaxDeductionHeadMaster> itDeducHead = incomeTaxDeductionHeadRepo.findById(saveDto.getId());
			if (!itDeducHead.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.NoIncometaxDeductionHeadFound + saveDto.getId());
			}
			deducHead.setId(saveDto.getId());
		}
		deducHead.setDeductionHeadName(saveDto.getDeductionHeadName());
		deducHead.setMaxAmtLimit(saveDto.getMaxAmtLimit());
		deducHead.setUnderTaxLimit(saveDto.getUnderTaxLimit());
		deducHead.setDisplayOrder(saveDto.getDisplayOrder());
		if (saveDto.getTaxEffectId() != null) {
			Optional<GeneralMst> taxEffect = generalMstRepo.findById(saveDto.getTaxEffectId());
			taxEffect.ifPresent(deducHead::setTaxEffectId);
		}

		if (saveDto.getPayheadEffect() != null) {
			Optional<GeneralMst> payheadEffect = generalMstRepo.findById(saveDto.getPayheadEffect());
			payheadEffect.ifPresent(deducHead::setPayheadEffect);
		}

		if (saveDto.getUserId() != null) {
			UserMst createdBy = new UserMst();
			createdBy.setUserId(saveDto.getUserId());
			deducHead.setCreatedBy(createdBy);
		}
		PayrollIncomeTaxDeductionHeadMaster savedDeducHead = incomeTaxDeductionHeadRepo.save(deducHead);

		return new ApiResponse2<>(true, ApiResponseStatus.saved, savedDeducHead, HttpStatus.OK.value());
	}


	public ApiResponse2<?> getITaxDeducHead(IncomeTaxDeducHeadDto fetchDto) {
		List<PayrollIncomeTaxDeductionHeadMaster> itDeducHead = incomeTaxDeductionHeadRepo.findByDeductionHeadNameAndTaxEffectId(fetchDto.getDeductionHeadName(), fetchDto.getTaxEffectId());

		if (itDeducHead.isEmpty()) {
			return new ApiResponse2<>(true, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, itDeducHead, HttpStatus.OK.value());
	}



	public ApiResponse2<?> saveITSanctionLimit(@Valid PayrollitSanctionLimitDto saveDto) {
		if(saveDto.getId() != null) {
			if(payrollITSanctionLimitMstRepo.findById(saveDto.getId()).isPresent()) {
				PayrollITSanctionLimitMst itSectionMst = itSanctionLimitMapper.dtoToSanctionLimit(saveDto);
				itSectionMst.setId(saveDto.getId());
				Optional<PayrollIncomeTaxSectionMst> itSection = incomeTaxSectionRepo.findById(saveDto.getItsectionId());
				if(!itSection.isPresent()) {
					throw new NoRecordFoundException(ApiResponseStatus.noRecords);
				}
				itSectionMst.setItsectionId(itSection.get());
				PayrollITSanctionLimitMst itSanctionRes = payrollITSanctionLimitMstRepo.save(itSectionMst);
				return new ApiResponse2<>(true,ApiResponseStatus.saved, itSanctionRes, HttpStatus.OK.value());
			}
			else {
				throw new NoRecordFoundException(ApiResponseStatus.noRecords);
			}
		}
		else {
			PayrollITSanctionLimitMst itSectionMst = itSanctionLimitMapper.dtoToSanctionLimit(saveDto);
			Optional<PayrollIncomeTaxSectionMst> itSection = incomeTaxSectionRepo.findById(saveDto.getItsectionId());
			if(!itSection.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.noRecords);
			}
			itSectionMst.setItsectionId(itSection.get());
			PayrollITSanctionLimitMst itSanctionRes = payrollITSanctionLimitMstRepo.save(itSectionMst);
			return new ApiResponse2<>(true,ApiResponseStatus.saved, itSanctionRes, HttpStatus.OK.value());

		}
	}

	public ApiResponse2<?> getITSanctionLimit() {
		List<PayrollITSanctionLimitMst> itSanctions = payrollITSanctionLimitMstRepo.findAll();
		if(itSanctions.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, itSanctions, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveItSectionDedHeadMapping( @Valid ItSectionDedHeadMappingDto saveDto) {
		Optional<PayrollIncomeTaxSectionMst> itSection = incomeTaxSectionRepo.findById(saveDto.getItSectionId());
		if(!itSection.isPresent()) {
			throw new NoRecordFoundException(ApiResponseStatus.NoIncometaxSectionFound + saveDto.getItSectionId() );
		}

		Optional<PayrollIncomeTaxDeductionHeadMaster> deductionHeadId =  incomeTaxDeductionHeadRepo.findById(saveDto.getDeductionHeadId());
		if(!deductionHeadId.isPresent()) {
			throw new NoRecordFoundException(ApiResponseStatus.NoDeductionHeadFound + saveDto.getDeductionHeadId() );
		}
		if(saveDto.getId() != null) {		
			Optional<ItSectionDedHeadMapping> sectionDedHead = itSectionDedHeadMappingRepo.findById(saveDto.getId());
			if(sectionDedHead.isPresent()) {			
				sectionDedHead.get().setDeductionHeadId(deductionHeadId.get());
				sectionDedHead.get().setIsActive(saveDto.getIsActive());
				sectionDedHead.get().setItSectionId(itSection.get());

				UserMst user = new UserMst();
				user.setUserId(saveDto.getUserId());
				sectionDedHead.get().setCreatedBy(user);

				ItSectionDedHeadMapping res = itSectionDedHeadMappingRepo.save(sectionDedHead.get());
				return new ApiResponse2<>(true,ApiResponseStatus.saved, res, HttpStatus.OK.value());
			}
			else {
				throw new NoRecordFoundException(ApiResponseStatus.NoITSectionDeductionHeadFound + saveDto.getId() );
			}

		}
		else {
			ItSectionDedHeadMapping sectionDedHead= new ItSectionDedHeadMapping();
			sectionDedHead.setDeductionHeadId(deductionHeadId.get());
			sectionDedHead.setIsActive(saveDto.getIsActive());
			sectionDedHead.setItSectionId(itSection.get());

			UserMst user = new UserMst();
			user.setUserId(saveDto.getUserId());
			sectionDedHead.setCreatedBy(user);

			ItSectionDedHeadMapping res = itSectionDedHeadMappingRepo.save(sectionDedHead);
			return new ApiResponse2<>(true,ApiResponseStatus.saved, res, HttpStatus.OK.value());
		}
	}

	public ApiResponse2<?> getItSectionDedHeadMapping() {
		List<ItSectionDedHeadMapping> itSectionsDedHeads= itSectionDedHeadMappingRepo.findAll();
		if(itSectionsDedHeads.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, itSectionsDedHeads, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveITaxDeclaration(@Valid IncomeTaxDeclarationDto saveDto) {
		PayrollTaxDeclarationMaster declaration = new PayrollTaxDeclarationMaster();

		if (saveDto.getId() != null) {
			Optional<PayrollTaxDeclarationMaster> itDeclartion = payrollTaxDeclarationRepo.findById(saveDto.getId());
			if (!itDeclartion.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.NoIncometaxDeclarationFound + saveDto.getId());
			}
			declaration.setId(saveDto.getId());
		}
		declaration.setItDecSubFrom(saveDto.getItDecSubFrom());
		declaration.setItDecSubTo(saveDto.getItDecSubTo());
		declaration.setProofInvSubFrom(saveDto.getProofInvSubFrom());
		declaration.setProofInvSubTo(saveDto.getProofInvSubTo());
		declaration.setIsActive(saveDto.getIsActive());
		if (saveDto.getFyId() != null) {
			Optional<GeneralMst> fyid = generalMstRepo.findById(saveDto.getFyId());
			fyid.ifPresent(declaration::setFyId);
		}

		if (saveDto.getUserId() != null) {
			UserMst createdBy = new UserMst();
			createdBy.setUserId(saveDto.getUserId());
			declaration.setCrtBy(createdBy);
		}

		PayrollTaxDeclarationMaster savedDeclaration = payrollTaxDeclarationRepo.save(declaration);

		return new ApiResponse2<>(true, ApiResponseStatus.saved, savedDeclaration, HttpStatus.OK.value());
	}


	public ApiResponse2<?> getITaxDeclaration() {
		List<PayrollTaxDeclarationMaster> itDec = payrollTaxDeclarationRepo.findAllOrderByIdDesc();
		if(itDec.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, itDec, HttpStatus.OK.value());
	}

	public ApiResponse2<?> savePfTax(@Valid ProfessionalTaxDto saveDto) {
		PayrollProfessionalTaxMaster pf = new PayrollProfessionalTaxMaster();

		if (saveDto.getId() != null) {
			Optional<PayrollProfessionalTaxMaster> pfTax = payrollProfessionalTaxRepo.findById(saveDto.getId());
			if (!pfTax.isPresent()) {
				throw new NoRecordFoundException(ApiResponseStatus.NoProfessionalTaxFound + saveDto.getId());
			}
			pf.setId(saveDto.getId());
		}
		pf.setMinIncome(saveDto.getMinIncome());
		pf.setMaxIncome(saveDto.getMaxIncome());
		pf.setProfessionalTaxAmount(saveDto.getProfessionalTaxAmount());
		pf.setEffectiveFrom(saveDto.getEffectiveFrom());
		pf.setEffectiveTo(saveDto.getEffectiveTo());
		if (saveDto.getStateId() != null) {
			Optional<PayrollLocationMaster> stateId = payrollLocationMasterRepo.findById(saveDto.getStateId());
			stateId.ifPresent(pf::setStateId);
		}
		if (saveDto.getUserId() != null) {
			UserMst createdBy = new UserMst();
			createdBy.setUserId(saveDto.getUserId());
			pf.setCrtBy(createdBy);
		}

		PayrollProfessionalTaxMaster savedPf = payrollProfessionalTaxRepo.save(pf);

		return new ApiResponse2<>(true, ApiResponseStatus.saved, savedPf, HttpStatus.OK.value());
	}


	public ApiResponse2<?> getPfTax() {
		List<PayrollProfessionalTaxMaster> pft = payrollProfessionalTaxRepo.findAll();
		if(pft.isEmpty()) {
			throw new NoRecordFoundException(ApiResponseStatus.noRecords);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, pft, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getITSectionScheme() {
		List<PayrollITSectionSchemeMst> payrollITSectionSchemeMst = payrollItSectionSchemeMstRepo.findAllByOrderByCrtOnDesc();
		if(payrollITSectionSchemeMst.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}		
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, payrollITSectionSchemeMstMapper.modelToDtos(payrollITSectionSchemeMst), HttpStatus.OK.value());	
	}

	public ApiResponse2<?> saveITSectionScheme(@Valid ItSectionSchemeDto itSectionSchemeDto) {
		if (itSectionSchemeDto.getId() != null) {
			Optional<PayrollITSectionSchemeMst> existingITSectionScheme = payrollItSectionSchemeMstRepo.findById(itSectionSchemeDto.getId());
			if (!existingITSectionScheme.isPresent()) {
				return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
			}
			PayrollITSectionSchemeMst payrollITSectionSchemeMst = payrollITSectionSchemeMstMapper.dtoToModel(itSectionSchemeDto);
			payrollITSectionSchemeMst.setId(itSectionSchemeDto.getId());
			payrollItSectionSchemeMstRepo.save(payrollITSectionSchemeMst);
			return new ApiResponse2<>(true, ApiResponseStatus.updated, payrollITSectionSchemeMst, HttpStatus.OK.value());
		} else {
			if (payrollItSectionSchemeMstRepo.findBySchemeNameAndSectionId_Id(itSectionSchemeDto.getSchemeName(), itSectionSchemeDto.getSectionId()) != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existing, null, HttpStatus.OK.value());
			}
			PayrollITSectionSchemeMst payrollITSectionSchemeMst = payrollITSectionSchemeMstMapper.dtoToModel(itSectionSchemeDto);
			payrollItSectionSchemeMstRepo.save(payrollITSectionSchemeMst);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, payrollITSectionSchemeMst, HttpStatus.OK.value());
		}
	}

	public ApiResponse2<?> getITSectionSchemeDropdown() {
		List<ItSectionSchemeDropdownDto> itSectionSchemeDropdownDto = payrollItSectionSchemeMstRepo.findByIsActive(true);
		if(itSectionSchemeDropdownDto.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, itSectionSchemeDropdownDto, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveItDeclaration(EmpItDeclarationDto empItDeclarationDto) {
		PayrollEmpItDeclarationMst empDec = empItDeclarationMapper.dtoToMst(empItDeclarationDto);
		List<PayrollEmpItDeclarationSchemeMst> schemes = new ArrayList<>();
		for(EmpItDeclarationSchemeDto schemeDto : empItDeclarationDto.getEmpItDecSchemes()) {
			PayrollEmpItDeclarationSchemeMst scheme = empItDeclarationMapper.schemeDtoToMst(schemeDto);
			scheme.setEmpItDec(empDec);
			schemes.add(scheme);
		}
		empDec.setEmpItDecSchemes(schemes);
		payrollEmpItDeclarationRepo.save(empDec);
		return new ApiResponse2<>(true, ApiResponseStatus.saved, null, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getItDecWindow(Long empId) {
		List<PayrollTaxDeclarationMaster> itDec = payrollTaxDeclarationRepo.findAllActiveWindowOrderByIdDesc();
		if(itDec.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		Long totalIncome = 0L;
		Long totalDeduction = 0L;
		List<EmpItDeclarationWindow> response = new ArrayList<>();
		List<EmpPayEntitlement> payEnt = empPayEntitlementRepo.findAllByEmpId_EmpId(empId);
		if(payEnt == null) {
			return new ApiResponse2<>(false,ApiResponseStatus.noEmp, null, HttpStatus.OK.value());
		}
		totalIncome = payEnt.stream().mapToLong(obj -> obj.getPayheadValue()).sum();	
		for(PayrollTaxDeclarationMaster window : itDec) {
			EmpItDeclarationWindow res = itDeclarationSetupMapper.dtoToMst(window);
			PayrollEmpItDeclarationMst itEmpDec = payrollEmpItDeclarationRepo.findAllByEmpId_EmpIdAndItDec_Id(empId,window.getId());
			if(itEmpDec == null) {
				res.setSalaryIncome(totalIncome* 12);
			}
			else {
				res.setSalaryIncome(totalIncome* 12);
				totalDeduction = itEmpDec.getEmpItDecSchemes().stream().mapToLong(obj-> obj.getSchemeVal()).sum();
				res.setTotalDeduction(totalDeduction);
				res.setNetIncome(totalIncome * 12  + itEmpDec.getOtherIncome() );
				res.setOtherIncome(itEmpDec.getOtherIncome());

			}
			response.add(res);
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, response, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getItSectionsByRegimeType(Long regType) {
		List<ItDeclarationSectionResponse> sections = incomeTaxSectionRepo.findSectionsByRegime(regType);
		if(sections.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, sections, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getSchemeBySection(Integer sectionId) {
		List<ItDeclarationSchemesResponse> scehems = payrollItSectionSchemeMstRepo.findBySectionId(sectionId);
		if(scehems.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value()); 
		}
		return new ApiResponse2<>(true,ApiResponseStatus.fetch, scehems, HttpStatus.OK.value());
	}
}
