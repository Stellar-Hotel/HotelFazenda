 package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Desktop;
import controle.Arredondar.RoundedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Conta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTextField textUser;
	private JTextField textEm;
	private JTextField textPron;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Conta frame = new Conta(null);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Conta(Funcionarios Func) {
		setBackground(new Color(250, 250, 250));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]",
				"[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px][40px][211px,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome=new Home(Func);
				TelaHome.setVisible(true);
				dispose();
			}
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(Conta.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");
		
				JLabel lblAtividades = new JLabel("Atividades");
				lblAtividades.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TelaAtividades TelaAtiv=new TelaAtividades(Func);
						TelaAtiv.setVisible(true);
						dispose();
					}
				});
				lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblAtividades.setIcon(new ImageIcon(Conta.class.getResource("/visao/Atividades.jpg")));
				BarraLateral.add(lblAtividades, "flowy,cell 0 2,grow");
		
				JLabel lblQuartos = new JLabel("Quartos");
				lblQuartos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						TelaDeAcomodacoes TelaAco=new TelaDeAcomodacoes(Func);
						TelaAco.setVisible(true);
						dispose();
					}
				});
				lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblQuartos.setIcon(new ImageIcon(Conta.class.getResource("/visao/Quartos.jpg")));
				BarraLateral.add(lblQuartos, "cell 0 3,grow");
		
				JLabel lblServicos = new JLabel("Serviços");
				lblServicos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						TelaServicos TelaServ=new TelaServicos(Func);
						TelaServ.setVisible(true);
						dispose();
					}
				});
				lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblServicos.setIcon(new ImageIcon(Conta.class.getResource("/visao/Servicos.jpg")));
				BarraLateral.add(lblServicos, "cell 0 4,grow");
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				AdminFuncionarios TelaAdm=new AdminFuncionarios(Func);
				TelaAdm.setVisible(true);
				dispose();
			}
		});
		lblFuncionarios.setIcon(new ImageIcon(Conta.class.getResource("/visao/funcionarios.png")));
		lblFuncionarios.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblFuncionarios, "cell 0 6");
		
		JLabel lblConta = new JLabel("Conta");
		lblConta.setIcon(new ImageIcon(Conta.class.getResource("/visao/conta.png")));
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblConta, "cell 0 7,alignx left");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 9,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Conta.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNewLabel_2 = new JLabel("Erik Roncaglio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 1 1,aligny bottom");
		lblNewLabel_2.setText(Func.getNome()+" "+Func.getSobrenome());

		JLabel lblNewLabel_3 = new JLabel("erikroncaglio@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");
		lblNewLabel_3.setText(Func.getEmailFunc());

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login TelaLogin=new Login();
				TelaLogin.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(Conta.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 3 2 1,alignx center,aligny top");
		
				JLabel lblHospede = new JLabel("Hospede");
				lblHospede.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TelaDeHospedes Chama = new TelaDeHospedes(Func);
						Chama.setVisible(true);
						dispose();
						
					}
				});
				lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblHospede.setIcon(new ImageIcon(Conta.class.getResource("/visao/Hospede.jpg")));
				BarraLateral.add(lblHospede, "cell 0 5,grow");

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
		lblNewLabel.setIcon(new ImageIcon(Conta.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(Conta.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(Conta.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[:220px:275px,grow][40px][40px][:40px:100px,grow][40px][40px][40px][40px,grow][40px][40px][40px][40px][:40px:100px,grow][40px]", "[40px][:40px:120px,grow][40px][40px][40px][40px][40px][40px][40px][grow][40px]"));
		
		JLabel lblNewLabel_1 = new JLabel("Conta");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(235, 235, 235));
		Principal.add(panel_5, "cell 0 1 1 9,grow");
		panel_5.setLayout(new MigLayout("", "[::137px,grow][::137px,grow]", "[30px][30px][30px][30px][30px][:30px:90px,grow][30px][30px][15px][30px][30px][::100px,grow]"));
		
		
		ImageIcon IC=new ImageIcon("C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\Avatar.jpg");
		
		JLabel lblNewLabel_18 = new JLabel("Dados do Usuário");
		lblNewLabel_18.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_18, "cell 0 0 2 1");
		
//		IC.setImage(IC.getImage().getScaledInstance(lblNewLabel_12.getWidth(), lblNewLabel_12.getHeight(), 100));
		
		
		JLabel lblNome = new JLabel("Blbla");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNome, "flowx,cell 0 7 2 1,alignx center");
		lblNome.setText(Func.getNome()+" "+Func.getSobrenome());
		
		JLabel lblNewLabel_15 = new JLabel("Email:");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_15, "cell 0 8 2 1,alignx center,aligny top");
		lblNewLabel_15.setText(Func.getUsuario().getLogin());
		
		JLabel lblNewLabel_16 = new JLabel("Pronomes:");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_16, "cell 0 9,alignx center");
		lblNewLabel_16.setText(Func.getPronomeFunc());
		
		JLabel lblNewLabel_17 = new JLabel("Telefone::");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_17, "cell 1 9,alignx center");
		lblNewLabel_17.setText(Func.getTelefone());
		
		JLabel lblNewLabel_12 = new JLabel("Setor");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_12, "cell 0 10,alignx center");
		lblNewLabel_12.setText(Func.getSetor());
		
		JLabel lblNewLabel_13 = new JLabel("Funcao");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_13, "cell 1 10,alignx center");
		lblNewLabel_13.setText(Func.getFuncao());
		ImageIcon Ver=new ImageIcon("C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\Ver.png");
		ImageIcon NaoVer=new ImageIcon("C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao//NaoVer.png");
		
		
		JLabel lblNewLabel_7 = new JLabel("Usuário:");
		Principal.add(lblNewLabel_7, "cell 4 2,alignx center,aligny center");
		
		textUser = new JTextField();
		Principal.add(textUser, "cell 5 2 5 1,growx,aligny center");
		textUser.setColumns(10);
		textUser.setText(Func.getUsuario().getLogin());
		
		JLabel lblNewLabel_9 = new JLabel("Email");
		Principal.add(lblNewLabel_9, "cell 4 4,alignx center");
		
		textEm = new JTextField();
		Principal.add(textEm, "cell 5 4 5 1,growx");
		textEm.setColumns(10);
		textEm.setText(Func.getEmailFunc());
		
		JLabel lblNewLabel_10 = new JLabel("Pronomes");
		Principal.add(lblNewLabel_10, "cell 4 6,alignx center");
		
		textPron = new JTextField();
		Principal.add(textPron, "cell 5 6 2 1,growx");
		textPron.setColumns(10);
		textPron.setText(Func.getPronomeFunc());
		
		JLabel lblNewLabel_11 = new JLabel("Senha");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		Principal.add(lblNewLabel_11, "cell 4 8,alignx trailing,aligny center");
		
		passwordField = new JPasswordField();
		Principal.add(passwordField, "cell 5 8 5 1,growx");
		passwordField.setEchoChar('*');
		passwordField.setText(Func.getUsuario().getSenha());
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setIcon(Ver);
		lblNewLabel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(lblNewLabel_14.getIcon()==Ver) {
				lblNewLabel_14.setIcon(NaoVer);
				passwordField.setEchoChar((char) 0);
				}else if(lblNewLabel_14.getIcon()==NaoVer)
				{
					lblNewLabel_14.setIcon(Ver);
					passwordField.setEchoChar('*');
				}
			}
		});
		
		Principal.add(lblNewLabel_14, "cell 10 8,alignx center");
		
		JButton btnDescartarMudanas = new JButton("Descartar Mudanças");
		btnDescartarMudanas.setForeground(Color.BLACK);
		btnDescartarMudanas.setBorder(new RoundedBorder(Color.black, 10));
		btnDescartarMudanas.setBackground(new Color(117, 187, 68));
		Principal.add(btnDescartarMudanas, "cell 9 10 3 1,alignx center");
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Func.setPronomeFunc(textPron.getText());
				Func.setEmailFunc(textEm.getText());
				Func.getUsuario().setLogin(textUser.getText());			
				Func.getUsuario().setSenha(passwordField.getText());
				
				
				lblNome.setText(Func.getNome()+""+Func.getSobrenome());
				lblNewLabel_15.setText(Func.getUsuario().getLogin());
				lblNewLabel_16.setText(Func.getPronomeFunc());
				lblNewLabel_17.setText(Func.getTelefone());
				lblNewLabel_12.setText(Func.getSetor());
				lblNewLabel_13.setText(Func.getFuncao());
				
			}
		});
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setBorder(new RoundedBorder(Color.black, 10));
		btnSalvar.setBackground(new Color(117, 187, 68));
		Principal.add(btnSalvar, "cell 12 10 2 1,alignx center");

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
					Desktop.getDesktop().browse(new URL("https://www.instagram.com/stellar_.hotel?igsh=bDl2dmkwY2MzNHFy").toURI());
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
		lblInstagram.setIcon(new ImageIcon(Conta.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		lblFacebook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=pIwHGjo3Lqs").toURI());
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
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(Conta.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		lblWhatsapp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=pIwHGjo3Lqs").toURI());
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
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(Conta.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://www.youtube.com/watch?v=pIwHGjo3Lqs").toURI());
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
		lblTwitter.setIcon(new ImageIcon(Conta.class.getResource("/visao/twitter.jpg")));
	}
}
