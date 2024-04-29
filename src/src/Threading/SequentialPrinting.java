package Threading;

public class SequentialPrinting {
    private static final Object lock = new Object();
    private static int nextNumber = 1;
    private static int maxNumber = 10;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> printNumber("t1"));
        Thread t2 = new Thread(() -> printNumber("t2"));
        Thread t3 = new Thread(() -> printNumber("t3"));

        t1.start();
        t2.start();
        t3.start();
    }

    private static void printNumber(String threadName) {
        synchronized (lock) {
            while (nextNumber <= maxNumber) {
                // Check if it's this thread's turn to print
                if ((threadName.equals("t1") && nextNumber % 3 == 1) ||
                    (threadName.equals("t2") && nextNumber % 3 == 2) ||
                    (threadName.equals("t3") && nextNumber % 3 == 0)) {
                    System.out.println(threadName + " " + nextNumber);
                    nextNumber++;
                    lock.notifyAll(); // Notify all threads waiting on lock
                } else {
                    try {
                        lock.wait(); // Wait for other threads to print
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
