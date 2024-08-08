package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.Arredondar.RoundedBorder;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Usuarios;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import utils.DefaultJOptionPane;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.IOException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTextField txtLogin;
	private JPasswordField passwordField;
	ArrayList<Usuarios> Lista;
	private JTable table;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		DefaultJOptionPane.test();
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Login frame = new Login();
//				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 670);

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		Lista = new ArrayList<Usuarios>();

		 
		contentPane_1 = new JPanel();
	 

		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		panel.setBounds(512, 41, 641, 388);
		contentPane_1.add(panel);
		panel.setLayout(null);
		panel.setBackground(new Color(64, 49, 39));

		JLabel lblNewLabel = new JLabel("Bem vindo ao ");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(7, 7, 190, 41);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		panel.add(lblNewLabel);
		DefaultIconButton btnNewButton_1 = new DefaultIconButton(new ImageIcon(getClass().getResource("/visao/arrowBack.png")));
		btnNewButton_1.setBackgroundColor(new Color(203, 167, 58));
		btnNewButton_1.setHoverColor(new Color(203, 167, 58).darker());
		btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		
						dispose();
					}
				});
	btnNewButton_1.setBounds(10, 11, 44, 40);
			contentPane_1.add(btnNewButton_1);
		JLabel lblNewLabel_2_1_1 = new JLabel("Entrar");
		lblNewLabel_2_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_2_1_1.setBounds(7, 28, 144, 70);
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 55));
		panel.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_3 = new JLabel("Digite seu nome de usuario ou endereço de e-mail");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(33, 121, 554, 32);
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		panel.add(lblNewLabel_3);

		txtLogin = new JTextField();
		txtLogin.setBounds(33, 160, 554, 41);
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					FazLogin();
				}
			}
		});
		txtLogin.setBorder(new RoundedBorder(Color.black, 10)); // Altere a cor e o raio conforme necessário
		panel.add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Digite sua senha");
		lblNewLabel_3_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_3_1.setBounds(33, 227, 554, 32);
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		panel.add(lblNewLabel_3_1);

		passwordField = new JPasswordField(20);
		passwordField.setBounds(33, 266, 554, 41);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					FazLogin();
				}
			}
		});
		passwordField.setBorder(new RoundedBorder(Color.black, 10));
		passwordField.setToolTipText("Digite sua senha");
		passwordField.setColumns(10);
		passwordField.setEchoChar('*');
		panel.add(passwordField);

		JLabel lblNewLabel_1 = new JLabel("Hotel Fazenda");
		lblNewLabel_1.setBounds(204, 7, 190, 41);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(203, 167, 58));
		panel.add(lblNewLabel_1);

		 ImageIcon Ver = createWhiteIcon("/visao/Ver.png");
	        ImageIcon NaoVer = createWhiteIcon("/visao/NaoVer.png");

		ImageIcon icon = new ImageIcon(getClass().getResource("/visao/Sair.png"));
		
				JLabel lblNewLabel_4 = new JLabel("");
				lblNewLabel_4.setBounds(597, 276, 24, 24);
				lblNewLabel_4.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (lblNewLabel_4.getIcon() == Ver) {
							lblNewLabel_4.setIcon(NaoVer);
							passwordField.setEchoChar((char) 0);
						} else if (lblNewLabel_4.getIcon() == NaoVer) {
							lblNewLabel_4.setIcon(Ver);
							passwordField.setEchoChar('*');
						}
					}
				});
				lblNewLabel_4.setIcon(Ver);
				panel.add(lblNewLabel_4);
		DefaultIconButton btnNewButton = new DefaultIconButton("Entrar", icon);
		btnNewButton.setBounds(389, 318, 199, 44);
		btnNewButton.setHoverColor(new Color(203, 167, 58).darker());
		btnNewButton.setBackgroundColor(new Color(203, 167, 58));
		 
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		
						FazLogin();
		
					}
				});
				
						panel.add(btnNewButton);

//		ImageIcon logo = new ImageIcon(getClass().getResource("/visao/logoLogin.png"));
//
//		JLabel lblNewLabel_5 = new JLabel(logo);

		BufferedImage originalImage;
		try {
			originalImage = ImageIO.read(getClass().getResource("/visao/logoLogin.png"));
			// Definir o tamanho desejado
			int newWidth = 1470; // Largura desejada
			int newHeight = 770; // Altura desejada

			// Redimensionar a imagem
			Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

			// Criar um ImageIcon a partir da imagem redimensionada
			ImageIcon logo = new ImageIcon(resizedImage);

			// Criar um JLabel e definir o ImageIcon
			JLabel lblNewLabel_5 = new JLabel(logo);
			lblNewLabel_5.setBounds(0, 0, 1200, 700);
			 
			contentPane_1.add(lblNewLabel_5);
			
			
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	
	}

	private void FazLogin() {
		String login = txtLogin.getText();
		String senha = passwordField.getText();

		UsuariosDAO dao = UsuariosDAO.getInstancia();

		Usuarios usuarios = dao.BuscarUsuario(login.trim(), senha.trim());

		if (usuarios != null) {
			TelaSucesso sucesso = new TelaSucesso("Login Efetuado com Sucesso");

			FuncionariosDAO DAOF = FuncionariosDAO.getConexao();
			Funcionarios Func = DAOF.BuscarFuncionarioPorIdUsuario(usuarios);
			CurrentFunc.getInstance().setLoggedInFuncionario(Func);
			Home c = new Home();
			c.setExtendedState(JFrame.MAXIMIZED_BOTH);
			c.setVisible(true);

			dispose();
			sucesso.setVisible(true);

		} else {
			TelaErro erro = new TelaErro("Algum Erro Ocorreu");
			erro.setVisible(true);
			txtLogin.setBorder(new RoundedBorder(Color.RED, 10)); // Mudando a cor da borda para vermelho
			passwordField.setBorder(new RoundedBorder(Color.RED, 10));
		}
	}
	  private static ImageIcon createWhiteIcon(String path) {
	        try {
	            BufferedImage image = ImageIO.read(Login.class.getResource(path));
	            BufferedImage whiteImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
	            
	            Graphics2D g2d = whiteImage.createGraphics();
	            g2d.drawImage(image, 0, 0, null);
	            
	            // Alterar a cor da imagem para branco
	            RescaleOp rescaleOp = new RescaleOp(new float[]{1f, 1f, 1f, 1f}, new float[]{255f, 255f, 255f, 0f}, null);
	            g2d.drawImage(image, rescaleOp, 0, 0);
	            g2d.dispose();
	            
	            return new ImageIcon(whiteImage);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }


}
