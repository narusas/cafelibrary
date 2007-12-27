package net.narusas.cafelibrary;

import junit.framework.TestCase;

public class BorrowerTest extends TestCase {
	public void testBorrow() {
		Library lib = new Library();
		Book book = new Book("abc");
		Borrower borrower = new Borrower("me", lib);
		borrower.borrow(book);

		assertEquals(borrower, book.getBorrower());
		borrower.returnBook(book);

	}

	public void testExclusiveBorrow() {
		Library lib = new Library();
		Book book = new Book("abc");
		Borrower borrower = new Borrower("me", lib);
		borrower.add(book);

		assertEquals(borrower, book.getBorrower());
		assertEquals(1, borrower.getBookSize());

		Borrower borrower2 = new Borrower("me2", lib);
		lib.add(borrower);
		lib.add(borrower2);
		borrower2.add(book);
		
		

		assertEquals(borrower2, book.getBorrower());
		assertEquals(0, borrower.getBookSize());
	}
}
