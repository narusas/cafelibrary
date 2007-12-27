package net.narusas.ui.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GridentButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -883883777968442822L;
	Color[][] colorSet = new Color[][] { { new Color(235, 235, 235), new Color(120, 120, 120) },
			{ new Color(120, 120, 120), new Color(235, 235, 235) } };
	private int upColor = 0;
	private int downColor = 1;

	public GridentButton(String label) {
		super(label);
		setContentAreaFilled(false);
	}

	public GridentButton() {
		super("");
		setContentAreaFilled(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			// You might want to make the highlight color
			// a property of the RoundButton class.
			g.setColor(Color.lightGray);
		} else {
			g.setColor(getBackground());
		}
		int arc = 0;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Font font = g.getFont();
		font = font.deriveFont(Font.PLAIN, 12);
		arc = 5;
		String text = getText();
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D stringBounds = metrics.getStringBounds(text, g);

		Dimension size = getSize();
		int h = size.height;
		int w = size.width;

		// Create the path that runs through the rounded rectangle
		float h2 = h / 2;
		float h4 = h / 4;

		GeneralPath path = new GeneralPath();
		path.moveTo(0, h);
		path.curveTo(0, h - h4, h4, h2, h2, h2);
		path.lineTo(w - h2, h2);
		path.curveTo(w - h4, h2, w, h4, w, 0);
		path.lineTo(w, h);

		// Create a buffered image to paint the rounded rectangle
		BufferedImage vBuffer = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D bg2d = vBuffer.createGraphics();
		bg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Paint the background
		RoundRectangle2D fillArea = new RoundRectangle2D.Double(0.0D, 0.0D, w, h, arc, arc);
		bg2d.setClip(fillArea);

		if (getModel().isArmed()) {
			upColor = 1;
			downColor = 0;
		} else {
			upColor = 0;
			downColor = 1;
		}
		Color vStartColor = getUpColor(0);
		Color vEndColor = getUpColor(1);

		java.awt.Paint p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h, vEndColor);
		bg2d.setPaint(p);

		bg2d.fill(fillArea);

		Composite composite = g2d.getComposite();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .9f));
		Shape clip = g2d.getClip();
		g2d.setClip(fillArea);
		g2d.drawImage(vBuffer, 0, 0, null);
		g2d.setClip(clip);
		g2d.setComposite(composite);

		// Paint a border around the background since it was clipped and the
		// edges
		// weren't anti-aliased
		Color vWrapColor = new Color(60, 60, 60);
		g2d.setColor(vWrapColor);
		g2d.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

		ImageIcon icon = (ImageIcon) getIcon();
		if (icon != null) {
			Image img = icon.getImage();

			int imgH = img.getHeight(null);
			int x = (w - img.getWidth(null)) / 2;
			int y = (h - img.getHeight(null)) / 2;
//			if (y < 0) {
//				g.drawImage(img, 7, 3, img.getWidth(null), h - 4, null);
//			} else {
				g.drawImage(img, x, y, null);
//			}
		}

		int x = (size.width - w) / 2;
		int y = (size.height - h) / 2;
		// Figure out where to draw the text
		x = (w - (int) stringBounds.getWidth()) / 2;
		y = (h / 2) + ((metrics.getAscent() - metrics.getDescent()) / 2);

		// Draw a shadwo
		g2d.setColor(new Color(0, 0, 0, 70));
		g2d.drawString(text, x + 2, y + 2);

		// Draw the text
		g2d.setColor(Color.WHITE);
		g2d.drawString(text, x, y);
	}

	private Color getUpColor(int i) {
		return colorSet[upColor][i];
	}

	private Color getDownColor(int i) {
		return colorSet[downColor][i];
	}

	@Override
	protected void paintBorder(Graphics g) {
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		JButton b = new GridentButton("ABC");
		// b.setIcon(new ImageIcon("images/add_book.png"));
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(b);
		f.getContentPane().add(new JButton("DEF"));
		f.setSize(100, 100);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
}
