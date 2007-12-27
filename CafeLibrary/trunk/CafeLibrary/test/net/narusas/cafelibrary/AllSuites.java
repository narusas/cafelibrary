package net.narusas.cafelibrary;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllSuites {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary");
		// $JUnit-BEGIN$
		suite.addTest(AllTests.suite());
		suite.addTest(net.narusas.cafelibrary.bookfactories.AllTests.suite());
		suite.addTest(net.narusas.cafelibrary.fetcher.AllTests.suite());
		suite.addTest(net.narusas.cafelibrary.apps.AllTests.suite());
		suite.addTest(net.narusas.cafelibrary.serial.AllTests.suite());

		// $JUnit-END$
		return suite;
	}

}
