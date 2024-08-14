package in.kpmg.hrms.payroll.dtos.employee.responseDto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import in.kpmg.hrms.payroll.dtos.CommonDropdownResDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpDocumentsResDto {
    private Long id;
    private Long empId;
    private CommonDropdownResDto documentId;
    private String filePath;
    private String fileName;
    private Integer displayOrder;
    private Boolean isActive;
    private CommonDropdownResDto crtBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp crtOn;
    private CommonDropdownResDto updBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm aa", timezone = "Asia/Kolkata")
    private Timestamp updOn;
}
