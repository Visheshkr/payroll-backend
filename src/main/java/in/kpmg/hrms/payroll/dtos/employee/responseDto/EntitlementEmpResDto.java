package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntitlementEmpResDto {

    private String empId;
    private CommonDropdownResDto gpfPranType;
    private String gpfPranNo;
    private CommonDropdownResDto designation;
    private CommonDropdownResDto department;
    private String employeeName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    private Date dob;
    private String mobileNo;
    private CommonDropdownResDto serviceType;
    private CommonDropdownResDto office;
    private CommonDropdownResDto group;
    private Boolean govtQuarterType;
    private List<HashMap<String, Object>> disability;
}
