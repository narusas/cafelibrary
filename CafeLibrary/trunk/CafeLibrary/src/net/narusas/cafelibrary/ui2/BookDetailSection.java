package net.narusas.cafelibrary.ui2;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.WindowConstants;
import net.narusas.ui.component.GridentButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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
public class BookDetailSection extends TitledPanel {
	private GridentButton editButton;
	private JComboBox selectBookFactoryComboBox;
	private JLabel bflabel;
	private JScrollPane scrollPane;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BookDetailSection());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setSize(307, 468);
		frame.setVisible(true);
		frame.setPreferredSize(new java.awt.Dimension(307, 468));
	}

	public BookDetailSection() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridBagLayout optionPanelLayout = new GridBagLayout();
			{
				optionPanelLayout.rowWeights = new double[] {0.1};
				optionPanelLayout.rowHeights = new int[] {7};
				optionPanelLayout.columnWeights = new double[] {0.0, 0.1, 0.0};
				optionPanelLayout.columnWidths = new int[] {7, 20, 7};
			}
			
			this.setPreferredSize(new java.awt.Dimension(418, 600));
			super.getOptionPanel().setLayout(optionPanelLayout);
			{
				scrollPane = new JScrollPane();
				super.getContentPanel().add(getScrollPane(), BorderLayout.CENTER);
				scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				scrollPane.setBackground(new java.awt.Color(255,255,255));
			}
			{
				editButton = new GridentButton();
				super.getOptionPanel().add(editButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 15, 0, 0), 0, 0));
				editButton.setIcon(new ImageIcon("images/write.png"));
				editButton.setPreferredSize(new Dimension(32, 20));
				
			}
			{
				ComboBoxModel selectBookFactoryComboBoxModel = 
					new DefaultComboBoxModel(
							new String[] { "다음+네이버", "아마존","알라딘" });
				selectBookFactoryComboBox = new JComboBox();
				super.getOptionPanel().add(selectBookFactoryComboBox, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 15), 0, 0));
				selectBookFactoryComboBox.setModel(selectBookFactoryComboBoxModel);
				selectBookFactoryComboBox.setBackground(new java.awt.Color(255,255,255));
			}
			{
				bflabel = new JLabel();
				super.getOptionPanel().add(bflabel, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
				bflabel.setText("\uc815\ubcf4 \uc18c\uc2a4");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public GridentButton getEditButton() {
		return editButton;
	}

	public JComboBox getSelectBookFactoryComboBox() {
		return selectBookFactoryComboBox;
	}

	public JLabel getBflabel() {
		return bflabel;
	}

}
