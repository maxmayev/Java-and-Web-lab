package cheese;

/**
 * @author Stanislav Chetvertkov
 *         Date: 29.06.12
 *         Time: 22:55
 */
public class CheeseStore {

    Integer cheeseSold = 0;

    public synchronized void getCheese(String name) {
        cheeseSold++;
        System.out.println("cheese sold:"+cheeseSold + name);
    }
}
