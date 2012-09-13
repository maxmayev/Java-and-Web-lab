package infiniteStack;

/**
 * @author Stanislav Chetvertkov
 *         Date: 27.06.12
 *         Time: 18:25
 */
public class InfiniteStack {

    private StackNode currentNode;

    public void pop(){
        StackNode oldNode = this.currentNode;
        this.currentNode = currentNode.getBottomNode();
        oldNode = null;
    }

    public void push(StackNode node){
        node.setBottomNode(this.currentNode);
        this.currentNode = node;
    }

    public StackNode getCurrentNode(){
        return this.currentNode;
    }

    public void setCurrentNode(StackNode stackNode){
        this.currentNode = stackNode;
    }
}
