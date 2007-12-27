package net.narusas.cafelibrary.serial;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryListener;
import net.narusas.cafelibrary.LibrarySerilizePolicy;

public class EveryLibraryChangeSerializePolicy extends LibrarySerilizePolicy implements LibraryListener {

	private final Library lib;

	public EveryLibraryChangeSerializePolicy(Library lib) {
		this.lib = lib;
		this.lib.addLibraryListener(this);
	}

	public void bookChanged(Library library, Book value) {
		fire(null);
	}

	public void bookListAdded(BookList value) {
		fire(null);
	}

	public void bookListChanged(BookList value) {
		fire(null);
	}

	public void bookListRemoved(BookList value) {
		fire(null);
	}

	public void borrowerAdded(Borrower value) {
		fire(null);
	}

	public void borrowerChanged(Borrower value) {
		fire(null);
	}

	public void borrowerRemoved(Borrower value) {
		fire(null);
	}

	public void bookAdded(BookList list, Book book) {
		fire(null);
	}

	public void bookRemoved(BookList list, Book book) {
		fire(null);
	}

	public void nameChanged(BookList list, String newName) {
		fire(null);
	}

}
