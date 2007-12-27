package net.narusas.cafelibrary.serial;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.narusas.cafelibrary.LibrarySerilizePolicy;

public class PeriodicSerializePolicy extends LibrarySerilizePolicy implements Runnable {

	private ScheduledThreadPoolExecutor scheduledExecutor;

	public PeriodicSerializePolicy(long periodSeconds) {
		scheduledExecutor = new ScheduledThreadPoolExecutor(1);
		scheduledExecutor.scheduleWithFixedDelay(this, 0, periodSeconds, TimeUnit.SECONDS);
	}

	public void run() {
		fire(null);
	}


}
