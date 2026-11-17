package proyecto.retrovault.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ConsolaColorRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        if (!isSelected) {
            Object val = table.getModel().getValueAt(row, 2);
            String consola = val != null ? val.toString() : "";

            Color bg;
            switch (consola) {
                case "NES":
                    bg = new Color(70, 20, 20);
                    break;
                case "SNES":
                    bg = new Color(45, 20, 65);
                    break;
                case "Mega Drive":
                    bg = new Color(20, 30, 75);
                    break;
                case "Game Boy":
                    bg = new Color(20, 55, 30);
                    break;
                case "PlayStation":
                    bg = new Color(15, 40, 70);
                    break;
                case "Nintendo 64":
                    bg = new Color(55, 20, 55);
                    break;
                case "Game Gear":
                    bg = new Color(65, 35, 10);
                    break;
                case "Arcade":
                    bg = new Color(60, 50, 10);
                    break;
                default:
                    bg = new Color(35, 35, 35);
                    break;
            }
            label.setBackground(bg);
            label.setForeground(Color.WHITE);
        }
        return label;
    }
}
