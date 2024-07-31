package utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class DefaultIconButton extends JButton {
    private Color backgroundColor = new Color(76, 175, 80); // Cor padrão de fundo (verde)
    private Color hoverColor = new Color(56, 142, 60); // Cor de hover padrão (verde escuro)
    private boolean mouseHover = false;
    private static final int ICON_SIZE = 20; // Tamanho do ícone
    private static final int TEXT_ICON_GAP = 5; // Espaço entre texto e ícone

    public DefaultIconButton(String text, ImageIcon icon) {
        super(text);
        setIcon(resizeAndRecolorIcon(icon, ICON_SIZE, ICON_SIZE, Color.WHITE)); // Redimensionar e colorir o ícone
        initButton();
    }

    public DefaultIconButton(String text) {
        super(text);
        initButton();
    }

    public DefaultIconButton(ImageIcon icon) {
        super();
        setIcon(resizeAndRecolorIcon(icon, ICON_SIZE, ICON_SIZE, Color.WHITE)); // Redimensionar e colorir o ícone
        initButton();
    }

    private void initButton() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setFont(new Font("Segoe UI", Font.BOLD, 17));
        setForeground(Color.WHITE);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setBorder(new EmptyBorder(10, 20, 10, 20));

        // Configuração do layout do botão para alinhamento de texto e ícone
        setHorizontalTextPosition(SwingConstants.RIGHT);
        setIconTextGap(TEXT_ICON_GAP);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseHover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseHover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (mouseHover) {
            g2d.setColor(hoverColor);
        } else {
            g2d.setColor(backgroundColor);
        }

        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
        g2d.dispose();
        super.paintComponent(g);
    }

    // Método para alterar a cor de fundo do botão
    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        repaint();
    }

    // Método para alterar a cor do hover do botão
    public void setHoverColor(Color color) {
        this.hoverColor = color;
        repaint();
    }

    private static ImageIcon resizeAndRecolorIcon(ImageIcon icon, int width, int height, Color color) {
        Image originalImage = icon.getImage();
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();

        // Aplicar filtro de cor
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = resizedImage.getRGB(x, y);
                int alpha = (argb >> 24) & 0xff;
                if (alpha != 0) {
                    resizedImage.setRGB(x, y, color.getRGB() & 0x00ffffff | (alpha << 24));
                }
            }
        }

        return new ImageIcon(resizedImage);
    }
}
