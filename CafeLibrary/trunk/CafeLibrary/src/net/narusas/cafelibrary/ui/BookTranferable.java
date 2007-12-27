package net.narusas.cafelibrary.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import net.narusas.cafelibrary.Book;

public class BookTranferable implements Transferable {

	private final Book exportData;

	public BookTranferable(Book exportData) {
		this.exportData = exportData;
	}

	
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		return exportData;
	}

	
	public DataFlavor[] getTransferDataFlavors() {
		return null;
	}

	
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return false;
	}

	public Book getBook() {
		return exportData;
	}

}
