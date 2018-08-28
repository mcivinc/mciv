package nebula.soft.flask.real;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.utils.FlaskXMLUtils;

import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;


public class BooleanMatrixFlask extends AbstractFlask {
	public BooleanMatrixFlask(List<List<Boolean>> v_) {
		super(MCFlaskType.BOOLEAN_MATRIX);
		_v = new GenericMatrixFlask<Boolean>(v_);
	}
	
	public BooleanMatrixFlask(List<List<Boolean>> v_, String name_) {
		super(MCFlaskType.BOOLEAN_MATRIX, name_);
		_v = new GenericMatrixFlask<Boolean>(v_);
	}
	
	protected BooleanMatrixFlask(List<List<Boolean>> v_, List<String> names_) {
		super(MCFlaskType.BOOLEAN_MATRIX, names_);
		_v = new GenericMatrixFlask<Boolean>(v_);
	}
	
	public int rows() {
		return _v.rows();
	}
	
	public int columns() {
		return _v.columns();
	}
	
	public List<List<Boolean>> getBooleanMatrix() {
		return _v.get();
	}
	
	public Boolean getBoolean(int r_, int c_) {
		return _v.get(r_, c_);
	}
	
	public Boolean set(int r_, int c_, Boolean v_) {
		return _v.set(r_, c_, v_);
	}
	
	public synchronized MCFlask clone() {
		List<List<Boolean>> v = _v.clone().get();
		return new BooleanMatrixFlask(v, _names);
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}

	// ***********************************************************
	// protected methods
	// ***********************************************************
	
	protected synchronized int innerCompareTo(MCFlask v_) {
		try {
			int tmp = shortName().compareTo(v_.shortName());
			if (tmp != 0)
				return tmp;
			if (rows() > v_.rows())
				return 1;
			if (rows() < v_.rows())
				return -1;
			if (columns() > v_.columns())
				return 1;
			if (columns() < v_.columns())
				return -1;
			int nrows = rows();
			int ncols = columns();
			for (int r=0; r<nrows; r++)
				for (int c=0; c<ncols; c++) {
					tmp = getBoolean(r,c).compareTo(v_.getBoolean(r,c));
					if (tmp != 0)
						return tmp;
				}
			return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in BoooleanMatrixFlask.innerCompareTo(V)", e_);
		}
	}
	
	// **********************************************************
	// data members
	// **********************************************************
	
	private final GenericMatrixFlask<Boolean> _v;
	private static final long serialVersionUID = 6712196907885782200L;
}
