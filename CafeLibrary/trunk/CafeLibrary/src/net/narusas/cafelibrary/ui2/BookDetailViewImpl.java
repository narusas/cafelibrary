package net.narusas.cafelibrary.ui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.apps.BookDetailView;
import net.narusas.cafelibrary.apps.BookDetailViewCallback;

public class BookDetailViewImpl implements BookDetailView {

	private BookDetailViewCallback callback;
	private final BookDetailSection ui;
	private Book book;
	private BookDetailPresentView detailView;
	private BookDetailEditView editView;
	private SelectAddingMethodView selectAddingMethodView;
	private SearchedBookListView searchView;
	private SearchedBookListController searchViewController;

	public BookDetailViewImpl(BookDetailSection section) {
		this.ui = section;
		ui.getEditButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.bookEditRequested();
			}
		});

		detailView = new BookDetailPresentView();
		editView = new BookDetailEditView();
		selectAddingMethodView = new SelectAddingMethodView();
		selectAddingMethodView.getSearchTitleButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.bookAddBySearchRequested(selectAddingMethodView.getSearchTitle().getText());
			}
		});

		selectAddingMethodView.getSearchTitle().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.bookAddBySearchRequested(selectAddingMethodView.getSearchTitle().getText());
			}
		});

		searchView = new SearchedBookListView();
		searchViewController = new SearchedBookListController(searchView);
	}

	public void clearInfos() {

	}

	public void setBook(Book book) {
		this.book = book;
		detailView.setBook(book);
		editView.setBook(book);
		ui.getTitleLabel().setText(book == null ? null : book.getTitle());
	}

	public void setCallback(BookDetailViewCallback callback) {
		this.callback = callback;
	}

	public void setSearch(String searchTarget) {
	}

	public void setSearchingBookList(BookList searchingList) {
		searchViewController.setBookList(searchingList);
	}

	public void showBookDetailView() {
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(detailView);
	}

	public void showBookEditView() {
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(editView);
	}

	public void showSearchingBookView() {
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(searchView);

	}

	public void showSelectAddingMethodView() {
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(selectAddingMethodView);
	}
}
