package visao.ModaisDeAvisos;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import net.miginfocom.swing.MigLayout;

public class TelaSucesso extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaSucesso frame = new TelaSucesso("teste");
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TelaSucesso(String content) {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(351, 216);

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
        setContentPane(contentPane);

        contentPane.setLayout(new MigLayout("", "[458.00px]", "[120.00px][24.00px][47.00px]"));

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(TelaSucesso.class.getResource("/visao/sucesso.png")));
        contentPane.add(lblNewLabel, "cell 0 0,alignx center,aligny center");

        JLabel lblNewLabel_1 = new JLabel("Tudo Certo!");
        lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 24));
        contentPane.add(lblNewLabel_1, "cell 0 1,alignx center,aligny top");

        JLabel lblNewLabel_2 = new JLabel(content);
        lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contentPane.add(lblNewLabel_2, "cell 0 2,alignx center,aligny top");

        animarTela();
    }

    public void animarTela() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = screenSize.width - getWidth() - 20; // 20 pixels de margem à direita
        int y = screenSize.height; // Posição inicial fora da tela

        // Ajustar a posição final para o canto direito e um pouco acima da tela
        int finalY = screenSize.height - getHeight() - 45; // Ajuste este valor para a posição final desejada

        Timer subirTimer = new Timer(10, new ActionListener() {
            int dy = screenSize.height;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (dy > finalY) {
                    setLocation(x, dy);
                    dy -= 6; // Velocidade da animação
                } else {
                    ((Timer) e.getSource()).stop();
                    Timer descerTimer = new Timer(10, new ActionListener() {
                        int dy = getLocation().y;

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (dy < finalY) {
                                setLocation(x, dy);
                                dy += 20; // Velocidade da animação
                            } else {
                                ((Timer) e.getSource()).stop();
                                dispose();
                            }
                        }
                    });
                    descerTimer.setInitialDelay(2000);
                    descerTimer.start();
                }
            }
        });
        subirTimer.setInitialDelay(0);
        subirTimer.start();
    }
}
