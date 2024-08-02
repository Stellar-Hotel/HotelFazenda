package raven.cell;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableActionCellEditor extends DefaultCellEditor {

	private TableActionEvent event;
	private boolean showEditButton;
	private boolean showDeleteButton;

	public TableActionCellEditor(TableActionEvent event, boolean showEditButton, boolean showDeleteButton) {
		super(new JCheckBox());
		this.event = event;
		this.showEditButton = showEditButton;
		this.showDeleteButton = showDeleteButton;
	}

	@Override
	public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
		PanelAction action = new PanelAction(showEditButton, showDeleteButton);
		action.initEvent(event, row);
		action.setBackground(jtable.getSelectionBackground());
		return action;
	}
}
