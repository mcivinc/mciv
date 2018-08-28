package nebula.soft.flask.real;

import static org.junit.Assert.*;
import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskException;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DoubleFlaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDouble() {
		MCFlask f = MCFlaskFactory.make(2.0);
		assertTrue(f.isDouble());
		assertTrue(f.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
		try {
			assertTrue(f.getDouble()==2.0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		f = MCFlaskFactory.make(3.0, "hello");
		assertTrue(f.isDouble());
		assertTrue(f.shortName().equals("hello"));
		try {
			assertTrue(f.getDouble()==3.0);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsDouble() {
		MCFlask f = MCFlaskFactory.make(1.0);
		assertTrue(f.isDouble());
		assertTrue(f.type()==MCFlaskType.DOUBLE);
		assertTrue(f.isBoolean()==false);
		try {
			f.getBoolean();
		} catch (MCFlaskException fe) {
			//System.out.println(fe.getMessage());
			return;
		} catch (Throwable t) {
			fail(t.getMessage());
		} 
		fail("should thrown exception");
	}
	
	@Test
	public void testCompareTo() {
		MCFlask f1 = MCFlaskFactory.make(1.0);
		MCFlask f2 = MCFlaskFactory.make(2.0);
		MCFlask f3 = MCFlaskFactory.make(2.0);
		MCFlask f4 = MCFlaskFactory.make(true);
		assertTrue(f1.compareTo(f2)==-1);
		assertTrue(f2.compareTo(f1)==1);
		assertTrue(f2.compareTo(f3)==0);
		assertTrue(f1.compareTo(f4)==1);
	}
	
	@Test
	public void testSerialize() {
		MCFlask f = MCFlaskFactory.make(1.234567891234e13);
		String s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
		s = f.serialize(false);
		assertTrue(s != null);
		//System.out.println(s);
		f = MCFlaskFactory.make(2.0, "foo");
		s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><D V=\"1.2\"/>");
			assertTrue(f != null);
			assertTrue(f.isDouble());
			assertTrue(f.getDouble()==1.2);
			assertTrue(f.fullName().equals(MCFlaskDictionary.DEFAULT_STRING));
			
			f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><D Ver=\"1\" N=\"foo\" V=\"2.3\"/>");
			assertTrue(f != null);
			assertTrue(f.isDouble());
			assertTrue(f.getDouble()==2.3);
			assertTrue(f.fullName().equals("foo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.make(2.3, "foo");
			MCFlask hydrated = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(hydrated != null);
			assertTrue(hydrated.isDouble());
			assertTrue(hydrated.getDouble()==2.3);
			assertTrue(hydrated.fullName().equals("foo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
