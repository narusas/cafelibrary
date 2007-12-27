package net.narusas.cafelibrary.ui2;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import net.narusas.ui.component.GridentButton;
import net.narusas.ui.component.ImageView;
import net.narusas.ui.component.Separator;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SelectAddingMethodView extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2698304427634396204L;
	private ImageView imageView1;
	private Separator separator3;
	private GridentButton gridentButton1;
	private JTextArea jTextArea1;
	private ImageView imageView3;
	private Separator separator2;
	private GridentButton searchISBNButton;
	private JTextField searchISBN;
	private ImageView imageView2;
	private Separator separator1;
	private GridentButton searchTitleButton;
	private JTextField searchTitle;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SelectAddingMethodView());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SelectAddingMethodView() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(300, 349));
			GridBagLayout thisLayout = new GridBagLayout();
			this.setBackground(new java.awt.Color(255,255,255));
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {7, 7, 7, 20, 20, 20, 20, 7};
			thisLayout.columnWeights = new double[] {0.0, 0.1, 0.0};
			thisLayout.columnWidths = new int[] {7, 7, 7};
			this.setLayout(thisLayout);
			{
				imageView1 = new ImageView();
				imageView1.setImage(Toolkit.getDefaultToolkit().getImage("images/search.png"));
				this.add(imageView1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 15, 10, 10), 0, 0));
				imageView1.setPreferredSize(new Dimension(32,32));
				imageView1.setOpaque(false);

			}
			{
				searchTitle = new JTextField();
				this.add(searchTitle, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				searchTitle.setText("TITLE");
			}
			{
				searchTitleButton = new GridentButton();
				this.add(getSearchTitleButton(), new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
				searchTitleButton.setText("SEARCH");
				searchTitleButton.setPreferredSize(new Dimension(60,22));
			}
			{
				separator1 = new Separator();
				this.add(separator1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				imageView2 = new ImageView();
				this.add(imageView2, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 15, 10, 10), 0, 0));
				imageView2.setPreferredSize(new Dimension(32,32));
				imageView2.setImage(Toolkit.getDefaultToolkit().getImage("images/barcode.png"));
				imageView2.setOpaque(false);
			}
			{
				searchISBN = new JTextField();
				this.add(searchISBN, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				searchISBN.setText("0 000000 000000");
			}
			{
				searchISBNButton = new GridentButton();
				this.add(searchISBNButton, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
				searchISBNButton.setText("SEARCH");
				searchISBNButton.setPreferredSize(new Dimension(60,22));
			}
			{
				separator2 = new Separator();
				this.add(separator2, new GridBagConstraints(0, 3, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
			{
				imageView3 = new ImageView();
				this.add(imageView3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 15, 10, 10), 0, 0));
				imageView3.setPreferredSize(new Dimension(32,32));
				imageView3.setOpaque(false);
				imageView3.setImage(Toolkit.getDefaultToolkit().getImage("images/write_large.png"));
			}
			{
				jTextArea1 = new JTextArea();
				this.add(jTextArea1, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 10, 0, 10), 0, 0));
				jTextArea1.setText("\ucc45\uc5d0 \ub300\ud55c \uc815\ubcf4\ub97c \uc9c1\uc811 \uc785\ub825\ud558\uc5ec \ucc45\uc744 \ucd94\uac00\ud569\ub2c8\ub2e4.");
				jTextArea1.setEditable(false);
				jTextArea1.setOpaque(false);
				jTextArea1.setLineWrap(true);
				jTextArea1.setBorder(new LineBorder(new java.awt.Color(60,60,60), 1, true));
				jTextArea1.setMargin(new java.awt.Insets(4, 4, 4, 4));
			}
			{
				gridentButton1 = new GridentButton();
				this.add(gridentButton1, new GridBagConstraints(2, 4, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 10), 0, 0));
				gridentButton1.setText("Enter");
				gridentButton1.setPreferredSize(new Dimension(60,22));
			}
			{
				separator3 = new Separator();
				this.add(separator3, new GridBagConstraints(0, 5, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JTextField getSearchTitle() {
		return searchTitle;
	}
	
	public GridentButton getSearchTitleButton() {
		return searchTitleButton;
	}

	public GridentButton getSearchISBNButton() {
		return searchISBNButton;
	}

	public JTextField getSearchISBN() {
		return searchISBN;
	}

}
