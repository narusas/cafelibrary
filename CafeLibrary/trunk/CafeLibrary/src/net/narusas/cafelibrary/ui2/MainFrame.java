package net.narusas.cafelibrary.ui2;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import net.narusas.ui.component.GridentPanel;
import net.narusas.ui.component.GridentSplitPane;

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
public class MainFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7378487107102138346L;
	private GridentPanel gridentPanel1;
	private BookListsSection bookListsSection;
	private BookDetailSection bookDetailSection;
	private BooksListSection booksListSection1;
	private JPanel rightPanel;
	private JSplitPane jSplitPane1;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame inst = new MainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public MainFrame() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			this.setPreferredSize(new java.awt.Dimension(800, 600));
			{
				gridentPanel1 = new GridentPanel();
				BorderLayout gridentPanel1Layout = new BorderLayout();
				gridentPanel1.setLayout(gridentPanel1Layout);
				getContentPane().add(gridentPanel1, BorderLayout.CENTER);
				{
					jSplitPane1 = new JSplitPane();
					gridentPanel1.add(jSplitPane1, BorderLayout.CENTER);
					jSplitPane1.setBorder(new EmptyBorder(12, 12, 12, 12));
					GridentSplitPane ui = new GridentSplitPane();
					jSplitPane1.setUI(ui);
					jSplitPane1.setDividerLocation(200);
					jSplitPane1.setOpaque(false);
					{
						bookListsSection = new BookListsSection();
						jSplitPane1.add(bookListsSection, JSplitPane.LEFT);
					}
					{
						rightPanel = new JPanel();
						BorderLayout rightPanelLayout = new BorderLayout();
						rightPanel.setLayout(rightPanelLayout);
						jSplitPane1.add(rightPanel, JSplitPane.RIGHT);
						rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
						rightPanel.setOpaque(false);
						{
							bookDetailSection = new BookDetailSection();
							rightPanel.add(getBookDetailSection(), BorderLayout.EAST);
							bookDetailSection.setPreferredSize(new java.awt.Dimension(315, 1000));
							bookDetailSection.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
							bookDetailSection.setOpaque(false);
						}
						{
							booksListSection1 = new BooksListSection();
							rightPanel.add(booksListSection1, BorderLayout.CENTER);

							booksListSection1.setOpaque(false);
						}
					}

				}
			}
			pack();
			this.setSize(904, 657);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BookDetailSection getBookDetailSection() {
		return bookDetailSection;
	}

	public BookListsSection getBookListsSection() {
		return bookListsSection;
	}

	public BooksListSection getBooksListSection1() {
		return booksListSection1;
	}

	public JSplitPane getJSplitPane1() {
		return jSplitPane1;
	}

}
