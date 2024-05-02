package Threading;

import java.util.LinkedList;
import java.util.Queue;

class QueueThread<T> {

    Queue<T> queue;
    int capacity;

    QueueThread(int capacity) {
        queue = new LinkedList<>();
        this.capacity = capacity;
    }

    public synchronized void produce(T data) {
        while (capacity == queue.size()) {
            try {
                System.out.println("Queue is waiting to consume due to capacity");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        queue.add(data);
        System.out.println("prodcued " + data);
        notify();

    }

    public synchronized void consume() {

        while (queue.size() == 0) {
            try {
                System.out.println("Queue is empty so waiting for producer to add something");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T data = queue.poll();
        notify();
        System.out.println("consumed " + data);

    }

}

public class ProducerConsumerQueue {

    public static void main(String[] args) {

        QueueThread<Integer> queueThread = new QueueThread<>(10);

        Thread produceThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                queueThread.produce(i);
            }
        });

        Thread consumThread = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                queueThread.consume();
            }
        });

        produceThread.start();
        consumThread.start();

        System.out.println("Main thread");
    }
}