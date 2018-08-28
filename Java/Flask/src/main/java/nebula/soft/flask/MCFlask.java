package nebula.soft.flask;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.xml.stream.XMLStreamWriter;

/**
 * Generic container 
 * - composite
 * - functor
 * 
 * @author jozef
 *
 */
public interface MCFlask extends Serializable, Cloneable, Comparable<MCFlask> {
	public MCFlaskType type();
	public String typeAsString();
	public String shortName();
	public String fullName();
	public String serialize(boolean isTopLevel_);
	public MCFlask clone();
	public int compareTo(MCFlask v_);
	//
	// Composite
	//
	public int size() throws MCFlaskException;
	// Map
	public boolean isMap();
	public MCFlask get(String key_) throws MCFlaskException;
	public MCFlask put(String key_, MCFlask v_) throws MCFlaskException;
	public boolean hasKey(String key_) throws MCFlaskException;
	public Set<String> keys() throws MCFlaskException;
	// List
	public boolean isList();
	public MCFlask get(int indx_) throws MCFlaskException;
	public MCFlask put(int indx_, MCFlask v_) throws MCFlaskException;
	public void append(MCFlask v_) throws MCFlaskException;
	//
	// Leaf
	//
	// boolean
	public boolean isBoolean();
	public boolean getBoolean() throws MCFlaskException;
	// integer
	public boolean isInteger();
	public int getInt() throws MCFlaskException;
	// double
	public boolean isDouble();
	public double getDouble() throws MCFlaskException;
	// string
	public boolean isString();
	public String getString() throws MCFlaskException;
	// boolean matrix
	public int rows() throws MCFlaskException;
	public int columns() throws MCFlaskException;
	public boolean isBooleanMatrix();
	public List<List<Boolean>> getBooleanMatrix() throws MCFlaskException;
	public Boolean getBoolean(int r_, int c_) throws MCFlaskException;
	public Boolean set(int r_, int c_, Boolean v_) throws MCFlaskException;
	// integer matrix
	public boolean isIntegerMatrix();
	public List<List<Integer>> getIntegerMatrix() throws MCFlaskException;
	public Integer getInteger(int r_, int c_) throws MCFlaskException;
	public Integer set(int r_, int c_, Integer v_) throws MCFlaskException;
	// double matrix
	public boolean isDoubleMatrix();
	public List<List<Double>> getDoubleMatrix() throws MCFlaskException;
	public Double getDouble(int r_, int c_) throws MCFlaskException;
	public Double set(int r_, int c_, Double v_) throws MCFlaskException;
	// string matrix
	public boolean isStringMatrix();
	public List<List<String>> getStringMatrix() throws MCFlaskException;
	public String getString(int r_, int c_) throws MCFlaskException;
	public String set(int r_, int c_, String v_) throws MCFlaskException;
}
//
// END
//
