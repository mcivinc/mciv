package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.MCFlaskVersion;
import nebula.soft.flask.utils.*;

public class StringFlask extends AbstractFlask {
	public StringFlask(String v_) {
		super(MCFlaskType.STRING);
		_v = v_;
	}
	
	public StringFlask(String v_, String name_) {
		super(MCFlaskType.STRING, name_);
		_v = v_;
	}
	
	protected StringFlask(String v_, List<String> names_) {
		super(MCFlaskType.STRING, names_);
		_v = v_;
	}
	
	public String getString() {
		return _v;
	}
	
	public MCFlask clone() {
		return new StringFlask(_v, _names);
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// *************************************************************
	// protected methods
	// *************************************************************
	
	protected int innerCompareTo(MCFlask v_) {
		try {
			return(_v.compareTo(v_.getString()));
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in StringFlask.innerCompareTo(v)", e_);
		}
	}
		
	private final String _v;
	private static final long serialVersionUID = 6712196907885932300L;
}
//
// END
//

