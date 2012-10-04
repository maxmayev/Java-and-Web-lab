package cheeseStore;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {
    public static void main(String[] args) {
        PriorityBlockingQueue<CheeseOrder> queue =
                new PriorityBlockingQueue<CheeseOrder>(4,
                        new Comparator<CheeseOrder>() {
                            @Override
                            public int compare(CheeseOrder o1, CheeseOrder o2) {
                                return o1.compareTo(o2);
                            }
                        });


        Thread a = new Thread(new Producer(queue));
        Thread b = new Thread(new Consumer(queue));
        a.start();
        b.start();


    }

}