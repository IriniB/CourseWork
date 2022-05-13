package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PopUpWindow {
    public PopUpWindow(String text) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Roboto", Font.BOLD, 20));
        textArea.setText(text);
        textArea.setCaretColor(Color.WHITE);
        textArea.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        });
        panel.add(textArea);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
