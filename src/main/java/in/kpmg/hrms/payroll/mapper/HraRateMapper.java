package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.RequestDto.SaveHraRateDto;
import in.kpmg.hrms.payroll.models.PayrollHRARateMst;
import in.kpmg.hrms.payroll.models.PayrollHRARateMstAudit;

@Mapper(componentModel = "spring")
public interface HraRateMapper {
	
	HraRateMapper mapper = Mappers.getMapper(HraRateMapper.class);
	
	
	
	
	@Mapping(source = "userId", target = "createdBy.userId")
	PayrollHRARateMst dtoToHramSt(SaveHraRateDto saveHraRateDto) ;
	
	
	@Mapping(source = "createdBy.userId", target = "userId")
	SaveHraRateDto HraMstToDto(PayrollHRARateMst payrollHRARateMst);
	
	default List<SaveHraRateDto> HraRatesToDtos(List<PayrollHRARateMst> payrollHRARateMst){
		List<SaveHraRateDto> response = new ArrayList<>();
		for(PayrollHRARateMst mst : payrollHRARateMst) {
			response.add(HraMstToDto(mst));
		}
		return response;
		
	}

	@Mapping(source = "createdBy", target = "userId")
	SaveHraRateDto HraMstToDto(PayrollHRARateMstAudit payrollHRARateMst);

	default List<SaveHraRateDto> HraRatesAuditToDtos(List<PayrollHRARateMstAudit> hraAudit){
		List<SaveHraRateDto> response = new ArrayList<>();
		for(PayrollHRARateMstAudit mst : hraAudit) {
			response.add(HraMstToDto(mst));
		}
		return response;
	}

}
