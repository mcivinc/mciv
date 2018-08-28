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

public class DoubleMatrixFlaskTest {

	MCFlask dmf = null;
	MCFlask dmfn = null;
	MCFlask dmfrc = null;
	MCFlask dmfe = null;
	
	@Before
	public void setUp() throws Exception {
		List<Double> r1 = new Vector<Double>(3);
		List<Double> r2 = new Vector<Double>(3);
		List<List<Double>> m = new Vector<List<Double>>();
		m.add(r1);
		m.add(r2);
		m.get(0).add(11.0);
		m.get(0).add(12.0);
		m.get(0).add(13.0);
		m.get(1).add(21.0);
		m.get(1).add(22.0);
		m.get(1).add(23.0);
		dmf = MCFlaskFactory.makeDoubleMatrix(m);
		dmfn = MCFlaskFactory.makeDoubleMatrix(m, "foo");
		dmfrc = MCFlaskFactory.make(3,4,MCFlaskType.DOUBLE_MATRIX);
		dmfe = MCFlaskFactory.makeDoubleMatrix(new Vector<List<Double>>(), "empty");
	}

	@After
	public void tearDown() throws Exception {
		dmf = null;
		dmfn = null;
		dmfrc = null;
		dmfe = null;
	}

	@Test
	public void testDoubleMatrixFlaskListOfListOfDouble() {
		assertTrue(dmf != null);
		assertTrue(dmf.isDoubleMatrix());
		assertTrue(dmf.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}

	@Test
	public void testDoubleMatrixFlaskListOfListOfDoubleString() {
		assertTrue(dmfn != null);
		assertTrue(dmfn.isDoubleMatrix());
		assertTrue(dmfn.shortName().equals("foo"));
	}
	
	@Test
	public void testDoubleMatrixFlaskRowsColumns() {
		assertTrue(dmfrc != null);
		assertTrue(dmfrc.isDoubleMatrix());
		assertTrue(dmfrc.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}
	
	@Test
	public void testDoubleMatrixFlaskNull() {
		assertTrue(dmfe != null);
		assertTrue(dmfe.isDoubleMatrix());
	}
	
	@Test
	public void testRows() {
		try {
			assertTrue(dmf.rows() == 2);
			assertTrue(dmfn.rows() == 2);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testColumns() {
		try {
			assertTrue(dmf.columns() == 3);
			assertTrue(dmfn.columns() == 3);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		try {
			List<List<Double>> m = dmf.getDoubleMatrix();
			assertTrue(m != null);
			assertTrue(m.size() == 2);
			assertTrue(m.get(0).size() == 3);
			assertTrue(m.get(0).get(0) == 11);
			assertTrue(m.get(0).get(1) == 12);
			assertTrue(m.get(1).get(0) == 21);
			assertTrue(m.get(1).get(1) == 22);
			assertTrue(m.get(1).get(2) == 23);
			assertTrue(dmf.getDouble(0,0) == 11);
			assertTrue(dmf.getDouble(0,1) == 12);
			assertTrue(dmf.getDouble(1,0) == 21);
			assertTrue(dmf.getDouble(1,1) == 22);
			assertTrue(dmf.getDouble(1,2) == 23);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSet() {
		try {
			assertTrue(dmf.getDouble(0,0) == 11);
			Double old = dmf.set(0,0, 33.0);
			assertTrue(dmf.getDouble(0,0) == 33);
			assertTrue(old == 11);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testClone() {
		try {
			MCFlask cl = dmf.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getDouble(0,0) == 11);
			assertTrue(cl.getDouble(1,0) == 21);
			assertTrue(cl.getDouble(1,1) == 22);
			
			assertTrue(cl.set(0, 0, 44.0) == 11);
			assertTrue(cl.getDouble(0,0) == 44);
			assertTrue(cl.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(dmf.getDouble(0,0) == 11);
			
			cl = dmfn.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.getDouble(0,0) == 11);
			assertTrue(cl.getDouble(1,0) == 21);
			assertTrue(cl.getDouble(1,1) == 22);
			
			assertTrue(cl.set(0, 0, 43.0) == 11);
			assertTrue(cl.getDouble(0,0) == 43);
			assertTrue(cl.shortName().equals("foo"));
			assertTrue(dmfn.getDouble(0,0) == 11);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		List<List<Double>> m = new Vector<List<Double>>();
		List<Double> r1 = new Vector<Double>();
		List<Double> r2 = new Vector<Double>();
		r1.add(111.0);
		r1.add(112.0);
		r1.add(113.0);
		r2.add(221.0);
		r2.add(222.0);
		r2.add(223.0);
		m.add(r1);
		m.add(r2);
		try {
			assertTrue(dmf.compareTo(dmf) == 0);
			MCFlask g = MCFlaskFactory.makeDoubleMatrix(m);
			assertTrue(dmf.compareTo(g) < 0);
			m = new Vector<List<Double>>();
			m.add(r1);
			g = MCFlaskFactory.makeDoubleMatrix(m);
			assertTrue(dmf.compareTo(g) > 0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testSerialize() {
		String s = dmfn.serialize(true);
		assertTrue(s!=null);
		//System.out.println(s);
	}

	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><DM Ver=\"1\" N=\"foo\" nr=\"2\" nc=\"3\"><r i=\"0\" V=\"11.0,12.0,13.0\"/><r i=\"1\" V=\"21.0,22.0,23.0\"/></DM>");
			assertTrue(f != null);
			assertTrue(f.isDoubleMatrix());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.getDouble(0,0)==11.0);
			assertTrue(f.getDouble(0,1)==12.0);
			assertTrue(f.getDouble(0,2)==13.0);
			assertTrue(f.getDouble(1,0)==21.0);
			assertTrue(f.getDouble(1,1)==22.0);
			assertTrue(f.getDouble(1,2)==23.0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate(dmfn.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isDoubleMatrix());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.rows()==2);
			assertTrue(f.columns()==3);
			assertTrue(f.getDouble(0,0)==11.0);
			assertTrue(f.getDouble(0,1)==12.0);
			assertTrue(f.getDouble(0,2)==13.0);
			assertTrue(f.getDouble(1,0)==21.0);
			assertTrue(f.getDouble(1,1)==22.0);
			assertTrue(f.getDouble(1,2)==23.0);
			
			f = MCFlaskFactory.hydrate(dmfe.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isDoubleMatrix());
			assertTrue(f.rows()==0);
			assertTrue(f.columns()==0);
			assertTrue(f.fullName().equals("empty"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
