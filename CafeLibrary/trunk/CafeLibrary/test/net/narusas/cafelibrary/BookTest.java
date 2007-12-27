package net.narusas.cafelibrary;

import junit.framework.TestCase;

public class BookTest extends TestCase {
	boolean isTested = false;
	int testCount = 0;
	int testCount2 = 0;

	protected void setUp() throws Exception {
		super.setUp();
		isTested = false;
		testCount = 0;
		testCount2 = 0;
	}

	public void testCreateBook() {
		Book book = new Book(0, "untitled");
		assertEquals(0, book.getId());
		assertEquals("untitled", book.getName());
		book.setTitle("abc");
		assertEquals("abc", book.getName());
		assertEquals("abc", book.getTitle());
	}

	public void testIdGenerator() {
		long id = IdGenerator.newId();
		assertTrue(id != IdGenerator.newId());
		IdGenerator.setLastId(100);
		assertTrue(IdGenerator.newId() > 100);
	}

	public void testBookList() {
		Book book = new Book("abc");
		BookList list = new BookList(0, "bookList");

		list.add(book);
		assertEquals(1, list.getBookSize());
		assertNull(list.findBookById(0));
		assertEquals(book, list.findBookById(book.getId()));

		list.remove(book);
		assertNull(list.findBookById(book.getId()));
	}

	public void testBookListener() {
		Book book = new Book("abc");
		book.addListener(new BookListener() {
			public void bookChanged(Book book, String attrName, Object value) {
				assertEquals("title", attrName);
				assertEquals("named", value);
				isTested = true;
			}
		});
		book.setTitle("named");
		assertTrue(isTested);
	}

	public void testBookListListener() {
		final Book theBook = new Book("abc");
		BookList bList = new BookList("def");

		bList.addListener(new BookListListener() {
			public void bookAdded(BookList bList, Book book) {
				assertEquals(theBook, book);
				isTested = true;
			}

			public void bookRemoved(BookList bList, Book book) {
				assertEquals(theBook, book);
				isTested = true;
			}

			public void nameChanged(BookList bList, String newName) {
				assertEquals("new name", newName);
				isTested = true;
			}
		});
		isTested = false;

		bList.add(theBook);
		assertTrue(isTested);

		isTested = false;
		bList.remove(theBook);
		assertTrue(isTested);

		isTested = false;
		bList.setName("new name");
		assertTrue(isTested);
	}

	

	public void testSerailBook() {
		Book book1 = new Book("야와라 2");

		assertTrue(book1.isSerial());
		assertEquals(2, book1.getSerialNo());
		assertEquals("야와라", book1.getTitleWithNoSerial());

		Book book2 = new Book("유리가면 3(애장판)");

		assertTrue(book2.isSerial());
		assertEquals(3, book2.getSerialNo());
		assertEquals("유리가면 (애장판)", book2.getTitleWithNoSerial());
	}

	public void testFilter() {
		assertTrue(new Book("Title 2").isMatch("2"));
		assertFalse(new Book("Title two").isMatch("2"));
	}

	public void testAddBookRecurcive() {
		Library lib = new Library();
		BookList bookList = new BookList("booklist");
		lib.add(bookList);
		assertEquals(0, lib.getBookSize());

		Book newBook = new Book("new book");
		bookList.add(newBook);

		assertEquals(1, lib.getBookSize());
		assertEquals(newBook, lib.get(0));
	}
}
