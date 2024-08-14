package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SaveEmpSalaryBillDto;
import in.kpmg.hrms.payroll.dtos.SalaryBillDto.SaveEmpSalaryPayHeadsDto;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingDetails;
import in.kpmg.hrms.payroll.models.EmpSalaryProcessingMst;
import in.kpmg.hrms.payroll.models.PayrollPayHeadConfigMaster;
import in.kpmg.hrms.payroll.models.PayrollPayHeadMaster;
import in.kpmg.hrms.payroll.models.UserMst;

@Mapper(componentModel = "spring")
public interface EmpSalaryProcessingMapper {
	
	EmpSalaryProcessingMapper mapper = Mappers.getMapper(EmpSalaryProcessingMapper.class);
	
	
	@Mapping(source = "empId", target = "empId.empId")
	@Mapping(source = "fyPayMonthId", target = "fyPayMonthId.id")
	@Mapping(source = "empSalary", target = "empSalary")
	@Mapping(source = "crtBy", target = "crtBy")
	EmpSalaryProcessingMst dtoToEntity(SaveEmpSalaryBillDto saveEmpSalaryBillDto);
	
	
	
	@Mapping(source = "payheadId", target = "payheadId")
	@Mapping(source = "payheadConfigId", target = "payheadConfigId")
	EmpSalaryProcessingDetails dtoToPayHeadMst(SaveEmpSalaryPayHeadsDto emp);
	
	
	default List<EmpSalaryProcessingDetails> dtoToSalaryMst(List<SaveEmpSalaryPayHeadsDto> empSalary){
		
		List<EmpSalaryProcessingDetails> mst = new ArrayList<>();
		for(SaveEmpSalaryPayHeadsDto paHeads : empSalary) {
			mst.add(dtoToPayHeadMst(paHeads));
		}
		return mst;
	}
	
	default UserMst userIdTomSt(Integer id) {
		if(id == null) {
			return null;
		}
		UserMst user = new UserMst();
		user.setUserId(id);
		return user;
	}
	
	
	
	default PayrollPayHeadMaster idToPayrollHead(Integer id) {
		if(id == null) {
			return null;
		}
		PayrollPayHeadMaster payhead = new PayrollPayHeadMaster();
		payhead.setPayheadId(id);
		return payhead;
	}
	
	default PayrollPayHeadConfigMaster idToPayrollHeadConfig(Integer id) {
		if(id == null) {
			return null;
		}
		PayrollPayHeadConfigMaster payhead = new PayrollPayHeadConfigMaster();
		payhead.setPayheadConfigId(id);
		return payhead;
	}

}
