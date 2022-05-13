
package dancingLinks;

import java.util.Stack;

public class Sudoku {
    private final Stack<Node> workingSolution;
    private boolean isSolved;
    private final ConstraintList constraintList;
    public int[][] inputData;
    private final Stack<Node> initialNodes;

    public Sudoku() {
        constraintList = new ConstraintList();
        workingSolution = new Stack<>();
        inputData = new int[9][9];
        initialNodes = new Stack<>();
    }

    public boolean preSolvingProcessing() {
        isSolved = false;
        while(!workingSolution.empty()) {
            workingSolution.pop();
        }
        if (!processInputData()) {
            return false;
        }
        boolean returnValue = solve();
        uncoverInitialNodes();
        return returnValue;
    }

    public boolean processInputData() {
        while(!workingSolution.empty()) {
            workingSolution.pop();
        }
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                int newValue = inputData[i][j];
                if (newValue != 0) {
                    if (!processNewValue(i, j, newValue)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean processNewValue(int rowNumber, int colNumber, int newValue) {
        Node newNode = constraintList.find(new Node(rowNumber, colNumber, newValue - 1));
        if (newNode == null) {
            return false;
        }
        constraintList.cover(newNode.headerNode);
        for (Node nextRowNode = newNode.right; nextRowNode != newNode; nextRowNode = nextRowNode.right) {
            constraintList.cover(nextRowNode.headerNode);
        }
        initialNodes.push(newNode);
        workingSolution.push(newNode);
        return true;
    }

    private boolean solve() {
        if (constraintList.isEmpty()) {
            return true;
        } else {
            HeaderNode nextHeader = pickConstraint();
            constraintList.cover(nextHeader);
            for (Node nextNodeInCol = nextHeader.down; nextNodeInCol != nextHeader && !isSolved; nextNodeInCol = nextNodeInCol.down) {
                workingSolution.push(nextNodeInCol);
                for (Node nextRowNode = nextNodeInCol.right; nextRowNode != nextNodeInCol; nextRowNode = nextRowNode.right) {
                    constraintList.cover(nextRowNode.headerNode);
                }
                isSolved = solve();

                if (!isSolved) {
                    workingSolution.pop();
                }
                for (Node nextRowNode = nextNodeInCol.right; nextRowNode != nextNodeInCol; nextRowNode = nextRowNode.right) {
                    constraintList.uncover(nextRowNode.headerNode);
                }
            }
            constraintList.uncover(nextHeader);
            return isSolved;
        }
    }

    private HeaderNode pickConstraint() {
        int minNodesCount = -1;
        HeaderNode mostSuitableColumn = constraintList.root.rightHeader;

        for (HeaderNode nextHeader = mostSuitableColumn; nextHeader != constraintList.root; nextHeader = nextHeader.rightHeader) {
            int nodesCounter = 0;
            for (Node nextRowNode = nextHeader.down; nextRowNode != nextHeader; nextRowNode = nextRowNode.down) {
                ++nodesCounter;
            }

            if (nodesCounter < minNodesCount || minNodesCount == -1) {
                mostSuitableColumn = nextHeader;
                minNodesCount = nodesCounter;
            }
        }
        return mostSuitableColumn;
    }

    private void uncoverInitialNodes() {
        while(!initialNodes.empty()) {
            Node nextNode = initialNodes.peek();
            HeaderNode header = nextNode.headerNode;

            for (Node nextRowNode = nextNode.right; nextRowNode != nextNode; nextRowNode = nextRowNode.right) {
                constraintList.uncover(nextRowNode.headerNode);
            }
            constraintList.uncover(header);
            initialNodes.pop();
        }
    }

    public int[][] convertSolutionToArray() {
        int[][] result = new int[9][9];
        while (!workingSolution.empty()) {
            result[workingSolution.peek().rowNumber][workingSolution.peek().columnNumber] = workingSolution.peek().value + 1;
            workingSolution.pop();
        }
        return result;
    }

    public void printSolution(int[][] result) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
