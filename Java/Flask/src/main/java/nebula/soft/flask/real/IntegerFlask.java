package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class IntegerFlask extends AbstractFlask {
	public IntegerFlask(int v_) {
		super(MCFlaskType.INTEGER);
		_v = v_;
	}
	
	public IntegerFlask(int v_, String name_) {
		super(MCFlaskType.INTEGER, name_);
		_v = v_;
	}
	
	protected IntegerFlask(int v_, List<String> names_) {
		super(MCFlaskType.INTEGER, names_);
		_v = v_;
	}
	
	public int getInt() {
		return _v;
	}
	
	public MCFlask clone() {
		return new IntegerFlask(_v, _names);
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
			if (_v>v_.getInt()) 
				return 1;
			if (_v<v_.getInt())
				return -1;
			else
				return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in IntegerFlask.innerCompareTo(v)", e_);
		}
	}
	
	// *************************************************************
	// data members
	// *************************************************************
	
	private int _v;
	private static final long serialVersionUID = 6640846996946340328L;
}
//
// END
//

