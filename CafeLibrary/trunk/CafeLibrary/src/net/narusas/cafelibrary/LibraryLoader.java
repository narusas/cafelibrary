package net.narusas.cafelibrary;

public abstract class LibraryLoader {
	private static LibraryLoader instance;
	protected LibraryStorage storage;

	public abstract Library load();

	public LibraryLoader(LibraryStorage storage) {
		this.storage = storage;
	}

	public static void setPlugin(LibraryLoader plugin) {
		instance = plugin;
	}

	public static LibraryLoader getInstance() {
		return instance;
	}

}
