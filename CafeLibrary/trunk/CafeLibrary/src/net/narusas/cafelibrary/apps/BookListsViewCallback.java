package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;

public interface BookListsViewCallback {

	void bookListSelected(BookList list);

	void borrowerSelected(Borrower borrower);

	void bookListAddRequested();

	void borrowerAddRequested();

	void deleteBookList();

	void deleteBorrower();

	void setView(BookListsView view);

}
