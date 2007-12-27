package net.narusas.cafelibrary.ui2;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.Borrower;

public class LibraryBorrowerListModel extends LibraryListModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8367376795491403498L;

	public Object getElementAt(int index) {
		return library == null ? null : library.getBorrower(index);
	}

	public int getSize() {
		return library == null ? 0 : library.sizeOfBorrowers();
	}

	protected void addToList(int index, Book book) {
		Borrower borrower = library.getBorrower(index - 1);
		borrower.add(book);
	}
}
