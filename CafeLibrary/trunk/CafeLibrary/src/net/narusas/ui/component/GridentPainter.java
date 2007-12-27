package net.narusas.ui.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;

public class GridentPainter {
	Color[][] colorSet = new Color[][] { { new Color(235, 235, 235), new Color(120, 120, 120) },
			{ new Color(120, 120, 120), new Color(235, 235, 235) } };

	public enum PaintDirection {
		UP, DOWN
	};

	public void paintRect(Graphics g, int x, int y, int w, int h, PaintDirection dir) {
		int arc = 5;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

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

	}
}
