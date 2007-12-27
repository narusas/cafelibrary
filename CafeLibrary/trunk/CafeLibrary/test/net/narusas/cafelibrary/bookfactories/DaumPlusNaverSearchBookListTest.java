package net.narusas.cafelibrary.bookfactories;

import java.util.LinkedList;

import net.narusas.cafelibrary.Book;

import junit.framework.TestCase;

public class DaumPlusNaverSearchBookListTest extends TestCase {
	public void testQueryEmpty() throws InterruptedException {
		DaumPlusNaverSearchBookList list = new DaumPlusNaverSearchBookList(new MockDaum(new LinkedList<Book>()),
				new MockNaver(null), "具客扼");
		assertEquals(0, list.getBookSize());
	}

	public void testQueryOneBook() {
		LinkedList<Book> books = new LinkedList<Book>();
		books.add(new Book("Title"));
		MockDaum daum = new MockDaum(books);

		DaumPlusNaverSearchBookList list = new DaumPlusNaverSearchBookList(daum, new MockNaver(null), "具客扼");
		list.join();
		assertEquals(1, list.getBookSize());
	}

	public void testBehindMerge() throws InterruptedException {
		LinkedList<Book> books = new LinkedList<Book>();
		Book bookForDaum = new Book("Title");
		bookForDaum.setIsbn("000");
		books.add(bookForDaum);
		MockDaum daum = new MockDaum(books);

		LinkedList<Book> booksForNaver = new LinkedList<Book>();
		Book bookForNaver = new Book("Title");
		bookForNaver.setDescription("Description");
		booksForNaver.add(bookForNaver);
		MockNaver naver = new MockNaver(booksForNaver);

		DaumPlusNaverSearchBookList list = new DaumPlusNaverSearchBookList(daum, naver, "具客扼");
		Thread.sleep(100);
		assertEquals(1, list.getBookSize());
		assertEquals("Description", list.get(0).getDescription());
	}
}
