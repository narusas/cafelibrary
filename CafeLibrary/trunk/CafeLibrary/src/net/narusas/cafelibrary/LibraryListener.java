package net.narusas.cafelibrary;

public interface LibraryListener extends BookListListener {

	void bookListAdded(BookList value);

	void bookListRemoved(BookList value);

	void bookListChanged(BookList value);

	void borrowerAdded(Borrower value);

	void borrowerRemoved(Borrower value);

	void borrowerChanged(Borrower value);

	void bookChanged(Library library, Book value);

}
