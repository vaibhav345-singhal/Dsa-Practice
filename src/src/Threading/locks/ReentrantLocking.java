package Threading.locks;

import java.util.concurrent.locks.ReentrantLock;

class Producer {

    boolean isAvailable = false;

    public void produce(ReentrantLock reentrantLock) {

        try {
            reentrantLock.lock();
            System.out.println("Lock Acquired by " + Thread.currentThread().getName());
            isAvailable = true;

            Thread.sleep(5000);

        } catch (Exception e) {

        } finally {
            reentrantLock.unlock();
            System.out.println("Lock released by " + Thread.currentThread().getName());
        }

    }
}

public class ReentrantLocking {

    public static void main(String[] args) {

        Producer producer = new Producer();

        Producer producer1 = new Producer();

        ReentrantLock reentrantLock = new ReentrantLock();

        Thread th1 = new Thread(() -> {
            producer.produce(reentrantLock);
        });

        Thread th2 = new Thread(() -> {
            producer1.produce(reentrantLock);
        });

        th1.start();
        th2.start();
    }
}