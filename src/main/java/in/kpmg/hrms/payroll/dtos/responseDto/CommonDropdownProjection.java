package in.kpmg.hrms.payroll.dtos.responseDto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommonDropdownProjection {
    Long getId();

    String getLabel();

    @JsonIgnore
    Long getGeneralType();
}
