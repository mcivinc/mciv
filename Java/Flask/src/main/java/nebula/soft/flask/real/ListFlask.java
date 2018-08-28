package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.MCFlaskVersion;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class ListFlask extends AbstractFlask {

	public ListFlask() {
		super(MCFlaskType.LIST);
		_v = new Vector<MCFlask>();
	}
	
	public ListFlask(String name_) {
		super(MCFlaskType.LIST, name_);
		_v = new Vector<MCFlask>();
	}
	
	protected ListFlask(List<String> names_) {
		super(MCFlaskType.LIST, names_);
		_v = new Vector<MCFlask>();
	}
	
	// *******************************************************
	// public methods
	// *******************************************************
	
	public int size() throws MCFlaskException {
		return _v.size();
	}
	
	public MCFlask get(int indx_) throws MCFlaskException {
		try {
			return _v.get(indx_);
		} catch (Exception e_) {
			throw new MCFlaskException(this, "Exception in get("+indx_+"): "+e_.getMessage(), e_);
		}
	}
	
	public MCFlask put(int indx_, MCFlask v_) throws MCFlaskException {
		if (v_ == null)
			throw new MCFlaskException(this, "Passed null value into put(indx,value)");
		try {
			return _v.set(indx_, v_);
		} catch (Exception e_) {
			throw new MCFlaskException(this, "Exception in set("+indx_+", Flask): "+e_.getMessage(), e_);
		}
	}
	
	public void append(MCFlask v_) throws MCFlaskException {
		if (v_ == null)
			throw new MCFlaskException(this, "Passed null value into append(value)");
		try {
			_v.add(v_);
		} catch (Exception e_) {
			throw new MCFlaskException(this, "Exception in append(Flask): "+e_.getMessage(), e_);
		}
	}
		
	public synchronized MCFlask clone() {
		try {
			MCFlask rv = new ListFlask(_names);
			for (int i=0; i<size(); i++)
				rv.append(_v.get(i).clone());
			return rv;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in ListFlask.clone()", e_);
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
			for (int i=0; i<size(); i++) {
				int tmp = _v.get(i).compareTo(v_.get(i));
				if (tmp != 0)
					return tmp;
			}
			return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in ListFlask.innerCompareTo(v)", e_);
		}
	}
		
	// ******************************************************
	// data members
	// ******************************************************
	
	private final List<MCFlask> _v;
	private static final long serialVersionUID = 6712446907885932300L;
}
