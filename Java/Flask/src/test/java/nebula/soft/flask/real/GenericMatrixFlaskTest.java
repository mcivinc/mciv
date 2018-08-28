package nebula.soft.flask.real;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GenericMatrixFlaskTest {

	GenericMatrixFlask<Boolean> bmf = null;
	
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
		bmf = new GenericMatrixFlask<Boolean>(m);
	}

	@After
	public void tearDown() throws Exception {
		bmf = null;
	}

	@Test
	public void testGenericMatrixFlask() {
		assertTrue(bmf != null);
		//System.out.println(bmf.toString());
	}

	@Test
	public void testGenericMatrixFlaskNull() {
		try {
			GenericMatrixFlask<Boolean> f = new GenericMatrixFlask<Boolean>(null);
		} catch (Exception e_) {
			//e_.printStackTrace();
			System.out.println(e_.getMessage());
			return;
		}
		fail("Exception expected");
	}
	
	@Test
	public void testGenericMatrixFlaskInconsistent() {
		try {
			List<List<Boolean>> m = new Vector<List<Boolean>>();
			List<Boolean> r1 = new Vector<Boolean>();
			r1.add(true);
			r1.add(false);
			List<Boolean> r2 = new Vector<Boolean>();
			r2.add(false);
			r2.add(true);
			r2.add(false);
			m.add(r1);
			m.add(r2);
			GenericMatrixFlask<Boolean> f = new GenericMatrixFlask<Boolean>(m);
		} catch (Exception e_) {
			System.out.println(e_.getMessage());
			return;
		}
		fail("Exception expected");
	}
	
	@Test
	public void testRows() {
		assertTrue(bmf.rows()==2);
	}

	@Test
	public void testColumns() {
		assertTrue(bmf.columns()==3);
	}
	
	@Test
	public void testGet() {
		List<List<Boolean>> m = bmf.get();
		assertTrue(m != null);
		assertTrue(m.size() == 2);
		assertTrue(m.get(0).size() == 3);
		assertTrue(m.get(0).get(0) == true);
		assertTrue(m.get(0).get(1) == false);
		assertTrue(m.get(1).get(0) == false);
		assertTrue(m.get(1).get(1) == true);
		assertTrue(m.get(1).get(2) == false);
		assertTrue(bmf.get(0,0) == true);
		assertTrue(bmf.get(0,1) == false);
		assertTrue(bmf.get(1,0) == false);
		assertTrue(bmf.get(1,1) == true);
		assertTrue(bmf.get(1,2) == false);
	}

	@Test
	public void testSet() {
		assertTrue(bmf.get(0,0) == true);
		Boolean old = bmf.set(0,0, false);
		assertTrue(bmf.get(0,0) == false);
		assertTrue(old == true);
	}
	
	@Test
	public void testClone() {
		try {
			GenericMatrixFlask<Boolean> cl = bmf.clone();
			assertTrue(cl != null);
			assertTrue(cl.rows() == 2);
			assertTrue(cl.columns() == 3);
			assertTrue(cl.get(0,0) == true);
			assertTrue(cl.get(1,0) == false);
			assertTrue(cl.get(1,1) == true);
			
			assertTrue(cl.set(0, 0, false) == true);
			assertTrue(cl.get(0,0) == false);
			assertTrue(bmf.get(0,0) == true);
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
			GenericMatrixFlask<Boolean> g = new GenericMatrixFlask<Boolean>(m);
			assertTrue(bmf.compareTo(g) < 0);
			m = new Vector<List<Boolean>>();
			m.add(r1);
			g = new GenericMatrixFlask<Boolean>(m);
			assertTrue(bmf.compareTo(g) > 0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
