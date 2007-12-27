package net.narusas.cafelibrary.bookfactories;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.xml.sax.SAXException;

import net.narusas.cafelibrary.Book;

public class MockDaum extends DaumFetcher {
	private final List<Book> books;

	public MockDaum(List<Book> books) {
		this.books = books;
	}

	@Override
	public FetchResult query(String query, int pageNo) throws UnsupportedEncodingException, SAXException, IOException {
		return new FetchResult(books, query);
	}
}
