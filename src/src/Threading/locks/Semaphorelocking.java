package Threading.locks;

import java.util.concurrent.Semaphore;

class Resource {
    boolean isAvailable = false;

    Semaphore semaphore = new Semaphore(2);

    public void produce() {
        try {
            semaphore.acquire();
            System.out.println("Acquired by " + Thread.currentThread().getName());

            isAvailable = true;
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            System.out.println("Released by " + Thread.currentThread().getName());
            semaphore.release();

        }
    }
}

public class Semaphorelocking {

    public static void main(String[] args) {

        Resource resource = new Resource();

        Thread t1 = new Thread(() -> {
            resource.produce();
        });
        Thread t2 = new Thread(() -> {
            resource.produce();
        });
        Thread t3 = new Thread(() -> {
            resource.produce();
        });
        Thread t4 = new Thread(() -> {
            resource.produce();
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}
