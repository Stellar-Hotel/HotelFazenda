package raven.cell;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author RAVEN
 */
public class TableActionCellRender extends DefaultTableCellRenderer {
    private static final Color ROW_COLOR1 = new Color(245, 245, 245); // Light Gray
    private static final Color ROW_COLOR2 = Color.WHITE;
    private static final Color SELECTED_ROW_COLOR = new Color(190, 253, 151) ; // Cor da linha selecionada
    private static final Color SELECTED_ROW_TEXT_COLOR = Color.WHITE; // Cor do texto da linha selecionada

    private int selectedRow = -1; // Índice da linha selecionada

    public TableActionCellRender(int selectedRow) {
        this.selectedRow = selectedRow;
    }

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(jtable, o, isSelected, hasFocus, row, column);

        // Cria uma instância de PanelAction (ou o componente desejado)
        PanelAction action = new PanelAction();

        // Define a cor de fundo da linha com base na seleção
        Color rowColor = (row % 2 == 0) ? ROW_COLOR1 : ROW_COLOR2;
        if (row == selectedRow) {
            rowColor = SELECTED_ROW_COLOR;
            com.setForeground(SELECTED_ROW_TEXT_COLOR);
        } else {
            com.setForeground(Color.BLACK);
        }

        // Aplica a cor de fundo
        action.setBackgroundColor(rowColor);

        return action;
    }

    public void setSelectedRow(int row) {
        this.selectedRow = row;
    }
}
