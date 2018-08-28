package nebula.soft.flask.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLStreamWriter;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskRuntimeException;
import nebula.soft.flask.MCFlaskVersion;

public class FlaskXMLUtils {
	
	public static void addElem(boolean isTopLevel_, XMLStreamWriter xtw_, MCFlask f_) throws MCFlaskException {
		if (f_ == null) 
			throw new MCFlaskException("Passed null Flask to FlaskXMLUtils.addElem(.)");
		if (xtw_ == null)
			throw new MCFlaskException("Passed null XMLStreamWriter to FlaskXMLUtils.addElem(.)");
		try {
			switch (f_.type()) {
				case BOOLEAN:
					xtw_.writeEmptyElement(MCFlaskDictionary.BooleanFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, Boolean.toString(f_.getBoolean()));
					xtw_.flush();
					return;
				case INTEGER:
					xtw_.writeEmptyElement(MCFlaskDictionary.IntegerFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
							xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, Integer.toString(f_.getInt()));
					xtw_.flush();
					return;
				case DOUBLE:
					xtw_.writeEmptyElement(MCFlaskDictionary.DoubleFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
							xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, Double.toString(f_.getDouble()));
					xtw_.flush();
					return;
				case STRING:
					xtw_.writeEmptyElement(MCFlaskDictionary.StringFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, f_.getString());
					xtw_.flush();
					return;
				case BOOLEAN_MATRIX:
					xtw_.writeStartElement(MCFlaskDictionary.BooleanMatrixFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.BooleanMatrixFlask.NUM_ROWS_NAME, Integer.toString(f_.rows()));
					xtw_.writeAttribute(MCFlaskDictionary.BooleanMatrixFlask.NUM_COLS_NAME, Integer.toString(f_.columns()));
					List<List<Boolean>> mb = f_.getBooleanMatrix();
					for (int i=0; i<mb.size(); i++) {
						xtw_.writeEmptyElement(MCFlaskDictionary.BooleanMatrixFlask.ROW_ELEM_NAME);
						xtw_.writeAttribute(MCFlaskDictionary.BooleanMatrixFlask.INDX_NAME, Integer.toString(i));
						xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, FlaskXMLUtils.list2csv(mb.get(i)));
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				case INTEGER_MATRIX:
					xtw_.writeStartElement(MCFlaskDictionary.IntegerMatrixFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.IntegerMatrixFlask.NUM_ROWS_NAME, Integer.toString(f_.rows()));
					xtw_.writeAttribute(MCFlaskDictionary.IntegerMatrixFlask.NUM_COLS_NAME, Integer.toString(f_.columns()));
					List<List<Integer>> mi = f_.getIntegerMatrix();
					for (int i=0; i<mi.size(); i++) {
						xtw_.writeEmptyElement(MCFlaskDictionary.IntegerMatrixFlask.ROW_ELEM_NAME);
						xtw_.writeAttribute(MCFlaskDictionary.IntegerMatrixFlask.INDX_NAME, Integer.toString(i));
						xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, FlaskXMLUtils.list2csv(mi.get(i)));
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				case DOUBLE_MATRIX:
					xtw_.writeStartElement(MCFlaskDictionary.DoubleMatrixFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.DoubleMatrixFlask.NUM_ROWS_NAME, Integer.toString(f_.rows()));
					xtw_.writeAttribute(MCFlaskDictionary.DoubleMatrixFlask.NUM_COLS_NAME, Integer.toString(f_.columns()));
					List<List<Double>> md = f_.getDoubleMatrix();
					for (int i=0; i<md.size(); i++) {
						xtw_.writeEmptyElement(MCFlaskDictionary.DoubleMatrixFlask.ROW_ELEM_NAME);
						xtw_.writeAttribute(MCFlaskDictionary.DoubleMatrixFlask.INDX_NAME, Integer.toString(i));
						xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, FlaskXMLUtils.list2csv(md.get(i)));
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				case STRING_MATRIX:
					xtw_.writeStartElement(MCFlaskDictionary.StringMatrixFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.StringMatrixFlask.NUM_ROWS_NAME, Integer.toString(f_.rows()));
					xtw_.writeAttribute(MCFlaskDictionary.StringMatrixFlask.NUM_COLS_NAME, Integer.toString(f_.columns()));
					List<List<String>> ms = f_.getStringMatrix();
					for (int i=0; i<ms.size(); i++) {
						xtw_.writeEmptyElement(MCFlaskDictionary.StringMatrixFlask.ROW_ELEM_NAME);
						xtw_.writeAttribute(MCFlaskDictionary.StringMatrixFlask.INDX_NAME, Integer.toString(i));
						xtw_.writeAttribute(MCFlaskDictionary.VALUE_ATTRIB_NAME, FlaskXMLUtils.list2csv(ms.get(i)));
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				case LIST:
					xtw_.writeStartElement(MCFlaskDictionary.ListFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
							xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.SIZE_ATTRIB_NAME, Integer.toString(f_.size()));
					for (int i=0; i<f_.size(); i++) {
						xtw_.writeStartElement(MCFlaskDictionary.ListFlask.SUB_ELEM_NAME);
						xtw_.writeAttribute(MCFlaskDictionary.ListFlask.INDX_NAME, Integer.toString(i));
						FlaskXMLUtils.addElem(false, xtw_, f_.get(i));
						xtw_.writeEndElement();
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				case MAP:
					xtw_.writeStartElement(MCFlaskDictionary.MapFlask.ELEM_NAME);
					if (isTopLevel_) 
						xtw_.writeAttribute(MCFlaskDictionary.VERSION_ATTRIB_NAME, MCFlaskVersion.VERSION);
					if (!f_.fullName().equals(MCFlaskDictionary.DEFAULT_STRING))
						xtw_.writeAttribute(MCFlaskDictionary.NAMES_ATTRIB_NAME, f_.fullName());
					xtw_.writeAttribute(MCFlaskDictionary.SIZE_ATTRIB_NAME, Integer.toString(f_.size()));
					for (String k : f_.keys()) {
						xtw_.writeStartElement(k);
						FlaskXMLUtils.addElem(false, xtw_, f_.get(k));
						xtw_.writeEndElement();
					}
					xtw_.writeEndElement();
					xtw_.flush();
					return;
				default:
					throw new MCFlaskException(f_, "Internal error in FlaskXMLUtils.addElem(.)");	
			}
		} catch (Exception e_) {
			throw new MCFlaskRuntimeException(f_, "BooleanFlask.serialize() failed", e_);
		}
		
		
	}
	
	public static <T> String list2csv(List<T> l_) throws MCFlaskException {
		if (l_ == null)
			throw new MCFlaskException("Passed null list into MCFlaskXMLUtils.list2csv()");
		StringBuffer sb = new StringBuffer();
		if (l_.size() > 0) {
			sb.append(l_.get(0));
			for (int i=1; i<l_.size(); i++)
				sb.append(MCFlaskDictionary.FIELD_SEPARATOR).append(l_.get(i));
		}
		return sb.toString();
	}

	public static List<String> fromCsv(String csv_) throws MCFlaskException {
		if (csv_ == null)
			throw new MCFlaskException("Passed null csv into MCFlaskXMLUtils.fromCsv()");
		List<String> rv = new Vector<String>(Arrays.asList(csv_.split(MCFlaskDictionary.FIELD_SEPARATOR)));
		return rv;
	}

}
