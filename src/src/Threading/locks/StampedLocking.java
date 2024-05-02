package Threading.locks;

import java.util.concurrent.locks.StampedLock;

class ShareadResourcess {

    int isAvailable = 10;

    StampedLock stampedLock = new StampedLock();

    public void produce() {
        System.out.println("Write Lock acquired by " + Thread.currentThread().getName());

        long stamp = stampedLock.writeLock();
        try {

            isAvailable = 12;

        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.out.println("Write Lock Released By " + Thread.currentThread().getName());
            stampedLock.unlock(stamp);
        }
    }

    public void consume() {
        System.out.println("Optimistic Lock acquired by " + Thread.currentThread().getName());
        long stamp = stampedLock.tryOptimisticRead();
        try {

            isAvailable = 11;
            Thread.sleep(6000);

            if (stampedLock.validate(stamp)) {
                System.out.println("Updated value with optimistic " + Thread.currentThread().getName());
            } else {
                System.out.println("Rollback " + Thread.currentThread().getName());
                isAvailable = 10;
            }

            System.out.println(isAvailable);
        } catch (Exception e) {

        }
    }

}

public class StampedLocking {
    public static void main(String[] args) {

        ShareadResourcess shareadResource = new ShareadResourcess();

        Thread t1 = new Thread(() -> {
            shareadResource.consume();
        });

        Thread t3 = new Thread(() -> {
            shareadResource.produce();
        });

        t1.start();
        // t2.start();
        t3.start();

    }
}
