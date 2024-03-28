package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controle.Arredondar.RoundedBorder;
import controle.Usuarios.UsuariosDAO;
import modelo.Usuarios;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaDeCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNome;
	private JTextField textSenha;
	private JTextField textTelefone;
	private JTextField textUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastro frame = new TelaDeCadastro();
					// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public TelaDeCadastro() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[65px,grow][4px][302.00px][96.00][69.00][100.00][19.00][93px][28px,grow][33px][115px,grow][97px]", "[14px][94px][20px][27px][20px][:20px:30px,grow][20px,grow][grow][23px][][][grow][]"));

		JLabel lblNewLabel = new JLabel("Bem-Vindo ao");
		contentPane.add(lblNewLabel, "flowx,cell 0 0");

		JLabel lblNewLabel_7 = new JLabel("Já possui uma conta?");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login chama = new Login();
				chama.setVisible(true);
				dispose();

			}
		});
		contentPane.add(lblNewLabel_7, "cell 11 0");

		JLabel lblNewLabel_8 = new JLabel("Entrar");
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login chama = new Login();
				chama.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_8.setForeground(new Color(117, 187, 68));
		contentPane.add(lblNewLabel_8, "cell 11 1,alignx center,aligny top");

		JLabel lblNewLabel_1 = new JLabel("Inscrever-se");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		contentPane.add(lblNewLabel_1, "cell 0 2,alignx left,growy");

		JLabel lblNewLabel_2 = new JLabel("Digite seu nome de usuário");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 4,growx,aligny top");

		textUser = new JTextField();
		textUser.setBorder(new RoundedBorder(Color.black, 10));
		textUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
			}
		});
		contentPane.add(textUser, "cell 0 5 12 1,growx");
		textUser.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Digite seu nome");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3, "cell 0 6,growx,aligny bottom");
		
				JLabel lblNewLabel_4 = new JLabel("Telefone");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
				contentPane.add(lblNewLabel_4, "cell 5 6,aligny bottom");

		textNome = new JTextField();
		textNome.setBorder(new RoundedBorder(Color.black, 10));
		contentPane.add(textNome, "cell 0 7 4 1,growx,aligny top");
		textNome.setColumns(10);

		textTelefone = new JTextField();
		textTelefone.setBorder(new RoundedBorder(Color.black, 10));
		contentPane.add(textTelefone, "cell 5 7 7 1,growx,aligny top");
		textTelefone.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Digite sua senha");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_5, "cell 0 8,growx,aligny top");

		textSenha = new JTextField();
		textSenha.setBorder(new RoundedBorder(Color.black, 10));
		contentPane.add(textSenha, "cell 0 9 12 1,growx,aligny top");
		textSenha.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Hotel Fazenda");
		lblNewLabel_6.setForeground(new Color(117, 187, 68));
		contentPane.add(lblNewLabel_6, "cell 0 0");
								
										JButton btnCadastrar = new JButton("Cadastrar");
										btnCadastrar.setBorder(new RoundedBorder(Color.black, 10));
										btnCadastrar.setBackground(new Color(117, 187, 68));
										btnCadastrar.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												String Senha = textSenha.getText();
												String Login = textUser.getText();

												if ((Senha.isEmpty() || (Login.isEmpty()))) {
													JOptionPane.showMessageDialog(null, "Usuário ou senha não inserido");
													// chama tela de login
												} else {
													Usuarios User = new Usuarios();
													UsuariosDAO DAO = UsuariosDAO.getInstancia();
													User.setSenha(Senha);

													User.setLogin(Login);

													int id = DAO.inserirUsuario(User);

													if (id > 0) {
														JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
														Login tela = new Login();
														tela.setVisible(true);
														dispose();

													}
												}
											}
										});
										contentPane.add(btnCadastrar, "cell 4 11,alignx center");
	}
}