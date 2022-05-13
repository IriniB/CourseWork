
package dancingLinks;

public class Node {
    protected Node up;
    protected Node down;
    protected Node left;
    protected Node right;
    protected HeaderNode headerNode;
    protected int value;
    protected int rowNumber;
    protected int columnNumber;

    public Node(int rowNumber, int columnNumber, int value) {
        this();
        this.value = value;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public Node() {
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
        this.headerNode = null;
    }
}
