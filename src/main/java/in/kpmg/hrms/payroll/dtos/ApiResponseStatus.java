package in.kpmg.hrms.payroll.dtos;

public class ApiResponseStatus {
	
	public static final String saved = "Data saved successfully";
	public static final String existingCode = "Type code existed already ";
	public static final String NoIncometaxSectionFound = "Income tax section not found with id : ";
	public static final String NoIncometaxSlabFound = "Income tax slab not found with id : ";
	public static final String NoIncometaxDeductionHeadFound = "Income tax deduction head not found with id : ";
	public static final String NoIncometaxDeclarationFound = "Income tax declaration not found with id : ";
	public static final String NoEmpType = "Employee type not found with id : ";
	public static final String NoFyId = "Fy not found with FY id : ";
	public static final String existedFY = "Data exist already with given FY ! ";
	public static final String existedFYMON = "Data exist already with given Fy and Month! ";
	public static final String NoDeductionHeadFound = "Deduction head not found with id : ";
	public static final String NoITSectionDeductionHeadFound = "Section-Deduction head mapping not found with id : ";
	public static final String existing = "Record existed already ";
	public static final String alreadyProcessed = "salary already proccessed for this month for given employee  ";
	public static final String fetch = "Data fetched successfully";
	public static final String deleted = "Data deleted successfully";
	public static final String updated = "Data edited successfully";
	public static final String exception = "Unexpected exception";
	public static final String validationErrors = "validation errors found";
	public static final String badRequest = "Bad Request";
	public static final String noRecords = "No records found";
	public static final String noEmp = "Employee not found";
	public static final String noGroup = "group not found";
	public static final String nohoa = "No hoa found";
	public static final String configCombinationExistes = "Combination of configuration existed already ! ";
	public static final String noOffice = "No office found";
	public static final String groupsExisted = "Group existed already ";
	public static final String NotExstedgroup = "Group doesn't exists ";
	public static final String alreadyEmpGrp = "Employee mapped to group already ";
	public static final String inValidFormula = "Invalid formula !";
	public static final String NoProfessionalTaxFound = "Professional Tax not found with id : ";
	public static final String noFyMonth = "record existed already with given FY Month ";
	public static final String existedHoaFyMonth = "record existed already with given hoa and FY Month ";
	public static final String invalidBandID = "Invalid Pay Band ID";
	public static final String invalidUserId = "Invalid User ID";
	public static final String alreadyProccesed = "Salary already processed";
}
