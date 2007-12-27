package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;

public interface BookListsView {

	void unhighlightBorrowers();

	void selectBookList(BookList list1);

	void setBookListVisible();

	String askNewBookListName();

	void unhighlightBookLists();

	void selectBorrower(Borrower borrower);

	void setBorrowerVisible();
	
	void setCallback(BookListsViewCallback controller);
}
