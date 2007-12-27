package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.geom.Rectangle2D;

import javax.swing.JTextArea;

public class BookGroupLaytout implements LayoutManager {

	public void addLayoutComponent(String name, Component comp) {

	}

	public void layoutContainer(Container parent) {
		int maxY = 0;
		int nextXPos = 0;
		
		Dimension preferredSize = parent.getPreferredSize();

		// 0번은 Title Text Area임.
		for (int i = 1; i < parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			Dimension size = c.getPreferredSize();
			if (nextXPos >= preferredSize.width){
				continue;
			}
			c.setBounds(nextXPos, 0, size.width, size.height);
			nextXPos += c.getPreferredSize().getWidth();
			maxY = Math.max(maxY, (int) c.getPreferredSize().getHeight());
		}

		JTextArea title = (JTextArea) parent.getComponent(0);
		Font font = title.getFont();
		FontMetrics fm = title.getFontMetrics(font);
		String text = title.getText();
		if (text == null) {
			text = "";
		}
		Rectangle2D size = fm.getStringBounds(text, parent.getGraphics());

		int width = nextXPos;
		int titleWidth = Math.min(width, (int) size.getWidth())+5;

		int gap = width - titleWidth;
		if (gap != 0) {
			gap = gap / 2;
		}

		int x = gap;
		title.setSize(titleWidth, 30);
		title.setBounds(x, maxY, titleWidth, title.getPreferredSize().height);
	}

	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	public Dimension preferredLayoutSize(Container parent) {
		int maxY = 0;
		int nextXPos = 0;

		// 0번은 Title Text Area임.
		for (int i = 1; i < parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			nextXPos += c.getPreferredSize().getWidth();
			maxY = Math.max(maxY, (int) c.getPreferredSize().getHeight());
		}

		Component title = parent.getComponent(0);
		int width = nextXPos;
		int titleWidth = Math.min(width, title.getSize().width);

		int gap = width - titleWidth;
		if (gap != 0) {
			gap = gap / 2;
		}

		int x = gap;
		title.setSize(titleWidth, title.getSize().height);
		int height = (int) title.getSize().getHeight();
		title.setSize(width, 30);
		return new Dimension(width, maxY + title.getPreferredSize().height + 5);
	}

	public void removeLayoutComponent(Component comp) {

	}

}
