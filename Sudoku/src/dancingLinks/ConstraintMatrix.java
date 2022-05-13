package dancingLinks;

public class ConstraintMatrix {
    private final int height = 729;
    private final int width = 324;
    public int getHeight() { return height; }
    public int getWidth() { return width; }

    private Node rowConstraint;
    private Node colConstraint;
    private Node boxConstraint;
    private Node cellConstraint;

    public Node[][] matrix;

    public ConstraintMatrix() {
        matrix = new Node[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                matrix[i][j] = null;
            }
        }
        makeMatrix();
    }

    /* matrixRowIndex:
        000
        001
        002
        ...
        010
        ...
        888
    */
    private void makeMatrix() {
        for (int rowNumber = 0; rowNumber < 9; ++rowNumber) {
            for (int colNumber = 0; colNumber < 9; ++colNumber) {
                for (int value = 0; value < 9; ++value) {
                    int matrixRowIndex = rowNumber * 81 + colNumber * 9 + value;
                    fillMatrixRow(matrixRowIndex, rowNumber, colNumber, value);
                    makeLinksInRow();
                }
            }
        }
    }

    /*
    строка		  столбец	    куб         ячейка
	1-9			  1-9			1-9         1-81
	каждой по 9   каждой по 9   каждой по 9
     */
    private void fillMatrixRow(int matrixRowIndex, int rowNumber, int colNumber, int value) {
        rowConstraint = matrix[matrixRowIndex][calcRowConstraintIndex(rowNumber, value)] =
                new Node(rowNumber, colNumber, value);
        colConstraint = matrix[matrixRowIndex][calcColConstraintIndex(colNumber, value)] =
                new Node(rowNumber, colNumber, value);
        boxConstraint = matrix[matrixRowIndex][calcBoxConstraintIndex(rowNumber, colNumber, value)] =
                new Node(rowNumber, colNumber, value);
        cellConstraint = matrix[matrixRowIndex][calcCellConstraintIndex(rowNumber, colNumber)] =
                new Node(rowNumber, colNumber, value);
    }

    private int calcRowConstraintIndex(int row, int val) {
        return row * 9 + val;
    }

    private int calcColConstraintIndex(int col, int val) {
        // colOffset
        return 81 + (col * 9 + val);
    }

    private int calcBoxConstraintIndex(int row, int col, int val) {
        // boxOffset
        return 162 + ((row / 3 + col / 3 * 3) * 9 + val);
    }

    private int calcCellConstraintIndex(int row, int col) {
        // cellOffset
        return 243 + (row * 9 + col);
    }


    private void makeLinksInRow() {
        rowConstraint.left = cellConstraint;
        rowConstraint.right = colConstraint;

        colConstraint.left = rowConstraint;
        colConstraint.right = boxConstraint;

        boxConstraint.left = colConstraint;
        boxConstraint.right = cellConstraint;

        cellConstraint.left = boxConstraint;
        cellConstraint.right = rowConstraint;
    }
}
