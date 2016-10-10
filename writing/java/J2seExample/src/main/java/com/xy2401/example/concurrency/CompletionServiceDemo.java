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
 *  JDK并发工具包CompletionService和ExecutorCompletionService的好处和使用场景 
 * http://blog.csdn.net/aitangyong/article/details/38169383?utm_source=tuicool&utm_medium=referral
 * 
 * @author zhy
 * 
 */
public class CompletionServiceDemo {

	// 阻塞队列
	final BlockingQueue<Future<Integer>> blockingQueue = null;

	final BlockingQueue<Future<Integer>> blockingQueue1 = new LinkedBlockingDeque<Future<Integer>>();// 类比LinkedList

	final BlockingQueue<Future<Integer>> blockingQueue2 = new ArrayBlockingQueue<Future<Integer>>(AleepingBeauty_NUM);// 类比ArrayList

	final BlockingQueue<Future<Integer>> blockingQueue3 = new PriorityBlockingQueue<Future<Integer>>();// 优先队列

	final BlockingQueue<Future<Integer>> blockingQueue4 = new SynchronousQueue<Future<Integer>>();// 其中每个
																									// put
																									// 必须等待一个
																									// take

	final BlockingQueue<Future<Integer>> blockingQueue5 = new LinkedBlockingDeque<Future<Integer>>(10);

	// 睡美人数量 最大年纪 最长睡眠时间 超时时间
	public static int AleepingBeauty_NUM = 10, Max_age = 20, Max_Sleep_time = 1000, TimeOut = 500;

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		CompletionServiceDemo CompletionServiceDemo = new CompletionServiceDemo();

		System.out.println("\n默认");// 即默认为 LinkedBlockingQueue
		CompletionServiceDemo.BlockingQueue(CompletionServiceDemo.blockingQueue);

//		System.out.println("\nLinkedBlockingDeque");
//		CompletionServiceDemo.BlockingQueue(CompletionServiceDemo.blockingQueue1);
//
//		System.out.println("\nArrayBlockingQueue");
//		CompletionServiceDemo.BlockingQueue(CompletionServiceDemo.blockingQueue2);
//
//		// TODO 有问题 没有搞明白 不管。
//		// The ThreadPoolExecutor transforms the Runnable you submit into a
//		// RunnableFuture using the newTaskFor() method, and adds this
//		// RunnableFuture to the queue. So if you want to use a PriorityQueue,
//		// you should override the newTaskFor() method to make sure the
//		// RunnableFuture instances it creates are comparable.
//		// Or you could also use the execute() method instead of the submit()
//		// method, to bypass the task creation.
//
//		// System.out.println("\nPriorityBlockingQueue");
//		// CompletionServiceDemo.BlockingQueue(CompletionServiceDemo.blockingQueue3);
//
//		System.out.println("\nSynchronousQueue");
//		CompletionServiceDemo.BlockingQueue(CompletionServiceDemo.blockingQueue4);
	}

	public void BlockingQueue(BlockingQueue<Future<Integer>> _blockingQueue) {

		/**
		 * 内部维护11个线程的线程池
		 */
		ExecutorService exec = Executors.newFixedThreadPool(AleepingBeauty_NUM);

		CompletionService<Integer> completionService = null;
		if (_blockingQueue == null) {
			completionService = new ExecutorCompletionService<Integer>(exec);

		} else {
			completionService = new ExecutorCompletionService<Integer>(exec, _blockingQueue);

		}

		long t1 = System.currentTimeMillis();

		/**
		 * 模拟瞬间产生10个任务，且每个任务执行时间不一致
		 */
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
				f.get(TimeOut, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
		long t3 = System.currentTimeMillis();

		System.out.println("任务添加时间:" + (t2 - t1));
		System.out.println("任务添加完成:" + (t3 - t2));

		// 启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。
		exec.shutdown();

	}

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
			return sleepTime;
		}

		@Override
		public int compareTo(AleepingBeauty other) {
			return age - other.age;
		}

	}

}
