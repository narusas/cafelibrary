package net.narusas.cafelibrary;

import java.awt.Image;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Book extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9184573745645700810L;
	public static final Book NullBook = new NullBook();

	static class NullBook extends Book {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8989148288257097451L;

		public NullBook() {
			super(0, "");
			url = "";
			coverSmallUrl = "";
			coverLargeUrl = "";
			description = "";
			notes = "";
			author = "";
			translator = "";
			publisher = "";

			category = "";
			isbn = "";
			canSale = false;
			originalPrice = "";
			salePrice = "";
			borrower = Borrower.NullBorrower;
		}
	}

	String url;
	String coverSmallUrl;
	String coverLargeUrl;
	String description;
	String author = "";
	String translator;
	String publisher;
	Date publishDate;

	String category;
	String isbn;
	boolean canSale;
	String originalPrice;
	String salePrice;
	Date purchaseDate;

	private List<BookListener> listeners = new LinkedList<BookListener>();

	protected int favorite;
	protected String notes;
	protected Borrower borrower;
	protected int serialNo;
	protected String titleWithNoSerial;
	protected Image coverImage;
	protected boolean imgRequested = false;

	static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy.MM.dd");

	public Book() {
		super(IdGenerator.newId(), null);
	}

	public Book(String name) {
		super(IdGenerator.newId(), name);
		setTitle(name);
	}

	public Book(long id, String name) {
		super(id, name);
		setTitle(name);
	}

	public String getTitle() {
		return name;
	}

	public void setTitle(String title) {
		this.name = title;
		calcSerial(title);
		notifyChanged(this, "title", title);
	}

	private void calcSerial(String title) {
		titleWithNoSerial = title;
		String[] tokens = title.split(" ");
		if (tokens.length != 1) {
			int index = tokens.length - 1;
			String last = tokens[index];
			String susfix = "";
			if (last.endsWith(")") && last.contains("(")) {
				susfix = last.substring(last.indexOf("("));
				// index -= 1;
				last = last.substring(0, last.indexOf("("));// tokens[index];
			}

			try {
				serialNo = Integer.parseInt(last.trim());
				titleWithNoSerial = "";
				for (int i = 0; i < index; i++) {
					titleWithNoSerial += tokens[i];
					if (i + 1 != index) {
						titleWithNoSerial += " ";
					}
				}
				titleWithNoSerial += " " + susfix;
				titleWithNoSerial = titleWithNoSerial.trim();

			} catch (Throwable e) {
			}
		}
	}

	private void notifyChanged(Book book, String attrName, Object value) {
		for (int i = 0; i < listeners.size(); i++) {
			BookListener listener = listeners.get(i);
			listener.bookChanged(book, attrName, value);
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
		notifyChanged(this, "url", url);
	}

	public String getCoverSmallUrl() {
		return coverSmallUrl;
	}

	public void setCoverSmallUrl(String coverSmallUrl) {
		this.coverSmallUrl = coverSmallUrl;
		notifyChanged(this, "coverSmallUrl", coverSmallUrl);
	}

	public String getCoverLargeUrl() {
		return coverLargeUrl;
	}

	public void setCoverLargeUrl(String coverLargeUrl) {
		this.coverLargeUrl = coverLargeUrl;
		notifyChanged(this, "coverLargeUrl", coverLargeUrl);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
		notifyChanged(this, "description", description);
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
		notifyChanged(this, "author", author);
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
		notifyChanged(this, "translator", translator);
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
		notifyChanged(this, "publisher", publisher);
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
		notifyChanged(this, "publishDate", publishDate);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
		notifyChanged(this, "category", category);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
		notifyChanged(this, "isbn", isbn);
	}

	public boolean isCanSale() {
		return canSale;
	}

	public void setCanSale(boolean canSale) {
		this.canSale = canSale;
		notifyChanged(this, "canSale", canSale);
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
		notifyChanged(this, "originalPrice", originalPrice);
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
		notifyChanged(this, "favorite", favorite);
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
		notifyChanged(this, "salePrice", salePrice);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book target = (Book) obj;
			return id == target.id;
		} else
			return false;

	}

	public int getFavorite() {
		return favorite;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date pucharseDate) {
		this.purchaseDate = pucharseDate;
		notifyChanged(this, "purchaseDate", pucharseDate);
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
		notifyChanged(this, "notes", notes);
	}

	public Borrower getBorrower() {
		return borrower;
	}

	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
		notifyChanged(this, "borrower", borrower);
	}

	public void addListener(BookListener listener) {
		listeners.add(listener);
	}

	public void removeListener(BookListener listener) {
		listeners.remove(listener);
	}

	public void merge(Book book) {
		if (author == null || "".equals(author)) {
			setAuthor(book.getAuthor());
		}
		if (category == null || "".equals(category)) {
			setCategory(book.getCategory());
		}
		if (coverLargeUrl == null || "".equals(coverLargeUrl)) {
			setCoverLargeUrl(book.getCoverLargeUrl());
		}
		if (coverSmallUrl == null || "".equals(coverSmallUrl)) {
			setCoverSmallUrl(book.getCoverSmallUrl());
		}

		if (description == null || "".equals(description)) {
			setDescription(book.getDescription());
		}
		if (publishDate == null || "".equals(publishDate)) {
			setPublishDate(book.getPublishDate());
		}
		if (publisher == null || "".equals(publisher)) {
			setPublisher(book.getPublisher());
		}
		if (translator == null || "".equals(translator)) {
			setTranslator(book.getTranslator());
		}

		if (originalPrice == null || "".equals(originalPrice)) {
			setOriginalPrice(book.getOriginalPrice());
		}
	}

	public void imageDownloaded() {
		notifyChanged(this, "coverImage", null);
	}

	public boolean isSerial() {
		return serialNo != 0;
	}

	public int getSerialNo() {
		return serialNo;
	}

	public String getTitleWithNoSerial() {
		return titleWithNoSerial;
	}

	public Image getCoverImage() {
		if (coverImage == null) {
			coverImage = Toolkit.getDefaultToolkit().getImage("data/" + getId() + "L.jpg");
		}
		return coverImage;
	}

	public String toString() {
		return "Book{id=" + id + ",title=" + name + ",author=" + author + ",publisher=" + publisher + ",coverS="
				+ coverSmallUrl + ",coverL=" + coverLargeUrl + ",description=" + description + "}";
	}

	public boolean isMatch(String filter) {
		return filter == null || "".equals(filter) || isTitleMatch(filter) || isAuthorMatch(filter);
	}

	private boolean isAuthorMatch(String filter) {
		return getAuthor() != null && getAuthor().toLowerCase().contains(filter);
	}

	private boolean isTitleMatch(String filter) {
		return getTitle() != null && getTitle().toLowerCase().contains(filter);
	}

	public String getPurchaseDateString() {
		return purchaseDate == null ? "" : dateFormatter.format(purchaseDate);
	}

	public String getPublishDateString() {
		return publishDate == null ? "" : dateFormatter.format(publishDate);
	}

}
