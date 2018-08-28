package nebula.soft.flask.real;

import java.util.List;
import java.util.Vector;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskType;

public class DoubleMatrixFlask extends AbstractFlask {
	public DoubleMatrixFlask(List<List<Double>> v_) {
		super(MCFlaskType.DOUBLE_MATRIX);
		_v = new GenericMatrixFlask<Double>(v_);
	}
	
	public DoubleMatrixFlask(List<List<Double>> v_, String name_) {
		super(MCFlaskType.DOUBLE_MATRIX, name_);
		_v = new GenericMatrixFlask<Double>(v_);
	}
	
	protected DoubleMatrixFlask(List<List<Double>> v_, List<String> names_) {
		super(MCFlaskType.DOUBLE_MATRIX, names_);
		_v = new GenericMatrixFlask<Double>(v_);
	}
	
	public int rows() {
		return _v.rows();
	}
	
	public int columns() {
		return _v.columns();
	}
	
	public List<List<Double>> getDoubleMatrix() {
		return _v.get();
	}
	
	public Double getDouble(int r_, int c_) {
		return _v.get(r_, c_);
	}
	
	public Double set(int r_, int c_, Double v_) {
		return _v.set(r_, c_, v_);
	}
	
	public synchronized MCFlask clone() {
		List<List<Double>> v = _v.clone().get();
		return new DoubleMatrixFlask(v, _names);
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer("{");
		sb.append(super.toString()).append(", value: `").append(_v).append("'}");
		return sb.toString();
	}
	
	// *****************************************************************
	// protected methods
	// *****************************************************************
	
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
					tmp = getDouble(r,c).compareTo(v_.getDouble(r,c));
					if (tmp != 0)
						return tmp;
				}
			return 0;
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(this, "Iternal error in DoubleMatrixFlask.innerCompareTo(v)", e_);
		}
	}
	
	// *****************************************************************
	// data members
	// *****************************************************************
	
	private final GenericMatrixFlask<Double> _v;
	private static final long serialVersionUID = 6412194907885782200L;

}
