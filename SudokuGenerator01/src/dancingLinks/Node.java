
package dancingLinks;

public class Node {
    Node up;
    Node down;
    Node left;
    Node right;
    HeaderNode headerNode;
    int value;
    int rowNumber;
    int columnNumber;

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
