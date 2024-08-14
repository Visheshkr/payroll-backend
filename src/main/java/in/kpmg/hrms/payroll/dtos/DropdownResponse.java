package in.kpmg.hrms.payroll.dtos;

public interface DropdownResponse {
	
	Integer getTypeId();
	
	String getTypeCode();
	
	String getTypeName();
	
	Integer getDeptDsgnId();
	
	Integer getPcServiceTypeId();
	Integer getPbServTypeId();
	
	String getTypeCodeStr();
	
	String getDetailId();
	
	String getSubDetailId();
	
	Integer getMinorId();
	
	Integer getMajorid();
	
	Integer getSubMajorId();

}
