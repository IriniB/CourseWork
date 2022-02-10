package GUI;

import dancingLinks.GameLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


public class StartingMenu {
    JFrame frame;
    JPanel panel;
    MainWindow mainWindow;
    boolean isAutoCorrectOn;

    public StartingMenu() {
    /*
        try {
            //recommended way to set Nimbus LaF because old versions of Java 6
            //don't have it included
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    */
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createButtons() {
        JButton easy = new JButton();
        easy.setPreferredSize(new Dimension(150, 70));
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(GameLevel.easy, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        easy.setText("Легкая");


        JButton middle = new JButton();
        middle.setPreferredSize(new Dimension(150, 70));
        middle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(GameLevel.middle, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        middle.setText("Средняя");

        JButton hard = new JButton();
        hard.setPreferredSize(new Dimension(150, 70));
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainWindow = new MainWindow(GameLevel.hard, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        hard.setText("Сложная");

        JCheckBox autoCorrect = new JCheckBox();
        autoCorrect.setPreferredSize(new Dimension(150, 70));
        autoCorrect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    isAutoCorrectOn = true;
                } else {
                    isAutoCorrectOn = false;
                }
            }
        });
        autoCorrect.setText("Проверка ошибок");


        panel.add(easy);
        panel.add(middle);
        panel.add(hard);
        panel.add(autoCorrect);
    }

}
