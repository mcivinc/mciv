package nebula.soft.flask;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import javax.xml.stream.*;

import nebula.soft.flask.real.BooleanFlask;
import nebula.soft.flask.real.BooleanMatrixFlask;
import nebula.soft.flask.real.DoubleFlask;
import nebula.soft.flask.real.DoubleMatrixFlask;
import nebula.soft.flask.real.IntegerFlask;
import nebula.soft.flask.real.IntegerMatrixFlask;
import nebula.soft.flask.real.ListFlask;
import nebula.soft.flask.real.MapFlask;
import nebula.soft.flask.real.StringFlask;
import nebula.soft.flask.real.StringMatrixFlask;
import nebula.soft.flask.utils.FlaskXMLUtils;

public final class MCFlaskFactory {
	public static MCFlask make(boolean v_) {
		return new BooleanFlask(v_);
	}
	
	public static MCFlask make(boolean v_, String name_) {
		return new BooleanFlask(v_, name_);
	}
	
	public static MCFlask make(int v_) {
		return new IntegerFlask(v_);
	}
	
	public static MCFlask make(int v_, String name_) {
		return new IntegerFlask(v_, name_);
	}
	
	public static MCFlask make(double v_) {
		return new DoubleFlask(v_);
	}
	
	public static MCFlask make(double v_, String name_) {
		return new DoubleFlask(v_, name_);
	}
	
	public static MCFlask make(String v_) {
		return new StringFlask(v_);
	}
	
	public static MCFlask make(String v_, String name_) {
		return new StringFlask(v_, name_);
	}
	
	public static MCFlask makeBooleanMatrix(List<List<Boolean>> v_) {
		return new BooleanMatrixFlask(v_);
	}
	
	public static MCFlask makeBooleanMatrix(List<List<Boolean>> v_, String name_) {
		return new BooleanMatrixFlask(v_, name_);
	}
	
	public static MCFlask makeIntegerMatrix(List<List<Integer>> v_) {
		return new IntegerMatrixFlask(v_);
	}
	
	public static MCFlask makeIntegerMatrix(List<List<Integer>> v_, String name_) {
		return new IntegerMatrixFlask(v_, name_);
	}
	
	public static MCFlask makeDoubleMatrix(List<List<Double>> v_) {
		return new DoubleMatrixFlask(v_);
	}
	
	public static MCFlask makeDoubleMatrix(List<List<Double>> v_, String name_) {
		return new DoubleMatrixFlask(v_, name_);
	}
	
	public static MCFlask makeStringMatrix(List<List<String>> v_) {
		return new StringMatrixFlask(v_);
	}
	
	public static MCFlask makeStringMatrix(List<List<String>> v_, String name_) {
		return new StringMatrixFlask(v_, name_);
	}
	
	public static MCFlask make(int nrows_, int ncols_, MCFlaskType t_) {
		if (nrows_ < 1)
			throw new MCFlaskRuntimeException("passed nrows: "+nrows_+"<1 into FlaskFactory.make(nrows,ncols,FlaskType)");
		if (ncols_ < 1)
			throw new MCFlaskRuntimeException("passed ncols: "+ncols_+"<1 into FlaskFactory.make(nrows,ncols,FlaskType)");
		switch (t_) {
		case BOOLEAN_MATRIX:
			return new BooleanMatrixFlask(makeMatrix(nrows_, ncols_, new Boolean(MCFlaskDictionary.DEFAULT_BOOLEAN)));
		case INTEGER_MATRIX:
			return new IntegerMatrixFlask(makeMatrix(nrows_, ncols_, new Integer(MCFlaskDictionary.DEFAULT_INTEGER)));
		case DOUBLE_MATRIX:
			return new DoubleMatrixFlask(makeMatrix(nrows_, ncols_, new Double(MCFlaskDictionary.DEFAULT_DOUBLE)));
		case STRING_MATRIX:
			return new StringMatrixFlask(makeMatrix(nrows_, ncols_, new String(MCFlaskDictionary.DEFAULT_STRING)));
		default:
			break;
		}
		throw new MCFlaskRuntimeException("FlaskFactory.make(nrows, ncols, FlaskType="+t_.toString()+" is not supported");
	}
	
	public static MCFlask make(int nrows_, int ncols_, MCFlaskType t_, String name_) {
		if (nrows_ < 1)
			throw new MCFlaskRuntimeException("passed nrows: "+nrows_+"<1 into FlaskFactory.make(nrows,ncols,FlaskType,name)");
		if (ncols_ < 1)
			throw new MCFlaskRuntimeException("passed ncols: "+ncols_+"<1 into FlaskFactory.make(nrows,ncols,FlaskType,name)");
		switch (t_) {
		case BOOLEAN_MATRIX:
			return new BooleanMatrixFlask(makeMatrix(nrows_, ncols_, new Boolean(MCFlaskDictionary.DEFAULT_BOOLEAN)), name_);
		case INTEGER_MATRIX:
			return new IntegerMatrixFlask(makeMatrix(nrows_, ncols_, new Integer(MCFlaskDictionary.DEFAULT_INTEGER)), name_);
		case DOUBLE_MATRIX:
			return new DoubleMatrixFlask(makeMatrix(nrows_, ncols_, new Double(MCFlaskDictionary.DEFAULT_DOUBLE)), name_);
		case STRING_MATRIX:
			return new StringMatrixFlask(makeMatrix(nrows_, ncols_, new String(MCFlaskDictionary.DEFAULT_STRING)), name_);
		default:
			break;
		}
		throw new MCFlaskRuntimeException("FlaskFactory.make(nrows, ncols, FlaskType="+t_.toString()+" is not supported");
	}
	
	public static MCFlask make(MCFlaskType t_) {
		switch (t_) {
		case LIST:
			return new ListFlask();
		case MAP:
			return new MapFlask();
		default:
			break;
		}
		throw new MCFlaskRuntimeException("FlaskFactory.make(FlaskType) does not support: "+t_.toString());
	}
	
	public static MCFlask make(MCFlaskType t_, String name_) {
		switch (t_) {
		case LIST:
			return new ListFlask(name_);
		case MAP:
			return new MapFlask(name_);
		default:
			break;
		}
		throw new MCFlaskRuntimeException("FlaskFactory.make(FlaskType,name) does not support: "+t_.toString());
	}
	
	public static MCFlask hydrate(String xml_) throws MCFlaskException {
		if (xml_ == null)
			throw new MCFlaskException("Passed null xml string into FlaskFactory.hydrate(string)");
		try {
			XMLInputFactory xmlif = XMLInputFactory.newInstance();
			XMLStreamReader xtr = xmlif.createXMLStreamReader(new StringReader(xml_));
			MCFlask rv = hydrate(xtr);
			xtr.close();
			return rv;
		} catch (Exception e_) {
			throw new MCFlaskException("FlaskFactory.hydrate(xmlstring) failed", e_);
		}
	}
	
	// ************************************************
	// private methods
	// ************************************************
	
	private static <T> List<List<T>> makeMatrix(int nrows_, int ncols_, T t_) {
		List<List<T>> rv = new Vector<List<T>>();
		for (int r=0; r<nrows_; r++) {
			List<T> row = new Vector<T>();
			for (int c=0; c<ncols_; c++)
				row.add(t_);
			rv.add(row);
		}
		return rv;
	}
	
	private static MCFlask hydrate(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		MCFlask rv = null;
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.BooleanFlask.ELEM_NAME)) {
					rv = hydrateBoolean(xtr_);
				} else if (nm.equals(MCFlaskDictionary.IntegerFlask.ELEM_NAME)) {
					rv = hydrateInteger(xtr_);
				} else if (nm.equals(MCFlaskDictionary.DoubleFlask.ELEM_NAME)) {
					rv = hydrateDouble(xtr_);
				} else if (nm.equals(MCFlaskDictionary.StringFlask.ELEM_NAME)) {
					rv = hydrateString(xtr_);
				} else if (nm.equals(MCFlaskDictionary.BooleanMatrixFlask.ELEM_NAME)) {
					rv = hydrateBooleanMatrix(xtr_);
				} else if (nm.equals(MCFlaskDictionary.IntegerMatrixFlask.ELEM_NAME)) {
					rv = hydrateIntegerMatrix(xtr_);
				} else if (nm.equals(MCFlaskDictionary.DoubleMatrixFlask.ELEM_NAME)) {
					rv = hydrateDoubleMatrix(xtr_);
				} else if (nm.equals(MCFlaskDictionary.StringMatrixFlask.ELEM_NAME)) {
					rv = hydrateStringMatrix(xtr_);
				} else if (nm.equals(MCFlaskDictionary.ListFlask.ELEM_NAME)) {
					rv = hydrateList(xtr_);
				} else if (nm.equals(MCFlaskDictionary.MapFlask.ELEM_NAME)) {
					rv = hydrateMap(xtr_);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			default:
			}
			if (rv != null) break;
			xtr_.next();
		}
		return rv;
	}
	
	private static MCFlask hydrateBoolean(XMLStreamReader xtr_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		String val = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateBoolean(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		if (val == null)
			throw new MCFlaskException("Did not found "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" attribute when parsing xml in FlaskFactory.hydrateBoolean()");
		if (names == null) {
			return MCFlaskFactory.make(Boolean.parseBoolean(val));
		} else {
			return MCFlaskFactory.make(Boolean.parseBoolean(val), names);
		}
	}
	
	private static MCFlask hydrateInteger(XMLStreamReader xtr_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		String val = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateInteger(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		if (val == null)
			throw new MCFlaskException("Did not found "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" attribute when parsing xml in FlaskFactory.hydrateInteger()");
		if (names == null) {
			return MCFlaskFactory.make(Integer.parseInt(val));
		} else {
			return MCFlaskFactory.make(Integer.parseInt(val), names);
		}
	}
	
	private static MCFlask hydrateDouble(XMLStreamReader xtr_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		String val = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateDouble(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		if (val == null)
			throw new MCFlaskException("Did not found "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" attribute when parsing xml in FlaskFactory.hydrateDouble()");
		if (names == null) {
			return MCFlaskFactory.make(Double.parseDouble(val));
		} else {
			return MCFlaskFactory.make(Double.parseDouble(val), names);
		}
	}
	
	private static MCFlask hydrateString(XMLStreamReader xtr_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		String val = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateString(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		if (val == null)
			throw new MCFlaskException("Did not found "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" attribute when parsing xml in FlaskFactory.hydrateString()");
		if (names == null) {
			return MCFlaskFactory.make(val);
		} else {
			return MCFlaskFactory.make(val, names);
		}
	}
	
	private static MCFlask hydrateBooleanMatrix(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateBooleanMatrix(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.BooleanMatrixFlask.NUM_ROWS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.BooleanMatrixFlask.NUM_ROWS_NAME+" element");
		int nr = Integer.parseInt(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.BooleanMatrixFlask.NUM_COLS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.BooleanMatrixFlask.NUM_COLS_NAME+" element");
		int nc = Integer.parseInt(tmp);

		Map<Integer,List<Boolean>> indx2list = new TreeMap<Integer,List<Boolean>>();
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.BooleanMatrixFlask.ROW_ELEM_NAME)) {
					addBooleanRow(xtr_, indx2list);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.BooleanMatrixFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (indx2list.size() != nr)
			throw new MCFlaskException("Expected "+nr+" rows but hydrated "+indx2list.size());
		List<List<Boolean>> m = new Vector<List<Boolean>>();
		for (Map.Entry<Integer,List<Boolean>> en : indx2list.entrySet())
			m.add(en.getValue());
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.makeBooleanMatrix(m);
		} else {
			rv = MCFlaskFactory.makeBooleanMatrix(m, names);
		}
		if (rv.columns() != nc)
			throw new MCFlaskException("Expected "+nc+" columns but hydrated "+rv.columns());
		return rv;
	}
	
	private static MCFlask hydrateIntegerMatrix(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateIntegerMatrix(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.IntegerMatrixFlask.NUM_ROWS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.IntegerMatrixFlask.NUM_ROWS_NAME+" element");
		int nr = Integer.parseInt(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.IntegerMatrixFlask.NUM_COLS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.IntegerMatrixFlask.NUM_COLS_NAME+" element");
		int nc = Integer.parseInt(tmp);

		Map<Integer,List<Integer>> indx2list = new TreeMap<Integer,List<Integer>>();
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.IntegerMatrixFlask.ROW_ELEM_NAME)) {
					addIntegerRow(xtr_, indx2list);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.IntegerMatrixFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (indx2list.size() != nr)
			throw new MCFlaskException("Expected "+nr+" rows but hydrated "+indx2list.size());
		List<List<Integer>> m = new Vector<List<Integer>>();
		for (Map.Entry<Integer,List<Integer>> en : indx2list.entrySet())
			m.add(en.getValue());
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.makeIntegerMatrix(m);
		} else {
			rv = MCFlaskFactory.makeIntegerMatrix(m, names);
		}
		if (rv.columns() != nc)
			throw new MCFlaskException("Expected "+nc+" columns but hydrated "+rv.columns());
		return rv;
	}
	
	private static MCFlask hydrateDoubleMatrix(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateDoubleMatrix(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.DoubleMatrixFlask.NUM_ROWS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.DoubleMatrixFlask.NUM_ROWS_NAME+" element");
		int nr = Integer.parseInt(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.DoubleMatrixFlask.NUM_COLS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.DoubleMatrixFlask.NUM_COLS_NAME+" element");
		int nc = Integer.parseInt(tmp);

		Map<Integer,List<Double>> indx2list = new TreeMap<Integer,List<Double>>();
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.DoubleMatrixFlask.ROW_ELEM_NAME)) {
					addDoubleRow(xtr_, indx2list);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.DoubleMatrixFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (indx2list.size() != nr)
			throw new MCFlaskException("Expected "+nr+" rows but hydrated "+indx2list.size());
		List<List<Double>> m = new Vector<List<Double>>();
		for (Map.Entry<Integer,List<Double>> en : indx2list.entrySet())
			m.add(en.getValue());
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.makeDoubleMatrix(m);
		} else {
			rv = MCFlaskFactory.makeDoubleMatrix(m, names);
		}
		if (rv.columns() != nc)
			throw new MCFlaskException("Expected "+nc+" columns but hydrated "+rv.columns());
		return rv;
	}
	
	private static MCFlask hydrateStringMatrix(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateStringMatrix(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.StringMatrixFlask.NUM_ROWS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.StringMatrixFlask.NUM_ROWS_NAME+" element");
		int nr = Integer.parseInt(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.StringMatrixFlask.NUM_COLS_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.StringMatrixFlask.NUM_COLS_NAME+" element");
		int nc = Integer.parseInt(tmp);

		Map<Integer,List<String>> indx2list = new TreeMap<Integer,List<String>>();
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.StringMatrixFlask.ROW_ELEM_NAME)) {
					addStringRow(xtr_, indx2list);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.StringMatrixFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (indx2list.size() != nr)
			throw new MCFlaskException("Expected "+nr+" rows but hydrated "+indx2list.size());
		List<List<String>> m = new Vector<List<String>>();
		for (Map.Entry<Integer,List<String>> en : indx2list.entrySet())
			m.add(en.getValue());
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.makeStringMatrix(m);
		} else {
			rv = MCFlaskFactory.makeStringMatrix(m, names);
		}
		if (rv.columns() != nc)
			throw new MCFlaskException("Expected "+nc+" columns but hydrated "+rv.columns());
		return rv;
	}
	
	private static MCFlask hydrateList(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateList(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.SIZE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.SIZE_ATTRIB_NAME+" element");
		int sz = Integer.parseInt(tmp);
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.make(MCFlaskType.LIST);
		} else {
			rv = MCFlaskFactory.make(MCFlaskType.LIST, names);
		}
		
		
		Map<Integer,MCFlask> indx2flask = new TreeMap<Integer,MCFlask>();
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.ListFlask.SUB_ELEM_NAME)) {
					addFlask(xtr_, indx2flask);
				} else {
					throw new MCFlaskException("Unsupported element: "+nm);
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.ListFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (indx2flask.size() != sz)
			throw new MCFlaskException("Expected "+sz+" rows but hydrated "+indx2flask.size());
		for (Map.Entry<Integer,MCFlask> en : indx2flask.entrySet())
			rv.append(en.getValue());
		return rv;
	}
	
	private static MCFlask hydrateMap(XMLStreamReader xtr_) throws MCFlaskException, XMLStreamException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String ver = attribKey2Val.get(MCFlaskDictionary.VERSION_ATTRIB_NAME);
		if (ver != null)
			if (Integer.parseInt(ver) != MCFlaskVersion.VERSION_AS_INT)
				throw new MCFlaskException("Version mismatch in FlaskFactory.hydrateMap(), found: "+ver+", expected: "+MCFlaskVersion.VERSION);
		String names = attribKey2Val.get(MCFlaskDictionary.NAMES_ATTRIB_NAME);
		String tmp = attribKey2Val.get(MCFlaskDictionary.SIZE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.SIZE_ATTRIB_NAME+" element");
		int sz = Integer.parseInt(tmp);
		MCFlask rv = null;
		if (names == null) {
			rv = MCFlaskFactory.make(MCFlaskType.MAP);
		} else {
			rv = MCFlaskFactory.make(MCFlaskType.MAP, names);
		}
		
		
		String key = null;
		boolean foundEnd = false;
		xtr_.next();
		while(xtr_.hasNext()) {
			switch (xtr_.getEventType()) {
			case XMLStreamConstants.START_ELEMENT:
				addMapping(xtr_, rv);
				break;
			case XMLStreamConstants.END_ELEMENT:
				String nm = xtr_.getLocalName();
				if (nm.equals(MCFlaskDictionary.MapFlask.ELEM_NAME))
					foundEnd = true;
				break;
			default:
			}
			xtr_.next();
			if (foundEnd) break;
		}
		if (rv.size() != sz)
			throw new MCFlaskException("Expected "+sz+" mappings but hydrated "+rv.size());
		return rv;
	}
	
	private static void addFlask(XMLStreamReader xtr_, Map<Integer,MCFlask> indx2flask_) throws XMLStreamException, MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String i = attribKey2Val.get(MCFlaskDictionary.ListFlask.INDX_NAME);
		if (i == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.ListFlask.INDX_NAME+" attribute in list element");
		xtr_.next();
		if (!xtr_.hasNext())
			throw new MCFlaskRuntimeException("internal error in MCFlaskFactory.addFlask()");
		MCFlask rv = hydrate(xtr_);
		indx2flask_.put(new Integer(i), rv);
	}
	
	private static void addMapping(XMLStreamReader xtr_, MCFlask f_) throws XMLStreamException, MCFlaskException {
		String key = xtr_.getLocalName();
		xtr_.next();
		if (!xtr_.hasNext())
			throw new MCFlaskRuntimeException("internal error in MCFlaskFactory.addFlask()");
		MCFlask rv = hydrate(xtr_);
		f_.put(key, rv);
	}
	
	private static Map<String,String> attributes(XMLStreamReader xtr_) {
		Map<String,String> rv = new HashMap<String,String>();
		int n = xtr_.getAttributeCount();
		for (int i=0; i<n; i++) {
			String lname = xtr_.getAttributeLocalName(i);
			String val = xtr_.getAttributeValue(i);
			rv.put(lname,val);
		}
		return rv;
	}

	private static void addBooleanRow(XMLStreamReader xtr_, Map<Integer,List<Boolean>> indx2list_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String tmp = attribKey2Val.get(MCFlaskDictionary.BooleanMatrixFlask.INDX_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.BooleanMatrixFlask.INDX_NAME+" in matrix row element");
		Integer k = new Integer(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" in matrix row element");
		List<String> l = FlaskXMLUtils.fromCsv(tmp);
		List<Boolean> row = new Vector<Boolean>();
		for (String el : l)
			row.add(new Boolean(el));
		indx2list_.put(k, row);
	}
	
	private static void addDoubleRow(XMLStreamReader xtr_, Map<Integer,List<Double>> indx2list_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String tmp = attribKey2Val.get(MCFlaskDictionary.DoubleMatrixFlask.INDX_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.DoubleMatrixFlask.INDX_NAME+" in matrix row element");
		Integer k = new Integer(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" in matrix row element");
		List<String> l = FlaskXMLUtils.fromCsv(tmp);
		List<Double> row = new Vector<Double>();
		for (String el : l)
			row.add(new Double(el));
		indx2list_.put(k, row);
	}
	
	private static void addIntegerRow(XMLStreamReader xtr_, Map<Integer,List<Integer>> indx2list_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String tmp = attribKey2Val.get(MCFlaskDictionary.IntegerMatrixFlask.INDX_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.IntegerMatrixFlask.INDX_NAME+" in matrix row element");
		Integer k = new Integer(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" in matrix row element");
		List<String> l = FlaskXMLUtils.fromCsv(tmp);
		List<Integer> row = new Vector<Integer>();
		for (String el : l)
			row.add(new Integer(el));
		indx2list_.put(k, row);
	}
	
	private static void addStringRow(XMLStreamReader xtr_, Map<Integer,List<String>> indx2list_) throws MCFlaskException {
		Map<String,String> attribKey2Val = attributes(xtr_);
		String tmp = attribKey2Val.get(MCFlaskDictionary.StringMatrixFlask.INDX_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.StringMatrixFlask.INDX_NAME+" in matrix row element");
		Integer k = new Integer(tmp);
		tmp = attribKey2Val.get(MCFlaskDictionary.VALUE_ATTRIB_NAME);
		if (tmp == null)
			throw new MCFlaskException("Missing "+MCFlaskDictionary.VALUE_ATTRIB_NAME+" in matrix row element");
		List<String> row = FlaskXMLUtils.fromCsv(tmp);
		indx2list_.put(k, row);
	}
	
}
//
// END
//

