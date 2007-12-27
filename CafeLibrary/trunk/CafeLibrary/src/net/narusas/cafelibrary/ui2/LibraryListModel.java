package net.narusas.cafelibrary.ui2;

import javax.swing.AbstractListModel;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryListener;

public class LibraryListModel extends AbstractListModel implements LibraryListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4808620970315282364L;

	Library library;

	public Object getElementAt(int index) {
		return library == null ? null : library.getBookList(index);

	}

	public int indexOf(BookList list) {
		return library.indexOf(list);
	}

	public int getSize() {
		return library == null ? 0 : library.sizeOfBookLists();
	}

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
		library.addLibraryListener((LibraryListener) this);
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void bookListAdded(BookList value) {
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void bookListRemoved(BookList value) {
		System.out.println("Remove:" + value);
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void borrowerAdded(Borrower value) {
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void borrowerRemoved(Borrower value) {
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void bookAdded(BookList list, Book book) {
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void bookRemoved(BookList list, Book book) {
		fireContentsChanged(this, 0, getSize() - 1);
	}

	public void nameChanged(BookList list, String newName) {

	}

	public void bookChanged(Library library, Book value) {
		fireContentsChanged(library, 0, getSize() - 1);

	}

	public void add(int index, String bookId) {
		System.out.println("## add by dnd:" + bookId);
		String[] tokens = bookId.split(",");
		for (String token : tokens) {
			long id = Long.parseLong(token);
			Book book = library.findBookById(id);
			System.out.println(book);
			addToList(index, book);
		}
	}

	protected void addToList(int index, Book book) {
		System.out.println("index:" + index);
		BookList bookList = library.getBookList(index - 2);
		System.out.println(bookList);
		bookList.add(book);
	}

	public void bookListChanged(BookList value) {

	}

	public void borrowerChanged(Borrower value) {

	}
}
