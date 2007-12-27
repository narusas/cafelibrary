package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;

public interface BookDetailView {

	void clearInfos();

	void setBook(Book book);

	void showBookDetailView();

	void showSelectAddingMethodView();

	void showBookEditView();

	void showSearchingBookView();

	void setSearch(String searchTarget);

	void setSearchingBookList(BookList searchingList);
	
	void setCallback(BookDetailViewCallback controller);
}
