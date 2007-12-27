package net.narusas.cafelibrary.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfModel;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfUI;
import net.narusas.cafelibrary.ui.bookshelf.SerialBookSelectionListener;

public class BookTableController {

	private final Library lib;
	private final MainFrame f;
	private BookTableSectionListener listener;
	private BookList list;
	private BookShelfUI bookShelfUI;

	private boolean isTableView = true;

	public BookTableController(Library lib, MainFrame f) {
		this.lib = lib;
		this.f = f;
		bind();
	}

	private void bind() {
		f.getBookTableControlPanel().getAddBookButton().addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});

		f.getBookListTable().getBookListTable().addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_DELETE) {
					deleteBook();
				}
			}
		});
		f.getBookListTable().getBookListTable().getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					
					public void valueChanged(ListSelectionEvent e) {
						bookSelected(e);
					}
				});

		JTable table = f.getBookListTable().getBookListTable();
		JTableHeader headers = table.getTableHeader();
		headers.addMouseListener(f.getBookListTable().getBookListTableModel().getMouseAdapter(table));

		f.getBookTableControlPanel().getSearchTextField().addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				filter(f.getBookTableControlPanel().getSearchTextField().getText());
			}
		});

		f.getBookTableControlPanel().getSearchTextField().addFocusListener(new FocusAdapter() {
			
			public void focusGained(FocusEvent e) {
				selectSearchField();
			}

		});

		f.getBookTableControlPanel().getReturnBookButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				returnBook();
			}
		});

		f.getBookTableControlPanel().getClearButton().addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				String filterText = f.getBookTableControlPanel().getSearchTextField().getText();
				if (filterText == null || "".equals(filterText)) {
					return;
				}
				f.getBookTableControlPanel().getSearchTextField().setText("");
				filter(null);

			}
		});

		f.getBookTableControlPanel().getPublishButton().addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				ftpPublish();
			}
		});

		f.getBookTableControlPanel().getChangeShowMethodButton().addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				changeShowMethod();
			}
		});

		f.getBookTableControlPanel().getDeteteBookButton().addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				deleteBook();
			}
		});
		changeShowMethod();
		enableDnD();
	}

	protected void changeShowMethod() {
		if (isTableView) {
			f.getBookTableControlPanel().getChangeShowMethodButton().setIcon(new ImageIcon("images/view_as_table.png"));
			f.getBookTableControlPanel().getChangeShowMethodButton().repaint();
			f.getBookListTable().removeAll();
			if (bookShelfUI == null) {
				bookShelfUI = new BookShelfUI();
				bookShelfUI.addKeyListener(new KeyAdapter() {

					
					public void keyTyped(KeyEvent e) {
						System.out.println("#:" + e);
						if (e.getKeyChar() == KeyEvent.VK_DELETE) {
							deleteBook();
						}
					}

				});
			}

			JScrollPane sp = new JScrollPane();
			sp.getViewport().add(bookShelfUI);
			bookShelfUI.setSelectionListener(new SerialBookSelectionListener() {
				
				public void serialBookSelected(Book book) {
					notifyBookSelected(book);
				}
			});

			sp.addKeyListener(new KeyAdapter() {

				
				public void keyTyped(KeyEvent e) {
					System.out.println("#2:" + e);
					if (e.getKeyChar() == KeyEvent.VK_DELETE) {
						deleteBook();
					}
				}

			});

			f.getBookListTable().add(sp);
			BookList bList = list;

			updateBookShelf(bList);
		} else {
			f.getBookTableControlPanel().getChangeShowMethodButton().setIcon(
					new ImageIcon("images/view_as_thumbnail.png"));
			f.getBookTableControlPanel().getChangeShowMethodButton().repaint();
			f.getBookListTable().removeAll();
			JScrollPane sp = new JScrollPane();
			sp.setViewportView(f.getBookListTable().getBookListTable());
			f.getBookListTable().add(sp, BorderLayout.CENTER);
			f.getBookListTable().revalidate();
			bookListSelected(list);
		}
		isTableView = !isTableView;
	}

	private void updateBookShelf(BookList bList) {
		if (bookShelfUI == null) {
			return;
		}
		bookShelfUI.setModel(new BookShelfModel(bList));
		f.getBookListTable().revalidate();
	}

	protected void ftpPublish() {
		FTPPublishController controller = new FTPPublishController(lib, f);
		controller.show();
	}

	private void selectSearchField() {
		f.getBookTableControlPanel().getSearchTextField().select(0,
				f.getBookTableControlPanel().getSearchTextField().getText().length());
	}

	protected void addBook() {
		if (listener != null) {
			listener.addBook();
		}
	}

	private void enableDnD() {
		f.getBookListTable().getBookListTable().setDragEnabled(true);
		f.getBookListTable().getBookListTable().setTransferHandler(new TableTransferHandler());
	}

	protected void returnBook() {
		BookList booklist = f.getBookListTable().getBookListTableModel().getBookList();
		if ((booklist instanceof Borrower) == false) {
			return;
		}
		returnBook(f.getBookListTable().getBookListTableModel().get(
				f.getBookListTable().getBookListTable().getSelectedRow()));
	}

	private void returnBook(Book book) {
		Borrower borrower = (Borrower) f.getBookListTable().getBookListTableModel().getBookList();
		borrower.returnBook(book);
	}

	protected void filter(String text) {
		f.getBookListTable().getBookListTableModel().setFilter(text);
	}

	protected void deleteBook() {
		if (isTableView) {
			int row = f.getBookListTable().getBookListTable().getSelectedRow();
			f.getBookListTable().getBookListTableModel().remove(row);
			bookSelected(row);
		} else {
			bookShelfUI.deleteSelectedBook();
		}

	}

	protected void bookSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || UIStatus.isBGUpdate()) {
			return;
		}
		int row = f.getBookListTable().getBookListTable().getSelectedRow();
		bookSelected(row);
	}

	private void bookSelected(int row) {
		if (row >= f.getBookListTable().getBookListTable().getRowCount()) {
			row = row - 1;
		}
		f.getBookListTable().getBookListTable().getSelectionModel().setSelectionInterval(row, row);
		Book book = f.getBookListTable().getBookListTableModel().get(row);
		notifyBookSelected(book);
	}

	private void notifyBookSelected(Book book) {
		if (listener != null) {
			listener.bookSelected(book);
		}
	}

	void bookListSelected(BookList bList) {
		list = bList;

		f.getBookListTable().setBorder(
				BorderFactory.createTitledBorder(null, bList == null ? "Unknwon" : bList.getName(),
						TitledBorder.LEADING, TitledBorder.TOP));
		f.getBookListTable().getBookListTableModel().setBookList(bList);
		f.getBookListTable().getBookListTable().getSelectionModel().setSelectionInterval(0, 0);
		updateBookShelf(list);
		f.getBookTableControlPanel().getReturnBookButton().setEnabled(bList instanceof Borrower);

	}

	public void setListener(BookTableSectionListener listener) {
		this.listener = listener;
	}

}

interface BookTableSectionListener {
	void bookSelected(Book book);

	void addBook();
}
