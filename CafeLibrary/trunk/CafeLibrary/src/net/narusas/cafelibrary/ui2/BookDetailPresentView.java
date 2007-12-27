package net.narusas.cafelibrary.ui2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import net.narusas.cafelibrary.Book;
import net.narusas.ui.component.ImageView;
import net.narusas.ui.component.Separator;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class BookDetailPresentView extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7897500319303507309L;
	private ImageView coverImageView;
	private JTextArea title;
	private JTextArea author;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField borrower;
	private JTextField purchaseDate;
	private JTextField price;
	private JTextField isbn;
	private JTextField category;
	private JTextField publishDate;
	private JTextField publisher;
	private JTextArea notes;
	private JTextArea description;
	private Separator separator3;
	private Separator separator2;
	private Separator separator1;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel publisherLabel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BookDetailPresentView());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BookDetailPresentView() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(300, 500));
			GridBagLayout thisLayout = new GridBagLayout();
			this.setBackground(new java.awt.Color(255, 255, 255));
			thisLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.1, 0.0, 0.1 };
			thisLayout.rowHeights = new int[] { 79, 50, 27, 20, 20, 20, 20, 20, 20, 20, 20, 20, 24, 20, 20, 7 };
			thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.1 };
			thisLayout.columnWidths = new int[] { 61, 23, 7, 7 };
			this.setLayout(thisLayout);
			{
				coverImageView = new ImageView();
				this.add(coverImageView, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
				coverImageView.setPreferredSize(new Dimension(70, 100));
				coverImageView.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
			}
			{
				title = new JTextArea();
				this.add(title, new GridBagConstraints(1, 0, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(10, 0, 0, 10), 0, 0));
				title.setText("Title");
				title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				title.setFont(new java.awt.Font("±¼¸²", 1, 14));
				title.setEditable(false);
				title.setOpaque(false);
				title.setLineWrap(true);
			}
			{
				author = new JTextArea();
				this.add(getAuthor(), new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 10), 0, 0));
				author.setText("Author");
				author.setEditable(false);
				author.setOpaque(false);
				author.setLineWrap(true);
			}
			{
				publisherLabel = new JLabel();
				this.add(publisherLabel, new GridBagConstraints(0, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				publisherLabel.setText("\ucd9c\ud310\uc0ac");
				publisherLabel.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1, new GridBagConstraints(0, 4, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel1.setText("\ucd9c\ud310\uc77c");
				jLabel1.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel2 = new JLabel();
				this.add(jLabel2, new GridBagConstraints(0, 5, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel2.setText("\uc7a5\ub974");
				jLabel2.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel3 = new JLabel();
				this.add(jLabel3, new GridBagConstraints(0, 6, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel3.setText("ISBN");
				jLabel3.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel4 = new JLabel();
				this.add(jLabel4, new GridBagConstraints(0, 7, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel4.setText("\uac00\uaca9");
				jLabel4.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel5 = new JLabel();
				this.add(jLabel5, new GridBagConstraints(0, 9, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel5.setText("\uad6c\ub9e4\uc77c");
				jLabel5.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel6 = new JLabel();
				this.add(jLabel6, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel6.setText("\uc120\ud638\ub3c4");
				jLabel6.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				jLabel7 = new JLabel();
				this.add(jLabel7, new GridBagConstraints(0, 11, 2, 1, 0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 4), 0, 0));
				jLabel7.setText("\ub300\uc5ec\uc790");
				jLabel7.setForeground(new java.awt.Color(128, 128, 128));
			}
			{
				separator1 = new Separator();
				this.add(separator1, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				separator2 = new Separator();
				this.add(separator2, new GridBagConstraints(0, 12, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				separator3 = new Separator();
				this.add(separator3, new GridBagConstraints(0, 14, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				description = new JTextArea();
				this.add(description, new GridBagConstraints(0, 13, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
				description.setText("description");
				description.setEditable(false);
				description.setOpaque(false);
				description.setLineWrap(true);
			}
			{
				notes = new JTextArea();
				this.add(notes, new GridBagConstraints(0, 15, 4, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 10, 10, 10), 0, 0));
				notes.setText("notes");
				notes.setEditable(false);
				notes.setOpaque(false);
				notes.setLineWrap(true);
			}
			{
				publisher = new JTextField();
				this.add(getPublisher(), new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				publisher.setText("publisher");
				publisher.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				publisher.setEditable(false);
				publisher.setOpaque(false);
			}
			{
				publishDate = new JTextField();
				this.add(publishDate, new GridBagConstraints(2, 4, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				publishDate.setText("publishDate");
				publishDate.setEditable(false);
				publishDate.setOpaque(false);
				publishDate.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				category = new JTextField();
				this.add(category, new GridBagConstraints(2, 5, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				category.setText("category");
				category.setEditable(false);
				category.setOpaque(false);
				category.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				isbn = new JTextField();
				this.add(isbn, new GridBagConstraints(2, 6, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				isbn.setText("isbn");
				isbn.setEditable(false);
				isbn.setOpaque(false);
				isbn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				price = new JTextField();
				this.add(price, new GridBagConstraints(2, 7, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				price.setText("price");
				price.setEditable(false);
				price.setOpaque(false);
				price.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				purchaseDate = new JTextField();
				this.add(purchaseDate, new GridBagConstraints(2, 9, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				purchaseDate.setText("purchaseDate");
				purchaseDate.setEditable(false);
				purchaseDate.setOpaque(false);
				purchaseDate.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
			{
				borrower = new JTextField();
				this.add(borrower, new GridBagConstraints(2, 11, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 4, 0, 10), 0, 0));
				borrower.setText("borrower");
				borrower.setEditable(false);
				borrower.setOpaque(false);
				borrower.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ImageView getCoverImageView() {
		return coverImageView;
	}

	public JTextArea getAuthor() {
		return author;
	}

	public JTextField getPublisher() {
		return publisher;
	}

	public JTextArea getTitle() {
		return title;
	}

	public JTextField getBorrower() {
		return borrower;
	}

	public JTextField getPurchaseDate() {
		return purchaseDate;
	}

	public JTextField getPrice() {
		return price;
	}

	public JTextField getIsbn() {
		return isbn;
	}

	public JTextField getCategory() {
		return category;
	}

	public JTextField getPublishDate() {
		return publishDate;
	}

	public JTextArea getNotes() {
		return notes;
	}

	public JTextArea getDescription() {
		return description;
	}

	public JLabel getPublisherLabel() {
		return publisherLabel;
	}

	public void setBook(Book book) {
		if (book == null) {
			book = Book.NullBook;
		}
		coverImageView.setImage(book.getCoverImage(), 70, 100);
		coverImageView.setPreferredSize(new Dimension(70, 100));
		title.setText(book.getTitle());
		author.setText(book.getAuthor());
		publisher.setText(book.getPublisher());
		category.setText(book.getCategory());
		isbn.setText(book.getIsbn());
		price.setText(book.getOriginalPrice());
		purchaseDate.setText(book.getPurchaseDateString());
		publishDate.setText(book.getPublishDateString());
		borrower.setText(book.getBorrower() == null ? "" : book.getBorrower().getName());
		description.setText(book.getDescription());
		notes.setText(book.getNotes());

	}

}
