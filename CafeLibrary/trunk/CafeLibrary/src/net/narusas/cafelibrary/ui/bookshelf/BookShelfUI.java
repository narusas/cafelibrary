package net.narusas.cafelibrary.ui.bookshelf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.TransferHandler;
import javax.swing.WindowConstants;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.serial.LibrarySerializer;
import net.narusas.cafelibrary.ui2.bookshelf.BookShelfLayout;
import net.narusas.ui.component.StringTransferHandler;

public class BookShelfUI extends JPanel implements SerialBookSelectionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3459847188533591175L;
	private Image bg;
	private int width;
	private BookShelfModel model;
	private SerialBookSelectionListener listener;
	private Book selectedBook;

	public BookShelfUI() {
		super();
		setLayout(new BookShelfLayout(5, 15, 130));
		bg = Toolkit.getDefaultToolkit().createImage("images/wood.jpg");
		loadImage(bg);
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

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBG(g);
		drawPlatform(g);
	}

	private void drawPlatform(Graphics g) {
		int h = getHeight();
		int y = 0;
		g.setColor(Color.GRAY);
		while (y < h) {
			g.fillRect(0, y + BookShelfConstraints.getDropHeight(), getWidth(), 5);

			y += BookShelfConstraints.getDropHeight() + 35;

		}
	}

	private void drawBG(Graphics g) {
		int w = bg.getWidth(null);
		int h = bg.getHeight(null);

		int x = 0;
		int y = 0;
		while (y < getHeight()) {
			g.drawImage(bg, x, y, null);
			if (x < getWidth()) {
				x += w;
			} else {
				x = 0;
				y += h;
			}
		}
	}

	public void setModel(final BookShelfModel model) {
		this.model = model;
		model.addListener(new BookShelfModelListener() {

			public void bookListChanged() {
				refresh(model);

			}
		});
		refresh(model);
	}

	private void refresh(BookShelfModel model) {
		removeAll();
		for (int i = 0; i < model.size(); i++) {
			final SerialBookUI ui = new SerialBookUI(model.getBookList(i));
			ui.setListener(this);
			ui.setTransferHandler(new BookShelfTransferHandler());
			MouseAdapter ml = new MouseAdapter() {

				public void mousePressed(MouseEvent e) {
					SerialBookUI c = (SerialBookUI) e.getSource();
					if (c.isDraggable()) {
						TransferHandler th = c.getTransferHandler();
						th.exportAsDrag(c, e, TransferHandler.COPY);
					}

				}
			};
			ui.addMouseListener(ml);
			ui.addKeyListener(this);
			add(ui);
		}
		repaint();
		revalidate();

	}

	public void setSelectionListener(SerialBookSelectionListener listener) {
		this.listener = listener;
	}

	public void serialBookSelected(Book book) {
		selectedBook = book;
		if (listener != null) {
			listener.serialBookSelected(book);
		}
	}

	public void deleteSelectedBook() {
		if (selectedBook == null || model == null) {
			return;
		}
		model.remove(selectedBook);
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
			deleteSelectedBook();
		}
	}

	public boolean isFocusable() {
		return true;
	}

	public static void main(String[] args) {
		BookList list1 = createList();
		try {
			Library lib = LibrarySerializer.load();
			BookShelfModel model = new BookShelfModel(lib);

			JFrame f = new JFrame();
			BookShelfConstraints.setZoom(1.0f);
			f.getContentPane().setLayout(new BorderLayout());
			BookShelfUI comp = new BookShelfUI();
			comp.setModel(model);
			comp.setPreferredSize(new Dimension(400, 10000));
			JScrollPane sc = new JScrollPane();
			sc.getViewport().add(comp);
			f.getContentPane().add(sc, BorderLayout.CENTER);

			f.setSize(400, 400);
			f.setVisible(true);
			f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static BookList createList() {
		BookList list1 = new BookList("야와라");

		Book b1 = new Book(1681, "야와라 1");
		b1.setBorrower(new Borrower("AAA", null));
		list1.add(b1);

		Book b2 = new Book(1682, "야와라 2");
		b2.setBorrower(new Borrower("DEF", null));
		list1.add(b2);

		list1.add(new Book("야와라 69"));
		list1.add(new Book("유리가면 1"));
		list1.add(new Book("유리가면 2"));
		list1.add(new Book("이기적인 유전자"));

		return list1;
	}
}
