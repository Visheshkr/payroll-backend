package in.kpmg.hrms.payroll.dtos;

import lombok.Data;

@Data
public class ResponseException {
	
	private String msg;
	private String code;

}
