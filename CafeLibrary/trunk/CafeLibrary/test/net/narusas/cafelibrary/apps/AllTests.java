package net.narusas.cafelibrary.apps;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary.apps");
		//$JUnit-BEGIN$
		suite.addTestSuite(BookDetailControllerTest.class);
		suite.addTestSuite(ApplicationModelTest.class);
		suite.addTestSuite(BooksListControllerTest.class);
		suite.addTestSuite(BookListsControllerTest.class);
		//$JUnit-END$
		return suite;
	}

}
