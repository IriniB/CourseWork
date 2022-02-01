package GUI;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class Cell {
    boolean focused = false;
    JTextField textField;
    int correctValue;
    boolean isInitial = false;
    int i;
    int j;

    public Cell(int value, int correctValue, int i, int j) {
        this.i = i;
        this.j = j;
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(40, 40));
        textField.setFont(new Font("Roboto", Font.BOLD, 35));
        textField.setHorizontalAlignment(JTextField.CENTER);
        if (value != 0) {
            textField.setText(Integer.toString(value));
            textField.setForeground(Color.BLUE);
            isInitial = true;
        }
        this.correctValue = correctValue;

        textField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
//зачем первое условие???
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

                if ((c != KeyEvent.VK_BACK_SPACE) && Character.toString(c).equals(Integer.toString(correctValue))) {
                    Field.correctCounter++;
                    Field.filledCounter++;
                    if (Field.correctCounter == 81) {
                        System.out.println("РЕШЕНО");
                    }
                } else if ((c != KeyEvent.VK_BACK_SPACE)){
                    Field.filledCounter++;
                    if (Field.filledCounter == 81) {
                        System.out.println("НЕПРАВИЛЬНО");
                    }
                    // падает потому что пытается пустую строку скастить к инту
                } else if (Integer.parseInt(textField.getText()) == correctValue) {
                    Field.correctCounter--;
                    Field.filledCounter--;
                } else {
                    Field.filledCounter--;
                }

            }
        });


        Cell current = this;
        textField.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                Field.lastFocused = current;
            }
        });

/*
        textField.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
*/


    }
}
