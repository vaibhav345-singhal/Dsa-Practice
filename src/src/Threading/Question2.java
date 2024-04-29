// print three threads and each should start when previous is done 

package Threading;

class Thread2 extends Thread {

    @Override
    public void run() {
        System.out.println("started thread " + getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Thread end " + getName());
    }
}

public class Question2 {

    public static void main(String[] args) {

        Thread2 thread = new Thread2();
        Thread2 thread1 = new Thread2();
        Thread2 thread2 = new Thread2();

        thread.start();

        try {
            thread.join();
            thread1.start();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            thread1.join();
            thread2.start();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
