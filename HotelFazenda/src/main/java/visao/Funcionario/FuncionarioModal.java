package visao.Funcionario;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Usuarios;
import utils.DefaultIconButton;
import visao.ConfirmacaoADM;
import visao.Conta;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;

public class FuncionarioModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Principal;
	private JTextField textNome;
	private JTextField textSobrenome;
	private JTextField textFuncao;
	private JTextField textCPF;
	private JTextField textSalario;
	private JTextField textNivel;
	private JTextField txtEmail;
	private JTextField txtPronome;
	private JTextField txtTelefone;
	private JTextField txtSetor;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private AdminFuncionarios telaFunc;

	public FuncionarioModal(AdminFuncionarios telaFunc, Funcionarios funcionario) {
		this.telaFunc = telaFunc;
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 670);

		Principal = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		Principal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setContentPane(Principal);

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

		MaskFormatter MCpf = null;
		MaskFormatter Num = null;
		MaskFormatter Num1 = null;

		try {
			Num = new MaskFormatter("####.##");
			Num.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 9 indica que aceita apenas números

		try {
			MCpf = new MaskFormatter("###.###.###-##");
			MCpf.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Num1 = new MaskFormatter("#");
			Num1.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Principal.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Login:");
		lblNewLabel_7.setBounds(10, 320, 254, 44);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_7);
		txtLogin = new JTextField();
		txtLogin.setBounds(10, 364, 254, 44);
//		txtLogin.setText("Login");
		txtLogin.setColumns(10);
		txtLogin.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtLogin);

		JLabel lblNewLabel_9 = new JLabel("Senha:");
		lblNewLabel_9.setBounds(294, 145, 254, 44);
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_9);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(294, 189, 254, 44);
		txtSenha.setToolTipText("");
//		txtSenha.setText("senha");
		txtSenha.setEchoChar('*');
		txtSenha.setColumns(10);
		txtSenha.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSenha);

		ImageIcon Ver = new ImageIcon(Conta.class.getResource("/visao/Ver.png"));
		ImageIcon NaoVer = new ImageIcon(Conta.class.getResource("/visao/NaoVer.png"));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(552, 189, 29, 44);
		lblNewLabel_2.setIcon(Ver);
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (lblNewLabel_2.getIcon() == Ver) {
					lblNewLabel_2.setIcon(NaoVer);
					txtSenha.setEchoChar((char) 0);
				} else if (lblNewLabel_2.getIcon() == NaoVer) {
					lblNewLabel_2.setIcon(Ver);
					txtSenha.setEchoChar('*');
				}
			}
		});
		Principal.add(lblNewLabel_2);

		JLabel lblNewLabel_10 = new JLabel("Nome:");
		lblNewLabel_10.setBounds(10, 59, 254, 44);
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_10);

		textNome = new JTextField();
		textNome.setBounds(10, 103, 254, 44);
		textNome.setBorder(new RoundedBorder(Color.black, 10));
//		textNome.setText("nome");
		textNome.setToolTipText("");
		Principal.add(textNome);
		textNome.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Sobrenome:");
		lblNewLabel_11.setBounds(294, 59, 254, 44);
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_11);

		textSobrenome = new JTextField();
		textSobrenome.setBounds(294, 103, 254, 44);
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
//		textSobrenome.setText("sobrenome");
		Principal.add(textSobrenome);
		textSobrenome.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Função:");
		lblNewLabel_12.setBounds(10, 234, 254, 44);
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_12);

		textFuncao = new JTextField();
		textFuncao.setBounds(10, 276, 254, 44);
		textFuncao.setBorder(new RoundedBorder(Color.black, 10));
//		textFuncao.setText("funcao");
		Principal.add(textFuncao);
		textFuncao.setColumns(10);

		textSalario = new JFormattedTextField(Num);
		textSalario.setBounds(294, 275, 254, 44);
		textSalario.addFocusListener(new FocusAdapter() {
		});

		JLabel lblNewLabel_13 = new JLabel("Salário:");
		lblNewLabel_13.setBounds(294, 231, 254, 44);
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_13);
		textSalario.setBorder(new RoundedBorder(Color.black, 10));
		textSalario.setText("salario");
		Principal.add(textSalario);
		textSalario.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Email:");
		lblNewLabel_14.setBounds(294, 319, 254, 44);
		lblNewLabel_14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_14);

		txtEmail = new JTextField();
		txtEmail.setBounds(294, 363, 254, 44);
		// txtEmail.setText("email");
		txtEmail.setColumns(10);
		txtEmail.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtEmail);

		textNivel = new JFormattedTextField(Num1);
		textNivel.setBounds(10, 452, 254, 44);

		JLabel lblNewLabel_15 = new JLabel("Nível:");
		lblNewLabel_15.setBounds(10, 408, 254, 44);
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_15);
		textNivel.setBorder(new RoundedBorder(Color.black, 10));
		textNivel.setText("nivel");
		Principal.add(textNivel);
		textNivel.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("CPF");
		lblNewLabel_16.setBounds(294, 405, 254, 44);
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_16);

		textCPF = new JFormattedTextField(MCpf);
		textCPF.setBounds(294, 449, 254, 44);
		textCPF.setBorder(new RoundedBorder(Color.black, 10));

		Principal.add(textCPF);
		textCPF.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Pronomes:");
		lblNewLabel_17.setBounds(10, 496, 254, 44);
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_17);

		txtPronome = new JFormattedTextField(mPron);
		txtPronome.setBounds(10, 540, 254, 44);
		txtPronome.setText("");
		txtPronome.setColumns(10);
		txtPronome.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtPronome);

		JLabel lblNewLabel_18 = new JLabel("Telefone:");
		lblNewLabel_18.setBounds(10, 145, 254, 44);
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_18);

		txtTelefone = new JFormattedTextField(mNum);
		txtTelefone.setBounds(10, 189, 254, 44);
		txtTelefone.setText("telefone");
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtTelefone);

		JLabel lblNewLabel_19 = new JLabel("Setor:");
		lblNewLabel_19.setBounds(294, 492, 254, 44);
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_19);

		txtSetor = new JTextField();
		txtSetor.setBounds(294, 536, 254, 44);
//		txtSetor.setText("setor");
		txtSetor.setColumns(10);
		txtSetor.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSetor);
		if (funcionario == null) {
			DefaultIconButton btnCadastrarNovo = new DefaultIconButton("Cadastrar");
			btnCadastrarNovo.setBounds(303, 606, 254, 44);

			btnCadastrarNovo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FuncionariosDAO funcionariosDAO = FuncionariosDAO.getConexao();
					UsuariosDAO usuariosDAO = UsuariosDAO.getInstancia();

					// Criação do funcionário
					if ((textCPF.getText().isEmpty()) || (textFuncao.getText().isEmpty())
							|| (textNivel.getText().isEmpty()) || (textNome.getText().isEmpty())
							|| (textSobrenome.getText().isEmpty()) || (textSalario.getText().isEmpty())
							|| (txtEmail.getText().isEmpty()) || (txtLogin.getText().isEmpty())
							|| (txtPronome.getText().isEmpty()) || (txtSenha.getText().isEmpty())
							|| (txtSetor.getText().isEmpty()) || (txtTelefone.getText().isEmpty())) {

						TelaErro telaErro = new TelaErro("Campos vazios");
						telaErro.setVisible(true);

						textCPF.setBorder(new RoundedBorder(Color.RED, 10));

						textFuncao.setBorder(new RoundedBorder(Color.RED, 10));

						textNivel.setBorder(new RoundedBorder(Color.RED, 10));

						textNome.setBorder(new RoundedBorder(Color.RED, 10));

						textSobrenome.setBorder(new RoundedBorder(Color.RED, 10));

						textSalario.setBorder(new RoundedBorder(Color.RED, 10));

						txtEmail.setBorder(new RoundedBorder(Color.RED, 10));

						txtLogin.setBorder(new RoundedBorder(Color.RED, 10));

						txtPronome.setBorder(new RoundedBorder(Color.RED, 10));

						txtSenha.setBorder(new RoundedBorder(Color.RED, 10));

						txtSetor.setBorder(new RoundedBorder(Color.RED, 10));

						txtTelefone.setBorder(new RoundedBorder(Color.RED, 10));
					} else {

						Usuarios user = new Usuarios();
						user.setLogin(txtLogin.getText());
						user.setSenha(txtSenha.getText());
						user.setTipo(true);
						user.setIdUsuario(usuariosDAO.inserirUsuario(user));

						Funcionarios funcionario = new Funcionarios();
						funcionario.setCPF(textCPF.getText());
						funcionario.setFuncao(textFuncao.getText());
						funcionario.setNivelDeAcesso(Integer.valueOf(textNivel.getText()));
						funcionario.setNome(textNome.getText());
						funcionario.setSobrenome(textSobrenome.getText());
						funcionario.setSalario(Float.valueOf(textSalario.getText()));
						funcionario.setPronomeFunc(txtPronome.getText());
						funcionario.setEmailFunc(txtEmail.getText());
						funcionario.setTelefone(txtTelefone.getText());
						funcionario.setSetor(txtSetor.getText());

						funcionario.setUsuario(user); // Associa o usuário ao funcionário

						// Insere o funcionário
						int i = funcionariosDAO.InserirFuncionario(funcionario);

						if (i > 0) {
							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);

							dispose();
						}
						telaFunc.atualizarJTable();
					}
				}
			});

			Principal.add(btnCadastrarNovo);

		} else {

			String nome = funcionario.getNome();
			String sobrenome = funcionario.getSobrenome();
			String funcao = funcionario.getFuncao();
			String cpf = funcionario.getCPF();
			String salario = String.valueOf(funcionario.getSalario());
			String nivel = String.valueOf(funcionario.getNivelDeAcesso());
			String email = funcionario.getEmailFunc();
			String pronome = funcionario.getPronomeFunc();
			String telefone = funcionario.getTelefone();
			String setor = funcionario.getSetor();
			String login = funcionario.getUsuario().getLogin();
			String senha = funcionario.getUsuario().getSenha();

			// Preenche os textfields com os dados recuperados
			textNome.setText(nome);
			textSobrenome.setText(sobrenome);
			textFuncao.setText(funcao);
			textCPF.setText(cpf);
			textSalario.setText(salario);
			textNivel.setText(nivel);
			txtEmail.setText(email);
			txtPronome.setText(pronome);
			txtTelefone.setText(telefone);
			txtSetor.setText(setor);
			txtLogin.setText(login);
			txtSenha.setText(senha);

			DefaultIconButton btnAtualizarSelecionado = new DefaultIconButton("Atualizar");
			btnAtualizarSelecionado.setBounds(303, 606, 254, 44);

			btnAtualizarSelecionado.setBackgroundColor(Color.ORANGE);
			btnAtualizarSelecionado.setHoverColor(Color.ORANGE.darker());
			btnAtualizarSelecionado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Funcionarios funcA = new Funcionarios();

					if ((textCPF.getText().isEmpty()) || (textFuncao.getText().isEmpty())
							|| (textNivel.getText().isEmpty()) || (textNome.getText().isEmpty())
							|| (textSobrenome.getText().isEmpty()) || (textSalario.getText().isEmpty())) {
						TelaErro telaErro = new TelaErro("Campos vazios");
						telaErro.setVisible(true);

						textCPF.setBorder(new RoundedBorder(Color.RED, 10));

						textFuncao.setBorder(new RoundedBorder(Color.RED, 10));

						textNivel.setBorder(new RoundedBorder(Color.RED, 10));

						textNome.setBorder(new RoundedBorder(Color.RED, 10));

						textSobrenome.setBorder(new RoundedBorder(Color.RED, 10));

						textSalario.setBorder(new RoundedBorder(Color.RED, 10));
					} else {

						funcA = funcionario;
						funcA.setCPF(textCPF.getText());
						funcA.setFuncao(textFuncao.getText());
						funcA.setNivelDeAcesso(Integer.valueOf(textNivel.getText()));
						funcA.setNome(textNome.getText());
						funcA.setSobrenome(textSobrenome.getText());
						funcA.setSalario(Float.valueOf(textSalario.getText()));

						ConfirmacaoADM telinha = new ConfirmacaoADM(Func, funcA, telaFunc, 2);
						telinha.setVisible(true);
						telinha.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//						Boolean foi=DAO.AtualizarFuncionarios(func);
//						
//						if(foi==true) {
//							JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
//						}

						telaFunc.atualizarJTable();
						dispose();
					}

				}
			});
			btnAtualizarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
			btnAtualizarSelecionado.setBackground(new Color(117, 187, 68));
			Principal.add(btnAtualizarSelecionado);
		}

		DefaultIconButton dfltcnbtnLimparCampos = new DefaultIconButton(
				new ImageIcon(FuncionarioModal.class.getResource("/visao/rsz_1rsz_eraser256x239.png")), 15);

		dfltcnbtnLimparCampos.setBackgroundColor(new Color(255, 204, 153));
		dfltcnbtnLimparCampos.setHoverColor(new Color(255, 204, 153));
		dfltcnbtnLimparCampos.setBounds(518, 59, 42, 33);
		dfltcnbtnLimparCampos.setSize(30, 30);
		dfltcnbtnLimparCampos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEmail.setText(null);
				txtLogin.setText(null);
				txtPronome.setText(null);
				txtSenha.setText(null);
				txtSetor.setText(null);
				txtTelefone.setText(null);
				textCPF.setText(null);
				textFuncao.setText(null);
				textNivel.setText(null);
				textNome.setText(null);
				textSalario.setText(null);
				textSobrenome.setText(null);
			}
		});

		dfltcnbtnLimparCampos.setBorder(new RoundedBorder(Color.black, 10));
		dfltcnbtnLimparCampos.setBackground(new Color(117, 187, 68));
		Principal.add(dfltcnbtnLimparCampos);

		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackgroundColor(Color.RED);
		btnNewButton.setHoverColor(Color.RED.darker());

		btnNewButton.setBounds(10, 606, 254, 44);

		Principal.add(btnNewButton);

		JLabel lblNewLabel = new JLabel(funcionario == null ? "Cadastrar funcionário" : "Atualizar funcionário");
		lblNewLabel.setBounds(10, 11, 179, 22);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		Principal.add(lblNewLabel);
	}
}
