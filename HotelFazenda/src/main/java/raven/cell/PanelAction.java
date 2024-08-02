package raven.cell;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelAction extends JPanel {

	private ActionButton cmdEdit;
	private ActionButton cmdDelete;
	private boolean showEditButton;
	private boolean showDeleteButton;

	public PanelAction(boolean showEditButton, boolean showDeleteButton) {
		this.showEditButton = showEditButton;
		this.showDeleteButton = showDeleteButton;
		initComponents();
	}

	public void initEvent(TableActionEvent event, int row) {
		if (showEditButton) {
			cmdEdit.addActionListener(ae -> event.onEdit(row));
		}
		if (showDeleteButton) {
			cmdDelete.addActionListener(ae -> event.onDelete(row));
		}
	}

	private void initComponents() {
		cmdEdit = new ActionButton(Color.ORANGE);
		cmdDelete = new ActionButton(Color.RED);

		cmdEdit.setIcon(new ImageIcon(PanelAction.class.getResource("/visao/edit.png")));
		cmdDelete.setIcon(new ImageIcon(PanelAction.class.getResource("/visao/Trash.png")));

		cmdEdit.setText("Editar");
		cmdDelete.setText("Deletar");

		// Definindo a cor do texto para branco
		cmdEdit.setForeground(Color.WHITE);
		cmdDelete.setForeground(Color.WHITE);

		// Definindo o mesmo tamanho para ambos os botões
		Dimension buttonSize = new Dimension(82, 30);
		cmdEdit.setPreferredSize(buttonSize);
		cmdEdit.setMaximumSize(buttonSize);
		cmdDelete.setPreferredSize(buttonSize);
		cmdDelete.setMaximumSize(buttonSize);

		GroupLayout layout = new GroupLayout(this);
		layout.setHorizontalGroup(
				layout.createParallelGroup(Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addContainerGap(148, Short.MAX_VALUE)
								.addComponent(cmdEdit, showEditButton ? GroupLayout.PREFERRED_SIZE : 0,
										showEditButton ? GroupLayout.PREFERRED_SIZE : 0, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(cmdDelete, showDeleteButton ? GroupLayout.PREFERRED_SIZE : 0,
										showDeleteButton ? GroupLayout.PREFERRED_SIZE : 0, GroupLayout.PREFERRED_SIZE)
								.addGap(210)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap(143, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(cmdEdit, showEditButton ? GroupLayout.PREFERRED_SIZE : 0,
										showEditButton ? GroupLayout.PREFERRED_SIZE : 0, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmdDelete, showDeleteButton ? GroupLayout.PREFERRED_SIZE : 0,
										showDeleteButton ? GroupLayout.PREFERRED_SIZE : 0, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(132, Short.MAX_VALUE)));
		this.setLayout(layout);
	}

	// Método para definir a cor de fundo
	public void setBackgroundColor(Color color) {
		this.setBackground(color);
	}
}
