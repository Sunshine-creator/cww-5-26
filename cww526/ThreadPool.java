package cww526;
import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {
           //不适用线程池
        System.out.println("送到北京");
        System.out.println("送到富平");
        System.out.println("自己处理");
           //使用手动建立线程
        new Thread(()->{
            System.out.println("送到北京");
        }).start();
        new Thread(()->{
            System.out.println("送到富平");
        }).start();
        new Thread(()->{
            System.out.println("自己处理");
        }).start();
           //使用jdk线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                 4,     //创建的线程数，比如员工，线程
                 10,    //总员工，正式工和临时工
                 60,      // 空闲时间
//                  10，
//                  60，
                TimeUnit.SECONDS,   //时间单位
                new ArrayBlockingQueue<>(1000),  //阻塞队列，仓库库存，线程库
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {   //线程类型，创建线程方式，Ruannable 表示包裹
                        return new Thread(r);
                    }
                },
//                new ThreadPoolExecutor.CallerRunsPolicy()    //库存满了以后的阻塞的手段
//                new ThreadPoolExecutor().DiscardOldestPolicy
                new ThreadPoolExecutor.DiscardPolicy()
//              new ThreadPoolExecutor.AbortPolicy()
        ); //创建线程池
        pool.execute(()->{       //创建任务,yonglam表达式，main线程运行，公司客户
            System.out.println("送到北京");
        });
        pool.execute(()->{   //公司的接口
            System.out.println("送到富平");
        });
        pool.execute(()->{
            System.out.println("自己处理");
        });
        ExecutorService pool1 =Executors.newSingleThreadExecutor(); //单线性池
//        ExecutorService pool2 =Executors.
    }
}
