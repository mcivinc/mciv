package nebula.soft.flask.real;

import nebula.soft.flask.MCFlaskType;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;

public class GenericMatrixFlask<T extends Comparable<T>> 
implements Serializable, Cloneable, Comparable<GenericMatrixFlask<T>> {
	public GenericMatrixFlask(List<List<T>> v_) {
		// verify each row has the same number of columns
		if (v_ == null)
			throw new MCFlaskRuntimeException("Passed null into constructor");
		int nrows = v_.size();
		if (nrows > 0) {
			int ncols = v_.get(0).size();
			for (int r = 1; r < nrows; r++) {
				if (v_.get(r).size() != ncols)
					throw new MCFlaskRuntimeException("Inconsistent column size in constructor");
			}
		}
		_v = v_;
	}
	
	public int rows() {
		return _v.size();
	}
	
	public int columns() {
		if (rows() == 0)
			return 0;
		return _v.get(0).size();
	}
	
	public synchronized List<List<T>> get() {
		return _v;
	}
	
	public synchronized T get(int r_, int c_) {
		return _v.get(r_).get(c_);
	}

	public synchronized T set(int r_, int c_, T v_) {
		return _v.get(r_).set(c_, v_);
	}
	
	public synchronized GenericMatrixFlask<T> clone() {
		int nrows = rows();
		int ncols = columns();
		List<List<T>> rv = new Vector<List<T>>(nrows);
		for (int i=0; i<nrows; i++) {
			List<T> tmp = new Vector<T>(ncols);
			for (int j=0; j<ncols; j++)
				tmp.add(get(i,j));  // TODO check
			rv.add(tmp);
		}
		try {
			return new GenericMatrixFlask<T>(rv);
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException("Internal logic error in GenericMatrixFlask.clone()", e_);
		}
	}
	
	public synchronized String serialize() {
		// TODO
		return null;
	}
	
	@Override
	public synchronized int compareTo(GenericMatrixFlask<T> o_) {
		if (rows() > o_.rows())
			return 1;
		if (rows() < o_.rows())
			return -1;
		if (columns() > o_.columns())
			return 1;
		if (columns() < o_.columns())
			return -1;
		int nrows = rows();
		int ncols = columns();
		for (int r=0; r<nrows; r++)
			for (int c=0; c<ncols; c++) {
				int tmp = get(r,c).compareTo(o_.get(r,c));
				if (tmp != 0)
					return tmp;
			}
		return 0;
	}
	
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(_v);
		return sb.toString();
	}
	
	// **********************************************************************
	// private methods
	// **********************************************************************
	
	private static <T> MCFlaskType type2FlaskType(T t_) {
		if (t_ instanceof Boolean)
			return MCFlaskType.BOOLEAN_MATRIX;
		else if (t_ instanceof Double)
			return MCFlaskType.DOUBLE_MATRIX;
		else if (t_ instanceof Integer)
			return MCFlaskType.INTEGER_MATRIX;
		else
			return MCFlaskType.STRING_MATRIX;
	}
	
	// *********************************************************************
	// data members
	// *********************************************************************
	
	private final List<List<T>> _v;
	private static final long serialVersionUID = 6712196907985782200L;
	
}
