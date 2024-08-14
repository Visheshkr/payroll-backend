package in.kpmg.hrms.payroll.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import in.kpmg.hrms.payroll.constants.EmployeeConstants;
import in.kpmg.hrms.payroll.constants.GlobalEnumConstants.Status;
import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpCountDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpCountWithFyMonthHoa;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpPayHeadDetailsResponse;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmpSalaryProcess;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmployeeReportSearchDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmployeeSalaryReportResponse;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.EmployeesSalaryReport;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.FyMonthStatusDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GetEmpDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GetGroupsDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GroupSalaryProcessDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.GroupWiseSalaryStatus;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.ProcessEmployee;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SalaryProcessResponse;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SaveEmpSalaryBillDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SaveEmpSalaryPayHeadsDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.requestDto.RegularSalaryProcessReqDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.requestDto.SalarySlipReqDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.responseDto.SalarySlipResponseDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayEntitlementResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.SalaryStructureResponseDto;
import in.kpmg.hrms.payroll.dtos.responseDto.CommonDropdownProjection;
import in.kpmg.hrms.payroll.mapper.EmpSalaryProcessingMapper;
import in.kpmg.hrms.payroll.mapper.EmployeeDetailsToDtoMapper;
import in.kpmg.hrms.payroll.mapper.FyMonthHoaMapper;
import in.kpmg.hrms.payroll.mapper.SalarySlipMapper;
import in.kpmg.hrms.payroll.mapper.SalaryStructureMapper;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;
import in.kpmg.hrms.payroll.models.EmpPayEntitlementOptions;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingDetails;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingMst;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;
import in.kpmg.hrms.payroll.models.PayrollFyMonthStatusMst;
import in.kpmg.hrms.payroll.models.PayrollFyPaymonthMst;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;
import in.kpmg.hrms.payroll.models.PayrollHoaOfficeMst;
import in.kpmg.hrms.payroll.repo.EmpPayEntitlementOptionsRepo;
import in.kpmg.hrms.payroll.repo.EmpPayEntitlementRepo;
import in.kpmg.hrms.payroll.repo.EmpSalaryProcessingRepo;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmployeeMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollFyMonthStatusRepo;
import in.kpmg.hrms.payroll.repo.PayrollFyPaymonthMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollGroupMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollHoaOfficeRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayheadMstRepo;
import in.kpmg.hrms.payroll.utils.MonthToDayUtlity;
import in.kpmg.hrms.payroll.utils.NumberToWordsConverter;


@Service
public class SalaryBillService {

	@Autowired
	FyMonthHoaMapper fyMonthHoaMapper;

	@Autowired
	private PayrollEmployeeMasterRepo payrollEmployeeMasterRepo;

	@Autowired
	private EmpSalaryProcessingRepo empSalaryProcessingRepo;

	@Autowired
	private PayrollFyPaymonthMstRepo payrollFyPaymonthMstRepo;

	@Autowired
	private PayrollPayheadMstRepo payrollPayheadMstRepo;

	@Autowired
	private GeneralMstRepo generalMstRepo;

	@Autowired
	PayrollFyMonthStatusRepo payrollFyMonthStatusRepo;

	@Autowired
	EmpSalaryProcessingMapper empSalaryProcessingMapper;

	@Autowired
	PayrollHoaOfficeRepo payrollHoaOfficeRepo;

	@Autowired
	EmpPayEntitlementRepo empPayEntitlementRepo;

	@Autowired
	EmpPayEntitlementOptionsRepo empPayEntitlementOptionsRepo;


	@Autowired
	Validator validator;


	@Autowired
	PayrollGroupMasterRepo payrollGroupMasterRepo;

	@Autowired
	EmployeeDetailsToDtoMapper employeeDetailsToDtoMapper;

	public interface PayHead {

	}

	public ApiResponse2<?> saveFyMonthHoa(@Valid FyMonthStatusDto fyMonthStatusDto) {

		if (fyMonthStatusDto.getId() != null) {
			Optional<PayrollFyMonthStatusMst> fyMonth = payrollFyMonthStatusRepo.findById(fyMonthStatusDto.getId());
			if (fyMonth.isPresent()) {
				if (payrollFyMonthStatusRepo.findByFyMonthId(fyMonthStatusDto.getFyMonthId(), fyMonthStatusDto.getHoaId()) != null) {
					return new ApiResponse2<>(false, ApiResponseStatus.existedHoaFyMonth, null, HttpStatus.OK.value());
				}
				PayrollFyMonthStatusMst fyMonthHoaMst = fyMonthHoaMapper.ftoToEntity(fyMonthStatusDto);
				fyMonthHoaMst.setId(fyMonthStatusDto.getId());
				payrollFyMonthStatusRepo.save(fyMonthHoaMst);
				return new ApiResponse2<>(true, ApiResponseStatus.updated, fyMonthHoaMst, HttpStatus.OK.value());
			} else {
				return new ApiResponse2<>(false, ApiResponseStatus.noFyMonth, null, HttpStatus.OK.value());
			}
		} else {
			if (payrollFyMonthStatusRepo.findByFyMonthId(fyMonthStatusDto.getFyMonthId(), fyMonthStatusDto.getHoaId()) != null) {
				return new ApiResponse2<>(false, ApiResponseStatus.existedHoaFyMonth, null, HttpStatus.OK.value());
			}
			PayrollFyMonthStatusMst fyMonthHoaMst = fyMonthHoaMapper.ftoToEntity(fyMonthStatusDto);
			payrollFyMonthStatusRepo.save(fyMonthHoaMst);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, fyMonthHoaMst, HttpStatus.OK.value());
		}

	}

	public ApiResponse2<?> getFyMonthsByHoa(FyMonthStatusDto fyMonthStatusDto) {
		List<PayrollFyMonthStatusMst> hoaFyMonthStatus = payrollFyMonthStatusRepo.findByHoaOrFy(fyMonthStatusDto.getHoaId(), fyMonthStatusDto.getFyId());
		if (hoaFyMonthStatus.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		EmpCountWithFyMonthHoa response = new EmpCountWithFyMonthHoa();
		response.setFyMonthHoa(fyMonthHoaMapper.mstToDtoList(hoaFyMonthStatus));

		EmpCountDto counts = payrollEmployeeMasterRepo.findAllocatedEmp(fyMonthStatusDto.getOfficeId());
		response.setAllocatedEmp(counts.getAllocatedEmp());
		response.setUnAllocatedEmp(counts.getUnAllocatedEmp());
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, response, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getGrpWiseCounts(GetGroupsDto getGroupsDto) {

		SalaryProcessResponse groups = new SalaryProcessResponse();
		Optional<PayrollFyMonthStatusMst> fyMonthHoaMapping = payrollFyMonthStatusRepo.findById(getGroupsDto.getFyMonHoa());
		if (!fyMonthHoaMapping.isPresent()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		groups.setFy(fyMonthHoaMapping.get().getFyMonthId().getFyId().getTypeName());
		groups.setCurrentYear(fyMonthHoaMapping.get().getFyMonthId().getCurrentYear().getTypeName());
		groups.setHoa(fyMonthHoaMapping.get().getHoaId().getHoa());
		groups.setMonth(fyMonthHoaMapping.get().getFyMonthId().getMonthId().getMonthDesc());
		groups.setFyMonId(fyMonthHoaMapping.get().getFyMonthId().getId());
		PayrollHoaOfficeMst hoaOffice = payrollHoaOfficeRepo.findByHoa(fyMonthHoaMapping.get().getHoaId().getHoa());
		if (hoaOffice != null) {
			groups.setOfficeId(hoaOffice.getOffice().getOfcId());
			groups.setOfficeName(hoaOffice.getOffice().getOfficeName());
			EmpCountDto empCount = payrollEmployeeMasterRepo.findAllocatedEmp(hoaOffice.getOffice().getOfcId().intValue());
			groups.setUnAllocatedEmp(empCount.getUnAllocatedEmp());
		}

		List<GroupWiseSalaryStatus> totalGroup = empSalaryProcessingRepo.getProccedGrps(fyMonthHoaMapping.get().getFyMonthId().getId(), fyMonthHoaMapping.get().getHoaId().getId());
		if (totalGroup.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		List<GroupWiseSalaryStatus> unproccesedGrp = new ArrayList<>();
		List<GroupWiseSalaryStatus> proccesedGrp = new ArrayList<>();
		for (GroupWiseSalaryStatus grp : totalGroup) {
			if (grp.getAllocatedYN().equals("Y")) {
				proccesedGrp.add(grp);
			} else {
				unproccesedGrp.add(grp);
			}
		}
		groups.setProccesedEmps(proccesedGrp);
		groups.setNotProccessedEmps(unproccesedGrp);
		return new ApiResponse2<>(true, ApiResponseStatus.saved, groups, HttpStatus.OK.value());
	}

	public ApiResponse2<?> saveSalaryBill(List<SaveEmpSalaryBillDto> saveEmpSalaryBillDto) {
		List<EmpSalaryProcessingMst> salaryProcess = new ArrayList<>();
		for (SaveEmpSalaryBillDto dto : saveEmpSalaryBillDto) {
			String tempRefNo = empSalaryProcessingRepo.generateRefNo();

			GeneralMst status = new GeneralMst();
			status.setTypeId(Status.Proccessed.getCode());

			EmpSalaryProcessingMst empSalary = empSalaryProcessingRepo.findByEmpIdAndFyMonthId(dto.getEmpId(), dto.getFyPayMonthId());
			if (empSalary != null) {
				if (empSalary.getStatus().getTypeId() == Status.Drafted.getCode()) {
					EmpSalaryProcessingMst mainMst = empSalaryProcessingMapper.dtoToEntity(dto);
					for (EmpSalaryProcessingDetails innerMst : mainMst.getEmpSalary()) {
						innerMst.setEmpSalaryId(mainMst);
					}
					mainMst.setId(empSalary.getId());
					mainMst.setStatus(status);
					mainMst.setTempRefNo(tempRefNo);
					salaryProcess.add(empSalary);
				} else {
					return new ApiResponse2<>(false, ApiResponseStatus.alreadyProcessed, null, HttpStatus.OK.value());
				}
			} else {
				EmpSalaryProcessingMst mainMst = empSalaryProcessingMapper.dtoToEntity(dto);
				mainMst.setTempRefNo(tempRefNo);
				mainMst.setStatus(status);
				for (EmpSalaryProcessingDetails innerMst : mainMst.getEmpSalary()) {
					innerMst.setEmpSalaryId(mainMst);
				}
				salaryProcess.add(mainMst);
			}

		}
		if (!salaryProcess.isEmpty()) {
			empSalaryProcessingRepo.saveAll(salaryProcess);
			return new ApiResponse2<>(true, ApiResponseStatus.saved, null, HttpStatus.OK.value());
		}

		return null;
	}

	private void validatePayHeadConfig(SaveEmpSalaryBillDto dto, Errors errors) {
		ValidationUtils.invokeValidator(validator, dto, errors, PayHead.class);
	}

	public ApiResponse2<Object> sendErrors(Errors errors) {
		List<String> errorString = new ArrayList<>();
		for (FieldError fieldError : errors.getFieldErrors()) {
			errorString.add(fieldError.getDefaultMessage());

		}
		return new ApiResponse2<>(false, ApiResponseStatus.validationErrors, errorString, HttpStatus.BAD_REQUEST.value());

	}

	public ApiResponse2<?> findEmpByGrpCode(GetEmpDto getEmp) {
		ProcessEmployee empDetails = new ProcessEmployee();
		Optional<PayrollFyMonthStatusMst> fyMonthHoaMapping = payrollFyMonthStatusRepo.findById(getEmp.getFyMonHoa());
		if (!fyMonthHoaMapping.isPresent()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		empDetails.setFy(fyMonthHoaMapping.get().getFyMonthId().getFyId().getTypeName());
		empDetails.setCurrentYear(fyMonthHoaMapping.get().getFyMonthId().getCurrentYear().getTypeName());
		empDetails.setHoa(fyMonthHoaMapping.get().getHoaId().getHoa());
		empDetails.setMonth(fyMonthHoaMapping.get().getFyMonthId().getMonthId().getMonthDesc());

		PayrollHoaOfficeMst hoaOffice = payrollHoaOfficeRepo.findByHoa(fyMonthHoaMapping.get().getHoaId().getHoa());
		if (hoaOffice != null) {
			empDetails.setOfficeId(hoaOffice.getOffice().getOfcId());
			empDetails.setOfficeName(hoaOffice.getOffice().getOfficeName());
			EmpCountDto empCount = payrollEmployeeMasterRepo.findAllocatedEmp(hoaOffice.getOffice().getOfcId().intValue());
			empDetails.setUnAllocatedEmp(empCount.getUnAllocatedEmp());
		}
		PayrollGroupMaster group = payrollGroupMasterRepo.findByGrpCode(getEmp.getGrpCode());
		if (group == null) {
			return new ApiResponse2<>(false, ApiResponseStatus.noGroup, null, HttpStatus.OK.value());
		}
		empDetails.setGrpName(group.getGrpName());
		List<EmpSalaryProcess> employees = payrollEmployeeMasterRepo.findByGrpIdAndFyMon(fyMonthHoaMapping.get().getFyMonthId().getId(), group.getGrpId());
		if (employees.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		List<EmpPayHeadDetailsResponse> emps = changeInterfaceToDto(employees);
		for (EmpPayHeadDetailsResponse emp : emps) {
			emp.setPayHeads(empPayEntitlementRepo.findPayHeadsByEmpId(emp.getEmpId(), fyMonthHoaMapping.get().getFyMonthId().getId()));


		}
		empDetails.setEmployees(emps);

		return new ApiResponse2<>(false, ApiResponseStatus.fetch, empDetails, HttpStatus.OK.value());
	}

	private List<EmpPayHeadDetailsResponse> changeInterfaceToDto(List<EmpSalaryProcess> employees) {
		List<EmpPayHeadDetailsResponse> response = new ArrayList<>();
		for (EmpSalaryProcess emp : employees) {
			if (emp.getPayheadRefNo() == null) {
				response.add(employeeDetailsToDtoMapper.interfaceToDto(emp));
			}

		}
		return response;
	}


	public ResponseEntity<?> financialYearDropdown() {
		List<CommonDropdownProjection> financialYearList = this.generalMstRepo.findByTypeIdDesc(EmployeeConstants.FINANCIAL_YEAR);

		return ResponseEntity.ok(financialYearList);
	}

	public ResponseEntity<?> fyMonthDropdown(Long financialYear) {

		List<PayrollFyPaymonthMst> fyPaymonthMstList = this.payrollFyPaymonthMstRepo.findAllByFyId_TypeIdAndFyId_IsActiveIsTrueAndMonthId_IsActiveIsTrueAndCurrentYear_IsActiveIsTrueAndIsActiveIsTrueOrderByCurrentYear_TypeNameAscMonthId_PaymonthNoAsc(financialYear);

		List<CommonDropdownResDto> fyMonthList = fyPaymonthMstList.stream().map(value -> new CommonDropdownResDto(value.getId(), value.getMonthId().getMonthDesc() + " " + value.getCurrentYear().getTypeName()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(fyMonthList);
	}

	public ResponseEntity<?> fetchSalaryBillDetails(SalarySlipReqDto salarySlipReqDto) {

		PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findById(salarySlipReqDto.getEmpId()).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with employee code: " + salarySlipReqDto.getEmpId()));

		EmpSalaryProcessingMst empSalaryProcessingMst = this.empSalaryProcessingRepo.findByEmpId_EmpIdAndFyPayMonthId_IdAndFyPayMonthId_IsActiveIsTrue(salarySlipReqDto.getEmpId(), salarySlipReqDto.getFyPaymonthId()).orElseThrow(() -> new EntityNotFoundException("Salary Details not found for employee with employee code: " + salarySlipReqDto.getEmpId() + " and financial year pay month id: " + salarySlipReqDto.getFyPaymonthId()));

		SalarySlipResponseDto salarySlipResponseDto = SalarySlipMapper.INSTANCE.toDto(payrollEmployeeMaster);

		List<PayheadResDto> payheadResDtoList = SalarySlipMapper.INSTANCE.mapPayheadResDtoList(empSalaryProcessingMst.getEmpSalary());


		// Group by TypeDesc
		Map<Long, List<PayheadResDto>> groupedProjections = payheadResDtoList.stream()
				.collect(Collectors.groupingBy(PayheadResDto::getPayheadTypeId));

		//        System.out.println(groupedProjections);

		Function<List<PayheadResDto>, List<PayheadResDto>> sortAndTransform = list -> list.stream()
				.sorted(Comparator.comparing(PayheadResDto::getPayheadName))
				.collect(Collectors.toList());

		//        System.out.println(empPayEntitlementList);

		salarySlipResponseDto.setPaymentPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_PAYMENT, Collections.emptyList())));
		salarySlipResponseDto.setRecoveryPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_RECOVERY, Collections.emptyList())));
		salarySlipResponseDto.setDeductionPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_DEDUCTION, Collections.emptyList())));

		AtomicLong grossEarning = new AtomicLong();
		AtomicLong grossDeduction = new AtomicLong();
		AtomicLong basicRate = new AtomicLong();

		salarySlipResponseDto.getPaymentPayhead().forEach(value -> {
			grossEarning.addAndGet(value.getPayheadValue());

			if (value.getPayheadId() == EmployeeConstants.BASIC_PAYHEAD_ID)
				basicRate.set(value.getPayheadValue());
		});

		salarySlipResponseDto.setBasicRate(basicRate.get());

		salarySlipResponseDto.getDeductionPayhead().forEach(value -> {
			grossDeduction.addAndGet(value.getPayheadValue());
		});

		long netPay = grossEarning.get() - grossDeduction.get();
		String netPayInWords = NumberToWordsConverter.convertToIndianCurrency(String.valueOf(netPay));

		salarySlipResponseDto.setGrossEarning(grossEarning.get());
		salarySlipResponseDto.setGrossDeduction(grossDeduction.get());
		salarySlipResponseDto.setNetPayInWords(netPayInWords);
		salarySlipResponseDto.setNetPay(netPay);


		return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_SALARY_SLIP_DETAILS_MESSAGE, salarySlipResponseDto, HttpStatus.OK.value()));
	}

	public ResponseEntity<?> fetchEmployeePayEntitlementDetails(Long empId) {

		PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findById(empId).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with employee code: " + empId));

		EmpPayEntitlementOptions empPayEntitlementOptions = this.empPayEntitlementOptionsRepo.findByEmpId_EmpId(empId).orElseThrow(() -> new EntityNotFoundException("Pay Entitlement Details not found with emp id: " + empId));

		List<EmpPayEntitlement> empPayEntitlementList = this.empPayEntitlementRepo.findAllByPayheadEntitlementId_IdOrderByPayheadId_PayheadName(empPayEntitlementOptions.getId());

		SalaryStructureResponseDto salaryStructureResponseDto = SalaryStructureMapper.INSTANCE.toDto(payrollEmployeeMaster);

		List<PayheadResDto> payheadResDtoList = SalaryStructureMapper.INSTANCE.mapPayheadResDtoList(empPayEntitlementList);


		// Group by TypeDesc
		Map<Long, List<PayheadResDto>> groupedProjections = payheadResDtoList.stream()
				.collect(Collectors.groupingBy(PayheadResDto::getPayheadTypeId));

		//        System.out.println(groupedProjections);

		Function<List<PayheadResDto>, List<PayheadResDto>> sortAndTransform = list -> list.stream()
				.sorted(Comparator.comparing(PayheadResDto::getPayheadName))
				.collect(Collectors.toList());

		//        System.out.println(empPayEntitlementList);

		salaryStructureResponseDto.setPaymentPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_PAYMENT, Collections.emptyList())));
		salaryStructureResponseDto.setRecoveryPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_RECOVERY, Collections.emptyList())));
		salaryStructureResponseDto.setDeductionPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_DEDUCTION, Collections.emptyList())));

		AtomicLong grossEarning = new AtomicLong();
		AtomicLong grossDeduction = new AtomicLong();

		salaryStructureResponseDto.getPaymentPayhead().forEach(value -> {
			grossEarning.addAndGet(value.getPayheadValue());
		});

		salaryStructureResponseDto.getDeductionPayhead().forEach(value -> {
			grossDeduction.addAndGet(value.getPayheadValue());
		});

		long netPay = grossEarning.get() - grossDeduction.get();
		String netPayInWords = NumberToWordsConverter.convertToIndianCurrency(String.valueOf(netPay));

		salaryStructureResponseDto.setGrossEarning(grossEarning.get());
		salaryStructureResponseDto.setGrossDeduction(grossDeduction.get());
		salaryStructureResponseDto.setNetPayInWords(netPayInWords);
		salaryStructureResponseDto.setNetPay(netPay);


		return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_SALARY_STRUCTURE_DETAILS_MESSAGE, salaryStructureResponseDto, HttpStatus.OK.value()));
	}


	public ResponseEntity<?> fetchRegularSalaryProcessing(RegularSalaryProcessReqDto regularSalaryProcessReqDto) {
		PayrollFyPaymonthMst payrollFyPaymonthMst = this.payrollFyPaymonthMstRepo.findByIdAndIsActiveIsTrue(regularSalaryProcessReqDto.getFyPayMonthId()).orElseThrow(() -> new EntityNotFoundException("Financial Year Pay Month not found with pay month id: " + regularSalaryProcessReqDto.getFyPayMonthId()));

		String monthName = payrollFyPaymonthMst.getMonthId().getMonthDesc();
		String year = payrollFyPaymonthMst.getCurrentYear().getTypeName();

		int daysInMonth = MonthToDayUtlity.getDaysInMonth(monthName, Integer.parseInt(year));

		int noOfPaidDays = daysInMonth - regularSalaryProcessReqDto.getLossOfPayInDays();

		List<PayEntitlementResDto> regularSalaryProcessing = this.payrollPayheadMstRepo.getRegularSalaryProcessing(regularSalaryProcessReqDto.getEmpId(), regularSalaryProcessReqDto.getFyPayMonthId(), regularSalaryProcessReqDto.getLossOfPayInDays());

		List<PayheadResDto> payheadResDtoList = regularSalaryProcessing.stream().map((value) -> {
			PayheadResDto payheadResDto = new PayheadResDto();
			payheadResDto.setPayheadId(value.getPayheadId().intValue());
			payheadResDto.setPayheadValue(Long.parseLong(value.getAmount()));
			payheadResDto.setPayheadType(new CommonDropdownResDto(value.getPayheadTypeId().longValue(), value.getPayheadType()));
			payheadResDto.setPayheadConfigId(value.getPayheadConfigId()!=null ? value.getPayheadConfigId().intValue() : null);
			payheadResDto.setPayheadName(value.getPayheadName());

			return payheadResDto;
		}).collect(Collectors.toList());

		// Group by TypeDesc
		Map<Long, List<PayheadResDto>> groupedProjections = payheadResDtoList.stream()
				.collect(Collectors.groupingBy(PayheadResDto::getPayheadTypeId));

		//        System.out.println(groupedProjections);

		Function<List<PayheadResDto>, List<PayheadResDto>> sortAndTransform = list -> list.stream()
				.sorted(Comparator.comparing(PayheadResDto::getPayheadName))
				.collect(Collectors.toList());

		//        System.out.println(empPayEntitlementList);

		HashMap<String, Object> response = new HashMap<>();

		response.put("noOfPaidDays", noOfPaidDays);
		response.put("paymentPayhead", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_PAYMENT, Collections.emptyList())));
		response.put("recoveryPayhead", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_RECOVERY, Collections.emptyList())));
		response.put("deductionPayhead", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_DEDUCTION, Collections.emptyList())));


		return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_SALARY_PROCESS_DETAILS_MESSAGE, response, HttpStatus.OK.value()));
	}

	public ApiResponse2<?> processGroupSalary(@Valid GroupSalaryProcessDto groupSalaryProcessDto) {
		List<PayrollEmployeeMaster> emps = payrollEmployeeMasterRepo.findByGrpId(groupSalaryProcessDto.getGrpCode());
		if(emps.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		List<Long> empIds = new ArrayList<>();
		emps.forEach((emp)-> empIds.add(emp.getEmpId()));
		PayrollFyPaymonthMst payrollFyPaymonthMst = this.payrollFyPaymonthMstRepo.findByIdAndIsActiveIsTrue(groupSalaryProcessDto.getFyMonId()).orElseThrow(() -> new EntityNotFoundException("Financial Year Pay Month not found with pay month id: " + groupSalaryProcessDto.getFyMonId()));

		String monthName = payrollFyPaymonthMst.getMonthId().getMonthDesc();
		String year = payrollFyPaymonthMst.getCurrentYear().getTypeName();

		Integer daysInMonth = MonthToDayUtlity.getDaysInMonth(monthName, Integer.parseInt(year));
		List<Long> proccesedEmps = empSalaryProcessingRepo.findUnproccesedEmp(groupSalaryProcessDto.getFyMonId(),empIds );
		if(proccesedEmps.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.alreadyProccesed, null, HttpStatus.OK.value());
		}
		
		empIds.removeAll(proccesedEmps);
		

		List<SaveEmpSalaryBillDto> salaryBillDto = new ArrayList<>();
		for(Long emp : empIds) {
			SaveEmpSalaryBillDto salary = new SaveEmpSalaryBillDto();
			salary.setEmpId(emp);
			salary.setFyPayMonthId(groupSalaryProcessDto.getFyMonId());
			salary.setPaidDays(daysInMonth);
			
			List<EmpPayEntitlement> empEnt = empPayEntitlementRepo.findPayHeadsByEmpId(emp );
			List<SaveEmpSalaryPayHeadsDto> empSalary = empEnt.stream().map((value)->{
				SaveEmpSalaryPayHeadsDto payHeads = new SaveEmpSalaryPayHeadsDto();
				payHeads.setPayheadConfigId(value.getPayheadConfigId()!= null ? value.getPayheadConfigId().getPayheadConfigId(): null);
				payHeads.setPayheadId(value.getPayheadId().getPayheadId());
				payHeads.setPayheadValue(value.getPayheadValue());
				return payHeads;
			}).collect(Collectors.toList());
			salary.setEmpSalary(empSalary);
			salaryBillDto.add(salary);
		}
		
		return saveSalaryBill(salaryBillDto);
	}

	public ApiResponse2<?> getEmployeeReport(@Valid EmployeeReportSearchDto employeeReportSearchDto) {
		List<EmployeeSalaryReportResponse> report = payrollHoaOfficeRepo.getReport(employeeReportSearchDto.getDeptId(), employeeReportSearchDto.getOfficeIds(),employeeReportSearchDto.getPayMontId());
		if (report.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		EmployeesSalaryReport empCountsReport = new EmployeesSalaryReport();
		empCountsReport.setReport(report);
		empCountsReport.setTotalCount(report.stream().mapToInt(value -> value.getTotalEmp()).sum());
		empCountsReport.setInitiatedCount(report.stream().mapToInt(value -> value.getProcessedCount()).sum());
		empCountsReport.setNotInitiatedCount(report.stream().mapToInt(value -> value.getNotDelayedCount()).sum());
		empCountsReport.setDelayedCount(report.stream().mapToInt(value -> value.getDelayedCount()).sum());;
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, empCountsReport, HttpStatus.OK.value());
	}


}
