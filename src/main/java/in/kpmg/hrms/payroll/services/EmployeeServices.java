package in.kpmg.hrms.payroll.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.kpmg.hrms.payroll.constants.EmployeeConstants;
import in.kpmg.hrms.payroll.dtos.ApiResponse2;
import in.kpmg.hrms.payroll.dtos.ApiResponseStatus;
import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import in.kpmg.hrms.payroll.dtos.DropdownResponse;
import in.kpmg.hrms.payroll.dtos.MappingDropdownResDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SearchEmployesDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.BasicPayReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EducationRequestDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EducationalDtlsDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpFamilyDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmpPayEntitlementOptionsReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeDocumentsDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeDocumentsReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.EmployeeReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.FamilyRequestDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.OfficeDropdownReqDto;
import in.kpmg.hrms.payroll.dtos.employee.requestDto.PayEntitlementReqDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.CadreDropdownResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpBankDtlsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpDisabilityResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpDocumentsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpEducationalDtlsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpFamilyResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmpMstResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EmployeeMstRes;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EntitlementEmpRes;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.EntitlementEmpResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.OfficeDropdownResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayEntitlementOptionsResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayEntitlementResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.PayheadResDto;
import in.kpmg.hrms.payroll.dtos.employee.responseDto.SavedEmployeeResDto;
import in.kpmg.hrms.payroll.dtos.responseDto.CommonDropdownProjection;
import in.kpmg.hrms.payroll.dtos.responseDto.EmployeeDropdownDto;
import in.kpmg.hrms.payroll.exceptions.BadRequestException;
import in.kpmg.hrms.payroll.exceptions.NotFoundException;
import in.kpmg.hrms.payroll.exceptions.UnauthorizedException;
import in.kpmg.hrms.payroll.mapper.EducationalDtlsMapper;
import in.kpmg.hrms.payroll.mapper.EmpFamilyMapper;
import in.kpmg.hrms.payroll.mapper.EmpMstMapper;
import in.kpmg.hrms.payroll.mapper.EmployeeDocumentsMapper;
import in.kpmg.hrms.payroll.mapper.PayEntitlementMapper;
import in.kpmg.hrms.payroll.models.Department;
import in.kpmg.hrms.payroll.models.EmpPayEntitlement;
import in.kpmg.hrms.payroll.models.EmpPayEntitlementOptions;
import in.kpmg.hrms.payroll.models.EmployeeDocuments;
import in.kpmg.hrms.payroll.models.PayrollBandServiceTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollCadreMaster;
import in.kpmg.hrms.payroll.models.PayrollDesignationDeptMapping;
import in.kpmg.hrms.payroll.models.PayrollEducationalDetails;
import in.kpmg.hrms.payroll.models.PayrollEmpFamily;
import in.kpmg.hrms.payroll.models.PayrollEmployeeDisability;
import in.kpmg.hrms.payroll.models.PayrollEmployeeMaster;
import in.kpmg.hrms.payroll.models.PayrollGroupMaster;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;
import in.kpmg.hrms.payroll.models.PayrollLocationMaster;
import in.kpmg.hrms.payroll.models.PayrollOfficeMaster;
import in.kpmg.hrms.payroll.models.PayrollPayCommisionServTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollPayMatrix;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;
import in.kpmg.hrms.payroll.models.RoleMst;
import in.kpmg.hrms.payroll.models.UserMst;
import in.kpmg.hrms.payroll.models.UserRoleMappingMst;
import in.kpmg.hrms.payroll.repo.DepartmentRepo;
import in.kpmg.hrms.payroll.repo.EmpPayEntitlementOptionsRepo;
import in.kpmg.hrms.payroll.repo.EmpPayEntitlementRepo;
import in.kpmg.hrms.payroll.repo.EmployeeDocumentsRepo;
import in.kpmg.hrms.payroll.repo.GeneralMstRepo;
import in.kpmg.hrms.payroll.repo.GeneralTypeMstRepo;
import in.kpmg.hrms.payroll.repo.PayMatrixRepo;
import in.kpmg.hrms.payroll.repo.PayrollBandServiceTypeMappingRepo;
import in.kpmg.hrms.payroll.repo.PayrollCadreMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollDesignationDeptRepo;
import in.kpmg.hrms.payroll.repo.PayrollEducationalDtlsRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmpFamilyRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmployeeDisabilityRepo;
import in.kpmg.hrms.payroll.repo.PayrollEmployeeMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollGroupMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollHraRateRepo;
import in.kpmg.hrms.payroll.repo.PayrollLocationMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollOfficeMasterRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayCommisionServTypeRepo;
import in.kpmg.hrms.payroll.repo.PayrollPayheadMstRepo;
import in.kpmg.hrms.payroll.repo.PayrollServiceTypeRepo;
import in.kpmg.hrms.payroll.repo.UserRepo;
import in.kpmg.hrms.payroll.repo.UserRoleMapRepo;
import in.kpmg.hrms.payroll.utils.EmployeeUtility;
import in.kpmg.hrms.payroll.utils.RoleUtility;
import in.kpmg.hrms.payroll.utils.UploadRealPathUtility;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeServices {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRoleMapRepo userRoleMapRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GeneralMstRepo generalMstRepo;

    @Autowired
    private GeneralTypeMstRepo generalTypeMstRepo;

    @Autowired
    private PayrollServiceTypeRepo payrollServiceTypeRepo;

    @Autowired
    private PayrollCadreMasterRepo payrollCadreMasterRepo;

    @Autowired
    private PayrollEmployeeMasterRepo payrollEmployeeMasterRepo;

    @Autowired
    private PayrollGroupMasterRepo payrollGroupMasterRepo;

    @Autowired
    private PayrollOfficeMasterRepo payrollOfficeMasterRepo;

    @Autowired
    private PayrollLocationMasterRepo payrollLocationMasterRepo;

    @Autowired
    private PayrollDesignationDeptRepo payrollDesignationDeptRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    @Autowired
    private PayrollDesignationDeptRepo deptDesignMappingRepo;

    @Autowired
    private PayrollEmployeeDisabilityRepo payrollEmployeeDisabilityRepo;

    @Autowired
    private PayrollEmpFamilyRepo payrollEmpFamilyRepo;

    @Autowired
    private PayrollEducationalDtlsRepo payrollEducationalDtlsRepo;

    @Autowired
    private UploadRealPathUtility uploadRealPathUtility;

    @Autowired
    private PayrollPayheadMstRepo payrollPayheadMstRepo;

    @Autowired
    private PayrollHraRateRepo payrollHraRateRepo;

    @Autowired
    private EmpPayEntitlementOptionsRepo empPayEntitlementOptionsRepo;

    @Autowired
    private EmpPayEntitlementRepo empPayEntitlementRepo;

    @Autowired
    private EmployeeDocumentsRepo employeeDocumentsRepo;

    @Autowired
    private PayrollPayCommisionServTypeRepo payrollPayCommisionServTypeRepo;

    @Autowired
    private PayMatrixRepo payMatrixRepo;

    @Autowired
    private PayrollBandServiceTypeMappingRepo payrollBandServiceTypeMappingRepo;

    @Autowired
    private RoleUtility roleUtility;

//    public ResponseEntity<?> dropdownInit() {
//        HashMap<String, Object> response = new HashMap<>();
//
//        //prefix
//        List<GeneralMst> prefixDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Prefix");
//
//        List<CommonDropdownResDto> prefixList = prefixDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        response.put("prefix", prefixList);
//
//        //blood group
//        List<GeneralMst> bloodGroupDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Blood Group");
//
//        List<CommonDropdownResDto> bloodGroupList = bloodGroupDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).collect(Collectors.toList());
//
//        //employee type
//        List<GeneralMst> empTypeDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Employee Type mst");
//
//        List<CommonDropdownResDto> empTypeList = empTypeDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //gender
//        List<GeneralMst> genderDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Gender mst");
//
//        List<CommonDropdownResDto> genderList = genderDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //caste
//        List<GeneralMst> casteDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Caste master");
//
//        List<CommonDropdownResDto> casteList = casteDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //marital status
//        List<GeneralMst> maritalStatusDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Marital status");
//
//        List<CommonDropdownResDto> maritalStatusList = maritalStatusDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //category
//        List<GeneralMst> categoryDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Category master");
//
//        List<CommonDropdownResDto> categoryList = categoryDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //type of disability
//        List<GeneralMst> todDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Types of Disability");
//
//        List<CommonDropdownResDto> todList = todDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //nationality
//        List<GeneralMst> nationalityDb = this.generalMstRepo.findByGeneralTypeId_TypeDescAndGeneralTypeId_IsActiveIsTrueAndIsActiveIsTrue("Nationality");
//
//        List<CommonDropdownResDto> nationalityList = nationalityDb.stream()
//                .map(value -> new CommonDropdownResDto(value.getTypeId(), value.getTypeName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0)).collect(Collectors.toList());
//
//        //department
//        List<Department> departmentDb = this.departmentRepo.findAllByIsActiveIsTrue();
//
//        List<CommonDropdownResDto> departmentList = departmentDb.stream()
//                .map(value -> new CommonDropdownResDto((long) value.getDeptId(), value.getName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).collect(Collectors.toList());
//
//        response.put("prefix", prefixList);
//        response.put("bloodGroup", bloodGroupList);
//        response.put("employeeType", empTypeList);
//        response.put("gender", genderList);
//        response.put("caste", casteList);
//        response.put("maritalStatus", maritalStatusList);
//        response.put("category", categoryList);
//        response.put("typesOfDisability", todList);
//        response.put("nationality", nationalityList);
//        response.put("department", departmentList);
//
//        return ResponseEntity.ok(response);
//    }

    public ResponseEntity<?> dropdownInit() {
        HashMap<String, Object> response = new HashMap<>();

        // List of required type descriptions
        List<Long> typeIds = new ArrayList<>(EmployeeConstants.TYPE_DESC_LIST_DROPDOWN);

        // Fetching necessary GeneralMst records
        List<CommonDropdownProjection> dropdownProjections = this.generalMstRepo.findByTypeIds(typeIds);

        // Group by TypeDesc
        Map<Long, List<CommonDropdownProjection>> groupedProjections = dropdownProjections.stream()
                .collect(Collectors.groupingBy(CommonDropdownProjection::getGeneralType));

        // sorting and transforming lists
//        Function<List<CommonDropdownProjection>, List<CommonDropdownResDto>> sortAndTransform = list -> list.stream()
//                .map(value -> new CommonDropdownResDto(value.getId(), value.getLabel()))
//                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
//                .sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0))
//                .collect(Collectors.toList());

        Function<List<CommonDropdownProjection>, List<CommonDropdownProjection>> sortAndTransform = list -> list.stream()
                .sorted(Comparator.comparing(CommonDropdownProjection::getLabel))
                .sorted(Comparator.comparing(projection -> projection.getLabel().equals("Other") ? 1 : 0))
                .collect(Collectors.toList());

        // general mst dropdowns
        response.put("prefix", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_PREFIX, Collections.emptyList())));
        response.put("bloodGroup", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_BLOOD_GROUP, Collections.emptyList())));
        response.put("employeeType", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_EMPLOYEE_TYPE, Collections.emptyList())));
        response.put("gender", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_GENDER, Collections.emptyList())));
        response.put("caste", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_CASTE, Collections.emptyList())));
        response.put("maritalStatus", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_MARITAL_STATUS, Collections.emptyList())));
        response.put("socialCategory", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_SOCIAL_CATEGORY, Collections.emptyList())));
        response.put("typesOfDisability", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_TYPES_OF_DISABILITY, Collections.emptyList())));
        response.put("nationality", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_NATIONALITY, Collections.emptyList())));
        response.put("religion", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_RELIGION, Collections.emptyList())));
        response.put("relationship", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_RELATIONSHIP, Collections.emptyList())));
        response.put("sourceOfRecruitment", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_SOURCE_OF_RECRUITMENT, Collections.emptyList())));
        response.put("payslipAuthority", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_PAYSLIP_AUTHORITY, Collections.emptyList())));
        response.put("gpfPranType", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_PAYSLIP_GPF_PRAN_TYPE, Collections.emptyList())));
        response.put("joiningTime", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_JOINING_TIME, Collections.emptyList())));
        response.put("heightCmFeet", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_HEIGHT_CM_FEET, Collections.emptyList())));
//        response.put("groupType", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_GROUP_TYPE, Collections.emptyList())));
        response.put("quarterType", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_QUARTER_TYPE, Collections.emptyList())));

        // Fetch Department records
        List<Department> departmentDb = this.departmentRepo.findAllByIsActiveIsTrue();
        List<CommonDropdownResDto> departmentList = departmentDb.stream()
                .map(value -> new CommonDropdownResDto((long) value.getDeptId(), value.getName()))
                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
                .collect(Collectors.toList());

        response.put("department", departmentList);

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> groupByOffice(Long officeId) {
        //group master
        List<PayrollGroupMaster> payrollGroupMasterList = this.payrollGroupMasterRepo.findAllByOfficeId_OfcIdAndIsActiveIsTrue(officeId);
        List<CommonDropdownResDto> groupList = payrollGroupMasterList.stream()
                .map(value -> new CommonDropdownResDto(value.getGrpId(), value.getGrpName()))
                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
                .collect(Collectors.toList());

        return ResponseEntity.ok(groupList);
    }

    public ResponseEntity<?> educationalDtlsDropdownInit() {
        HashMap<String, Object> response = new HashMap<>();

        // List of required type descriptions
        List<Long> typeIds = new ArrayList<>(EmployeeConstants.EDUCATIONAL_DETAILS_LIST_DROPDOWN);

        // Fetching necessary GeneralMst records
        List<CommonDropdownProjection> dropdownProjections = this.generalMstRepo.findByTypeIds(typeIds);

        // Group by TypeDesc
        Map<Long, List<CommonDropdownProjection>> groupedProjections = dropdownProjections.stream()
                .collect(Collectors.groupingBy(CommonDropdownProjection::getGeneralType));

        // sorting and transforming lists
//        Function<List<CommonDropdownProjection>, List<CommonDropdownResDto>> sortAndTransform = list -> list.stream()
//                .map(value -> new CommonDropdownResDto(value.getId(), value.getLabel()))
//                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
//                .sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0))
//                .collect(Collectors.toList());

        Function<List<CommonDropdownProjection>, List<CommonDropdownProjection>> sortAndTransform = list -> list.stream()
                .sorted(Comparator.comparing(CommonDropdownProjection::getLabel))
                .collect(Collectors.toList());

        // general mst dropdowns
        response.put("qualification", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_QUALIFICATION, Collections.emptyList())));
        response.put("marksCgpa", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_MARKS_CGPA, Collections.emptyList())));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> stateDropdownInit() {
        List<PayrollLocationMaster> stateDb = this.payrollLocationMasterRepo.findAllByLocTypeId_TypeIdAndLocTypeId_IsActiveIsTrueAndIsActiveIsTrue(EmployeeConstants.STATE_TYPE_ID);

        return ResponseEntity.ok(stateDb.stream().map(value -> new CommonDropdownResDto(value.getLocId(), value.getLocName())).collect(Collectors.toList()));
    }

    public ResponseEntity<?> districtDropdownByState(Long stateId) {
        List<PayrollLocationMaster> stateDb = this.payrollLocationMasterRepo.findAllByLocParentId_LocIdAndLocParentId_IsActiveIsTrueAndAndIsActiveIsTrue(stateId);

        return ResponseEntity.ok(stateDb.stream().map(value -> new CommonDropdownResDto(value.getLocId(), value.getLocName())).collect(Collectors.toList()));
    }

    public ResponseEntity<?> officeDropdown(OfficeDropdownReqDto officeDropdownReqDto) {
        List<PayrollOfficeMaster> payrollOfficeMasterList = this.payrollOfficeMasterRepo.findByConditions(officeDropdownReqDto.getDeptId(), officeDropdownReqDto.getStateId(), officeDropdownReqDto.getDistrictId(), officeDropdownReqDto.getOfficeName());

        return ResponseEntity.ok(payrollOfficeMasterList.stream().map(payrollOfficeMaster -> new OfficeDropdownResDto(payrollOfficeMaster.getOfcId(), payrollOfficeMaster.getOfficeName(), payrollOfficeMaster.getAddressLine1())).collect(Collectors.toList()));
    }

    public ResponseEntity<?> designationByDepartmentDropdown(Integer deptId) {
        List<PayrollDesignationDeptMapping> designationDb = this.deptDesignMappingRepo.findAllByDeptId_DeptIdAndDeptId_IsActiveIsTrueAndIsActiveIsTrue(deptId);

        List<CommonDropdownResDto> designationList = designationDb.stream().map((value) -> new CommonDropdownResDto((long) value.getDsgnId().getId(), value.getDsgnId().getName())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).collect(Collectors.toList());

        return ResponseEntity.ok(designationList);
    }

    public ResponseEntity<?> serviceTypeByEmployeeType() {
        List<PayrollServiceTypeMaster> serviceTypeMasterDb = this.payrollServiceTypeRepo.findAllByIsActiveIsTrueOrderByServiceType();

        List<CommonDropdownResDto> serviceTypeMasterList = serviceTypeMasterDb.stream().map((value) -> new CommonDropdownResDto((long) value.getId(), value.getServiceType())).sorted(Comparator.comparing(CommonDropdownResDto::getLabel)).collect(Collectors.toList());

        return ResponseEntity.ok(serviceTypeMasterList);
    }

    public ResponseEntity<?> cadreByServiceType(Integer serviceTypeId) {
        List<PayrollCadreMaster> cadreMastersDb = this.payrollCadreMasterRepo.findAllByServiceTypeId_IdAndIsActiveIsTrue(serviceTypeId);

        List<CadreDropdownResDto> cadreMastersList = cadreMastersDb.stream().map((value) -> new CadreDropdownResDto((long) value.getId(), value.getCadreName(), value.getRetirementAge())).sorted(Comparator.comparing(CadreDropdownResDto::getLabel)).collect(Collectors.toList());

        return ResponseEntity.ok(cadreMastersList);
    }

    public ResponseEntity<?> payCommissionServiceDropdown(Integer serviceTypeId) {
        // Fetching necessary GeneralMst records
        List<PayrollPayCommisionServTypeMapping> payCommisionServTypeList = this.payrollPayCommisionServTypeRepo.findAllByServTypeId_IdAndServTypeId_IsActiveIsTrueAndPcId_IsActiveIsTrueAndIsActiveIsTrue(serviceTypeId);

        List<MappingDropdownResDto> payCommissionList = payCommisionServTypeList.stream().map(value -> new MappingDropdownResDto(value.getPcId().getTypeId(), value.getPcId().getTypeName(), value.getId())).sorted(Comparator.comparing(MappingDropdownResDto::getLabel))
                .collect(Collectors.toList());

        // List<CommonDropdownProjection> dropdownProjections = this.generalMstRepo.findByTypeId(EmployeeConstants.GENERAL_TYPE_DESC_PAY_COMMISSION);

        // List<CommonDropdownProjection> payCommissionList = dropdownProjections.stream()
        //         .sorted(Comparator.comparing(CommonDropdownProjection::getLabel))
        //         .collect(Collectors.toList());

        return ResponseEntity.ok(payCommissionList);
    }

    public ResponseEntity<?> payLevelByPayCommissionServiceDropdown(Integer pcServMappingId) {
        // Fetching necessary GeneralMst records
        List<PayrollBandServiceTypeMapping> payrollBandServiceTypeMappingList = this.payrollBandServiceTypeMappingRepo.findAllByPcServTypeId_IdAndPcServTypeId_IsActiveIsTrueAndIsActiveIsTrue(pcServMappingId);

        List<MappingDropdownResDto> payLevelList = payrollBandServiceTypeMappingList.stream()
                .map(value -> new MappingDropdownResDto(value.getPayBandId().getTypeId(), value.getPayBandId().getTypeName(), value.getId()))
                .sorted(Comparator.comparing(MappingDropdownResDto::getLabel))
                .collect(Collectors.toList());

        return ResponseEntity.ok(payLevelList);
    }

    public ResponseEntity<?> basicPayByPayLevel(BasicPayReqDto basicPayReqDto) {
        PayrollBandServiceTypeMapping payrollBandServiceTypeMapping = this.payrollBandServiceTypeMappingRepo.findByPayBandId_TypeIdAndPayBandId_IsActiveIsTrueAndPcServTypeId_ServTypeId_IdAndPcServTypeId_ServTypeId_IsActiveIsTrueAndPcServTypeId_PcId_TypeIdAndPcServTypeId_PcId_IsActiveIsTrueAndPcServTypeId_IsActiveIsTrueAndIsActiveIsTrue(basicPayReqDto.getPayLevelId(), basicPayReqDto.getServiceTypeId(), basicPayReqDto.getPayCommissionId()).orElseThrow(() -> new EntityNotFoundException("Pay Band Service Type Mapping not found with pay level id:" + basicPayReqDto.getPayLevelId() + " pay commission id: " + basicPayReqDto.getPayCommissionId() + " service type id: " + basicPayReqDto.getServiceTypeId()));

        List<PayrollPayMatrix> payMatrixList = this.payMatrixRepo.findAllByPayBandIdOrderByValueAsc(payrollBandServiceTypeMapping);

        List<CommonDropdownResDto> basicPayList = payMatrixList.stream().map(value -> new CommonDropdownResDto(value.getId(), value.getValue().toString())).collect(Collectors.toList());

        return ResponseEntity.ok(basicPayList);
    }

    public ResponseEntity<?> basicPayByPayLevelNew(Integer payBandMappingId) {
        PayrollBandServiceTypeMapping payrollBandServiceTypeMapping = this.payrollBandServiceTypeMappingRepo.findAllByIdAndIsActiveIsTrue(payBandMappingId).orElseThrow(() -> new EntityNotFoundException("Pay Band Service Type Mapping not found with pay level mapping id:" + payBandMappingId));

        List<PayrollPayMatrix> payMatrixList = this.payMatrixRepo.findAllByPayBandIdOrderByValueAsc(payrollBandServiceTypeMapping);

        List<CommonDropdownResDto> basicPayList = payMatrixList.stream().map(value -> new CommonDropdownResDto(value.getId(), value.getValue().toString())).collect(Collectors.toList());

        return ResponseEntity.ok(basicPayList);
    }

    public ResponseEntity<?> hraTierDropdown() {
        List<PayrollHRARateMst> payrollHRARateMstList = this.payrollHraRateRepo.findAllByIsActiveIsTrue();

        List<CommonDropdownResDto> hraTierList = payrollHRARateMstList.stream().map(value -> new CommonDropdownResDto((long) value.getTierId(), value.getTierName()))
                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
                .collect(Collectors.toList());

        return ResponseEntity.ok(hraTierList);
    }

    public ResponseEntity<?> ctaEntitlementDropdown() {
        // Fetching necessary GeneralMst records
        List<CommonDropdownProjection> dropdownProjections = this.generalMstRepo.findByTypeId(EmployeeConstants.GENERAL_TYPE_DESC_CTA_ENTITLEMENT);

        List<CommonDropdownProjection> ctaEntitlementDropdown = dropdownProjections.stream()
                .sorted(Comparator.comparing(CommonDropdownProjection::getLabel))
                .collect(Collectors.toList());

        return ResponseEntity.ok(ctaEntitlementDropdown);
    }

    public ResponseEntity<?> documentTypeDropdown() {
        List<CommonDropdownProjection> documentTypeDb = this.generalMstRepo.findByTypeId(EmployeeConstants.GENERAL_TYPE_DESC_DOCUMENT_TYPE);

        return ResponseEntity.ok(documentTypeDb);
    }

    public ResponseEntity<?> entitlementDropdownInit() {
        HashMap<String, Object> response = new HashMap<>();

        // List of required type descriptions
        List<Long> typeIds = new ArrayList<>(EmployeeConstants.ENTITLEMENT_DROPDOWN_INIT);

        // Fetching necessary GeneralMst records
        List<CommonDropdownProjection> dropdownProjections = this.generalMstRepo.findByTypeIds(typeIds);

        // Group by TypeDesc
        Map<Long, List<CommonDropdownProjection>> groupedProjections = dropdownProjections.stream()
                .collect(Collectors.groupingBy(CommonDropdownProjection::getGeneralType));

        // sorting and transforming lists
//        Function<List<CommonDropdownProjection>, List<CommonDropdownResDto>> sortAndTransform = list -> list.stream()
//                .map(value -> new CommonDropdownResDto(value.getId(), value.getLabel()))
//                .sorted(Comparator.comparing(CommonDropdownResDto::getLabel))
//                .sorted(Comparator.comparing(dto -> dto.getLabel().equals("Other") ? 1 : 0))
//                .collect(Collectors.toList());

        Function<List<CommonDropdownProjection>, List<CommonDropdownProjection>> sortAndTransform = list -> list.stream()
                .sorted(Comparator.comparing(CommonDropdownProjection::getLabel))
                .collect(Collectors.toList());

        // general mst dropdowns
        response.put("ctaAllowanceApplicable", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_CTA_ALLOWANCE, Collections.emptyList())));
        response.put("daStop", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_DA_STOP, Collections.emptyList())));
        response.put("medicalStop", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_MEDICAL_STOP, Collections.emptyList())));
        response.put("npsOpted", sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.GENERAL_TYPE_DESC_NPS_OPTED, Collections.emptyList())));

        return ResponseEntity.ok(response);
    }

    public PayrollEmployeeMaster convertToEntity(EmployeeReqDto dto) {
        return EmpMstMapper.INSTANCE.toEntity(dto);
    }

    public EmpMstResDto convertToDto(PayrollEmployeeMaster entity) {
        return EmpMstMapper.INSTANCE.toDto(entity);
    }

    public EmpDisabilityResDto convertEmpDisabilityToDto(PayrollEmployeeDisability entity) {
        return EmpMstMapper.INSTANCE.toEmpDisabilityDto(entity);
    }

    public EmpBankDtlsResDto convertEmpBankDtlsToDto(PayrollEmployeeMaster entity) {
        return EmpMstMapper.INSTANCE.toBankDetailsDto(entity);
    }

    public void convertBankDetailsToEntity(EmployeeReqDto dto, PayrollEmployeeMaster entity) {
        EmpMstMapper.INSTANCE.toEntityBankDetails(dto, entity);
    }

//    public ResponseEntity<?> saveEmployeeMaster(EmployeeReqDto employeeReqDto, Authentication authentication, String saveType) {
//        String username = authentication.getName();
//        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(username).orElseThrow(() -> new EntityNotFoundException("User doesn't exists!"));
//
//        if (!saveType.equals("personal-details") && !saveType.equals("address-details") && !saveType.equals("bank-details"))
//            throw new NotFoundException("Invalid path variable for saving employee onboarding details");
//
////        System.out.println(userMst);
////        System.out.println(employeeReqDto);
//        PayrollEmployeeMaster payrollEmployeeMaster = convertToEntity(employeeReqDto);
//        PayrollEmployeeMaster savedPayrollEmployeeMaster = this.payrollEmployeeMasterRepo.save(payrollEmployeeMaster);
//        EmployeeReqDto employeeReqDto1 = convertToDto(savedPayrollEmployeeMaster);
////        System.out.println(payrollEmployeeMaster);
//
//        return ResponseEntity.ok(employeeReqDto1);
//    }

    @Transactional
    public ResponseEntity<?> saveEmployeeMaster(EmployeeReqDto employeeReqDto, Authentication authentication, String saveType) {
        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        if (!saveType.equals(EmployeeConstants.PERSONAL_DETAILS) && !saveType.equals(EmployeeConstants.BANK_DETAILS)) {
            throw new NotFoundException("Invalid path variable for saving employee onboarding details");
        }

        PayrollEmployeeMaster payrollEmployeeMaster;
        if (saveType.equals(EmployeeConstants.PERSONAL_DETAILS)) {
            payrollEmployeeMaster = convertToEntity(employeeReqDto);

            Optional<PayrollEmployeeMaster> employeeByPanNo = this.payrollEmployeeMasterRepo.findByPanNo(employeeReqDto.getPanNo());

            if (employeeByPanNo.isPresent())
                throw new DataIntegrityViolationException("Employee with this pancard already exists " + employeeReqDto.getPanNo());

            if (employeeReqDto.getIsDisabled()) {

                if (employeeReqDto.getEmpDisabilityReqDtoList() == null || employeeReqDto.getEmpDisabilityReqDtoList().isEmpty())
                    throw new BadRequestException("Employee Disability Detail(s) is/are required if is Disability (yes/no) is selected as yes");
            }

            if (employeeReqDto.getHeightMeasure() == EmployeeConstants.HEIGHT_MEASURE_CM)
                payrollEmployeeMaster.setHeightInch(null);

            if (employeeReqDto.getIsPayslip() && employeeReqDto.getPayslipAuthority() == null)
                throw new BadRequestException("Payslip Authority is required if payslip option is selected as yes");

            else if (!employeeReqDto.getIsPayslip())
                payrollEmployeeMaster.setPayslipAuthority(null);


            if (employeeReqDto.getIsGovtQuarterOccupied() && employeeReqDto.getQuarterType() == null)
                throw new BadRequestException("Government Quarter Type is required if Govt. Quarter Occupied(Yes/No) option is selected as yes");

            else if (!employeeReqDto.getIsGovtQuarterOccupied())
                payrollEmployeeMaster.setQuarterType(null);

            payrollEmployeeMaster.setRefNo(this.payrollEmployeeMasterRepo.refNoGeneration());
            payrollEmployeeMaster.setCrtBy(userMst);

            Integer retirementAge = this.payrollCadreMasterRepo.getRetirementAge(employeeReqDto.getCadreId());
            Date superannuationDate = EmployeeUtility.calculateSuperannuationDate(employeeReqDto.getDob(), retirementAge);

            payrollEmployeeMaster.setSuperannuationDate(superannuationDate);
            payrollEmployeeMaster = this.payrollEmployeeMasterRepo.save(payrollEmployeeMaster);

            if (payrollEmployeeMaster.getIsDisabled()) {
                List<PayrollEmployeeDisability> payrollEmployeeDisabilityList = EmployeeUtility.getEmpDisabilityDtoToEntityList(employeeReqDto.getEmpDisabilityReqDtoList());

                PayrollEmployeeMaster finalPayrollEmployeeMaster = payrollEmployeeMaster;
                payrollEmployeeDisabilityList.forEach(value -> {
                    value.setIsActive(true);
                    value.setCrtBy(userMst);
                    value.setEmpId(finalPayrollEmployeeMaster);

                    this.payrollEmployeeDisabilityRepo.save(value);
                });
            }


        } else {
            // Fetch the existing entity by ID
            String refNumber = employeeReqDto.getRefNo();
            payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNumber)
                    .orElseThrow(() -> new EntityNotFoundException("Employee record not found!"));

            // Update the relevant fields based on saveType
            convertBankDetailsToEntity(employeeReqDto, payrollEmployeeMaster);
            payrollEmployeeMaster.setUpdBy(userMst);
            payrollEmployeeMaster = this.payrollEmployeeMasterRepo.save(payrollEmployeeMaster);
        }


//        EmployeeReqDto employeeReqDto1 = convertToDto(payrollEmployeeMaster);

        return ResponseEntity.ok(new ApiResponse2<>(true, saveType.equals(EmployeeConstants.PERSONAL_DETAILS) ? EmployeeConstants.SAVE_PERSONAL_DETAILS_MESSAGE : EmployeeConstants.SAVE_BANK_DETAILS_MESSAGE, payrollEmployeeMaster.getRefNo(), HttpStatus.OK.value()));
    }

    @Transactional
    public ResponseEntity<?> saveEmployeeFamilyDetails(FamilyRequestDto familyRequestDto, Authentication authentication) {

        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        List<EmpFamilyDto> empFamilyDtoList = familyRequestDto.getEmpFamilyDtoList();
        String refNo = familyRequestDto.getRefNo();

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Record not found with reference number: " + refNo));


        List<PayrollEmpFamily> payrollEmpFamilyList = empFamilyDtoList.stream().map(empFamilyDto -> {

//            System.out.println(empFamilyDto);
//            log.info("empFamilyDto {}", empFamilyDto);
            if (empFamilyDto.getIsDisabled() && empFamilyDto.getDisabilityPercent() == null)
                throw new BadRequestException("Disability Percentage is required if Physically Disabled is selected as yes for " + empFamilyDto.getName());
            else if (!empFamilyDto.getIsDisabled())
                empFamilyDto.setDisabilityPercent(null);

            if (empFamilyDto.getIsDependant() && empFamilyDto.getDependantIncome() == null)
                throw new BadRequestException("Dependant Income is required if Dependant is selected as yes for " + empFamilyDto.getName());
            else if (!empFamilyDto.getIsDependant())
                empFamilyDto.setDependantIncome(null);

            if (empFamilyDto.getIsNominee() && empFamilyDto.getNominationPercent() == null)
                throw new BadRequestException("Nomination Percent is required if Nominee is selected as yes for " + empFamilyDto.getName());
            else if (!empFamilyDto.getIsNominee())
                empFamilyDto.setNominationPercent(null);

            PayrollEmpFamily payrollEmpFamily = EmpFamilyMapper.INSTANCE.dtoToEntity(empFamilyDto);
            payrollEmpFamily.setIsActive(true);
            payrollEmpFamily.setCrtBy(userMst);
            payrollEmpFamily.setEmpId(payrollEmployeeMaster);

//            System.out.println(payrollEmpFamily);
//            log.info("payrollEmpFamily {}", payrollEmpFamily);
            return payrollEmpFamily;
        }).collect(Collectors.toList());

        payrollEmpFamilyList.forEach(payrollEmpFamily -> this.payrollEmpFamilyRepo.save(payrollEmpFamily));

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.SAVE_FAMILY_DETAILS_MESSAGE, refNo, HttpStatus.OK.value()));
    }

    @Transactional
    public ResponseEntity<?> saveEducationalDetails(EducationRequestDto educationRequestDto, Authentication authentication) {
        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        List<EducationalDtlsDto> educationalDetailsList = educationRequestDto.getEducationalDetailsList();
        String refNo = educationRequestDto.getRefNo();

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Record not found with reference number: " + refNo));

        List<PayrollEducationalDetails> payrollEducationalDetailsList = educationalDetailsList.stream().map((educationalDtlsDto) -> {
            if (educationalDtlsDto.getMarksCgpaId() == EmployeeConstants.MARKS_TYPE_ID && educationalDtlsDto.getTotalMarks() == null && educationalDtlsDto.getMarksSecured() == null)
                throw new BadRequestException("Marks Secured and Total Marks are required if Marks option is selected for " + educationalDtlsDto.getCourse());
            else if (educationalDtlsDto.getMarksCgpaId() == EmployeeConstants.CGPA_TYPE_ID && educationalDtlsDto.getCgpa() == null)
                throw new BadRequestException("CGPA value is required if CGPA option is selected for " + educationalDtlsDto.getCourse());
            else if (educationalDtlsDto.getMarksCgpaId() == EmployeeConstants.MARKS_TYPE_ID)
                educationalDtlsDto.setCgpa(null);
            else if (educationalDtlsDto.getMarksCgpaId() == EmployeeConstants.CGPA_TYPE_ID) {
                educationalDtlsDto.setMarksSecured(null);
                educationalDtlsDto.setTotalMarks(null);
            }

            PayrollEducationalDetails payrollEducationalDetails = EducationalDtlsMapper.INSTANCE.dtoToEntity(educationalDtlsDto);
            if (educationalDtlsDto.getFilePath() != null && !educationalDtlsDto.getFilePath().isEmpty()) {
                HashMap<String, String> fileNameAndPath;
                try {
                    fileNameAndPath = this.uploadRealPathUtility.getFileNameAndPath(educationalDtlsDto.getFilePath(), refNo, EmployeeConstants.EMPLOYEE_DOCUMENTS);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                payrollEducationalDetails.setFilePath(fileNameAndPath.get("filePath"));
                payrollEducationalDetails.setFileName(fileNameAndPath.get("fileName"));
            }

            payrollEducationalDetails.setCrtBy(userMst);
            payrollEducationalDetails.setEmpId(payrollEmployeeMaster);

            return payrollEducationalDetails;

        }).collect(Collectors.toList());

        payrollEducationalDetailsList.forEach(value -> this.payrollEducationalDtlsRepo.save(value));

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.SAVE_EDUCATIONAL_DETAILS_MESSAGE, refNo, HttpStatus.OK.value()));
    }

    public ResponseEntity<?> getPayEntitlementList(PayEntitlementReqDto payEntitlementReqDto, Long payheadType) {
        Integer deptDesgnId = null;
        if (payEntitlementReqDto.getDepartmentId() != null && payEntitlementReqDto.getDesignationId() != null) {
            PayrollDesignationDeptMapping payrollDesignationDeptMapping = this.payrollDesignationDeptRepo.findByDeptId_DeptIdAndDsgnId_IdAndDeptId_IsActiveIsTrueAndDsgnId_IsActiveIsTrueAndIsActiveIsTrue(payEntitlementReqDto.getDepartmentId(), payEntitlementReqDto.getDesignationId()).orElseThrow(() -> new EntityNotFoundException("Department Designation mapping not found"));
            deptDesgnId = payrollDesignationDeptMapping.getId();
        }

//        log.info("deptDesgnId {}", deptDesgnId);
//        log.info("requestDto entitlement {}", payEntitlementReqDto);
//        List<PayEntitlementResDto> entitlementList = this.payrollPayheadMstRepo.getEntitlementList(payEntitlementReqDto.getBasicPay(), payEntitlementReqDto.getPayCommissionId(), payEntitlementReqDto.getServiceTypeId(), payEntitlementReqDto.getPayLevelId(), deptDesgnId, payEntitlementReqDto.getGrpId(), payheadType);

        List<PayEntitlementResDto> entitlementList = this.payrollPayheadMstRepo.getEntitlementListDummy(payEntitlementReqDto.getBasicPay(), payEntitlementReqDto.getPayCommissionId(), payEntitlementReqDto.getServiceTypeId(), payEntitlementReqDto.getPayLevelId(), deptDesgnId, payEntitlementReqDto.getGrpId(), payheadType, payEntitlementReqDto.getHraTier(), payEntitlementReqDto.getCtaEntitlement());


        return ResponseEntity.ok(entitlementList);
    }

    public ResponseEntity<?> getPayEntitlementListNew(PayEntitlementReqDto payEntitlementReqDto) {
        Integer deptDesgnId = null;
        if (payEntitlementReqDto.getDepartmentId() != null && payEntitlementReqDto.getDesignationId() != null) {
            PayrollDesignationDeptMapping payrollDesignationDeptMapping = this.payrollDesignationDeptRepo.findByDeptId_DeptIdAndDsgnId_IdAndDeptId_IsActiveIsTrueAndDsgnId_IsActiveIsTrueAndIsActiveIsTrue(payEntitlementReqDto.getDepartmentId(), payEntitlementReqDto.getDesignationId()).orElseThrow(() -> new EntityNotFoundException("Department Designation mapping not found"));
            deptDesgnId = payrollDesignationDeptMapping.getId();
        }


        List<PayEntitlementResDto> entitlementList = this.payrollPayheadMstRepo.getEntitlementListTest(payEntitlementReqDto.getBasicPay(), payEntitlementReqDto.getPayCommissionId(), payEntitlementReqDto.getServiceTypeId(), payEntitlementReqDto.getPayLevelId(), deptDesgnId, payEntitlementReqDto.getGrpId(), payEntitlementReqDto.getHraTier(), payEntitlementReqDto.getCtaEntitlement(), payEntitlementReqDto.getIsDaStop(), payEntitlementReqDto.getIsNPS());

        // Group by TypeDesc
        Map<Integer, List<PayEntitlementResDto>> groupedProjections = entitlementList.stream()
                .collect(Collectors.groupingBy(PayEntitlementResDto::getPayheadTypeId));

//        System.out.println(groupedProjections);

        Function<List<PayEntitlementResDto>, List<PayEntitlementResDto>> sortAndTransform = list -> list.stream()
                .sorted(Comparator.comparing(PayEntitlementResDto::getPayheadName))
                .collect(Collectors.toList());

//        System.out.println(empPayEntitlementList);

        HashMap<String, List<PayEntitlementResDto>> response = new HashMap<>();

        response.put("paymentPayhead", sortAndTransform.apply(groupedProjections.getOrDefault((int) EmployeeConstants.PAYHEAD_TYPE_PAYMENT, Collections.emptyList())));
        response.put("recoveryPayhead", sortAndTransform.apply(groupedProjections.getOrDefault((int) EmployeeConstants.PAYHEAD_TYPE_RECOVERY, Collections.emptyList())));
        response.put("deductionPayhead", sortAndTransform.apply(groupedProjections.getOrDefault((int) EmployeeConstants.PAYHEAD_TYPE_DEDUCTION, Collections.emptyList())));

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> getPayEntitlementEmployeeData(HashMap<String, String> req) {
        String refNo = req.get("refNo");
        EntitlementEmpRes payrollEmployee = payrollEmployeeMasterRepo.findByRefNoForEntitlement(refNo);

        EntitlementEmpResDto entitlementEmpResDto = new EntitlementEmpResDto();
        entitlementEmpResDto.setEmpId(payrollEmployee.getRefNo());
        entitlementEmpResDto.setDesignation(new CommonDropdownResDto(payrollEmployee.getDesignationId(), payrollEmployee.getDesignationName()));
        entitlementEmpResDto.setDepartment(new CommonDropdownResDto(payrollEmployee.getDeptId(), payrollEmployee.getDeptName()));
        entitlementEmpResDto.setDob(payrollEmployee.getDob());
        String name = payrollEmployee.getFirstName() + " ";
        name += payrollEmployee.getMiddleName() != null && !payrollEmployee.getMiddleName().isEmpty() ? payrollEmployee.getMiddleName() + " " : "";
        name += payrollEmployee.getLastName();
        entitlementEmpResDto.setEmployeeName(name);
        entitlementEmpResDto.setGroup(new CommonDropdownResDto(payrollEmployee.getGrpId(), payrollEmployee.getGrpName()));
        entitlementEmpResDto.setOffice(new CommonDropdownResDto(payrollEmployee.getOfcId(), payrollEmployee.getOfficeName()));
        entitlementEmpResDto.setMobileNo(payrollEmployee.getPersonalMobileNo());
        entitlementEmpResDto.setServiceType(new CommonDropdownResDto(payrollEmployee.getServiceId(), payrollEmployee.getServiceName()));
        entitlementEmpResDto.setGovtQuarterType(payrollEmployee.getIsGovtQuarterOccupied());
        entitlementEmpResDto.setGpfPranType(new CommonDropdownResDto(payrollEmployee.getGpfPranTypeId(), payrollEmployee.getGpfPranTypeName()));
        entitlementEmpResDto.setGpfPranNo(payrollEmployee.getGpfPranNo());

        List<PayrollEmployeeDisability> payrollEmployeeDisabilityList = payrollEmployeeDisabilityRepo.findAllByEmpId_EmpIdAndIsActiveIsTrue(payrollEmployee.getEmpId());

        List<HashMap<String, Object>> disabilityTypeList = payrollEmployeeDisabilityList.stream().map(value -> {
            HashMap<String, Object> response = new HashMap<>();
            response.put("disabilityType", new CommonDropdownResDto(value.getDisabilityType().getTypeId(), value.getDisabilityType().getTypeName()));
            response.put("percentage", value.getPercentage());
            return response;
        }).collect(Collectors.toList());

        entitlementEmpResDto.setDisability(disabilityTypeList);

        return ResponseEntity.ok(entitlementEmpResDto);
    }

    @Transactional
    public ResponseEntity<?> saveEntitlement(EmpPayEntitlementOptionsReqDto empPayEntitlementOptionsReqDto, Authentication authentication) {
        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        String refNo = empPayEntitlementOptionsReqDto.getRefNo();

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Record not found with reference number: " + refNo));

        if (empPayEntitlementOptionsReqDto.getIsCtaApplicable() && empPayEntitlementOptionsReqDto.getCtaEntitlement() == null)
            throw new BadRequestException("CTA Entitlement is required if Is CTA Applicable is selected as yes");
        else if (!empPayEntitlementOptionsReqDto.getIsCtaApplicable())
            empPayEntitlementOptionsReqDto.setCtaEntitlement(null);

        if (payrollEmployeeMaster.getIsGovtQuarterOccupied() && empPayEntitlementOptionsReqDto.getHraTier() == null)
            throw new BadRequestException("HRA Tier is required if is Govt. Quarter Occupied is selected as yes");
        else if (!payrollEmployeeMaster.getIsGovtQuarterOccupied())
            empPayEntitlementOptionsReqDto.setHraTier(null);

        EmpPayEntitlementOptions empPayEntitlementOptions = PayEntitlementMapper.INSTANCE.payEntitlementOptionsDtoToEntity(empPayEntitlementOptionsReqDto);

        empPayEntitlementOptions.setEmpId(payrollEmployeeMaster);
        empPayEntitlementOptions.setCrtBy(userMst);

        EmpPayEntitlementOptions savedPayEntitlementOptions = this.empPayEntitlementOptionsRepo.save(empPayEntitlementOptions);

        empPayEntitlementOptionsReqDto.getEmpPayEntitlementReqDtoList().forEach(value -> {
            if ((value.getPayheadId() == EmployeeConstants.PAYHEAD_DA && empPayEntitlementOptionsReqDto.getIsDaStop()) ||
                    (value.getPayheadId() == EmployeeConstants.PAYHEAD_NPS && !empPayEntitlementOptions.getIsNpsOpted()))
                value.setPayheadValue(0);
        });

        List<EmpPayEntitlement> empPayEntitlementList = PayEntitlementMapper.INSTANCE.mapEmpPayEntitlementReqDtoList(empPayEntitlementOptionsReqDto.getEmpPayEntitlementReqDtoList());

        empPayEntitlementList.forEach(value -> {
            value.setCrtBy(userMst);
            value.setPayheadEntitlementId(savedPayEntitlementOptions);
            value.setEmpId(payrollEmployeeMaster);

            this.empPayEntitlementRepo.save(value);
        });

//        log.info("empPayEntitlementOptions {}", empPayEntitlementOptions);
//        log.info("empPayEntitlementList {}", empPayEntitlementList);

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.SAVE_PAY_ENTITLEMENT_MESSAGE, refNo, HttpStatus.OK.value()));
    }

    @Transactional
    public ResponseEntity<?> saveDocuments(EmployeeDocumentsReqDto employeeDocumentsReqDto, Authentication authentication) {
        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        String refNo = employeeDocumentsReqDto.getRefNo();

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Record not found with reference number: " + refNo));

        List<@Valid EmployeeDocumentsDto> employeeDocumentsList = employeeDocumentsReqDto.getEmployeeDocumentsList();

        // Extract provided document IDs
        Set<Long> providedDocumentIds = employeeDocumentsList.stream()
                .map(EmployeeDocumentsDto::getDocumentId)
                .collect(Collectors.toSet());

        // Check if any required document IDs are missing
        if (!providedDocumentIds.containsAll(EmployeeConstants.REQUIRED_DOCUMENT_IDS)) {
            Set<Long> missingDocumentIds = new HashSet<>(EmployeeConstants.REQUIRED_DOCUMENT_IDS);
            missingDocumentIds.removeAll(providedDocumentIds);
            throw new IllegalArgumentException("Missing required document IDs: " + missingDocumentIds);
        }


        AtomicInteger counter = new AtomicInteger(1);
        employeeDocumentsList.stream().map(employeeDocumentsDto -> {
                    EmployeeDocuments employeeDocuments = EmployeeDocumentsMapper.INSTANCE.employeeDocumentsDtoToEntity(employeeDocumentsDto);

                    HashMap<String, String> fileNameAndPath;
                    try {
                        fileNameAndPath = this.uploadRealPathUtility.getFileNameAndPath(employeeDocumentsDto.getFilePath(), refNo, EmployeeConstants.EMPLOYEE_DOCUMENTS);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    employeeDocuments.setFilePath(fileNameAndPath.get("filePath"));
                    employeeDocuments.setFileName(fileNameAndPath.get("fileName"));
                    employeeDocuments.setDisplayOrder(counter.getAndIncrement());
                    employeeDocuments.setIsActive(true);
                    employeeDocuments.setEmpId(payrollEmployeeMaster);
                    employeeDocuments.setCrtBy(userMst);
                    return employeeDocuments;
                }).collect(Collectors.toList())
                .forEach(employeeDocumentsRepo::save);


        List<PayrollEmpFamily> payrollEmpFamilyList = this.payrollEmpFamilyRepo.findAllByEmpId_EmpIdAndIsActiveIsTrue(payrollEmployeeMaster.getEmpId());
        List<PayrollEducationalDetails> educationalDetailsList = this.payrollEducationalDtlsRepo.findAllByEmpId_EmpId(payrollEmployeeMaster.getEmpId());
        Optional<EmpPayEntitlementOptions> optionalEmpPayEntitlementOptions = this.empPayEntitlementOptionsRepo.findByEmpId_EmpId(payrollEmployeeMaster.getEmpId());
        List<EmpPayEntitlement> empPayEntitlementList = this.empPayEntitlementRepo.findPayHeadsByEmpId(payrollEmployeeMaster.getEmpId());

        if (payrollEmployeeMaster.getBankAccNo() != null && !payrollEmpFamilyList.isEmpty() && !educationalDetailsList.isEmpty() && optionalEmpPayEntitlementOptions.isPresent() && !empPayEntitlementList.isEmpty()) {
            UserMst user = getUserMst(payrollEmployeeMaster, userMst);

            UserMst employeeSaved = this.userRepo.save(user);

            UserRoleMappingMst userRoleMappingMst = new UserRoleMappingMst();
            RoleMst roleMst = new RoleMst();
            roleMst.setRoleId(EmployeeConstants.EMPLOYEE_ROLE_ID);
            userRoleMappingMst.setRole(roleMst);
            userRoleMappingMst.setIsActive(true);
            userRoleMappingMst.setUser(employeeSaved);
            userRoleMappingMst.setCrtBy(userMst);

            this.userRoleMapRepo.save(userRoleMappingMst);

        } else if (payrollEmployeeMaster.getBankAccNo() == null)
            throw new EntityNotFoundException("Bank details not filled!");
        else if (payrollEmpFamilyList.isEmpty())
            throw new EntityNotFoundException("Family details not filled!");
        else if (educationalDetailsList.isEmpty())
            throw new EntityNotFoundException("Educational details not filled!");
        else if (!optionalEmpPayEntitlementOptions.isPresent())
            throw new EntityNotFoundException("Employee Pay Entitlement options not filled!");
        else throw new EntityNotFoundException("Employee Pay Entitlement list not filled!");

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.SAVE_DOCUMENTS_MESSAGE, refNo, HttpStatus.OK.value()));
    }

    private static UserMst getUserMst(PayrollEmployeeMaster payrollEmployeeMaster, UserMst userMst) {
        UserMst user = new UserMst();
        String fullname = payrollEmployeeMaster.getFirstName() + " ";
        fullname = !payrollEmployeeMaster.getMiddleName().isEmpty() ? payrollEmployeeMaster.getMiddleName() + " " : "";
        fullname = payrollEmployeeMaster.getLastName();
        user.setFullname(fullname);
        user.setEmpCode(payrollEmployeeMaster.getEmpId().toString());
        user.setUserName(payrollEmployeeMaster.getFirstName().trim().toLowerCase() + payrollEmployeeMaster.getEmpId().toString());
        user.setMobileNo(payrollEmployeeMaster.getPersonalMobileNo());
        user.setEmail(payrollEmployeeMaster.getPersonalEmail());
        user.setPancard(payrollEmployeeMaster.getPanNo());
        user.setPassword(EmployeeConstants.EMPLOYEE_DEFAULT_PASSWORD);
        user.setIsActive(true);
        user.setCreatedBy(userMst.getUserId());
        return user;
    }

    public ResponseEntity<?> getEmployeePersonalDetails(String refNo) {
        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        EmpMstResDto empMstDto = convertToDto(payrollEmployeeMaster);

        List<PayrollEmployeeDisability> payrollEmpDisabilityList = this.payrollEmployeeDisabilityRepo.findAllByEmpId_EmpIdAndIsActiveIsTrue(payrollEmployeeMaster.getEmpId());

        List<EmpDisabilityResDto> empDisabilityDtoList = payrollEmpDisabilityList.stream().map(value -> {
            // EmpDisabilityResDto empDisabilityResDto = new EmpDisabilityResDto();
            // empDisabilityResDto.setDisabilityType(new CommonDropdownResDto(value.getDisabilityType().getTypeId(), value.getDisabilityType().getTypeName()));
            // empDisabilityResDto.setDisabilityPercent(value.getPercentage());

            // return empDisabilityResDto;

            return convertEmpDisabilityToDto(value);

        }).collect(Collectors.toList());

        empMstDto.setEmpDisabilityList(empDisabilityDtoList);

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_PERSONAL_DETAILS_MESSAGE, empMstDto, HttpStatus.OK.value()));
    }

    public ResponseEntity<?> getEmployeeBankDetails(String refNo) {
        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        EmpBankDtlsResDto empBankDtlsDto = convertEmpBankDtlsToDto(payrollEmployeeMaster);


        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_BANK_DETAILS_MESSAGE, empBankDtlsDto, HttpStatus.OK.value()));
    }

    public ResponseEntity<?> getEmpFamilyDetails(String refNo) {

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        List<PayrollEmpFamily> empFamilyList = this.payrollEmpFamilyRepo.findAllByEmpId_EmpIdAndIsActiveIsTrue(payrollEmployeeMaster.getEmpId());

//        System.out.println(empFamilyList);

        List<EmpFamilyResDto> empFamilyDtoList = empFamilyList.stream().map(EmpFamilyMapper.INSTANCE::EntityToDto).collect(Collectors.toList());


        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_FAMILY_DETAILS_MESSAGE, empFamilyDtoList, HttpStatus.OK.value()));
    }

    public ResponseEntity<?> getEmpEducationalDetails(String refNo) {

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        List<PayrollEducationalDetails> payrollEducationalDtlsList = this.payrollEducationalDtlsRepo.findAllByEmpId_EmpId(payrollEmployeeMaster.getEmpId());

        List<EmpEducationalDtlsResDto> empEducationalDtlsDtoList = payrollEducationalDtlsList.stream().map(EducationalDtlsMapper.INSTANCE::EntityToDto).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_EDUCATIONAL_DETAILS_MESSAGE, empEducationalDtlsDtoList, HttpStatus.OK.value()));
    }

    public ResponseEntity<?> getEmpDocuments(String refNo) {

        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        List<EmployeeDocuments> employeeDocumentsList = this.employeeDocumentsRepo.findAllByEmpId_EmpIdAndIsActiveIsTrue(payrollEmployeeMaster.getEmpId());

        List<EmpDocumentsResDto> empDocumentsDtoList = employeeDocumentsList.stream().map(EmployeeDocumentsMapper.INSTANCE::entityToDto).collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_DOCUMENTS_MESSAGE, empDocumentsDtoList, HttpStatus.OK.value()));

    }


    public ResponseEntity<?> getEmpPayEntitlementDetails(String refNo) {
        PayrollEmployeeMaster payrollEmployeeMaster = this.payrollEmployeeMasterRepo.findByRefNo(refNo).orElseThrow(() -> new EntityNotFoundException("Employee Details not found with reference number: " + refNo));

        EmpPayEntitlementOptions empPayEntitlementOptions = this.empPayEntitlementOptionsRepo.findByEmpId_EmpId(payrollEmployeeMaster.getEmpId()).orElseThrow(() -> new EntityNotFoundException("Pay entitlement details not found with employee reference number: " + refNo));

        PayEntitlementOptionsResDto payEntitlementOptionsResDto = PayEntitlementMapper.INSTANCE.entityToDto(empPayEntitlementOptions);

        List<EmpPayEntitlement> empPayEntitlementList = this.empPayEntitlementRepo.findAllByPayheadEntitlementId_IdOrderByPayheadId_PayheadName(empPayEntitlementOptions.getId());


        List<PayheadResDto> payheadResDtos = PayEntitlementMapper.INSTANCE.mapPayheadResDtoList(empPayEntitlementList);


        // Group by TypeDesc
        Map<Long, List<PayheadResDto>> groupedProjections = payheadResDtos.stream()
                .collect(Collectors.groupingBy(PayheadResDto::getPayheadTypeId));

//        System.out.println(groupedProjections);

        Function<List<PayheadResDto>, List<PayheadResDto>> sortAndTransform = list -> list.stream()
                .sorted(Comparator.comparing(PayheadResDto::getPayheadName))
                .collect(Collectors.toList());

//        System.out.println(empPayEntitlementList);

        payEntitlementOptionsResDto.setPaymentPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_PAYMENT, Collections.emptyList())));
        payEntitlementOptionsResDto.setRecoveryPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_RECOVERY, Collections.emptyList())));
        payEntitlementOptionsResDto.setDeductionPayhead(sortAndTransform.apply(groupedProjections.getOrDefault(EmployeeConstants.PAYHEAD_TYPE_DEDUCTION, Collections.emptyList())));

        return ResponseEntity.ok(new ApiResponse2<>(true, EmployeeConstants.FETCHED_PAY_ENTITLEMENT_MESSAGE, payEntitlementOptionsResDto, HttpStatus.OK.value()));

    }

    public ResponseEntity<?> getAllEmployeeListAdmin(Authentication authentication)
    {
        UserMst userMst = this.userRepo.findByUserNameIgnoreCaseAndIsActiveIsTrue(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("User doesn't exist!"));

        Boolean admin = roleUtility.isAdmin(userMst);

        if (!admin)
            throw new UnauthorizedException();

        List<SavedEmployeeResDto> savedEmployeeResDtoList = this.payrollEmployeeMasterRepo.findAllBySavedEmployee();

        return ResponseEntity.ok(savedEmployeeResDtoList);

    }

	public ApiResponse2<?> getEmpByGrpCode(Long grpCode) {
		List<EmployeeDropdownDto> employees = payrollEmployeeMasterRepo.findEmpListByGrpId(grpCode);
		if (employees.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, employees, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getEmpForReport(SearchEmployesDto searchEmployesDto) {
		
		List<EmployeeMstRes> employees = new ArrayList<>();
		if(searchEmployesDto.getStatus() == 'T') {
			employees = payrollEmployeeMasterRepo.findTotalEmployees(searchEmployesDto.getOfficeId());
		}
		else if (searchEmployesDto.getStatus() == 'P') {
			employees = payrollEmployeeMasterRepo.findProccessedEmployees(searchEmployesDto.getOfficeId(), searchEmployesDto.getFyMonId());
		}
		else if (searchEmployesDto.getStatus() == 'N'){
			employees = payrollEmployeeMasterRepo.findNotProccessedEmployees(searchEmployesDto.getOfficeId(), searchEmployesDto.getFyMonId());
		}
		else if (searchEmployesDto.getStatus() == 'D') {
			employees = payrollEmployeeMasterRepo.findDelayedEmployees(searchEmployesDto.getOfficeId(), searchEmployesDto.getFyMonId());
		}
		if(employees.isEmpty()) {
			return new ApiResponse2<>(false, ApiResponseStatus.noRecords, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, employees, HttpStatus.OK.value());
	}

	public ApiResponse2<?> getEmpDetailsByEmpId(Long empId) {
		EmployeeMstRes emp = payrollEmployeeMasterRepo.findByEmpId(empId);
		if(emp ==null) {
			return new ApiResponse2<>(false, ApiResponseStatus.noEmp, null, HttpStatus.OK.value());
		}
		return new ApiResponse2<>(true, ApiResponseStatus.fetch, emp, HttpStatus.OK.value());
	}
	
}
