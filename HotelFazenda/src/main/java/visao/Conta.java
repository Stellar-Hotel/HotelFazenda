package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import utils.DefaultModal;
import utils.DefaultScreen;
import visao.Atividade.TelaAtividades;
import visao.Reserva.TelaDeAcomodacoes;
import visao.Servico.TelaServicos;

public class Conta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textUser;
	private JTextField textEm;
	private JTextField textPron;
	private JPasswordField passwordField;
	private JTextField textTel;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;
 
	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
	public Conta() {

		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
		screen();
		MaskFormatter mNum = null, mPron = null;

		try {
			mPron = new MaskFormatter("UUU/UUUU");
			mPron.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mNum = new MaskFormatter("+## ## #####-####");
			mNum.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		RoundedBorder bordaVermelha = new RoundedBorder(Color.red, 10);
		RoundedBorder bordaPreta = new RoundedBorder(Color.black, 10);

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("",
				"[:220px:275px,grow][40px][40px][:40px:100px,grow][40px][40px][40px][40px][40px,grow][40px][40px][40px][:40px:100px,grow][40px]",
				"[40px][:40px:120px,grow][40px][40px][40px][40px][40px][40px][40px][grow][40px]"));

		JLabel lblNewLabel_1 = new JLabel("Conta");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNewLabel_17 = new JLabel("Alterações");
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		Principal.add(lblNewLabel_17, "cell 1 0 3 1,alignx left,aligny bottom");

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(235, 235, 235));
		Principal.add(panel_5, "cell 0 1 1 9,grow");
		panel_5.setLayout(new MigLayout("", "[::137px,grow][::137px,grow]",
				"[30px][30px][30px][30px][30px][:30px:90px,grow][30px][30px][15px][30px][30px][::100px,grow]"));

		ImageIcon IC = new ImageIcon(
				"C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\Avatar.jpg");

		JLabel lblNewLabel_18 = new JLabel("Dados do Usuário");
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_18, "cell 0 0 2 1");

//		IC.setImage(IC.getImage().getScaledInstance(lblNewLabel_12.getWidth(), lblNewLabel_12.getHeight(), 100));

		JLabel lblNome = new JLabel("Blbla");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblNome, "flowx,cell 0 7 2 1,alignx center");
		lblNome.setText(Func.getNome() + " " + Func.getSobrenome());

		JLabel lblNewLabel_15 = new JLabel("Email:");
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_15, "cell 0 8 2 1,alignx center,aligny top");
		lblNewLabel_15.setText(Func.getUsuario().getLogin());

		JLabel lblNewLabel_16 = new JLabel("Pronomes:");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_16, "cell 0 9,alignx center");
		lblNewLabel_16.setText(Func.getPronomeFunc());

		JLabel lblTel = new JLabel("Telefone::");
		lblTel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblTel, "cell 1 9,alignx center");
		lblTel.setText(Func.getTelefone());

		JLabel lblNewLabel_12 = new JLabel("Setor");
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_12, "cell 0 10,alignx center");
		lblNewLabel_12.setText(Func.getSetor());

		JLabel lblNewLabel_13 = new JLabel("Funcao");
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_13, "cell 1 10,alignx center");
		lblNewLabel_13.setText(Func.getFuncao());

		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.setForeground(new Color(255, 38, 38));
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_19, "cell 10 2 3 1");

		JLabel lblNewLabel_7 = new JLabel("Usuário:");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Principal.add(lblNewLabel_7, "cell 4 2,alignx center,aligny center");

		textUser = new JTextField();
		textUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (textUser.getText().equals(Func.getUsuario().getLogin())) {
					textUser.setBorder(bordaPreta);
					lblNewLabel_19.setText("");

				} else {
					textUser.setBorder(bordaVermelha);
					lblNewLabel_19.setText("Este Dado será alterado!");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (textUser.getText().equals(Func.getUsuario().getLogin())) {
					textUser.setBorder(bordaPreta);
					lblNewLabel_19.setText("");

				} else {
					textUser.setBorder(bordaVermelha);
					lblNewLabel_19.setText("Este Dado será alterado!");
				}
			}
		});
		textUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});
		textUser.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textUser.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(textUser, "cell 5 2 5 1,growx,aligny center");
		textUser.setColumns(10);
		textUser.setText(Func.getUsuario().getLogin());

		JLabel lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_20.setForeground(new Color(255, 38, 38));
		Principal.add(lblNewLabel_20, "cell 10 4 3 1");

		JLabel lblNewLabel_9 = new JLabel("Email:");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Principal.add(lblNewLabel_9, "cell 4 4,alignx center");
		textEm = new JTextField();
		textEm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textEm.getText().equals(Func.getEmailFunc())) {
					textEm.setBorder(bordaPreta);
					lblNewLabel_20.setText("");
				} else {
					textEm.setBorder(bordaVermelha);
					lblNewLabel_20.setText("Este Dado será alterado!");
				}

			}
		});
		textEm.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		textEm.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(textEm, "cell 5 4 5 1,growx");
		textEm.setColumns(10);
		textEm.setText(Func.getEmailFunc());

		JLabel lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setForeground(new Color(255, 38, 38));
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_21, "cell 1 6 3 1,alignx right,aligny center");

		JLabel lblNewLabel_10 = new JLabel("Pronomes:");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Principal.add(lblNewLabel_10, "cell 4 6,alignx center");

		textPron = new JFormattedTextField(mPron);
		textPron.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textPron.getText().equals(Func.getPronomeFunc())) {
					textPron.setBorder(bordaPreta);
					lblNewLabel_21.setText("");
				} else {
					textPron.setBorder(bordaVermelha);
					lblNewLabel_21.setText("Este Dado será alterado!");
				}

			}
		});
		textPron.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		textPron.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(textPron, "cell 5 6 2 1,growx");
		textPron.setColumns(10);
		textPron.setText(Func.getPronomeFunc());

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		Principal.add(lblTelefone, "cell 7 6,alignx trailing");

		JLabel lblNewLabel_22 = new JLabel("");
		lblNewLabel_22.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_22.setForeground(new Color(255, 38, 38));
		Principal.add(lblNewLabel_22, "cell 10 6 3 1");

		textTel = new JFormattedTextField(mNum);
		textTel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textTel.getText().equals(Func.getTelefone())) {
					textTel.setBorder(bordaPreta);
					lblNewLabel_22.setText("");
				} else {
					textTel.setBorder(bordaVermelha);
					lblNewLabel_22.setText("Este Dado será alterado!");
				}
			}
		});
		textTel.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		textTel.setBorder(new RoundedBorder(Color.black, 10));
		textTel.setText(Func.getTelefone());
		Principal.add(textTel, "cell 8 6 2 1,growx");
		textTel.setColumns(10);

		JLabel lblNewLabel_23 = new JLabel("");
		lblNewLabel_23.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_23.setForeground(new Color(255, 38, 38));
		Principal.add(lblNewLabel_23, "cell 1 8 3 1,alignx right");

		JLabel lblNewLabel_11 = new JLabel("Senha:");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		Principal.add(lblNewLabel_11, "cell 4 8,alignx center,aligny center");

		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (passwordField.getText().equals(Func.getUsuario().getSenha())) {
					passwordField.setBorder(bordaPreta);
					lblNewLabel_23.setText("");
				} else {
					passwordField.setBorder(bordaVermelha);
					lblNewLabel_23.setText("Este Dado será alterado!");
				}
			}
		});
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		passwordField.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(passwordField, "cell 5 8 5 1,growx");
		passwordField.setEchoChar('*');
		passwordField.setText(Func.getUsuario().getSenha());

		ImageIcon Ver = new ImageIcon(Conta.class.getResource("/visao/Ver.png"));
		ImageIcon NaoVer = new ImageIcon(Conta.class.getResource("/visao/NaoVer.png"));
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(Ver);
		lblNewLabel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblNewLabel_14.getIcon() == Ver) {
					lblNewLabel_14.setIcon(NaoVer);
					passwordField.setEchoChar((char) 0);
				} else if (lblNewLabel_14.getIcon() == NaoVer) {
					lblNewLabel_14.setIcon(Ver);
					passwordField.setEchoChar('*');
				}
			}
		});

		Principal.add(lblNewLabel_14, "cell 10 8,alignx center");
		DefaultIconButton btnDescartarMudanas = new DefaultIconButton("Descartar Mudanças");
		btnDescartarMudanas.setBackgroundColor(new Color(255, 38, 38));
		btnDescartarMudanas.setHoverColor(Color.RED);
		btnDescartarMudanas.setHoverColor(Color.RED.darker());

		btnDescartarMudanas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnDescartarMudanas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUser.setText(Func.getUsuario().getLogin());
				textEm.setText(Func.getEmailFunc());
				textPron.setText(Func.getPronomeFunc());
				textTel.setText(Func.getTelefone());
				passwordField.setText(Func.getUsuario().getSenha());

			}
		});
		btnDescartarMudanas.setForeground(Color.BLACK);
		btnDescartarMudanas.setBorder(new RoundedBorder(Color.black, 10));
		btnDescartarMudanas.setBackground(new Color(255, 38, 38));
		Principal.add(btnDescartarMudanas, "cell 9 10 3 1,alignx center");

		Conta Tela = this;

		DefaultIconButton btnSalvar = new DefaultIconButton("Cancelar");

		btnSalvar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SenhaN = passwordField.getText();

				String senhaD = JOptionPane.showInputDialog("Insira a senha ATUAL de sua conta para atualizar");
				if (senhaD.equals(Func.getUsuario().getSenha())) {

					Func.setPronomeFunc(textPron.getText());
					Func.setEmailFunc(textEm.getText());
					Func.getUsuario().setLogin(textUser.getText());
					Func.getUsuario().setSenha(SenhaN);
					Func.setTelefone(textTel.getText());

					FuncionariosDAO dao = FuncionariosDAO.getConexao();
					UsuariosDAO daoU = UsuariosDAO.getInstancia();
					dao.AtualizarFuncionarios(Func);
					daoU.atualizarUsuarios(Func.getUsuario());

					textUser.setBorder(bordaPreta);
					textEm.setBorder(bordaPreta);
					textPron.setBorder(bordaPreta);
					textTel.setBorder(bordaPreta);
					passwordField.setBorder(bordaPreta);

					lblNewLabel_19.setText("");
					lblNewLabel_20.setText("");
					lblNewLabel_21.setText("");
					lblNewLabel_22.setText("");
					lblNewLabel_23.setText("");
				}

				lblNome.setText(Func.getNome() + "" + Func.getSobrenome());
				lblNewLabel_15.setText(Func.getUsuario().getLogin());
				lblNewLabel_16.setText(Func.getPronomeFunc());
				lblTel.setText(Func.getTelefone());
				lblNewLabel_12.setText(Func.getSetor());
				lblNewLabel_13.setText(Func.getFuncao());
				lblTel.setText(Func.getTelefone());

			}
		});
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setBorder(new RoundedBorder(Color.black, 10));
		btnSalvar.setBackground(new Color(117, 187, 68));
		Principal.add(btnSalvar, "cell 12 10 2 1,alignx center");
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
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
}
