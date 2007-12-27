package net.narusas.cafelibrary.ui;

import java.awt.GridBagConstraints;

import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

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
public class FTPPublishDialog extends javax.swing.JDialog {
	private JLabel serverLabel;
	private JButton cancelButton;
	private JButton publishButton;
	private JTextField pathTextField;
	private JPasswordField passwordTextField;
	private JTextField idTextField;
	private JTextField serverTextField;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JLabel jLabel1;

	/**
	 * Auto-generated main method to display this JDialog
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				FTPPublishDialog inst = new FTPPublishDialog(frame);
				inst.setVisible(true);
			}
		});
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public JButton getPublishButton() {
		return publishButton;
	}

	public JTextField getPathTextField() {
		return pathTextField;
	}

	public JTextField getPasswordTextField() {
		return passwordTextField;
	}

	public JTextField getIdTextField() {
		return idTextField;
	}

	public JTextField getServerTextField() {
		return serverTextField;
	}

	public FTPPublishDialog(JFrame frame) {
		super(frame);
		initGUI();
	}

	private void initGUI() {
		try {
			{
				GridBagLayout thisLayout = new GridBagLayout();
				thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
				thisLayout.rowHeights = new int[] { 7, 7, 7, 20, 7 };
				thisLayout.columnWeights = new double[] { 0.0, 0.1, 0.1 };

				thisLayout.columnWidths = new int[] { 7, 7, 7 };
				getContentPane().setLayout(thisLayout);
				{
					serverLabel = new JLabel();
					getContentPane().add(
							serverLabel,
							new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
									GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
					serverLabel.setText("Server");
				}
				{
					jLabel1 = new JLabel();
					getContentPane().add(
							jLabel1,
							new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
									GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
					jLabel1.setText("ID");
				}
				{
					jLabel2 = new JLabel();
					getContentPane().add(
							jLabel2,
							new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
									GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
					jLabel2.setText("Password");
				}
				{
					jLabel3 = new JLabel();
					getContentPane().add(
							jLabel3,
							new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
									GridBagConstraints.NONE, new Insets(0, 10, 0, 5), 0, 0));
					jLabel3.setText("\uacbd\ub85c(Path)");
				}
				{
					serverTextField = new JTextField();
					getContentPane().add(serverTextField, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(10, 0, 5, 10), 0, 0));
					serverTextField.setText("idTextField");
				}
				{
					idTextField = new JTextField();
					getContentPane().add(idTextField, new GridBagConstraints(1, 1, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 10), 0, 0));
					idTextField.setText("idTextField");
				}
				{
					passwordTextField = new JPasswordField();
					getContentPane().add(passwordTextField, new GridBagConstraints(1, 2, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 10), 0, 0));
					passwordTextField.setText("idTextField");
				}
				{
					pathTextField = new JTextField();
					getContentPane().add(pathTextField, new GridBagConstraints(1, 3, 2, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 10), 0, 0));
					pathTextField.setText("idTextField");
				}
				{
					publishButton = new JButton();
					getContentPane().add(
							publishButton,
							new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST,
									GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					publishButton.setText("\uce28\ud310");
				}
				{
					cancelButton = new JButton();
					getContentPane().add(
							cancelButton,
							new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
									GridBagConstraints.NONE, new Insets(0, 5, 0, 0), 0, 0));
					cancelButton.setText("\ucde8\uc18c");
				}
			}
			this.setSize(421, 194);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
