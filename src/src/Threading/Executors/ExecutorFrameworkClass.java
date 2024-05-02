package Threading.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorFrameworkClass {

    public static void main(String[] args) {

        // ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 1,
        // TimeUnit.MINUTES,
        // new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new
        // ThreadPoolExecutor.AbortPolicy());

        // or

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 2000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10), new CustomThreadFactory(), new CustomRejectedHandler());

        for (int i = 0; i < 25; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Thread Name " + Thread.currentThread().getName());
            });
        }
        
        if(!threadPoolExecutor.isShutdown()){
            threadPoolExecutor.shutdown();
        }
    }
}

class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(false);
        t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

}

class CustomRejectedHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("Task denied " + r.toString());
    }

}