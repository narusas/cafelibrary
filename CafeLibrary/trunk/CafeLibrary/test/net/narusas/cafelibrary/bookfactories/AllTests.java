package net.narusas.cafelibrary.bookfactories;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary.bookfactories");
		// $JUnit-BEGIN$
		suite.addTestSuite(DaumPlusNaverSearchBookListTest.class);
		// $JUnit-END$
		return suite;
	}

}
