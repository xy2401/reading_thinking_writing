package com.xy2401.example.concurrency;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 参考 http://blog.csdn.NET/lmj623565791/article/details/27250059
 * 将Executor和BlockingQueue功能融合在一起，可以将Callable的任务提交给它来执行， 然后使用take()方法获得已经完成的结果
 * 
 * JDK并发工具包CompletionService和ExecutorCompletionService的好处和使用场景
 * http://blog.csdn.net/aitangyong/article/details/38169383?utm_source=tuicool&utm_medium=referral
 * 
 * @author zhy
 * 
 */
public class CompletionServiceDemo {

	// 阻塞队列
	final BlockingQueue<Future<Integer>> blockingQueue = null;

	final BlockingQueue<Future<Integer>> blockingQueue1 = new LinkedBlockingDeque<Future<Integer>>();// 类比LinkedList

	// 睡美人数量 最大年纪 最长睡眠时间 超时时间
	public static int AleepingBeauty_NUM = 10, Max_age = 20, Max_Sleep_time = 1000, TimeOut = 500;

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService exec = Executors.newFixedThreadPool(AleepingBeauty_NUM);

		CompletionService<Integer> completionService = null;

		completionService = new ExecutorCompletionService<Integer>(exec);

		long t1 = System.currentTimeMillis();

		for (int i = 0; i < AleepingBeauty_NUM; i++) {
			// 随机一个 睡美人i 0~20岁 睡眠时间0~1000
			completionService.submit(
					new AleepingBeauty("睡美人" + i, new Random().nextInt(20), new Random().nextInt(Max_Sleep_time)));
		}

		long t2 = System.currentTimeMillis();
		System.out.println("任务添加完成");

		for (int i = 0; i < 10; i++) {
			try {
				// 谁最先执行完成，直接返回
				Future<Integer> f = completionService.take();
				f.get(100, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		long t3 = System.currentTimeMillis();

		System.out.println("任务添加时间:" + (t2 - t1));
		System.out.println("任务添加完成:" + (t3 - t2));

		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
		exec.shutdown();

	}

}
