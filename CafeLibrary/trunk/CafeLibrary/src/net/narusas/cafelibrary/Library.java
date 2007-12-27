package net.narusas.cafelibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Library extends BookList implements BookListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6302884094339530168L;

	enum EventType {
		BOOK_ADDED, BOOK_REMOVED, BOOKLIST_ADDED, BOOKLIST_REMOVED, BOOKLIST_CHANGED, BORROWER_ADDED, BORROWER_REMOVED, BORROWER_CHANGED, BOOK_CHANGED
	};

	private LinkedList<BookList> bookLists;
	private LinkedList<Borrower> borrowers;
	LinkedList<LibraryListener> libListeners;

	BookListListener bookListListener = new BookListListener() {

		public void bookAdded(BookList list, Book book) {
			EventType eventType = EventType.BOOKLIST_CHANGED;
			if (list instanceof Borrower) {
				eventType = EventType.BORROWER_CHANGED;
			}
			if (contains(book) == false) {
				add(book);
			}
			notifyLibraryChanged(eventType, list);
		}

		public void bookRemoved(BookList list, Book book) {
			EventType eventType = EventType.BOOKLIST_CHANGED;
			if (list instanceof Borrower) {
				eventType = EventType.BORROWER_CHANGED;
			}
			notifyLibraryChanged(eventType, list);
		}

		public void nameChanged(BookList list, String newName) {

		}
	};

	public Library() {
		super(Long.MIN_VALUE, "Library");
		bookLists = new LinkedList<BookList>();
		bookLists.add(this);
		borrowers = new LinkedList<Borrower>();
		libListeners = new LinkedList<LibraryListener>();

	}

	private void notifyLibraryChanged(EventType event, Object value) {
		for (int i = 0; i < libListeners.size(); i++) {
			LibraryListener listener = libListeners.get(i);
			switch (event) {
			case BOOK_ADDED:
				listener.bookAdded(this, (Book) value);
				break;
			case BOOK_REMOVED:
				listener.bookRemoved(this, (Book) value);
				break;

			case BOOK_CHANGED:
				listener.bookChanged(this, (Book) value);
				break;

			case BOOKLIST_ADDED:
				listener.bookListAdded((BookList) value);
				break;
			case BOOKLIST_REMOVED:
				listener.bookListRemoved((BookList) value);
				break;

			case BOOKLIST_CHANGED:
				listener.bookListChanged((BookList) value);
				break;

			case BORROWER_ADDED:
				listener.borrowerAdded((Borrower) value);
				break;
			case BORROWER_REMOVED:
				listener.borrowerRemoved((Borrower) value);
				break;

			case BORROWER_CHANGED:
				listener.borrowerChanged((Borrower) value);
				break;
			}
		}
	}

	public void add(Book book) {
		super.add(book);
		book.addListener(this);
		notifyLibraryChanged(EventType.BOOK_ADDED, (Object) book);
	}

	public void remove(Book book) {
		super.remove(book);
		removeFromBookLists(book);
		exclusive(null, book);
		notifyLibraryChanged(EventType.BOOK_REMOVED, (Object) book);
	}

	private void removeFromBookLists(Book book) {
		for (BookList bList : bookLists) {
			if (bList == this) {
				continue;
			}
			bList.remove(book);
		}
	}

	public Book remove(int row) {
		Book remove = super.remove(row);
		removeFromBookLists(remove);
		exclusive(null, remove);
		notifyLibraryChanged(EventType.BOOK_REMOVED, (Object) remove);
		return remove;
	}

	public void add(BookList bList) {
		bookLists.add(bList);
		bList.addListener(bookListListener);
		notifyLibraryChanged(EventType.BOOKLIST_ADDED, (Object) bList);
	}

	public void remove(BookList bList) {
		if (bList == this) {
			return;
		}
		bookLists.remove(bList);
		notifyLibraryChanged(EventType.BOOKLIST_REMOVED, bList);
	}

	public void add(Borrower borrower) {
		borrower.setLibrary(this);
		borrowers.add(borrower);
		borrower.addListener(bookListListener);
		notifyLibraryChanged(EventType.BORROWER_ADDED, borrower);
	}

	public void remove(Borrower borrower) {
		borrowers.remove(borrower);
		notifyLibraryChanged(EventType.BORROWER_REMOVED, borrower);
	}

	public int sizeOfBookLists() {
		return bookLists.size();
	}

	public BookList getBookList(int index) {
		return bookLists.get(index);
	}

	public void addLibraryListener(LibraryListener libarayListener) {
		libListeners.add(libarayListener);
	}

	public Borrower getBorrower(int index) {
		return borrowers.get(index);
	}

	public int sizeOfBorrowers() {
		return borrowers.size();
	}

	public String getName() {
		return "Library";
	}

	public void bookChanged(Book book, String attrName, Object value) {
		notifyLibraryChanged(EventType.BOOK_CHANGED, book);
	}

	public void removeBookList(int i) {
		if (i == 0) {
			return;
		}
		BookList remove = bookLists.remove(i);
		notifyLibraryChanged(EventType.BOOKLIST_REMOVED, remove);
	}

	public void removeBorrower(int i) {
		Borrower remove = borrowers.remove(i);
		notifyLibraryChanged(EventType.BOOKLIST_REMOVED, remove);
	}

	public void exclusive(Borrower borrower, Book book) {
		for (int i = 0; i < borrowers.size(); i++) {
			Borrower b = borrowers.get(i);
			if (b == borrower) {
				continue;
			}
			b.remove(book);
		}
	}

	public int indexOf(BookList selectedBookList) {
		return bookLists.indexOf(selectedBookList);
	}

}
