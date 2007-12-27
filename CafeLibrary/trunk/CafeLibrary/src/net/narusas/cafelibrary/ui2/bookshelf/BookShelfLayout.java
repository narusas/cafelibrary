package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class BookShelfLayout implements LayoutManager2 {
	int vGap;
	int hGap;
	private final int rowHeight;

	public BookShelfLayout(int hGap, int vGap, int rowHeight) {
		this.hGap = hGap;
		this.vGap = vGap;
		this.rowHeight = rowHeight;
	}

	public void addLayoutComponent(String name, Component comp) {

	}

	public void layoutContainer(Container parent) {
//		System.out.println("P:"+parent);
		Container viewPort = parent.getParent();
		Dimension viewSize = null;

		if (viewPort != null) {
			System.out.println(viewPort.getParent());
			viewSize = viewPort.getParent().getSize();
		}
//		System.out.println(viewSize);

		int parentW = viewSize.width;
		int w = 15, h = 15;

		int maxW = parentW;

		for (int i = 0; i < parent.getComponentCount(); i++) {
			BookGroupView ui = (BookGroupView) parent.getComponent(i);
			Dimension size = ui.getPreferredSize();
			if (size.getWidth() > parentW) {
				parent.setPreferredSize(new Dimension((int) size.getWidth() + 10, parent.getHeight()));
			}
			if (w + size.getWidth() > maxW) {
				w = 15;
				h += rowHeight + vGap;
			}
			ui.setBounds(w, h, (int) size.getWidth(), (int) size.getHeight());
			w += size.getWidth() + hGap;
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		return preferredLayoutSize(parent);
	}

	public Dimension preferredLayoutSize(Container parent) {
//		Container viewPort = parent.getParent();
//		Dimension viewSize = null;
//
//		if (viewPort != null) {
//			System.out.println(viewPort.getParent());
//			viewSize = viewPort.getParent().getSize();
//		}
//		System.out.println(viewSize);
//
//		int parentW = viewSize.width;
//		int w = 15, h = 15;
//		int maxW = parentW;
//		for (int i = 0; i < parent.getComponentCount(); i++) {
//			Component ui = parent.getComponent(i);
//			Dimension size = ui.getPreferredSize();
//			w += size.getWidth() + hGap;
//			if (w + size.getWidth() > maxW) {
//				w = 0;
//				h += rowHeight + vGap;
//			}
//		}

		return new Dimension(300, 300);
	}

	private int getMaxWidth(Container parent) {
		int parentW = parent.getWidth();
		int maxW = parentW;
		for (int i = 0; i < parent.getComponentCount(); i++) {
			Component ui = parent.getComponent(i);
			Dimension size = ui.getPreferredSize();
			maxW = Math.max(maxW, size.width);
		}
		return maxW;
	}

	public void removeLayoutComponent(Component comp) {

	}

	public void addLayoutComponent(Component comp, Object constraints) {
		// TODO Auto-generated method stub
		
	}

	public float getLayoutAlignmentX(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getLayoutAlignmentY(Container target) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void invalidateLayout(Container target) {
		// TODO Auto-generated method stub
		
	}

	public Dimension maximumLayoutSize(Container target) {
		// TODO Auto-generated method stub
		return null;
	}

}
