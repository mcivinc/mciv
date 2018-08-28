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

public class IntegerFlaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetInt() {
		MCFlask f = MCFlaskFactory.make(2);
		assertTrue(f.isInteger());
		assertTrue(f.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
		try {
			assertTrue(f.getInt()==2);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		f = MCFlaskFactory.make(3, "hello");
		assertTrue(f.isInteger());
		assertTrue(f.shortName().equals("hello"));
		try {
			assertTrue(f.getInt()==3);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsInteger() {
		MCFlask f = MCFlaskFactory.make(1);
		assertTrue(f.isInteger());
		assertTrue(f.type()==MCFlaskType.INTEGER);
		assertTrue(f.isBoolean()==false);
		try {
			f.getDouble();
		} catch (MCFlaskException fe) {
			//System.out.println(fe.getMessage());
			return;
		} catch (Throwable t) {
			fail(t.getMessage());
		} 
		fail("should thrown exception");
	}
	
	@Test
	public void testSerialize() {
		MCFlask f = MCFlaskFactory.make(1);
		String s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
		s = f.serialize(false);
		assertTrue(s != null);
		//System.out.println(s);
		f = MCFlaskFactory.make(2, "foo");
		s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><I V=\"1\"/>");
			assertTrue(f != null);
			assertTrue(f.isInteger());
			assertTrue(f.getInt() == 1);
			assertTrue(f.fullName().equals(MCFlaskDictionary.DEFAULT_STRING));
			
			f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><I Ver=\"1\" N=\"foo\" V=\"1\"/>");
			assertTrue(f != null);
			assertTrue(f.isInteger());
			assertTrue(f.getInt() == 1);
			assertTrue(f.fullName().equals("foo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.make(2, "hello");
			MCFlask hydrated = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(hydrated != null);
			assertTrue(hydrated.isInteger());
			assertTrue(hydrated.getInt() == 2);
			assertTrue(hydrated.fullName().equals("hello"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

}
