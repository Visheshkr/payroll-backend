package in.kpmg.hrms.payroll.dtos;

import lombok.Data;

@Data
public class DesgnDto {
    private Integer id;
    private String name;
    private String nameTelugu;
    private String code;
    private Boolean isActive;
    private Integer createdBy;
    private Integer updatedBy;
}
