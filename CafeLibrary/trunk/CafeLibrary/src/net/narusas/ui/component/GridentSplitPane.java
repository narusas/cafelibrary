package net.narusas.ui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class GridentSplitPane extends BasicSplitPaneUI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1384549481670790131L;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLUE);
		GridentPanel cp = new GridentPanel();
		cp.setLayout(new BorderLayout());
		JSplitPane sp = new JSplitPane();
		sp.setOpaque(false);
		sp.setBorder(new EmptyBorder(20, 20, 20, 20));
		GridentSplitPane ui = new GridentSplitPane();
		sp.setUI(ui);
		{
			JPanel jPanel1 = new JPanel();
			sp.add(jPanel1, JSplitPane.RIGHT);
			jPanel1.add(new JLabel("ABC"));

		}
		{
			JPanel jPanel2 = new JPanel();
			sp.add(jPanel2, JSplitPane.LEFT);
			jPanel2.add(new JLabel("DEF"));
		}

		cp.add(sp, BorderLayout.CENTER);
		frame.getContentPane().add(cp);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(200, 200);

		// frame.pack();
		frame.setVisible(true);
	}

	@Override
	public BasicSplitPaneDivider createDefaultDivider() {
		return new Divider(this);
	}
}

class Divider extends BasicSplitPaneDivider {

	private ImageIcon icon;
	private final BasicSplitPaneUI ui;

	public Divider(BasicSplitPaneUI ui) {
		super(ui);
		this.ui = ui;
		icon = new ImageIcon("images/dot.png");
	}

	@Override
	public void paint(Graphics g) {
		if (ui.getOrientation() == JSplitPane.HORIZONTAL_SPLIT) {
			int y = (getHeight() - icon.getImage().getHeight(null)) / 2;
			g.drawImage(icon.getImage(), 0, y, null);
		} else {
			int x = (getWidth() - icon.getImage().getWidth(null)) / 2;
			g.drawImage(icon.getImage(), x, 0, null);

		}
	}

}
