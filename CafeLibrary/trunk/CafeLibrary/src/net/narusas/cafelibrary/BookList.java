package net.narusas.cafelibrary;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BookList extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7287096159197454561L;

	protected HashMap<Long, Book> bookMap = new HashMap<Long, Book>();
	protected List<Book> books = new LinkedList<Book>();

	protected List<BookListListener> listeners = new LinkedList<BookListListener>();

	public BookList(long id, String name) {
		super(id, name);
	}

	public BookList(String name) {
		super(IdGenerator.newId(), name);
	}

	public void add(Book book) {
		if (book == null) {
			return;
		}
		if (contains(book)) {
			return;
		}

		books.add(book);
		bookMap.put(book.getId(), book);
		notifyBookAdded(this, book);
	}

	boolean contains(Book book) {
		return bookMap.get(book.getId()) != null;
	}

	protected void notifyBookAdded(BookList bookList, Book book) {
		for (int i = 0; i < listeners.size(); i++) {
			BookListListener listener = listeners.get(i);
			listener.bookAdded(bookList, book);
		}
	}

	public int getBookSize() {
		return bookMap.size();
	}

	public Book findBookById(long id) {
		return bookMap.get(id);
	}

	public void remove(Book book) {
		if (book == null) {
			return;
		}
		Book removed = bookMap.remove(book.getId());
		if (removed == null) {
			return;
		}
		books.remove(book);
		notifyBookRemoved(this, removed);

	}

	public Book remove(int row) {
		Book removed = books.remove(row);
		if (removed == null) {
			return null;
		}
		bookMap.remove(removed.getId());
		notifyBookRemoved(this, removed);
		return removed;
	}

	protected void notifyBookRemoved(BookList bookList, Book removed) {
		for (int i = 0; i < listeners.size(); i++) {
			BookListListener listener = listeners.get(i);
			listener.bookRemoved(bookList, removed);
		}
	}

	public void addListener(BookListListener bookListListener) {
		if (bookListListener == null) {
			return;
		}
		listeners.add(bookListListener);
	}

	public void removeListener(BookListListener bookListListener) {
		listeners.remove(bookListListener);
	}

	public void setName(String name) {
		super.setName(name);
		notifyNameChanged(this, name);
	}

	protected void notifyNameChanged(BookList bookList, String name) {
		for (int i = 0; i < listeners.size(); i++) {
			BookListListener listener = listeners.get(i);
			listener.nameChanged(bookList, name);
		}
	}

	public int size() {
		return bookMap.size();
	}

	public Book get(int row) {
		if (row < 0 || row >= books.size()) {
			return null;
		}
		return books.get(row);
	}

	public List<Book> getCollection() {
		return books;
	}

	public void sortBySerialNo() {
		Collections.sort(books, new Comparator<Book>() {
			public int compare(Book o1, Book o2) {
				return o1.getSerialNo() < o2.getSerialNo() ? -1 : (o1.getSerialNo() > o2.getSerialNo() ? 1 : 0);
			}
		});
	}

	public int indexOf(Book selectedBook) {
		return books.indexOf(selectedBook);
	}

}
