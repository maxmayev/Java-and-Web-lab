package infiniteStack;

<<<<<<< HEAD

public class StackNode {
    private String value;
    private StackNode bottomNode;

    public StackNode(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

=======
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


>>>>>>> 5448757c8858910ab4d958e2a15ee05f11bd5268
    public StackNode getBottomNode(){
        return bottomNode;
    }

    public void setBottomNode(StackNode bottomNode){
        this.bottomNode = bottomNode;
    }
}
