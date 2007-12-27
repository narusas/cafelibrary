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
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.Serializable;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicButtonUI;

public class GlassButtonUI extends BasicButtonUI implements MouseListener, Serializable, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5952931061165534592L;
	private final static GlassButtonUI ui = new GlassButtonUI();
	public static float[] BLUR = { 0.10f, 0.10f, 0.10f, 0.10f, 0.30f, 0.10f, 0.10f, 0.10f, 0.10f };
	public static ConvolveOp blurOp = new ConvolveOp(new Kernel(3, 3, BLUR));
	Color[][] colorSet = new Color[][] { { new Color(10, 0, 40), new Color(175, 165, 225) },
			{ new Color(5, 0, 50), new Color(105, 100, 155) } };
	private int upColor = 0;
	private int downColor = 1;

	public static BasicButtonUI createUI(JComponent c) {
		return ui;
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.addMouseListener(this);

	}

	@Override
	public void uninstallUI(JComponent c) {
		super.uninstallUI(c);
		c.removeMouseListener(this);
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		AbstractButton b = (AbstractButton) c;
		int arc = 0;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		Font font = g.getFont();
		font = font.deriveFont(Font.PLAIN, 12);
		arc = 3;
		String text = b.getText();
		g.setFont(font);
		FontMetrics metrics = g.getFontMetrics();
		Rectangle2D stringBounds = metrics.getStringBounds(text, g);

		Dimension size = c.getSize();
		int h = size.height;
		int w = size.width;

		Icon icon = b.getIcon();
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

		Area area = new Area(fillArea);
		area.subtract(new Area(path));

		Color vStartColor = getUpColor(0);
		Color vEndColor = getUpColor(1);

		java.awt.Paint p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h, vEndColor);
		bg2d.setPaint(p);

		bg2d.fill(area);

		vStartColor = getDownColor(0);
		vEndColor = getDownColor(1);

		p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h, vEndColor);
		bg2d.setPaint(p);

		bg2d.fill(path);
		// Blur the background
		vBuffer = blurOp.filter(vBuffer, null);
		bg2d = vBuffer.createGraphics();

		// Figure out where to place the background and the text
		int insetX = (size.width - w) / 2;
		int insetY = (size.height - h) / 2;

		g2d.translate(insetX, insetY);

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
		Color vWrapColor = new Color(175, 165, 225);
		g2d.setColor(vWrapColor);
		g2d.drawRoundRect(0, 0, w, h, arc, arc);

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

		g2d.translate(-insetX, -insetY);
	}

	private Color getUpColor(int i) {
		return colorSet[upColor][i];
	}

	private Color getDownColor(int i) {
		return colorSet[downColor][i];
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {
		upColor = 1;
		downColor = 0;
		JComponent c = (JComponent) e.getSource();
		c.repaint();
	}

	public void mouseReleased(MouseEvent e) {
		upColor = 0;
		downColor = 1;
		JComponent c = (JComponent) e.getSource();
		c.repaint();
	}

	public void keyPressed(KeyEvent e) {

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		JButton b = new JButton("ABC");
		b.setUI(GlassButtonUI.createUI(b));
		GlassButtonUI.createUI(b).installUI(b);
		f.getContentPane().setLayout(new FlowLayout());
		f.getContentPane().add(b);
		f.getContentPane().add(new JButton("DEF"));
		f.setSize(100, 100);
		f.setVisible(true);
	}
}
