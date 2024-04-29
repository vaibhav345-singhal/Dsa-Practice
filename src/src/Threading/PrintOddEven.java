package Threading;

class CounterNew {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class PrinterNew extends Thread {

    private CounterNew counter;
    private final int max = 1000;

    public PrinterNew(CounterNew counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        while (counter.getCount() < max) {
            synchronized (this) {
                if (getName().equals("1") && counter.getCount() % 2 != 0) {
                    System.out.println("Thread " + getName() + "  counter is " + counter.getCount());
                    counter.increment();
                }

                else if (getName().equals("2") && counter.getCount() % 2 == 0) {
                    System.out.println("Thread " + getName() + "  counter is " + counter.getCount());
                    counter.increment();
                }
            }
        }
    }

}

public class PrintOddEven {

    public static void main(String[] args) {

        CounterNew counter = new CounterNew();

        PrinterNew podd = new PrinterNew(counter);
        PrinterNew peven = new PrinterNew(counter);

        podd.setName("1");
        peven.setName("2");

        podd.start();
        peven.start();
    }

}
