package net.narusas.cafelibrary.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.bookfactories.NaverBookInfomationMerger;
import net.narusas.cafelibrary.bookfactories.FetchResult;

public class BookDetailController {

	private final Library lib;

	private final MainFrame f;

	private String searchText;

	private SearchThread searchThread;

	private Book book;

	public BookDetailController(Library lib, MainFrame f) {
		this.lib = lib;
		this.f = f;
		bind();
	}

	private void bind() {
		f.getBookControlPanel().getEditButton().addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						editBook();
					}
				});
	}

	protected void editBook() {
//		int row = f.getBookListTable().getBookListTable().getSelectedRow();
//		Book book = f.getBookListTable().getBookListTableModel().get(row);
		if (book == null) {
			return;
		}
		f.switchToEditBook(book);
	}

	protected void addBook() {
		f.switchToSelectAddingMethod();
		f.getSelectAddingMethodPanel().getAddBookByWriteButton()
				.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						addBookByWrite();
					}
				});
		f.getSelectAddingMethodPanel().getAddBookBySearchButton()
				.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						addBookBySearch();
					}
				});

		f.getSelectAddingMethodPanel().getSearchTextField().addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addBookBySearch();
					}
				});

		// 서치 TF에 포커스가 가면 글자를 모두 선택하게 하여 쉽게 변경할수 있게 한다.
		f.getSelectAddingMethodPanel().getSearchTextField().addFocusListener(
				new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						f.getSelectAddingMethodPanel().getSearchTextField()
								.select(
										0,
										f.getSelectAddingMethodPanel()
												.getSearchTextField().getText()
												.length());
					}
				});

	}

	protected void addBookByWrite() {
		Book book = new Book("");
		lib.add(book);
		f.switchToAddBookByWrite();
		f.getAddBookByWritePanel().setBook(book);
	}

	protected void addBookBySearch() {
		searchText = f.getSelectAddingMethodPanel().getSearchTextField()
				.getText();
		f.switchToAddBookBySearch(searchText);
		startSearch(1);
		f.getAddBookBySearchPanel().getSearchTextField().addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						searchText = f.getAddBookBySearchPanel()
								.getSearchTextField().getText();
						startSearch(1);
					}
				});
		f.getAddBookBySearchPanel().getSearchButton().addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						searchText = f.getAddBookBySearchPanel()
								.getSearchTextField().getText();
						startSearch(1);
					}
				});
		f.getAddBookBySearchPanel().setListener(new AddBookBySearchListener() {

			public void addBook(final Book book) {

				new Thread(){
					public void run() {
						UIStatus.setBGUpdate(true);
						lib.add(book);
						UIStatus.setBGUpdate(false);
					}
				}.start();
				
			}
		});

		f.getAddBookBySearchPanel().getNextPageButton().addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						nextSearch();
					}
				});
		f.getAddBookBySearchPanel().getPreviousButton().addActionListener(
				new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						previousSearch();
					}
				});
	}

	protected void previousSearch() {
		FetchResult res = f.getAddBookBySearchPanel().getFetchResult();
		if (res == null || res.isFirstPageResult()) {
			return;
		}
		startSearch(res.getCurrentPage() - 1);
	}

	void bookSelected(Book book) {
		this.book = book;
		f.switchToBookDetail();
		f.getBookDetailPanel().setBook(book);
	}

	protected void nextSearch() {
		FetchResult res = f.getAddBookBySearchPanel().getFetchResult();
		if (res == null || res.isLastPageResult()) {
			return;
		}
		startSearch(res.getCurrentPage() + 1);
	}

	private void startSearch(int pageNo) {
		if (searchThread != null) {
			searchThread.stop();
			searchThread = null;
		}
		searchThread = new SearchThread(pageNo, searchText, f);
		searchThread.start();
	}

}

interface BookDetailListener {
	void bookAdded(Book book);
}
