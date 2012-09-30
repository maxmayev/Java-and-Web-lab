package infiniteStack;

import com.sun.istack.internal.Nullable;


public class StackNode<T> {

    private T value;

    @Nullable
    private StackNode bottomNode;

    public StackNode(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    public StackNode getBottomNode(){
        return bottomNode;
    }

    public void setBottomNode(StackNode bottomNode){
        this.bottomNode = bottomNode;
    }
}
