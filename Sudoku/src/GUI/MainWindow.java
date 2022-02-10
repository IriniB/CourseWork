package GUI;

import dancingLinks.GameLevel;
import dancingLinks.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public Field field;

    public MainWindow(GameLevel gameLevel) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(400, 500));
        //frame.setResizable(false);
        field = new Field(gameLevel);
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



        menu.add(exit);
        menu.add(hint);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
}
