package Threading.locks;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class ShareadResources {

    boolean isAvailable = false;

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void consume() {
        try {
            readWriteLock.readLock().lock();
            System.out.println("Read Lock acquired by " + Thread.currentThread().getName());
            System.out.println(isAvailable);
        } catch (Exception e) {

        } finally {
            System.out.println("Read Lock Released By " + Thread.currentThread().getName());
            readWriteLock.readLock().unlock();
        }
    }

    public void produce() {
        try {

            readWriteLock.writeLock().lock();
            System.out.println("Write Lock acquired by " + Thread.currentThread().getName());
            Thread.sleep(3000);
            isAvailable = true;

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("Write Lock Released By " + Thread.currentThread().getName());
            readWriteLock.writeLock().unlock();
        }
    }
}

public class ReadWriteLock {

    public static void main(String[] args) {

        ShareadResources shareadResource = new ShareadResources();

        Thread t1 = new Thread(() -> {
            shareadResource.consume();
        });

        Thread t2 = new Thread(() -> {
            shareadResource.consume();
        });
        Thread t3 = new Thread(() -> {
            shareadResource.produce();
        });

        t3.start();

        t1.start();
        t2.start();
    }
}
