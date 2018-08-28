package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class AbstractFlask implements MCFlask {
	protected AbstractFlask(MCFlaskType type_) {
		if (type_ == null)
			throw new MCFlaskRuntimeException("Passed null type into AbstractFlask(type)");
		_type = type_;
		_names = new Vector<String>();
		_names.add(MCFlaskDictionary.DEFAULT_STRING);
	}
	
	protected AbstractFlask(MCFlaskType type_, String name_) {
		if (type_ == null)
			throw new MCFlaskRuntimeException("Passed null type into AbstractFlask(type,name)");
		if (name_ == null)
			throw new MCFlaskRuntimeException("Passed null name into AbstractFlask(type,name)");
		_type = type_;
		_names = new Vector<String>();
		_names.add(name_);
	}
	
	protected AbstractFlask(MCFlaskType type_, List<String> names_) {
		if (type_ == null)
			throw new MCFlaskRuntimeException("Passed null type into AbstractFlask(type,names)");
		if (names_ == null)
			throw new MCFlaskRuntimeException("Passed null names into AbstractFlask(type,names)");
		_type = type_;
		_names = new Vector<String>();
		for (String n : names_) _names.add(n);
	}

	public MCFlaskType type() {
		return _type;
	}

	public String typeAsString() {
		return _type.toString();
	}

	public synchronized String shortName() {
		return _names.get(_names.size()-1);
	}

	public synchronized String fullName() {
		StringBuffer sb = new StringBuffer();
		boolean notFirst = false;
		for (String n : _names) {
			if (notFirst) {
				sb.append(MCFlaskDictionary.NAME_SEPARATOR);
				notFirst = true;
			}
			sb.append(n);
		}
		return sb.toString();
	}

	public String serialize(boolean isTopLevel_) {
		try {
			StringWriter sw = new StringWriter();
			XMLOutputFactory xof = XMLOutputFactory.newInstance();
			XMLStreamWriter xtw = xof.createXMLStreamWriter(sw);
			xtw.writeStartDocument();
			FlaskXMLUtils.addElem(isTopLevel_, xtw, this);
			xtw.writeEndDocument();
			xtw.flush();
			xtw.close();
			return sw.toString();
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "IntegerFlask.serialize() failed", e_);
		}
	}

	public int size() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (size) by type: "+typeAsString());
	}

	public boolean isMap() {
		if (_type==MCFlaskType.MAP) return true;
		return false;
	}

	public MCFlask get(String key_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (get) by type: "+ typeAsString());
	}

	public MCFlask put(String key_, MCFlask v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (put) by type: "+typeAsString());
	}

	public boolean hasKey(String key_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (hasKey) by type: "+typeAsString());
	}

	public Set<String> keys() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (keys) by type: "+typeAsString());
	}

	public boolean isList() {
		if (_type==MCFlaskType.LIST) return true;
		return false;
	}

	public MCFlask get(int indx_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (get) by type: "+typeAsString());
	}

	public MCFlask put(int indx_, MCFlask v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (put) by type: "+typeAsString());
	}

	public void append(MCFlask v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (append) by type: "+typeAsString());
	}
	//
	// Leaf
	//
	// boolean
	//
	public boolean isBoolean() {
		if (_type==MCFlaskType.BOOLEAN) return true;
		return false;
	}

	public boolean getBoolean() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getBoolean) by type: "+typeAsString());
	}

	//
	// integer
	//
	public boolean isInteger() {
		if (_type==MCFlaskType.INTEGER) return true;
		return false;
	}

	public int getInt() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getInt) by type: "+typeAsString());
	}
	//
	// double
	//
	public boolean isDouble() {
		if (_type==MCFlaskType.DOUBLE) return true;
		return false;
	}

	public double getDouble() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getDouble) by type: "+typeAsString());
	}
	//
	// string
	//
	public boolean isString() {
		if (_type==MCFlaskType.STRING) return true;
		return false;
	}

	public String getString() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getString) by type: "+typeAsString());
	}

	//
	// Boolean matrix
	// 
	public int rows() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (rows) by type: "+typeAsString());
	}

	public int columns() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (columns) by type: "+typeAsString());
	}

	public boolean isBooleanMatrix() {
		if (_type==MCFlaskType.BOOLEAN_MATRIX) return true;
		return false;
	}

	public List<List<Boolean>> getBooleanMatrix() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getBooleanMatrix) by type: "+typeAsString());
	}

	public Boolean getBoolean(int r_, int c_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getBoolean) by type: "+typeAsString());
	}

	public Boolean set(int r_, int c_, Boolean v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (set(r,c,Boolean)) by type: "+typeAsString());
	}

	//
	// Integer matrix
	//
	public boolean isIntegerMatrix() {
		if (_type==MCFlaskType.INTEGER_MATRIX) return true;
		return false;
	}

	public List<List<Integer>> getIntegerMatrix() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getIntegerMatrix) by type: "+typeAsString());
	}

	public Integer getInteger(int r_, int c_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getInteger) by type: "+typeAsString());
	}

	public Integer set(int r_, int c_, Integer v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (set(r,c,Integer)) by type: "+typeAsString());
	}

	//
	// Double matrix
	//
	public boolean isDoubleMatrix() {
		if (_type==MCFlaskType.DOUBLE_MATRIX) return true;
		return false;
	}

	public List<List<Double>> getDoubleMatrix() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getDoubleMatrix) by type: "+typeAsString());
	}

	public Double getDouble(int r_, int c_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getDouble) by type: "+typeAsString());
	}

	public Double set(int r_, int c_, Double v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (set(r,c,Double)) by type: "+typeAsString());
	}

	//
	// String matrix
	//
	public boolean isStringMatrix() {
		if (_type==MCFlaskType.STRING_MATRIX) return true;
		return false;
	}

	public List<List<String>> getStringMatrix() throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getStringMatrix) by type: "+typeAsString());
	}

	public String getString(int r_, int c_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (getString) by type: "+typeAsString());
	}

	public String set(int r_, int c_, String v_) throws MCFlaskException {
		throw new MCFlaskException(this, "Unsupported operation (set(r,c,String)) by type: "+typeAsString());
	}

	public MCFlask clone() {
		throw new MCFlaskRuntimeException(this, "Internal error in AbstractFlask.clone()");
	}
	
	public int compareTo(MCFlask v_) {
		if (v_==null) return 1;
		if (_type==v_.type())
			return innerCompareTo(v_);
		if (_type.ordinal()>v_.type().ordinal())
			return 1;
		else 
			return -1;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(typeAsString()).append(", names: ").append(_names.toString());
		return sb.toString();
	}

	// ***********************************************************************
	// protected methods
	// ***********************************************************************
	
	protected int innerCompareTo(MCFlask v_) {
		throw new MCFlaskRuntimeException(this, "innerCompareTo(v) not yet implemented");
	}
	
	// **************************************************************
	// data members
	// **************************************************************
	
	public final MCFlaskType _type;
	protected List<String> _names;
	private static final long serialVersionUID = 8640353980387197811L;
	
}
//
// END
//
