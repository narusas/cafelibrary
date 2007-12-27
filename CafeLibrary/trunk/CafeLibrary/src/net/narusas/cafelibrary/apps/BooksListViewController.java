package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.BookList;

public interface BooksListViewController {

	void setBookList(BookList list);

	public void switchToTableView(); 

}
