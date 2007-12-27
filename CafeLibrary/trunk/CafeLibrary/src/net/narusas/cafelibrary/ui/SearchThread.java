package net.narusas.cafelibrary.ui;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.swing.SwingUtilities;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.bookfactories.NaverBookInfomationMerger;
import net.narusas.cafelibrary.bookfactories.DaumFetcher;
import net.narusas.cafelibrary.bookfactories.FetchResult;
import net.narusas.cafelibrary.bookfactories.NaverFetcher;

import org.xml.sax.SAXException;

public class SearchThread extends Thread {
	private final int pageNo;
	private final String searchText;
	private final MainFrame f;
	public static NaverBookInfomationMerger merger;

	public SearchThread(int pageNo, String searchText, MainFrame f) {
		super("Search Thread");
		this.pageNo = pageNo;
		this.searchText = searchText;
		this.f = f;
		setDaemon(true);
		if (merger == null) {
			merger = new NaverBookInfomationMerger(new NaverFetcher());
			merger.start();
		}
	}

	public void run() {
		DaumFetcher fetcher = new DaumFetcher();
		try {
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					f.getAddBookBySearchPanel().clearBooks();
					f.getAddBookBySearchPanel().startWaiter();
				}
			});

			final FetchResult books = fetcher.query(searchText, pageNo);
			List<Book> res = books.getRes();
			merger.add(res);
			SwingUtilities.invokeLater(new Runnable() {

				public void run() {
					f.getAddBookBySearchPanel().stopWaiter();
					f.getAddBookBySearchPanel().addBooks(books);
				}
			});

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
