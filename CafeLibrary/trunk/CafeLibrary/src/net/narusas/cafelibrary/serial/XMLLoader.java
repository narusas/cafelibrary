package net.narusas.cafelibrary.serial;

import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryLoader;
import net.narusas.cafelibrary.LibraryStorage;

public class XMLLoader extends LibraryLoader {

	public XMLLoader(LibraryStorage storage) {
		super(storage);
	}

	@Override
	public Library load() {
		if (storage == null) {
			return null;
		}
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(storage.read());
			Library lib = new Library();
			if (doc == null) {
				return lib;
			}

			parseLibrary(doc, lib);

			return lib;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}

	private static void parseLibrary(Document doc, Library lib) {
		Node root = doc.getChildNodes().item(0);

		Node books = root.getChildNodes().item(0);
		Node booklists = root.getChildNodes().item(1);
		Node borrowers = root.getChildNodes().item(2);

		parseBooks(books, lib);
		parseBooklists(booklists, lib);
		parseBorrowers(borrowers, lib);

	}

	private static void parseBooks(Node books, Library lib) {
		for (int i = 0; i < books.getChildNodes().getLength(); i++) {
			Node bookNode = books.getChildNodes().item(i);
			parseBook(bookNode, lib);
		}
	}

	private static void parseBook(Node bookNode, Library lib) {
		String id = bookNode.getAttributes().getNamedItem("id").getNodeValue();
		String author = bookNode.getAttributes().getNamedItem("author").getNodeValue();
		String category = bookNode.getAttributes().getNamedItem("category").getNodeValue();
		String coverLargeUrl = bookNode.getAttributes().getNamedItem("coverLargeUrl").getNodeValue();
		String coverSmallUrl = bookNode.getAttributes().getNamedItem("coverSmallUrl").getNodeValue();
		String description = bookNode.getAttributes().getNamedItem("description").getNodeValue();
		String isbn = bookNode.getAttributes().getNamedItem("isbn").getNodeValue();
		String notes = bookNode.getAttributes().getNamedItem("notes").getNodeValue();
		String originalPrice = bookNode.getAttributes().getNamedItem("originalPrice").getNodeValue();
		String publisher = bookNode.getAttributes().getNamedItem("publisher").getNodeValue();
		String salePrice = bookNode.getAttributes().getNamedItem("salePrice").getNodeValue();
		String title = bookNode.getAttributes().getNamedItem("title").getNodeValue();
		String translator = bookNode.getAttributes().getNamedItem("translator").getNodeValue();
		String publishDate = bookNode.getAttributes().getNamedItem("publishDate").getNodeValue();
		String purchaseDate = bookNode.getAttributes().getNamedItem("purchaseDate").getNodeValue();
		String favorite = bookNode.getAttributes().getNamedItem("favorite").getNodeValue();

		Book book = new Book(Long.parseLong(id), title);
		book.setAuthor(author);
		book.setCategory(category);
		book.setCoverLargeUrl(coverLargeUrl);
		book.setCoverSmallUrl(coverSmallUrl);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setNotes(notes);
		book.setOriginalPrice(originalPrice);
		book.setPublisher(publisher);
		book.setSalePrice(salePrice);
		book.setTranslator(translator);
		book.setPublishDate(toDate(publishDate));
		book.setPurchaseDate(toDate(purchaseDate));
		book.setFavorite(Integer.parseInt(favorite));

		lib.add(book);
	}

	private static Date toDate(String publishDate) {
		if (publishDate == null || "".equals(publishDate)) {
			return null;
		}

		return new Date(Long.parseLong(publishDate));
	}

	private static void parseBooklists(Node booklists, Library lib) {
		for (int i = 0; i < booklists.getChildNodes().getLength(); i++) {
			Node bookListNode = booklists.getChildNodes().item(i);
			String id = bookListNode.getAttributes().getNamedItem("id").getNodeValue();
			String name = bookListNode.getAttributes().getNamedItem("name").getNodeValue();
			String bindBooks = bookListNode.getAttributes().getNamedItem("books").getNodeValue();
			BookList bList = new BookList(Long.parseLong(id), name);
			if (bindBooks != null && "".equals(bindBooks) == false) {
				String[] tokens = bindBooks.split(",");
				for (int k = 0; k < tokens.length; k++) {
					String token = tokens[k];
					bList.add(lib.findBookById(Long.parseLong(token)));
				}
			}
			lib.add(bList);
		}
	}

	private static void parseBorrowers(Node booklists, Library lib) {
		for (int i = 0; i < booklists.getChildNodes().getLength(); i++) {
			Node bookListNode = booklists.getChildNodes().item(i);
			String id = bookListNode.getAttributes().getNamedItem("id").getNodeValue();
			String name = bookListNode.getAttributes().getNamedItem("name").getNodeValue();
			String bindBooks = bookListNode.getAttributes().getNamedItem("books").getNodeValue();
			Borrower borrower = new Borrower(Long.parseLong(id), name, lib);
			if (bindBooks != null && "".equals(bindBooks) == false) {
				String[] tokens = bindBooks.split(",");
				for (int k = 0; k < tokens.length; k++) {
					String token = tokens[k];
					borrower.add(lib.findBookById(Long.parseLong(token)));
				}
			}
			lib.add(borrower);
		}
	}

}
