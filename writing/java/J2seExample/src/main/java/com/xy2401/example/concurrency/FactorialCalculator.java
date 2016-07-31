package com.xy2401.example.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * reference:http://ifeve.com/thread-executors-4/
 * @author xy
 *
 */
public class FactorialCalculator implements Callable<Integer> {
	private Integer number;

	public FactorialCalculator(Integer number) {
		this.number = number;
	}

	/**
	 * 计算阶乘
	 */
	@Override
	public Integer call() throws Exception { 
		int result = 1;
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.printf("%s: %d\n", Thread.currentThread().getName(), result); 
		return result;
	}

	public static void main(String[] args) {
		
//		newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
//		newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//		newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
//		newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
		
		//是未某个用户开辟一个线程池(Session范围)还是整个web项目一个线程池Application范围 TODO 大概Spring有相关的线程池吧。
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
		List<Future<Integer>> resultList = new ArrayList<>();
		
		Random random = new Random();

		for (int i = 0; i < 10; i++) {
			Integer number = random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Integer> result = executor.submit(calculator);
			resultList.add(result);
		}

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//反复使用get方法,轮询任务完成情况
		do {
			System.out.printf("Main: Number of Completed Tasks:%d\n", executor.getCompletedTaskCount());
			for (int i = 0; i < resultList.size(); i++) {
				Future<Integer> result = resultList.get(i);
				System.out.printf("Main: Task %d: %s\n", i, result.isDone());
			}
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (executor.getCompletedTaskCount() < resultList.size());
		
		
		System.out.printf("Main: Results\n");
		for (int i = 0; i < resultList.size(); i++) {
			Future<Integer> result = resultList.get(i);
			Integer number = null;
			try {
				number = result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Main: Task %d: %d\n", i, number);
		}
		executor.shutdown(); 
	}

}
