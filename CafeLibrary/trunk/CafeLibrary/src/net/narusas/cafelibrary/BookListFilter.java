package net.narusas.cafelibrary;

public class BookListFilter extends BookList implements BookListListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4125810198855285813L;
	private final BookList bookList;
	private String filter;

	public BookListFilter(BookList bookList, String filter) {
		super(bookList.getId(), bookList.getName());
		this.bookList = bookList;
		this.bookList.addListener(this);
		filter(bookList, filter);
	}

	private void filter(BookList bookList, String filter) {
		this.filter = filter.toLowerCase();
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if (book.isMatch(filter)) {
				add(book);
			}
		}
	}

	public void remove(Book book) {
		super.remove(book);
		bookList.remove(book);
	}

	public Book remove(int row) {
		Book remove = super.remove(row);
		bookList.remove(remove);
		return remove;
	}

	public BookList getOriginal() {
		return this.bookList;
	}

	public void nameChanged(BookList list, String newName) {
		super.setName(newName);
		notifyNameChanged(this, newName);
	}

	public void bookAdded(BookList list, Book book) {
		if (book.isMatch(filter)) {
			add(book);
		}
	}

	public void bookRemoved(BookList list, Book book) {
		if (book.isMatch(filter)) {
			remove(book);
		}
	}

}
