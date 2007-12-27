package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfConstraints;
import net.narusas.cafelibrary.ui2.bookshelf.BookView.Show;

public abstract class BookUI extends JComponent {
	protected static Image coverMaterialShadow, coverDropShadow, backMaterialShadow, noCover, selectionBorder,
			emptyBack;
	protected static boolean imgInited;

	public static BookUI get(Show showStatus) {
		if (showStatus == Show.Cover) {
			return new BookCoverUI();
		}
		if (showStatus == Show.SelectedCover) {
			return new BookSelectedCoverUI();
		}
		if (showStatus == Show.Back) {
			return new BookBackUI();
		}
		if (showStatus == Show.EmptyBack) {
			return new BookEmptyBackUI();
		}
		return null;
	}

	public BookUI() {
		initShadowImages();
	}

	public abstract Dimension getPreferredSize(BookView bookView);

	public abstract Dimension getSize(BookView bookView);

	public abstract void paintComponent(Graphics g, BookView bookView);

	protected void initShadowImages() {
		if (imgInited == false) {
			imgInited = true;
			try {
				coverMaterialShadow = Toolkit.getDefaultToolkit().createImage("images/coverOnShadow.png");
				coverDropShadow = Toolkit.getDefaultToolkit().createImage("images/coverDropShadow.png");
				backMaterialShadow = Toolkit.getDefaultToolkit().createImage("images/coverBackMaterialShadow.png");
				noCover = Toolkit.getDefaultToolkit().createImage("images/noCover.jpg");
				selectionBorder = Toolkit.getDefaultToolkit().createImage("images/selection_border.png");
				emptyBack = Toolkit.getDefaultToolkit().createImage("images/coverEmptyBook.png");
				loadImage(coverMaterialShadow);
				loadImage(coverDropShadow);
				loadImage(backMaterialShadow);
				loadImage(noCover);
				loadImage(selectionBorder);
				loadImage(emptyBack);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void loadImage(Image image) {
		MediaTracker tracker = new MediaTracker(this);
		int loadStatus;
		synchronized (tracker) {
			tracker.addImage(image, 0);
			try {
				tracker.waitForID(0, 0);
			} catch (InterruptedException e) {
				System.out.println("INTERRUPTED while loading Image");
			}
			loadStatus = tracker.statusID(0, false);
			tracker.removeImage(image, 0);
		}
	}

	protected void drawSerialNo(int x, int seriNoYDelta, Graphics2D imgG, FontMetrics fm, Book book) {
		if (book.isSerial() == false) {
			return;
		}
		imgG.setColor(Color.BLACK);

		{
			imgG.setColor(Color.black);

			String str = String.valueOf(book.getSerialNo());
			int strWidth = SwingUtilities.computeStringWidth(fm, str);

			imgG.setColor(Color.white);
			int strX = x + BookShelfConstraints.getWidth() - strWidth - 13;
			imgG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			imgG.fillArc(strX - 2, BookShelfConstraints.getHeight() - seriNoYDelta - 10, strWidth + 4, 11, 0, 360);
			imgG.setColor(Color.BLACK);

			imgG.drawString(str, strX, BookShelfConstraints.getHeight() - seriNoYDelta - 1);
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(new BookView(new Book(8976, "ABC 1")));
		BookView book2 = new BookView(new Book(802, "DEFC"));
		book2.setShowStatus(BookView.Show.Back);
		f.getContentPane().add(book2);

		BookView book3 = new BookView(new Book(803, "DEFC 69"));
		book3.setShowStatus(BookView.Show.SelectedCover);
		f.getContentPane().add(book3);

		BookView book4 = new BookView(new Book(803, "aaa 1"));
		book4.setShowStatus(BookView.Show.EmptyBack);
		f.getContentPane().add(book4);

		f.setSize(300, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
