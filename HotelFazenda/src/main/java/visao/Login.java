package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.Usuarios.UsuariosDAO;
import modelo.Usuarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtSenha;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public Login() {

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
 
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[1080px,Grow]", "[720px,Grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[:84px:84px,grow][84px][210.00px,grow][:125px:125px][][119px][143px,grow][115px]", "[:1.00px:14px][:50px:50px][:141.00px:141.00px][3px][38.00px][50.00px][:100px:100px][33.00px][50.00px][:200px:200px][:45px:45px][:45px:45px][:45px:45px,grow]"));
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao ");
		panel.add(lblNewLabel, "flowx,cell 0 0,growx,aligny top");
		
		JLabel lblNewLabel_4 = new JLabel("Não possui uma conta?");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeCadastro chama = new TelaDeCadastro();
				chama.setVisible(true);
				dispose();
				
			}
		});
		panel.add(lblNewLabel_4, "cell 7 0,alignx left,aligny top");
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Entrar");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		panel.add(lblNewLabel_2_1_1, "cell 0 1");
		
		JLabel lblNewLabel_4_1 = new JLabel("Inscrever-se");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeCadastro chama = new TelaDeCadastro();
				chama.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_4_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_4_1, "cell 7 1,alignx left,aligny top");
		
		JLabel lblNewLabel_3 = new JLabel("Digite seu nome de usuario ou endereço de e-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3, "cell 0 4 4 1,growx,aligny top");
		
		txtLogin = new JTextField();
		txtLogin.setToolTipText("Digite seu usuario");
		txtLogin.setText("\n");
		panel.add(txtLogin, "cell 0 5 8 1,grow");
		txtLogin.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Digite sua senha");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3_1, "cell 0 7,growx,aligny top");
		
		txtSenha = new JTextField();
		txtSenha.setToolTipText("Digite sua senha");
		txtSenha.setColumns(10);
		panel.add(txtSenha, "cell 0 8 8 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Hotel Fazenda");
		lblNewLabel_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_1, "cell 0 0,alignx left,aligny top");
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios User=new Usuarios();
				UsuariosDAO DAO=UsuariosDAO.getInstancia();
				
				User.setLogin(txtLogin.getText());
				User.setSenha(txtSenha.getText());
				
				Usuarios usuarios = new Usuarios();
				String login = null;
				usuarios.setLogin(login);
				String senha = null;
				usuarios.setSenha(senha);
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_2.setForeground(new Color(0, 128, 255));
		panel.add(lblNewLabel_2, "cell 7 9,alignx right,aligny top");
		btnNewButton.setBackground(new Color(117, 187, 68));
		btnNewButton.setForeground(new Color(0, 0, 0));
		panel.add(btnNewButton, "cell 3 10,grow");
	}
}
