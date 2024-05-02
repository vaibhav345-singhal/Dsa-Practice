package Threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CounterNew counter = new CounterNew();

        PrinterNew printerNew = new PrinterNew(counter);
        PrinterNew printerNew2 = new PrinterNew(counter);

        printerNew.setName("1");
        printerNew2.setName("2");

        executorService.submit(printerNew);
        executorService.submit(printerNew2);

        executorService.shutdown();

       
    }
}