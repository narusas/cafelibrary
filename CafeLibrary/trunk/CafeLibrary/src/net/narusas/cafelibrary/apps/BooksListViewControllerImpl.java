package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.serial.FTPAccount;

public class BooksListViewControllerImpl implements BooksListViewController, BooksListViewCallback {

	private final ApplicationModel model;
	private BookList selectedBookList;
	private BooksListView view;

	enum ViewMethod {
		AS_TABLE, AS_BOOKSHELF
	};

	ViewMethod currentMethod = ViewMethod.AS_TABLE;

	public BooksListViewControllerImpl(ApplicationModel model) {
		this.model = model;
	}

	public BookList getSelectedBookList() {
		return selectedBookList;
	}

	public void setBookList(BookList list) {
		this.selectedBookList = list;

		updateView(list);
	}

	private void updateView(BookList list) {
		if (view == null) {
			return;
		}
		view.updateModels(list);
		view.enableAddBookControl(true);
		view.enableDeleteControl(false);
		if (list instanceof Borrower) {
			view.setDeleteControlAsReturnBookImage();
		} else {
			view.setDeleteControlAsDeleteImage();
		}
		view.setFilter(null);
		Book book = (list == null || list.size() == 0) ? null : list.get(0);
		view.setSelectedBook(book);
		view.enableDeleteControl(book != null);
		view.setSelectedBookVisible();
	}

	public void bookSelected(Book book) {
		model.setBook(book);
	}

	public void deleteBookRequested() {
		model.deleteBook();
	}

	public void publishRequested(FTPAccount account) {
		model.publishdRequested(account);
	}

	public void setView(BooksListView view) {
		this.view = view;
	}

	public void requestPublish() {
		FTPAccount account = view.askPublishInfo(FTPAccount.getLast());
		if (account == null) {
			return;
		}
		publishRequested(account);
	}

	public void bookAddRequested() {
		model.bookAddReuqested();
	}

	public void changeViewRequested() {
		if (currentMethod == ViewMethod.AS_TABLE) {
			currentMethod = ViewMethod.AS_BOOKSHELF;
			switchToBookShelfView();
		} else {
			currentMethod = ViewMethod.AS_TABLE;
			switchToTableView();
		}
	}

	public void switchToTableView() {
		view.switchToTableView();
	}

	public void switchToBookShelfView() {
		view.switchToBookShelfView();
	}

	public void filteringRequested(String text) {
		model.filter(text);
	}

	public void showOptionsRequested() {

	}

	public void bookSelected(int row) {
		model.setBook(model.getLibrary().get(row));
	}

}

interface BookListViewControllerListener {
	void bookAddRequested();
}