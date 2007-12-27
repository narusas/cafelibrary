package net.narusas.cafelibrary;

public class Borrower extends BookList {

	public static final Borrower NullBorrower = new NullBorrower();

	static class NullBorrower extends Borrower {
		public NullBorrower() {
			super(0, "", null);
		}
	}

	private Library lib;

	public Borrower(long id, String name, Library lib) {
		super(id, name);
		this.lib = lib;
	}

	public Borrower(String name, Library lib) {
		super(IdGenerator.newId(), name);
		this.lib = lib;
	}

	public Borrower(String name) {
		super(IdGenerator.newId(), name);
	}

	public void setLibrary(Library lib) {
		this.lib = lib;
	}

	public void borrow(Book book) {
		add(book);
	}

	public void returnBook(Book book) {
		remove(book);
	}

	public void add(Book book) {
		if (book == null) {
			return;
		}
		super.add(book);
		lib.exclusive(this, book);

		book.setBorrower(this);
	}

	public void remove(Book book) {
		if (book == null) {
			return;
		}
		super.remove(book);

		book.setBorrower(null);
	}

}
