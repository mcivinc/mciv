package nebula.soft.flask;

public class MCFlaskDictionary {
	public static final String NAME_SEPARATOR = ":";
	public static final String FIELD_SEPARATOR = ",";
	public static final boolean DEFAULT_BOOLEAN = false;
	public static final int DEFAULT_INTEGER = -1;
	public static final double DEFAULT_DOUBLE = -1.0;
	public static final String DEFAULT_STRING = "NotSet";
	//
	public static final String VERSION_ATTRIB_NAME = "Ver";
	public static final String PARAMS_ATTRIB_NAME = "P";
	public static final String NAMES_ATTRIB_NAME = "N";
	public static final String VALUE_ATTRIB_NAME = "V";
	public static final String SIZE_ATTRIB_NAME = "s";
	
	
	public class BooleanFlask {
		public static final String ELEM_NAME = "B";
	}
	
	public class IntegerFlask {
		public static final String ELEM_NAME = "I";
	}
	
	public class DoubleFlask {
		public static final String ELEM_NAME = "D";
	}
	
	public class StringFlask {
		public static final String ELEM_NAME = "S";
	}
	
	public class BooleanMatrixFlask {
		public static final String ELEM_NAME = "BM";
		public static final String ROW_ELEM_NAME = "r";
		public static final String NUM_COLS_NAME = "nc";
		public static final String NUM_ROWS_NAME = "nr";
		public static final String INDX_NAME = "i";
	}
	
	public class IntegerMatrixFlask {
		public static final String ELEM_NAME = "IM";
		public static final String ROW_ELEM_NAME = "r";
		public static final String NUM_COLS_NAME = "nc";
		public static final String NUM_ROWS_NAME = "nr";
		public static final String INDX_NAME = "i";
	}
	
	public class DoubleMatrixFlask {
		public static final String ELEM_NAME = "DM";
		public static final String ROW_ELEM_NAME = "r";
		public static final String NUM_COLS_NAME = "nc";
		public static final String NUM_ROWS_NAME = "nr";
		public static final String INDX_NAME = "i";
	}
	
	public class StringMatrixFlask {
		public static final String ELEM_NAME = "SM";
		public static final String ROW_ELEM_NAME = "r";
		public static final String NUM_COLS_NAME = "nc";
		public static final String NUM_ROWS_NAME = "nr";
		public static final String INDX_NAME = "i";
	}
	
	public class ListFlask {
		public static final String ELEM_NAME = "L";
		public static final String SUB_ELEM_NAME = "e";
		public static final String INDX_NAME = "i";
	}
	
	public class MapFlask {
		public static final String ELEM_NAME = "M";
	}
}
