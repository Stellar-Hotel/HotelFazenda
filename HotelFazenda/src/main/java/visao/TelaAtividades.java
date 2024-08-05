package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultModal;
import utils.DefaultScreen;

public class TelaAtividades extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;
	AtividadesDAO ADao = AtividadesDAO.getInstancia();
	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
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
	private JTextField textCapacidade;

	public TelaAtividades() {
		ADao.AtualizarAtividades();
		screen();

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

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("",
				"[:100px:100px][74.00][92.00][][][:45px:45px,grow][grow][-47.00][36.00,grow][121px]",
				"[7.00][24.00][:29.00px:50px][][][][][][][][-21.00][][42.00][:-32.00px:10px,grow][-41.00][][-25.00][:300px:300px][:90px:90px,grow][:75.00:75]"));

		JLabel lblNewLabel_1 = new JLabel("Atividades");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 1,alignx center,aligny top");

		JScrollPane cTable = new JScrollPane();
		Principal.add(cTable, "cell 6 14 4 5,grow");

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));

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
		// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
		// LetterDocumentFilter());

		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBorder(new RoundedBorder(Color.BLACK, 8));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaAtividadesHospedes chama = new TelaAtividadesHospedes(Func, ListaAtividades);
				chama.setVisible(true);

			}
		});

		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(117, 187, 68));
		panel_7.add(btnNewButton_1, "cell 2 11");

		table = new CustomTable(model1);
		cTable.setViewportView(table);

		model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade", "IdadeMinima", "Horario",
				"HorarioFim", "NomeAtividade", "Data", "IDFuncionario", "Capacidade", "Ações" }));

		model2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "IdAtividade", "Funcionario", "NomeAtividade" }));

		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cTable.setViewportView(table);
				atualizarJTable();
			}
		});

		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cTable.setViewportView(new JTable(model2));
				atualizarJTable();
			}
		});

		atualizarJTable();
		contentPane.add(Principal, "cell 1 1,grow");

	}

	public void screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("insets 0, gap 0", "[200px:1064px:200][grow]",
				"[73:69px:73,grow,center][560px,grow][52px]"));

		DefaultModal BarraLateral = new DefaultModal();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_10);
		panel_10.setLayout(new MigLayout("", "[93px]", "[32px,grow]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		panel_10.add(lblHome, "cell 0 0,alignx left,aligny center");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home();
				TelaHome.setVisible(true);
				TelaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblHome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Home.jpg")));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[163px,grow,fill]", "[32px,grow,fill]"));

		JLabel lblNewLabel_15 = new JLabel("Funcionários");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_15, "cell 0 0,alignx left,aligny center");
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm = new AdminFuncionarios();
				TelaAdm.setVisible(true);
				TelaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_15.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_5);
		panel_5.setLayout(new MigLayout("", "[123px,grow]", "[32px,grow]"));

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblHospede, "cell 0 0,alignx left,aligny center");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeHospedes Chama = new TelaDeHospedes();
				Chama.setVisible(true);
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblHospede.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHospede.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Hospede.jpg")));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_6);
		panel_6.setLayout(new MigLayout("", "[112px,grow]", "[32px,grow]"));

		JLabel lblNewLabel_19 = new JLabel("Quartos");
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblNewLabel_19, "cell 0 0,alignx left,aligny center");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Quartos2 q2 = new Quartos2();
				q2.setVisible(true);
				q2.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_19.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Quartos.jpg")));
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_7);
		panel_7.setLayout(new MigLayout("", "[120px,grow]", "[32px,grow]"));

		JLabel lblQuartos = new JLabel("Reservas");
		lblQuartos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblQuartos, "cell 0 0,alignx left,aligny center");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeAcomodacoes TelaAco = new TelaDeAcomodacoes();
				TelaAco.setVisible(true);
				TelaAco.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblQuartos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblQuartos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/reserva.png")));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_8);
		panel_8.setLayout(new MigLayout("", "[115px,grow]", "[32px,grow]"));

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_8.add(lblServicos, "cell 0 0,alignx left,aligny center");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaServicos TelaServ = new TelaServicos();
				TelaServ.setVisible(true);
				TelaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblServicos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblServicos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Servicos.jpg")));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_9);
		panel_9.setLayout(new MigLayout("", "[138px,grow]", "[32px,grow]"));

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(lblAtividades, "cell 0 0,alignx left,aligny center");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaAtividades TelaAtiv = new TelaAtividades();
				TelaAtiv.setVisible(true);
				TelaAtiv.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblAtividades.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblAtividades.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Atividades.jpg")));

		JLabel label = new JLabel("");
		BarraLateral.add(label);

		JLabel lblNewLabel_3 = new JLabel("");
		BarraLateral.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		BarraLateral.add(lblNewLabel_4);

		JLabel lblNewLabel = new JLabel("");
		BarraLateral.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		BarraLateral.add(label_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel);
		panel.setLayout(new MigLayout("", "[][167.00,grow,center]", "[32.00,grow,center]"));

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login novoLogin = new Login();
				novoLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				novoLogin.setVisible(true);
				dispose();

			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 0 2 1,alignx center,aligny center");

		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(203, 167, 58));

		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior
				.setLayout(new MigLayout("", "[200:209.00px:200][grow,center][50:40px:50,right]", "[29.00px,center]"));
		ImageIcon logoIcon = new ImageIcon(Quartos2.class.getResource("/visao/logoMedia.png"));

		Image logoImage = logoIcon.getImage().getScaledInstance(190, 55, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		logoIcon = new ImageIcon(logoImage);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_4, "cell 0 0,alignx left,aligny center");
		panel_4.setLayout(new MigLayout("", "[251.00px]", "[15][25]"));

		JLabel lblNewLabel_1 = new JLabel("Bem-Vindo(a),");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNome = new JLabel(Func.getNome());
		lblNome.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNome, "cell 0 1,alignx left,aligny top");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		BarraSuperior.add(panel_11, "flowx,cell 1 0");
		JLabel lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblLogo);
		lblLogo.setIcon(logoIcon);
		panel_11.setBackground(new Color(203, 167, 58));
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_2, "cell 1 0,alignx center,aligny center");
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		BarraSuperior.add(lblNewLabel_2, "cell 2 0,alignx center");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta();
				telaConta.setVisible(true);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});

		ImageIcon contaIcon = new ImageIcon(Quartos2.class.getResource("/visao/contaAppBar.png"));
		Image contaImage = contaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		contaIcon = new ImageIcon(contaImage);
		lblNewLabel_2.setIcon(contaIcon);
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
		lblInstagram.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Facebook.jpg")));

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
		lblTwitter.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/twitter.jpg")));
	}

	protected void atualizarJTable() {

		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {
				int linhaSelecionada = table.getSelectedRow();
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

		};
		DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade",
				"IdadeMinima", "Horario", "HorarioFim", "NomeAtividade", "Data", "Capacidade", "Ações" });

		AtividadesDAO AtivDAO = AtividadesDAO.getInstancia();
		ListaAtividades = AtivDAO.ListarAtividades();

		for (int i = 0; i < ListaAtividades.size(); i++) {
			Atividades p = ListaAtividades.get(i);
			modelo1.addRow(new Object[] { p.getIdAtividade(), p.getIdadeMinima(), p.getHorario(), p.getHorarioFim(),
					p.getNomeAtividade(), p.getData(), p.getCapacidade() });
		}

		table.setModel(modelo1);

		TableActionCellRender cellRenderer = new TableActionCellRender(true, true); // Inicialmente nenhuma linha
																					// selecionada
		table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);

		// Adicionar um MouseListener à tabela para atualizar a linha selecionada
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				if (row >= 0) {
					cellRenderer.setSelectedRow(row);
					table.repaint(); // Repaint to apply the new row color
				}
			}
		});

		table.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event, true, true));
		table.setRowHeight(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(145);

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
