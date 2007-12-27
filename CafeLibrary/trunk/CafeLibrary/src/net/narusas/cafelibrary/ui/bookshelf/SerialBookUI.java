package net.narusas.cafelibrary.ui.bookshelf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.BookListener;

public class SerialBookUI extends JComponent implements BookListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6236677375072146587L;
	private final BookList list;
	static Image coverMaterialShadow, coverDropShadow, backMaterialShadow, noCover;
	static boolean imgInited = false;

	int seriNoYDelta = 2;
	int selected = 0;
	private FontMetrics fm;
	private Font font;
	Image shadow;
	private SerialBookSelectionListener listener;
	static Properties selectionProps;
	private static File storeFile;

	int oldSelected = 0;
	static {
		selectionProps = new Properties();
		storeFile = new File("selection.props");
		try {
			selectionProps.load(new FileReader(storeFile));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	public SerialBookUI(BookList bList) {
		super();
		list = bList;
		loadLastSelected(bList);
		initShadowImages();
		loadBooksImages();
		bindEvents();
	}

	private void loadLastSelected(BookList bList) {
		try {
			selected = Integer.parseInt(selectionProps.getProperty(bList.getName(), "0"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void bindEvents() {
		addMouseMotionListener(new MouseMotionAdapter() {

			public void mouseDragged(MouseEvent e) {
				select(e.getX());
			}
		});

		addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent e) {
				select(e.getX());
			}
		});
	}

	private void loadBooksImages() {
		for (int i = 0; i < list.getBookSize(); i++) {
			Book book = list.get(i);
			book.addListener(this);
		}
	}

	private void initShadowImages() {
		if (imgInited == false) {
			imgInited = true;
			try {
				coverMaterialShadow = Toolkit.getDefaultToolkit().createImage("images/coverOnShadow.png");
				coverDropShadow = Toolkit.getDefaultToolkit().createImage("images/coverDropShadow.png");
				backMaterialShadow = Toolkit.getDefaultToolkit().createImage("images/coverBackMaterialShadow.png");
				noCover = Toolkit.getDefaultToolkit().createImage("images/noCover.jpg");
				loadImage(coverMaterialShadow);
				loadImage(coverDropShadow);
				loadImage(backMaterialShadow);
				loadImage(noCover);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isFocusable() {
		return true;
	}

	public boolean isDraggable() {
		return oldSelected == selected;
	}

	void loadImage(Image image) {
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

	protected void select(int x) {
		int progressX = 0;
		for (int i = 0; i < list.getBookSize(); i++) {
			if (i == selected) {
				if (x >= progressX && x <= progressX + BookShelfConstraints.getWidth()) {
					setSelection(i);
					bookSelected();
					return;
				}
				progressX += BookShelfConstraints.getWidth() + 2;
			} else {
				if (x >= progressX && x <= progressX + BookShelfConstraints.getBackWeight()) {
					setSelection(i);
					bookSelected();
					return;
				}
				progressX += BookShelfConstraints.getBackWeight();
			}
		}
	}

	private void setSelection(int i) {
		oldSelected = selected;
		selected = i;
		saveSelection(list.getName(), selected);
	}

	private void saveSelection(String name, int selected) {
		selectionProps.setProperty(name, String.valueOf(selected));
		try {
			selectionProps.store(new FileWriter(storeFile), "Selection history");
		} catch (IOException e) {
		}
	}

	private void bookSelected() {
		repaint();
		if (listener != null) {
			listener.serialBookSelected(list.get(selected));
		}
	}

	public void setListener(SerialBookSelectionListener listener) {
		this.listener = listener;

	}

	public int getWidth() {
		return (list.getBookSize() - 1) * BookShelfConstraints.getBackWeight() + BookShelfConstraints.getDropWidth()
				+ 5;
	}

	public int getHeight() {
		return BookShelfConstraints.getDropHeight() + 2 + 40;
	}

	public Dimension getPreferredSize() {
		return new Dimension(getWidth(), getHeight());
	}

	public Dimension getSize() {
		return getPreferredSize();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.setColor(new Color(255,0,0,100));
		// g.fillRect(0, 0, getWidth(), getHeight());
		// g.setColor(Color.black);
		// g.drawRect(0, 0, getWidth()-1, getHeight()-1);
		draw(g);

	}

	private void draw(Graphics g) {
		font = g.getFont().deriveFont(9f);
		g.setFont(font);
		fm = Toolkit.getDefaultToolkit().getFontMetrics(font);

		int x = 5;
		for (int i = 0; i < list.getBookSize(); i++) {
			Book book = list.get(i);
			if (i == selected) {
				x = drawCover(g, book, x);
			} else {
				x = drawBack(g, book, x);
			}
		}
		drawTitle(g);
	}

	private void drawTitle(Graphics g) {
		g.setColor(Color.white);
		int strWidth = SwingUtilities.computeStringWidth(fm, list.getName());
		int x = (getWidth() - strWidth) / 2;
		if (x < 0) {
			x = 0;
		}
		int h = 11;
		String[] text = null;
		int maxWidth = strWidth;
		if (strWidth > getWidth()) {
			maxWidth = 0;
			int rows = (int) (((float) strWidth / (float) getWidth()) + 0.9999);
			h = 11 * rows;
			// System.out.println("R:" + rows + " SW:" + strWidth + " W:" +
			// getWidth() + " H:" + h);
			text = new String[rows];
			int len = list.getName().length() / rows;
			int last = 0;
			for (int i = 0; i < rows; i++) {
				if (i + 1 == rows) {
					text[i] = list.getName().substring(last);

					int width = SwingUtilities.computeStringWidth(fm, text[i]);
					if (width > maxWidth) {
						maxWidth = width;
					}
				} else {
					text[i] = list.getName().substring(last, last + len);
					int width = SwingUtilities.computeStringWidth(fm, text[i]);
					if (width > maxWidth) {
						maxWidth = width;
					}
				}
				last += len;
			}
		}
		g.fillRect(x + 5, BookShelfConstraints.getDropHeight() + 3, maxWidth + 5, h);
		g.setColor(Color.BLACK);
		g.drawRect(x + 5, BookShelfConstraints.getDropHeight() + 3, maxWidth + 5, h);
		if (text == null) {
			g.drawString(list.getName(), x + 7, BookShelfConstraints.getDropHeight() + 12);
		} else {
			for (int i = 0; i < text.length; i++) {
				g.drawString(text[i], x + 7, BookShelfConstraints.getDropHeight() + 12 + i * 11);
			}
		}

	}

	private int drawCover(Graphics g, Book book, int x) {
		Image img = book.getCoverImage();

		g.drawImage(coverDropShadow, x - BookShelfConstraints.getDropGapWidth(), 5,
				BookShelfConstraints.getDropWidth(), BookShelfConstraints.getDropHeight(), null);
		if (img == null || img.getWidth(null) <= 0) {
			g.drawImage(noCover, x, BookShelfConstraints.getDropGapHeight(), BookShelfConstraints.getWidth(),
					BookShelfConstraints.getHeight(), null);
		} else {
			g.drawImage(img, x, BookShelfConstraints.getDropGapHeight(), BookShelfConstraints.getWidth(),
					BookShelfConstraints.getHeight(), null);
		}

		g.drawImage(coverMaterialShadow, x, BookShelfConstraints.getDropGapHeight(), BookShelfConstraints.getWidth(),
				BookShelfConstraints.getHeight(), null);

		if (book.getBorrower() != null) {
			int sx = x + BookShelfConstraints.getWidth();
			int sy = BookShelfConstraints.getDropGapHeight();
			int[] xPoints = new int[] { sx - 25, sx - 10, sx, sx };
			int[] yPoints = new int[] { sy, sy, sy + 10, sy + 25 };
			g.setColor(new Color(255, 0, 0, 200));
			g.fillPolygon(xPoints, yPoints, 4);
		}

		g.setColor(Color.BLACK);
		// g.drawRoundRect(x, 0, imgW, imgH, 3, 3);

		{
			g.setColor(Color.black);

			String str = String.valueOf(book.getSerialNo());
			int strWidth = SwingUtilities.computeStringWidth(fm, str);

			g.setColor(Color.white);
			int strX = x + BookShelfConstraints.getWidth() - strWidth - 13;
			g.fillArc(strX - 2, BookShelfConstraints.getHeight() - seriNoYDelta - 10, strWidth + 4, 11, 0, 360);
			g.setColor(Color.BLACK);

			g.drawString(str, strX, BookShelfConstraints.getHeight() - seriNoYDelta);
		}

		return x + BookShelfConstraints.getWidth() + 2;
	}

	public boolean isFocusOwner() {
		return true;
	}

	private int drawBack(Graphics g, Book book, int x) {
		int sy = BookShelfConstraints.getDropGapHeight();
		g.setColor(new Color(244, 244, 230));
		g.fillRect(x, sy, BookShelfConstraints.getBackWidth(), BookShelfConstraints.getHeight());
		// g.setColor(Color.black);
		// g.drawRoundRect(x, sy, BookShelfConstraints.getBackWidth(),
		// BookShelfConstraints.getHeight(), 3, 3);
		g.drawImage(backMaterialShadow, x, sy, BookShelfConstraints.getBackWidth(), BookShelfConstraints.getHeight(),
				null);
		if (book.getBorrower() != null) {
			g.setColor(new Color(255, 0, 0, 200));
			g.fillRect(x, sy + 10, BookShelfConstraints.getBackWidth(), 15);

			g.setColor(Color.black);
			g.drawRect(x, sy + 10, BookShelfConstraints.getBackWidth(), 15);
		}

		String str = String.valueOf(book.getSerialNo());
		int strWidth = SwingUtilities.computeStringWidth(fm, str);
		int strX = x + (BookShelfConstraints.getBackWeight() - 2 - strWidth) / 2 + 1;
		g.setColor(Color.white);
		g.fillArc(strX - 2, BookShelfConstraints.getHeight() - seriNoYDelta - 10, strWidth + 2, 11, 0, 360);
		g.setColor(Color.BLACK);
		g.drawString(str, strX, BookShelfConstraints.getHeight() - seriNoYDelta);
		return x + BookShelfConstraints.getBackWeight();
	}

	public Book getSelectedBook() {
		return list.get(selected);
	}

	public void bookChanged(Book book, String attrName, Object value) {
		if ("coverImage".equals(attrName)) {
			repaint();
		}
	}
}
