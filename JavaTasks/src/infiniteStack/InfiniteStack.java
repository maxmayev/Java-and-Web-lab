package infiniteStack;

import java.util.*;


public class InfiniteStack<T> extends Stack<T> {

    private StackNode<T> currentNode;

    @Override
    public synchronized T pop(){
        if (currentNode!=null){
            if (currentNode.getBottomNode()!=null){
                StackNode<T> oldNode = currentNode;
                currentNode = currentNode.getBottomNode();
                return oldNode.getValue();
            }else{
                T toReturn = currentNode.getValue();
                currentNode = null;
            }
        }
        return null;
    }

    @Override
    public synchronized T peek(){
        if (currentNode!=null){
            return currentNode.getValue();
        }else {
            return null;
        }
    }

    @Override
    public synchronized T push(T node){
        StackNode<T> stackNode = new StackNode<T>(node);
        stackNode.setBottomNode(this.currentNode);
        this.currentNode = stackNode;
        return node;
    }

    public StackNode getCurrentNode(){
        return this.currentNode;
    }

    public void setCurrentNode(StackNode stackNode){
        this.currentNode = stackNode;
    }

    @Override
    public boolean empty() {
        return currentNode==null;
    }

    @Override
    public int size() {
        int size = 0;
        StackNode iterationNode = currentNode;

        while (iterationNode!=null){
            size++;
            iterationNode = iterationNode.getBottomNode();
        }

        return size;
    }

    @Override
    public boolean contains(Object o) {
        T valueObject = (T)o;

        boolean contains;

        StackNode iterationNode = currentNode;
        while (iterationNode!=null){
            contains = iterationNode.getBottomNode().getValue().equals(valueObject);
            if (contains){
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<StackNode<T>>() {
            @Override
            public boolean hasNext() {
                return currentNode.getBottomNode()!=null;
            }

            @Override
            public StackNode<T> next() {
                StackNode<T> oldNode = currentNode;
                currentNode = currentNode.getBottomNode();
                return oldNode;
            }

            @Override
            public void remove() {
                pop();
            }
        };
    }

}
