package net.narusas.cafelibrary.ui2;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.apps.ApplicationModel;
import net.narusas.cafelibrary.apps.BookListsView;
import net.narusas.cafelibrary.apps.BookListsViewController;
import net.narusas.cafelibrary.apps.BookListsViewControllerImpl;
import net.narusas.cafelibrary.apps.MockBookDetailController;
import net.narusas.cafelibrary.apps.MockBooksListController;

public class BookListsTestor implements BookListsViewController {
	Library lib;
	ApplicationModel model;
	Book book1;
	Book book2;
	Book book3;
	Book book4;
	BookList bList1;
	BookList bList2;
	Borrower borrower;
	private BookListsViewImpl uiImpl;
	private BookListsViewControllerImpl blcIimpl;

	protected void setUp() throws Exception {
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

	public static void main(String[] args) {
		try {
			new BookListsTestor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BookListsTestor() throws Exception {
		setUp();
		JFrame frame = new JFrame();
		BookListsSection section = new BookListsSection();
		section.setLibrary(lib);
		uiImpl = new BookListsViewImpl(section);
		blcIimpl = new BookListsViewControllerImpl(model);
		blcIimpl.setView(uiImpl);
		model.setup(this, new MockBooksListController(), new MockBookDetailController());
		uiImpl.setCallback(blcIimpl);
		frame.getContentPane().add(section);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void setSelectBookList(BookList list) {
		System.out.println("setSelectBookList:" + list);
		blcIimpl.setSelectBookList(list);
	}

	public void setSelectBorrower(Borrower borrower) {
		System.out.println("setSelectBorrower:" + borrower);
		blcIimpl.setSelectBorrower(borrower);
	}

	public void setView(BookListsView view) {
		blcIimpl.setView(view);
	}
}
