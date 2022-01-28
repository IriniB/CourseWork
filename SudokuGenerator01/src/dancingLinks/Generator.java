package dancingLinks;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private int[][] data;
    public int[][] getData() { return data; }

    private final Random rand;

    public Generator() {
        rand = new Random();
        data = new int[9][9];
        generateInputData();
        generate();
    }

    private void generate() {
        Sudoku sudoku = new Sudoku();
        sudoku.inputData = data;
        sudoku.game();
        data = sudoku.convertSolutionToArray();
        hideCells();
        sudoku.printSolution(data);
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

    // hard = 60
    // middle = 50
    // easy = 40
    private void hideCells() {
        int toHideCount = 50;
        ArrayList<IndexPair> existedPairs = new ArrayList<>();
        while (toHideCount != 0) {
            IndexPair pair = IndexPair.generateRandom();
            while (existedPairs.contains(pair)) {
                pair = IndexPair.generateRandom();
            }
            existedPairs.add(pair);
            data[pair.getRowNumber()][pair.getColNumber()] = 0;
            --toHideCount;
        }
    }
}
