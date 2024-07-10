package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class TelaDeHospedes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					TelaDeHospedes frame = new TelaDeHospedes();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaDeHospedes(Funcionarios Func) {
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
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px,baseline][40px][211.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home(Func);
				TelaHome.setVisible(true);
				dispose();
			}
			
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Home.jpg")));
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
				lblAtividades.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Atividades.jpg")));
				BarraLateral.add(lblAtividades, "cell 0 2,grow");
		
				JLabel lblQuartos = new JLabel("Quartos");
				lblQuartos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						TelaDeAcomodacoes TelaDeAcomodacoes=new TelaDeAcomodacoes(Func);
						TelaDeAcomodacoes.setVisible(true);
						dispose();
					}
				});
				lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblQuartos.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Quartos.jpg")));
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
						lblServicos.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Servicos.jpg")));
						BarraLateral.add(lblServicos, "cell 0 4,grow");
		
				JLabel lblHospede = new JLabel("Hospede");
				lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblHospede.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Hospede.jpg")));
				BarraLateral.add(lblHospede, "cell 0 5,grow");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/funcionarios.png")));
		BarraLateral.add(lblNewLabel_1, "flowx,cell 0 6");
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm=new AdminFuncionarios(Func);
				TelaAdm.setVisible(true);
				dispose();
			}
		});
		lblFuncionarios.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblFuncionarios, "cell 0 6");
		
		JLabel lblNewLabel_2_1 = new JLabel("Conta");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta=new Conta(Func);
				telaConta.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2_1.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/conta.png")));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_2_1, "cell 0 7");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 9,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNewLabel_2 = new JLabel("Erik Roncaglio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 1 1,aligny bottom");

		JLabel lblNewLabel_3 = new JLabel(Func.getEmailFunc());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login novoLogin = new Login();
				novoLogin.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new GridLayout(1, 0, 0, 0));

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
		lblInstagram.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop().browse(new URL("https://x.com/Stellar1933323?t=sMKnmdFjz2z29kZNNmOY3g&s=09").toURI());
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
		lblTwitter.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/twitter.jpg")));
	}
}
