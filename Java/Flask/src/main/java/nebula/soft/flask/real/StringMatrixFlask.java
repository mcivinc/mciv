package nebula.soft.flask.real;

import java.util.List;
import java.util.Vector;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;

public class StringMatrixFlask extends AbstractFlask {
	public StringMatrixFlask(List<List<String>> v_) {
		super(MCFlaskType.STRING_MATRIX);
		_v = new GenericMatrixFlask<String>(v_);
	}
	
	public StringMatrixFlask(List<List<String>> v_, String name_) {
		super(MCFlaskType.STRING_MATRIX, name_);
		_v = new GenericMatrixFlask<String>(v_);
	}
	
	protected StringMatrixFlask(List<List<String>> v_, List<String> names_) {
		super(MCFlaskType.STRING_MATRIX, names_);
		_v = new GenericMatrixFlask<String>(v_);
	}
	
	public int rows() {
		return _v.rows();
	}
	
	public int columns() {
		return _v.columns();
	}
	
	public List<List<String>> getStringMatrix() {
		return _v.get();
	}
	
	public String getString(int r_, int c_) {
		return _v.get(r_, c_);
	}
	
	public String set(int r_, int c_, String v_) {
		return _v.set(r_, c_, v_);
	}
	
	public synchronized MCFlask clone() {
		List<List<String>> v = _v.clone().get();
		return new StringMatrixFlask(v, _names);
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// *************************************************************
	// protected methods
	// *************************************************************
	
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
					tmp = getString(r,c).compareTo(v_.getString(r,c));
					if (tmp != 0)
						return tmp;
				}
			return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Internal error in StringMatrixFlask.innerCompareTo(v)", e_);
		}
	}
	
	private final GenericMatrixFlask<String> _v;
	private static final long serialVersionUID = 6412194907845782200L;

}
