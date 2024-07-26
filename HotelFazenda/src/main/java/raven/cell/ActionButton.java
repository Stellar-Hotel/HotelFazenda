package raven.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton {
    private boolean mousePress;
    private Color backgroundColor;

    public ActionButton(Color color) {
        this.backgroundColor = color;
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(3, 3, 3, 3));
        setForeground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        if (mousePress) {
            g2.setColor(backgroundColor.darker());
        } else {
            g2.setColor(backgroundColor);
        }
        g2.fill(new RoundRectangle2D.Double(0, 0, width, height, 20, 20));
        g2.dispose();
        super.paintComponent(grphcs);
    }
}
