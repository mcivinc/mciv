package nebula.soft.flask.real;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

public class StringMatrixFlaskTest {

	MCFlask smf = null;
	MCFlask smfn = null;
	MCFlask smfrc = null;
	
	@Before
	public void setUp() throws Exception {
		List<String> r1 = new Vector<String>(3);
		List<String> r2 = new Vector<String>(3);
		List<List<String>> m = new Vector<List<String>>();
		m.add(r1);
		m.add(r2);
		m.get(0).add("11");
		m.get(0).add("12");
		m.get(0).add("13");
		m.get(1).add("21");
		m.get(1).add("22");
		m.get(1).add("23");
		smf = MCFlaskFactory.makeStringMatrix(m);
		smfn = MCFlaskFactory.makeStringMatrix(m, "foo");
		smfrc = MCFlaskFactory.make(3,4,MCFlaskType.STRING_MATRIX);
	}

	@After
	public void tearDown() throws Exception {
		smf = null;
		smfn = null;
		smfrc = null;
	}

	@Test
	public void testStringMatrixFlaskListOfListOfString() {
		assertTrue(smf != null);
		assertTrue(smf.isStringMatrix());
		assertTrue(smf.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}

	@Test
	public void testStringMatrixFlaskListOfListOfStringString() {
		assertTrue(smfn != null);
		assertTrue(smfn.isStringMatrix());
		assertTrue(smfn.shortName().equals("foo"));
	}
	
	@Test
	public void testStringMatrixFlaskRowsColumns() {
		assertTrue(smfrc != null);
		assertTrue(smfrc.isStringMatrix());
		assertTrue(smfrc.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}
	
	@Test
	public void testRows() {
		try {
			assertTrue(smf.rows() == 2);
			assertTrue(smfn.rows() == 2);
			assertTrue(smfrc.rows() == 3);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testColumns() {
		try {
			assertTrue(smf.columns() == 3);
			assertTrue(smfn.columns() == 3);
			assertTrue(smfrc.columns() == 4);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		try {
			List<List<String>> m = smf.getStringMatrix();
			assertTrue(m != null);
			assertTrue(m.size() == 2);
			assertTrue(m.get(0).size() == 3);
			assertTrue(m.get(0).get(0).equals("11"));
			assertTrue(m.get(0).get(1).equals("12"));
			assertTrue(m.get(1).get(0).equals("21"));
			assertTrue(m.get(1).get(1).equals("22"));
			assertTrue(m.get(1).get(2).equals("23"));
			assertTrue(smf.getString(0,0).equals("11"));
			assertTrue(smf.getString(0,1).equals("12"));
			assertTrue(smf.getString(1,0).equals("21"));
			assertTrue(smf.getString(1,1).equals("22"));
			assertTrue(smf.getString(1,2).equals("23"));
			assertTrue(smfrc.getString(0,0).equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(smfrc.getString(1,1).equals(MCFlaskDictionary.DEFAULT_STRING));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSet() {
		try {
			assertTrue(smf.getString(0,0).equals("11"));
			String old = smf.set(0,0, "33");
			assertTrue(smf.getString(0,0).equals("33"));
			assertTrue(old.equals("11"));
			
			assertTrue(smfrc.set(0,0,"bar").equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(smfrc.getString(0,0).equals("bar"));
			assertTrue(smfrc.set(1,1,"far").equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(smfrc.getString(1,1).equals("far"));
			assertTrue(smfrc.getString(1,2).equals(MCFlaskDictionary.DEFAULT_STRING));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testClone() {
		try {
			MCFlask cl = smf.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getString(0,0).equals("11"));
			assertTrue(cl.getString(1,0).equals("21"));
			assertTrue(cl.getString(1,1).equals("22"));
			
			assertTrue(cl.set(0, 0, "44").equals("11"));
			assertTrue(cl.getString(0,0).equals("44"));
			assertTrue(cl.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(smf.getString(0,0).equals("11"));
			
			cl = smfn.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getString(0,0).equals("11"));
			assertTrue(cl.getString(1,0).equals("21"));
			assertTrue(cl.getString(1,1).equals("22"));
			
			assertTrue(cl.set(0, 0, "43").equals("11"));
			assertTrue(cl.getString(0,0).equals("43"));
			assertTrue(cl.shortName().equals("foo"));
			assertTrue(smfn.getString(0,0).equals("11"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		List<List<String>> m = new Vector<List<String>>();
		List<String> r1 = new Vector<String>();
		List<String> r2 = new Vector<String>();
		r1.add("111");
		r1.add("112");
		r1.add("113");
		r2.add("221");
		r2.add("222");
		r2.add("223");
		m.add(r1);
		m.add(r2);
		try {
			assertTrue(smf.compareTo(smf) == 0);
			MCFlask g = MCFlaskFactory.makeStringMatrix(m);
			assertTrue(smf.compareTo(g) < 0);
			m = new Vector<List<String>>();
			m.add(r1);
			g = MCFlaskFactory.makeStringMatrix(m);
			assertTrue(smf.compareTo(g) > 0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testSerialize() {
		String s = smfn.serialize(true);
		assertTrue(s!=null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><SM Ver=\"1\" N=\"foo\" nr=\"2\" nc=\"3\"><r i=\"0\" V=\"11,12,13\"/><r i=\"1\" V=\"21,22,23\"/></SM>");
			assertTrue(f!=null);
			assertTrue(f.isStringMatrix());
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.getString(0,0).equals("11"));
			assertTrue(f.getString(0,1).equals("12"));
			assertTrue(f.getString(0,2).equals("13"));
			assertTrue(f.getString(1,0).equals("21"));
			assertTrue(f.getString(1,1).equals("22"));
			assertTrue(f.getString(1,2).equals("23"));
		
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate(smfn.serialize(true));
			assertTrue(f!=null);
			assertTrue(f.isStringMatrix());
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.getString(0,0).equals("11"));
			assertTrue(f.getString(0,1).equals("12"));
			assertTrue(f.getString(0,2).equals("13"));
			assertTrue(f.getString(1,0).equals("21"));
			assertTrue(f.getString(1,1).equals("22"));
			assertTrue(f.getString(1,2).equals("23"));
		
			f = MCFlaskFactory.hydrate(smfrc.serialize(true));
			assertTrue(f!=null);
			assertTrue(f.isStringMatrix());
			assertTrue(f.rows()==3);
			assertTrue(f.columns()==4);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
