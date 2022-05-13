package GUI;

import dancingLinks.GameLevel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;


public class StartingMenu {
    private final JFrame frame;
    private final JPanel panel;
    private boolean isAutoCorrectOn;
    public static HashMap<String, Color> colors;

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
        frame.setPreferredSize(new Dimension(180, 380));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createButtons() {
        JButton easy = new JButton();
        easy.setPreferredSize(new Dimension(140, 60));
        easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(GameLevel.easy, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        easy.setText("Легкая");
        easy.setBackground(colors.get("buttonColor"));
        easy.setFont(new Font("Arial", Font.BOLD, 20));


        JButton middle = new JButton();
        middle.setPreferredSize(new Dimension(140, 60));
        middle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(GameLevel.middle, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        middle.setText("Средняя");
        middle.setBackground(colors.get("buttonColor"));
        middle.setFont(new Font("Arial", Font.BOLD, 20));

        JButton hard = new JButton();
        hard.setPreferredSize(new Dimension(140, 60));
        hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainWindow(GameLevel.hard, isAutoCorrectOn);
                frame.setVisible(false);
            }
        });
        hard.setText("Сложная");
        hard.setBackground(colors.get("buttonColor"));
        hard.setFont(new Font("Arial", Font.BOLD, 20));


        JPanel checkBoxPanel = new JPanel();
        JCheckBox autoCorrect = new JCheckBox();
        checkBoxPanel.add(autoCorrect);
        String s = "<html>Проверка"
                + "<br>ошибок";
        JLabel multiLineLabel = new JLabel(s);
        multiLineLabel.setLabelFor(autoCorrect);
        checkBoxPanel.add(multiLineLabel);

        autoCorrect.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                isAutoCorrectOn = e.getStateChange() == ItemEvent.SELECTED;
            }
        });
        checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#7a8a99")));
        checkBoxPanel.setPreferredSize(new Dimension(140, 60));
        checkBoxPanel.setBackground(colors.get("buttonColor"));
        autoCorrect.setBackground(colors.get("buttonColor"));
        multiLineLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton rules = new JButton();
        rules.setPreferredSize(new Dimension(140, 60));
        rules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PopUpWindow("От игрока требуется заполнить свободные клетки\n" +
                        "цифрами от 1 до 9 так, чтобы в каждой строке, в каждом\n" +
                        "столбце и в каждом малом квадрате 3×3 каждая цифра\n" +
                        "встречалась бы только один раз.\n\n" +
                        "При включении автоматической проверки ошибок, если игрок\n" +
                        "допустил ошибку в заполнении поля, то неправильная цифра\n" +
                        "подсвечивается красным цветом.\n\n" +
                        "При установке курсора в пустую клетку и нажатии кнопки <Подсказка>\n" +
                        "в этой клетке появится нужная цифра.\n\n" +
                        "В какой-то момент может возникнуть ситуация, что к решению невозможно\n" +
                        "прийти путем логических размышлений и игроку надо будет попробовать\n" +
                        "несколько вариантов заполнения поля, пока он не найдет правильный.\n" +
                        "В таком случае есть возможность сохранить текущее заполнение поля и\n" +
                        "потом при надобности вернуться к нему. Для этого необходимо нажать кнопки\n" +
                        "<Сохранить> и <Восстановить> соответственно.\n\n" +
                        "При нажатии на кнопку <Выход> происходит сброс текущей игры и\n" +
                        "выполняется выход в главное меню.");
            }
        });
        rules.setText("Правила");
        rules.setBackground(colors.get("buttonColor"));
        rules.setFont(new Font("Arial", Font.BOLD, 20));

        panel.add(easy);
        panel.add(middle);
        panel.add(hard);
        panel.add(checkBoxPanel);
        panel.add(rules);
    }

    public static void initColors() {
        colors = new HashMap<>();
        colors.put("cellBaseColor", Color.WHITE);
        colors.put("cellSelectedColor", Color.decode("#BBDEFB"));
        colors.put("cellAroundSelectedColor", Color.decode("#DEE7F2"));
        colors.put("cellTextColor", Color.decode("#4B61B9"));
        colors.put("cellNewTextColor", Color.BLACK);
        colors.put("fieldBackgroundColor", Color.decode("#BEC6D3"));
        colors.put("cellWrongValueColor", Color.decode("#8B0000"));
        colors.put("boxBorderLineColor", Color.decode("#1B223D"));
        colors.put("buttonColor", Color.decode("#c5d7ed"));
    }

    public static void main(String[] args) {
        initColors();
        new StartingMenu();
    }
}
