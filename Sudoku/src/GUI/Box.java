package GUI;

import javax.swing.*;
import java.awt.*;

public class Box {
    JPanel boxPanel;
    Cell[] cells;

    public Box(Cell[] cells) {
        boxPanel = new JPanel();
        boxPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        boxPanel.setLayout(new GridLayout(3, 3));

        this.cells = cells;
        for (int i = 0; i < 9; ++i) {
            boxPanel.add(cells[i].textField);
        }
        boxPanel.setBackground(Colors.fieldBackgroundColor);
    }

    public boolean isCellBelongs(Cell cell) {
        for (int i = 0; i < 9; ++i) {
            if (cells[i] == cell) {
                return true;
            }
        }
        return false;
    }
}
