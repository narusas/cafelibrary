package net.narusas.cafelibrary.bookfactories;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import org.xml.sax.SAXException;

import net.narusas.cafelibrary.Book;

public class MockNaver extends NaverFetcher {

	private final LinkedList<Book> books;

	public MockNaver(LinkedList<Book> books) {
		this.books = books;
	}

	@Override
	public FetchResult queryByISBN(String isbn, int pageNo) throws UnsupportedEncodingException, SAXException,
			IOException {
		return new FetchResult(books, isbn);
	}
}
