package net.narusas.cafelibrary.ui.bookshelf;

import javax.swing.JComponent;

import net.narusas.ui.component.StringTransferHandler;

public class BookShelfTransferHandler extends StringTransferHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5823527346295139670L;

	
	protected void cleanup(JComponent c, boolean remove) {

	}

	
	protected String exportString(JComponent c) {
		SerialBookUI ui = (SerialBookUI) c;
		return String.valueOf(ui.getSelectedBook().getId());
	}

	
	protected void importString(JComponent c, String str) {

	}

}
