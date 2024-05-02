package Threading.Executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureExecutorClass {

    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.HOURS, new ArrayBlockingQueue<>(10));

        poolExecutor.allowCoreThreadTimeOut(true);

        // runnable with returing null always
        Future<?> futureObj = poolExecutor.submit(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            System.out.println("This is the task that thread will do");
        });

        System.out.println(futureObj.state());
        System.out.println(futureObj.isDone());

        try {
            futureObj.get(3, TimeUnit.SECONDS); // waits for task completion for given time
        } catch (InterruptedException | ExecutionException | TimeoutException e) {

            e.printStackTrace();
        }

        // futureObj.cancel(true);

        try {
            futureObj.get(); // waits for task completion then continues
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(futureObj.state());
        System.out.println(futureObj.isDone());
        System.out.println(futureObj.isCancelled());

        // runnable with returning actual value

        List<Integer> output = new ArrayList<>();
        Future<List<Integer>> futureOutput = poolExecutor.submit(new RunnableToReturnValue(output), output);
        try {
            System.out.println("Result from runnable " + futureOutput.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // callable example

        Future<List<Integer>> future = poolExecutor.submit(() -> { // more cleaner way to do this instead of runnable with task 
            List<Integer> list = new ArrayList<>(); 
            System.out.println("do something");
            list.add(2000);
            return list;
        });

        try {
            System.out.println("Result from callable " + future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
