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

public class IntegerMatrixFlaskTest {

	MCFlask imf = null;
	MCFlask imfn = null;
	MCFlask imfrc = null;
	
	@Before
	public void setUp() throws Exception {
		List<Integer> r1 = new Vector<Integer>(3);
		List<Integer> r2 = new Vector<Integer>(3);
		List<List<Integer>> m = new Vector<List<Integer>>();
		m.add(r1);
		m.add(r2);
		m.get(0).add(11);
		m.get(0).add(12);
		m.get(0).add(13);
		m.get(1).add(21);
		m.get(1).add(22);
		m.get(1).add(23);
		imf = MCFlaskFactory.makeIntegerMatrix(m);
		imfn = MCFlaskFactory.makeIntegerMatrix(m, "foo");
		imfrc = MCFlaskFactory.make(3,4,MCFlaskType.INTEGER_MATRIX);
	}

	@After
	public void tearDown() throws Exception {
		imf = null;
		imfn = null;
		imfrc = null;
	}

	@Test
	public void testIntegerMatrixFlaskListOfListOfInteger() {
		assertTrue(imf != null);
		assertTrue(imf.isIntegerMatrix());
		assertTrue(imf.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}

	@Test
	public void testIntegerMatrixFlaskListOfListOfIntegerString() {
		assertTrue(imfn != null);
		assertTrue(imfn.isIntegerMatrix());
		assertTrue(imfn.shortName().equals("foo"));
	}
	
	@Test
	public void testIntegerMatrixFlaskRowsColumns() {
		assertTrue(imfrc != null);
		assertTrue(imfrc.isIntegerMatrix());
		assertTrue(imfrc.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}
	
	@Test
	public void testRows() {
		try {
			assertTrue(imf.rows() == 2);
			assertTrue(imfn.rows() == 2);
			assertTrue(imfrc.rows() == 3);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testColumns() {
		try {
			assertTrue(imf.columns() == 3);
			assertTrue(imfn.columns() == 3);
			assertTrue(imfrc.columns() == 4);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		try {
			List<List<Integer>> m = imf.getIntegerMatrix();
			assertTrue(m != null);
			assertTrue(m.size() == 2);
			assertTrue(m.get(0).size() == 3);
			assertTrue(m.get(0).get(0) == 11);
			assertTrue(m.get(0).get(1) == 12);
			assertTrue(m.get(1).get(0) == 21);
			assertTrue(m.get(1).get(1) == 22);
			assertTrue(m.get(1).get(2) == 23);
			assertTrue(imf.getInteger(0,0) == 11);
			assertTrue(imf.getInteger(0,1) == 12);
			assertTrue(imf.getInteger(1,0) == 21);
			assertTrue(imf.getInteger(1,1) == 22);
			assertTrue(imf.getInteger(1,2) == 23);
			assertTrue(imfrc.getInteger(0,0) == MCFlaskDictionary.DEFAULT_INTEGER);
			assertTrue(imfrc.getInteger(1,1) == MCFlaskDictionary.DEFAULT_INTEGER);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSet() {
		try {
			assertTrue(imf.getInteger(0,0) == 11);
			Integer old = imf.set(0,0, 33);
			assertTrue(imf.getInteger(0,0) == 33);
			assertTrue(old == 11);
			
			assertTrue(imfrc.set(0, 0, 45) == MCFlaskDictionary.DEFAULT_INTEGER);
			assertTrue(imfrc.getInteger(0,0) == 45);
			assertTrue(imfrc.set(1,1,54) == MCFlaskDictionary.DEFAULT_INTEGER);
			assertTrue(imfrc.getInteger(1,1) == 54);
			assertTrue(imfrc.getInteger(0, 0) == 45);
			assertTrue(imfrc.getInteger(1,2) == MCFlaskDictionary.DEFAULT_INTEGER);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testClone() {
		try {
			MCFlask cl = imf.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getInteger(0,0) == 11);
			assertTrue(cl.getInteger(1,0) == 21);
			assertTrue(cl.getInteger(1,1) == 22);
			
			assertTrue(cl.set(0, 0, 44) == 11);
			assertTrue(cl.getInteger(0,0) == 44);
			assertTrue(cl.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(imf.getInteger(0,0) == 11);
			
			cl = imfn.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getInteger(0,0) == 11);
			assertTrue(cl.getInteger(1,0) == 21);
			assertTrue(cl.getInteger(1,1) == 22);
			
			assertTrue(cl.set(0, 0, 43) == 11);
			assertTrue(cl.getInteger(0,0) == 43);
			assertTrue(cl.shortName().equals("foo"));
			assertTrue(imfn.getInteger(0,0) == 11);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		List<List<Integer>> m = new Vector<List<Integer>>();
		List<Integer> r1 = new Vector<Integer>();
		List<Integer> r2 = new Vector<Integer>();
		r1.add(111);
		r1.add(112);
		r1.add(113);
		r2.add(221);
		r2.add(222);
		r2.add(223);
		m.add(r1);
		m.add(r2);
		try {
			assertTrue(imf.compareTo(imf) == 0);
			MCFlask g = MCFlaskFactory.makeIntegerMatrix(m);
			assertTrue(imf.compareTo(g) < 0);
			m = new Vector<List<Integer>>();
			m.add(r1);
			g = MCFlaskFactory.makeIntegerMatrix(m);
			assertTrue(imf.compareTo(g) > 0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testSerialize() {
		String s = imfn.serialize(true);
		assertTrue(s!=null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><IM Ver=\"1\" N=\"foo\" nr=\"2\" nc=\"3\"><r i=\"0\" V=\"11,12,13\"/><r i=\"1\" V=\"21,22,23\"/></IM>");
			assertTrue(f != null);
			assertTrue(f.isIntegerMatrix());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.getInteger(0,0) == 11);
			assertTrue(f.getInteger(0,1) == 12);
			assertTrue(f.getInteger(0,2) == 13);
			assertTrue(f.getInteger(1,0) == 21);
			assertTrue(f.getInteger(1,1) == 22);
			assertTrue(f.getInteger(1,2) == 23);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate(imfn.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isIntegerMatrix());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.getInteger(0,0) == 11);
			assertTrue(f.getInteger(0,1) == 12);
			assertTrue(f.getInteger(0,2) == 13);
			assertTrue(f.getInteger(1,0) == 21);
			assertTrue(f.getInteger(1,1) == 22);
			assertTrue(f.getInteger(1,2) == 23);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
}
