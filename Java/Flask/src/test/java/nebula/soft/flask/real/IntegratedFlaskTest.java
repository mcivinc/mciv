package nebula.soft.flask.real;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

public class IntegratedFlaskTest {
	
	MCFlask f = null;

	@Before
	public void setUp() throws Exception {
		f = MCFlaskFactory.make(MCFlaskType.LIST, "top");
		MCFlask l = MCFlaskFactory.make(MCFlaskType.LIST, "embedded");
		l.append(MCFlaskFactory.make(1));
		l.append(MCFlaskFactory.make("foo"));
		f.append(l);
		f.append(MCFlaskFactory.make(2.0));
		MCFlask m = MCFlaskFactory.make(MCFlaskType.MAP, "embeddedmap");
		m.put("intkey", MCFlaskFactory.make(3));
		m.put("booleankey", MCFlaskFactory.make(true));
		f.append(m);
	}

	@After
	public void tearDown() throws Exception {
		f = null;
	}

	@Test
	public void testOne() {
		try {
			//System.out.println(f.serialize(true));
			
			MCFlask h = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(h != null);
			assertTrue(h.isList());
			assertTrue(h.fullName().equals("top"));
			assertTrue(h.size()==3);
			assertTrue(h.get(0).isList());
			assertTrue(h.get(0).size()==2);
			assertTrue(h.get(0).get(0).getInt() == 1);
			assertTrue(h.get(0).get(1).getString().equals("foo"));
			assertTrue(h.get(1).getDouble() == 2.0);
			assertTrue(h.get(2).isMap());
			assertTrue(h.get(2).fullName().equals("embeddedmap"));
			assertTrue(h.get(2).get("intkey").getInt() == 3);
			assertTrue(h.get(2).get("booleankey").getBoolean() == true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testTwo() {
		try {
			MCFlask ll = MCFlaskFactory.make(MCFlaskType.LIST, "deep");
			MCFlask lm = MCFlaskFactory.make(MCFlaskType.MAP, "deeper");
			lm.put("k1", MCFlaskFactory.make(10));
			lm.put("k2",  MCFlaskFactory.make(true));
			ll.append(lm);
			ll.append(MCFlaskFactory.make(21));
			f.get(2).put("deep", ll);
			
			//System.out.println(f.serialize(true));
			MCFlask h = MCFlaskFactory.hydrate(f.serialize(true));
			assertTrue(h != null);
			assertTrue(h.isList());
			assertTrue(h.fullName().equals("top"));
			assertTrue(h.size()==3);
			assertTrue(h.get(0).isList());
			assertTrue(h.get(0).size()==2);
			assertTrue(h.get(0).get(0).getInt() == 1);
			assertTrue(h.get(0).get(1).getString().equals("foo"));
			assertTrue(h.get(1).getDouble() == 2.0);
			assertTrue(h.get(2).isMap());
			assertTrue(h.get(2).size()==3);
			assertTrue(h.get(2).fullName().equals("embeddedmap"));
			assertTrue(h.get(2).get("intkey").getInt() == 3);
			assertTrue(h.get(2).get("booleankey").getBoolean() == true);
			assertTrue(h.get(2).get("deep").isList());
			assertTrue(h.get(2).get("deep").size()==2);
			assertTrue(h.get(2).get("deep").get(0).isMap());
			assertTrue(h.get(2).get("deep").get(1).isInteger());
			assertTrue(h.get(2).get("deep").get(0).get("k1").getInt()==10);
			assertTrue(h.get(2).get("deep").get(0).get("k2").getBoolean()==true);
			
			
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
}
