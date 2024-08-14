package in.kpmg.hrms.payroll.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.kpmg.hrms.payroll.dtos.ServiceTypeInputDto;
import in.kpmg.hrms.payroll.models.PayrollServiceTypeMaster;

@Mapper(componentModel = "spring")
public interface ServiceTypeMstMapper {



    ServiceTypeMstMapper mapper = Mappers.getMapper(ServiceTypeMstMapper.class);


    ServiceTypeInputDto entityToDto(PayrollServiceTypeMaster serviceTypeMater);


   
    PayrollServiceTypeMaster dtoToEntity(ServiceTypeInputDto inputDto);


    default List<ServiceTypeInputDto> serviceTypeMstToDtos(List<PayrollServiceTypeMaster> payrollServiceTypeMasters){
        List<ServiceTypeInputDto> response = new ArrayList<>();
        for(PayrollServiceTypeMaster mst : payrollServiceTypeMasters) {
            response.add(entityToDto(mst));
        }
        return response;

    }

}
