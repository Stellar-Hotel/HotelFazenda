package controle.Data;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCellRenderer extends DefaultTableCellRenderer {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public DateCellRenderer() {
        // Define o alinhamento centralizado
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        // Aplica a mesma fonte e tamanho de texto da CustomTable
        cell.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        // Aplica as cores de fundo e texto conforme a tabela personalizada
        if (isSelected) {
            cell.setBackground(new Color(190, 253, 151)); // Cor de fundo para linha selecionada
            cell.setForeground(Color.BLACK); // Cor do texto para linha selecionada
        } else {
            // Alternar entre cores de linha
            Color rowColor = (row % 2 == 0) ? new Color(245, 245, 245) : Color.WHITE;
            cell.setBackground(rowColor);
            cell.setForeground(Color.GRAY.darker()); // Cor do texto não selecionado
        }
        
        // Formata a data
        if (value instanceof Date) {
            value = dateFormat.format((Date) value);
        }
        super.setValue(value);
        
        return cell;
    }
}
