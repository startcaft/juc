package startcaft.juc.www;

import juc.framework.branchs.BranchThread;
import juc.framework.branchs.Task;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.concurrent.*;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class JucFrameworkBranchsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JucFrameworkBranchsApplication.class, args);

		//doTasks();
		//创建线程池
		ExecutorService es = Executors.newSingleThreadExecutor();
		//创建Callable对象任务
		BranchThread<Integer> branchThread = new BranchThread<Integer>(new Task<Integer>() {
			@Override
			public Integer getResult() {
				try {
					Thread.sleep(2000);
					System.out.println("模拟子线程运算");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return 100;
			}
		});
		//创建FutureTask
		FutureTask<Integer> futureTask=branchThread.getFutureTask();
		//执行任务
		es.submit(futureTask);

		BranchThread<Integer> branchThread1 = new BranchThread<Integer>(new Task<Integer>() {
			@Override
			public Integer getResult() {
				try {
					Thread.sleep(2000);
					System.out.println("模拟子线程运算");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return 200;
			}
		});
		//创建FutureTask
		FutureTask<Integer> futureTask1=branchThread1.getFutureTask();
		//执行任务
		es.submit(futureTask1);
		//关闭线程池
		es.shutdown();
		try {
			Thread.sleep(3000);
			System.out.println("主线程在执行其他任务");

			if(futureTask.get()!=null){
				//输出获取到的结果
				System.out.println("futureTask.get()-->"+futureTask.get());
			}else{
				//输出获取到的结果
				System.out.println("futureTask.get()未获取到结果");
			}

			if(futureTask1.get()!=null){
				//输出获取到的结果
				System.out.println("futureTask1.get()-->"+futureTask1.get());
			}else{
				//输出获取到的结果
				System.out.println("futureTask1.get()未获取到结果");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("主线程在执行完成");
	}
}
