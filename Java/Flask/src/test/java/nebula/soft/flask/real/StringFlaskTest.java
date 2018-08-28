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

public class StringFlaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetString() {
		MCFlask f = MCFlaskFactory.make("Foo");
		assertTrue(f.isString());
		assertTrue(f.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
		try {
			assertTrue(f.getString().equals("Foo"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		f = MCFlaskFactory.make("Boo", "hello");
		assertTrue(f.isString());
		assertTrue(f.shortName().equals("hello"));
		try {
			assertTrue(f.getString().equals("Boo"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testIsString() {
		MCFlask f = MCFlaskFactory.make("Foo");
		assertTrue(f.isString());
		assertTrue(f.type()==MCFlaskType.STRING);
		assertTrue(f.isBoolean()==false);
		assertTrue(f.isDouble()==false);
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
	public void testSerialize() {
		MCFlask f = MCFlaskFactory.make("boo");
		String s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
		s = f.serialize(false);
		assertTrue(s != null);
		//System.out.println(s);
		f = MCFlaskFactory.make("boo", "foo");
		s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><S V=\"boo\"/>");
			assertTrue(f != null);
			assertTrue(f.isString());
			assertTrue(f.getString().equals("boo"));
			assertTrue(f.fullName().equals(MCFlaskDictionary.DEFAULT_STRING));
			
			f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><S Ver=\"1\" N=\"foo\" V=\"boo\"/>");
			assertTrue(f != null);
			assertTrue(f.isString());
			assertTrue(f.getString().equals("boo"));
			assertTrue(f.fullName().equals("foo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.make("foo", "boo");
			MCFlask h = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(h != null);
			assertTrue(h.isString());
			assertTrue(h.getString().equals("foo"));
			assertTrue(h.fullName().equals("boo"));
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

}
