package net.narusas.ui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.Dimension;
import javax.swing.BorderFactory;

import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
public class TitledPanel extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4920670436362872535L;
	private GridentPanel titlePanel;
	private JPanel contentPanel;
	private JLabel titleLabel;
	private GridentPanel optionPanel;
	private final boolean useOptionPanel;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new TitledPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public TitledPanel() {
		this(true);
	}

	public TitledPanel(boolean useOptionPanel) {
		super();
		this.useOptionPanel = useOptionPanel;
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			{
				contentPanel = new JPanel();
				BorderLayout contentPanelLayout = new BorderLayout();
				contentPanel.setLayout(contentPanelLayout);
				this.add(getTitlePanel(), BorderLayout.NORTH);
				this.add(contentPanel, BorderLayout.CENTER);
				if (useOptionPanel) {
					this.add(getOptionPanel(), BorderLayout.SOUTH);
				}

				contentPanel.setBackground(new java.awt.Color(255, 255, 255));
				contentPanel.setBorder(new LineBorder(new java.awt.Color(60, 60, 60), 1, false));
				// contentPanel.add(getJLabel1(), BorderLayout.CENTER);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public GridentPanel getTitlePanel() {
		if (titlePanel == null) {
			titlePanel = new GridentPanel();
			titlePanel.setLayout(new BorderLayout());
			titlePanel.setBorder(new LineBorder(new java.awt.Color(60, 60, 60), 1, false));
			titlePanel.setPreferredSize(new java.awt.Dimension(400, 20));
			titlePanel.add(getTitleLabel(), BorderLayout.CENTER);
		}
		return titlePanel;
	}

	public JLabel getTitleLabel() {
		if (titleLabel == null) {
			titleLabel = new JLabel();
			titleLabel.setText("Collections");
			titleLabel.setFont(new java.awt.Font("±¼¸²", 1, 11));
			titleLabel.setAlignmentY(0.1f);
			titleLabel.setVerticalTextPosition(SwingConstants.TOP);
			titleLabel.setPreferredSize(new java.awt.Dimension(29, 10));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return titleLabel;
	}

	public GridentPanel getOptionPanel() {
		if (optionPanel == null) {
			optionPanel = new GridentPanel(2);
			optionPanel.useDarkerColors();
			optionPanel.setPreferredSize(new java.awt.Dimension(400, 35));
			optionPanel.setBorder(new LineBorder(new java.awt.Color(60, 60, 60), 1, false));
		}
		return optionPanel;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
