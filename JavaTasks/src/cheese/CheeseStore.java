package cheese;


public class CheeseStore {

    Integer cheeseSold = 0;

    public synchronized void getCheese(String name) {
        cheeseSold++;
        System.out.println("cheese sold:"+cheeseSold + name);
    }
}
