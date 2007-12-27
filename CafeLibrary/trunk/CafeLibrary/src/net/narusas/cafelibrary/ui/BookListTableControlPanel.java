package net.narusas.cafelibrary.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
public class BookListTableControlPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5614663120175372698L;
	private JButton returnBookButton;
	private JTextField searchTextField;
	private JButton deteteBookButton;
	private JButton changeShowMethodButton;
	private JButton publishButton;
	private JButton clearButton;
	private JButton addBookButton;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BookListTableControlPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BookListTableControlPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0};
			thisLayout.rowHeights = new int[] { 7 };
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 20, 7, 7, 20, 20};
			this.setPreferredSize(new java.awt.Dimension(575, 40));
			this.setLayout(thisLayout);
			{
				addBookButton = new JButton();
				this.add(addBookButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 15, 0, 5), 0, 0));
				addBookButton.setIcon(new ImageIcon("images/add_book.png"));
				addBookButton.setPreferredSize(new java.awt.Dimension(30, 30));
				addBookButton.setToolTipText("책 추가");
			}
			{
				returnBookButton = new JButton();
				this.add(returnBookButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				returnBookButton.setPreferredSize(new java.awt.Dimension(30, 30));
				returnBookButton.setIcon(new ImageIcon("images/returnBook.png"));
				returnBookButton.setToolTipText("선택된 책을 반환합니다.");
			}
			{
				deteteBookButton = new JButton();
				this.add(deteteBookButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
				deteteBookButton.setPreferredSize(new Dimension(30,30));
				deteteBookButton.setIcon(new ImageIcon("images/deteteBook.png"));
				deteteBookButton.setText("");
				deteteBookButton.setToolTipText("선택된 책을 목록에서 제거합니다");
			}
			{
				searchTextField = new JTextField();
				this.add(searchTextField, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 20, 0, 0), 0, 0));
				searchTextField.setText("SEARCH");
				searchTextField.setColumns(20);
			}
			{
				clearButton = new JButton();
				this.add(clearButton, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				clearButton.setText("X");
				clearButton.setToolTipText("검색란을 정리합니다. ");
			}
			{
				publishButton = new JButton();
				this.add(publishButton, new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				publishButton.setText("FTP");
			}
			{
				changeShowMethodButton = new JButton();
				this.add(changeShowMethodButton, new GridBagConstraints(6, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				changeShowMethodButton.setIcon(new ImageIcon("images/view_as_table.png"));
				changeShowMethodButton.setPreferredSize(new Dimension(32, 32));
				changeShowMethodButton.setToolTipText("보는 방식을 변경합니다");
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getAddBookButton() {
		return addBookButton;
	}

	public JButton getReturnBookButton() {
		return returnBookButton;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public JButton getPublishButton() {
		return publishButton;
	}

	public JButton getChangeShowMethodButton() {
		return changeShowMethodButton;
	}

	public JButton getDeteteBookButton() {
		return deteteBookButton;
	}

}
