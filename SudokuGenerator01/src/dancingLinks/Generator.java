package dancingLinks;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    int toHideCount;
    //public void setToHideCount(int value) { toHideCount = value; }

    private int[][] data;
    public int[][] getData() { return data; }

    private int[][] fullData;
    public int[][] getFullData() { return fullData; }

    private final Random rand;

    public Generator(int toHideCount) {
        this.toHideCount = toHideCount;
        rand = new Random();
        data = new int[9][9];
        generateInputData();
        generate();
    }

    private void generateInputData() {
        ArrayList<Integer> alreadyExist = new ArrayList<>();
        for (int j = 0; j < 9; ++j) {
            int nextValue = rand.nextInt(1, 10);
            while (alreadyExist.contains(nextValue)) {
                nextValue = rand.nextInt(1, 10);
            }
            alreadyExist.add(nextValue);
            data[0][j] = nextValue;
        }
        for (int i = 1; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                data[i][j] = 0;
            }
        }
    }

    public void generate() {
        Sudoku sudoku = new Sudoku();
        sudoku.inputData = data;
        sudoku.game();
        data = sudoku.convertSolutionToArray();
        fullData = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            System.arraycopy(data[i], 0, fullData[i], 0, 9);
        }
        hideCells();
        sudoku.printSolution(data);
    }

    // hard = 60
    // middle = 50
    // easy = 40
    private void hideCells() {
        int hideTmp = toHideCount;
        ArrayList<IndexPair> existedPairs = new ArrayList<>();
        while (hideTmp != 0) {
            IndexPair pair = IndexPair.generateRandom();
            while (existedPairs.contains(pair)) {
                pair = IndexPair.generateRandom();
            }
            existedPairs.add(pair);
            data[pair.getRowNumber()][pair.getColNumber()] = 0;
            --hideTmp;
        }
    }
}
