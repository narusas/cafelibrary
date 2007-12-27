package net.narusas.cafelibrary.ui.bookshelf;

public class BookShelfConstraints {
	static float zoom = 1.0f;

	static final int WIDTH = 72;
	static final int HEIGHT = 100;

	static final int DROP_WIDTH = 80;
	static final int DROP_HEIGHT = 110;
	static int width = WIDTH;
	static int height = HEIGHT;

	static int dropWidth = DROP_WIDTH;
	static int dropHeight = DROP_HEIGHT;

	private static int dropGapWidth;

	private static int dropGapHeight;

	private static int backWeight = 18;

	private static int backGap = 2;

	private static int backWidth = 16;
	static {
		setZoom(1.0f);
	}

	public static int getBackWeight() {
		return backWeight;
	}

	public static void setBackWeight(int backWieght) {
		BookShelfConstraints.backWeight = backWieght;
		backWidth = backWeight - backGap;
	}

	public static float getZoom() {
		return zoom;
	}

	public static void setZoom(float zoom) {
		BookShelfConstraints.zoom = zoom;
		width = (int) (WIDTH * zoom);
		height = (int) (HEIGHT * zoom);

		dropWidth = (int) (DROP_WIDTH * zoom);
		dropHeight = (int) (DROP_HEIGHT * zoom);

		dropGapWidth = (dropWidth - width) / 2;
		dropGapHeight = dropHeight - height;
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public static int getDropWidth() {
		return dropWidth;
	}

	public static int getDropHeight() {
		return dropHeight;
	}

	public static int getDropGapHeight() {
		return dropGapHeight;
	}

	public static int getDropGapWidth() {
		return dropGapWidth;
	}

	public static int getBackWidth() {
		return backWidth;
	}

	public static int getBackGap() {
		return backGap;
	}

	public static void setBackGap(int backGap) {
		BookShelfConstraints.backGap = backGap;
		backWidth = backWeight - backGap;
	}
}
