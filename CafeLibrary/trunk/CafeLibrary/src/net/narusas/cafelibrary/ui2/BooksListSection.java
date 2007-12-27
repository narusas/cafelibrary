package net.narusas.cafelibrary.ui2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import net.narusas.cafelibrary.Library;
import net.narusas.ui.component.GridentButton;
import net.narusas.ui.component.TitledPanel;

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
public class BooksListSection extends TitledPanel {
	private JScrollPane scrollPane;
	private JTextField filterTextField;
	private GridentButton changeViewButton;
	private GridentButton optionButton;
	private GridentButton clearFilterButton;
	private GridentButton deleteButton;
	private GridentButton addBookButton;
	private JPopupMenu popup;
	private Library lib;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BooksListSection());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public BooksListSection() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout optionPanelLayout = new GridBagLayout();
			{
				optionPanelLayout.rowWeights = new double[] { 0.1 };
				optionPanelLayout.rowHeights = new int[] { 7 };
				optionPanelLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1, 0.1, 0.0 };
				optionPanelLayout.columnWidths = new int[] { 7, 7, 20, 7, 20, 7 };
			}

			this.setPreferredSize(new java.awt.Dimension(426, 418));
			super.getOptionPanel().setLayout(optionPanelLayout);
			super.getTitlePanel().add(getChangeViewButton(), BorderLayout.EAST);
			{
				addBookButton = new GridentButton();
				super.getOptionPanel().add(
						addBookButton,
						new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
								GridBagConstraints.NONE, new Insets(0, 15, 0, 5), 0, 0));
				addBookButton.setIcon(new ImageIcon("images/add_book.png"));
				addBookButton.setPreferredSize(new Dimension(32, 20));
			}
			{
				deleteButton = new GridentButton();
				super.getOptionPanel().add(
						getDeleteButton(),
						new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
								GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				deleteButton.setIcon(new ImageIcon("images/delete.png"));
				deleteButton.setPreferredSize(new Dimension(32, 20));
			}
			{
				filterTextField = new JTextField();
				super.getOptionPanel().add(
						filterTextField,
						new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
								GridBagConstraints.HORIZONTAL, new Insets(0, 50, 0, 0), 0, 0));
				filterTextField.setText("SEARCH");
				filterTextField.setColumns(15);
				filterTextField.setBorder(new LineBorder(new java.awt.Color(60, 60, 60), 1, false));
			}
			{
				clearFilterButton = new GridentButton();
				super.getOptionPanel().add(
						clearFilterButton,
						new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
								new Insets(0, 0, 0, 0), 0, 0));
				clearFilterButton.setIcon(new ImageIcon("images/delete.png"));
				clearFilterButton.setPreferredSize(new Dimension(20, 20));
			}
			{
				optionButton = new GridentButton();
				super.getOptionPanel().add(
						optionButton,
						new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
								new Insets(0, 5, 0, 5), 0, 0));
				optionButton.setIcon(new ImageIcon("images/option.png"));
				optionButton.setPreferredSize(new Dimension(32, 20));
				optionButton.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent evt) {
						Component c = evt.getComponent();
						getPopupMenu().show(evt.getComponent(), c.getWidth(), 0);
					}

					public void mouseReleased(MouseEvent evt) {
						Component c = evt.getComponent();
						getPopupMenu().show(evt.getComponent(), c.getWidth(), 0);
					}
				});

			}
			{
				scrollPane = new JScrollPane();
				super.getContentPanel().add(scrollPane, BorderLayout.CENTER);
				scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				{
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GridentButton getAddBookButton() {
		return addBookButton;
	}

	public GridentButton getDeleteButton() {
		return deleteButton;
	}

	public JTextField getFilterTextField() {
		return filterTextField;
	}

	public GridentButton getClearFilterButton() {
		return clearFilterButton;
	}

	public GridentButton getOptionButton() {
		return optionButton;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JPopupMenu getPopupMenu() {
		if (popup == null) {
			popup = new JPopupMenu();
			popup.add(new JMenuItem("선택된 책 제거"));
			popup.add(new JMenuItem("FTP 출판"));
			popup.add(new JMenuItem("마지막 FTP 출판 반복하기"));
		}
		return popup;
	}

	public JButton getChangeViewButton() {
		if (changeViewButton == null) {
			changeViewButton = new GridentButton();
			changeViewButton.setIcon(new ImageIcon("images/view_as_thumbnail.png"));
			changeViewButton.setPreferredSize(new Dimension(18, 16));
		}
		return changeViewButton;
	}

	public void setLibrary(Library lib) {
		this.lib = lib;
	}
	
}
