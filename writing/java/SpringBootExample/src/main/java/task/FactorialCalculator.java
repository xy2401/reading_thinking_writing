package task;

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
 * 计算阶乘
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
		//System.out.printf("%s: %d\n", Thread.currentThread().getName(), result); 
		return result;
	}
 

}
