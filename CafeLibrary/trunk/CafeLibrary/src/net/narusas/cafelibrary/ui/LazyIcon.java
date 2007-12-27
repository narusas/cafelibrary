package net.narusas.cafelibrary.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import net.narusas.cafelibrary.Book;
import net.narusas.util.lang.NFile;

public class LazyIcon implements Icon {
	private Icon icon;
	private String fileName;

	public LazyIcon(String fileName) {
		this.fileName = fileName;
	}

	public LazyIcon(Book book) {
		this.fileName = "data/" + book.getId() + "S.jpg";
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		getIcon().paintIcon(c, g, x, y);
	}

	public int getIconWidth() {
		return getIcon().getIconWidth();
	}

	public int getIconHeight() {
		return getIcon().getIconHeight();
	}

	public Icon getIcon() {
//		if (icon == null) {
			try {
				File f = new File(fileName);
				if (f.exists()) {
					byte[] data = NFile.readBytes(f);
					Image img = Toolkit.getDefaultToolkit().createImage(data);
					icon = new ImageIcon(img);
				} else {
					BufferedImage temp = new BufferedImage(40, 55, BufferedImage.TYPE_INT_RGB);
					temp.getGraphics().setColor(Color.WHITE);
					temp.getGraphics().fillRect(0, 0, 40, 55);
					temp.getGraphics().setColor(Color.BLACK);
					temp.getGraphics().drawLine(0, 0, 40, 55);
					temp.getGraphics().drawLine(40, 0, 0, 55);
					icon = new ImageIcon(temp);
				}

			} catch (Throwable e) {
				throw new RuntimeException("Failed to load icon " + fileName, e);
			}
//		}
		return icon;
	}

	public static ImageIcon resolve(Icon icon) {
		if (icon instanceof LazyIcon)
			icon = ((LazyIcon) icon).getIcon();
		return (ImageIcon) icon;
	}
}
