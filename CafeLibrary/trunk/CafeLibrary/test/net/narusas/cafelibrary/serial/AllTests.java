package net.narusas.cafelibrary.serial;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for net.narusas.cafelibrary.serial");
		//$JUnit-BEGIN$
		suite.addTestSuite(SerializerImplementationTest.class);
		suite.addTestSuite(FTPAccountTest.class);
		suite.addTestSuite(LibrarySerializerTest.class);
		//$JUnit-END$
		return suite;
	}

}
