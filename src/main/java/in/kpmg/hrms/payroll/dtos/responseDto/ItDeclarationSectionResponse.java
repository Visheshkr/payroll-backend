package in.kpmg.hrms.payroll.dtos.responseDto;

import java.util.List;

import lombok.Data;


public interface ItDeclarationSectionResponse {

	String getSectionName();

	Integer getSectionId();

	Long getMaxLimit();

	//	private List<ItDeclarationSchemesResponse> schemes;


}
