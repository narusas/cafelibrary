package net.narusas.cafelibrary;

import java.io.IOException;
import java.io.InputStream;

public interface LibraryStorage {
	void write(byte[] serializedLibrary, Object param) throws IOException; 

	InputStream read() throws IOException;
}
