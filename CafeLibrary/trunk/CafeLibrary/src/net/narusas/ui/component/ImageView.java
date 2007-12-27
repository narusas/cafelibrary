package net.narusas.ui.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class ImageView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6349768202305090923L;
	private Image img;

	public ImageView() {

	}

	public ImageView(Image img, int w, int h) {
		this.img = img;
		super.setPreferredSize(new Dimension(w, h));
	}

	public void setImage(Image img) {
		this.img = img;
		repaint();
	}

	public void setImage(Image img, int w, int h) {
		this.img = img;
		super.setPreferredSize(new Dimension(w, h));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img == null) {
			return;
		}
		int w = getPreferredSize().width;
		int h = getPreferredSize().height;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, w, h, new ImageObserver() {
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				if ((infoflags & ImageObserver.ABORT) != 0) {
					return true;
				}
				repaint();
				return true;
			}
		});
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new FlowLayout());
		ImageView img = new ImageView(Toolkit.getDefaultToolkit().getImage("images/noCover.jpg"), 70, 100);
		img.setBorder(new LineBorder(Color.BLACK, 1));
		f.getContentPane().add(img);
		f.setSize(200, 200);
		f.setVisible(true);

	}
}
