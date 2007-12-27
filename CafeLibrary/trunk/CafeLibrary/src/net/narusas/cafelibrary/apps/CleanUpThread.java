package net.narusas.cafelibrary.apps;

import java.io.File;

import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.ui.SearchThread;

public class CleanUpThread extends Thread {

	private final Library lib;

	public CleanUpThread(Library lib) {
		super("CleanUpThread");
		this.lib = lib;
		setDaemon(true);
	}

	public void run() {
		joinBookInformationThread();
		cleanUpUselessFiles();
	}

	/**
	 * 
	 */
	private void cleanUpUselessFiles() {
		File[] lists = new File("data").listFiles();
		if (lists == null) {
			return;
		}
		for (File file : lists) {
			char[] nameChars = file.getName().toCharArray();
			String name = "";
			for (char c : nameChars) {
				if (Character.isDigit(c)) {
					name += c;
				} else {
					break;
				}
			}

			try {
				long id = Long.parseLong(name);
				if (lib.findBookById(id) == null) {
					file.delete();
					file.deleteOnExit();
				}
			} catch (NumberFormatException e) {
			}
		}
	}

	/**
	 * 
	 */
	private void joinBookInformationThread() {
		if (SearchThread.merger != null) {
			SearchThread.merger.close();
		}
	}
}
