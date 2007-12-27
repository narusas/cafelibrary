package net.narusas.cafelibrary;

import java.io.Writer;

public interface LibrarySerializer {

	byte[] serialize(Library lib);
}
