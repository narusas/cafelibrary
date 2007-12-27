package net.narusas.cafelibrary.ui2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import net.narusas.cafelibrary.BookList;

public class BookCountCellRenderer extends JLabel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4090144578035613476L;
	static Border noFocusBorder;
	FontMetrics fm = null;
	Insets insets = new Insets(0, 0, 0, 0);
	private BookList bookList;

	Icon headerIcon;

	public BookCountCellRenderer() {
		super();
		initIcon();
		noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		setOpaque(true);
		setBorder(noFocusBorder);
	}

	protected void initIcon() {
		headerIcon = new ImageIcon("images/booklist.png");
	}

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {
		JList.DropLocation dropLocation = list.getDropLocation();
		bookList = (BookList) value;
		if (dropLocation != null && !dropLocation.isInsert() && dropLocation.getIndex() == index) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
			setBorder(noFocusBorder);
		} else {
			setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
			setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
			setFont(list.getFont());
			setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);
		}
		return this;
	}

	
	public Dimension getPreferredSize() {
		return new Dimension(30, 40);
	}

	
	public void paint(Graphics g) {
		fm = g.getFontMetrics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());

		headerIcon.paintIcon(this, g, 0, 5);

		if (bookList == null) {
			return;
		}
		g.setColor(getForeground());
		g.setFont(getFont());

		insets = getInsets();
		int x = 26;
		int y = insets.top + fm.getAscent() + 8;
		String title = bookList.getName();
		String count = String.valueOf(bookList.getBookSize());

		g.drawString(title, x, y);

		x = getWidth() - fm.stringWidth(count) - 5;

		g.setColor(Color.WHITE);
		drawCircle(g, x, count);

		g.setColor(new Color(100, 100, 100, 100));
		drawCircle(g, x, count);

		g.setColor(getForeground());
		g.drawString(count, x, y);
	}

	private void drawCircle(Graphics g, int x, String count) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.fillArc(x - 6, 8, fm.stringWidth(count) + 11, getHeight() - 20, 0, 360);
	}

}

