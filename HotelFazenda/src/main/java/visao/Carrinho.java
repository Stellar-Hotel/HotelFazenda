package visao;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Panel;
import net.miginfocom.swing.MigLayout;

import modelo.Servicos;

public class Carrinho extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Carrinho frame = new Carrinho(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @param lista 
     */
    public Carrinho(ArrayList<Servicos> lista) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new MigLayout("", "[][][438.00,grow][123.00,grow][10px:n,grow][331.00,grow][15:n,grow]", "[10:n][35.00][26.00][29.00][13.00][][grow][][]"));

        JLabel lblNewLabel = new JLabel("Carrinho");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
        contentPane.add(lblNewLabel, "cell 1 1 2 1");

        JSeparator separator = new JSeparator();
        contentPane.add(separator, "cell 0 2 4 1,growx");

        JLabel lblNewLabel_2 = new JLabel("Itens do Carrinho");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        contentPane.add(lblNewLabel_2, "cell 1 3 2 1");

        JLabel lblNewLabel_3 = new JLabel("Você tem n itens no carrinho.");
        lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        contentPane.add(lblNewLabel_3, "cell 1 4 2 1");
        
        table = new JTable();
        contentPane.add(table, "cell 2 6,grow");
        
       // table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "CPF" }));


       
        JPanel panel = new JPanel();
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBackground(new Color(128, 128, 255));
        contentPane.add(panel, "cell 5 1 1 7,grow");
        panel.setLayout(new MigLayout("", "[grow][grow][grow]", "[142.00,grow][][][][][][][][][][][]"));

        JLabel lblNewLabel_4 = new JLabel("Compras");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 16));
        panel.add(lblNewLabel_4, "cell 0 0");

        JLabel lblNewLabel_5 = new JLabel("Pagamento");
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 28));
        panel.add(lblNewLabel_5, "cell 1 0");

        JLabel lblNewLabel_6 = new JLabel("Selecione o método de pagamento:");
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        panel.add(lblNewLabel_6, "cell 0 1 2 1,grow");

        JPanel panel_1 = new JPanel();
        panel_1.setToolTipText("");
        panel_1.setBackground(new Color(0, 255, 0));
        panel.add(panel_1, "cell 0 2,grow");

        JLabel lblNewLabel_7 = new JLabel("Dinheiro");
        panel.add(lblNewLabel_7, "cell 0 3");

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(new Color(0, 255, 255));
        panel.add(panel_2, "cell 1 2,grow");

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(new Color(128, 0, 64));
        panel.add(panel_3, "cell 2 2,grow");

        JLabel lblNewLabel_8 = new JLabel("Pix");
        panel.add(lblNewLabel_8, "cell 0 4");

        JLabel lblNewLabel_9 = new JLabel("Cartão");
        panel.add(lblNewLabel_9, "cell 2 4");

        JLabel lblNewLabel_10 = new JLabel("Total dos itens:");
        lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panel.add(lblNewLabel_10, "cell 0 5");

        JLabel lblNewLabel_11 = new JLabel("Subtotal");
        panel.add(lblNewLabel_11, "cell 0 6");

        JLabel lblNewLabel_12 = new JLabel("R$");
        panel.add(lblNewLabel_12, "cell 2 6");

        JLabel lblNewLabel_12_1 = new JLabel("R$");
        panel.add(lblNewLabel_12_1, "cell 2 7");

        JLabel lblNewLabel_11_1 = new JLabel("Desconto");
        panel.add(lblNewLabel_11_1, "cell 0 7");

        JLabel lblNewLabel_12_2 = new JLabel("R$");
        panel.add(lblNewLabel_12_2, "cell 2 8");

        JLabel lblNewLabel_11_2 = new JLabel("Taxa");
        panel.add(lblNewLabel_11_2, "cell 0 8");

        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(new Color(0, 0, 0));
        separator_2.setBackground(new Color(0, 0, 0));
        panel.add(separator_2, "cell 0 9 3 1,growx");

        JLabel lblNewLabel_13 = new JLabel("Total:");
        lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 14));
        panel.add(lblNewLabel_13, "cell 0 10");

        Panel panel_4 = new Panel();
        panel_4.setBackground(new Color(80, 224, 31));
        panel.add(panel_4, "cell 0 11 3 1,grow");
        panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JLabel lblNewLabel_14 = new JLabel("Finalizar Pedido");
        lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        panel_4.add(lblNewLabel_14, "cell 0 0,alignx center,aligny center");
    }
}
