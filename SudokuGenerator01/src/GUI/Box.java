package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Box {
    JPanel boxPanel;
    Cell[] cells;

    public Box(Cell[] cells) {
        boxPanel = new JPanel();
        boxPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        boxPanel.setLayout(new GridLayout(3, 3));

        this.cells = cells;
        for (int i = 0; i < 9; ++i) {
            boxPanel.add(cells[i].textField);
        }
    }
}
