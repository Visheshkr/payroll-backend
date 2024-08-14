package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.PayCommisionServTypeInputDto;
import in.kpmg.hrms.payroll.models.GeneralMst;
import in.kpmg.hrms.payroll.models.PayrollPayCommisionServTypeMapping;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;

@Mapper(componentModel = "spring")
public interface PayCommissionServiceTypeMapper {

    PayCommissionServiceTypeMapper mapper = Mappers.getMapper(PayCommissionServiceTypeMapper.class);


    @Mapping(source = "servTypeId.id", target = "servTypeId")
    @Mapping(source = "servTypeId.serviceType", target = "serviceTypeName")
    @Mapping(source = "pcId.typeId", target = "pcId")
    @Mapping(source = "pcId.typeName", target = "pcIdTypeName")
    PayCommisionServTypeInputDto entityToDto(PayrollPayCommisionServTypeMapping payrollPayCommisionServTypeMapping);

    @Mapping(source = "servTypeId", target = "servTypeId", ignore = true)
    @Mapping(source = "pcId", target = "pcId", ignore = true)
    PayrollPayCommisionServTypeMapping dtoToEntity(PayCommisionServTypeInputDto payCommisionServTypeInputDto);


    default List<PayCommisionServTypeInputDto> payCommissionerviceTypeMstToDtos(List<PayrollPayCommisionServTypeMapping> payCommisionServTypeMappings){
        List<PayCommisionServTypeInputDto> response = new ArrayList<>();
        for(PayrollPayCommisionServTypeMapping mst : payCommisionServTypeMappings) {
            response.add(entityToDto(mst));
        }
        return response;

    }
    
    default PayrollServiceTypeMaster idToServiceMst (Integer id) {
    	if(id == null)
    	{
    		return null;
    	}
    	PayrollServiceTypeMaster service = new PayrollServiceTypeMaster();
    	service.setId(id);
    	return service;
    }
    
    default GeneralMst idToGeneralMst (Integer id) {
    	if(id == null)
    	{
    		return null;
    	}
    	GeneralMst service = new GeneralMst();
    	service.setTypeId((long)id);
    	return service;
    }
    
    
    	


}
