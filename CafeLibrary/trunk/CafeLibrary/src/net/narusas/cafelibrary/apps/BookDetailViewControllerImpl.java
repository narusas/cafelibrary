package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;

public class BookDetailViewControllerImpl implements BookDetailViewController, BookDetailViewCallback {

	private final ApplicationModel model;
	private Book selectedBook;
	private BookDetailView view;
	private boolean handleExternalEvent = true;

	enum ViewStatus {
		VIEW, EDIT, SELECT_ADDING, SEARCH
	};

	ViewStatus status = ViewStatus.VIEW;

	public BookDetailViewControllerImpl(ApplicationModel model) {
		this.model = model;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setBook(Book book) {
		if (handleExternalEvent == false) {
			return;
		}
		this.selectedBook = book;
		if (view == null) {
			return;
		}
		view.showBookDetailView();
		view.clearInfos();
		view.setBook(book);

	}

	public void add(Book newBook) {
		model.add(newBook);
	}

	public void setView(BookDetailView view) {
		this.view = view;
	}

	public void bookAddRequested() {
		view.showSelectAddingMethodView();
	}

	public Book getSettedBook() {
		return this.selectedBook;
	}

	public void bookAddByHandRequested() {
		enableExternalEventHanding(false);
		Book book = new Book("Title");
		model.add(book);
		view.showBookEditView();
		view.setBook(book);
		enableExternalEventHanding(true);
	}

	private void enableExternalEventHanding(boolean b) {
		handleExternalEvent = b;
	}

	public void bookAddBySearchRequested(String searchTarget) {
		BookList searchingList = model.getSelectedBookFactory().findBooks(searchTarget);
		view.showSearchingBookView();
		view.setSearch(searchTarget);
		view.setSearchingBookList(searchingList);
	}

	public void addSearchedBook(Book book) {
		enableExternalEventHanding(false);
		model.add(book);
		enableExternalEventHanding(true);
	}

	public void switchToSelectAddingMethodView() {
		view.showSelectAddingMethodView();
	}

	public void bookEditRequested() {
		if (status != ViewStatus.EDIT) {
			status = ViewStatus.EDIT;
			view.showBookEditView();
			view.setBook(model.selectedBook);
		} else {
			status = ViewStatus.VIEW;
			view.showBookDetailView();
			view.setBook(model.selectedBook);
		}

	}

}
