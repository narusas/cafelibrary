package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import junit.framework.TestCase;

public class ControllerTest extends TestCase {
	Library lib;
	ApplicationModel model;
	Book book1;
	Book book2;
	Book book3;
	Book book4;
	BookList bList1;
	BookList bList2;
	Borrower borrower;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		model = new ApplicationModel();
		lib = new Library();
		book1 = new Book("Title 1");
		book2 = new Book("Title 2");
		book3 = new Book("Title 3");
		book4 = new Book("Title 4");

		bList1 = new BookList("BookList 1");
		bList2 = new BookList("BookList 2");

		borrower = new Borrower("Borrower 1", lib);
		borrower.add(book4);

		lib.add(book1);
		lib.add(book2);
		lib.add(book3);
		lib.add(book4);

		bList1.add(book1);
		bList1.add(book2);
		bList1.add(book3);
		lib.add(bList1);
		lib.add(bList2);

		lib.add(borrower);
		model.setLibrary(lib);

	}

	protected void setupModel() {
		model = new ApplicationModel();
		lib = new Library();
		book1 = new Book("Title 1");
		book2 = new Book("Title 2");
		book3 = new Book("Title 3");
		book4 = new Book("Title 4");

		bList1 = new BookList("BookList 1");
		bList2 = new BookList("BookList 2");

		borrower = new Borrower("Borrower 1", lib);
		borrower.add(book4);

		lib.add(book1);
		lib.add(book2);
		lib.add(book3);
		lib.add(book4);

		bList1.add(book1);
		bList1.add(book2);
		bList1.add(book3);
		lib.add(bList1);
		lib.add(bList2);

		lib.add(borrower);
		model.setLibrary(lib);
	}

}
