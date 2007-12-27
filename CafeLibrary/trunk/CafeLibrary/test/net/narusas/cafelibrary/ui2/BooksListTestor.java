package net.narusas.cafelibrary.ui2;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.apps.ApplicationModel;
import net.narusas.cafelibrary.apps.BookListsViewControllerImpl;
import net.narusas.cafelibrary.apps.BooksListViewControllerImpl;
import net.narusas.cafelibrary.apps.MockBookDetailController;

public class BooksListTestor {
	Library lib;
	ApplicationModel model;
	Book book1;
	Book book2;
	Book book3;
	Book book4;
	BookList bList1;
	BookList bList2;
	Borrower borrower;
	private BooksListViewImpl uiImpl;
	private BooksListViewControllerImpl blcIimpl;
	private BookListsViewImpl uiImpl2;
	private BookListsViewControllerImpl blcIimpl2;

	protected void setUp() throws Exception {
		model = new ApplicationModel();
		lib = new Library();
		book1 = new Book(124, "Title 1");
		book2 = new Book(125, "Title 2");
		book3 = new Book(126, "Title 3");
		book4 = new Book(127, "Title 4");

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
			new BooksListTestor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BooksListTestor() {
		try {
			setUp();
		} catch (Exception e) {
		}

		BooksListSection section = new BooksListSection();
		section.setLibrary(lib);
		uiImpl = new BooksListViewImpl(section);
		blcIimpl = new BooksListViewControllerImpl(model);
		blcIimpl.setView(uiImpl);
		uiImpl.setCallback(blcIimpl);

		blcIimpl.switchToTableView();

		BookListsSection section2 = new BookListsSection();
		section2.setLibrary(lib);
		uiImpl2 = new BookListsViewImpl(section2);
		blcIimpl2 = new BookListsViewControllerImpl(model);
		blcIimpl2.setView(uiImpl2);
		uiImpl2.setCallback(blcIimpl2);

		model.setup(blcIimpl2, blcIimpl, new MockBookDetailController());
		// model.setBookList(bList1);
		// uiImpl.setCallback(blcIimpl);

		JFrame frame = new JFrame();
		frame.getContentPane().add(section);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		JFrame frame2 = new JFrame();
		frame2.getContentPane().add(section2);
		frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame2.pack();
		frame2.setVisible(true);
	}
}
