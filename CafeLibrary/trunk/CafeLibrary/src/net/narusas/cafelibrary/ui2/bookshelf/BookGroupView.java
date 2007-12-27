package net.narusas.cafelibrary.ui2.bookshelf;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import net.narusas.cafelibrary.Book;

public class BookGroupView extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363042950245277940L;
	private JTextArea title;

	public BookGroupView() {
		super();
		setLayout(new BookGroupLaytout());
		setOpaque(false);
		title = new JTextArea();
		title.setLineWrap(true);
		title.setEditable(false);
		title.setBorder(new LineBorder(new Color(60, 60, 60), 1));

		add(title);
	}

	@Override
	public void setBounds(int x, int y, int width, int height) {
		// System.out.println(getParent());
		// System.out.println("setBound:
		// x="+x+",y="+y+",w="+width+",h="+height);
		super.setBounds(x, y, width, height);
	}

	@Override
	public Dimension getPreferredSize() {
		System.out.println(getParent());
		Container p = getParent();
		Dimension size = super.getPreferredSize();
		if (size.width < p.getWidth()) {
			Dimension res = new Dimension(p.getWidth(), size.height);
			return res;
		} else {
			return size;
		}
	}

	@Override
	public void setSize(int width, int height) {
		System.out.println(width + ":" + height);
		// TODO Auto-generated method stub
		super.setSize(width, height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(g.getClipRect());
		g.setColor(Color.RED);
		g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
	}

	public JTextArea getTitle() {
		return title;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		// f.getContentPane().setBackground(Color.BLUE);
		f.getContentPane().setLayout(new BorderLayout());
		BookGroupView bg = new BookGroupView();
		bg.getTitle().setText("Rapid");
		bg.add(new BookView(new Book(800, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(801, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(802, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(803, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(804, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(800, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(801, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(802, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(803, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(804, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(800, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(801, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(802, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(803, "ABC 1"), BookView.Show.Back));
		bg.add(new BookView(new Book(804, "ABC 1"), BookView.Show.Back));

		bg.add(new BookView(new Book(805, "ABC 1"), BookView.Show.EmptyBack));
		bg.add(new BookView(new Book(806, "ABC 1")));
		bg.add(new BookView(new Book(799, "ABC 1"), BookView.Show.SelectedCover));

		f.getContentPane().add(bg,BorderLayout.CENTER);
		f.setSize(500, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
