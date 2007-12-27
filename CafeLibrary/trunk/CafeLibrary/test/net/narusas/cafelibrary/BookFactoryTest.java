package net.narusas.cafelibrary;

import junit.framework.TestCase;

public class BookFactoryTest extends TestCase {
	public void testListUpFactories() {
		BookFactory.addPlugin(new BookFactory("BF 1") {
			public BookList findBooks(String searchTarget) {
				return null;
			}

			public Book findSpecificBook(String searchTarget) {
				return null;
			}
		});

		BookFactory.addPlugin(new BookFactory("BF 2") {
			public BookList findBooks(String searchTarget) {
				return null;
			}

			public Book findSpecificBook(String searchTarget) {
				return null;
			}
		});

		assertEquals(2, BookFactory.listPlugins().size());
		assertEquals("BF 1", BookFactory.listPlugins().get(0).getName());
		assertEquals("BF 2", BookFactory.listPlugins().get(1).getName());
	}

}
