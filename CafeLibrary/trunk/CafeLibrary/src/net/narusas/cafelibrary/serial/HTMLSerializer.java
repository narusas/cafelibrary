package net.narusas.cafelibrary.serial;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Vector;
import java.util.logging.Logger;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Config;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibrarySerializer;
import HTML.Template;

public class HTMLSerializer implements LibrarySerializer {
	static SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
	static Logger logger = Logger.getLogger("log");

	public byte[] serialize(Library lib) {
		try {
			Template t = new Template("web/index.tmpl");
			Vector<Hashtable<String, Object>> books = new Vector<Hashtable<String, Object>>();
			for (int i = 0; i < lib.getBookSize(); i++) {
				Book book = lib.get(i);
				Hashtable<String, Object> param = new Hashtable<String, Object>();
				put(param, "id", String.valueOf(book.getId()));
				put(param, "title", book.getTitle());
				put(param, "author", book.getAuthor());
				put(param, "favorite", String.valueOf(book.getFavorite()));
				put(param, "cover", book.getCoverLargeUrl());
				put(param, "publisher", book.getPublisher());
				put(param, "publishDate", book.getPublishDate() == null ? "" : formatter.format(book.getPublishDate()));
				put(param, "category", book.getCategory());
				put(param, "translator", book.getTranslator());
				put(param, "purchaseDate", book.getPurchaseDate() == null ? "" : formatter.format(book
						.getPurchaseDate()));
				put(param, "borrower", book.getBorrower() == null ? "" : book.getBorrower().getName());
				put(param, "description", book.getDescription().replace('"', '\''));
				put(param, "notes", book.getNotes().replace('"', '\''));
				put(param, "isbn", book.getIsbn());
				books.add(param);
			}
			Vector<Hashtable<String, Object>> booklists = new Vector<Hashtable<String, Object>>();
			addBookList(booklists, lib);
			for (int i = 0; i < lib.sizeOfBookLists(); i++) {
				BookList bList = lib.getBookList(i);
				addBookList(booklists, bList);
			}
			Vector<Hashtable<String, Object>> borrowers = new Vector<Hashtable<String, Object>>();
			for (int i = 0; i < lib.sizeOfBorrowers(); i++) {
				BookList bList = lib.getBorrower(i);
				addBookList(borrowers, bList);
			}
			t.setParam("books", books);
			t.setParam("booklists", booklists);
			t.setParam("borrowers", borrowers);

			return t.output().getBytes(Config.CHARSET);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void addBookList(Vector<Hashtable<String, Object>> booklists, BookList bList) {
		Hashtable<String, Object> param = new Hashtable<String, Object>();
		put(param, "id", String.valueOf(bList.getId()));
		put(param, "name", bList.getName());
		Vector<Hashtable<String, Object>> ownbooks = new Vector<Hashtable<String, Object>>();
		for (int k = 0; k < bList.getBookSize(); k++) {
			Book ownbook = bList.get(k);
			Hashtable<String, Object> ownBookParam = new Hashtable<String, Object>();
			put(ownBookParam, "bookid", String.valueOf(ownbook.getId()));
			ownbooks.add(ownBookParam);
		}
		put(param, "ownbooks", ownbooks);
		booklists.add(param);
	}

	private static void put(Hashtable<String, Object> param, String key, Object value) {
		param.put(key, value == null ? "" : value);
	}
}
