package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;
import javax.xml.stream.*;
import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class BooleanFlask extends AbstractFlask {
	public BooleanFlask(boolean v_) {
		super(MCFlaskType.BOOLEAN);
		_v = v_;
	}
	
	public BooleanFlask(boolean v_, String name_) {
		super(MCFlaskType.BOOLEAN, name_);
		_v = v_;
	}
	
	protected BooleanFlask(boolean v_, List<String> names_) {
		super(MCFlaskType.BOOLEAN, names_);
		_v = v_;
	}
	
	public boolean getBoolean() {
		return _v;
	}
	
	public MCFlask clone() {
		try {
			return new BooleanFlask(_v, _names);
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal logic error in BooleanFlask.clone()", e_);
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// ***********************************************************
	// protected methods
	// ***********************************************************
	
	protected int innerCompareTo(MCFlask v_) {
		try {
			if (_v==v_.getBoolean())
				return 0;
			if (_v==false) 
				return -1;
			else 
				return 1;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal logic error in BooleanFlask.innerCompareTo(v)", e_);
		}
	}
	
	// ***********************************************************
	// data members
	// ***********************************************************
	
	private final boolean _v;
	private static final long serialVersionUID = 6712196907885932200L;
}
//
// END
//

