package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;

public interface BookListsViewController {

	void setSelectBookList(BookList list);

	void setSelectBorrower(Borrower borrower);

	void setView(BookListsView mockBookListsView);

}
