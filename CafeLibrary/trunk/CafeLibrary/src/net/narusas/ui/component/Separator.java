package net.narusas.ui.component;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Separator extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9027755786338969661L;

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(60, 60, 60));
		g.drawLine(15, 0, getSize().width - 15, 0);
	}

}
