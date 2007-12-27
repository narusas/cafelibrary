package net.narusas.cafelibrary.fetcher;

import junit.framework.TestCase;

public class RegxTest extends TestCase {
	public void testNormalize(){
		String src = "\"abc\"";
		assertEquals("'abc'", src.replace('"', '\''));
	}
}
