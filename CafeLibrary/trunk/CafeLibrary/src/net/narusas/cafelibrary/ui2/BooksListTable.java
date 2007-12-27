package net.narusas.cafelibrary.ui2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;

import javax.swing.DefaultCellEditor;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.apps.IconHolder;
import net.narusas.cafelibrary.ui.FavoriteTableCellEditor;
import net.narusas.ui.component.GridentTableColumnHeader;

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
public class BooksListTable extends javax.swing.JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2020003297744077751L;
	private JScrollPane scrollPane;
	private JTable bookListTable;
	private BooksListTableModel model;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		BookList bList = new BookList("ddd");
		Book b = new Book(315, "ABC");
		b.setFavorite(5);
		bList.add(b);
		bList.add(new Book(316, "DEF"));
		bList.add(new Book(317, "DDD"));
		JFrame frame = new JFrame();
		BooksListTable p = new BooksListTable();
		p.getBookListTableModel().setBookList(bList);
		JTable table = p.getBookListTable();
		JTableHeader headers = table.getTableHeader();
		headers.addMouseListener(p.getBookListTableModel().getMouseAdapter(table));

		frame.getContentPane().add(p);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();

		frame.setVisible(true);
		frame.getContentPane().setBackground(new java.awt.Color(255,255,255));
	}

	public BooksListTable() {
		super();
		initGUI();
	}

	/**
	 * 
	 */
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			setPreferredSize(new Dimension(400, 300));
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			this.setBackground(new java.awt.Color(255,255,255));
			{
				scrollPane = new JScrollPane();
				this.add(scrollPane, BorderLayout.CENTER);
				scrollPane.setBackground(new java.awt.Color(255,255,255));
				scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				{

					model = new BooksListTableModel();

					bookListTable = new JTable();
					bookListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					bookListTable.setAutoCreateColumnsFromModel(false);
					bookListTable.setModel(model);
					for (int i = 0; i < BooksListTableModel.COLS.length; i++) {
						ColumnData colData = BooksListTableModel.COLS[i];
						TableCellEditor editor = null;
						if (colData.title.equals("선호도")) {
							editor = new FavoriteTableCellEditor();
						} else if (colData.title.equals("제목")) {
							editor = new DefaultCellEditor(new JTextField());
						} else if (colData.title.equals("만든이")) {
							editor = new DefaultCellEditor(new JTextField());
						}

						BGColoredTableCellRenderer renderer = new BGColoredTableCellRenderer();
						renderer.setHorizontalAlignment(colData.alignment);
						TableColumn col = new TableColumn(i, colData.width, renderer, editor);
						if (colData.isFixedWidth) {
							col.setMaxWidth(colData.width);
							col.setMinWidth(colData.width);
						}
						GridentTableColumnHeader headerRenderer = new GridentTableColumnHeader(colData.title);
						headerRenderer.setBorder(new LineBorder(new Color(60, 60, 60), 1));
						headerRenderer.setPreferredSize(new Dimension(100, 15));
						col.setHeaderRenderer(headerRenderer);

						bookListTable.addColumn(col);
					}

					scrollPane.setViewportView(getBookListTable());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JTable getBookListTable() {
		return bookListTable;
	}

	public BooksListTableModel getBookListTableModel() {
		return model;
	}

	public void setTitle(String title) {
		this.setBorder(BorderFactory.createTitledBorder(null, title, TitledBorder.LEADING, TitledBorder.TOP));
	}

}

class BGColoredTableCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	Color LIGHT_BLUE = new Color(242, 246, 254);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8437457444256103463L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (row % 2 == 0) {
			setBackground(LIGHT_BLUE);
			setForeground(Color.BLACK);
		} else {
			setBackground(Color.WHITE);
			setForeground(Color.BLACK);
		}

		return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}

	protected void setValue(Object value) {
		if (value instanceof Icon) {
			Icon icon = (Icon) value;
			setIcon(icon);
		} else if (value instanceof Integer) {
			setIcon(IconHolder.favorite[((Integer) value).intValue()]);
		}

		else {
			super.setValue(value);
		}
	}

}

class ColumnData {
	String title;
	int width;
	int alignment;
	boolean isFixedWidth;
	int rendererType;

	public ColumnData(String title, int width, int alignment, boolean isFixedWidth, int rendererType) {
		super();
		this.title = title;
		this.width = width;
		this.alignment = alignment;
		this.isFixedWidth = isFixedWidth;
		this.rendererType = rendererType;
	}
}
