package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.SwingUtilities;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfConstraints;

public class BookBackUI extends BookUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9125242659977838075L;
	private FontMetrics fm;
	private Image bufferImg;

	@Override
	public Dimension getPreferredSize(BookView bookView) {
		return new Dimension(18, 110);
	}

	@Override
	public Dimension getSize(BookView bookView) {
		return getPreferredSize(bookView);
	}

	@Override
	public void paintComponent(Graphics g, BookView bookView) {
		if (bufferImg == null) {
			drawBackbuffer(bookView);
		}

		g.drawImage(bufferImg, 0, 0, null);
	}

	private void drawBackbuffer(BookView bookView) {
		bufferImg = new BufferedImage(18, 100, BufferedImage.TYPE_INT_ARGB);//bookView.createImage(18, 110);
		int x = 1;
		int seriNoYDelta = 2;
		Book book = bookView.getBook();

		Graphics2D imgG = (Graphics2D) bufferImg.getGraphics();

		if (fm == null) {
			Font font = imgG.getFont().deriveFont(9f);
			imgG.setFont(font);
			fm = Toolkit.getDefaultToolkit().getFontMetrics(font);
		}

		int sy = BookShelfConstraints.getDropGapHeight();
		imgG.setColor(new Color(244, 244, 230));
		imgG.fillRect(x, sy, BookShelfConstraints.getBackWidth(), BookShelfConstraints.getHeight());
		imgG.drawImage(backMaterialShadow, x, sy, BookShelfConstraints.getBackWidth(),
				BookShelfConstraints.getHeight(), null);
		if (book.getBorrower() != null) {
			imgG.setColor(new Color(255, 0, 0, 200));
			imgG.fillRect(x, sy + 10, BookShelfConstraints.getBackWidth(), 15);

			imgG.setColor(Color.black);
			imgG.drawRect(x, sy + 10, BookShelfConstraints.getBackWidth(), 15);
		}

		String str = String.valueOf(book.getSerialNo());
		int strWidth = SwingUtilities.computeStringWidth(fm, str);
		int strX = x + (BookShelfConstraints.getBackWeight() - 2 - strWidth) / 2 + 1;
		imgG.setColor(Color.white);
		imgG.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		imgG.fillArc(strX - 2, BookShelfConstraints.getHeight() - seriNoYDelta - 10, strWidth + 2, 11, 0, 360);
		imgG.setColor(Color.BLACK);
		imgG.drawString(str, strX, BookShelfConstraints.getHeight() - seriNoYDelta - 1);
	}

}
