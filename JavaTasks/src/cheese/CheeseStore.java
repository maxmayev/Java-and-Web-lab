package cheeseStore;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue<CheeseOrder> queue;

    public Producer(PriorityBlockingQueue<CheeseOrder> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while(true) {
                Thread.sleep(100);

                int greedyCustomerCount = 2;
                int normalCustomerCount = 2;

                for(CheeseOrder cheeseOrder : queue){
                    if (cheeseOrder.getGreed() == 200){
                        greedyCustomerCount--;
                    }else if (cheeseOrder.getGreed() == 100){
                        normalCustomerCount--;
                    }
                }

                if (greedyCustomerCount>0){
                    queue.put(produceCheeseOrder(100));
                } else if (normalCustomerCount>0){
                    queue.put(produceCheeseOrder(200));
                }

            }
        }
        catch(InterruptedException e) {
        }
    }

    private CheeseOrder produceCheeseOrder(int priority) {
        CheeseOrder futurePet = new CheeseOrder(priority);
        System.out.println(new Date() + " put");
        return futurePet;
    }


}

public class CheeseOrder implements Comparable<CheeseOrder> {

    private int greed;

    public CheeseOrder(int newCost) {
        greed = newCost;
    }

    public int compareTo(CheeseOrder f) {
        Integer ourCost = getGreed();
        Integer otherCost = f.getGreed();
        return otherCost.compareTo(ourCost);

    }

    public int getGreed() {
        return greed;
    }
}
