package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;

public interface BookDetailViewController {

	void setView(BookDetailView view);

	void setBook(Book book);

	Book getSettedBook();

	void bookAddRequested();

	void bookAddByHandRequested();

	void bookAddBySearchRequested(String string);

	void addSearchedBook(Book book);

	void switchToSelectAddingMethodView();

}
