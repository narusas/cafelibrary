package net.narusas.cafelibrary.apps;

import java.io.File;

import net.narusas.cafelibrary.BookFactory;
import net.narusas.cafelibrary.Library;
import net.narusas.cafelibrary.LibraryLoader;
import net.narusas.cafelibrary.LibrarySerializePolicySet;
import net.narusas.cafelibrary.LibrarySerializer;
import net.narusas.cafelibrary.LibrarySerilizePolicy;
import net.narusas.cafelibrary.LibraryStorage;
import net.narusas.cafelibrary.bookfactories.DaumPlusNaverBookFactory;
import net.narusas.cafelibrary.serial.EveryLibraryChangeSerializePolicy;
import net.narusas.cafelibrary.serial.FileLibraryStorage;
import net.narusas.cafelibrary.serial.HTML2FTPLibraryStorage;
import net.narusas.cafelibrary.serial.HTMLSerializer;
import net.narusas.cafelibrary.serial.XMLLoader;
import net.narusas.cafelibrary.serial.XMLSerializer;

public class Setup {
	public void setup(ApplicationModel model) {
		LibraryStorage storage = new FileLibraryStorage(new File("library.xml"));

		LibraryLoader.setPlugin(new XMLLoader(storage));
		Library lib = LibraryLoader.getInstance().load();
		model.setLibrary(lib);

		LibrarySerializer serializer = new XMLSerializer();
		LibrarySerilizePolicy policy = new EveryLibraryChangeSerializePolicy(lib);
		LibrarySerializePolicySet set = new LibrarySerializePolicySet("File", serializer, storage, policy);
		set.setLibrary(lib);

		LibrarySerializePolicySet.addPlugin(set);

		serializer = new HTMLSerializer();
		storage = new HTML2FTPLibraryStorage();
		policy = LibrarySerilizePolicy.ACTION_POLICY;
		set = new LibrarySerializePolicySet("FTP", serializer, storage, policy);
		set.setLibrary(lib);

		LibrarySerializePolicySet.addPlugin(set);

		BookFactory.addPlugin(new DaumPlusNaverBookFactory());
	}
}
