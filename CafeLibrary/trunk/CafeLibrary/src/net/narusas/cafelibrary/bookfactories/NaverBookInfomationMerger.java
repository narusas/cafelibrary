package net.narusas.cafelibrary.bookfactories;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import net.narusas.cafelibrary.Book;

import org.xml.sax.SAXException;

public class NaverBookInfomationMerger extends Thread {

	private LinkedList<Book> books;

	private NaverFetcher naverFetcher;

	private ImageDownloader imgFetcher;

	private boolean isClose = false;

	public NaverBookInfomationMerger(NaverFetcher naverFetcher) {
		super("Book Information Merger");
		setDaemon(true);
		books = new LinkedList<Book>();
		this.naverFetcher = naverFetcher;
		imgFetcher = new ImageDownloader();
	}

	public void run() {
		while (isClose == false) {
			synchronized (books) {
				if (books.size() == 0) {
					try {
						books.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (books.size() == 0) {
					continue;
				}
			}
			Book book = books.remove(0);
			if (book.getIsbn() == null || "".equals(book.getIsbn())) {
				continue;
			}
			try {
				FetchResult res = naverFetcher.queryByISBN(book.getIsbn(), 1);
				if (res == null || res.getRes() == null || res.getRes().size() == 0) {
					continue;
				}
				Book naverBook = res.getRes().get(0);
				book.merge(naverBook);
				imgFetcher.download(book);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void add(Book book) {
		synchronized (books) {
			books.add(book);
			books.notifyAll();
		}
	}

	public void add(List<Book> res) {
		synchronized (books) {
			books.addAll(res);
			books.notifyAll();
		}
	}

	public void close() {
		isClose = true;
	}
}
