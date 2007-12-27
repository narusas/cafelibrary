package net.narusas.cafelibrary;

import net.narusas.cafelibrary.serial.ActionPolicy;

public abstract class LibrarySerilizePolicy {
	private LibrarySerializePolicySet set;

	public void set(LibrarySerializePolicySet set) {
		this.set = set;
	}

	protected void fire(Object param) {
		if (set == null) {
			return;
		}
		set.serialize(param);
	}

	public final static ActionPolicy ACTION_POLICY = new ActionPolicy();
}
