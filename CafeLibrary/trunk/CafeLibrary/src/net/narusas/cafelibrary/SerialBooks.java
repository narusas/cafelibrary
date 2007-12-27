package net.narusas.cafelibrary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class SerialBooks {
	HashMap<String, BookList> serialBooks;

	public SerialBooks(BookList srcBooks) {
		serialBooks = new HashMap<String, BookList>();
		for (int i = 0; i < srcBooks.getBookSize(); i++) {
			addSerialBook(srcBooks.get(i));
		}
	}

	private void addSerialBook(Book book) {
		if (book.isSerial()) {
			if (serialBooks.containsKey(book.getTitleWithNoSerial())) {
				BookList bList = serialBooks.get(book.getTitleWithNoSerial());
				bList.add(book);
				sortSerialBook(bList);
				return;
			}

			BookList bList = new BookList(book.getTitleWithNoSerial());
			bList.add(book);
			serialBooks.put(book.getTitleWithNoSerial(), bList);
		} else {
			BookList bList = new BookList(book.getTitle());
			bList.add(book);
			serialBooks.put(book.getTitle(), bList);
		}
	}

	private void sortSerialBook(BookList list) {
		list.sortBySerialNo();
	}

	public List<BookList> getSerialBooks() {
		return sort(new ArrayList<BookList>(serialBooks.values()));
	}

	private List<BookList> sort(List<BookList> list) {
		Collections.sort(list, new Comparator<BookList>() {
			public int compare(BookList o1, BookList o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		return list;
	}
}
