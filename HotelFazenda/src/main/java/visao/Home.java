package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controle.ValidarDia;
import controle.Atividades.AtividadesDAO;
import controle.Funcionarios.FuncionariosDAO;
import controle.Hospedagens.HospedagensDAO;
import controle.Hospede.HospedeDAO;
import controle.Quartos.QuartosDAO;
import modelo.Atividades;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import utils.DefaultModal;
import utils.DefaultScreen;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;
 
	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
	HospedagensDAO HgDao = HospedagensDAO.getInstancia();
	HospedeDAO HDao = HospedeDAO.getInstancia();
	AtividadesDAO ADao = AtividadesDAO.getInstancia();
	QuartosDAO QDao = QuartosDAO.getConexao();
	FuncionariosDAO FDao = FuncionariosDAO.getConexao();
	ArrayList<Hospedagens> listaHospedagens;
	ArrayList<Hospedes> listaHospedes;
	ArrayList<Atividades> listaAtividades;
	ArrayList<Quartos> listaQuartos;
	ArrayList<Funcionarios> listaFuncionarios;
	private int diasSelecionados = 3; // Valor padrão inicial

	ArrayList<String> listaImagens = new ArrayList<String>(Arrays.asList("/visao/logoGrande.png", "/visao/Whatsapp.jpg",
			"/visao/Facebook.jpg", "/visao/instagram.png"));
	int imageIndex = 0;
	private JLabel lblNewLabel_9;
	LocalDate hoje = LocalDate.now();
	JPanel mostrarAtividades = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
			g2d.dispose();
		}
	};

	public void loadAtividades() {

		ArrayList<Atividades> listaAtividades = ADao.ListarAtividades();
		ArrayList<Atividades> atividadesProximas = new ArrayList<>();

		LocalDate limite = hoje.plusDays(diasSelecionados); // Usa diasSelecionados

		for (Atividades atividade : listaAtividades) {
			LocalDate dataAtividade = atividade.getData().toLocalDate();

			if (!dataAtividade.isBefore(hoje) && !dataAtividade.isAfter(limite)) {
				atividadesProximas.add(atividade);
			}
		}

		atividadesProximas.sort(Comparator.comparing(Atividades::getHorario));
		mostrarAtividades.setBounds(1313, 174, 272, 673);

		mostrarAtividades.removeAll();

		for (Atividades atividade : atividadesProximas) {
			JLabel labelAtividade = new JLabel(
					atividade.getNomeAtividade() + " - " + atividade.getData() + " - " + atividade.getHorario());
			labelAtividade.setFont(new Font("Arial", Font.BOLD, 14));
			mostrarAtividades.add(labelAtividade);
		}

		mostrarAtividades.revalidate();
		mostrarAtividades.repaint();
	}

	public Home() {
		 
		 
screen();
		loadAtividades();
		loadInfos();
		
		LocalDate hoje = LocalDate.now();
		if (ValidarDia.lerDia(hoje.toString())) {
		HgDao.AtualizarSituacao();

		}
		ADao.AtualizarAtividades();
		mostrarAtividades.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));

		JPanel panel_5 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		panel_5.setBounds(108, 41, 1096, 167);

		Principal.setLayout(null);
		Principal.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 5, 0, 0));

		JLabel lblQuarto = new JLabel("0");
		lblQuarto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQuarto.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblQuarto.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblAtividade = new JLabel("0");
		lblAtividade.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAtividade.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblAtividade.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHospedes = new JLabel("0");
		lblHospedes.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHospedes.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblHospedes.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblFunc = new JLabel("");
		lblFunc.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFunc.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblFunc.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHospedagem = new JLabel("");
		lblHospedagem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHospedagem.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblHospedagem.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_2 = new JLabel("Quartos ");
		lblNewLabel_7_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7 = new JLabel("Atividades");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_4 = new JLabel("Hospedes");
		lblNewLabel_7_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_4.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_3 = new JLabel("Funcionários");
		lblNewLabel_7_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_1 = new JLabel("Hospedagens");
		lblNewLabel_7_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_21 = new JLabel("Atividades nos proximos ");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_21.setBounds(1307, 120, 177, 43);
		Principal.add(lblNewLabel_21);

		Principal.add(mostrarAtividades);

		loadInfos();
		lblQuarto.setText(String.valueOf(listaQuartos.size()));
		lblAtividade.setText(String.valueOf(listaAtividades.size()));
		lblHospedes.setText(String.valueOf(listaHospedes.size()));
		lblFunc.setText(String.valueOf(listaFuncionarios.size()));
		lblHospedagem.setText(String.valueOf(listaHospedagens.size()));

		panel_5.add(lblFunc);
		panel_5.add(lblQuarto);
		panel_5.add(lblAtividade);
		panel_5.add(lblHospedagem);
		panel_5.add(lblHospedes);
		panel_5.add(lblNewLabel_7_3);
		panel_5.add(lblNewLabel_7_2);
		panel_5.add(lblNewLabel_7);
		panel_5.add(lblNewLabel_7_1);
		panel_5.add(lblNewLabel_7_4);

		JLabel lblNewLabel_10 = new JLabel(" ");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (imageIndex > 0) {
					imageIndex -= 1;
					updateImage();

				}
			}
		});
		lblNewLabel_10.setIcon(new ImageIcon(Home.class.getResource("/visao/arrowBack.png")));
		lblNewLabel_10.setBounds(119, 509, 57, 26);
		Principal.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("seta direita");
		lblNewLabel_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (imageIndex < listaImagens.size()) {
					imageIndex += 1;
					updateImage();
				}
			}
		});
		lblNewLabel_11.setBounds(1082, 524, 107, 20);
		Principal.add(lblNewLabel_11);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(186, 322, 863, 525);
		Principal.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel_9 = new JLabel("");
		panel_6.add(lblNewLabel_9);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);

		JComboBox comboBoxDias = new JComboBox();
		comboBoxDias.setBounds(1494, 126, 107, 34);
		Principal.add(comboBoxDias);
		updateImage();

		comboBoxDias.addItem("3 dias");
		comboBoxDias.addItem("7 dias");
		comboBoxDias.addItem("15 dias");
		comboBoxDias.addItem("30 dias");
		comboBoxDias.addItem("60 dias");

		comboBoxDias.addActionListener(e -> {
			switch ((String) comboBoxDias.getSelectedItem()) {
			case "3 dias":
				diasSelecionados = 3;
				break;
			case "7 dias":
				diasSelecionados = 7;
				break;
			case "15 dias":
				diasSelecionados = 15;
				break;
			case "30 dias":
				diasSelecionados = 30;
				break;
			case "60 dias":
				diasSelecionados = 60;
				break;
			}
			loadAtividades();
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

	public void loadInfos() {
		listaHospedagens = HgDao.ListarHospedagens();
		listaHospedes = HDao.ListarHospedes();
		listaAtividades = ADao.ListarAtividades();
		listaQuartos = QDao.ListarQuartos();
		listaFuncionarios = FDao.ListarFuncionarios();

	}

	private void updateImage() {
		lblNewLabel_9.setIcon(new ImageIcon(Home.class.getResource(listaImagens.get(imageIndex))));
	}

}
