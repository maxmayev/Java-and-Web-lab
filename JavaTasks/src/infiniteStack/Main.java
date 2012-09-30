package infiniteStack;

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


    }
}
