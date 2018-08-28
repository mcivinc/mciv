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

public class BooleanFlaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetBoolean() {
		MCFlask f = MCFlaskFactory.make(false);
		assertTrue(f.isBoolean());
		assertTrue(f.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
		//System.out.println(f);
		try {
			assertTrue(f.getBoolean()==false);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		f = MCFlaskFactory.make(true, "hello");
		assertTrue(f.isBoolean());
		assertTrue(f.shortName().equals("hello"));
		//System.out.println(f);
		try {
			assertTrue(f.getBoolean()==true);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testIsBoolean() {
		MCFlask f = MCFlaskFactory.make(true);
		assertTrue(f.isBoolean());
		assertTrue(f.type()==MCFlaskType.BOOLEAN);
		assertTrue(f.isInteger()==false);
		try {
			int i = f.getInt();
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
		MCFlask f1 = MCFlaskFactory.make(false);
		MCFlask f2 = MCFlaskFactory.make(true);
		MCFlask f3 = MCFlaskFactory.make(true);
		assertTrue(f1.compareTo(f2)==-1);
		assertTrue(f2.compareTo(f1)==1);
		assertTrue(f2.compareTo(f3)==0);
	}
	
	@Test
	public void testSerialize() {
		MCFlask f = MCFlaskFactory.make(true);
		String s = f.serialize(true);
		assertTrue(s != null);
		//System.out.println(s);
		f = MCFlaskFactory.make(true, "foo");
		s = f.serialize(true);
		assertTrue(s!= null);
		//System.out.println(s);
	}
	
	@Test
	public void testHydrate() {
		try {
			String serialized = new String("<?xml version=\"1.0\" ?><B Ver=\"1\" V=\"true\"/>");
			MCFlask f = MCFlaskFactory.hydrate(serialized);
			assertTrue(f != null);
			assertTrue(f.isBoolean());
			assertTrue(f.getBoolean()==true);
			
			serialized = new String("<?xml version=\"1.0\" ?><B Ver=\"1\" N=\"foo\" V=\"true\"/>");
			f = MCFlaskFactory.hydrate(serialized);
			assertTrue(f != null);
			assertTrue(f.isBoolean());
			assertTrue(f.getBoolean()==true);
			assertTrue(f.fullName().equals("foo"));
			
			
		} catch (Exception e_) {
			fail("Exception: "+e_);
		}
		
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.make(true);
			MCFlask hydrated = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(hydrated != null);
			assertTrue(hydrated.isBoolean());
			assertTrue(hydrated.fullName().equals(MCFlaskDictionary.DEFAULT_STRING));
			assertTrue(hydrated.getBoolean()==true);
			
			f = MCFlaskFactory.make(true, "foo");
			hydrated = MCFlaskFactory.hydrate(f.serialize(false));
			assertTrue(hydrated != null);
			assertTrue(hydrated.isBoolean());
			assertTrue(hydrated.fullName().equals("foo"));
			assertTrue(hydrated.getBoolean()==true);
			
		} catch (Exception e_) {
			fail("Exception: "+e_);
		}
	}

}
