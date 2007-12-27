package net.narusas.cafelibrary.fetcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.bookfactories.DaumFetcher;
import net.narusas.cafelibrary.bookfactories.FetchResult;
import net.narusas.cafelibrary.serial.LibrarySerializer;

import org.xml.sax.SAXException;

import junit.framework.TestCase;

public class DaumFetcherTest extends TestCase {
	public void testFetcher() throws UnsupportedEncodingException, SAXException, IOException {
		String apikey = "41fbf00c99adcd9293d95acb9383c28b";

		DaumFetcher fetcher = new DaumFetcher(apikey);
		FetchResult fetRes = fetcher.query("은하", 1);

		List<Book> books = fetRes.getRes();
		assertTrue(books.size() > 0);
		Library lib = new Library();
		BookList dummy = new BookList("Dummy");
		for (Book book : books) {
			lib.add(book);
			dummy.add(book);
		}
		lib.add(dummy);
		LibrarySerializer s = new LibrarySerializer(lib);
		s.store(lib);

	}

	public void testFetcherCountPage() throws UnsupportedEncodingException, SAXException, IOException {
		String apikey = "41fbf00c99adcd9293d95acb9383c28b";

		DaumFetcher fetcher = new DaumFetcher(apikey);
		FetchResult fetRes = fetcher.query("야와라", 1);
		assertEquals(31, fetRes.getTotalCount());
		assertEquals(1, fetRes.getCurrentPage());
		assertEquals(19, fetRes.getResultCount());
		assertTrue(fetRes.isFirstPageResult());
		assertFalse(fetRes.isLastPageResult());
		assertEquals(2, fetRes.getTotalPage());

	}

	// public void testParseDate() {
	// String src = "Thu, 20 Oct 05 00:00:00 +0900";
	//
	// DaumFetcher fetcher = new DaumFetcher("");
	//
	// Date d = fetcher.toDate(src);
	// assertEquals(2005 - 1900, d.getYear());
	// assertEquals(10 - 1, d.getMonth());
	// assertEquals(20, d.getDate());
	// }
}
