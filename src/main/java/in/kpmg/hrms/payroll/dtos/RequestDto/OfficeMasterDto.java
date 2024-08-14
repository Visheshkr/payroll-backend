package in.kpmg.hrms.payroll.dtos.RequestDto;

import javax.validation.constraints.NotNull;

import in.kpmg.hrms.payroll.services.MasterMappingService.Operational;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficeMasterDto {

	private Long ofcId;
	
	@NotNull(message = "Status is mandatory")
    private Boolean status;

    @NotNull(message = "Office Name is mandatory")
    private String officeName;

    @NotNull(message = "Office Code is mandatory")
    private String ofcCode;

    @NotNull(message = "Office Name Regional Language is mandatory")
    private String officeNameRegLang;
    
    @NotNull(message = "Office Type Id is mandatory")
    private Long ofcTypeId;

    @NotNull(message = "Office Level Id is mandatory")
    private Long ofcLevelId;

    @NotNull(message = "Office Phone Number is mandatory")
    private Long ofcPhoneNumber;

    @NotNull(message = "Department Code is mandatory")
    private Integer deptCode;

    @NotNull(message = "Reporting Office Id is mandatory", groups= {Operational.class})
    private Long reportingOfcId;

    @NotNull(message = "Treasure Code is mandatory")
    private String treasureCode;

    @NotNull(message = "Address Line 1 is mandatory")
    private String addressLine1;

    @NotNull(message = "State Id is mandatory")
    private Long stateId;

    @NotNull(message = "Division Id is mandatory")
    private Long divisionId;

    @NotNull(message = "District Id is mandatory")
    private Long districtId;

    @NotNull(message = "Block Id is mandatory")
    private Long blockId;

    @NotNull(message = "Sub Division Id is mandatory")
    private Long subDivisionId;

    @NotNull(message = "Pincode is mandatory")
    private Integer pincode;

    @NotNull(message = "Is Active is mandatory")
    private Boolean isActive;

    private Integer displayCode;

    private String tan;

    private String nsdlDdoCose;

    private String intbenfId;

    private String bankAccStatus;

    private Integer userId;
}
