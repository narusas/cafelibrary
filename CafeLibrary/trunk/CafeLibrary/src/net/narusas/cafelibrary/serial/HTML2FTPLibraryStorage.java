package net.narusas.cafelibrary.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

public class HTML2FTPLibraryStorage extends FTPStorage {

	static Logger logger = Logger.getLogger("log");

	protected void uploads(byte[] serializedLibrary) throws IOException {
		getClient().changeWorkingDirectory(account.getPath());
		logger.info("Start to upload");
		upload("index.html", serializedLibrary);
		upload("main.css", new File("web/main.css"));
		upload("cafelibrary.js", new File("web/cafelibrary.js"));
		getClient().makeDirectory("img");
		getClient().changeWorkingDirectory("img");
		for (int i = 0; i <= 5; i++) {
			String fileName = "favorite_star_" + i + ".png";
			upload(fileName, new File("web/img/" + fileName));
		}
	}

	protected void upload(String remoteFileName, File file) throws FileNotFoundException, IOException {
		logger.info("Upload " + remoteFileName);
		getClient().storeFile(remoteFileName, new FileInputStream(file));
	}

}
