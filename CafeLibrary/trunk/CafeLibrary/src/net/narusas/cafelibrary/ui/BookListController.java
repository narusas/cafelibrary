package net.narusas.cafelibrary.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DropMode;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.ui.component.ListTransferHandler;

public class BookListController {

	private final Library lib;
	private final MainFrame f;
	private BookListSectionListener listener;
	private BookList selectedBookList;

	public BookListController(Library lib, MainFrame f) {
		this.lib = lib;
		this.f = f;
		bind();
	}

	public void setFirstList() {
		f.getLibraryBookListPanel().getBookListsList().setSelectedIndex(0);
	}

	private void bind() {
		f.getLibraryBookListPanel().getListModel().setLibrary(lib);
		f.getLibraryBorrowerPanel().getListModel().setLibrary(lib);

		f.getLibraryControlPanel().getAddBookListButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBookList();
			}
		});

		f.getLibraryControlPanel().getAddBorrowerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBorrower();
			}
		});

		f.getLibraryBookListPanel().getBookListsList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				bookListSelected(e);
			}
		});

		f.getLibraryBookListPanel().getBookListsList().addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				bookListSelected();

			}
		});

		f.getLibraryBorrowerPanel().getBookListsList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				borrowerSelected(e);
			}
		});

		f.getLibraryBookListPanel().getBookListsList().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					deleteBookList();
				}
			}
		});

		f.getLibraryBorrowerPanel().getBookListsList().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					deleteBorrower();
				}
			}
		});
		enableDnD();
	}

	private void enableDnD() {
		f.getLibraryBookListPanel().getBookListsList().setDropMode(DropMode.ON);
		f.getLibraryBookListPanel().getBookListsList().setDragEnabled(true);
		f.getLibraryBookListPanel().getBookListsList().setTransferHandler(new ListTransferHandler());

		f.getLibraryBorrowerPanel().getBookListsList().setDropMode(DropMode.ON);
		f.getLibraryBorrowerPanel().getBookListsList().setDragEnabled(true);
		f.getLibraryBorrowerPanel().getBookListsList().setTransferHandler(new ListTransferHandler());
	}

	protected void deleteBorrower() {
		int selectedRow = f.getLibraryBorrowerPanel().getBookListsList().getSelectedIndex();
		lib.removeBorrower(selectedRow);
	}

	protected void deleteBookList() {
		int selectedRow = f.getLibraryBookListPanel().getBookListsList().getSelectedIndex();
		lib.removeBookList(selectedRow - 1);
	}

	protected void borrowerSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}

		borrowerSelected();
	}

	private void borrowerSelected() {
		f.getBookTableControlPanel().getSearchTextField().setText("");
		int row = f.getLibraryBorrowerPanel().getBookListsList().getSelectedIndex();
		borrowerSelected(row);
	}

	private void borrowerSelected(int row) {
		f.getLibraryBookListPanel().getBookListsList().clearSelection();
		f.getLibraryBorrowerPanel().getBookListsList().setSelectedIndex(row);
		Borrower borrower = (Borrower) f.getLibraryBorrowerPanel().getBookListsList().getSelectedValue();

		System.out.println("Borrower selected:" + borrower);
		if (listener != null) {
			listener.bookListSelected(borrower);
		}
	}

	protected void bookListSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			return;
		}
		bookListSelected();

	}

	private void bookListSelected() {
		f.getBookTableControlPanel().getSearchTextField().setText("");
		int row = f.getLibraryBookListPanel().getBookListsList().getSelectedIndex();
		bookListSelected(row);
	}

	private void bookListSelected(int row) {
		f.getLibraryBorrowerPanel().getBookListsList().clearSelection();
		f.getLibraryBookListPanel().getBookListsList().setSelectedIndex(row);

		selectedBookList = (BookList) f.getLibraryBookListPanel().getBookListsList().getSelectedValue();
		System.out.println("BookList selected:" + selectedBookList);

		if (listener != null) {
			listener.bookListSelected(selectedBookList);
		}
	}

	protected void addBookList() {
		String name = JOptionPane.showInputDialog(f, "새로운 북리스트의 이름을 입력해 주세요.");
		if (name != null) {
			lib.add(new BookList(name));
		}
	}

	protected void addBorrower() {
		String name = JOptionPane.showInputDialog(f, "새로운 대여자의  이름을 입력해 주세요.");
		if (name != null) {
			lib.add(new Borrower(name, lib));
		}
	}

	public void setListener(BookListSectionListener listener) {
		this.listener = listener;
	}
}

interface BookListSectionListener {
	void bookListSelected(BookList bookList);
}
