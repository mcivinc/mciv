package nebula.soft.flask.real;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nebula.soft.flask.MCFlask;
import nebula.soft.flask.MCFlaskDictionary;
import nebula.soft.flask.MCFlaskFactory;
import nebula.soft.flask.MCFlaskType;

public class ListFlaskTest {

	MCFlask lf1 = null;
	MCFlask lf2 = null;
	MCFlask lfe = null;
	
	@Before
	public void setUp() throws Exception {
		lf1 = MCFlaskFactory.make(MCFlaskType.LIST);
		MCFlask e1 = MCFlaskFactory.make(1);
		MCFlask e2 = MCFlaskFactory.make(2);
		MCFlask e3 = MCFlaskFactory.make(3.0);
		MCFlask e4 = MCFlaskFactory.make(true);
		lf1.append(e1);
		lf1.append(e2);
		lf1.append(e3);
		lf2 = MCFlaskFactory.make(MCFlaskType.LIST, "foo");
		lf2.append(e1);
		lf2.append(e2);
		lf2.append(e3);
		lf2.append(e4);
		lfe = MCFlaskFactory.make(MCFlaskType.LIST, "empty");
	}

	@After
	public void tearDown() throws Exception {
		lf1 = null;
		lf2 = null;
		lfe = null;
	}

	@Test
	public void testListFlask() {
		assertTrue(lf1 != null);
		assertTrue(lf1.isList());
		assertTrue(lf1.shortName().equals(MCFlaskDictionary.DEFAULT_STRING));
		//System.out.println(lf1);
	}

	@Test
	public void testListFlaskString() {
		assertTrue(lf2 != null);
		assertTrue(lf2.isList());
		assertTrue(lf2.shortName().equals("foo"));
	}

	@Test
	public void testType() {
		assertTrue(lf1.type() == MCFlaskType.LIST);
		assertTrue(lf2.type() == MCFlaskType.LIST);
		assertTrue(lfe.type() == MCFlaskType.LIST);
	}

	@Test
	public void testTypeAsString() {
		assertTrue(lf1.typeAsString().equals("LIST"));
		assertTrue(lf2.typeAsString().equals("LIST"));
		assertTrue(lfe.typeAsString().equals("LIST"));
	}

	@Test
	public void testSize() {
		try {
			assertTrue(lf1.size()==3);
			assertTrue(lf2.size()==4);
			assertTrue(lfe.size()==0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testGetInt() {
		try {
			assertTrue(lf1.get(0).isInteger());
			assertTrue(lf1.get(0).getInt()==1);
			assertTrue(lf1.get(1).isInteger());
			assertTrue(lf1.get(1).getInt()==2);
			assertTrue(lf1.get(2).isDouble());
			assertTrue(lf1.get(2).getDouble()==3.0);
			
			assertTrue(lf2.get(3).isBoolean());
			assertTrue(lf2.get(3).getBoolean()==true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testPutIntFlask() {
		try {
			MCFlask tmp = MCFlaskFactory.make(11);
			assertTrue(lf1.put(0, tmp).getInt()==1);
			assertTrue(lf1.get(0).getInt()==11);
			assertTrue(lf1.size()==3);
			
			assertTrue(lf2.put(1,  tmp).getInt()==2);
			assertTrue(lf2.get(1).getInt()==11);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testAppend() {
		try {
			MCFlask tmp = MCFlaskFactory.make("boo");
			lf1.append(tmp);
			assertTrue(lf1.size()==4);
			assertTrue(lf1.get(3).isString());
			assertTrue(lf1.get(3).getString().equals("boo"));
			
			lf2.append(tmp);
			assertTrue(lf2.size()==5);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testClone() {
		try {
			MCFlask cl1 = lf1.clone();
			assertTrue(cl1.size()==3);
			MCFlask tmp = MCFlaskFactory.make("boo");
			cl1.put(1, tmp);
			assertTrue(cl1.size()==3);
			assertTrue(cl1.get(1).isString());
			assertTrue(cl1.get(1).getString().equals("boo"));
			
			assertTrue(lf1.size()==3);
			assertTrue(lf1.get(1).isInteger());
			assertTrue(lf1.get(1).getInt()==2);
			
			cl1.append(tmp);
			assertTrue(cl1.size()==4);
			assertTrue(lf1.size()==3);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

	@Test
	public void testCompareTo() {
		try {
			assertTrue(lf1.compareTo(lf2)==-1);
			assertTrue(lf1.compareTo(lf1)==0);
			assertTrue(lf2.compareTo(lf2)==0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerialize() {
		try {
			String s = lf2.serialize(true);
			assertTrue(s != null);
			//System.out.println(s);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate("<?xml version=\"1.0\" ?><L Ver=\"1\" N=\"foo\" s=\"4\"><e i=\"0\"><I V=\"1\"/></e><e i=\"1\"><I V=\"2\"/></e><e i=\"2\"><D V=\"3.0\"/></e><e i=\"3\"><B V=\"true\"/></e></L>");
			assertTrue(f!=null);
			assertTrue(f.isList());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.size()==4);
			assertTrue(f.get(0).isInteger());
			assertTrue(f.get(0).getInt()==1);
			assertTrue(f.get(1).isInteger());
			assertTrue(f.get(1).getInt()==2);
			assertTrue(f.get(2).isDouble());
			assertTrue(f.get(2).getDouble()==3.0);
			assertTrue(f.get(3).isBoolean());
			assertTrue(f.get(3).getBoolean()==true);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}
	
	@Test
	public void testSerializeHydrate() {
		try {
			MCFlask f = MCFlaskFactory.hydrate(lf2.serialize(true));
			assertTrue(f!=null);
			assertTrue(f.isList());
			assertTrue(f.fullName().equals("foo"));
			assertTrue(f.size()==4);
			assertTrue(f.get(0).isInteger());
			assertTrue(f.get(0).getInt()==1);
			assertTrue(f.get(1).isInteger());
			assertTrue(f.get(1).getInt()==2);
			assertTrue(f.get(2).isDouble());
			assertTrue(f.get(2).getDouble()==3.0);
			assertTrue(f.get(3).isBoolean());
			assertTrue(f.get(3).getBoolean()==true);
			
			f = MCFlaskFactory.hydrate(lfe.serialize(true));
			assertTrue(f!=null);
			assertTrue(f.isList());
			assertTrue(f.fullName().equals("empty"));
			assertTrue(f.size()==0);
		} catch (Exception e_) {
			fail("Exception: "+e_.getMessage());
		}
	}

}
