//simulate a traffic signal

package Threading;

class Signal extends Thread {

    private String threadName;

    public Signal(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Signal is " + threadName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

public class Question3 {

    public static void main(String[] args) throws InterruptedException {
        Signal s1 = new Signal("Red");
        Signal s2 = new Signal("Yellow");
        Signal s3 = new Signal("Green");

        s1.start();

        s1.join();

        s2.start();

        s2.join();

        s3.start();

    }
}
