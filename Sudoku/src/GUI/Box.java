package GUI;

import javax.swing.*;
import java.awt.*;

public class Box {
    protected JPanel boxPanel;
    private final Cell[] cells;

    public Box(Cell[] cells) {
        boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(3, 3));
        boxPanel.setBorder(BorderFactory.createLineBorder(StartingMenu.colors.get("boxBorderLineColor"), 2));

        this.cells = cells;
        for (int i = 0; i < 9; ++i) {
            boxPanel.add(cells[i].textField);
        }
        boxPanel.setBackground(StartingMenu.colors.get("fieldBackgroundColor"));
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
