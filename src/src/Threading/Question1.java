package Threading;

class Printer implements Runnable {

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            System.out.println("Hello From Thread " + Thread.currentThread().getName());
        }
    }
}

public class Question1 {

    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread t1 = new Thread(printer);

        Thread t2 = new Thread(printer);

        t1.setName("1");
        t2.setName("2");

        t1.start();
        t2.start();
    }
}
