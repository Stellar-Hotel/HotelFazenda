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

public class Conta extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTextField textUser;
	private JTextField textField;
	private JTextField textField_1;
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

		JLabel lblNewLabel_3 = new JLabel("erikroncaglio@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

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
		Principal.setLayout(new MigLayout("", "[:220px:275px,grow][40px][40px][:40px:100px,grow][40px][40px][40px][40px][40px][40px][40px][40px][:40px:100px,grow][40px]", "[40px][40px][40px][40px][40px][40px][40px][40px][grow][40px]"));
		
		JLabel lblNewLabel_1 = new JLabel("Conta");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(235, 235, 235));
		Principal.add(panel_5, "cell 0 1 1 8,grow");
		panel_5.setLayout(new MigLayout("", "[grow][]", "[40px][40px][40px][40px][40px][40px][40px][40px][grow]"));
		
		JPanel panel_6 = new JPanel();
		
		
		ImageIcon IC=new ImageIcon("C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\Avatar.jpg");

		JLabel lblNewLabel_12 = new JLabel() {
			public void paintComponent(Graphics g)
			{
				
				super.paintComponent(g);
//				g.drawImage(IC.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, getWidth(), getHeight(), this);
				ImageIcon icon=(ImageIcon) getIcon();
				if(icon!=null)
				{
//					ImageDrawer.drawScaledImage(icon.getImage(),this, g);
				}
				
			}
			
		};
		
	//	ImageIcon IC2=new ImageIcon(IC.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		panel_6.setLayout(new BorderLayout(0, 0));
		lblNewLabel_12.setIcon(IC);
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_6.add(lblNewLabel_12, BorderLayout.CENTER);
		panel_5.add(panel_6, "cell 0 0 2 4,alignx center,aligny center");
		
//		IC.setImage(IC.getImage().getScaledInstance(lblNewLabel_12.getWidth(), lblNewLabel_12.getHeight(), 100));
		
		
		JLabel Nomr = new JLabel("Blbla");
		Nomr.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(Nomr, "flowx,cell 0 4");
		
		JLabel lblNewLabel_15 = new JLabel("BlaBla");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_15, "cell 1 4,alignx right");
		
		JLabel lblNewLabel_7 = new JLabel("Usuário:");
		Principal.add(lblNewLabel_7, "cell 4 2,alignx center,aligny center");
		
		textUser = new JTextField();
		Principal.add(textUser, "cell 5 2 5 1,growx,aligny center");
		textUser.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Email");
		Principal.add(lblNewLabel_9, "cell 4 3,alignx center");
		
		textField = new JTextField();
		Principal.add(textField, "cell 5 3 5 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Pronomes");
		Principal.add(lblNewLabel_10, "cell 4 4,alignx center");
		
		textField_1 = new JTextField();
		Principal.add(textField_1, "cell 5 4 5 1,growx");
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Senha");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		Principal.add(lblNewLabel_11, "cell 4 5,alignx center,aligny center");
		
		passwordField = new JPasswordField();
		Principal.add(passwordField, "cell 5 5 5 1,growx");
		
		JButton btnNewButton_1 = new JButton("Descartar");
		Principal.add(btnNewButton_1, "cell 8 9 3 1,alignx center");
		
		JButton btnNewButton = new JButton("Salvar");
		Principal.add(btnNewButton, "cell 11 9 3 1,alignx center");

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
