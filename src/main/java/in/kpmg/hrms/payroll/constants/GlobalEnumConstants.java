package in.kpmg.hrms.payroll.constants;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public class GlobalEnumConstants {
	
	public enum PayHeadType {
		Payment(26), Recovery(27), Deduction(28);

		private int code;

		private PayHeadType(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		private static final Map<Integer, PayHeadType> LOOKUP = new HashMap<Integer, PayHeadType>();

		static {
			for (PayHeadType value : EnumSet.allOf(PayHeadType.class)) {
				LOOKUP.put(value.getCode(), value);
			}
		}

		public static PayHeadType fromCode(int code) {
			return LOOKUP.get(code);
		}
	}
	
	public enum GeneralTypeMst {
		payHeadMst(6), GroupTypeId(30), BtDesc(29), GroupType(14), payCommissionMst(13), ctaEntitlement(44), FinancialYear(4), 
		taxRegimeType(41), taxSubCategory(42), gender(1), taxEffect(40), payHeadEffect(47), groupCodes(51),Status(52), employeeType(5);

		private int code;

		private GeneralTypeMst(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		private static final Map<Integer, GeneralTypeMst> LOOKUP = new HashMap<Integer, GeneralTypeMst>();

		static {
			for (GeneralTypeMst value : EnumSet.allOf(GeneralTypeMst.class)) {
				LOOKUP.put(value.getCode(), value);
			}
		}

		public static GeneralTypeMst fromCode(int code) {
			return LOOKUP.get(code);
		}
	}
	
	public enum FinanceheadTypeMst {
		Minor(3), Major(1), SubMajor(2), SubHead(4), Detail(5), SubDetail(6);

		private int code;

		private FinanceheadTypeMst(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}

		private static final Map<Integer, FinanceheadTypeMst> LOOKUP = new HashMap<Integer, FinanceheadTypeMst>();

		static {
			for (FinanceheadTypeMst value : EnumSet.allOf(FinanceheadTypeMst.class)) {
				LOOKUP.put(value.getCode(), value);
			}
		}

		public static FinanceheadTypeMst fromCode(int code) {
			return LOOKUP.get(code);
		}
	}
	
	public enum Status {
		Drafted(403L), Proccessed(377L);

		private Long code;

		private Status(Long code) {
			this.code = code;
		}

		public Long getCode() {
			return code;
		}

		private static final Map<Long,Status> LOOKUP = new HashMap<Long, Status>();

		static {
			for (Status value : EnumSet.allOf(Status.class)) {
				LOOKUP.put(value.getCode(), value);
			}
		}

		public static  Status fromCode(Long code) {
			return LOOKUP.get(code);
		}
	}
	
	


}
