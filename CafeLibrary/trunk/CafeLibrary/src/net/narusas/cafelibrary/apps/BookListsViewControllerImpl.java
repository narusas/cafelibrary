package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;

public class BookListsViewControllerImpl implements BookListsViewController, BookListsViewCallback {

	private final ApplicationModel model;
	private BookListsView view;
	private boolean isUpdating;

	public BookListsViewControllerImpl(ApplicationModel model) {
		this.model = model;
	}

	public void bookListSelected(BookList list) {
		if (isUpdating) {
			return;
		}
		isUpdating = true;
		model.setBookList(list);
		isUpdating = false;
	}

	public void borrowerSelected(Borrower borrower) {
		if (isUpdating) {
			return;
		}
		isUpdating = true;
		model.setBookList(borrower);
		isUpdating = false;
	}

	public void filter(String filterString) {
		model.filter(filterString);
	}

	public void deleteBookList() {
		model.deleteBookList();
	}

	public void deleteBorrower() {
		model.deleteBorrower();
	}

	public void setView(BookListsView view) {
		this.view = view;
	}

	public void setSelectBookList(BookList list1) {
		view.unhighlightBorrowers();
		view.selectBookList(list1);
		view.setBookListVisible();
	}

	public void setSelectBorrower(Borrower borrower) {
		view.unhighlightBookLists();
		view.selectBorrower(borrower);
		view.setBorrowerVisible();
	}

	public void bookListAddRequested() {
		String newName = view.askNewBookListName();
		if (newName == null || "".equals(newName)) {
			return;
		}

		model.addBookList(new BookList(newName));
	}

	public void borrowerAddRequested() {
		String newName = view.askNewBookListName();
		if (newName == null || "".equals(newName)) {
			return;
		}

		model.addBorrower(new Borrower(newName));
	}

}
