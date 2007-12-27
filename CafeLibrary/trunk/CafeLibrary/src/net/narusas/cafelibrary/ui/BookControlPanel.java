package net.narusas.cafelibrary.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;

import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.JFrame;

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
public class BookControlPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4644681670723332669L;
	private JButton editButton;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BookControlPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BookControlPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			FlowLayout thisLayout = new FlowLayout();
			thisLayout.setAlignment(FlowLayout.LEFT);
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(113, 40));
			{
				editButton = new JButton();
				this.add(editButton);
				editButton.setIcon(new ImageIcon("images/write.png"));
				editButton.setPreferredSize(new Dimension(32, 32));
				editButton.setToolTipText("Edit book detail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getEditButton() {
		return editButton;
	}

}
