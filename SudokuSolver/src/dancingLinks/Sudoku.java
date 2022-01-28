
package dancingLinks;

import java.util.Scanner;
import java.util.Stack;

public class Sudoku {
    Stack<Node> workingSolution;
    boolean isSolved;
    ExactCoverList exactCoverList;

    public Sudoku() {
        exactCoverList = new ExactCoverList();
        workingSolution = new Stack<>();
    }

    public void game() {
        isSolved = false;
        while(!workingSolution.empty()) {
            workingSolution.pop();
        }
        Stack<Node> initialNodes = readField();

        if (solve()) {
            System.out.println("\nsolved\n");
        } else {
            System.out.println("\nnope\n");
        }
        uncoverInitialNodes(initialNodes);
        printSolution();
    }

    private Stack<Node> readField() {
        Stack<Node> initialNodes = new Stack<>();
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                int newValue = in.nextInt();
                if (newValue != 0) {
                    processNewValue(i, j, newValue, initialNodes);
                }
            }
        }
        return initialNodes;
    }

    private void processNewValue(int rowNumber, int colNumber, int newValue, Stack<Node> initialNodes) {
        Node newNode = exactCoverList.find(new Node(rowNumber, colNumber, newValue - 1));
        exactCoverList.cover(newNode.headerNode);
        for (Node nextRowNode = newNode.right; nextRowNode != newNode; nextRowNode = nextRowNode.right) {
            exactCoverList.cover(nextRowNode.headerNode);
        }
        initialNodes.push(newNode);
        workingSolution.push(newNode);
    }

    private boolean solve() {
        if (exactCoverList.isEmpty()) {
            return true;
        } else {
            HeaderNode nextHeader = pickConstraint();
            exactCoverList.cover(nextHeader);
            for (Node nextNodeInCol = nextHeader.down; nextNodeInCol != nextHeader && !isSolved; nextNodeInCol = nextNodeInCol.down) {
                workingSolution.push(nextNodeInCol);
                for (Node nextRowNode = nextNodeInCol.right; nextRowNode != nextNodeInCol; nextRowNode = nextRowNode.right) {
                    exactCoverList.cover(nextRowNode.headerNode);
                }
                isSolved = solve();

                if (!isSolved) {
                    workingSolution.pop();
                }
                for (Node nextRowNode = nextNodeInCol.right; nextRowNode != nextNodeInCol; nextRowNode = nextRowNode.right) {
                    exactCoverList.uncover(nextRowNode.headerNode);
                }
            }
            exactCoverList.uncover(nextHeader);
            return isSolved;
        }
    }

    private HeaderNode pickConstraint() {
        int minNodesCount = -1;
        HeaderNode mostSuitableColumn = exactCoverList.root.rightHeader;

        for (HeaderNode nextHeader = mostSuitableColumn; nextHeader != exactCoverList.root; nextHeader = nextHeader.rightHeader) {
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

    private void uncoverInitialNodes(Stack<Node> initialNodes) {
        while(!initialNodes.empty()) {
            Node nextNode = initialNodes.peek();
            HeaderNode header = nextNode.headerNode;

            for (Node nextRowNode = nextNode.right; nextRowNode != nextNode; nextRowNode = nextRowNode.right) {
                exactCoverList.uncover(nextRowNode.headerNode);
            }
            exactCoverList.uncover(header);
            initialNodes.pop();
        }
    }

    private void printSolution() {
        int[][] result = new int[9][9];
        while (!workingSolution.empty()) {
            result[workingSolution.peek().rowNumber][workingSolution.peek().columnNumber] = workingSolution.peek().value + 1;
            workingSolution.pop();
        }
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                System.out.print(result[i][j]+" ");
            }
            System.out.println();
        }
    }
}
