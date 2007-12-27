package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import net.narusas.cafelibrary.Book;

public class BookView extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2285057156675963663L;
	private final Book book;
	private Show showStatus;
	private BookUI ui;

	public static enum Show {
		Cover, SelectedCover, Back, EmptyBack
	};

	public BookView(Book book) {
		this(book, Show.Cover);
		setOpaque(false);
	}

	public BookView(Book book, Show show) {
		this.book = book;
		setShowStatus(show);
	}

	public Show getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Show showStatus) {
		this.showStatus = showStatus;
		ui = BookUI.get(showStatus);
	}

	@Override
	public Dimension getPreferredSize() {
		return ui.getPreferredSize(this);
	}

	@Override
	public Dimension getSize() {
		return ui.getSize(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ui.paintComponent(g, this);
//		g.setColor(new Color(255, 0, 0, 100));
//		Dimension size = getSize();
//		g.drawRect(0, 0, size.width - 1, size.height - 1);
	}

	public Book getBook() {
		return book;
	}

}
