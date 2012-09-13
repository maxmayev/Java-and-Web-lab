package cheese;

/**
 * @author Stanislav Chetvertkov
 *         Date: 29.06.12
 *         Time: 22:20
 */
public class Main {

    static CheeseStore cheeseStore = new CheeseStore();

    public static void main(String[] args){

        CustomerRunnable customerRunnable = new CustomerRunnable();
        customerRunnable.setCheeseStore(cheeseStore);
        customerRunnable.setName("greedy 1");

        CustomerRunnable customerRunnable2 = new CustomerRunnable();
        customerRunnable2.setCheeseStore(cheeseStore);
        customerRunnable2.setName("greedy 2");

        CustomerRunnable customerRunnable3 = new CustomerRunnable();
        customerRunnable3.setCheeseStore(cheeseStore);
        customerRunnable3.setName("reg 3");

        CustomerRunnable customerRunnable4 = new CustomerRunnable();
        customerRunnable4.setCheeseStore(cheeseStore);
        customerRunnable4.setName("reg 4");

        Thread greedyThread = new Thread(customerRunnable);
        greedyThread.setPriority(10);
        Thread greedyThread2 = new Thread(customerRunnable2);
        greedyThread2.setPriority(10);

        Thread regularThread = new Thread(customerRunnable3);
        regularThread.setPriority(2);

        Thread regularThread2 = new Thread(customerRunnable4);
        regularThread2.setPriority(2);

        greedyThread.start();
        greedyThread2.start();
        regularThread.start();
        regularThread2.start();

    }
}
