package net.narusas.cafelibrary;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
	public enum SORT_COL {
		COL_CORVER, COL_TITLE, COL_CREATOR, COL_FAVORITE;

		public static SORT_COL get(int modelIndex) {
			switch (modelIndex) {
			case 0:
				return COL_CORVER;
			case 1:
				return COL_TITLE;
			case 2:
				return COL_CREATOR;
			case 3:
				return COL_FAVORITE;
			}
			return null;
		}
	};

	private final SORT_COL modelIndex;
	private final boolean sortAsc;

	public BookComparator(SORT_COL modelIndex, boolean sortAsc) {
		this.modelIndex = modelIndex;
		this.sortAsc = sortAsc;
	}

	public int compare(Book b1, Book b2) {
		int res = 0;
		switch (modelIndex) {
		case COL_CORVER:
			res = b1.getId() < b2.getId() ? -1 : (b1.getId() > b2.getId() ? 1 : 0);
			break;
		case COL_TITLE:
			res = b1.getTitleWithNoSerial().compareTo(b2.getTitleWithNoSerial());
			if (res == 0) {
				res = b1.getSerialNo() < b2.getSerialNo() ? -1 : (b1.getSerialNo() > b2.getSerialNo() ? 1 : 0);
			}
			break;
		case COL_CREATOR:
			res = b1.getAuthor().compareTo(b2.getAuthor());
			break;
		case COL_FAVORITE:
			res = b1.getFavorite() < b2.getFavorite() ? -1 : (b1.getFavorite() > b2.getFavorite() ? 1 : 0);
			break;

		}
		if (sortAsc) {
			res = -res;
		}
		return res;

	}
}