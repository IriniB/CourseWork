package GUI;

import dancingLinks.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    public Field field;

    public MainWindow(int toHideCount) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setPreferredSize(new Dimension(400, 500));
        //frame.setResizable(false);
        field = new Field(toHideCount);
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
                sudoku.game();
                int[][] solvedField = sudoku.convertSolutionToArray();
                Field.lastFocused.textField.setText(Integer.toString(solvedField[Field.lastFocused.i][Field.lastFocused.j]));
                Field.correctCounter++;
                Field.filledCounter++;
            }
        });



        menu.add(exit);
        menu.add(hint);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.pack();
        frame.setVisible(true);
    }
}
