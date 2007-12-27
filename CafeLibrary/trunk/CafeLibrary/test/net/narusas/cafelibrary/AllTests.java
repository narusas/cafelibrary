package net.narusas.cafelibrary;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary");
		// $JUnit-BEGIN$
		suite.addTestSuite(BorrowerTest.class);
		suite.addTestSuite(BookTest.class);
		suite.addTestSuite(BookFactoryTest.class);
		suite.addTestSuite(LibraryManagerTest.class);
		suite.addTestSuite(LibraryTest.class);
		// $JUnit-END$
		return suite;
	}

}
