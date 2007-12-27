package net.narusas.cafelibrary.fetcher;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary.fetcher");
		// $JUnit-BEGIN$
		suite.addTestSuite(DaumFetcherTest.class);
		suite.addTestSuite(RegxTest.class);
		suite.addTestSuite(NaverFetcherTest.class);
		suite.addTestSuite(ImageFetcherTest.class);
		// $JUnit-END$
		return suite;
	}

}
