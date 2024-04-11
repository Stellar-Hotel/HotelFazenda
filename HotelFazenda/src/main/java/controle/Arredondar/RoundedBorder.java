package controle.Arredondar; 

import java.awt.Color; 		
import java.awt.Component; 
import java.awt.Graphics;  
import java.awt.Insets; 
import javax.swing.border.AbstractBorder; 

public class RoundedBorder extends AbstractBorder {

    private Color borderColor; // Cor da borda
    private int radius; // Raio do arredondamento da borda

    public RoundedBorder(Color borderColor, int radius) {
        this.borderColor = borderColor; // Inicializa a cor da borda
        this.radius = radius; // Inicializa o raio do arredondamento da borda
    }

    // Método chamado para desenhar a borda
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        super.paintBorder(c, g, x, y, width, height); // Chama o método paintBorder da superclasse
        g.setColor(borderColor); // Define a cor da borda
     
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);    // Desenha um retângulo arredondado com as dimensões e raio especificados
    }

    // Método para obter as margens da borda
    @Override
    public Insets getBorderInsets(Component c) {
        // Retorna as margens da borda como um objeto Insets com todas as margens definidas com o valor do raio
        return new Insets(radius, radius, radius, radius);
    }

    // Método para preencher as margens da borda
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        // Preenche as margens da borda fornecidas no objeto Insets
        insets.left = insets.top = insets.right = insets.bottom = radius;
        return insets; // Retorna as margens preenchidas
    }
}
