package net.narusas.cafelibrary.ui2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;

import javax.swing.WindowConstants;

import net.narusas.ui.component.GridentButton;
import net.narusas.ui.component.ImageView;
import net.narusas.ui.component.Separator;
import net.narusas.ui.component.VerticalLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
public class SearchedBookListView extends javax.swing.JPanel {
	private JTextField search;
	private JButton searchButton;
	private ImageView icon;
	private Separator separator1;
	private JPanel contentPanel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SearchedBookListView());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public SearchedBookListView() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(300, 600));
			GridBagLayout thisLayout = new GridBagLayout();
			this.setBackground(new java.awt.Color(255, 255, 255));
			thisLayout.rowWeights = new double[] { 0.0, 0.0, 0.1 };
			thisLayout.rowHeights = new int[] { 7, 7, 7 };
			thisLayout.columnWeights = new double[] { 0.0, 0.1, 0.0 };
			thisLayout.columnWidths = new int[] { 7, 7, 7 };
			this.setLayout(thisLayout);
			{
				search = new JTextField();
				this.add(search, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(10, 8, 0, 8), 0, 0));
				search.setText("SEARCH");
			}
			{
				searchButton = new GridentButton();
				this.add(searchButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(10, 0, 0, 10), 0, 0));
				searchButton.setText("SEARCH");
				searchButton.setPreferredSize(new Dimension(60,22));
			}
			{
				icon = new ImageView();
				this.add(icon, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.NONE, new Insets(10, 10, 0, 0), 0, 0));
				icon.setImage(Toolkit.getDefaultToolkit().getImage("images/search.png"));
				icon.setPreferredSize(new java.awt.Dimension(32, 32));
				icon.setOpaque(false);
			}
			{
				contentPanel = new JPanel();
				VerticalLayout contentPanelLayout = new VerticalLayout();
				this.add(contentPanel, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				contentPanel.setBackground(new java.awt.Color(255, 255, 255));
				contentPanel.setLayout(contentPanelLayout);
			}
			{
				separator1 = new Separator();
				this.add(separator1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTextField getSearch() {
		return search;
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

}
