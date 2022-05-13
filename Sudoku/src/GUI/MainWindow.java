package GUI;

import dancingLinks.GameLevel;
import dancingLinks.Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    protected Field field;
    private int[][] savedState;

    public MainWindow(GameLevel gameLevel, boolean isAutoCorrectOn) {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(460, 500));
        frame.setLayout(new BorderLayout());
        field = new Field(gameLevel);
        field.autoCorrect = isAutoCorrectOn;
        frame.add(field.fieldPanel, BorderLayout.CENTER);
        createButtons(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Sudoku");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createButtons(JFrame frame) {
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
        exit.setBackground(StartingMenu.colors.get("buttonColor"));

        JButton hint = new JButton();
        hint.setPreferredSize(new Dimension(100, 30));
        hint.setText("Подсказка");
        hint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sudoku sudoku = new Sudoku();
                sudoku.inputData = field.convertCellValues();
                if (!sudoku.preSolvingProcessing()) {
                    new PopUpWindow("Невозможно дать подсказку, так как\n" +
                            " в заполнении поля есть ошибки");
                } else {
                    int[][] solvedField = sudoku.convertSolutionToArray();
                    field.lastFocused.textField.setText(Integer.toString(solvedField[field.lastFocused.i][field.lastFocused.j]));
                    field.filledCounter++;
                }
            }
        });
        hint.setBackground(StartingMenu.colors.get("buttonColor"));

        JButton stateSave = new JButton();
        stateSave.setPreferredSize(new Dimension(100, 30));
        stateSave.setText("Сохранить");
        stateSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savedState = field.convertCellValues();
            }
        });
        stateSave.setBackground(StartingMenu.colors.get("buttonColor"));

        JButton stateRecover = new JButton();
        stateRecover.setPreferredSize(new Dimension(120, 30));
        stateRecover.setText("Восстановить");
        stateRecover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (savedState == null) {
                    new PopUpWindow("Вы не сохраняли состояние игры");
                } else {
                    field.setCellValues(savedState);
                }
            }
        });
        stateRecover.setBackground(StartingMenu.colors.get("buttonColor"));

        JPanel menuPanel = new JPanel();
        menuPanel.add(exit);
        menuPanel.add(hint);
        menuPanel.add(stateSave);
        menuPanel.add(stateRecover);
        frame.add(menuPanel, BorderLayout.SOUTH);
    }
}
