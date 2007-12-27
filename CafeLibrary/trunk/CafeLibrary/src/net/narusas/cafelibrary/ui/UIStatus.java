package net.narusas.cafelibrary.ui;

public class UIStatus {
	private static boolean isBGUpdate = false;

	public static boolean isBGUpdate() {
		return isBGUpdate;
	}

	public static void setBGUpdate(boolean v) {
		isBGUpdate = v;
	}
}
