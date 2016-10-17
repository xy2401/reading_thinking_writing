package com.xy2401.example.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 使用 CompletionService 可以一个一个的获取返回结果。如果需要来一个处理一个的话
 * invokeAll(tasks)阻塞完全结束后才会继续执行 返回例子 不着急先處理完成的話
 * 
 *start
 *submit1
 *submit2
 *pool-1-thread-1  睡美人1 18岁  睡了  1000 
 *completionService.take() 1
 *future.get() 1
 *pool-1-thread-2  睡美人2 17岁  睡了  10000
 *completionService.take() 2
 *future.get() 2 
 *completionService end
 *add aleepingBeauty1
 *add aleepingBeauty2
 *pool-1-thread-3  睡美人1 18岁  睡了  1000
 *pool-1-thread-4  睡美人2 17岁  睡了  10000
 *exec.invokeAll
 *futures.get(0).get()
 *futures.get(1).get()
 * 
 * @author xy
 *
 */

 

public class AleepingBeautyDemo2 {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("start");

		ExecutorService exec = Executors.newFixedThreadPool(10);

		// 18岁 睡眠时间1000毫秒的公主
		AleepingBeauty aleepingBeauty1 = new AleepingBeauty("睡美人1", 18, 1000);
		// 18岁 睡眠时间10000毫秒的公主
		AleepingBeauty aleepingBeauty2 = new AleepingBeauty("睡美人2", 17, 10000);

		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec);

		// 获取结果
		try {
			completionService.submit(aleepingBeauty1);
			System.out.println("submit1");
			completionService.submit(aleepingBeauty2);
			System.out.println("submit2");
			Future<Integer> future = completionService.take();
			System.out.println("completionService.take() 1");
			future.get();
			System.out.println("future.get() 1");
			future = completionService.take();
			System.out.println("completionService.take() 2");
			future.get();
			System.out.println("future.get() 2");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("completionService end");

		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		tasks.add(aleepingBeauty1);
		System.out.println("add aleepingBeauty1");
		tasks.add(aleepingBeauty2);
		System.out.println("add aleepingBeauty2");

		List<Future<Integer>> futures = exec.invokeAll(tasks);
		System.out.println("exec.invokeAll");
		try {
			futures.get(0).get();
			System.out.println("futures.get(0).get()");
			futures.get(1).get();
			System.out.println("futures.get(1).get()");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
