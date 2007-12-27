package net.narusas.cafelibrary;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarySerializePolicySet {

	private final LibrarySerializer serializer;
	private final LibraryStorage storage;
	private final LibrarySerilizePolicy policy;
	private Library lib;

	static Logger logger = Logger.getLogger("log");

	static ExecutorService pool = Executors.newCachedThreadPool();

	static List<LibrarySerializePolicySet> sets = new LinkedList<LibrarySerializePolicySet>();
	private final String name;

	public static void addPlugin(LibrarySerializePolicySet set) {
		sets.add(set);
	}

	public static List<LibrarySerializePolicySet> listPlugins() {
		return Collections.unmodifiableList(sets);
	}

	public LibrarySerializePolicySet(String name, LibrarySerializer serializer, LibraryStorage storage,
			LibrarySerilizePolicy policy) {
		this.name = name;
		this.serializer = serializer;
		this.storage = storage;
		this.policy = policy;
		policy.set(this);
	}

	public void setLibrary(Library lib) {
		this.lib = lib;
	}

	public void serialize(final Object param) {
		if (lib == null) {
			return;
		}
		pool.execute(new Runnable() {
			public void run() {
				byte[] data = null;
				synchronized (lib) {
					data = serializer.serialize(lib);
				}
				try {
					storage.write(data, param);  
				} catch (IOException e) {
					logger.log(Level.WARNING, "라이브러리를 저장할때 문제가 발생했습니다. " + e.getMessage(), e);
					e.printStackTrace();
				}
			}
		});
	}

	public static LibrarySerializePolicySet getPlugin(String name) {
		for (LibrarySerializePolicySet set : sets) {
			if (set.getName().equals(name)) {
				return set;
			}

		}
		return null;

	}

	private String getName() {
		return name;
	}

	public LibrarySerializer getSerializer() {
		return serializer;
	}

	public LibraryStorage getStorage() {
		return storage;
	}

	public LibrarySerilizePolicy getPolicy() {
		return policy;
	}
}
