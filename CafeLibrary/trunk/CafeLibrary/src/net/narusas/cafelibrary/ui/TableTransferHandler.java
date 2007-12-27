package net.narusas.cafelibrary.ui;

/*
 * TableTransferHandler.java is used by the 1.4
 * ExtendedDnDDemo.java example.
 */
import javax.swing.*;
import javax.swing.table.*;

import net.narusas.cafelibrary.ui2.BooksListTableModel;
import net.narusas.ui.component.StringTransferHandler;

import java.awt.*;
import java.awt.datatransfer.*;

public class TableTransferHandler extends StringTransferHandler {
	private int[] rows = null;
	private int addIndex = -1; // Location where items were added
	private int addCount = 0; // Number of items added.

	protected String exportString(JComponent c) {
		JTable table = (JTable) c;
		rows = table.getSelectedRows();
		int colCount = table.getColumnCount();
		BooksListTableModel model = (BooksListTableModel) table.getModel();

		StringBuffer buff = new StringBuffer();

		for (int i = 0; i < rows.length; i++) {
			buff.append(model.get(rows[i]).getId());
			if (i != rows.length) {
				buff.append(",");
			}
		}

		return buff.toString();
	}

	protected void importString(JComponent c, String str) {

	}

	protected void cleanup(JComponent c, boolean remove) {

	}
}
