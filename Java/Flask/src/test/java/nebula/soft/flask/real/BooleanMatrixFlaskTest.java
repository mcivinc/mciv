package nebula.soft.flask.real;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

public class BooleanMatrixFlaskTest {
	
	MCFlask bmf = null;
	MCFlask bmfn = null;
	MCFlask bmfrc = null;
	MCFlask bmfe = null;
	
	@Before
	public void setUp() throws Exception {
		List<Boolean> r1 = new Vector<Boolean>(3);
		List<Boolean> r2 = new Vector<Boolean>(3);
		List<List<Boolean>> m = new Vector<List<Boolean>>();
		m.add(r1);
		m.add(r2);
		m.get(0).add(true);
		m.get(0).add(false);
		m.get(0).add(false);
		m.get(1).add(false);
		m.get(1).add(true);
		m.get(1).add(false);
		bmf = MCFlaskFactory.makeBooleanMatrix(m);
		bmfn = MCFlaskFactory.makeBooleanMatrix(m, "foo");
		bmfrc = MCFlaskFactory.make(3,4,MCFlaskType.BOOLEAN_MATRIX);
		bmfe = MCFlaskFactory.makeBooleanMatrix(new Vector<List<Boolean>>(), "empty");
	}

	@After
	public void tearDown() throws Exception {
		bmf = null;
		bmfn = null;
		bmfrc = null;
		bmfe = null;
	}

	@Test
	public void testBooleanMatrixNull() {
		try {
			MCFlask f = MCFlaskFactory.makeBooleanMatrix(null);
		} catch (Exception e_) {
			return;
		}
		fail("Exception expected");
	}
	
	@Test
	public void testBooleanMatrixFlaskListOfListOfBoolean() {
		assertTrue(bmf != null);
		assertTrue(bmf.isBooleanMatrix());
		assertTrue(bmf.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}

	@Test
	public void testBooleanMatrixFlaskListOfListOfBooleanString() {
		assertTrue(bmfn != null);
		assertTrue(bmfn.isBooleanMatrix());
		assertTrue(bmfn.shortName().equals("foo"));
		//System.out.println(bmfn);
		try {
			assertTrue(bmfe != null);
			assertTrue(bmfe.isBooleanMatrix());
			assertTrue(bmfe.rows() == 0);
			assertTrue(bmfe.columns() == 0);
			assertTrue(bmfe.fullName().equals("empty"));
			//System.out.println(bmfe);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testBooleanMatrixFlaskRowsCols() {
		assertTrue(bmfrc != null);
		assertTrue(bmfrc.isBooleanMatrix());
		assertTrue(bmfrc.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}
	
	@Test
	public void testRows() {
		try {
			assertTrue(bmf.rows() == 2);
			assertTrue(bmfn.rows() == 2);
			assertTrue(bmfrc.rows() == 3);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testColumns() {
		try {
			assertTrue(bmf.columns() == 3);
			assertTrue(bmfn.columns() == 3);
			assertTrue(bmfrc.columns() == 4);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		try {
			List<List<Boolean>> m = bmf.getBooleanMatrix();
			assertTrue(m != null);
			assertTrue(m.size() == 2);
			assertTrue(m.get(0).size() == 3);
			assertTrue(m.get(0).get(0) == true);
			assertTrue(m.get(0).get(1) == false);
			assertTrue(m.get(1).get(0) == false);
			assertTrue(m.get(1).get(1) == true);
			assertTrue(m.get(1).get(2) == false);
			assertTrue(bmf.getBoolean(0,0) == true);
			assertTrue(bmf.getBoolean(0,1) == false);
			assertTrue(bmf.getBoolean(1,0) == false);
			assertTrue(bmf.getBoolean(1,1) == true);
			assertTrue(bmf.getBoolean(1,2) == false);
			assertTrue(bmfrc.getBoolean(0,0) == MCFlaskDictionary.DEFAULT_BOOLEAN);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSet() {
		try {
			assertTrue(bmf.getBoolean(0,0) == true);
			Boolean old = bmf.set(0,0, false);
			assertTrue(bmf.getBoolean(0,0) == false);
			assertTrue(old == true);
			
			assertTrue(bmfrc.set(0,0,false)==MCFlaskDictionary.DEFAULT_BOOLEAN);
			assertTrue(bmfrc.set(1,2,true)==MCFlaskDictionary.DEFAULT_BOOLEAN);
			assertTrue(bmfrc.getBoolean(0,0) == false);
			assertTrue(bmfrc.getBoolean(1,2) == true);
			//System.out.println(bmfrc);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testClone() {
		try {
			MCFlask cl = bmf.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getBoolean(0,0) == true);
			assertTrue(cl.getBoolean(1,0) == false);
			assertTrue(cl.getBoolean(1,1) == true);
			
			assertTrue(cl.set(0, 0, false) == true);
			assertTrue(cl.getBoolean(0,0) == false);
			assertTrue(cl.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(bmf.getBoolean(0,0) == true);
			
			cl = bmfn.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getBoolean(0,0) == true);
			assertTrue(cl.getBoolean(1,0) == false);
			assertTrue(cl.getBoolean(1,1) == true);
			
			assertTrue(cl.set(0, 0, false) == true);
			assertTrue(cl.getBoolean(0,0) == false);
			assertTrue(cl.shortName().equals("foo"));
			assertTrue(bmfn.getBoolean(0,0) == true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		List<List<Boolean>> m = new Vector<List<Boolean>>();
		List<Boolean> r1 = new Vector<Boolean>();
		List<Boolean> r2 = new Vector<Boolean>();
		r1.add(true);
		r1.add(true);
		r1.add(true);
		r2.add(false);
		r2.add(false);
		r2.add(false);
		m.add(r1);
		m.add(r2);
		try {
			assertTrue(bmf.compareTo(bmf) == 0);
			MCFlask g = MCFlaskFactory.makeBooleanMatrix(m);
			assertTrue(bmf.compareTo(g) < 0);
			m = new Vector<List<Boolean>>();
			m.add(r1);
			g = MCFlaskFactory.makeBooleanMatrix(m);
			assertTrue(bmf.compareTo(g) > 0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerialize() {
		String s = bmfn.serialize(true);
		assertTrue(s!=null);
		s = bmfe.serialize(true);
		assertTrue(s!=null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><BM Ver=\"1\" N=\"foo\" nr=\"2\" nc=\"3\"><r i=\"0\" V=\"true,false,false\"/><r i=\"1\" V=\"false,true,false\"/></BM>");
			assertTrue(f != null);
			assertTrue(f.isBooleanMatrix());
			assertTrue(f.rows() == 2);
			assertTrue(f.columns() == 3);
			assertTrue(f.getBoolean(0,0)==true);
			assertTrue(f.getBoolean(1,0)==false);
			assertTrue(f.getBoolean(0,1)==false);
			assertTrue(f.getBoolean(1,1)==true);
			assertTrue(f.getBoolean(0,2)==false);
			assertTrue(f.getBoolean(1,0)==false);
			assertTrue(f.fullName().equals("foo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate(bmfn.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isBooleanMatrix());
			assertTrue(f.rows() == 2);
			assertTrue(f.columns() == 3);
			assertTrue(f.getBoolean(0,0)==true);
			assertTrue(f.getBoolean(1,0)==false);
			assertTrue(f.getBoolean(0,1)==false);
			assertTrue(f.getBoolean(1,1)==true);
			assertTrue(f.getBoolean(0,2)==false);
			assertTrue(f.getBoolean(1,0)==false);
			assertTrue(f.fullName().equals("foo"));
			
			f = MCFlaskFactory.hydrate(bmfe.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isBooleanMatrix());
			assertTrue(f.rows() == 0);
			assertTrue(f.columns() == 0);
			assertTrue(f.fullName().equals("empty"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

}
