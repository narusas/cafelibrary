package net.narusas.cafelibrary;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;

import net.narusas.cafelibrary.serial.ActionPolicy;
import net.narusas.cafelibrary.serial.EveryLibraryChangeSerializePolicy;
import net.narusas.cafelibrary.serial.PeriodicSerializePolicy;
import junit.framework.TestCase;

public class LibraryManagerTest extends TestCase {
	public void testSerializeWorkFlow() throws InterruptedException {
		Library lib = new Library();
		MockLibSerializer serializer = new MockLibSerializer();
		MockLibStorage storage = new MockLibStorage();
		MockSerializePolicy policy = new MockSerializePolicy();
		LibrarySerializePolicySet set = new LibrarySerializePolicySet("dummy", serializer, storage, policy);
		set.setLibrary(lib);

		policy.timeIsCome(); // call back
		Thread.sleep(50); // 백그라운드 쓰래드에서 작업이 이루어지니 잠시 기다린다.

		assertEquals(15, storage.serializedLibrary[0]);
	}

	public void testEveryLibraryChangePolicy() throws InterruptedException {
		Library lib = new Library();
		MockLibSerializer serializer = new MockLibSerializer();
		MockLibStorage storage = new MockLibStorage();
		LibrarySerilizePolicy policy = new EveryLibraryChangeSerializePolicy(lib);

		LibrarySerializePolicySet set = new LibrarySerializePolicySet("dummy", serializer, storage, policy);
		set.setLibrary(lib);

		lib.add(new Book("abc"));

		Thread.sleep(50); // 백그라운드 쓰래드에서 작업이 이루어지니 잠시 기다린다.

		assertEquals(15, storage.serializedLibrary[0]);
	}

	public void testPeriodicPolicy() throws InterruptedException {
		Library lib = new Library();
		MockLibSerializer serializer = new MockLibSerializer();
		MockLibStorage storage = new MockLibStorage();

		long periodSeconds = 1;
		LibrarySerilizePolicy policy = new PeriodicSerializePolicy(periodSeconds);

		LibrarySerializePolicySet set = new LibrarySerializePolicySet("dummy", serializer, storage, policy);
		set.setLibrary(lib);

		Thread.sleep(1100); // 백그라운드 쓰래드에서 작업이 이루어지니 잠시 기다린다.
		assertEquals(15, storage.serializedLibrary[0]);
	}

	public void testActionPolicy() throws InterruptedException {
		Library lib = new Library();
		MockLibSerializer serializer = new MockLibSerializer();
		MockLibStorage storage = new MockLibStorage();

		ActionPolicy policy = new ActionPolicy();
		LibrarySerializePolicySet set = new LibrarySerializePolicySet("dummy", serializer, storage, policy);
		set.setLibrary(lib);

		policy.actionPerformed(new ActionEvent(this, 0, null));

		Thread.sleep(50); // 백그라운드 쓰래드에서 작업이 이루어지니 잠시 기다린다.

		assertEquals(15, storage.serializedLibrary[0]);
	}
}

class MockLibSerializer implements LibrarySerializer {
	public byte[] serialize(Library lib) {
		return new byte[] { 15 };
	}
}

class MockLibStorage implements LibraryStorage {
	byte[] serializedLibrary;

	public void write(byte[] serializedLibrary, Object param) {
		this.serializedLibrary = serializedLibrary;
	}

	public InputStream read() throws IOException {
		return null;
	};
}

class MockSerializePolicy extends LibrarySerilizePolicy {
	public void timeIsCome() {
		fire(null);
	}

}