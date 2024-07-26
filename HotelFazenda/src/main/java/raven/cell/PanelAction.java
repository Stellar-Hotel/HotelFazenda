package raven.cell;

import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Dimension;

public class PanelAction extends JPanel {

    private ActionButton cmdEdit;
    private ActionButton cmdDelete;

    public PanelAction() {
        initComponents();
    }

    public void initEvent(TableActionEvent event, int row) {
        cmdEdit.addActionListener(ae -> event.onEdit(row));
        cmdDelete.addActionListener(ae -> event.onDelete(row));
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
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(148, Short.MAX_VALUE)
                    .addComponent(cmdEdit, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18)
                    .addComponent(cmdDelete, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(210))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(143, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                        .addComponent(cmdEdit, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmdDelete, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(132, Short.MAX_VALUE))
        );
        this.setLayout(layout);
    }
}
