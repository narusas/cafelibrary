package net.narusas.cafelibrary.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookListener;

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
public class BookItemPanel extends javax.swing.JPanel implements BookListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3404274448238358563L;
	private JButton addButton;
	private JTextArea authorTextArea;
	private JTextArea titleTextArea;
	private JLabel coverLabel;
	private Book book;
	private BookItemAddListener listener;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		Book book = new Book(315, "Rapid Development");
		book.setAuthor("Steve McConnel");

		JFrame frame = new JFrame();
		BookItemPanel p = new BookItemPanel();
		p.setBook(book);
		frame.getContentPane().add(p);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BookItemPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(270, 70));
			thisLayout.rowWeights = new double[] { 0.0, 0.0 };
			thisLayout.rowHeights = new int[] { 36, 7 };
			thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7 };
			this.setLayout(thisLayout);
			this.setBackground(new java.awt.Color(255, 255, 255));
			{
				addButton = new JButton();
				this.add(addButton, new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
				addButton.setIcon(new ImageIcon("images/add.png"));
				addButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
				addButton.setPreferredSize(new java.awt.Dimension(24, 24));
				addButton.setSize(24, 24);
			}
			{
				coverLabel = new JLabel();
				this.add(coverLabel, new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 5, 0, 5), 0, 0));
				coverLabel.setText("");
				coverLabel.setSize(55, 70);
				coverLabel.setPreferredSize(new Dimension(55, 70));
			}
			{
				titleTextArea = new JTextArea();
				this.add(titleTextArea, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
				titleTextArea.setText("titleTextArea");
				titleTextArea.setFont(new java.awt.Font("±¼¸²", 1, 12));
				titleTextArea.setEditable(false);
				titleTextArea.setLineWrap(true);
			}
			{
				authorTextArea = new JTextArea();
				this.add(authorTextArea, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
				authorTextArea.setText("authorTextArea");
				authorTextArea.setEditable(false);
				authorTextArea.setLineWrap(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
	}

	protected void addBook() {
		listener.addBook(this, book);
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JTextArea getAuthorTextArea() {
		return authorTextArea;
	}

	public JTextArea getTitleTextArea() {
		return titleTextArea;
	}

	public JLabel getCoverLabel() {
		return coverLabel;
	}

	public void setBook(Book book) {
		this.book = book;
		book.addListener(this);
		titleTextArea.setText(book.getTitle());
		authorTextArea.setText(book.getAuthor());
		coverLabel.setIcon(new LazyIcon(book));
	}

	public Book getBook() {
		return book;
	}

	public void addListener(BookItemAddListener listener) {
		this.listener = listener;
	}

	public void bookChanged(Book book, String attrName, Object value) {
		if ("coverImage".equals(attrName)) {
			coverLabel.repaint();
		}
	}
}
