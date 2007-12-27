package net.narusas.cafelibrary.ui2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookListener;
import net.narusas.ui.component.GridentButton;
import net.narusas.ui.component.ImageView;

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
public class SearchedBook extends javax.swing.JPanel implements BookListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 453397733555093714L;
	private GridentButton addButton;
	private JTextArea title;
	private JTextArea author;
	private ImageView coverImageView;

	static Color[] COLORS = new Color[] { new Color(255, 255, 255), new Color(230, 230, 255) };
	int colorIndex = 1;
	private Book book;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SearchedBook());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public SearchedBook() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(300, 79));
			thisLayout.rowWeights = new double[] { 0.1, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7 };
			thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.1 };
			thisLayout.columnWidths = new int[] { 7, 7, 7 };
			this.setLayout(thisLayout);
			this.setBackground(COLORS[colorIndex]);
			{
				addButton = new GridentButton();
				this.add(getAddButton(), new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 10, 0, 8), 0, 0));
				// addButton.setText("Add");
				addButton.setIcon(new ImageIcon("images/add_book.png"));
				addButton.setPreferredSize(new Dimension(35, 20));
			}
			{
				coverImageView = new ImageView();
				this.add(getCoverImageView(), new GridBagConstraints(1, 0, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 8), 0, 0));
				coverImageView.setPreferredSize(new java.awt.Dimension(50, 70));
				coverImageView.setBackground(new java.awt.Color(255, 255, 255));
				coverImageView.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 1, false));
				coverImageView.setSize(50, 70);
			}
			{
				title = new JTextArea();
				this.add(getTitle(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(5, 0, 0, 10), 0, 0));
				title.setText("Title title title title ttle title");
				title.setFont(new java.awt.Font("±¼¸²", 1, 14));
				title.setLineWrap(true);
				title.setEditable(false);
				title.setBackground(COLORS[colorIndex]);
			}
			{
				author = new JTextArea();
				this.add(author, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 5, 10), 0, 0));
				author.setText("author");
				author.setLineWrap(true);
				author.setEditable(false);
				author.setBackground(COLORS[colorIndex]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GridentButton getAddButton() {
		return addButton;
	}

	public ImageView getCoverImageView() {
		return coverImageView;
	}

	public JTextArea getTitle() {
		return title;
	}

	public JTextArea getAuthor() {
		return author;
	}

	public int getColorIndex() {
		return colorIndex;
	}

	public void setColorIndex(int colorIndex) {
		this.colorIndex = colorIndex;
		repaint();
	}

	public void setBook(Book book) {
		this.book = book;
		title.setText(book == null ? null : book.getTitle());
		author.setText(book == null ? null : book.getAuthor());
		coverImageView.setImage(book == null ? null : book.getCoverImage());

		book.addListener(this);
	}

	public void bookChanged(Book book, String attrName, Object value) {
		System.out.println(attrName + ":" + value);
		if ("coverImage".equals(attrName)) {
			if (value == null) {
				book.getCoverImage();
			} else {
				coverImageView.setImage(book.getCoverImage());
			}

			repaint();
		}
	}
}
