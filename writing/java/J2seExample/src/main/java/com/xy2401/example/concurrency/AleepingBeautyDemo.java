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
 * 使用 CompletionService 和  invokeAll 使用  get(500, TimeUnit.MILLISECONDS) 均无法 捕获超过时间异常
 * @author xy
 *
 */
public class AleepingBeautyDemo {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("start");

		ExecutorService exec = Executors.newFixedThreadPool(10);

		// 18岁 睡眠时间1000毫秒的公主
		AleepingBeauty aleepingBeauty = new AleepingBeauty("睡美人", 18, 1000);
		FutureTask<Integer> futureTask = new FutureTask<Integer>(aleepingBeauty);

		exec.execute(futureTask);
		// 获取结果
		try {
			System.out.println("future.get():" + futureTask.get());
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}

		// 获取带超时的结果 exec.execute(future); 可以捕获到超时异常
		futureTask = new FutureTask<Integer>(aleepingBeauty);
		exec.execute(futureTask);
		try {
			System.out.println("future.get(500, TimeUnit.MILLISECONDS):" + futureTask.get(500, TimeUnit.MILLISECONDS));
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}

		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exec);

		// 获取结果
		try {
			completionService.submit(aleepingBeauty);
			Future<Integer> future = completionService.take();
			System.out.println("completionService future.get():" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		// 带超时返回异常。并不会捕获异常
		try {
			completionService.submit(aleepingBeauty);
			Future<Integer> future = completionService.take();
			System.out.println("completionService future.get(500, TimeUnit.MILLISECONDS):"
					+ future.get(500, TimeUnit.MILLISECONDS));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 利用本身抛出的异常。 可以捕获异常
		try {
			aleepingBeauty.exception = true;// 会主动抛出异常的公主
			completionService.submit(aleepingBeauty);
			Future<Integer> future = completionService.take();
			System.out.println("completionService future.get(500, TimeUnit.MILLISECONDS):" + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("end");

		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		aleepingBeauty.exception = false;
		tasks.add(aleepingBeauty);

		System.out.println("exec.invokeAll");
		try {
			System.out.println("get()");
			exec.invokeAll(tasks).get(0).get();
			System.out.println(" get() end");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {// 无法捕获异常
			System.out.println("get(500, TimeUnit.MILLISECONDS)");
			exec.invokeAll(tasks).get(0).get(500, TimeUnit.MILLISECONDS);
			System.out.println(" get() end");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("exception = true");
		try {// 可以捕获异常
			aleepingBeauty.exception = true;
			System.out.println("exception get( )");
			exec.invokeAll(tasks).get(0).get();
			System.out.println("exception get() end");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
