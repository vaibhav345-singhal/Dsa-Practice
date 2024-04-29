package Threading;

class Counter {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public int getCount() {
        return counter;
    }
}

class Thread1 implements Runnable {

    private Counter counter;

    public Thread1(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {

        // synchronized(this){
        for (int i = 0; i < 10000; i++) {
            counter.increment();
        }
        // }
    }
}

public class DemoThread {

    public static void main(String[] args) {

        Counter counter = new Counter();

        Thread thread1 = new Thread(new Thread1(counter));
        thread1.start();

        Thread thread12 = new Thread(new Thread1(counter));
        thread12.start();

        try {
            thread1.join();
            thread12.join();

            System.out.println(counter.getCount());

        } catch (Exception e) {

        }

    }

}
