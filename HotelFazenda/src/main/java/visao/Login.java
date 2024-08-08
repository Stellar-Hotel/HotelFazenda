package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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

		Lista = new ArrayList<Usuarios>();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1080px,Grow]", "[720px,Grow]"));

		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[:84px:84px,grow][84px][210.00px,grow][:125px:125px][119px][143px,grow][115px][]", "[:1.00px:14px][:50px:70px][:125px:125px,grow][14.00px][38.00px][:30px:30px,grow][:70px:70px,grow][33.00px][:30px:30px,grow][93.00,grow][:45px:45px][:45px:45px][grow]"));

		JLabel lblNewLabel = new JLabel("Bem vindo ao ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		panel.add(lblNewLabel, "flowx,cell 0 0,growx,aligny top");

		JLabel lblNewLabel_2_1_1 = new JLabel("Entrar");
		lblNewLabel_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 55));
		panel.add(lblNewLabel_2_1_1, "cell 0 1");

		JLabel lblNewLabel_3 = new JLabel("Digite seu nome de usuario ou endereço de e-mail");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		panel.add(lblNewLabel_3, "cell 0 4 4 1,growx,aligny top");

		txtLogin = new JTextField();
		txtLogin.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				{
					FazLogin();
				}
			}
		});
		txtLogin.setBorder(new RoundedBorder(Color.black, 10)); // Altere a cor e o raio conforme necessário
		panel.add(txtLogin, "cell 0 5 7 1,grow");
		txtLogin.setColumns(10);

		JLabel lblNewLabel_3_1 = new JLabel("Digite sua senha");
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		panel.add(lblNewLabel_3_1, "cell 0 7,growx,aligny top");

		passwordField = new JPasswordField(20);
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER)
				{
					FazLogin();
				}
			}
		});
		passwordField.setBorder(new RoundedBorder(Color.black, 10));
		passwordField.setToolTipText("Digite sua senha");
		passwordField.setColumns(10);
		passwordField.setEchoChar('*');
		panel.add(passwordField, "cell 0 8 7 1,grow");

		JLabel lblNewLabel_1 = new JLabel("Hotel Fazenda");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_1, "cell 0 0,alignx left,aligny top");
		
		ImageIcon Ver = new ImageIcon(Conta.class.getResource("/visao/Ver.png"));
		ImageIcon NaoVer = new ImageIcon(Conta.class.getResource("/visao/NaoVer.png"));
		
		JLabel lblNewLabel_4 = new JLabel("");
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
		panel.add(lblNewLabel_4, "cell 7 8");

		JLabel lblNewLabel_2 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		panel.add(lblNewLabel_2, "cell 6 9,alignx right,aligny top");

		ImageIcon icon = new ImageIcon(getClass().getResource("/visao/Sair.png"));
		DefaultIconButton btnNewButton = new DefaultIconButton("Entrar", icon);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FazLogin();

			}
		});

		panel.add(btnNewButton, "cell 3 10,growx,aligny center");

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
}
