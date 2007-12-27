package net.narusas.cafelibrary.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import net.narusas.cafelibrary.apps.IconHolder;


public class FavoriteTableCellEditor extends AbstractCellEditor implements TableCellEditor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -167353864746622018L;
	private int favorite;
	protected int clickCountToStart = 1;
	private JLabel label;

	
	public Object getCellEditorValue() {
		return favorite;
	}

	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, final int row,
			final int column) {
		favorite = ((Integer) value).intValue();
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.LEFT);
		label.setBackground(Color.YELLOW);
		label.setIcon(IconHolder.favorite[favorite]);
		label.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				changeValue(e);
			}
		});

		label.addMouseMotionListener(new MouseMotionListener() {

			
			public void mouseDragged(MouseEvent e) {
				changeValue(e);

			}

			
			public void mouseMoved(MouseEvent e) {

			}
		});
		return label;
	}

	protected void changeValue(MouseEvent e) {

		int x = e.getX();
		if (x < 5) {
			favorite = 0;
		} else if (x < 20) {
			favorite = 1;
		} else if (x < 32) {
			favorite = 2;
		} else if (x < 44) {
			favorite = 3;
		} else if (x < 55) {
			favorite = 4;
		} else {
			favorite = 5;
		}
		label.setIcon(IconHolder.favorite[favorite]);
	}

	
	public boolean isCellEditable(EventObject anEvent) {
		if (anEvent instanceof MouseEvent && label != null) {
			label.dispatchEvent((MouseEvent) anEvent);
		}
		return true;
	}

}
