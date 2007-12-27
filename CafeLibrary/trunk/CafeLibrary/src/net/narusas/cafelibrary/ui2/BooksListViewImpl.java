package net.narusas.cafelibrary.ui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.apps.BooksListView;
import net.narusas.cafelibrary.apps.BooksListViewCallback;
import net.narusas.cafelibrary.apps.BooksListViewController;
import net.narusas.cafelibrary.serial.FTPAccount;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfModel;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfUI;
import net.narusas.cafelibrary.ui.bookshelf.SerialBookSelectionListener;

public class BooksListViewImpl implements BooksListView {
	private final BooksListSection ui;
	private BooksListViewCallback callback;
	private BooksListTable table;
	private BookShelfUI bookshelf;
	private BooksListViewController controller;
	private BookShelfModel bookshelfModel;

	public BooksListViewImpl(BooksListSection section) {
		this.ui = section;
		table = new BooksListTable();

		table.getBookListTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				int row = table.getBookListTable().getSelectedRow();
				callback.bookSelected(row);
			}
		});

		bookshelf = new BookShelfUI();
		bookshelf.setSelectionListener(new SerialBookSelectionListener() {

			public void serialBookSelected(Book book) {
				callback.bookSelected(book);
			}
		});
		ui.getChangeViewButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.changeViewRequested();
			}
		});

		ui.getAddBookButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.bookAddRequested();
			}
		});

		ui.getFilterTextField().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.filteringRequested(ui.getFilterTextField().getText());
			}
		});
		ui.getClearFilterButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.filteringRequested(null);
			}
		});

		ui.getDeleteButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.deleteBookRequested();
			}
		});

		ui.getOptionButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callback.showOptionsRequested();
			}
		});

	}

	public void setCallback(BooksListViewCallback callback) {
		this.callback = callback;
	}

	public FTPAccount askPublishInfo(FTPAccount last) {
		return null;
	}

	public void enableAddBookControl(boolean flag) {
		ui.getAddBookButton().setEnabled(flag);
	}

	public void enableDeleteControl(boolean flag) {
		ui.getDeleteButton().setEnabled(flag);
	}

	public void setDeleteControlAsDeleteImage() {
		ui.getDeleteButton().setIcon(new ImageIcon("images/delete.png"));
	}

	public void setDeleteControlAsReturnBookImage() {
		ui.getDeleteButton().setIcon(new ImageIcon("images/returnBook.png"));
	}

	public void setFilter(String text) {
		ui.getFilterTextField().setText(text);
	}

	public void setSelectedBook(Book book) {

	}

	public void setSelectedBookVisible() {

	}

	public void updateModels(BookList list) {
		table.getBookListTableModel().setBookList(list);
		bookshelfModel = new BookShelfModel(list);
		bookshelf.setModel(bookshelfModel);

		ui.getTitleLabel().setText(list == null ? null : list.getName());
	}

	public void switchToTableView() {
		ui.getChangeViewButton().setIcon(new ImageIcon("images/view_as_thumbnail.png"));
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(table);
		ui.revalidate();
		ui.repaint();
	}

	public void switchToBookShelfView() {
		ui.getChangeViewButton().setIcon(new ImageIcon("images/view_as_table.png"));
		ui.getScrollPane().getViewport().removeAll();
		ui.getScrollPane().getViewport().add(bookshelf);
		ui.revalidate();
		ui.repaint();
	}
}
