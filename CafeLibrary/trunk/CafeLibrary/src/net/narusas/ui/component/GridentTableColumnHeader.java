package net.narusas.ui.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GridentTableColumnHeader extends GridentPanel implements TableCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4625023302801425422L;
	private JLabel label;

	public GridentTableColumnHeader(String text) {
		super();
		this.setLayout(new BorderLayout());
		label = new JLabel(text);
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.CENTER);
	}

	public GridentTableColumnHeader(JButton[] buttons) {
		super();
		this.setLayout(new FlowLayout());
		for (JButton button : buttons) {
			add(button);
		}
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return this;
	}

}
