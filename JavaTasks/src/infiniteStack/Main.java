package infiniteStack;

<<<<<<< HEAD
public class Main {
    public static void main(String[] args){
        StackNode stackNode1 = new StackNode("1");
        StackNode stackNode2 = new StackNode("2");
        StackNode stackNode3 = new StackNode("3");
        StackNode stackNode4 = new StackNode("4");

        InfiniteStack infiniteStack = new InfiniteStack();
        infiniteStack.push(stackNode1);
        infiniteStack.push(stackNode2);
        infiniteStack.push(stackNode3);
        infiniteStack.push(stackNode4);

        infiniteStack.pop();
        infiniteStack.pop();
=======
import java.util.Iterator;


public class Main {
    public static void main(String[] args){

        InfiniteStack<String> infiniteStack = new InfiniteStack<String>();
        infiniteStack.push("1");
        infiniteStack.push("2");
        infiniteStack.push("3");
        infiniteStack.push("4");
        infiniteStack.push("5");

        System.out.println(infiniteStack.peek());

        infiniteStack.pop();
        System.out.println(infiniteStack.peek());

        infiniteStack.push("hi");

        System.out.println(infiniteStack.peek());

        Iterator iterator = infiniteStack.iterator();
        while (iterator.hasNext()){
            StackNode<String> stackNode = (StackNode<String>)iterator.next();
            System.out.println(stackNode.getValue());
        }

        infiniteStack.pop();
        infiniteStack.pop();
        System.out.println(infiniteStack.peek());


>>>>>>> 5448757c8858910ab4d958e2a15ee05f11bd5268
    }
}
