package net.narusas.ui.component;

import javax.swing.AbstractListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JList.DropLocation;

import net.narusas.cafelibrary.ui2.LibraryListModel;

public class ListTransferHandler extends StringTransferHandler {
	private int[] indices = null;
	private int addIndex = -1; // Location where items were added
	private int addCount = 0; // Number of items added.

	// Bundle up the selected items in the list
	// as a single string, for export.
	protected String exportString(JComponent c) {
		JList list = (JList) c;
		indices = list.getSelectedIndices();
		Object[] values = list.getSelectedValues();

		StringBuffer buff = new StringBuffer();

		for (int i = 0; i < values.length; i++) {
			Object val = values[i];
			buff.append(val == null ? "" : val.toString());
			if (i != values.length - 1) {
				buff.append("\n");
			}
		}

		return buff.toString();
	}

	// Take the incoming string and wherever there is a
	// newline, break it into a separate item in the list.
	protected void importString(JComponent c, String str) {
		JList target = (JList) c;
		LibraryListModel listModel = (LibraryListModel) target.getModel();
		int index = target.getSelectedIndex();

		JList.DropLocation dl = target.getDropLocation();
		// System.out.println("DL Index:" + dl.getIndex() + " index:" + index);
		index = dl.getIndex();
		// Prevent the user from dropping data back on itself.
		// For example, if the user is moving items #4,#5,#6 and #7 and
		// attempts to insert the items after item #5, this would
		// be problematic when removing the original items.
		// So this is not allowed.
		if (indices != null && index >= indices[0] - 1 && index <= indices[indices.length - 1]) {
			indices = null;
			return;
		}

		int max = listModel.getSize();
		if (index < 0) {
			index = max;
		} else {
			index++;
			if (index > max) {
				index = max;
			}
		}
		addIndex = index;
		String[] values = str.split("\n");
		addCount = values.length;
		for (int i = 0; i < values.length; i++) {
			listModel.add(index, values[i]);
		}
	}

	
	protected void cleanup(JComponent c, boolean remove) {

	}
}
