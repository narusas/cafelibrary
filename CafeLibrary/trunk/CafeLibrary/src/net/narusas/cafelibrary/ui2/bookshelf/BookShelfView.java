package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfConstraints;

public class BookShelfView extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6694144863899370789L;
	private Image bg;

	public BookShelfView() {
		super();
		bg = Toolkit.getDefaultToolkit().createImage("images/wood.jpg");
		loadImage(bg);
		setLayout(new BookShelfLayout(15, 15, 150));
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

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		final JScrollPane sp = new JScrollPane();

		final BookShelfView bsv = new BookShelfView();

		int count = 2000;
		Random random = new Random();
		while (count > 0) {
			BookGroupView bg = new BookGroupView();
			int c = random.nextInt(15);
			bg.getTitle().setText("Rapid");
			for (int i = 0; i < c; i++) {
				if (random.nextBoolean()) {
					bg.add(new BookView(new Book(788 + i, "ABC 1"), BookView.Show.Back));
				} else {
					bg.add(new BookView(new Book(788 + i, "ABC 1"), BookView.Show.Cover));
				}

			}
			bsv.add(bg);
			count -= c;
		}

		bsv.setPreferredSize(new Dimension(300, 25000));
		bsv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				bsv.removeAll();
				System.out.println("##");
				int count = 2000;
				Random random = new Random();
				while (count > 0) {
					BookGroupView bg = new BookGroupView();
					int c = random.nextInt(15);
					bg.getTitle().setText("Rapid");
					for (int i = 0; i < c; i++) {
						if (random.nextBoolean()) {
							bg.add(new BookView(new Book(788 + i, "ABC 1"), BookView.Show.Back));
						} else {
							bg.add(new BookView(new Book(788 + i, "ABC 1"), BookView.Show.Cover));
						}

					}
					bsv.add(bg);
					count -= c;
				}
				bsv.revalidate();
				bsv.repaint();
			}
		});
		sp.addComponentListener(new ComponentListener(){

			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void componentResized(ComponentEvent e) {
				System.out.println(e);
				sp.revalidate();
			}

			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}});
		sp.getViewport().add(bsv);
		f.getContentPane().add(sp, BorderLayout.CENTER);
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
