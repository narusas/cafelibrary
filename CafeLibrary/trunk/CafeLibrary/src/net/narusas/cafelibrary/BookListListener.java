package net.narusas.cafelibrary;

public interface BookListListener {

	void bookAdded(BookList bList, Book book);

	void bookRemoved(BookList bList, Book book);

	void nameChanged(BookList bList, String newName);

}
