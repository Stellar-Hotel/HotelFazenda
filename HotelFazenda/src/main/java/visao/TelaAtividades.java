package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.DocumentFilter.FilterBypass;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import visao.TelaDeHospedes.LetterDocumentFilter;

import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class TelaAtividades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private DefaultTableModel model1;
	private DefaultTableModel model2;

	private ArrayList<Atividades> ListaAtividades;
	private ArrayList<Atividades> ListaAtividadesinscritas;
	private JTextField textIdade;
	private JTextField textHorario;
	private JTextField textNomeatividade;
	private JTextField TextHorarioFim;
	private JTextField textData;
	private JTextField textField;
	private JTextField textCapacidade;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					TelaAtividades frame = new TelaAtividades();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//				
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaAtividades(Funcionarios Func) {
		MaskFormatter Data = null;
		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Num = null;
		try {
			Num = new MaskFormatter("##");
			Num.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Idade = null;
		try {
			Idade = new MaskFormatter("##");
			Idade.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Horario = null;
		try {
			Horario = new MaskFormatter("##:##");
			Horario.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListaAtividades = new ArrayList<Atividades>();
		ListaAtividadesinscritas = new ArrayList<Atividades>();

		model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade", "IdadeMinima", "Horario",
				"HorarioFim", "NomeAtividade", "Data", "IDFuncionario", "Capacidade" }));

		model2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "IdAtividade", "Funcionario", "NomeAtividade" }));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]",
				"[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[:130:130,grow]",
				"[20px:20px:20px][40px][40px][40px][40px][40px][40px][40px][211.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home(Func);
				TelaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaHome.setVisible(true);
				dispose();
			}
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaDeHospedes Chama = new TelaDeHospedes(Func);
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Chama.setVisible(true);
				dispose();
			}
		});
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 5,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaAtividades TelaAtiv = new TelaAtividades(Func);
				TelaAtiv.setVisible(true);
				dispose();
			}
		});
		lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAtividades.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 2,grow");

		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaDeAcomodacoes TelaDeAcomodacoes = new TelaDeAcomodacoes(Func);
				TelaDeAcomodacoes.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaDeAcomodacoes.setVisible(true);
				dispose();

			}
		});
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 3,grow");

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaServicos TelaServ = new TelaServicos(Func);
				TelaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaServ.setVisible(true);
				dispose();

			}
		});
		lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblServicos.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 4,grow");

		JLabel lblNewLabel_13 = new JLabel("Funcionários");
		lblNewLabel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm = new AdminFuncionarios(Func);
				TelaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaAdm.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_13.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_13, "cell 0 6");

		JLabel lblNewLabel_2 = new JLabel("Conta");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta(Func);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaConta.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/conta.png")));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_2, "cell 0 7");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 9,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNome = new JLabel("Erik Roncaglio");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNome, "cell 1 1,aligny bottom");
		lblNome.setText(Func.getNome() + " " + Func.getSobrenome());

		JLabel lblNewLabel_3 = new JLabel(Func.getEmailFunc());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login novoLogin = new Login();

				novoLogin.setVisible(true);
				novoLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				dispose();

			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 3 2 1,alignx center,aligny top");

		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior.setLayout(new MigLayout("",
				"[40px:54.00px:40px][150.00][300px:360.00px:300px][grow][40px:40px:40px,right]", "[29.00px]"));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 0, 33, 31);
		lblNewLabel.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/search.png")));

		txtPesquisa = new JTextField();
		panel_4.add(txtPesquisa, "cell 1 0,growx,aligny top");
		txtPesquisa.setBackground(new Color(245, 245, 245));
		txtPesquisa.setText("Search");
		txtPesquisa.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_2, "cell 4 0,grow");

		JLabel lblNewLabel_8 = new JLabel("");
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("",
				"[:100px:100px][74.00][92.00][][][:45px:45px,grow][grow][-47.00][36.00,grow][121px]",
				"[7.00][24.00][:29.00px:50px][][][][][][][][-21.00][][42.00][:-32.00px:10px,grow][-41.00][][-25.00][:300px:300px][:90px:90px,grow][:75.00:75]"));

		JLabel lblNewLabel_1 = new JLabel("Atividades");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 1,alignx center,aligny top");

		JScrollPane spTable = new JScrollPane();
		Principal.add(spTable, "cell 6 14 4 5,grow");

		table = new JTable(model1);
		spTable.setViewportView(table);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				spTable.setViewportView(new JTable(model2));
				atualizarJTable();
			}
		});
		Principal.add(panel_6, "flowy,cell 1 2");

		JLabel lblNewLabel_9 = new JLabel("Inscritos");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_6.add(lblNewLabel_9);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.LIGHT_GRAY);
		separator_1.setBackground(Color.LIGHT_GRAY);
		Principal.add(separator_1, "cell 1 6,growx,aligny top");

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(192, 192, 192));
		separator.setBackground(new Color(192, 192, 192));
		Principal.add(separator, "cell 0 6,growx,aligny top");

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				spTable.setViewportView(table);
				atualizarJTable();
			}
		});
		Principal.add(panel_5, "flowx,cell 0 2");

		JLabel lblNewLabel_7 = new JLabel("Todas as atividades");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_5.add(lblNewLabel_7);

		JPanel panel_7 = new JPanel();
		Principal.add(panel_7, "cell 0 15 5 1,grow");
		panel_7.setLayout(new MigLayout("", "[:110:110][:93.00:70][][:50:50][:90:90][]",
				"[14px,grow][20px,grow][20px,grow][][20px,grow][][][grow][][grow][grow][grow]"));

		JLabel lblNewLabel_10 = new JLabel("Cadastrar Atividade");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_10, "cell 0 0,alignx center,aligny top");

		JLabel lblNewLabel_12_1_1_1_1_1_1 = new JLabel("Nome da Atividade");
		lblNewLabel_12_1_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_1, "cell 0 1");

		textNomeatividade = new JTextField();
		textNomeatividade.setColumns(10);
		panel_7.add(textNomeatividade, "cell 1 1 2 1,growx,aligny center");
		textNomeatividade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNomeatividade.getDocument()).setDocumentFilter(new LetterDocumentFilter());

		JLabel lblNewLabel_12_1_1_1 = new JLabel("Idade");
		lblNewLabel_12_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1, "cell 3 1,alignx center");

		textIdade = new JFormattedTextField(Idade);
		panel_7.add(textIdade, "cell 4 1,growx,aligny center");
		textIdade.setColumns(10);
		textIdade.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1_3 = new JLabel("Capacidade");
		lblNewLabel_12_1_1_1_1_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_3, "cell 0 3,alignx center,aligny center");

		textCapacidade = new JFormattedTextField(Num);
		textCapacidade.setColumns(10);
		panel_7.add(textCapacidade, "cell 1 3 2 1,growx");
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1_2 = new JLabel("Data");
		lblNewLabel_12_1_1_1_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_2, "cell 3 3,alignx center,aligny center");

		textData = new JFormattedTextField(Data);
		textData.setColumns(10);
		panel_7.add(textData, "cell 4 3,growx,aligny center");
		textData.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1 = new JLabel("Horario");
		lblNewLabel_12_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1, "cell 0 5,alignx center,aligny center");

		textHorario = new JFormattedTextField(Horario);
		panel_7.add(textHorario, "cell 1 5,growx,aligny center");
		textHorario.setColumns(10);
		textHorario.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1 = new JLabel("Horario Fim");
		lblNewLabel_12_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1, "cell 2 5,alignx center");

		TextHorarioFim = new JFormattedTextField(Horario);
		TextHorarioFim.setColumns(10);
		panel_7.add(TextHorarioFim, "cell 3 5 2 1,growx,aligny center");
		TextHorarioFim.setBorder(new RoundedBorder(Color.black, 10));

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
						|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
						|| (textData.getText().isEmpty()) || (textCapacidade.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Textos vazios insira algo para adicionar!");
				} else {
					Integer Idade = Integer.valueOf(textIdade.getText());
					String Horario = textHorario.getText();
					String HorarioFim = TextHorarioFim.getText();
					String NomeAtividade = textNomeatividade.getText();
					int Capacidade = Integer.valueOf(textCapacidade.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dateFormat.setLenient(false);
					Date data = null;

					try {
						data = new Date(dateFormat.parse(textData.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data invalida.");
						return;
					}

					String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
					if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {
						JOptionPane.showMessageDialog(null, "Horário inválido. Insira no formato HH:mm");
					} else {
						Atividades ativ = new Atividades();

						ativ.setIdadeMinima(Idade);
						ativ.setHorario(Horario);
						ativ.setHorarioFim(HorarioFim);
						ativ.setNomeAtividade(NomeAtividade);
						ativ.setData(data);
						ativ.setCapacidade(Capacidade);
						System.out.println(Func.getIdFuncionario());
						ativ.setFuncionario(Func);

						AtividadesDAO DAO = AtividadesDAO.getInstancia();
						int id = DAO.InserirAtividades(ativ);

						if (id > 0) {
							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);
							atualizarJTable();
						}
					}
				}
			}
		});
		// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
		// LetterDocumentFilter());

		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(117, 187, 68));
		panel_7.add(btnCadastrar, "cell 1 7");

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setForeground(new Color(255, 255, 255));
		btnAlterar.setBackground(new Color(117, 187, 68));
		btnAlterar.setBorder(new RoundedBorder(Color.black, 8));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
						|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
						|| (textData.getText().isEmpty() || (textCapacidade.getText().isEmpty()))) {
					JOptionPane.showMessageDialog(null, "Textos vazios insira algo para atualizar");
				} else {

					Integer Idade = Integer.valueOf(textIdade.getText());
					String Horario = textHorario.getText();
					String HorarioFim = TextHorarioFim.getText();
					String NomeAtividade = textNomeatividade.getText();
					int Capacidade = Integer.valueOf(textCapacidade.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dateFormat.setLenient(false);
					Date data = null;

					try {
						data = new Date(dateFormat.parse(textData.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data invalida.");
						return;
					}
					String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
					if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {
						JOptionPane.showMessageDialog(null, "Horário inválido. Insira no formato HH:mm");
					} else {

						int linha = table.getSelectedRow();

						
						Atividades ativ = new Atividades();

						ativ.setIdadeMinima(Idade);
						ativ.setHorario(Horario);
						ativ.setHorarioFim(HorarioFim);
						ativ.setNomeAtividade(NomeAtividade);
						ativ.setData(data);
						ativ.setCapacidade(Capacidade);
						ativ.setFuncionario(Func);
						ativ.setIdAtividade(ListaAtividades.get(linha).getIdAtividade());
						

						AtividadesDAO DAO = AtividadesDAO.getInstancia();
						DAO.AtualizarAtividades(ativ);
						if (linha < 0) {
							JOptionPane.showMessageDialog(null, "selecione uma linha");
						} else if (linha >= 0) {
							DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							// model1.setValueAt(Idade, linha, 0);
							model1.setValueAt(Idade, linha, 1);
							model1.setValueAt(Horario, linha, 2);
							model1.setValueAt(HorarioFim, linha, 3);
							model1.setValueAt(NomeAtividade, linha, 4);
							model1.setValueAt(data, linha, 5);
							model1.setValueAt(Capacidade, linha, 6);

							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);

						}
					}

				}
			}
		});
		panel_7.add(btnAlterar, "flowx,cell 2 7,growx");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(117, 187, 68));
		btnExcluir.setBorder(new RoundedBorder(Color.black, 8));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AtividadesDAO DAO = AtividadesDAO.getInstancia();
				int linha = table.getSelectedRow();
				Atividades ativ = new Atividades();

				if (linha >= 0) {
					ativ = ListaAtividades.get(linha);
					DAO.RemoverAtividades(ativ);
					atualizarJTable();
					TelaSucesso c = new TelaSucesso("Atividade Exluída!");
					c.setVisible(true);
				} else if (linha <= 0) {
					JOptionPane.showMessageDialog(null, "selecione uma linha para excluir");
				}

			}
		});
		panel_7.add(btnExcluir, "cell 3 7,growx");

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.LIGHT_GRAY);
		panel_7.add(separator_2, "cell 0 8 6 1,growx");

		JLabel lblNewLabel_10_1_1 = new JLabel("Cadastrar Hospede");
		lblNewLabel_10_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_10_1_1, "cell 2 9 2 1");

		JLabel lblNewLabel_11_2 = new JLabel("Cadastrar Hospede");
		lblNewLabel_11_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_11_2, "cell 0 10,alignx left");

		textField = new JTextField();
		panel_7.add(textField, "cell 1 10 4 1,growx,aligny center");
		textField.setColumns(10);
		textField.setBorder(new RoundedBorder(Color.black, 10));
		// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
		// LetterDocumentFilter());

		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBorder(new RoundedBorder(Color.BLACK, 8));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int linha = table.getSelectedRow();
				
				if(linha >= 0) {
					TelaAtividadesHospedes chama = new TelaAtividadesHospedes(Func, ListaAtividades);
					chama.setVisible(true);
					dispose();
				}else if(linha <= 0) {

					JOptionPane.showMessageDialog(null, "selecione uma linha para adicionar um hospede");				
				}
			}
		});
		

		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(117, 187, 68));
		panel_7.add(btnNewButton_1, "cell 2 11");

		JPanel BarraInferior = new JPanel();
		BarraInferior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraInferior, "cell 1 2,grow");
		BarraInferior.setLayout(
				new MigLayout("", "[][679.00,grow,center][center][center][center][]", "[42.00,grow,center]"));

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		BarraInferior.add(panel_1, "cell 4 0,grow");
		panel_1.setLayout(new MigLayout("", "[][][][]", "[]"));

		JLabel lblInstagram = new JLabel("");
		lblInstagram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URL("https://www.instagram.com/stellar_.hotel?igsh=bDl2dmkwY2MzNHFy").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(lblInstagram, "cell 0 0");
		lblInstagram.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URL("https://x.com/Stellar1933323?t=sMKnmdFjz2z29kZNNmOY3g&s=09").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/twitter.jpg")));

		atualizarJTable();

	}

	protected void atualizarJTable() {
		DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade",
				"IdadeMinima", "Horario", "HorarioFim", "NomeAtividade", "Data", "Capacidade" });

		AtividadesDAO AtivDAO = AtividadesDAO.getInstancia();
		ListaAtividades = AtivDAO.ListarAtividades();

		for (int i = 0; i < ListaAtividades.size(); i++) {
			Atividades p = ListaAtividades.get(i);
			modelo1.addRow(new Object[] { p.getIdAtividade(), p.getIdadeMinima(), p.getHorario(), p.getHorarioFim(),
					p.getNomeAtividade(), p.getData(), p.getCapacidade() });
		}

		table.setModel(modelo1);
	}

	class LetterDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException { // insertString: Este método é chamado quando uma inserção de texto é
												// feita em um documento de texto.

			if (string != null && string.matches("[a-zA-Z]+")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException { // Este método é chamado quando uma substituição de texto é feita em um
												// documento de texto.
			if (text != null && text.matches("[a-zA-Z]+")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException { // Este método é
																									// chamado quando
																									// uma remoção de
																									// texto é feita em
																									// um documento de
																									// texto.
			super.remove(fb, offset, length);
		}
	}
}
