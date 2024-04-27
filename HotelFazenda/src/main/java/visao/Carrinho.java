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

import controle.Atividades.AtividadesDAO;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import java.awt.Panel;
import net.miginfocom.swing.MigLayout;
import modelo.Atividades;
import modelo.Servicos;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class Carrinho extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private DefaultTableModel model1;
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
   
    	
        model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "Produtos", "Preço", "Quantidade",
  				"Sub-Total" }));
          
              table = new JTable(model1);
      
    	
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


       
        JPanel panel = new JPanel();
        panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
        panel.setBackground(new Color(163, 182, 233));
        contentPane.add(panel, "cell 5 2 1 6,grow");
        panel.setLayout(null);

        JLabel lblNewLabel_4 = new JLabel("Compras");
        lblNewLabel_4.setBounds(24, 27, 114, 17);
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("Pagamento");
        lblNewLabel_5.setBounds(24, 66, 160, 33);
        lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD, 30));
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Selecione o método de pagamento:");
        lblNewLabel_6.setBounds(24, 134, 275, 17);
        lblNewLabel_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        panel.add(lblNewLabel_6);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
        panel_1.setBounds(24, 180, 86, 70);
        panel_1.setToolTipText("");
        panel_1.setBackground(new Color(255, 255, 255));
        panel.add(panel_1);
        panel_1.setLayout(new MigLayout("", "[95.00px]", "[75.00px]"));
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/nota.png")));
        panel_1.add(lblNewLabel_1, "cell 0 0,alignx center,aligny center");

        JLabel lblNewLabel_7 = new JLabel("Dinheiro");
        lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_7.setBounds(34, 261, 56, 14);
        panel.add(lblNewLabel_7);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
        panel_2.setBounds(131, 180, 86, 70);
        panel_2.setBackground(new Color(255, 255, 255));
        panel.add(panel_2);
        panel_2.setLayout(new MigLayout("", "[102.00px]", "[87.00px]"));
        
        JLabel lblNewLabel_15 = new JLabel("");
        lblNewLabel_15.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/pix.png")));
        panel_2.add(lblNewLabel_15, "cell 0 0,alignx center,aligny center");

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new LineBorder(new Color(128, 0, 64), 2, true));
        panel_3.setBounds(232, 180, 86, 70);
        panel_3.setBackground(new Color(255, 255, 255));
        panel.add(panel_3);
        panel_3.setLayout(new MigLayout("", "[103.00px]", "[88.00px]"));
        
        JLabel lblNewLabel_16 = new JLabel("");
        lblNewLabel_16.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/cartao.png")));
        panel_3.add(lblNewLabel_16, "cell 0 0,alignx center,aligny center");

        JLabel lblNewLabel_8 = new JLabel("Pix");
        lblNewLabel_8.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_8.setBounds(165, 261, 26, 14);
        panel.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("Cartão");
        lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_9.setBounds(257, 261, 42, 14);
        panel.add(lblNewLabel_9);

        JLabel lblNewLabel_10 = new JLabel("Total dos itens:");
        lblNewLabel_10.setBounds(24, 309, 127, 21);
        lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 18));
        panel.add(lblNewLabel_10);

        JLabel lblNewLabel_11 = new JLabel("Subtotal");
        lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_11.setBounds(29, 348, 64, 14);
        panel.add(lblNewLabel_11);

        JLabel lblNewLabel_12 = new JLabel("R$");
        lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_12.setBounds(245, 348, 73, 14);
        panel.add(lblNewLabel_12);

        JLabel lblNewLabel_12_1 = new JLabel("R$");
        lblNewLabel_12_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_12_1.setBounds(247, 373, 71, 14);
        panel.add(lblNewLabel_12_1);

        JLabel lblNewLabel_11_1 = new JLabel("Desconto");
        lblNewLabel_11_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_11_1.setBounds(29, 373, 64, 14);
        panel.add(lblNewLabel_11_1);

        JLabel lblNewLabel_12_2 = new JLabel("R$");
        lblNewLabel_12_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_12_2.setBounds(247, 396, 71, 14);
        panel.add(lblNewLabel_12_2);

        JLabel lblNewLabel_11_2 = new JLabel("Taxa");
        lblNewLabel_11_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        lblNewLabel_11_2.setBounds(29, 396, 64, 14);
        panel.add(lblNewLabel_11_2);

        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(10, 434, 321, 2);
        separator_2.setForeground(new Color(0, 0, 0));
        separator_2.setBackground(new Color(0, 0, 0));
        panel.add(separator_2);

        JLabel lblNewLabel_13 = new JLabel("Total:");
        lblNewLabel_13.setBounds(24, 457, 64, 17);
        lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 22));
        panel.add(lblNewLabel_13);

        Panel panel_4 = new Panel();
        panel_4.setBounds(24, 505, 299, 45);
        panel_4.setBackground(new Color(80, 224, 31));
        panel.add(panel_4);
        panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));

        JLabel lblNewLabel_14 = new JLabel("Finalizar Pedido");
        lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        panel_4.add(lblNewLabel_14, "cell 0 0,alignx center,aligny center");
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(new Color(0, 0, 0));
        separator_1.setBackground(new Color(0, 0, 0));
        separator_1.setBounds(85, 360, 150, 1);
        panel.add(separator_1);
        
        JSeparator separator_1_1 = new JSeparator();
        separator_1_1.setForeground(Color.BLACK);
        separator_1_1.setBackground(Color.BLACK);
        separator_1_1.setBounds(97, 383, 140, 1);
        panel.add(separator_1_1);
        
        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(Color.BLACK);
        separator_1_2.setBackground(Color.BLACK);
        separator_1_2.setBounds(64, 408, 173, 1);
        panel.add(separator_1_2);
        
        JScrollPane cTable = new JScrollPane();
        contentPane.add(cTable, "cell 2 6,grow");
        
    
        cTable.setViewportView(table);
      
        atualizarJTable(lista);
    }
    protected void atualizarJTable(ArrayList<Servicos> lista) {
    	DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {}, new
    	String[] { "Produtos", "Preço", "Quantidade",
			"Sub-Total"});

    	

    	 for (int i = 0; i < lista.size(); i++) {
    	 Servicos s = lista.get(i);
    	 modelo1.addRow(new Object[] { s.getNomeServico(), s.getPrecoServico(),s.getQuantidade(),s.getPrecoServico()*s.getQuantidade() });
    	}

    	table.setModel(modelo1);
    	}
}
