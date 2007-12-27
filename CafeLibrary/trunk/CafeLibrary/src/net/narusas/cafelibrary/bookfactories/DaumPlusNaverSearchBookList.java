package net.narusas.cafelibrary.bookfactories;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.xml.sax.SAXException;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;

public class DaumPlusNaverSearchBookList extends BookList implements Runnable {

	private final DaumFetcher daumFetcher;
	private final NaverFetcher naverFetcher;
	private final String searchTarget;
	private Thread thread;
	private NaverBookInfomationMerger merger;

	public DaumPlusNaverSearchBookList(DaumFetcher daumFetcher, NaverFetcher naverFetcher, String searchTarget) {
		super(searchTarget);
		this.daumFetcher = daumFetcher;
		this.naverFetcher = naverFetcher;
		this.searchTarget = searchTarget;

		merger = new NaverBookInfomationMerger(naverFetcher);
		merger.start();

		thread = new Thread(this);
		thread.start();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 589253185157258901L;

	public void run() {
		FetchResult res = query(1);
		if (res == null) {
			return;
		}
		addBooks(res);
		int lastPage = res.getTotalPage();
		for (int i = 2; i <= lastPage; i++) {
			res = query(i);
			addBooks(res);
		}

	}

	private FetchResult query(int page) {
		try {
			return daumFetcher.query(searchTarget, page);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void addBooks(FetchResult res) {
		for (int i = 0; i < res.getRes().size(); i++) {
			Book book = res.getRes().get(i);
			add(book);
			merger.add(book);
		}
	}

	void join() {
		if (thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
			}
		}
	}
}
