package raven.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {
    private static final Color ROW_COLOR1 = new Color(245, 245, 245); // Light Gray
    private static final Color ROW_COLOR2 = Color.WHITE;
    private static final Color SELECTED_ROW_COLOR = new Color(190, 253, 151); // Selected row color
    private static final Color SELECTED_ROW_TEXT_COLOR = Color.WHITE; // Selected row text color

    private int selectedRow = -1; // Index of the selected row
    private boolean showEditButton;
    private boolean showDeleteButton;

    public TableActionCellRender(boolean showEditButton, boolean showDeleteButton) {
        this.showEditButton = showEditButton;
        this.showDeleteButton = showDeleteButton;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        PanelAction action = new PanelAction(showEditButton, showDeleteButton);

        // Set the background color for the action panel
        Color rowColor = (row % 2 == 0) ? ROW_COLOR1 : ROW_COLOR2;
        if (row == selectedRow || isSelected) {
            rowColor = SELECTED_ROW_COLOR;
            action.setForeground(SELECTED_ROW_TEXT_COLOR);
        } else {
            action.setForeground(Color.BLACK);
        }

        action.setBackgroundColor(rowColor);

        return action;
    }

    public void setSelectedRow(int row) {
        this.selectedRow = row;
    }
}
