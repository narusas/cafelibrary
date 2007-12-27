package net.narusas.cafelibrary.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.ui2.BookCountCellRenderer;
import net.narusas.cafelibrary.ui2.LibraryListModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class LibraryList extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4134535525180560877L;
	private JScrollPane jScrollPane1;
	private JList bookListsList;
	private LibraryListModel listModel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		} catch (Exception e) {
		}
		final Library lib = new Library();
		final BookList b1 = new BookList("Book List 1") {
			
			public int getBookSize() {
				return 9999;
			}
		};

		lib.add(b1);
		lib.add(new BookList("Book List 2232323232323323232323232323232"));
		lib.add(new BookList("Book List 3"));
		lib.add(new BookList("Book List 4"));
		lib.add(new BookList("Book List 5"));
		lib.add(new BookList("Book List 6"));
		lib.add(new BookList("Book List 7"));
		lib.add(new BookList("Book List 8"));
		lib.add(new BookList("Book List 9"));
		lib.add(new BookList("Book List 10"));
		lib.add(new BookList("Book List 11"));
		lib.add(new BookList("Book List 12"));
		lib.add(new BookList("Book List 13"));

		JFrame frame = new JFrame();
		LibraryList bList = new LibraryList();
		bList.getListModel().setLibrary(lib);

		frame.getContentPane().add(bList);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		new Thread() {
			
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lib.add(new BookList("Book List 4"));

				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lib.remove(b1);
			}
		}.start();
	}

	public LibraryList() {
		super();
		initGUI();
	}

	public void setTitle(String title) {
		this.setBorder(BorderFactory.createTitledBorder(title));
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(130, 204));
			this.setBorder(BorderFactory.createTitledBorder("Untitled"));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, BorderLayout.CENTER);
				jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(130, 320));
				{
					listModel = new LibraryListModel();
					bookListsList = new JList();
					jScrollPane1.setViewportView(bookListsList);
					bookListsList.setModel(listModel);
					bookListsList.setPreferredSize(new java.awt.Dimension(107, 178));
					bookListsList.setCellRenderer(new BookCountCellRenderer());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JList getBookListsList() {
		return bookListsList;
	}

	public LibraryListModel getListModel() {
		return listModel;
	}



	public void setListModel(LibraryListModel listModel) {
		this.listModel = listModel;
		bookListsList.setModel(listModel);
	}

}
