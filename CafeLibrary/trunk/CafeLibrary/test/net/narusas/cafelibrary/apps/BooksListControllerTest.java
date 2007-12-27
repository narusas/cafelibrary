package net.narusas.cafelibrary.apps;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.serial.FTPAccount;

public class BooksListControllerTest extends ControllerTest {
	private BooksListViewCallback c;
	private MockBooksListView view;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		setupModel();

		c = new BooksListViewControllerImpl(model);

		model.setup(new MockBookListsController(), (BooksListViewController) c, new MockBookDetailController());

		view = new MockBooksListView();
		c.setView(view);
	}

	public void testSetBookList() {
		c.setBookList(bList1);
		assertEquals("updateModels(BookList 1),"// 
				+ "enableAddBookControl(true)," //
				+ "enableDeleteControl(false)," //
				+ "setDeleteControlAsDeleteImage,"//
				+ "setFilter(null),"// 
				+ "setSelectedBook(Title 1),"//
				+ "enableDeleteControl(true)," //
				+ "setSelectedBookVisible,", view.log);
	}

	public void testSetBorrower() {
		c.setBookList(borrower);
		assertEquals("updateModels(Borrower 1),"// 
				+ "enableAddBookControl(true)," //
				+ "enableDeleteControl(false)," //
				+ "setDeleteControlAsReturnBookImage,"//
				+ "setFilter(null),"// 
				+ "setSelectedBook(Title 4),"//
				+ "enableDeleteControl(true)," //
				+ "setSelectedBookVisible,", view.log);
	}

	public void testSetEmptyBookList() {
		c.setBookList(new BookList("dummy"));
		assertEquals("updateModels(dummy),"// 
				+ "enableAddBookControl(true)," //
				+ "enableDeleteControl(false)," //
				+ "setDeleteControlAsDeleteImage,"//
				+ "setFilter(null),"// 
				+ "setSelectedBook(null),"//
				+ "enableDeleteControl(false)," //
				+ "setSelectedBookVisible,", view.log);
	}

	public void testPublishd() {
		new FTPAccount("1", "2", "3", "4"); // store last
		c.requestPublish();
		assertEquals("askPublishInfo(last=FTPAccount{1,2,3,4},res=FTPAccount{a,b,c,d}),", view.log);
	}
}

class MockBooksListView implements BooksListView {
	String log = "";

	void clearLog() {
		log = "";
	}

	public void clearFilter() {
		log += "clearFilter,";
	}

	public void updateControls() {
		log += "updateControls,";
	}

	public void updateModels(BookList list) {
		log += "updateModels(" + (list == null ? null : list.getName()) + "),";

	}

	public void setSelectedBook(Book book) {
		log += "setSelectedBook(" + (book == null ? null : book.getName()) + "),";

	}

	public void setSelectedBookVisible() {
		log += "setSelectedBookVisible,";
	}

	public void enableAddBookControl(boolean flag) {
		log += "enableAddBookControl(" + flag + "),";
	}

	public void setDeleteControlAsDeleteImage() {
		log += "setDeleteControlAsDeleteImage,";
	}

	public void setDeleteControlAsReturnBookImage() {
		log += "setDeleteControlAsReturnBookImage,";
	}

	public void setFilter(String text) {
		log += "setFilter(" + text + "),";
	}

	public void enableDeleteControl(boolean flag) {
		log += "enableDeleteControl(" + flag + "),";
	}

	public FTPAccount askPublishInfo(FTPAccount last) {
		FTPAccount account = new FTPAccount("a", "b", "c", "d");
		log += "askPublishInfo(last=" + last + ",res=" + account + "),";
		return account;
	}

	public void setController(BooksListViewController controller) {

	}

	public void switchToTableView() {
		
	}

	public void switchToBookShelfView() {
		
	}

	public void setCallback(BooksListViewCallback callback) {
	}
}