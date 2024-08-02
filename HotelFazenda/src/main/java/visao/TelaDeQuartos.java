package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import controle.Hospedagens.HospedagensDAO;
import controle.Hospede.HospedeDAO;
import controle.Quartos.QuartosDAO;
import modelo.Atividades;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import modelo.Servicos;
import net.miginfocom.swing.MigLayout;
import utils.DefaultScreen;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TelaDeQuartos extends DefaultScreen {

	private ArrayList<Hospedagens> ListaHospedagens;
	private ArrayList<Quartos> ListaQuartos;
	private DefaultTableModel model1;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private JTextField textCPF;
	private JTextField textChecki;
	private JTextField textChecko;
	private ArrayList<Integer> listaCombobox = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {

	// TelaDeQuartos frame = new TelaDeQuartos();
	// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	protected void atualizarJTable(int tipoQuarto) {

		QuartosDAO Qdao = QuartosDAO.getConexao();
		ListaQuartos = Qdao.ListarQuartos();

		for (Quartos q : ListaQuartos) {
			if (q.getTipoQuarto() == tipoQuarto && q.getSituacao() == 1) {
				listaCombobox.add(q.getIdQuarto());
			}
		}

		DefaultTableModel model1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Hospede", "Checkin", "Checkout" }));

		model1.setRowCount(0);

		HospedagensDAO HospedagenDAO = HospedagensDAO.getInstancia();
		ListaHospedagens = HospedagenDAO.ListarHospedagens();

		for (Hospedagens p : ListaHospedagens) {

			model1.addRow(new Object[] { p.getQuarto().getIdQuarto(), p.getHospede().getNome(), p.getCheckin(), p.getCheckout() });
		}

	 
		table.setModel(model1);
	}

	/**
	 * Create the frame.
	 */
	public TelaDeQuartos(int x ) {
		super();
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
		setTitle("Tela de Quartos");

		Quartos quartos = new Quartos();

		ListaHospedagens = new ArrayList<Hospedagens>();

		DecimalFormat formato = new DecimalFormat("#.##");

		Quartos QuartoSelcionado = new Quartos();

		MaskFormatter formatoCpf = null;
		model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Hospede", "Checkin", "Checkout" });

		table = new JTable(model1);
		try {
			formatoCpf = new MaskFormatter("###.###.###-##");
			formatoCpf.setPlaceholderCharacter('_');
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MaskFormatter Data = null;
		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(
				new MigLayout("", "[201.00,grow][100px:74.00:150px,grow][:500.00:550px,grow]", "[][322.00,grow,fill]"));

		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][grow]"));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(
				new MigLayout("", "[grow][100px:74.00,grow][grow]", "[][grow][][][][][grow][grow][][][][][]"));

		JLabel lblNewLabel_7 = new JLabel("Reservar Quarto");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_6.add(lblNewLabel_7, "cell 0 0,aligny top");

		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12, "cell 0 1 2 1,grow");

		JLabel lblNewLabel_9 = new JLabel("CPF do hóspede");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_9, "cell 0 2,alignx left,aligny bottom");

		textCPF = new JTextField();
		textCPF = new JFormattedTextField(formatoCpf);
		panel_6.add(textCPF, "cell 1 2 2 1,growx");
		textCPF.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Data checkin");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_10, "cell 0 3,alignx left,aligny bottom");

		textChecki = new JTextField();
		textChecki = new JFormattedTextField(Data);
		panel_6.add(textChecki, "cell 1 3 2 1,grow");
		textChecki.setColumns(10);

		JLabel lblNewLabel_21 = new JLabel("Data checkout");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_21, "cell 0 4,alignx left,aligny bottom");

		textChecko = new JTextField();
		textChecko = new JFormattedTextField(Data);
		panel_6.add(textChecko, "cell 1 4 2 1,growx");
		textChecko.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("Quartos disponíveis");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_19, "cell 0 5,alignx left");

		atualizarJTable(x);

		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
		for (Integer valor : listaCombobox) {
			model.addElement(valor);

		}
		JComboBox comboBox = new JComboBox<>(model);

		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel_6.add(comboBox, "cell 1 5 2 1,growx");

		JLabel lblNewLabel_13 = new JLabel("Forma de pagamento:");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_13, "cell 0 6,alignx left,aligny bottom");

		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, "cell 0 7 3 4,growx,aligny top");
		panel_8.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));

		JPanel panel_13 = new JPanel();
		panel_13.setBorder(new LineBorder(Color.GREEN));
		panel_8.add(panel_13, "cell 0 0,grow");

		JLabel lblNewLabel_16 = new JLabel("");

		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_16.setIcon(new ImageIcon(
				"C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\nota.png"));
		panel_13.add(lblNewLabel_16);

		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new LineBorder(Color.CYAN));
		panel_8.add(panel_14, "cell 1 0,growx,aligny top");

		JLabel lblNewLabel_17 = new JLabel("");
		lblNewLabel_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		lblNewLabel_17.setIcon(new ImageIcon(
				"C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\pix.png"));
		panel_14.add(lblNewLabel_17);

		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new LineBorder(Color.RED));
		panel_8.add(panel_15, "cell 2 0,growx,aligny top");

		JLabel lblNewLabel_18 = new JLabel("");

		lblNewLabel_18.setIcon(new ImageIcon(
				"C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\cartao.png"));
		panel_15.add(lblNewLabel_18);

		JLabel label = new JLabel("New label");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_6.add(label, "cell 0 11");

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, "cell 0 1,alignx left,growy");
		panel_7.setLayout(new MigLayout("", "[grow][][grow][][]", "[grow][][][grow][][grow]"));

		JLabel lblNewLabel_11 = new JLabel("Subtotal:");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_11, "cell 0 0,alignx left,aligny bottom");

		JLabel lblsubtotal = new JLabel("");
		lblsubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblsubtotal, "cell 1 0");

		JPanel panel_11 = new JPanel();
		panel_7.add(panel_11, "cell 2 0 3 3,grow");

		JLabel lbldesconto = new JLabel("");
		lbldesconto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lbldesconto, "cell 1 1");

		JLabel lbltotal = new JLabel("");
		lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lbltotal, "cell 1 2");

		JPanel panel_9 = new JPanel();
		panel_7.add(panel_9, "cell 2 3 3 2,alignx center,aligny bottom");

		JButton btnNewButton_2 = new JButton("Cancelar");
		panel_9.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBorder(new RoundedBorder(Color.black, 8));
		btnNewButton_2.setBackground(new Color(204, 0, 0));

		JButton btnNewButton_3 = new JButton("Finalizar");
		panel_9.add(btnNewButton_3);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object pos =  comboBox.getSelectedItem();
				if (textCPF.getText().isEmpty() || textChecki.getText().isEmpty() || textChecko.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos");
				} else {
					String cpf = textCPF.getText();
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date checkin = null;
					Date checkout = null;
					QuartosDAO qDao = QuartosDAO.getConexao();

					Quartos quarto = qDao.buscarQuartoPorId((Integer) comboBox.getSelectedItem());

					try {
						checkin = new Date(dateFormat.parse(textChecki.getText()).getTime());
						checkout = new Date(dateFormat.parse(textChecko.getText()).getTime());
					} catch (ParseException e1) {
						e1.printStackTrace();
						return;
					}

					HospedeDAO hospedeDAO = HospedeDAO.getInstancia();
					Hospedes hospede = hospedeDAO.buscarHospedePorCPF(cpf);
					if (hospede == null) {
						JOptionPane.showMessageDialog(null, "Hóspede não encontrado.");
						return;
					}

					HospedagensDAO hospedagensDAO = HospedagensDAO.getInstancia();
					Hospedagens hospedagem = new Hospedagens();
					hospedagem.setCheckin(checkin);
					hospedagem.setCheckout(checkout);
					hospedagem.setHospde(hospede);
					hospedagem.setQuarto(quarto);
					hospedagensDAO.InserirHospedagem(hospedagem);
					atualizarJTable(x);
				}
			}

		});

		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBorder(new RoundedBorder(Color.black, 8));
		btnNewButton_3.setBackground(new Color(117, 187, 68));

		JLabel lblNewLabel_12 = new JLabel("Desconto:");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_12, "cell 0 1,alignx left,aligny top");

		JLabel lblNewLabel_14 = new JLabel("Total:");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_14, "cell 0 2,alignx left,aligny top");

		JPanel panel_10 = new JPanel();
		panel_7.add(panel_10, "cell 0 3 1 2,grow");

		JLabel lblNewLabel_1 = new JLabel("Quartos");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0");

		JScrollPane scrollPane1 = new JScrollPane(table);
		atualizarJTable(x);
		Principal.add(scrollPane1, "cell 2 1,grow");

	 

		lblNewLabel_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria();
					}

					desconto = subTotal * 0.08;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});

		lblNewLabel_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria();
					}

					desconto = subTotal * 0.05;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});
		lblNewLabel_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria();
					}

					desconto = subTotal * 0.03;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});
		setPrincipalPanel(Principal);
	}
}
