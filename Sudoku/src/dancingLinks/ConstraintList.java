package dancingLinks;

public class ConstraintList {
    private final ConstraintMatrix constraintMatrix;
    public HeaderNode root = new HeaderNode();

    public ConstraintList() {
        root.rightHeader = root.leftHeader = root;
        root.down = root.up = root;
        constraintMatrix = new ConstraintMatrix();
        makeLinksInColumns();
    }

    private void makeLinksInColumns() {
        for (int j = 0; j < constraintMatrix.getWidth(); ++j) {
            HeaderNode nextHeader = new HeaderNode();
            nextHeader.rightHeader = nextHeader.leftHeader = nextHeader.headerNode = nextHeader;
            nextHeader.down = nextHeader.up = nextHeader;
            Node previousNode = nextHeader;

            for (int i = 0; i < constraintMatrix.getHeight(); ++i) {
                if (constraintMatrix.matrix[i][j] != null) {
                    constraintMatrix.matrix[i][j].up = previousNode;
                    previousNode.down = constraintMatrix.matrix[i][j];
                    constraintMatrix.matrix[i][j].down = nextHeader;
                    nextHeader.up = constraintMatrix.matrix[i][j];
                    constraintMatrix.matrix[i][j].headerNode = nextHeader;
                    previousNode = constraintMatrix.matrix[i][j];
                }
            }
            addColumn(nextHeader, root);
        }
    }

    private void addColumn(HeaderNode newHeader, HeaderNode lastHeader) {
        if (lastHeader.rightHeader == root && lastHeader != newHeader) {
            lastHeader.rightHeader.leftHeader = newHeader;
            newHeader.rightHeader = lastHeader.rightHeader;
            newHeader.leftHeader = lastHeader;
            lastHeader.rightHeader = newHeader;
        } else {
            addColumn(newHeader, lastHeader.rightHeader);
        }
    }

    protected Node find(Node toFind) {
        for (HeaderNode nextInRow = root.rightHeader; nextInRow != root; nextInRow = nextInRow.rightHeader) {
            for (Node nextInColumn = nextInRow.down; nextInColumn != nextInRow; nextInColumn = nextInColumn.down) {
                if (nextInColumn.rowNumber == toFind.rowNumber && nextInColumn.columnNumber == toFind.columnNumber && nextInColumn.value == toFind.value) {
                    return nextInColumn;
                }
            }
        }
        return null;
    }

    protected boolean isEmpty() {
        return root.rightHeader == root;
    }

    protected void cover(HeaderNode header) {
        header.rightHeader.leftHeader = header.leftHeader;
        header.leftHeader.rightHeader = header.rightHeader;

        for (Node nextRowNode = header.down; nextRowNode != header; nextRowNode = nextRowNode.down) {
            for (Node nextColNode = nextRowNode.right; nextColNode != nextRowNode; nextColNode = nextColNode.right) {
                nextColNode.up.down = nextColNode.down;
                nextColNode.down.up = nextColNode.up;
            }
        }

    }

    protected void uncover(HeaderNode header) {
        for (Node nextRowNode = header.up; nextRowNode != header; nextRowNode = nextRowNode.up) {
            for (Node nextColNode = nextRowNode.left; nextColNode != nextRowNode; nextColNode = nextColNode.left) {
                nextColNode.down.up = nextColNode;
                nextColNode.up.down = nextColNode;
            }
        }
        header.rightHeader.leftHeader = header;
        header.leftHeader.rightHeader = header;
    }
}
