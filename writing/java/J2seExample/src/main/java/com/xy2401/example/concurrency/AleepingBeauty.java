package com.xy2401.example.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * 睡美人
 * 
 * @author xy
 *
 */
public class AleepingBeauty implements Callable<Integer>, Comparable<AleepingBeauty> {

	String name;
	int age;
	int sleepTime;// 睡眠時間
	int maxSleepTime = 900;// 规定最大睡眠时间
	boolean exception = false;// 睡过头会 主动 自动抛出异常

	public AleepingBeauty(String name, int age, int sleepTime) {
		super();
		this.name = name;
		this.age = age;
		this.sleepTime = sleepTime;
	}

	public AleepingBeauty() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer call() throws Exception {
		Thread.sleep(sleepTime);
		System.out.println(Thread.currentThread().getName() + "  " + name + " " + age + "岁  睡了  " + sleepTime);
		if (exception && sleepTime > maxSleepTime) {
			throw new TimeoutException("不好意思睡过头的");
		}
		return sleepTime;
	}

	@Override
	public int compareTo(AleepingBeauty other) {
		return age - other.age;
	}

}
