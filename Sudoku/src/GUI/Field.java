package GUI;

import dancingLinks.GameLevel;
import dancingLinks.Generator;
import dancingLinks.Sudoku;

import javax.swing.*;
import java.awt.*;


public class Field {
    JPanel fieldPanel;
    Box[] boxes;
    Generator generator;
    Cell[][] cells;
    Cell lastFocused;
    boolean autoCorrect;

    public int filledCounter;

    public Field(GameLevel gameLevel) {
        fieldPanel = new JPanel();
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fieldPanel.setLayout(new GridLayout(3, 3));

        generator = new Generator(gameLevel);
        filledCounter = 81 - generator.generate();

        cells = new Cell[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                cells[i][j] = new Cell(generator.getData()[i][j], generator.getFullData()[i][j], i, j, this);
            }
        }

        lastFocused = cells[0][0];


        boxes = new Box[9];
        for (int i = 0; i < 9; ++i) {
            Cell[] boxCells = new Cell[9];
            int index = 0;
            for (int row = generator.getStartRowIndex(i); row < generator.getStartRowIndex(i) + 3; ++row) {
                for (int col = generator.getStartColIndex(i); col < generator.getStartColIndex(i) + 3; ++col) {
                    boxCells[index] = cells[row][col];
                    ++index;
                }
            }
            boxes[i] = new Box(boxCells);
            fieldPanel.add(boxes[i].boxPanel);
        }

    }

    private Box findBoxOfCell(Cell cell) {
        for (int i = 0; i < 9; ++i) {
            if (boxes[i].isCellBelongs(cell)) {
                return boxes[i];
            }
        }
        return null;
    }

    public void colorSelected() {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                Box cellBox = findBoxOfCell(lastFocused);
                if (i == lastFocused.i && j == lastFocused.j) {
                    cells[i][j].textField.setBackground(Colors.cellSelectedColor);
                    cells[i][j].textField.setCaretColor(Colors.cellSelectedColor);
                }
                else if ((cells[i][j].getCurrentValue() != 0 && cells[i][j].getCurrentValue() == lastFocused.getCurrentValue()) ||
                        cellBox == findBoxOfCell(cells[i][j]) || i == lastFocused.i || j == lastFocused.j) {
                    cells[i][j].textField.setBackground(Colors.cellAroundSelectedColor);
                    cells[i][j].textField.setCaretColor(Colors.cellAroundSelectedColor);
                } else {
                    cells[i][j].textField.setBackground(Colors.cellBaseColor);
                    cells[i][j].textField.setCaretColor(Colors.cellBaseColor);
                }
            }
        }
    }

    public int[][] convertCellValues() {
        int[][] currentValues = new int[9][9];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                currentValues[i][j] = cells[i][j].getCurrentValue();
            }
        }
        return currentValues;
    }

    public void setCellValues(int[][] values) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                cells[i][j].setValue(values[i][j]);
            }
        }
    }

    public void checkForCorrectness() {
        Sudoku sudoku = new Sudoku();
        sudoku.inputData = convertCellValues();
        if (sudoku.game()) {
            new PopUpWindow("Решено!");
        } else {
            new PopUpWindow("Неправильно!");
        }
    }
}
