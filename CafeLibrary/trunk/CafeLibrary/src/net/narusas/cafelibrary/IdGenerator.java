package net.narusas.cafelibrary;

import java.util.prefs.Preferences;

public abstract class IdGenerator {
	static IdGenerator instance;

	public static long newId() {
		return getInstance().createId();
	}

	abstract protected long createId();

	private static IdGenerator getInstance() {
		if (instance == null) {
			instance = new PreferenceIdGenerator();
		}
		return instance;
	}

	public static void setLastId(long id) {
		getInstance().setLastIdReally(id);
	}

	abstract protected void setLastIdReally(long id);
}

class PreferenceIdGenerator extends IdGenerator {

	
	protected synchronized long createId() {
		long id = Preferences.userRoot().node("libcafe").getLong("lastId", 0);
		id++;
		setLastIdReally(id);
		return id;
	}

	
	protected void setLastIdReally(long id) {
		Preferences.userRoot().node("libcafe").putLong("lastId", id);
	}
}
