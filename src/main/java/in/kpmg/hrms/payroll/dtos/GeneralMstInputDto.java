package in.kpmg.hrms.payroll.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralMstInputDto {

    private Integer parentId;
    private String parentName;

//    private Long typeId;
//    private String typeDesc;
//    private String typeCode;

    private List<GeneralTypeMasterDto> generalMst;

}
