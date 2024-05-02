package Threading.locks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareadResource {

    Queue<Integer> queue;
    int capacity;

    ShareadResource(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public void produce(ReentrantLock lock, Condition condition, int data) {
        try {

            lock.lock();

            while (queue.size() == capacity) {
                System.out.println("Waiting for consumer " + Thread.currentThread().getName());
                condition.await();
            }

            queue.add(data);
            System.out.println("produced " + data);
            condition.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
            System.out.println("Lock released by " + Thread.currentThread().getName());
        }
    }

    public void consume(ReentrantLock lock, Condition condition) {

        try {

            lock.lock();

            while (queue.size() == 0) {
                System.out.println("waiting for producer " + Thread.currentThread().getName());
                condition.await();
            }

            int data = queue.poll();
            System.out.println("consumed " + data);
            condition.signal();

        } catch (Exception e) {

        } finally {
            lock.unlock();
            System.out.println("Lock released by " + Thread.currentThread().getName());
        }
    }

}

public class ProducerConsumerUsingReentrantLock {

    public static void main(String[] args) {

        ShareadResource shareadResource = new ShareadResource(10);

        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        Thread th1 = new Thread(() -> {

            for (int i = 0; i < 20; i++) {
                shareadResource.produce(reentrantLock, condition, i);
            }

        });

        Thread th2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                shareadResource.consume(reentrantLock, condition);
            }
        });

        th1.setName("Producer");
        th2.setName("Consumer");

        th1.start();
        th2.start();

    }
}
