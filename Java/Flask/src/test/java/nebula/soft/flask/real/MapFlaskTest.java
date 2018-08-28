package nebula.soft.flask.real;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

public class MapFlaskTest {

	MCFlask m = null;
	MCFlask mn = null;
	
	@Before
	public void setUp() throws Exception {
		m = MCFlaskFactory.make(MCFlaskType.MAP);
		mn = MCFlaskFactory.make(MCFlaskType.MAP, "foof");
	}

	@After
	public void tearDown() throws Exception {
		m = null;
		mn = null;
	}

	@Test
	public void testMapFlask() {
		assertTrue(m != null);
		assertTrue(m.isMap());
		assertTrue(m.type() == MCFlaskType.MAP);
		assertTrue(m.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
	}

	@Test
	public void testMapFlaskString() {
		assertTrue(mn != null);
		assertTrue(mn.isMap());
		assertTrue(mn.shortName().equals("foof"));
	}
	
	@Test
	public void testSize() {
		try {
			assertTrue(m.size()==0);
			assertTrue(mn.size()==0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testPutGet() {
		try {
			m.put("k1", MCFlaskFactory.make(true));
			m.put("k2", MCFlaskFactory.make(1));
			assertTrue(m.size()==2);
			assertTrue(m.get("k1") != null);
			assertTrue(m.get("k1").isBoolean());
			assertTrue(m.get("k1").getBoolean() == true);
			assertTrue(m.get("k2") != null);
			assertTrue(m.get("k2").isInteger());
			assertTrue(m.get("k2").getInt() == 1);
			assertTrue(m.put("k1", MCFlaskFactory.make(3.0)).getBoolean() == true);
			assertTrue(m.size() == 2);
			assertTrue(m.get("k1").isDouble());
			
			mn.put("k1", MCFlaskFactory.make(true));
			mn.put("k2", MCFlaskFactory.make(1));
			mn.put("k3",  MCFlaskFactory.make(MCFlaskType.LIST, "boo"));
			assertTrue(mn.size() == 3);
			assertTrue(mn.get("k3") != null);
			assertTrue(mn.get("k3").isList());
			assertTrue(mn.get("k3").size() == 0);
			assertTrue(mn.get("k4") == null);
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testHasKey() {
		try {
			assertTrue(m.hasKey("k1") == false);
			m.put("k1", MCFlaskFactory.make(1));
			assertTrue(m.hasKey("k1") == true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testKeys() {
		try { 
			assertTrue(m.keys() != null);
			assertTrue(m.keys().size() == 0);
			m.put("k1", MCFlaskFactory.make(1));
			m.put("k2", MCFlaskFactory.make(2));
			assertTrue(m.keys().size() == 2);
			assertTrue(m.keys().contains("k1"));
			assertTrue(m.keys().contains("k2"));
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testClone() {
		try {
			mn.put("k1", MCFlaskFactory.make(1, "boo"));
			MCFlask cl = mn.clone();
			assertTrue(cl != null);
			assertTrue(cl.isMap());
			assertTrue(cl.size()==1);
			assertTrue(cl.hasKey("k1"));
			assertTrue(cl.shortName().equals(mn.shortName()));
			assertTrue(cl.get("k1").isInteger());
			assertTrue(cl.get("k1").shortName().equals("boo"));
			assertTrue(cl.get("k1").getInt() == 1);
			
			cl.put("k2", MCFlaskFactory.make(2.0));
			cl.put("k1", MCFlaskFactory.make(3.0));
			assertTrue(mn.size()==1);
			assertTrue(mn.hasKey("k1"));
			assertTrue(mn.get("k1").isInteger());
			assertTrue(mn.get("k1").shortName().equals("boo"));
			assertTrue(mn.get("k1").getInt() == 1);
			assertTrue(cl.size()==2);
			assertTrue(cl.hasKey("k2"));
			assertTrue(cl.get("k1").isDouble());
			assertTrue(cl.get("k2").isDouble());
			
			//System.out.println(mn);
			//System.out.println(cl);
			
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testCompareTo() {
		try {
			assertTrue(m.compareTo(m)==0);
			assertTrue(mn.compareTo(mn)==0);
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testSerialize() {
		try {
			mn.put("k1", MCFlaskFactory.make(1));
			MCFlask l = MCFlaskFactory.make(MCFlaskType.LIST);
			l.append(MCFlaskFactory.make(2));
			l.append(MCFlaskFactory.make(true, "bool"));
			mn.put("k2", l);
			String s = mn.serialize(true);
			assertTrue(s != null);
			//System.out.println(s);
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><M Ver=\"1\" N=\"foof\" s=\"2\"><k2><L s=\"2\"><e i=\"0\"><I V=\"2\"/></e><e i=\"1\"><B N=\"bool\" V=\"true\"/></e></L></k2><k1><I V=\"1\"/></k1></M>");
			assertTrue(f != null);
			assertTrue(f.isMap());
			assertTrue(f.fullName().equals("foof"));
			assertTrue(f.size()==2);
			assertTrue(f.hasKey("k1"));
			assertTrue(f.get("k1").isInteger());
			assertTrue(f.get("k1").getInt()==1);
			assertTrue(f.hasKey("k2"));
			assertTrue(f.get("k2").isList());
			assertTrue(f.get("k2").size()==2);
			assertTrue(f.get("k2").get(0).isInteger());
			assertTrue(f.get("k2").get(0).getInt()==2);
			assertTrue(f.get("k2").get(1).isBoolean());
			assertTrue(f.get("k2").get(1).getBoolean()==true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			mn.put("k1", MCFlaskFactory.make(1));
			MCFlask l = MCFlaskFactory.make(MCFlaskType.LIST);
			l.append(MCFlaskFactory.make(2));
			l.append(MCFlaskFactory.make(true, "bool"));
			mn.put("k2", l);
			MCFlask f = MCFlaskFactory.hydrate(mn.serialize(true));
			assertTrue(f != null);
			assertTrue(f.isMap());
			assertTrue(f.fullName().equals("foof"));
			assertTrue(f.size()==2);
			assertTrue(f.hasKey("k1"));
			assertTrue(f.get("k1").isInteger());
			assertTrue(f.get("k1").getInt()==1);
			assertTrue(f.hasKey("k2"));
			assertTrue(f.get("k2").isList());
			assertTrue(f.get("k2").size()==2);
			assertTrue(f.get("k2").get(0).isInteger());
			assertTrue(f.get("k2").get(0).getInt()==2);
			assertTrue(f.get("k2").get(1).isBoolean());
			assertTrue(f.get("k2").get(1).getBoolean()==true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
