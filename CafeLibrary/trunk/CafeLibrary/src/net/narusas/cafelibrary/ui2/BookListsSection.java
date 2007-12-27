package net.narusas.cafelibrary.ui2;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.apps.BookListsView;
import net.narusas.cafelibrary.apps.BookListsViewCallback;
import net.narusas.cafelibrary.apps.BookListsViewController;
import net.narusas.ui.component.GridentButton;
import net.narusas.ui.component.GridentSplitPane;
import net.narusas.ui.component.TitledPanel;

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
public class BookListsSection extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3822023990179271147L;
	private JSplitPane splitPane;
	private GridentButton addBooklistButton;
	private JScrollPane jScrollPane2;
	private JList borrowersList;
	private JList booklistsList;
	private JScrollPane jScrollPane1;
	private GridentButton deleteButton;
	private GridentButton addBorrowerButton;
	private TitledPanel borrowersPanel;
	private TitledPanel booklistsPanel;
	private LibraryListModel booklistsModel;
	private LibraryBorrowerListModel borrowersModel;
	private Library lib;

	public JList getBorrowersList() {
		return borrowersList;
	}

	public JList getBooklistsList() {
		return booklistsList;
	}

	public void setAddBorrowerButton(GridentButton addBorrowerButton) {
		this.addBorrowerButton = addBorrowerButton;
	}

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		UIManager.put("ScrollBar.width", new Integer(14));
		final Library lib = new Library();
		final BookList b1 = new BookList("Book List 1") {

			public int getBookSize() {
				return 9999;
			}
		};

		lib.add(b1);
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

		lib.add(new Borrower("BBB 1"));
		lib.add(new Borrower("BBB 2"));
		lib.add(new Borrower("BBB 3"));
		lib.add(new Borrower("BBB 4"));
		lib.add(new Borrower("BBB 5"));
		lib.add(new Borrower("BBB 6"));

		JFrame frame = new JFrame();
		BookListsSection section = new BookListsSection();
		section.setLibrary(lib);
		frame.getContentPane().add(section);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BookListsSection() {
		super();
		initGUI();
	}

	public void setLibrary(Library lib) {
		this.lib = lib;
		booklistsModel.setLibrary(lib);
		borrowersModel.setLibrary(lib);
	}

	private void initGUI() {
		booklistsModel = new LibraryListModel();
		borrowersModel = new LibraryBorrowerListModel();
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(264, 300));
			{
				splitPane = new JSplitPane();
				this.add(getSplitPane(), BorderLayout.CENTER);
				splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
				splitPane.setDividerLocation(100);
				splitPane.setBackground(new java.awt.Color(192, 192, 192));
				splitPane.setUI(new GridentSplitPane());
//				splitPane.setDividerSize(6);
				{
					booklistsPanel = new TitledPanel(false);
					splitPane.add(booklistsPanel, JSplitPane.TOP);
					{
						jScrollPane1 = new JScrollPane();
						booklistsPanel.getContentPanel().add(jScrollPane1, BorderLayout.CENTER);
						jScrollPane1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						{
							booklistsList = new JList();
							jScrollPane1.setViewportView(booklistsList);
							booklistsList.setCellRenderer(new BookCountCellRenderer());
							booklistsList.setModel(booklistsModel);
						}
					}
				}
				{
					borrowersPanel = new TitledPanel();
					borrowersPanel.getTitleLabel().setText("대여자");
					splitPane.add(borrowersPanel, JSplitPane.BOTTOM);
					borrowersPanel.setPreferredSize(new java.awt.Dimension(262, 99));
					{
						jScrollPane2 = new JScrollPane();
						borrowersPanel.getContentPanel().add(jScrollPane2, BorderLayout.CENTER);
						jScrollPane2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						{
							borrowersList = new JList();
							jScrollPane2.setViewportView(borrowersList);
							borrowersList.setCellRenderer(new BookCountCellRenderer());
							borrowersList.setModel(borrowersModel);
						}
					}
					{
						addBooklistButton = new GridentButton();
						borrowersPanel.getOptionPanel().add(getAddBooklistButton());
						addBooklistButton.setIcon(new ImageIcon("images/add_booklist.png"));
						addBooklistButton.setPreferredSize(new Dimension(32, 20));
					}
					{
						addBorrowerButton = new GridentButton();
						borrowersPanel.getOptionPanel().add(getAddBorrowerButton());
						addBorrowerButton.setIcon(new ImageIcon("images/add_borrower.png"));
						addBorrowerButton.setPreferredSize(new Dimension(32, 20));
					}
					{
						deleteButton = new GridentButton();
						borrowersPanel.getOptionPanel().add(getDeleteButton());
						deleteButton.setIcon(new ImageIcon("images/delete.png"));
						deleteButton.setMargin(new java.awt.Insets(1, 4, 1, 4));
						deleteButton.setPreferredSize(new Dimension(32, 20));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public GridentButton getAddBooklistButton() {
		return addBooklistButton;
	}

	public GridentButton getAddBorrowerButton() {
		return addBorrowerButton;
	}

	public GridentButton getDeleteButton() {
		return deleteButton;
	}

	public String askNewBookListName() {
		return JOptionPane.showInputDialog(this, "새로운 책 목록의 이름을 입력해 주세요");
	}

	public LibraryListModel getBooklistsModel() {
		return booklistsModel;
	}

	public LibraryBorrowerListModel getBorrowersModel() {
		return borrowersModel;
	}

}
