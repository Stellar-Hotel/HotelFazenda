package raven.cell;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTableUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class CustomTable extends JTable {

	private int selectedRow = -1; // Index of the currently selected row

	public CustomTable(DefaultTableModel model) {
		super(model);
		customizeTable();
	}

	private void customizeTable() {
		// Define colors for header and rows
		Color headerColor = Color.green.darker(); // SteelBlue
		Color headerTextColor = Color.WHITE;
		Color rowColor1 = new Color(245, 245, 245); // Light Gray
		Color rowColor2 = Color.WHITE;
		Color selectedRowColor = new Color(190, 253, 151); // Selected row color
		Color selectedRowTextColor = Color.BLACK; // Selected row text color

		// Customize the table header
		JTableHeader header = getTableHeader();
		header.setBackground(headerColor);
		header.setForeground(headerTextColor);
		header.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Increased font size

		// Customize header renderer to make text uppercase
		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		headerRenderer.setVerticalAlignment(SwingConstants.CENTER);
		headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Same font size as header
		header.setDefaultRenderer(headerRenderer);

		// Customize the cell renderer for alternating row colors
		setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

				// Apply alternating row colors
				Color rowColor = (row % 2 == 0) ? rowColor1 : rowColor2;
				cell.setBackground(rowColor);

				// Change cell text color if row is selected
				if (isSelected || row == selectedRow) {
					cell.setBackground(selectedRowColor);
					cell.setForeground(selectedRowTextColor);
				} else {
					cell.setForeground(Color.GRAY.darker());
				}

				// Center align text
				((DefaultTableCellRenderer) cell).setHorizontalAlignment(SwingConstants.CENTER);
				((DefaultTableCellRenderer) cell).setVerticalAlignment(SwingConstants.CENTER);
				((DefaultTableCellRenderer) cell).setFont(new Font("Segoe UI", Font.PLAIN, 15)); // Cell font size

				return cell;
			}
		});

		// Auto resize columns
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// Settings to hide lines and grid
		setShowGrid(false); // Hide grid
		setGridColor(Color.WHITE); // Invisible grid color
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setOpaque(false);
		setCellSelectionEnabled(true);

		// Customize table panel for rounded borders
		setUI(new BasicTableUI() {
			@Override
			public void paint(Graphics g, JComponent c) {
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Draw rounded borders
				RoundRectangle2D.Double roundedRect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1,
						20, 20);
				g2d.setColor(Color.GRAY); // Border color
				g2d.draw(roundedRect);

				// Draw table background
				g2d.setColor(Color.WHITE); // Background color
				g2d.fill(roundedRect);

				g2d.dispose();
				super.paint(g, c);
			}
		});

		// Add a MouseListener to update selected row
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = rowAtPoint(e.getPoint());
				if (row >= 0) {
					selectedRow = row;
					// Repaint the table to apply the new row color
					repaint();
				}
			}
		});
	}
}
