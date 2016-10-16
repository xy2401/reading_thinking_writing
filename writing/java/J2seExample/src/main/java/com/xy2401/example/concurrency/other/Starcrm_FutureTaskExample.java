package com.xy2401.example.concurrency.other;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * （原创）Callable、FutureTask中阻塞超时返回的坑点
 * http://www.cnblogs.com/starcrm/p/5010863.html
 * 
 * @author xy
 *
 */
public class Starcrm_FutureTaskExample {
	public static void main(String[] args) {
		Starcrm_MyCallable callable1 = new Starcrm_MyCallable(1000);
		Starcrm_MyCallable callable2 = new Starcrm_MyCallable(2000);

		FutureTask<String> futureTask1 = new FutureTask<String>(callable1);
		FutureTask<String> futureTask2 = new FutureTask<String>(callable2);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(futureTask1);
		executor.execute(futureTask2);

		while (true) {
			try {
				if (futureTask1.isDone() && futureTask2.isDone()) {
					System.out.println("Done");
					// shut down executor service
					executor.shutdown();
					return;
				}

				if (!futureTask1.isDone()) {
					// 阻塞futureTask1
					System.out.println("FutureTask1 output=" + futureTask1.get());
				}

				if (!futureTask2.isDone()) {
					// 阻塞futureTask2
					System.out.println("FutureTask2 output=" + futureTask2.get());
				}

			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// do nothing
			}
		}

	}
}
