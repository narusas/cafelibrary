package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookFactory;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.BookListFilter;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibrarySerializePolicySet;
import net.narusas.cafelibrary.serial.FTPAccount;

public class ApplicationModel {
	BookList selectedBookList;
	Book selectedBook;

	public static enum ViewMethod {
		TABLE_VIEW, THUMBNAIL_VIEW
	};

	ViewMethod selectedViewMethod;

	public static enum BookDetailViewStatus {
		VIEW_DETAIL, SELECT_ADD_METHOD, EDIT_DETAIL, ADD_BY_SEARCH
	};

	BookDetailViewStatus currentBookDetailViewStatus;

	public static enum BookAddMethod {
		BY_SEARCH, BY_HAND
	};

	BookAddMethod selectedBookAddMethod;

	Library library;
	private BookListsViewController listController;
	private BooksListViewController listViewController;
	private BookDetailViewController bookDetailController;
	private BookFactory selectedBookFactory;

	public void setLibrary(Library lib) {
		this.library = lib;
	}

	public Library getLibrary() {
		return library;
	}

	public void setup(BookListsViewController listController, BooksListViewController listViewController,
			BookDetailViewController bookDetailController) {
		this.listController = listController;
		this.listViewController = listViewController;
		this.bookDetailController = bookDetailController;

	}

	public BookList getSelectedBookList() {
		return selectedBookList;
	}

	public Book getSelectedBook() {
		return selectedBook;
	}

	public void setBookList(BookList list) {
		System.out.println("setBookList:" + list + " c:" + list.getClass());
		this.selectedBookList = list;
		if (list instanceof Borrower) {
			listController.setSelectBorrower((Borrower) list);
		} else {
			listController.setSelectBookList(list);
		}

		listViewController.setBookList(list);
		setBook(list == null || list.size() == 0 ? null : list.get(0));
	}

	public void setBook(Book book) {
		this.selectedBook = book;
		bookDetailController.setBook(book);
	}

	public void deleteBook() {
		if (selectedBookList == null || selectedBook == null) {
			return;
		}
		int index = selectedBookList.indexOf(selectedBook);
		selectedBookList.remove(selectedBook);
		if (index >= selectedBookList.getBookSize()) {
			index--;
		}

		if (index < 0) {
			setBook(null);
		} else {
			setBook(selectedBookList.get(index));
		}
	}

	public void filter(String filterString) {
		System.out.println(filterString);
		System.out.println(selectedBookList.getClass());
		if (filterString == null || "".equals(filterString)) {
			Book currentSelectedBook = getSelectedBook();
			if (selectedBookList instanceof BookListFilter) {
				setBookList(((BookListFilter) selectedBookList).getOriginal());
			} else {
				setBookList(selectedBookList);
			}
			if (currentSelectedBook != null) {
				setBook(currentSelectedBook);
			}

		} else if (selectedBookList instanceof BookListFilter) {
			Book currentSelectedBook = getSelectedBook();
			BookList original = ((BookListFilter) selectedBookList).getOriginal();
			setBookList(new BookListFilter(original, filterString));
			if (currentSelectedBook != null) {
				setBook(currentSelectedBook);
			}
		}

		else {
			setBookList(new BookListFilter(selectedBookList, filterString));
		}

	}

	public void deleteBookList() {
		if (selectedBookList == null) {
			return;
		}
		int index = library.indexOf(selectedBookList);
		System.out.println("INDEX:" + index);
		library.remove(selectedBookList);

		if (index >= library.sizeOfBookLists()) {
			index--;
		}

		if (index < 0) {
			setBookList(null);
		} else {
			setBookList(library.getBookList(index));
		}
	}

	public void deleteBorrower() {
		if (selectedBookList == null) {
			return;
		}
		int index = library.indexOf((Borrower) selectedBookList);
		System.out.println("INDEX:" + index);
		library.remove((Borrower) selectedBookList);

		if (index >= library.sizeOfBorrowers()) {
			index--;
		}

		if (index < 0) {
			setBorrower((Borrower) null);
		} else {
			setBorrower((Borrower) library.getBorrower(index));
		}

	}

	private void setBorrower(Borrower borrower) {
		this.selectedBookList = borrower;
		listController.setSelectBorrower(borrower);
		listViewController.setBookList(borrower);
		setBook(borrower == null || borrower.size() == 0 ? null : borrower.get(0));
	}

	public void publishdRequested(FTPAccount account) {
		if (LibrarySerializePolicySet.getPlugin("FTP") == null) {
			return;
		}
		LibrarySerializePolicySet.getPlugin("FTP").serialize(account);
	}

	public void add(Book newBook) {
		selectedBookList.add(newBook);
		setBook(newBook);
	}

	public void addBookList(BookList bookList) {
		library.add(bookList);
	}

	public void addBorrower(Borrower borrower) {
		library.add(borrower);
	}

	public BookFactory getSelectedBookFactory() {
		return selectedBookFactory;
	}

	public void setBookFactory(BookFactory bf) {
		this.selectedBookFactory = bf;
	}

	public void bookAddReuqested() {
		bookDetailController.switchToSelectAddingMethodView();
	}

}
