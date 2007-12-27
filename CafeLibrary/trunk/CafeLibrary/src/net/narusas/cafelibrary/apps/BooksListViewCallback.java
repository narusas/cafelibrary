package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;

public interface BooksListViewCallback {

	
	void setBookList(BookList list1);

	void requestPublish();

	void setView(BooksListView view);

	void changeViewRequested();

	void bookAddRequested();

	void filteringRequested(String text);

	void deleteBookRequested();

	void showOptionsRequested();

	void switchToTableView();

	void bookSelected(int row);

	void bookSelected(Book book);

}
