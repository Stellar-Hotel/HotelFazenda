package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.Atividades.AtividadesDAO;
import controle.AtividadesHospedes.AtividadesHospedesDAO;
import controle.Funcionarios.FuncionariosDAO;
import controle.Hospedagens.HospedagensDAO;
import controle.Hospede.HospedeDAO;
import controle.Quartos.QuartosDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
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

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Canvas;
import javax.swing.JComboBox;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
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

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					Home frame = new Home();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}S
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */

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
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		loadAtividades();
		loadInfos();
		HgDao.AtualizarSituacao();

		mostrarAtividades.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]",
				"[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px][40px][40px][98.00,grow][]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(Home.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaDeHospedes Chama = new TelaDeHospedes();
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Chama.setVisible(true);
				dispose();

			}
		});
		
		JLabel lblNewLabel_19 = new JLabel("Quartos");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos2 q=new Quartos2( );
				q.setExtendedState(JFrame.MAXIMIZED_BOTH);
				q.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_19.setIcon(new ImageIcon(Home.class.getResource("/visao/Quartos.jpg")));
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_19, "cell 0 4");
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(Home.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 7,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaAtividades telaAtiv = new TelaAtividades();
				telaAtiv.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaAtiv.setVisible(true);
				dispose();

			}
		});
		lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAtividades.setIcon(new ImageIcon(Home.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 2,grow");

		JLabel lblQuartos = new JLabel("Reservas");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaDeAcomodacoes TelaDeAcomodacoes = new TelaDeAcomodacoes();
				TelaDeAcomodacoes.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaDeAcomodacoes.setVisible(true);
				dispose();

			}
		});
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(Home.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 3,grow");

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaServicos telaServ = new TelaServicos();
				telaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaServ.setVisible(true);
				dispose();

			}
		});
		lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblServicos.setIcon(new ImageIcon(Home.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 5,grow");

		JLabel lblNewLabel_1 = new JLabel("Funcionários");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				AdminFuncionarios telaAdm = new AdminFuncionarios();
				telaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaAdm.setVisible(true);
				dispose();

			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(Home.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_1, "cell 0 6");

		JLabel lblNewLabel_2 = new JLabel("Conta");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta();
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaConta.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2.setIcon(new ImageIcon(Home.class.getResource("/visao/conta.png")));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_2, "cell 0 8");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 10,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Home.class.getResource("/visao/Avatar.jpg")));
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
		lblNewLabel_5.setIcon(new ImageIcon(Home.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(Home.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(Home.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");

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
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
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
		lblInstagram.setIcon(new ImageIcon(Home.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(Home.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(Home.class.getResource("/visao/Whatsapp.jpg")));

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
		lblTwitter.setIcon(new ImageIcon(Home.class.getResource("/visao/twitter.jpg")));

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
