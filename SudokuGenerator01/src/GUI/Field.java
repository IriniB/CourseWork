package GUI;

import dancingLinks.Generator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Field {
    JPanel fieldPanel;
    ArrayList<Box> boxes;
    Generator generator;
    Cell[][] cells;
    static Cell lastFocused;

    public static int correctCounter;
    public static int filledCounter;

    public Field(int toHideCount) {
        correctCounter = 81 - toHideCount;
        filledCounter = 81 - toHideCount;
        fieldPanel = new JPanel();
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        fieldPanel.setLayout(new GridLayout(3, 3));

        generator = new Generator(toHideCount);

        cells = new Cell[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                cells[i][j] = new Cell(generator.getData()[i][j], generator.getFullData()[i][j], i, j);
            }
        }


        boxes = new ArrayList<>(9);
        for (int i = 0; i < 9; ++i) {
            Cell[] boxCells = new Cell[9];
            int index = 0;
            for (int row = getStartRowIndex(i); row < getStartRowIndex(i) + 3; ++row) {
                for (int col = getStartColIndex(i); col < getStartColIndex(i) + 3; ++col) {
                    boxCells[index] = cells[row][col];
                    ++index;
                }
            }
            boxes.add(new Box(boxCells));
            fieldPanel.add(boxes.get(i).boxPanel);
        }



    }

    private int getStartRowIndex(int boxNumber) {
        if (boxNumber >= 0 && boxNumber <= 2) {
            return 0;
        } else if (boxNumber >= 3 && boxNumber <= 5) {
            return 3;
        } else {
            return 6;
        }
    }

    private int getStartColIndex(int boxNumber) {
        if (boxNumber == 0 || boxNumber == 3 || boxNumber == 6) {
            return 0;
        } else if (boxNumber == 1 || boxNumber == 4 || boxNumber == 7) {
            return 3;
        } else {
            return 6;
        }
    }

    public int[][] convertCellValues() {
        int[][] currentValues = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (Objects.equals(cells[i][j].textField.getText(), "")) {
                    currentValues[i][j] = 0;
                } else {
                    currentValues[i][j] = Integer.parseInt(cells[i][j].textField.getText());
                }
            }
        }
        return currentValues;
    }
}
