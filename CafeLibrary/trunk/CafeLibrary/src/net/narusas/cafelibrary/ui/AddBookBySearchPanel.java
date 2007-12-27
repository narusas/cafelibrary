package net.narusas.cafelibrary.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;

import javax.swing.JButton;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.narusas.cafelibrary.Book;
import net.narusas.cafelibrary.bookfactories.FetchResult;
import net.narusas.ui.component.VerticalLayout;

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
public class AddBookBySearchPanel extends javax.swing.JPanel {
	private JScrollPane jScrollPane1;

	private JScrollPane jScrollPane2;

	private JPanel contentPanel;

	private JButton searchButton;

	private JLabel fetchStateLabel;

	private JTextField searchTextField;

	private JButton previousButton;

	private JButton nextPageButton;

	private JPanel jPanel2;

	private JLabel jLabel1;

	private JPanel jPanel1;

	private AddBookBySearchListener listener;

	private FetchResult fetchResult;

	private Timer timer;

	private JPanel timerPanel;

	private JLabel timerLabel;

	private Icon[] waiterIcons;

	private int waiter;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new AddBookBySearchPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public AddBookBySearchPanel() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setPreferredSize(new java.awt.Dimension(290, 354));
			this.setLayout(thisLayout);
			// this.setBackground(new java.awt.Color(255,255,255));
			this.setBorder(BorderFactory.createTitledBorder("\uac80\uc0c9\uc73c\ub85c \ucd94\uac00"));
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, BorderLayout.CENTER);
				jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{
					jPanel1 = new JPanel();
					GridBagLayout jPanel1Layout = new GridBagLayout();
					jPanel1Layout.columnWidths = new int[] { 7, 7, 7, 7 };
					jPanel1Layout.rowHeights = new int[] { 7, 7, 20 };
					jPanel1Layout.columnWeights = new double[] { 0.0, 0.0, 0.1, 0.0 };
					jPanel1Layout.rowWeights = new double[] { 0.0, 0.0, 0.1 };
					jScrollPane1.setViewportView(jPanel1);
					jPanel1.setLayout(jPanel1Layout);
					jPanel1.setPreferredSize(new java.awt.Dimension(255, 328));
					jPanel1.setBackground(new java.awt.Color(255, 255, 255));
					{
						jLabel1 = new JLabel();
						jLabel1.setIcon(new ImageIcon("images/search.png"));
						jPanel1.add(jLabel1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER,
								GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					}
					{
						searchTextField = new JTextField();
						jPanel1
								.add(searchTextField, new GridBagConstraints(1, 0, 2, 1, 0.0, 0.0,
										GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
										new Insets(0, 5, 0, 5), 0, 0));
						searchTextField.setText("searchTextArea");
					}
					{
						searchButton = new JButton();
						jPanel1.add(searchButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
						searchButton.setText("검색");
						searchButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
					}
					{
						jPanel2 = new JPanel();
						GridBagLayout jPanel2Layout = new GridBagLayout();
						jPanel1.add(jPanel2, new GridBagConstraints(1, 1, 3, 1, 0.0, 0.0, GridBagConstraints.WEST,
								GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
						jPanel2Layout.rowWeights = new double[] { 0.1 };
						jPanel2Layout.rowHeights = new int[] { 7 };
						jPanel2Layout.columnWeights = new double[] { 0.1, 0.0, 0.0 };
						jPanel2Layout.columnWidths = new int[] { 7, 7, 7 };
						jPanel2.setLayout(jPanel2Layout);
						jPanel2.setBackground(new java.awt.Color(255, 255, 255));
						{
							fetchStateLabel = new JLabel();
							jPanel2.add(fetchStateLabel, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 0), 0,
									0));
							fetchStateLabel.setText("검색결과:");
						}
						{
							nextPageButton = new JButton();
							jPanel2.add(nextPageButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 5), 0, 0));
							nextPageButton.setText("Next");
							nextPageButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
						}
						{
							previousButton = new JButton();
							jPanel2.add(previousButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
									GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
							previousButton.setText("Prev");
							previousButton.setMargin(new java.awt.Insets(1, 1, 1, 1));
						}
					}
					{
						jScrollPane2 = new JScrollPane();
						jPanel1.add(jScrollPane2, new GridBagConstraints(0, 2, 4, 1, 0.0, 0.0,
								GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
						jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
						{
							contentPanel = new JPanel();
							jScrollPane2.setViewportView(contentPanel);
							contentPanel.setLayout(new VerticalLayout(3));
							contentPanel.setBackground(new java.awt.Color(255, 255, 255));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getSearchButton() {
		return searchButton;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public void addBooks(FetchResult fetchResult) {
		this.fetchResult = fetchResult;
		for (Book book : fetchResult.getRes()) {
			BookItemPanel item = new BookItemPanel();
			item.setBook(book);
			item.addListener(new BookItemAddListener() {
				public void addBook(BookItemPanel bookItemPanel, Book book) {
					contentPanel.remove(bookItemPanel);
					addSearchedBook(book);
					jScrollPane1.revalidate();
					contentPanel.repaint();
				}
			});
			contentPanel.add(item);
			jScrollPane1.revalidate();
		}

		previousButton.setEnabled(!fetchResult.isFirstPageResult());
		nextPageButton.setEnabled(!fetchResult.isLastPageResult());
		fetchStateLabel.setText("총 검색수:" + fetchResult.getTotalCount() + " " + fetchResult.getCurrentPage() + "/"
				+ fetchResult.getTotalPage());
		jScrollPane1.revalidate();
		jScrollPane1.getViewport().setViewPosition(new Point(0, 0));

	}

	public void setListener(AddBookBySearchListener listener) {
		this.listener = listener;

	}

	protected void addSearchedBook(Book book) {
		listener.addBook(book);
	}

	public void clearBooks() {
		contentPanel.removeAll();
		jScrollPane1.revalidate();
	}

	public FetchResult getFetchResult() {
		return fetchResult;
	}

	public void setFetchResult(FetchResult fetchResult) {
		this.fetchResult = fetchResult;
	}

	public JButton getPreviousButton() {
		return previousButton;
	}

	public JButton getNextPageButton() {
		return nextPageButton;
	}

	public void startWaiter() {
		if (timer != null && timer.isRunning()) {
			return;
		}
		setTimerPanel();
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateTimerPanel();
			}
		});
		timer.start();
	}

	public void stopWaiter() {
		if (timer == null && timer.isRunning() == false) {
			return;
		}
		timer.stop();
		removeTimerPanel();

	}

	private void setTimerPanel() {
		timerPanel = new JPanel();
		timerPanel.setBackground(Color.WHITE);
		timerPanel.setLayout(new BorderLayout());
		timerPanel.setPreferredSize(new Dimension(290, 200));
		timerPanel.setSize(new Dimension(290, 200));
		timerLabel = new JLabel();
		waiterIcons = new Icon[8];
		for (int i = 0; i < 8; i++) {
			waiterIcons[i] = new ImageIcon("images/waiter" + i + ".png");
		}

		waiter = 0;
		timerLabel.setIcon(waiterIcons[waiter]);
		timerPanel.add(timerLabel, BorderLayout.CENTER);
		timerLabel.setPreferredSize(new java.awt.Dimension(275, 281));
		contentPanel.add(timerPanel);
		jScrollPane1.revalidate();
	}

	private void removeTimerPanel() {
		contentPanel.remove(timerPanel);
		jScrollPane1.revalidate();
	}

	protected void updateTimerPanel() {
		System.out.println("Update");
		waiter++;
		if (waiter >= 8) {
			waiter = 0;
		}
		timerLabel.setIcon(waiterIcons[waiter]);
		timerLabel.repaint();

	}
}
