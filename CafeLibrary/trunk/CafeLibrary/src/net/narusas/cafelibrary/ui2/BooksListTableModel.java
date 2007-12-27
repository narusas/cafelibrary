package net.narusas.cafelibrary.ui2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookComparator;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.BookListFilter;
import net.narusas.cafelibrary.BookListListener;
import net.narusas.cafelibrary.apps.IconHolder;

public class BooksListTableModel extends AbstractTableModel implements BookListListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4444816653205933009L;

	private static final int COL_CORVER = 0;

	private static final int COL_TITLE = 1;

	private static final int COL_CREATOR = 2;

	private static final int COL_FAVORITE = 3;

	BookList bookList;

	int sortCol = 1;
	boolean sortAsc = false;

	private BookList bookListSrc;

	static ColumnData[] COLS = new ColumnData[] {
	//
			new ColumnData("", 24, JLabel.CENTER, true, 1),// 
			new ColumnData("제목", 150, JLabel.LEFT, false, 0),//
			new ColumnData("만든이", 80, JLabel.LEFT, false, 0), //
			new ColumnData("선호도", 68, JLabel.LEFT, true, 0),

	};

	public int getColumnCount() {
		return COLS.length;
	}

	public int getRowCount() {
		if (bookList == null) {
			return 0;
		}
		return bookList.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = bookList.get(rowIndex);
		switch (columnIndex) {
		case COL_CORVER:
			return IconHolder.getInstance().getIconFor(book);
		case COL_TITLE:
			return book.getTitle();
		case COL_CREATOR:
			return book.getAuthor();
		case COL_FAVORITE:
			return book.getFavorite();
		}
		return null;
	}

	public BookList getBookList() {
		return bookList;
	}

	public void setBookList(BookList bookList) {
		if (this.bookList != null) {
			this.bookList.addListener(null);
		}
		if (bookList == null) {
			return;
		}
		this.bookList = bookList;
		this.bookListSrc = bookList;

		bookList.addListener(this);
		sort();
		fireTableDataChanged();
	}

	private void sort() {
		Collections.sort(bookList.getCollection(), new BookComparator(BookComparator.SORT_COL.COL_TITLE, sortAsc));
	}

	public String getColumnName(int column) {
		if (column == sortCol) {
			return COLS[column].title + (sortAsc ? ">>" : "<<");
		}
		return COLS[column].title;
	}

	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex >= 3;
	}

	
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Book book = bookList.get(rowIndex);
		switch (columnIndex) {
		case COL_FAVORITE:
			book.setFavorite(((Integer) value).intValue());
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		case COL_TITLE:
			book.setTitle((String) value);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		case COL_CREATOR:
			book.setAuthor((String) value);
			fireTableCellUpdated(rowIndex, columnIndex);
			break;
		}
	}

	public ColumnListener getMouseAdapter(JTable table) {
		return new ColumnListener(table);
	}

	class ColumnListener extends MouseAdapter {
		JTable table;

		public ColumnListener(JTable table) {
			this.table = table;
		}

		
		public void mouseClicked(MouseEvent e) {
			TableColumnModel colModel = table.getColumnModel();
			int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
			int modelIndex = colModel.getColumn(columnModelIndex).getModelIndex();

			if (modelIndex < 0) {
				return;
			}
			if (sortCol == modelIndex) {
				sortAsc = !sortAsc;
			} else {
				sortCol = modelIndex;
			}

			for (int i = 0; i < COLS.length; i++) {
				TableColumn column = colModel.getColumn(i);
				column.setHeaderValue(getColumnName(column.getModelIndex()));
			}
			table.getTableHeader().repaint();
			Collections.sort(bookList.getCollection(), new BookComparator(BookComparator.SORT_COL.get(modelIndex),
					sortAsc));
			table.tableChanged(new TableModelEvent(table.getModel()));
			table.repaint();
		}
	}

	
	public void bookAdded(BookList list, Book book) {
		sort();
		fireTableDataChanged();
	}

	
	public void bookRemoved(BookList list, Book book) {
		sort();
		fireTableDataChanged();

	}

	
	public void nameChanged(BookList list, String newName) {
		sort();
		fireTableDataChanged();

	}

	public Book get(int row) {
		if (row < 0 || row > bookList.size()) {
			return null;
		}
		return bookList.get(row);
	}

	public void remove(int selectedRow) {
		if (selectedRow == -1) {
			return;
		}
		bookList.remove(selectedRow);

	}

	public void setFilter(String text) {
		if (text == null || "".equals(text)) {
			bookList = bookListSrc;
			fireTableDataChanged();
			return;
		}

		bookList = new BookListFilter(bookListSrc, text);
		fireTableDataChanged();
	}
}
