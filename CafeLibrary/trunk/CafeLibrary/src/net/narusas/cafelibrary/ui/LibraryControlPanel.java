package net.narusas.cafelibrary.ui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

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
public class LibraryControlPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8418338725303476937L;
	private JButton addBookListButton;
	private JButton addBorrowerButton;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new LibraryControlPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public LibraryControlPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			this.setPreferredSize(new java.awt.Dimension(194, 41));
			this.setLayout(thisLayout);
			add(getAddBookListButton());
			add(getAddBorrowerButton());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getAddBookListButton() {
		if (addBookListButton == null) {
			addBookListButton = new JButton();
			addBookListButton.setPreferredSize(new java.awt.Dimension(30, 30));
			addBookListButton.setIcon(new ImageIcon("images/add_booklist.png"));
			addBookListButton.setToolTipText("Add Book list");
		}
		return addBookListButton;
	}

	public JButton getAddBorrowerButton() {
		if (addBorrowerButton == null) {
			addBorrowerButton = new JButton();
			addBorrowerButton.setPreferredSize(new java.awt.Dimension(30, 30));
			addBorrowerButton.setIcon(new ImageIcon("images/add_borrower.png"));
			addBorrowerButton.setToolTipText("Add Borrower");
		}
		return addBorrowerButton;
	}

}
