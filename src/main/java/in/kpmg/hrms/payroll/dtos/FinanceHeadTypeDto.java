package in.kpmg.hrms.payroll.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinanceHeadTypeDto {

    private Integer parentId;
    private String parentName;

    private List<FinanceHeadSaveDto> financeHeadDto;
}
