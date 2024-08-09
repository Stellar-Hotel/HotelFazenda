package controle.Combobox;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class RoundedComboBoxUI extends BasicComboBoxUI {
	private final JComboBox<?> comboBox;

	public RoundedComboBoxUI(JComboBox<?> comboBox) {
		this.comboBox = comboBox;
	}

	@Override
	public void installUI(JComponent c) {
		super.installUI(c);
		c.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	}

	@Override
	public void paint(Graphics g, JComponent c) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Desenhar o fundo com cantos arredondados
		g2.setColor(comboBox.getBackground());
		g2.fillRoundRect(0, 0, comboBox.getWidth(), comboBox.getHeight(), 15, 15);

		// Desenhar a borda arredondada
		g2.setColor(Color.GRAY); // Cor da borda
		g2.setStroke(new BasicStroke(1));
		g2.drawRoundRect(0, 0, comboBox.getWidth() - 1, comboBox.getHeight() - 1, 15, 15);

		super.paint(g, c);

		g2.dispose();
	}

	@Override
	public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(comboBox.getBackground());
		g2.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 15, 15);

		g2.dispose();
	}
}
