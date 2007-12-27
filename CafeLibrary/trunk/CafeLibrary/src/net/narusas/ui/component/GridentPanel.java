package net.narusas.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

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
public class GridentPanel extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8063112173537918957L;
	private final int highLightHeight;
	private Color vStartColor;
	private Color vEndColor;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (Exception e) {
		}

		JFrame frame = new JFrame();
		GridentPanel p = new GridentPanel();
		p.add(new JLabel("ABD"));
		frame.getContentPane().add(p);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public GridentPanel(int highLightHeight) {
		super();
		this.highLightHeight = highLightHeight;
		initGUI();
	}

	public GridentPanel() {
		this(0);
	}

	private void initGUI() {
		vStartColor = new Color(235, 235, 235);
		vEndColor = new Color(120, 120, 120);
		try {
			this.setPreferredSize(new java.awt.Dimension(224, 300));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Dimension size = getSize();
		int h = size.height;
		int w = size.width;
		int arc = 0;

		java.awt.Paint p = new GradientPaint(0.0F, 0.0F, vStartColor, 0.0F, h, vEndColor);
		g2d.setPaint(p);

		RoundRectangle2D fillArea = new RoundRectangle2D.Double(0.0D, 0.0D, w, h, arc, arc);
		g2d.setClip(fillArea);
		Area area = new Area(fillArea);

		g2d.fill(area);

		if (highLightHeight > 0) {
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, w, highLightHeight);
		}
	}

	public void useDarkerColors() {
		vStartColor = new Color(180, 180, 180);
	}
}
