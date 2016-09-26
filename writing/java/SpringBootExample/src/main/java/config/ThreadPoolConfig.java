package config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 线程池配置
 * @author xy
 *
 */
@Configuration
public class ThreadPoolConfig {
	
	@Bean 
	public ExecutorService fixedThreadPool() { 
		ExecutorService  executor =  Executors.newFixedThreadPool(1000);
		
		return executor;
	}
	
	/**
	 * Spring 线程池
	 * http://docs.spring.io/spring-framework/docs/3.0.x/javadoc-api/org/springframework/scheduling/concurrent/ThreadPoolTaskExecutor.html
	 * @return
	 */
	@Bean 
	public ThreadPoolTaskExecutor taskExecutor() { 
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();

		
//		配置解释
//		当一个任务通过execute(Runnable)方法欲添加到线程池时：
//		1、 如果此时线程池中的数量小于corePoolSize，即使线程池中的线程都处于空闲状态，也要创建新的线程来处理被添加的任务。
//		2、 如果此时线程池中的数量等于 corePoolSize，但是缓冲队列 workQueue未满，那么任务被放入缓冲队列。
//		3、如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量小于maximumPoolSize，建新的线程来处理被添加的任务。
//		4、 如果此时线程池中的数量大于corePoolSize，缓冲队列workQueue满，并且线程池中的数量等于maximumPoolSize，那么通过 handler所指定的策略来处理此任务。也就是：处理任务的优先级为：核心线程corePoolSize、任务队列workQueue、最大线程 maximumPoolSize，如果三者都满了，使用handler处理被拒绝的任务。
//		5、 当线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止。这样，线程池可以动态的调整池中的线程数。
 
									//		Set the capacity for the ThreadPoolExecutor's BlockingQueue. Default is Integer.MAX_VALUE.
									//		Any positive value will lead to a LinkedBlockingQueue instance; any other value will lead to a SynchronousQueue instance. 
		pool.setQueueCapacity(200); // 线程池所使用的缓冲队列   
									//		Set the ThreadPoolExecutor's core pool size. Default is 1.
									//		This setting can be modified at runtime, for example through JMX. 
		pool.setCorePoolSize(5);    //线程池维护线程的最少数量 
									//		Set the ThreadPoolExecutor's maximum pool size. Default is Integer.MAX_VALUE.
									//		This setting can be modified at runtime, for example through JMX. 
		pool.setMaxPoolSize(1000);    //线程池维护线程的最大数量   
									//		Set the ThreadPoolExecutor's keep-alive seconds. Default is 60.
									//		This setting can be modified at runtime, for example through JMX. 
        pool.setKeepAliveSeconds(30000);  //线程池维护线程所允许的空闲时间 
									//		Set whether to wait for scheduled tasks to complete on shutdown.
									//		Default is "false". Switch this to "true" if you prefer fully completed tasks at the expense of a longer shutdown phase. 
//		pool.setWaitForTasksToCompleteOnShutdown(true);
	   
		return pool;

	}
}
