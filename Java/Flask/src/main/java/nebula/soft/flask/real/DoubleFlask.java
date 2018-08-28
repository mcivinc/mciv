package nebula.soft.flask.real;

import java.io.StringWriter;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

public class DoubleFlask extends AbstractFlask {
	public DoubleFlask(double v_) {
		super(MCFlaskType.DOUBLE);
		_v = v_;
	}
	
	public DoubleFlask(double v_, String name_) {
		super(MCFlaskType.DOUBLE, name_);
		_v = v_;
	}
	
	protected DoubleFlask(double v_, List<String> names_) {
		super(MCFlaskType.DOUBLE, names_);
		_v = v_;
	}
	
	public double getDouble() {
		return _v;
	}
	
	public MCFlask clone() {
		return new DoubleFlask(_v, _names);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// ***************************************************************
	// protected methods
	// ***************************************************************
	
	protected int innerCompareTo(MCFlask v_) {
		try {
			double v = v_.getDouble();
			if (_v>v)
				return 1;
			if (_v==v) 
				return 0;
			else 
				return -1;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in DoubleFlask.innerCompareTo(v)", e_);
		}
	}
	
	// **************************************************************
	// data members
	// **************************************************************
	
	private double _v;
	private static final long serialVersionUID = -5895682720913929679L;
}
//
// END
//
