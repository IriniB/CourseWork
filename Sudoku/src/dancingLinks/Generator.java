package dancingLinks;
import java.util.ArrayList;
import java.util.Random;

public class Generator {
    private final GameLevel gameLevel;

    private int[][] data;
    public int[][] getData() { return data; }

    private int[][] fullData;
    public int[][] getFullData() { return fullData; }

    private final Random rand;

    public Generator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        rand = new Random();
        data = new int[9][9];
        generateStartingData();
    }

    private void generateStartingData() {
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

    public int generate() {
        Sudoku sudoku = new Sudoku();
        sudoku.inputData = data;
        sudoku.preSolvingProcessing();
        data = sudoku.convertSolutionToArray();
        fullData = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            System.arraycopy(data[i], 0, fullData[i], 0, 9);
        }
        int hideCount = hideCells();
        sudoku.printSolution(fullData);
        return hideCount;
    }

    private int hideCells() {
        int totalHideCells = 0;
        ArrayList<IndexPair> existedPairs = new ArrayList<>();
        // Счетчик по блокам.
        for (int i = 0; i < 9; ++i) {
            int toLeftCount = rand.nextInt(getToLeftCountStartValueForBox(),
                    getToLeftCountStartValueForBox() + 4);
            int toHideCount = 9 - toLeftCount;
            while (toHideCount != 0) {
                IndexPair pair = IndexPair.generateRandom(getStartRowIndex(i), getStartColIndex(i));
                while (existedPairs.contains(pair)) {
                    pair = IndexPair.generateRandom(getStartRowIndex(i), getStartColIndex(i));
                }
                existedPairs.add(pair);
                data[pair.getRowNumber()][pair.getColNumber()] = 0;
                --toHideCount;
                ++totalHideCells;
            }
        }
        return totalHideCells;
    }

    public int getStartRowIndex(int boxNumber) {
        if (boxNumber >= 0 && boxNumber <= 2) {
            return 0;
        } else if (boxNumber >= 3 && boxNumber <= 5) {
            return 3;
        } else {
            return 6;
        }
    }

    public int getStartColIndex(int boxNumber) {
        if (boxNumber == 0 || boxNumber == 3 || boxNumber == 6) {
            return 0;
        } else if (boxNumber == 1 || boxNumber == 4 || boxNumber == 7) {
            return 3;
        } else {
            return 6;
        }
    }

    private int getToLeftCountStartValueForBox() {
        if (gameLevel == GameLevel.easy) {
            return 4;
        } else if (gameLevel == GameLevel.middle) {
            return 2;
        } else {
            return 1;
        }
    }
}
