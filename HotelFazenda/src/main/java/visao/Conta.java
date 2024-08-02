package visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import utils.DefaultScreen;

public class Conta extends DefaultScreen {

	private static final long serialVersionUID = 1L;
  	private JTextField textUser;
	private JTextField textEm;
	private JTextField textPron;
	private JPasswordField passwordField;
	private JTextField textTel;

	public Conta() {
		super();
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

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
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNewLabel_17 = new JLabel("Alterações");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Principal.add(lblNewLabel_17, "cell 1 0 3 1,alignx left,aligny bottom");

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(235, 235, 235));
		Principal.add(panel_5, "cell 0 1 1 9,grow");
		panel_5.setLayout(new MigLayout("", "[::137px,grow][::137px,grow]",
				"[30px][30px][30px][30px][30px][:30px:90px,grow][30px][30px][15px][30px][30px][::100px,grow]"));

		ImageIcon IC = new ImageIcon(
				"C:\\Users\\Aluno\\Desktop\\HotelFazenda\\HotelFazenda\\src\\main\\java\\visao\\Avatar.jpg");

		JLabel lblNewLabel_18 = new JLabel("Dados do Usuário");
		lblNewLabel_18.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_18, "cell 0 0 2 1");

//		IC.setImage(IC.getImage().getScaledInstance(lblNewLabel_12.getWidth(), lblNewLabel_12.getHeight(), 100));

		JLabel lblNome = new JLabel("Blbla");
		lblNome.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNome, "flowx,cell 0 7 2 1,alignx center");
		lblNome.setText(Func.getNome() + " " + Func.getSobrenome());

		JLabel lblNewLabel_15 = new JLabel("Email:");
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_15, "cell 0 8 2 1,alignx center,aligny top");
		lblNewLabel_15.setText(Func.getUsuario().getLogin());

		JLabel lblNewLabel_16 = new JLabel("Pronomes:");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_16, "cell 0 9,alignx center");
		lblNewLabel_16.setText(Func.getPronomeFunc());

		JLabel lblTel = new JLabel("Telefone::");
		lblTel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblTel, "cell 1 9,alignx center");
		lblTel.setText(Func.getTelefone());

		JLabel lblNewLabel_12 = new JLabel("Setor");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_12, "cell 0 10,alignx center");
		lblNewLabel_12.setText(Func.getSetor());

		JLabel lblNewLabel_13 = new JLabel("Funcao");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		panel_5.add(lblNewLabel_13, "cell 1 10,alignx center");
		lblNewLabel_13.setText(Func.getFuncao());

		JLabel lblNewLabel_19 = new JLabel("");
		lblNewLabel_19.setForeground(new Color(255, 38, 38));
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_19, "cell 10 2 3 1");

		JLabel lblNewLabel_7 = new JLabel("Usuário:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		textUser.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		textUser.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(textUser, "cell 5 2 5 1,growx,aligny center");
		textUser.setColumns(10);
		textUser.setText(Func.getUsuario().getLogin());

		JLabel lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel_20.setForeground(new Color(255, 38, 38));
		Principal.add(lblNewLabel_20, "cell 10 4 3 1");

		JLabel lblNewLabel_9 = new JLabel("Email:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		textEm.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		textEm.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(textEm, "cell 5 4 5 1,growx");
		textEm.setColumns(10);
		textEm.setText(Func.getEmailFunc());

		JLabel lblNewLabel_21 = new JLabel("");
		lblNewLabel_21.setForeground(new Color(255, 38, 38));
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_21, "cell 1 6 3 1,alignx right,aligny center");

		JLabel lblNewLabel_10 = new JLabel("Pronomes:");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		lblTelefone.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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
		btnDescartarMudanas.setHoverColor(Color.RED.darker());

		btnDescartarMudanas.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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

		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
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

		setPrincipalPanel(Principal);
	}
}
