package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfConstraints;

public class BookCoverUI extends BookUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8411531626017340318L;

	private BookView bookView;

	protected FontMetrics fm;

	private Image bufferImg;

	public BookCoverUI() {
	}

	@Override
	public Dimension getPreferredSize(BookView bookView) {
		return new Dimension(80, 110);
	}

	@Override
	public Dimension getSize(BookView bookView) {
		return getPreferredSize(bookView);
	}

	@Override
	public void paintComponent(Graphics g, final BookView bookView) {
		drawBackbuffer(bookView);

		g.drawImage(bufferImg, 0, 0, null);

		
	}

	private void drawBackbuffer(final BookView bookView) {
		if (bufferImg != null){
			return;
		}
		bufferImg = new BufferedImage(80, 100, BufferedImage.TYPE_INT_ARGB);
		this.bookView = bookView;
		int x = 5;
		int seriNoYDelta = 2;

		Graphics2D imgG = (Graphics2D) bufferImg.getGraphics();

		if (fm == null) {
			Font font = imgG.getFont().deriveFont(9f);
			imgG.setFont(font);
			fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
		}

		Book book = bookView.getBook();
		Image img = book.getCoverImage();
		imgG.drawImage(coverDropShadow, x - BookShelfConstraints.getDropGapWidth(), 5, BookShelfConstraints
				.getDropWidth(), BookShelfConstraints.getDropHeight(), null);
		imgG.drawImage(img, x, BookShelfConstraints.getDropGapHeight(), BookShelfConstraints.getWidth(),
				BookShelfConstraints.getHeight(), new ImageObserver() {
					public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
						if ((infoflags & ImageObserver.ALLBITS) != 0) {
							bufferImg = null;
							bookView.repaint();
							return false;
						}
						return true;
					}
				});

		imgG.drawImage(coverMaterialShadow, x, BookShelfConstraints.getDropGapHeight(),
				BookShelfConstraints.getWidth(), BookShelfConstraints.getHeight(), null);

		if (book.getBorrower() != null) {
			int sx = x + BookShelfConstraints.getWidth();
			int sy = BookShelfConstraints.getDropGapHeight();
			int[] xPoints = new int[] { sx - 25, sx - 10, sx, sx };
			int[] yPoints = new int[] { sy, sy, sy + 10, sy + 25 };
			imgG.setColor(new Color(255, 0, 0, 200));
			imgG.fillPolygon(xPoints, yPoints, 4);
		}

		drawSerialNo(x, seriNoYDelta, imgG, fm, book);
	}
}
