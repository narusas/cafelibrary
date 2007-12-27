package net.narusas.cafelibrary.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import net.narusas.cafelibrary.LibraryStorage;
import net.narusas.util.lang.NFile;

public class FileLibraryStorage implements LibraryStorage {

	private final File target;

	public FileLibraryStorage(File target) {
		this.target = target;
	}

	public void write(byte[] serializedLibrary, Object param) throws IOException {
		NFile.write(target, serializedLibrary);
	}

	public InputStream read() throws FileNotFoundException {
		return new FileInputStream(target);
	}

}
