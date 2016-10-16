package com.xy2401.example.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureTaskDemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {// 使用Callable接口作为构造参数
			public String call() {

				for (int i = 0; i < Long.MAX_VALUE; i++) {

				}

				return "";
				// 真正的任务在这里执行，这里的返回值类型为String，可以为任意类型
			}
		});
		executor.execute(future);
		// 在这里可以做别的任何事情
		try {
			future.get(5000, TimeUnit.MILLISECONDS); // 取得结果，同时设置超时执行时间为5秒。同样可以用future.get()，不设置执行超时时间取得结果
		} catch (InterruptedException e) {
			e.printStackTrace();
			future.cancel(true);
		} catch (ExecutionException e) {
			e.printStackTrace();
			future.cancel(true);
		} catch (TimeoutException e) {
			e.printStackTrace();
			future.cancel(true);
		} finally {
			executor.shutdown();
		}
		
		System.out.println("finally");
	}
}
