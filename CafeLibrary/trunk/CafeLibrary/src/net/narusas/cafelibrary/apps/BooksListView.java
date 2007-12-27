package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.serial.FTPAccount;

public interface BooksListView {

	void updateModels(BookList list);

	void enableAddBookControl(boolean flag);

	void setDeleteControlAsDeleteImage();

	void setDeleteControlAsReturnBookImage();

	void setFilter(String object);

	void setSelectedBook(Book book);

	void setSelectedBookVisible();

	void enableDeleteControl(boolean flag);

	FTPAccount askPublishInfo(FTPAccount last);

	void setCallback(BooksListViewCallback callback);

	void switchToTableView();

	void switchToBookShelfView();
}
