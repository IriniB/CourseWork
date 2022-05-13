package GUI;

import dancingLinks.Sudoku;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class Cell {
    protected JTextField textField;
    private boolean isInitial = false;
    protected int i;
    protected int j;
    private final Field field;

    public Cell(int value, int i, int j, Field field) {
        this.i = i;
        this.j = j;
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(40, 40));
        textField.setFont(new Font("Roboto", Font.BOLD, 35));
        textField.setCaretColor(StartingMenu.colors.get("cellBaseColor"));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setBorder(BorderFactory.createLineBorder(StartingMenu.colors.get("boxBorderLineColor"), 1));

        textField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if (value != 0) {
            textField.setText(Integer.toString(value));
            textField.setForeground(StartingMenu.colors.get("cellTextColor"));
            isInitial = true;
        }
        addInputHandling(value);

        Cell current = this;
        textField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                field.lastFocused = current;
                field.colorSelected();
            }
        });

        this.field = field;

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char c = e.getKeyChar();
                if (c != KeyEvent.VK_BACK_SPACE){
                    if (field.filledCounter == 81) {
                        field.checkForCorrectness();
                        return;
                    }
                }

                if (field.autoCorrect) {
                    Sudoku sudoku = new Sudoku();
                    sudoku.inputData = field.convertCellValues();
                    if (!sudoku.processInputData() && !isInitial) {
                        textField.setForeground(StartingMenu.colors.get("cellWrongValueColor"));
                    }
                }
            }
        });
    }

    private void addInputHandling(int value) {
        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (!Objects.equals(textField.getText(), "")) {
                    e.consume();
                    return;
                }
                if ( ((c < '1') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
                    e.consume();
                    return;
                }
                if (isInitial) {
                    e.consume();
                    textField.setText(Integer.toString(value));
                    return;
                }
                if (c != KeyEvent.VK_BACK_SPACE) {
                    field.filledCounter++;
                } else {
                    field.filledCounter--;
                }

                textField.setForeground(StartingMenu.colors.get("cellNewTextColor"));
            }
        });
    }

    public int getCurrentValue() {
        String valueString = textField.getText();
        if (Objects.equals(valueString, "")) {
            return 0;
        } else {
            return Integer.parseInt(valueString);
        }
    }

    public void setValue(int value) {
        if (value != 0) {
            textField.setText(Integer.toString(value));
        } else {
            textField.setText("");
        }
    }
}
