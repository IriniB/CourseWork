package GUI;

import javax.swing.*;
import java.awt.*;

public class PopUpWindow {
    JFrame frame;
    JPanel panel;
    JTextArea textArea;

    public PopUpWindow(String text) {
        frame = new JFrame();
        panel = new JPanel();
        textArea = new JTextArea();
        textArea.setFont(new Font("Roboto", Font.BOLD, 20));
        textArea.setText(text);
        panel.add(textArea);
        frame.add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
