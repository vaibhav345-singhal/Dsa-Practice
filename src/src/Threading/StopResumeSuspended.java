package Threading;

class SharedResources {

    boolean isAvailable = false;

    public synchronized void produce() {

        System.out.println("Lock Accquired by " + Thread.currentThread().getName());
        try {
            Thread.sleep(8000);
        } catch (Exception e) {

        }
        isAvailable = true;
        System.out.println("Lock Released by " + Thread.currentThread().getName());

    }

}

public class StopResumeSuspended {

    // these three methods are deprecated because they do not release lock after
    // stopping or getting suspended
    public static void main(String[] args) {

        SharedResources sharedResources = new SharedResources();

        Thread th1 = new Thread(() -> {
            System.out.println("Thread 1 calling producer method ");
            sharedResources.produce();
        });

        Thread th2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {

            }
            System.out.println("Thread 2 calling producer method ");
            sharedResources.produce();
        });

        th1.start();
        th2.start();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {

        }

        System.out.println("Thread 0 is suspended ");

        th1.suspend();

        System.out.println("Main thread finished its work");

    }
}
