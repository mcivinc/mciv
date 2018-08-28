package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class MapFlask extends AbstractFlask {
	
	public MapFlask() {
		super(MCFlaskType.MAP);
		_v = new Hashtable<String,MCFlask>();
	}
	
	public MapFlask(String name_) {
		super(MCFlaskType.MAP, name_);
		_v = new Hashtable<String,MCFlask>();
	}
	
	protected MapFlask(List<String> names_) {
		super(MCFlaskType.MAP, names_);
		_v = new Hashtable<String,MCFlask>();
	}
	
	// *****************************************************************
	// public methods
	// *****************************************************************
	
	public int size() {
		return _v.size();
	}
	
	public MCFlask put(String key_, MCFlask v_) throws MCFlaskException {
		if (key_ == null)
			throw new MCFlaskException(this, "Passed null key into put(key,value)");
		if (v_ == null)
			throw new MCFlaskException(this, "Passed null value into put(key,value)");
		return _v.put(key_,v_);
	}
	
	public MCFlask get(String key_) throws MCFlaskException {
		if (key_ == null)
			throw new MCFlaskException(this, "Passed null key into get(key)");
		return _v.get(key_);
	}
	
	public boolean hasKey(String key_) throws MCFlaskException {
		if (key_ == null)
			throw new MCFlaskException(this, "Passed null key into hasKey(key)");
		return _v.containsKey(key_);
	}
	
	public Set<String> keys() {
		return _v.keySet();
	}
	
	public synchronized MCFlask clone() {
		try {
			MCFlask rv = new MapFlask(_names);
			for (String k : keys())
				rv.put(k, get(k).clone());
			return rv;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in MapFlask.clone()", e_);
		}
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// **********************************************************
	// protected methods
	// **********************************************************
	
	protected synchronized int innerCompareTo(MCFlask v_) {
		try {
			if (size()>v_.size())
				return 1;
			if (size()<v_.size())
				return -1;
			TreeSet<String> myKeys = new TreeSet<String>(keys());
			TreeSet<String> vKeys = new TreeSet<String>(v_.keys());
			// TODO
			
			return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in MapFlask.innerCompareTo(v)", e_);
		}
	}
	
	// ******************************************************
	// data members
	// ******************************************************
	
	private final Map<String,MCFlask> _v;
	private static final long serialVersionUID = 6712446747885932347L;
}
