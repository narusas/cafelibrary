package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.serial.FTPAccount;
import junit.framework.TestCase;

public class ApplicationModelTest extends TestCase {
	private BookListsViewControllerImpl listController;
	private BooksListViewControllerImpl listViewController;
	private BookDetailViewControllerImpl bookDetailController;
	private Library lib;
	private ApplicationModel model;
	private Book book1;
	private BookList bList1;
	private Borrower borrower;
	private Book book2;
	private BookList bList2;
	private Book book3;
	private Book book4;

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

		listController = new BookListsViewControllerImpl(model);
		listController.setView(new MockBookListsView());
		listViewController = new BooksListViewControllerImpl(model);
		listViewController .setView(new MockBooksListView());
		bookDetailController = new BookDetailViewControllerImpl(model);

		model.setup(listController, listViewController, bookDetailController);
	}

	public void testSelectBookList() {
		assertNull(model.getSelectedBookList());
		assertNull(model.getSelectedBook());

		listController.bookListSelected(bList1);

		assertEquals(bList1, model.getSelectedBookList());
		assertEquals(book1, model.getSelectedBook());

		assertEquals(bList1, listViewController.getSelectedBookList());
		assertEquals(book1, bookDetailController.getSelectedBook());
	}

	public void testSelectBookList_haveNoBooks() {
		listController.bookListSelected(bList1); // set first have books
		listController.bookListSelected(bList2); // set no books

		assertEquals(bList2, model.getSelectedBookList());
		assertNull(model.getSelectedBook());

		assertEquals(bList2, listViewController.getSelectedBookList());
		assertNull(bookDetailController.getSelectedBook());
	}

	public void testSelectBook() {
		listController.bookListSelected(bList1); // set first have books
		listViewController.bookSelected(book2);

		assertEquals(book2, model.getSelectedBook());
		assertEquals(book2, bookDetailController.getSelectedBook());
	}

	public void testDeleteBook() {
		assertEquals(3, bList1.getBookSize());
		listController.bookListSelected(bList1); // set first have books
		listViewController.bookSelected(book2);
		listViewController.deleteBookRequested();
		assertEquals(2, bList1.getBookSize());

		// 현재 보여지던 책이 지워지면, 그 아래권이 선택되어야 한다.
		assertEquals(book3, model.getSelectedBook());
		assertEquals(book3, bookDetailController.getSelectedBook());

		listViewController.deleteBookRequested();
		// 지워졌는데 아래권이 없다면 윗권을 선택해야 한다.
		assertEquals(book1, model.getSelectedBook());
		assertEquals(book1, bookDetailController.getSelectedBook());

		listViewController.deleteBookRequested();
		// 지워졌는데 남은 책이 없는 경우
		assertNull(model.getSelectedBook());
		assertNull(bookDetailController.getSelectedBook());
		assertEquals(0, bList1.getBookSize());
	}

	public void testReturnBook() {
		listController.bookListSelected(borrower);
		assertEquals(book4, bookDetailController.getSelectedBook());
		assertEquals(borrower, book4.getBorrower());
		listViewController.deleteBookRequested();
		assertNull(book4.getBorrower());
	}

	public void testDeleteBookList() {
		assertEquals(2, lib.sizeOfBookLists());
		listController.bookListSelected(bList1); // set first have books

		listController.deleteBookList();
		assertEquals(bList2, model.getSelectedBookList());
		assertEquals(bList2, listViewController.getSelectedBookList());
		assertNull(model.getSelectedBook());
		assertNull(bookDetailController.getSelectedBook());
	}

	public void testFilter() {
		listController.bookListSelected(bList1);

		listController.filter("2");

		assertEquals(1, model.getSelectedBookList().getBookSize());
		assertEquals(book2, model.getSelectedBook());

		assertEquals(book2, bookDetailController.getSelectedBook());

		listController.filter(null);

		assertEquals(3, model.getSelectedBookList().getBookSize());

		// 필터가 제거되어도 선택된 것은 그대로 선택 되어 있어야 한다.
		assertEquals(book2, model.getSelectedBook());
		assertEquals(book2, bookDetailController.getSelectedBook());
	}

	public void testDeleteBookWhenFiltering() {
		listController.bookListSelected(bList1);
		assertEquals(3, bList1.getBookSize());
		listController.filter("2");

		listViewController.deleteBookRequested();

		assertEquals(0, model.getSelectedBookList().getBookSize());
		assertEquals(2, bList1.getBookSize());

		assertNull(model.getSelectedBook());
		assertNull(bookDetailController.getSelectedBook());

		listController.filter(null);

		assertEquals(2, model.getSelectedBookList().getBookSize());
		// 아무것도 선택된게 없는 상태에서 필터가 제거되면 첫번째 책이 선택 되어야 한다.
		assertEquals(book1, model.getSelectedBook());
		assertEquals(book1, bookDetailController.getSelectedBook());
	}

	public void testAddBookWhenFiltering() {
		listController.bookListSelected(bList1);
		assertEquals(3, bList1.getBookSize());
		listController.filter("2");
		assertEquals(1, listViewController.getSelectedBookList().getBookSize());

		bList1.add(new Book("Title contains 2"));
		assertEquals(2, listViewController.getSelectedBookList().getBookSize());

		bList1.add(new Book("Title contains two"));
		assertEquals(2, listViewController.getSelectedBookList().getBookSize());
	}

	public void testSynchronization() {
		FTPAccount account = new FTPAccount("", "", "", "");
		listViewController.publishRequested(account);
	}

	public void testAddBook() {
		listController.bookListSelected(bList1);
		assertEquals(3, bList1.getBookSize());
		assertEquals(book1, model.getSelectedBook());
		assertEquals(book1, bookDetailController.getSelectedBook());

		Book newBook = new Book("new book");
		bookDetailController.add(newBook);

		assertEquals(4, bList1.getBookSize());

		// 추가된 책으로 바뀌어야 함.
		assertEquals(newBook, model.getSelectedBook());
		assertEquals(newBook, bookDetailController.getSelectedBook());
	}
}
