package Threading.Executors;

import java.util.List;

public class RunnableToReturnValue implements Runnable {

    List<Integer> list;

    RunnableToReturnValue(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {

        list.add(300);
    }

}
