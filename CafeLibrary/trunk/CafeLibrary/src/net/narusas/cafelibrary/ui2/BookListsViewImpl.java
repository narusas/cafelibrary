package net.narusas.cafelibrary.ui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.narusas.cafelibrary.BookList;
import net.narusas.cafelibrary.Borrower;
import net.narusas.cafelibrary.apps.BookListsView;
import net.narusas.cafelibrary.apps.BookListsViewCallback;

public class BookListsViewImpl implements BookListsView {

	private final BookListsSection panel;
	private BookListsViewCallback controller;
	private BookList selectedBookList;
	private Borrower selectedBorrower;

	public BookListsViewImpl(BookListsSection panel) {
		System.out.println(panel);
		this.panel = panel;
		bind();
	}

	private void bind() {
		panel.getBooklistsList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				BookList selectedBookList = (BookList) panel.getBooklistsList().getSelectedValue();
				if (selectedBookList == null) {
					return;
				}
				controller.bookListSelected(selectedBookList);
			}
		});

		panel.getBorrowersList().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				}
				Borrower selectedBorrwer = (Borrower) panel.getBorrowersList().getSelectedValue();
				if (selectedBorrwer == null) {
					return;
				}
				controller.borrowerSelected(selectedBorrwer);
			}
		});

		panel.getAddBooklistButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.bookListAddRequested();
			}
		});

		panel.getAddBorrowerButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.borrowerAddRequested();
			}
		});

		panel.getDeleteButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (selectedBookList != null) {
					controller.deleteBookList();
				} else if (selectedBorrower != null) {
					controller.deleteBorrower();
				}
			}
		});
	}

	public String askNewBookListName() {
		return JOptionPane.showInputDialog(panel, "새로운 목록이름을 입력해 주세요");
	}

	public void selectBookList(BookList list) {
		selectedBorrower = null;
		selectedBookList = list;
		int index = panel.getBooklistsModel().indexOf(list);
		panel.getBooklistsList().setSelectedIndex(index);
	}

	public void selectBorrower(Borrower borrower) {
		selectedBorrower = borrower;
		selectedBookList = null;
		panel.getBorrowersList().setSelectedValue(borrower, true);
	}

	public void setBookListVisible() {
		System.out.println("setBookListVisible");
	}

	public void setBorrowerVisible() {
		System.out.println("setBorrowerVisible");
	}

	public void setCallback(BookListsViewCallback controller) {
		this.controller = controller;
	}

	public void unhighlightBookLists() {
		System.out.println("unhighlightBookLists");
		panel.getBooklistsList().clearSelection();
	}

	public void unhighlightBorrowers() {
		System.out.println("unhighlightBorrowers");
		panel.getBorrowersList().clearSelection();
	}

}
