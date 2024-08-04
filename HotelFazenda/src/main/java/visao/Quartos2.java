package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Quartos.QuartosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultModal;
import utils.DefaultScreen;

public class Quartos2 extends JFrame {

	private ArrayList<Quartos> ListaQuartos;
	private DefaultTableModel model1;
	private static final long serialVersionUID = 1L;
 
	private JTable table;
	private JTextField textCPF;
	private JTextField textChecki;
	private JTextField textChecko;
	private JTextField textTipo;
	private JTextField textSituacao;
	private JTextField textCapacidade;
	private JTextField textManutencao;
	private JTextField textCama;
	private JTextField textFrigobar;
	private JTextField textAr;
	private JTextField textBanheira;
	private JTextField textTv;
	private JTextField textDiaria;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;
 
	Funcionarios Func2 = CurrentFunc.getInstance().getLoggedInFuncionario();
	protected void atualizarJTable() {

		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {

			}

		};

		DefaultTableModel model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "Tipo", "Situação",
				"Capacidade", "Manutenção", "Cama", "Frigobar", "Ar Condicionado", "Banheira", "TV", "Diária" }));

		model1.setRowCount(0);

		QuartosDAO QuartoDAO = QuartosDAO.getConexao();
		ListaQuartos = QuartoDAO.ListarQuartos();

		for (int i = 0; i < ListaQuartos.size(); i++) {

			Quartos p = ListaQuartos.get(i);
			model1.addRow(new Object[] { p.getTipoQuarto(), p.getSituacao(), p.getMaxPessoas(), p.getManutencao(),
					p.getTipoCama(), p.getFrigobar(), p.getArCondicionado(), p.getBanheira(), p.getTV(),
					"R$" + p.getPrecoDiaria() });
		}

		table.setModel(model1);
		TableActionCellRender cellRenderer = new TableActionCellRender(true, true); // Inicialmente nenhuma linha
																					// selecionada
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

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

		table.setRowHeight(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);
	}

	/**
	 * Create the frame.
	 */
	public Quartos2() {
	 
screen();
		setTitle("Tela de Quartos");

		Quartos quartos = new Quartos();

		ListaQuartos = new ArrayList<Quartos>();

		DecimalFormat formato = new DecimalFormat("#.##");

		Quartos QuartoSelcionado = new Quartos();

		MaskFormatter formatoCpf = null;

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
		Principal.setLayout(new MigLayout("", "[201.00,grow][100px:n,grow][100px:74.00:150px,grow][:500.00:550px,grow]",
				"[][322.00,grow,fill]"));

		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 0 1 2 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][grow]"));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0 2 2,grow");
		panel_6.setLayout(new MigLayout("", "[::100px,grow][100px:74.00:150px,grow][::100px,grow]",
				"[][][][][][][][][][][][grow][]"));

		JLabel lblNewLabel_7 = new JLabel("Tipo: ");
		panel_6.add(lblNewLabel_7, "cell 0 1,alignx trailing");

		textCPF = new JTextField();

		textChecki = new JTextField();

		textChecko = new JTextField();

		textTipo = new JTextField();
		textTipo.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textTipo, "cell 1 1 2 1,growx");
		textTipo.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Situação: ");
		panel_6.add(lblNewLabel_9, "cell 0 2,alignx trailing");

		textSituacao = new JTextField();
		textSituacao.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textSituacao, "cell 1 2 2 1,growx");
		textSituacao.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Capacidade: ");
		panel_6.add(lblNewLabel_10, "cell 0 3,alignx trailing");

		textCapacidade = new JTextField();
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCapacidade, "cell 1 3 2 1,growx");
		textCapacidade.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Manutenção: ");
		panel_6.add(lblNewLabel_11, "cell 0 4,alignx trailing");

		textManutencao = new JTextField();
		textManutencao.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textManutencao, "cell 1 4 2 1,growx");
		textManutencao.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Cama: ");
		panel_6.add(lblNewLabel_12, "cell 0 5,alignx trailing");

		textCama = new JTextField();
		textCama.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCama, "cell 1 5 2 1,growx");
		textCama.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Frigobar: ");
		panel_6.add(lblNewLabel_13, "cell 0 6,alignx trailing");

		textFrigobar = new JTextField();
		textFrigobar.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textFrigobar, "cell 1 6 2 1,growx");
		textFrigobar.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Ar Condicionado: ");
		panel_6.add(lblNewLabel_14, "cell 0 7,alignx trailing");

		textAr = new JTextField();
		textAr.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textAr, "cell 1 7 2 1,growx");
		textAr.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Banheira: ");
		panel_6.add(lblNewLabel_16, "cell 0 8,alignx trailing");

		textBanheira = new JTextField();
		textBanheira.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textBanheira, "cell 1 8 2 1,growx");
		textBanheira.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Televisão: ");
		panel_6.add(lblNewLabel_17, "cell 0 9,alignx trailing");

		textTv = new JTextField();
		textTv.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textTv, "cell 1 9 2 1,growx");
		textTv.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Diária: ");
		panel_6.add(lblNewLabel_18, "cell 0 10,alignx trailing");

		textDiaria = new JTextField();
		textDiaria.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textDiaria, "cell 1 10 2 1,growx");
		textDiaria.setColumns(10);

		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				q.setArCondicionado(Boolean.valueOf(textAr.getText()));
				q.setBanheira(Boolean.valueOf(textBanheira.getText()));
				q.setFrigobar(Boolean.valueOf(textFrigobar.getText()));
				q.setTV(Boolean.valueOf(textTv.getText()));
				q.setManutencao(textManutencao.getText());
				q.setMaxPessoas(Integer.valueOf(textCapacidade.getText()));
				q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
				q.setSituacao(Integer.valueOf(textSituacao.getText()));
				q.setTipoCama(textCama.getText());
				q.setTipoQuarto(Integer.valueOf(textTipo.getText()));
				dao.inserirQuarto(q);
				atualizarJTable();
			}
		});
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBorder(new RoundedBorder(Color.black, 10));
		btnSalvar.setBackground(new Color(117, 187, 68));
		panel_6.add(btnSalvar, "cell 0 12,alignx center");

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				int linha = table.getSelectedRow();

				q.setIdQuarto(ListaQuartos.get(linha).getIdQuarto());
				q.setArCondicionado(Boolean.valueOf(textAr.getText()));
				q.setBanheira(Boolean.valueOf(textBanheira.getText()));
				q.setFrigobar(Boolean.valueOf(textFrigobar.getText()));
				q.setManutencao(textManutencao.getText());
				q.setMaxPessoas(Integer.valueOf(textCapacidade.getText()));
				q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
				q.setSituacao(Integer.valueOf(textSituacao.getText()));
				q.setTipoCama(textCama.getText());
				q.setTipoQuarto(Integer.valueOf(textTipo.getText()));
				q.setTV(Boolean.valueOf(textTv.getText()));
				dao.atualizarQuarto(q);
				atualizarJTable();
			}
		});
		btnAtualizar.setBorder(new RoundedBorder(Color.black, 10));
		btnAtualizar.setBackground(new Color(117, 187, 68));
		panel_6.add(btnAtualizar, "cell 1 12,alignx center");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				int linha = table.getSelectedRow();

				q.setIdQuarto(ListaQuartos.get(linha).getIdQuarto());
				dao.removerQuarto(q);
				atualizarJTable();
			}
		});
		btnExcluir.setBorder(new RoundedBorder(Color.black, 10));
		btnExcluir.setBackground(new Color(117, 187, 68));
		panel_6.add(btnExcluir, "cell 2 12");

		JLabel lblNewLabel_1 = new JLabel("Quartos");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0");

		model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Situação do quarto", "Ações" });

		table = new CustomTable(model1);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					int i = table.getSelectedRow();
					if (i != -1) { // Verifica se alguma linha foi selecionada
						textAr.setText(String.valueOf(ListaQuartos.get(i).getArCondicionado()));
						textBanheira.setText(String.valueOf(ListaQuartos.get(i).getBanheira()));
						textCama.setText(ListaQuartos.get(i).getTipoCama());
						textCapacidade.setText(String.valueOf(ListaQuartos.get(i).getMaxPessoas()));
						textDiaria.setText(String.valueOf(ListaQuartos.get(i).getPrecoDiaria()));
						textFrigobar.setText(String.valueOf(ListaQuartos.get(i).getFrigobar()));
						textManutencao.setText(String.valueOf(ListaQuartos.get(i).getManutencao()));
						textSituacao.setText(String.valueOf(ListaQuartos.get(i).getSituacao()));
						textTipo.setText(String.valueOf(ListaQuartos.get(i).getTipoQuarto()));
						textTv.setText(String.valueOf(ListaQuartos.get(i).getTV()));
					}
				}
			}

		});
		JScrollPane scrollPane1 = new JScrollPane(table);
		atualizarJTable();
		Principal.add(scrollPane1, "cell 3 1,grow");

	 

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int i = table.getSelectedRow();
					if (i != -1) { // Verifica se alguma linha foi selecionada
						// Recupera os dados da linha selecionada
						int IdQuarto = ListaQuartos.get(i).getIdQuarto();
						int MaxPessoas = ListaQuartos.get(i).getMaxPessoas();
						String TipoCama = ListaQuartos.get(i).getTipoCama();
						String Manutencao = ListaQuartos.get(i).getManutencao();
						Integer TipoQuarto = ListaQuartos.get(i).getTipoQuarto();
						Boolean Frigobar = ListaQuartos.get(i).getFrigobar();
						Boolean ArCondicionado = ListaQuartos.get(i).getArCondicionado();
						Boolean Banheira = ListaQuartos.get(i).getBanheira();
						Boolean TV = ListaQuartos.get(i).getTV();
						Float PrecoDiaria = ListaQuartos.get(i).getPrecoDiaria();

						// Preenche os textfields com os dados recuperados
						QuartoSelcionado.setIdQuarto(IdQuarto);
						QuartoSelcionado.setMaxPessoas(MaxPessoas);
						QuartoSelcionado.setTipoCama(TipoCama);
						QuartoSelcionado.setManutencao(Manutencao);
						QuartoSelcionado.setTipoQuarto(TipoQuarto);
						QuartoSelcionado.setFrigobar(Frigobar);
						QuartoSelcionado.setArCondicionado(ArCondicionado);
						QuartoSelcionado.setBanheira(Banheira);
						QuartoSelcionado.setTV(TV);
						QuartoSelcionado.setPrecoDiaria(PrecoDiaria);

					}
				}
			}
		});
		contentPane.add(Principal, "cell 1 1,grow");

 	}
	public void screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("insets 0, gap 0", "[200px:1064px:200][grow]", "[73:69px:73,grow,center][560px,grow][52px]"));

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

		JLabel lblNome = new JLabel(Func2.getNome());
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
}