package net.narusas.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class GridentScrollBarUI extends BasicScrollBarUI {
	final static GridentScrollBarUI ui = new GridentScrollBarUI();

	public static GridentScrollBarUI createUI(JComponent c) {
		return ui;
	}

	@Override
	protected void installComponents() {
		super.installComponents();
	}

	@Override
	protected void uninstallComponents() {
		super.uninstallComponents();
	}

//	@Override
//	public void paint(Graphics g, JComponent c) {
//		super.paint(g, c);
//		g.setColor(Color.RED);
//		g.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);
//	}

	@Override
	protected void paintDecreaseHighlight(Graphics g) {
		super.paintDecreaseHighlight(g);
	}

	@Override
	protected void paintIncreaseHighlight(Graphics g) {
		super.paintIncreaseHighlight(g);
	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		// super.paintThumb(g, c, thumbBounds);
		g.setColor(Color.BLUE);
		g.fillRect(thumbBounds.x, thumbBounds.y, (int) thumbBounds.getWidth() - 1, (int) thumbBounds.getHeight() - 1);
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		super.paintTrack(g, c, trackBounds);

		g.setColor(Color.YELLOW);
		g.drawRect(trackBounds.x, trackBounds.y, (int) trackBounds.getWidth() - 1, (int) trackBounds.getHeight() - 1);
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		// JButton btn = super.createDecreaseButton(orientation);

		return new JButton("O:" + orientation);
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return super.createIncreaseButton(orientation);
	}

	public static void main(String[] args) {
		UIManager.put("ScrollBarUI", "net.narusas.cafelibrary.ui2.GridentScrollBarUI");
		JFrame f = new JFrame();
		JScrollPane sp = new JScrollPane();

		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(1000, 1000));
		p.add(new JLabel("ABD"));
		sp.getViewport().add(p);
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(sp, BorderLayout.CENTER);

		f.setSize(100, 100);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setVisible(true);
	}
}
