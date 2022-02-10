package GUI;

import dancingLinks.GameLevel;
import dancingLinks.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainWindow {

    public Field field;
    int[][] savedState;

    public MainWindow(GameLevel gameLevel, boolean isAutoCorrectOn) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(400, 500));
        //frame.setResizable(false);
        field = new Field(gameLevel);
        field.autoCorrect = isAutoCorrectOn;
        frame.add(field.fieldPanel, BorderLayout.CENTER);
        JPanel menu = new JPanel();
        frame.add(menu);

        JButton exit = new JButton();
        exit.setPreferredSize(new Dimension(100, 30));
        exit.setText("Выход");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StartingMenu();
                frame.setVisible(false);
            }
        });

        JButton hint = new JButton();
        hint.setPreferredSize(new Dimension(100, 30));
        hint.setText("Подсказка");
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sudoku sudoku = new Sudoku();
                sudoku.inputData = field.convertCellValues();
                if (!sudoku.game()) {
                    new PopUpWindow("Невозможно дать подсказку, так как\n" +
                            " в заполнении поля есть ошибки");
                } else {
                    int[][] solvedField = sudoku.convertSolutionToArray();
                    field.lastFocused.textField.setText(Integer.toString(solvedField[field.lastFocused.i][field.lastFocused.j]));
                    field.filledCounter++;
                }
            }
        });


        JButton stateSave = new JButton();
        stateSave.setPreferredSize(new Dimension(100, 30));
        stateSave.setText("Сохранить");
        stateSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(stateSave.getText(), "Сохранить")) {
                    savedState = field.convertCellValues();
                    stateSave.setText("Откатить");
                    return;
                }
                if (Objects.equals(stateSave.getText(), "Откатить")) {
                    field.setCellValues(savedState);
                    stateSave.setText("Сохранить");
                }
            }
        });



        menu.add(exit);
        menu.add(hint);
        menu.add(stateSave);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
