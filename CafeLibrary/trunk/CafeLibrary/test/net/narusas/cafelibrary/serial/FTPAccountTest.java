package net.narusas.cafelibrary.serial;

import junit.framework.TestCase;

public class FTPAccountTest extends TestCase {
	public void testUseLast() {
		FTPAccount account = new FTPAccount("abc", "def", "111", "222");
		assertEquals(FTPAccount.getLast(), account);
	}
}
