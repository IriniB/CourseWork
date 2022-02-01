package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingMenu {
    JFrame frame;
    JPanel panel;
    MainWindow mainWindow;

    public StartingMenu() {
        frame = new JFrame();
        panel = new JPanel();

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(new FlowLayout());
        panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        createButtons();

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Menu");
        frame.setPreferredSize(new Dimension(180, 400));
        frame.pack();
        frame.setVisible(true);
    }

    private void createButtons() {
        JButton easy = new JButton();
        easy.setPreferredSize(new Dimension(150, 70));
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(40);
                frame.setVisible(false);
            }
        });
        easy.setText("Легкая");


        JButton middle = new JButton();
        middle.setPreferredSize(new Dimension(150, 70));
        middle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(50);
                frame.setVisible(false);
            }
        });
        middle.setText("Средняя");

        JButton hard = new JButton();
        hard.setPreferredSize(new Dimension(150, 70));
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(3);
                frame.setVisible(false);
            }
        });
        hard.setText("Сложная");


        panel.add(easy);
        panel.add(middle);
        panel.add(hard);
    }

}
