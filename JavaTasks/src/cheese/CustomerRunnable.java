package cheeseStore;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue<CheeseOrder> queue;

    public Consumer(BlockingQueue<CheeseOrder> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                CheeseOrder cheeseOrder = queue.take();
                Thread.sleep(50);
                System.out.println(new Date() + " take");
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}