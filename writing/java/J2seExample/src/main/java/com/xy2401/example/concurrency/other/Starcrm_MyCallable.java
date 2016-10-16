package com.xy2401.example.concurrency.other;

import java.util.concurrent.Callable;

/**
 * http://www.cnblogs.com/starcrm
 * 
 * @author xy
 *
 */
public class Starcrm_MyCallable implements Callable<String> {

	private long waitTime;

	public Starcrm_MyCallable(int timeInMillis) {
		this.waitTime = timeInMillis;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(waitTime);
		return Thread.currentThread().getName();
	}

}