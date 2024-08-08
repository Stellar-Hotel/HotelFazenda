package controle.Combobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomArrowButton extends JButton {
    private static final int SIZE = 20; // Tamanho do botão
    private static final int ARROW_SIZE = 35; // Tamanho da seta

    public CustomArrowButton() {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setOpaque(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder());
        setFocusable(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Comportamento do botão, se necessário
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenhar a seta
        g2.setColor(Color.GRAY); // Cor da seta
        int xPoints[] = {SIZE / 2 - ARROW_SIZE / 2, SIZE / 2 + ARROW_SIZE / 2, SIZE / 2};
        int yPoints[] = {SIZE / 2 - ARROW_SIZE / 2, SIZE / 2 - ARROW_SIZE / 2, SIZE / 2 + ARROW_SIZE / 2};
        g2.fillPolygon(xPoints, yPoints, 3);

        g2.dispose();
    }
}
