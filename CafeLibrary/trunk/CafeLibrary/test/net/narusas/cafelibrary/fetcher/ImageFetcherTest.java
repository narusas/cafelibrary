package net.narusas.cafelibrary.fetcher;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.bookfactories.ImageDownloader;
import junit.framework.TestCase;

public class ImageFetcherTest extends TestCase {
	public void testDownloadImage() {
		Book book = new Book("abc");
		book.setCoverLargeUrl("http://photo-book.hanmail.net/images/book/large/477/l9788955377477.jpg");
		book.setCoverSmallUrl("http://photo-book.hanmail.net/images/book/medium/477/m9788955377477.jpg");

		ImageDownloader fetcher = new ImageDownloader();
		fetcher.download(book); 
	}
}
