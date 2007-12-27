package net.narusas.cafelibrary.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.ui2.BooksListTable;
import net.narusas.cafelibrary.ui2.BorrowerBookCountListCellRenderer;
import net.narusas.cafelibrary.ui2.LibraryBorrowerListModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class MainFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1493131103113021424L;
	private JPanel westPanel;
	private JPanel centerPanel;
	private JPanel eastPanel;
	private LibraryList libraryBookListPanel;
	private LibraryList libraryBorrowerPanel;
	private BooksListTable bookListTable;
	private LibraryControlPanel libraryControlPanel;
	private BookListTableControlPanel tableControlPanel;
	private JPanel westContentPane;
	private BookDetailPanel bookDetailPanel;
	private BookControlPanel bookControlPanel;
	private BookAddMethodSelectionPanel selectAddingMethodPanel;
	private BookEditPanel addBookByWritePanel;
	private Library lib;
	private AddBookBySearchPanel addBookBySearchPanel;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				final Library lib = new Library();
				final BookList b1 = new BookList("Book List 1") {
					
					public int getBookSize() {
						return 9999;
					}
				};

				lib.add(b1);
				lib.add(new Book(315, "ABC"));
				lib.add(new BookList("Book List 2232323232323323232323232323232"));
				lib.add(new BookList("Book List 3"));
				lib.add(new BookList("Book List 4"));
				lib.add(new BookList("Book List 5"));
				lib.add(new BookList("Book List 6"));
				lib.add(new BookList("Book List 7"));
				lib.add(new BookList("Book List 8"));
				lib.add(new BookList("Book List 9"));
				lib.add(new BookList("Book List 10"));
				lib.add(new BookList("Book List 11"));
				lib.add(new BookList("Book List 12"));
				lib.add(new BookList("Book List 13"));

				lib.add(new Borrower("abc1", lib));
				lib.add(new Borrower("abc2", lib));
				lib.add(new Borrower("abc3", lib));

				MainFrame inst = new MainFrame();
				inst.setLibrary(lib);
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	protected void setLibrary(Library lib) {
		this.lib = lib;
		libraryBookListPanel.getListModel().setLibrary(lib);

		libraryBorrowerPanel.setListModel(new LibraryBorrowerListModel());
		libraryBorrowerPanel.getListModel().setLibrary(lib);
		libraryBorrowerPanel.getBookListsList().setCellRenderer(new BorrowerBookCountListCellRenderer()); 
		bookListTable.getBookListTableModel().setBookList(lib);
	}

	public MainFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setTitle("Cafe Library ver 1.0 - contributed to Cafe Animate");
			BorderLayout thisLayout = new BorderLayout();
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(thisLayout);
//			getContentPane().setBackground(new java.awt.Color(255, 255, 255));
			{
				westPanel = new JPanel();
				westPanel.setLayout(new BorderLayout());

				westContentPane = new JPanel();
				westContentPane.setLayout(new BoxLayout(westContentPane, BoxLayout.Y_AXIS));

				getContentPane().add(westPanel, BorderLayout.WEST);
//				westPanel.setBackground(new java.awt.Color(255, 255, 255));
				{
					libraryBookListPanel = new LibraryList();
					libraryBookListPanel.setTitle("북 리스트");
					westContentPane.add(libraryBookListPanel);

					libraryBorrowerPanel = new LibraryList();
					libraryBorrowerPanel.setTitle("대여자");
					westContentPane.add(libraryBorrowerPanel);

				}
				westPanel.add(westContentPane, BorderLayout.CENTER);

				libraryControlPanel = new LibraryControlPanel();
				westPanel.add(libraryControlPanel, BorderLayout.SOUTH);

			}
			{
				centerPanel = new JPanel();
				centerPanel.setLayout(new BorderLayout());
				getContentPane().add(centerPanel, BorderLayout.CENTER);
				centerPanel.setPreferredSize(new java.awt.Dimension(367, 315));
//				centerPanel.setBackground(new java.awt.Color(255, 255, 255));
				{
					bookListTable = new BooksListTable();
					centerPanel.add(bookListTable, BorderLayout.CENTER);

					tableControlPanel = new BookListTableControlPanel();
					centerPanel.add(tableControlPanel, BorderLayout.SOUTH);

				}
				eastPanel = new JPanel();
				eastPanel.setLayout(new BorderLayout());
				getContentPane().add(eastPanel, BorderLayout.EAST);
//				eastPanel.setBackground(new java.awt.Color(255, 255, 255));
				{
					bookDetailPanel = new BookDetailPanel();
					eastPanel.add(bookDetailPanel, BorderLayout.CENTER);

					bookControlPanel = new BookControlPanel();
					eastPanel.add(bookControlPanel, BorderLayout.SOUTH);
				}
			}
			pack();
			this.setSize(1000, 700);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LibraryList getLibraryBookListPanel() {
		return libraryBookListPanel;
	}

	public LibraryList getLibraryBorrowerPanel() {
		return libraryBorrowerPanel;
	}

	public BooksListTable getBookListTable() {
		return bookListTable;
	}

	public LibraryControlPanel getLibraryControlPanel() {
		return libraryControlPanel;
	}

	public BookAddMethodSelectionPanel getSelectAddingMethodPanel() {
		return selectAddingMethodPanel;
	}

	public BookListTableControlPanel getBookTableControlPanel() {
		return tableControlPanel;
	}

	public BookDetailPanel getBookDetailPanel() {
		return bookDetailPanel;
	}

	public BookControlPanel getBookControlPanel() {
		return bookControlPanel;
	}

	public void setTableControlPanel(BookListTableControlPanel tableControlPanel) {
		this.tableControlPanel = tableControlPanel;
	}

	public void switchToSelectAddingMethod() {
		if (selectAddingMethodPanel != null) {
			return;
		}
		clearEastPanel();
		bookDetailPanel = null;
		selectAddingMethodPanel = new BookAddMethodSelectionPanel();

		eastPanel.add(selectAddingMethodPanel, BorderLayout.CENTER);
		eastPanel.revalidate();
	}

	public void switchToBookDetail() {
		if (bookDetailPanel != null) {
			return;
		}
		clearEastPanel();
		selectAddingMethodPanel = null;

		bookDetailPanel = new BookDetailPanel();
		eastPanel.add(bookDetailPanel, BorderLayout.CENTER);
		eastPanel.revalidate();
	}

	public void switchToAddBookByWrite() {
		if (addBookByWritePanel != null) {
			return;
		}

		clearEastPanel();
		addBookByWritePanel = new BookEditPanel();
		eastPanel.add(addBookByWritePanel, BorderLayout.CENTER);
		eastPanel.revalidate();
	}

	private void clearEastPanel() {
		if (selectAddingMethodPanel != null) {
			eastPanel.remove(selectAddingMethodPanel);
			selectAddingMethodPanel = null;
		}

		if (bookDetailPanel != null) {
			eastPanel.remove(bookDetailPanel);
			bookDetailPanel = null;
		}
		if (addBookByWritePanel != null) {
			eastPanel.remove(addBookByWritePanel);
			addBookByWritePanel = null;
		}
		if (addBookBySearchPanel != null) {
			eastPanel.remove(addBookBySearchPanel);
			addBookBySearchPanel = null;
		}
	}

	public BookEditPanel getAddBookByWritePanel() {
		return addBookByWritePanel;
	}

	public void switchToAddBookBySearch(String searchText) {
		if (addBookBySearchPanel != null) {
			return;
		}
		clearEastPanel();
		addBookBySearchPanel = new AddBookBySearchPanel();
		addBookBySearchPanel.getSearchTextField().setText(searchText);
		eastPanel.add(addBookBySearchPanel, BorderLayout.CENTER);
		eastPanel.revalidate();
	}

	public AddBookBySearchPanel getAddBookBySearchPanel() {
		return addBookBySearchPanel;
	}

	public void switchToEditBook(Book book) {
		if (addBookByWritePanel != null) {
			addBookByWritePanel.setBook(book);
			return;
		}
		clearEastPanel();
		addBookByWritePanel = new BookEditPanel();
		addBookByWritePanel.setBook(book);
		eastPanel.add(addBookByWritePanel, BorderLayout.CENTER);
		eastPanel.revalidate();

	}
}
