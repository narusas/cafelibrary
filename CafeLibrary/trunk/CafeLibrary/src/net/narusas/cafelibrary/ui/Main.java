package net.narusas.cafelibrary.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.birosoft.liquid.LiquidLookAndFeel;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.apps.CleanUpThread;
import net.narusas.cafelibrary.serial.LibrarySerializer;
import net.narusas.cafelibrary.ui.bookshelf.BookShelfConstraints;

public class Main {
	public static void main(String[] args) {
		System.out.println("Start");
		initUIConfig();

		Library lib = null;
		try {
			lib = LibrarySerializer.load();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		if (lib == null) {
			lib = new Library();
		}

		lib.addLibraryListener(new LibrarySerializer(lib));

		Controller controller = new Controller();
		MainFrame mainFrame = new MainFrame();

		controller.setLibrary(lib);
		mainFrame.setLibrary(lib);
		controller.setFrame(mainFrame);

		BookListController blc = new BookListController(lib, mainFrame);
		BookTableController btc = new BookTableController(lib, mainFrame);
		BookDetailController bdc = new BookDetailController(lib, mainFrame);

		controller.setup(blc, btc, bdc);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		BookShelfConstraints.setZoom(1.0f);
		int x = (screenSize.width - mainFrame.getWidth()) / 2;
		int y = (screenSize.height - mainFrame.getHeight()) / 2;
		mainFrame.setLocation(x, y);
		mainFrame.setVisible(true);
		Runtime.getRuntime().addShutdownHook(new CleanUpThread(lib));
	}

	private static void initUIConfig() {
		try {
		      UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
		   } catch (Exception e) {}

	}
}
