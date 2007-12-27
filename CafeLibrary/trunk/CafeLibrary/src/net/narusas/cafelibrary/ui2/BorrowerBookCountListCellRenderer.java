package net.narusas.cafelibrary.ui2;

import javax.swing.ImageIcon;

public class BorrowerBookCountListCellRenderer extends BookCountCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5091118028111308756L;

	public BorrowerBookCountListCellRenderer() {
		super();
	}

	protected void initIcon() {
		headerIcon = new ImageIcon("images/borrower.png");
	}
}
