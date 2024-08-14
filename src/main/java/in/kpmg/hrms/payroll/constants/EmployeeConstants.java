package in.kpmg.hrms.payroll.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EmployeeConstants {

    //employee first creation pass
    public static final String EMPLOYEE_DEFAULT_PASSWORD="IpWhGjWcuQeMi0tX1bLvItTnmwxoOwOi0RTyBUUOLpYT9XVlKlQHvsi5+3e/OpQd";

    //Employee Role id
    public static final int EMPLOYEE_ROLE_ID=3;
    public static final int SUPER_ADMIN_ROLE_ID=1;


    //Save emp mst api path variable
    public static final String PERSONAL_DETAILS = "personal-details";
    public static final String BANK_DETAILS = "bank-details";

    public static final String SAVE_PERSONAL_DETAILS_MESSAGE = "Personal Details saved successfully";
    public static final String SAVE_BANK_DETAILS_MESSAGE = "Bank Details saved successfully";
    public static final String SAVE_FAMILY_DETAILS_MESSAGE = "Family Details saved successfully";
    public static final String SAVE_EDUCATIONAL_DETAILS_MESSAGE = "Educational Details saved successfully";
    public static final String SAVE_PAY_ENTITLEMENT_MESSAGE = "Pay Entitlement Details saved successfully";
    public static final String SAVE_DOCUMENTS_MESSAGE = "Documents saved successfully";

    public static final String FETCHED_PERSONAL_DETAILS_MESSAGE = "Personal Details fetched successfully";
    public static final String FETCHED_BANK_DETAILS_MESSAGE = "Bank Details fetched successfully";
    public static final String FETCHED_FAMILY_DETAILS_MESSAGE = "Family Details fetched successfully";
    public static final String FETCHED_EDUCATIONAL_DETAILS_MESSAGE = "Educational Details fetched successfully";
    public static final String FETCHED_PAY_ENTITLEMENT_MESSAGE = "Pay Entitlement Details fetched successfully";
    public static final String FETCHED_DOCUMENTS_MESSAGE = "Documents fetched successfully";

    //Salary Slip
    public static final String FETCHED_SALARY_SLIP_DETAILS_MESSAGE = "Salary slip details fetched successfully";
    public static final String FETCHED_SALARY_STRUCTURE_DETAILS_MESSAGE = "Salary structure details fetched successfully";
    public static final String FETCHED_SALARY_PROCESS_DETAILS_MESSAGE = "Regular Salary processing fetched successfully";

    //Employee Module Names
    public static final String EMPLOYEE_DOCUMENTS = "Employee_Documents";


    //Marks and CGPA Id
    public static final int MARKS_TYPE_ID = 269;
    public static final int CGPA_TYPE_ID = 270;

    //Location Type Id
    public static final long STATE_TYPE_ID = 253;
    public static final long DISTRICT_TYPE_ID = 255;


    //General Mst Dropdown Init
    public static final long GENERAL_TYPE_DESC_CASTE = 46L;
    public static final long GENERAL_TYPE_DESC_PREFIX = 28L;
    public static final long GENERAL_TYPE_DESC_BLOOD_GROUP = 19L;
    public static final long GENERAL_TYPE_DESC_EMPLOYEE_TYPE = 5L;
    public static final long GENERAL_TYPE_DESC_GENDER = 1L;
    public static final long GENERAL_TYPE_DESC_MARITAL_STATUS = 21L;
    public static final long GENERAL_TYPE_DESC_RELIGION = 35L;
    public static final long GENERAL_TYPE_DESC_NATIONALITY = 23L;
    public static final long GENERAL_TYPE_DESC_RELATIONSHIP = 33L;
    public static final long GENERAL_TYPE_DESC_SOCIAL_CATEGORY = 39L;
    public static final long GENERAL_TYPE_DESC_TYPES_OF_DISABILITY = 22L;
    public static final long GENERAL_TYPE_DESC_SOURCE_OF_RECRUITMENT = 43L;
    public static final long GENERAL_TYPE_DESC_QUARTER_TYPE = 25L;
    public static final long GENERAL_TYPE_DESC_GROUP_TYPE = 30L;
    public static final long GENERAL_TYPE_DESC_HEIGHT_CM_FEET = 34L;
    public static final long GENERAL_TYPE_DESC_JOINING_TIME = 36L;
    public static final long GENERAL_TYPE_DESC_PAYSLIP_AUTHORITY = 38L;
    public static final long GENERAL_TYPE_DESC_PAYSLIP_GPF_PRAN_TYPE = 24L;
    public static final long GENERAL_TYPE_DESC_PAY_COMMISSION = 13L;
    public static final long GENERAL_TYPE_DESC_PAY_LEVEL = 31L;
    public static final long GENERAL_TYPE_DESC_CTA_ENTITLEMENT = 44L;
    public static final long GENERAL_TYPE_DESC_DOCUMENT_TYPE = 45L;

    //entitlement yes/no general mst ids
    public static final long GENERAL_TYPE_DESC_CTA_ALLOWANCE = 53L;
    public static final long GENERAL_TYPE_DESC_DA_STOP = 54L;
    public static final long GENERAL_TYPE_DESC_MEDICAL_STOP = 55L;
    public static final long GENERAL_TYPE_DESC_NPS_OPTED = 56L;

    public static final Set<Long> ENTITLEMENT_DROPDOWN_INIT = new HashSet<>(Arrays.asList(
            GENERAL_TYPE_DESC_CTA_ALLOWANCE, GENERAL_TYPE_DESC_DA_STOP, GENERAL_TYPE_DESC_MEDICAL_STOP, GENERAL_TYPE_DESC_NPS_OPTED
    ));

    //Qualification and Educational Type Desc
    public static final long GENERAL_TYPE_DESC_QUALIFICATION = 49L;
    public static final long GENERAL_TYPE_DESC_MARKS_CGPA = 50L;

    //basic payhead id
    public static final long BASIC_PAYHEAD_ID = 1;
    //payhead type
    public static final long PAYHEAD_TYPE_PAYMENT = 26;
    public static final long PAYHEAD_TYPE_RECOVERY = 27;
    public static final long PAYHEAD_TYPE_DEDUCTION = 28;

    //payhead
    public static final long PAYHEAD_DA = 13;
    public static final long PAYHEAD_NPS = 26;


    public static final long FINANCIAL_YEAR = 4;

    public static final HashSet<Long> TYPE_DESC_LIST_DROPDOWN = new HashSet<>();
    public static final HashSet<Long> EDUCATIONAL_DETAILS_LIST_DROPDOWN = new HashSet<>();

    static {
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_CASTE);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_PREFIX);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_BLOOD_GROUP);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_EMPLOYEE_TYPE);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_GENDER);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_MARITAL_STATUS);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_RELIGION);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_NATIONALITY);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_RELATIONSHIP);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_SOCIAL_CATEGORY);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_TYPES_OF_DISABILITY);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_SOURCE_OF_RECRUITMENT);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_QUARTER_TYPE);
//        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_GROUP_TYPE);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_HEIGHT_CM_FEET);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_JOINING_TIME);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_PAYSLIP_AUTHORITY);
        TYPE_DESC_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_PAYSLIP_GPF_PRAN_TYPE);

        EDUCATIONAL_DETAILS_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_QUALIFICATION);
        EDUCATIONAL_DETAILS_LIST_DROPDOWN.add(GENERAL_TYPE_DESC_MARKS_CGPA);
    }

    public static final int RETIREMENT_AGE = 60;

    public static final int HEIGHT_MEASURE_CM = 144;
    public static final int HEIGHT_MEASURE_FEET = 145;

    // Required document IDs
    public static final Set<Long> REQUIRED_DOCUMENT_IDS = new HashSet<>(Arrays.asList(
            216L, 217L, 212L, 219L/* , 213L, 214L, 215L, 218L, 220L, 221L, 222L, 223L, */
    ));
}
