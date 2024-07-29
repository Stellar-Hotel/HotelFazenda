package visao;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import controle.AtividadesHospedes.AtividadesHospedesDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TelaAtividadesHospedes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTable table;
    private DefaultTableModel model;
    private ArrayList<AtividadesHospedes> ListaatividadesHospedes;

    public TelaAtividadesHospedes(Funcionarios Func) {
        ListaatividadesHospedes = new ArrayList<AtividadesHospedes>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1080, 720);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 1044, 670);
        contentPane.add(panel);
        panel.setLayout(new MigLayout("", "[][][:100px:100px][][:100px:100px][:200:200,grow][:493.00:493.00,grow]", "[][][][85.00][:100px:100px,grow][:203.00px:203.00px,grow][:300:300]"));

        JLabel lblNewLabel = new JLabel("Nome da Atividade");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        panel.add(lblNewLabel, "cell 2 1,alignx center");

        JPanel panel_1 = new JPanel();
        panel_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        panel.add(panel_1, "cell 2 4 2 2,grow");
        panel_1.setLayout(new MigLayout("", "[][][]", "[][][][][][][][]"));

        JLabel lblNewLabel_1 = new JLabel("Adicionar Hospede");
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        panel_1.add(lblNewLabel_1, "cell 1 0");

        JLabel lblNewLabel_2 = new JLabel("Documento");
        lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        panel_1.add(lblNewLabel_2, "cell 0 3");

        textField = new JTextField();
        panel_1.add(textField, "cell 1 3 2 1,growx");
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Adicionar");
        btnNewButton.setBackground(new Color(117, 187, 68));
        btnNewButton.setBorder(new RoundedBorder(Color.black, 10));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para adicionar hospede
            }
        });
        panel_1.add(btnNewButton, "cell 0 7,alignx center");

        JButton btnNewButton_1 = new JButton("Alterar");
        btnNewButton_1.setBackground(new Color(117, 187, 68));
        btnNewButton_1.setBorder(new RoundedBorder(Color.black, 10));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para alterar hospede
            }
        });
        panel_1.add(btnNewButton_1, "cell 1 7,alignx center");

        JButton btnNewButton_2 = new JButton("Excluir");
        btnNewButton_2.setBackground(new Color(117, 187, 68));
        btnNewButton_2.setBorder(new RoundedBorder(Color.black, 10));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para excluir hospede
            }
        });
        panel_1.add(btnNewButton_2, "cell 2 7,alignx center");

        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, "cell 6 4 1 3,grow");

        // Configurar o modelo da tabela
        model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome" });
        table = new JTable(model);
        scrollPane.setViewportView(table);

        // Atualizar a tabela
        atualizarJTable();
    }

    protected void atualizarJTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Limpar linhas existentes

        AtividadesHospedesDAO hospedesatividadesDAO = AtividadesHospedesDAO.getInstancia();
        ListaatividadesHospedes = hospedesatividadesDAO.ListarAtividadesHospedes();

        for (AtividadesHospedes p : ListaatividadesHospedes) {
            model.addRow(new Object[] { p.getHospede().getNome() });
        }
    }
}
