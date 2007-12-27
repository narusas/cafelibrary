package net.narusas.cafelibrary.serial;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.net.ftp.FTPClient;

import net.narusas.cafelibrary.Config;
import net.narusas.cafelibrary.Library;
import net.narusas.util.lang.NFile;
import junit.framework.TestCase;

public class SerializerImplementationTest extends TestCase {
	public void testCreateXML() {
		Library lib = new Library();
		XMLSerializer xmlSerializer = new XMLSerializer();
		// <?xml version="1.0" encoding="UTF-8"
		// standalone="no"?><library><books/><booklists/><borrowers/></library>
		assertTrue(new String(xmlSerializer.serialize(lib), Config.CHARSET).contains("<library"));
	}

	public void testCreateHTML() {
		Library lib = new Library();
		HTMLSerializer serializer = new HTMLSerializer();
		assertTrue(new String(serializer.serialize(lib), Config.CHARSET).contains("bookList.name ="));
	}

	public void testFileStorage() throws IOException {
		File f = File.createTempFile("aaaa", "xml");
		FileLibraryStorage storage = new FileLibraryStorage(f);
		storage.write(new byte[] { 'A', 'B' }, null);

		assertEquals("AB", NFile.getText(f));
	}

	public void testFTPStorage() throws IOException {
		List<UploadEntity> expected = new LinkedList<UploadEntity>();
		expected.add(new UploadEntity("index.html", null, new byte[] { 'A', 'B' }));
		expected.add(new UploadEntity("main.css", null, null));
		expected.add(new UploadEntity("cafelibrary.js", null, null));
		expected.add(new UploadEntity("favorite_star_0.png", null, null));
		expected.add(new UploadEntity("favorite_star_1.png", null, null));
		expected.add(new UploadEntity("favorite_star_2.png", null, null));
		expected.add(new UploadEntity("favorite_star_3.png", null, null));
		expected.add(new UploadEntity("favorite_star_4.png", null, null));
		expected.add(new UploadEntity("favorite_star_5.png", null, null));

		final List<UploadEntity> entities = new LinkedList<UploadEntity>();
		HTML2FTPLibraryStorage storage = new HTML2FTPLibraryStorage() {
			@Override
			protected FTPClient getClient() {
				return new MockClient();
			}

			@Override
			protected void connect() throws SocketException, IOException, LoginFailException {
			}

			@Override
			protected void disconnect() throws IOException {
			}

			protected void upload(String remoteFileName, File file) {
				entities.add(new UploadEntity(remoteFileName, file, null));
			}

			protected void upload(String remoteFileName, byte[] data) {
				entities.add(new UploadEntity(remoteFileName, null, data));
			}
		};

		storage.write(new byte[] { 'A', 'B' }, new FTPAccount("narusas.net", "milines", "akghdn", "web"));

		for (int i = 0; i < expected.size(); i++) {
			assertEquals(expected.get(i), entities.get(i));
		}
	}

	class UploadEntity {
		String name;
		File file;
		byte[] data;

		public UploadEntity(String name, File file, byte[] data) {
			this.name = name;
			this.file = file;
			this.data = data;
		}

		public boolean equals(Object obj) {
			UploadEntity u1 = (UploadEntity) obj;
			return name.equals(u1.name) && isEqualsData(u1.data);
		}

		private boolean isEqualsData(byte[] data2) {
			if (data == data2) {
				return true;
			}
			for (int i = 0; i < data.length; i++) {
				if (data[i] != data2[i]) {
					return false;
				}
			}
			return true;
		}
	}
}

class MockClient extends FTPClient {
	@Override
	public boolean changeWorkingDirectory(String pathname) throws IOException {
		return true;
	}

	@Override
	public boolean makeDirectory(String pathname) throws IOException {
		return true;
	}
}