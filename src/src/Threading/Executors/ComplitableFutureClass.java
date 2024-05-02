package Threading.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ComplitableFutureClass {
    public static void main(String[] args) {

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1));

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("doing something ");
            return "Hello ";
        })
                .thenApplyAsync((String val) -> {
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return val + " this is";
                })
                .thenApplyAsync((String val) -> {
                    return val + " complitable future example ";
                });

        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        completableFuture.thenAccept((String val) -> {
            System.out.println(val);
        }); // now we cant chain it further because it returs void

        CompletableFuture<String> completableFuture1st = CompletableFuture.supplyAsync(() -> {
            return "1st result";
        });

        CompletableFuture<String> completableFuture2nd = CompletableFuture.supplyAsync(() -> {
            return "2nd result";
        });

        CompletableFuture<String> completableFutureFinal = completableFuture1st.thenCombine(completableFuture2nd,
                (String res1, String res2) -> {
                    return res1 + " " + res2;
                });

        try {
            System.out.println(completableFutureFinal.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}
