package net.narusas.cafelibrary.ui2;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryLoader;
import net.narusas.cafelibrary.apps.ApplicationModel;
import net.narusas.cafelibrary.apps.BookDetailViewControllerImpl;
import net.narusas.cafelibrary.apps.BookListsViewControllerImpl;
import net.narusas.cafelibrary.apps.BooksListViewControllerImpl;
import net.narusas.cafelibrary.bookfactories.DaumPlusNaverBookFactory;
import net.narusas.cafelibrary.serial.FileLibraryStorage;
import net.narusas.cafelibrary.serial.XMLLoader;

public class Main {
	private MainFrame mainFrame;
	private ApplicationModel model;
	private FileLibraryStorage storage;
	private Library lib;
	private BookListsSection bookListsSection;
	private BooksListSection booksListSection;
	private BookDetailSection bookDetailSection;
	private BookListsViewControllerImpl blsController;
	private BooksListViewControllerImpl bslController;
	private BookDetailViewControllerImpl blvController;
	private BookListsViewImpl blsvimpl;
	private BooksListViewImpl bslimpl;
	private BookDetailViewImpl bdvimpl;

	public static void main(String[] args) {
		new Main();
	}

	public Main() {
		UIManager.put("ScrollBar.width", new Integer(14));

		model = new ApplicationModel();
		model.setBookFactory(new DaumPlusNaverBookFactory());

		storage = new FileLibraryStorage(new File("library.xml"));
		LibraryLoader.setPlugin(new XMLLoader(storage));
		lib = LibraryLoader.getInstance().load();
		model.setLibrary(lib);

		MainFrame frame = new MainFrame();

		bookListsSection = frame.getBookListsSection();
		booksListSection = frame.getBooksListSection1();
		bookDetailSection = frame.getBookDetailSection();
		bookListsSection.setLibrary(lib);
		booksListSection.setLibrary(lib);

		blsController = new BookListsViewControllerImpl(model);
		bslController = new BooksListViewControllerImpl(model);
		blvController = new BookDetailViewControllerImpl(model);

		blsvimpl = new BookListsViewImpl(bookListsSection);
		bslimpl = new BooksListViewImpl(booksListSection);
		bdvimpl = new BookDetailViewImpl(bookDetailSection);

		blsController.setView(blsvimpl);
		bslController.setView(bslimpl);
		blvController.setView(bdvimpl);

		bslController.switchToTableView();

		blsvimpl.setCallback(blsController);
		bslimpl.setCallback(bslController);
		bdvimpl.setCallback(blvController);

		model.setup(blsController, bslController, blvController);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
	}
}
