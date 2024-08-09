package utils;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DefaultModal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DefaultModal() {

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

	}

}
