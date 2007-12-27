package net.narusas.cafelibrary.serial;

import java.io.File;
import java.io.IOException;

import net.narusas.util.lang.NFile;

public class FTPAccount {
	String server;
	String id;
	String password;
	String path;
	private static FTPAccount last;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public FTPAccount(String server, String id, String password, String path) {
		super();
		this.server = server;
		this.id = id;
		this.password = password;
		this.path = path;
		setLast(this);
	}

	private void setLast(FTPAccount account) {
		last = account;
		try {
			NFile.write(new File("ftp.cfg"), account.getServer() + "," + account.getId() + "," + account.getPassword()
					+ "," + account.getPath());
		} catch (IOException e) {
		}
	}

	public static FTPAccount getLast() {
		if (last == null) {
			last = readLastFromSavedFile();
		}
		return last;
	}

	private static FTPAccount readLastFromSavedFile() {
		try {
			String text = NFile.getText(new File("ftp.cfg"));
			if (text == null || "".equals(text)) {
				return null;
			}
			String[] tokens = text.split(",");
			return new FTPAccount(tokens[0], tokens[1], tokens[2], tokens[3]);
		} catch (Throwable e) {
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		FTPAccount account = (FTPAccount) obj;
		return server.equals(account.getServer()) && id.equals(account.getId())
				&& password.equals(account.getPassword()) && path.equals(account.getPath());
	}

	public static void clearLast() {
		last = null;
	}

	@Override
	public String toString() {
		return "FTPAccount{" + server + "," + id + "," + password + "," + path + "}";
	}
}
