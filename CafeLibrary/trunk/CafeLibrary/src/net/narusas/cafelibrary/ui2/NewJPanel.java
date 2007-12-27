package net.narusas.cafelibrary.ui2;

import java.awt.Dimension;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;


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
public class NewJPanel extends javax.swing.JPanel {
	private JSplitPane jSplitPane1;
	private JPanel jPanel1;
	private JPanel jPanel2;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new NewJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public NewJPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			{
				jSplitPane1 = new JSplitPane();
				this.add(jSplitPane1);
				{
					jPanel1 = new JPanel();
					jSplitPane1.add(jPanel1, JSplitPane.RIGHT);
				}
				{
					jPanel2 = new JPanel();
					jSplitPane1.add(jPanel2, JSplitPane.LEFT);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
