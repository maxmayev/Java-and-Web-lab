package infiniteStack;


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

    public StackNode getBottomNode(){
        return bottomNode;
    }

    public void setBottomNode(StackNode bottomNode){
        this.bottomNode = bottomNode;
    }
}
