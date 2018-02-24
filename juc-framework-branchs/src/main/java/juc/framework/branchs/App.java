package juc.framework.branchs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;


/**
 * @Author: pikai
 * @Description:
 * @Date: Created in 2018/2/22 16:56
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        test1();

        new App().test2();

        try {
            Thread.sleep(3000);
            System.out.println("主线程在执行其他任务");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续...");
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource(){
        System.out.println("-------------------------------------");
        DataSource dataSource =
                DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        System.out.println("-------------------------------------");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void test2(){
        TaskThread taskThread = new TaskThread("select count(*) from act_id_group",
                new DefaultTask(this.jdbcTemplate));

        TaskThread taskThread2 = new TaskThread("select count(*) from act_id_user",
                new DefaultTask(this.jdbcTemplate));

        TaskThread<Integer> taskThread3 = new TaskThread("select count(*) from act_id_membership",
                new DefaultTask(this.jdbcTemplate));


        Set<TaskThread>  taskThreads = new HashSet<>();
        taskThreads.add(taskThread);
        taskThreads.add(taskThread2);
        taskThreads.add(taskThread3);
        TaskDispatch taskDispatch = new TaskDispatch(taskThreads);


        taskDispatch.doTasks();
    }


    public static void test1(){
        TaskThread<Integer> taskThread = new TaskThread<Integer>("sql", new Task<Integer>() {
            @Override
            public Integer getResult(String sql) {
                try {
                    Thread.sleep(1000);
                    System.out.println("模拟数据统计1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 100;
            }
        });

        TaskThread<Integer> taskThread2 = new TaskThread<Integer>("sql", new Task<Integer>() {
            @Override
            public Integer getResult(String sql) {
                try {
                    Thread.sleep(1000);
                    System.out.println("模拟数据统计2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 200;
            }
        });

        TaskThread<Integer> taskThread3 = new TaskThread<Integer>("sql", new Task<Integer>() {
            @Override
            public Integer getResult(String sql) {
                try {
                    Thread.sleep(1000);
                    System.out.println("模拟数据统计3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 300;
            }
        });


        Set<TaskThread>  taskThreads = new HashSet<>();
        taskThreads.add(taskThread);
        taskThreads.add(taskThread2);
        taskThreads.add(taskThread3);
        TaskDispatch taskDispatch = new TaskDispatch(taskThreads);


        taskDispatch.doTasks();

    }
}
