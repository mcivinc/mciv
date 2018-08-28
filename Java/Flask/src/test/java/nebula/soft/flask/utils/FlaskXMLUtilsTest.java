package nebula.soft.flask.utils;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FlaskXMLUtilsTest {

	List<String> ls = null;
	List<Double> ld = null;
	String csv1 = "";
	String csv2 = "a,b,cc";
	
	@Before
	public void setUp() throws Exception {
		ls = new Vector<String>();
		ls.add("first");
		ls.add("second");
		ld = new Vector<Double>();
		ld.add(1.23456789123e12);
		ld.add(1.34567891234e13);
	}

	@After
	public void tearDown() throws Exception {
		ls = null;
		ld = null;
	}

	@Test
	public void testList2csv() {
		try {
			String s = FlaskXMLUtils.list2csv(ls);
			assertTrue(s!=null);
			//System.out.println(s);
		
			s = FlaskXMLUtils.list2csv(ld);
			assertTrue(s!=null);
			//System.out.println(s);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCsv2List() {
		try {
			List<String> t = FlaskXMLUtils.fromCsv(csv1);
			assertTrue(t != null);
			assertTrue(t.size()==1);
			assertTrue(t.get(0).equals(""));
			
			t = FlaskXMLUtils.fromCsv(csv2);
			assertTrue(t != null);
			assertTrue(t.size()==3);
			assertTrue(t.get(0).equals("a"));
			assertTrue(t.get(1).equals("b"));
			assertTrue(t.get(2).equals("cc"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

}
