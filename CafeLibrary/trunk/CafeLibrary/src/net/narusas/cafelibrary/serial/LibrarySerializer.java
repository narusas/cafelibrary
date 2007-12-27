package net.narusas.cafelibrary.serial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class LibrarySerializer implements LibraryListener {
	private final Library lib;

	public LibrarySerializer(Library lib) {
		this.lib = lib;
	}

	public void store(Library lib) throws IOException {
		File f = new File("library.xml");
		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		makeLibrary(doc, lib);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(f)));
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		} catch (TransformerException ex) {
			ex.printStackTrace();
		}
	}

	private void makeLibrary(Document doc, Library lib) {
		Element root = doc.createElement("library");
		doc.appendChild(root);

		Element books = doc.createElement("books");
		root.appendChild(books);
		makeBooks(books, doc, lib);

		Element booklists = doc.createElement("booklists");
		root.appendChild(booklists);

		makeBookLists(booklists, doc, lib);

		Element borrowers = doc.createElement("borrowers");
		root.appendChild(borrowers);
		makeBorrowers(borrowers, doc, lib);
	}

	private void makeBooks(Element books, Document doc, Library lib) {
		for (int i = 0; i < lib.getBookSize(); i++) {
			Book book = lib.get(i);
			makeBook(books, doc, book);
		}
	}

	private void makeBook(Element books, Document doc, Book book) {
		Element bookNode = doc.createElement("book");

		bookNode.setAttribute("id", String.valueOf(book.getId()));
		bookNode.setAttribute("author", book.getAuthor());
		bookNode.setAttribute("category", book.getCategory());
		bookNode.setAttribute("coverLargeUrl", book.getCoverLargeUrl());
		bookNode.setAttribute("coverSmallUrl", book.getCoverSmallUrl());
		bookNode.setAttribute("description", book.getDescription());
		bookNode.setAttribute("isbn", book.getIsbn());
		bookNode.setAttribute("notes", book.getNotes());
		bookNode.setAttribute("originalPrice", book.getOriginalPrice());
		bookNode.setAttribute("publisher", book.getPublisher());
		bookNode.setAttribute("salePrice", book.getSalePrice());
		bookNode.setAttribute("title", book.getTitle());
		bookNode.setAttribute("translator", book.getTranslator());
		bookNode.setAttribute("publishDate", book.getPublishDate() == null ? null : String.valueOf(book
				.getPublishDate().getTime()));
		bookNode.setAttribute("purchaseDate", book.getPurchaseDate() == null ? null : String.valueOf(book
				.getPurchaseDate().getTime()));
		bookNode.setAttribute("favorite", String.valueOf(book.getFavorite()));
		books.appendChild(bookNode);
	}

	private void makeBookLists(Element booklists, Document doc, Library lib) {
		for (int i = 0; i < lib.sizeOfBookLists(); i++) {
			BookList bList = lib.getBookList(i);
			makeBooksList(bList, booklists, doc);
		}
	}

	private void makeBooksList(BookList list, Element booklists, Document doc) {
		Element bListNode = doc.createElement("booklist");
		bListNode.setAttribute("id", String.valueOf(list.getId()));
		bListNode.setAttribute("name", list.getName());
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			buf.append(list.get(i).getId()).append(",");

		}
		bListNode.setAttribute("books", buf.toString());
		booklists.appendChild(bListNode);
	}

	private void makeBorrowers(Element borrowers, Document doc, Library lib) {
		for (int i = 0; i < lib.sizeOfBorrowers(); i++) {
			Borrower borrower = lib.getBorrower(i);
			makeBorrower(borrower, borrowers, doc);
		}
	}

	private void makeBorrower(Borrower borrower, Element borrowers, Document doc) {
		Element borrowerNode = doc.createElement("borrower");
		borrowerNode.setAttribute("id", String.valueOf(borrower.getId()));
		borrowerNode.setAttribute("name", borrower.getName());
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < borrower.size(); i++) {
			buf.append(borrower.get(i).getId()).append(",");

		}
		borrowerNode.setAttribute("books", buf.toString());
		borrowers.appendChild(borrowerNode);
	}

	public static Library load() throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File("library.xml");

		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

		Library lib = new Library();
		if (doc == null) {
			return lib;
		}

		parseLibrary(doc, lib);

		return lib;
	}

	private static void parseLibrary(Document doc, Library lib) {
		Node root = doc.getChildNodes().item(0);

		Node books = root.getChildNodes().item(0);
		Node booklists = root.getChildNodes().item(1);
		Node borrowers = root.getChildNodes().item(2);

		parseBooks(books, lib);
		parseBooklists(booklists, lib);
		parseBorrowers(borrowers, lib);

		bind(lib);

	}

	private static void bind(Library lib) {
		bindBookLists(lib);
		bindBorrowers(lib);
	}

	private static void bindBookLists(Library lib) {

	}

	private static void bindBorrowers(Library lib) {

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

	public void bookListAdded(BookList value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookListRemoved(BookList value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void borrowerAdded(Borrower value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void borrowerRemoved(Borrower value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookAdded(BookList list, Book book) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookRemoved(BookList list, Book book) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void nameChanged(BookList list, String newName) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bookChanged(Library library, Book value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void bookListChanged(BookList value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void borrowerChanged(Borrower value) {
		try {
			store(lib);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
