package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import controle.Atualizavel.Atualizavel;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import modelo.Servicos;
import modelo.Usuarios;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JFormattedTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminFuncionarios extends JFrame implements Atualizavel {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private JTextField textNome;
	private JTextField textSobrenome;
	private JTextField textFuncao;
	private JTextField textCPF;
	private JTextField textSalario;
	private JTextField textNivel;
	private DefaultTableModel model1;
	private ArrayList<Funcionarios> Lista = new ArrayList<Funcionarios>();
	private JTextField txtEmail;
	private JTextField txtPronome;
	private JTextField txtTelefone;
	private JTextField txtSetor;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					AdminFuncionarios frame = new AdminFuncionarios();
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

//	private MaskFormatter MaskaraCPF() {
//		// TODO Auto-generated method stub
//		MaskFormatter F_Mascara = new MaskFormatter();
//		try{
//			F_Mascara.setMask("###.###.###-##"); //Atribui a mascara
//			F_Mascara.setPlaceholderCharacter('_'); //Caracter para preencimento 
//		}
//		catch (Exception excecao) {
//			excecao.printStackTrace();
//		} 
//		return F_Mascara;
//	}
	/**
	 * Create the frame.
	 */
	public AdminFuncionarios(Funcionarios Func) {
		setTitle("AdminFuncionários");
		setBackground(new Color(250, 250, 250));

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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]",
				"[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBorder(null);
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]",
				"[20px:20px:20px][40px][40px][40px][40px][40px][40px][40px][211.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Home telaInicial = new Home(Func);
				telaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaInicial.setVisible(true);
				dispose();

			}
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeHospedes Chama = new TelaDeHospedes(Func);
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);
				Chama.setVisible(true);
				dispose();
			}
		});
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 5,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaAtividades TelaAtividades = new TelaAtividades(Func);
				TelaAtividades.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaAtividades.setVisible(true);
				dispose();
			}
		});
		lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAtividades.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 2,grow");

		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaDeAcomodacoes TelaDeAcomodacoes = new TelaDeAcomodacoes(Func);
				TelaDeAcomodacoes.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaDeAcomodacoes.setVisible(true);
				dispose();

			}
		});
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 3,grow");

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				TelaServicos telaServico = new TelaServicos(Func);
				telaServico.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaServico.setVisible(true);
				dispose();

			}
		});
		lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblServicos.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 4,grow");

		JLabel lblFuncionrios = new JLabel("Funcionários");
		lblFuncionrios.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/funcionarios.png")));
		lblFuncionrios.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblFuncionrios, "cell 0 6");

		JLabel lblConta = new JLabel("Conta");
		lblConta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta(Func);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaConta.setVisible(true);
				dispose();
			}
		});
		lblConta.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/conta.png")));
		lblConta.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblConta, "cell 0 7");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 9,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNome = new JLabel("Erik Roncaglio");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNome, "cell 1 1,aligny bottom");
		lblNome.setText(Func.getNome() + " " + Func.getSobrenome());

		JLabel lblNewLabel_3 = new JLabel(Func.getEmailFunc());
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

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
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBorder(null);
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[:60:60,grow][30px,grow][30px][30px,grow][30px,grow][30px][30px,grow][grow][][grow][30px][30px][30px][30px][grow]", "[40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px,grow,fill]"));

		JLabel lblNewLabel_1 = new JLabel("Admin Funcionários");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 1 0 5 1,alignx left,aligny bottom");
		
		JLabel lblNewLabel_7 = new JLabel("Login:");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_7, "cell 0 1,alignx trailing");

		txtLogin = new JTextField();
//		txtLogin.setText("Login");
		txtLogin.setColumns(10);
		txtLogin.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtLogin, "cell 1 1 3 1,growx");
		
		JLabel lblNewLabel_9 = new JLabel("Senha:");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_9, "cell 4 1,alignx trailing");

		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("");
//		txtSenha.setText("senha");
		txtSenha.setEchoChar('*');
		txtSenha.setColumns(10);
		txtSenha.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSenha, "cell 5 1 3 1,growx");

		ImageIcon Ver = new ImageIcon(Conta.class.getResource("/visao/Ver.png"));
		ImageIcon NaoVer = new ImageIcon(Conta.class.getResource("/visao/NaoVer.png"));

		JLabel lblNewLabel_2 = new JLabel("");
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
		Principal.add(lblNewLabel_2, "cell 8 1");
		
		JLabel lblNewLabel_10 = new JLabel("Nome:");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_10, "cell 0 2,alignx trailing");

		textNome = new JTextField();
		textNome.setBorder(new RoundedBorder(Color.black, 10));
//		textNome.setText("nome");
		textNome.setToolTipText("");
		Principal.add(textNome, "cell 1 2 8 1,growx");
		textNome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 9 1 6 11,grow");

		model1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Sobrenome", "Funcao", "CPF", "Salario" }));
		table = new JTable(model1);
		scrollPane.setViewportView(table);
		atualizarJTable();
		
		JLabel lblNewLabel_11 = new JLabel("Sobrenome:");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_11, "cell 0 3,alignx trailing");

		textSobrenome = new JTextField();
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
//		textSobrenome.setText("sobrenome");
		Principal.add(textSobrenome, "cell 1 3 8 1,growx");
		textSobrenome.setColumns(10);
		
		JLabel lblNewLabel_12 = new JLabel("Função:");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_12, "cell 0 4,alignx trailing");

		textFuncao = new JTextField();
		textFuncao.setBorder(new RoundedBorder(Color.black, 10));
//		textFuncao.setText("funcao");
		Principal.add(textFuncao, "cell 1 4 3 1,growx");
		textFuncao.setColumns(10);
				
						textSalario = new JFormattedTextField(Num);
						textSalario.addFocusListener(new FocusAdapter() {
						});
						
						JLabel lblNewLabel_13 = new JLabel("Salário:");
						lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 11));
						Principal.add(lblNewLabel_13, "cell 4 4,alignx right");
						textSalario.setBorder(new RoundedBorder(Color.black, 10));
						textSalario.setText("salario");
						Principal.add(textSalario, "cell 5 4 4 1,growx");
						textSalario.setColumns(10);
				
				JLabel lblNewLabel_14 = new JLabel("Email:");
				lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 11));
				Principal.add(lblNewLabel_14, "cell 0 5,alignx trailing");
		
				txtEmail = new JTextField();
				//		txtEmail.setText("email");
						txtEmail.setColumns(10);
						txtEmail.setBorder(new RoundedBorder(Color.black, 10));
						Principal.add(txtEmail, "cell 1 5 4 1,growx");

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int i = table.getSelectedRow();
					if (i != -1) { // Verifica se alguma linha foi selecionada
						// Recupera os dados da linha selecionada
						String nome = Lista.get(i).getNome();
						String sobrenome = Lista.get(i).getSobrenome();
						String funcao = Lista.get(i).getFuncao();
						String cpf = Lista.get(i).getCPF();
						String salario = String.valueOf(Lista.get(i).getSalario());
						String nivel = String.valueOf(Lista.get(i).getNivelDeAcesso());
						String email = Lista.get(i).getEmailFunc();
						String pronome = Lista.get(i).getPronomeFunc();
						String telefone = Lista.get(i).getTelefone();
						String setor = Lista.get(i).getSetor();
						String login = Lista.get(i).getUsuario().getLogin();
						String senha = Lista.get(i).getUsuario().getSenha();

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
					}
				}
			}
		});

		AdminFuncionarios telaPrincipal = this;
		JButton btnDeletarSelecionado = new JButton("Deletar Selecionado");
		btnDeletarSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcD = new Funcionarios();

				int linha = table.getSelectedRow();
				funcD = Lista.get(linha);

				ConfirmacaoADM telinha = new ConfirmacaoADM(Func, funcD, telaPrincipal, 1);
				telinha.setVisible(true);
				telinha.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

				atualizarJTable();

			}
		});
		JButton btnCadastrarNovo = new JButton("Cadastrar Novo");
		btnCadastrarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionariosDAO funcionariosDAO = FuncionariosDAO.getConexao();
				UsuariosDAO usuariosDAO = UsuariosDAO.getInstancia();

				// Criação do funcionário
				if ((textCPF.getText().isEmpty()) || (textFuncao.getText().isEmpty()) || (textNivel.getText().isEmpty())
						|| (textNome.getText().isEmpty()) || (textSobrenome.getText().isEmpty())
						|| (textSalario.getText().isEmpty()) || (txtEmail.getText().isEmpty())
						|| (txtLogin.getText().isEmpty()) || (txtPronome.getText().isEmpty())
						|| (txtSenha.getText().isEmpty()) || (txtSetor.getText().isEmpty())
						|| (txtTelefone.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Algo está vazio");
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
						JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
					}
					atualizarJTable();
				}
			}
		});
				
						textNivel = new JFormattedTextField(Num1);
						textNivel.addFocusListener(new FocusAdapter() {

						});
						
						JLabel lblNewLabel_15 = new JLabel("Nível:");
						lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 11));
						Principal.add(lblNewLabel_15, "cell 5 5,alignx trailing");
						textNivel.setBorder(new RoundedBorder(Color.black, 10));
						textNivel.setText("nivel");
						Principal.add(textNivel, "cell 6 5 3 1,growx");
						textNivel.setColumns(10);
				
				JLabel lblNewLabel_16 = new JLabel("CPF");
				lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 11));
				Principal.add(lblNewLabel_16, "cell 0 6,alignx trailing");
		
				textCPF = new JFormattedTextField(MCpf);
				textCPF.setBorder(new RoundedBorder(Color.black, 10));
				
						Principal.add(textCPF, "cell 1 6 3 1,growx");
						textCPF.setColumns(10);
		
		JLabel lblNewLabel_17 = new JLabel("Pronomes:");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_17, "cell 4 6 2 1,alignx right");

		txtPronome = new JFormattedTextField(mPron);
		txtPronome.setText("");
		txtPronome.setColumns(10);
		txtPronome.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtPronome, "cell 6 6 3 1,growx");
		
		JLabel lblNewLabel_18 = new JLabel("Telefone:");
		lblNewLabel_18.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_18, "cell 0 7,alignx trailing");

		txtTelefone = new JFormattedTextField(mNum);
		txtTelefone.setText("telefone");
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtTelefone, "cell 1 7 4 1,growx");
		
		JLabel lblNewLabel_19 = new JLabel("Setor:");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		Principal.add(lblNewLabel_19, "cell 5 7,alignx trailing");

		txtSetor = new JTextField();
//		txtSetor.setText("setor");
		txtSetor.setColumns(10);
		txtSetor.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSetor, "cell 6 7 3 1,growx");
		btnCadastrarNovo.setBorder(new RoundedBorder(Color.black, 10));
		btnCadastrarNovo.setBackground(new Color(117, 187, 68));
		Principal.add(btnCadastrarNovo, "cell 2 9 3 1,alignx right");

		JButton btnAtualizarSelecionado = new JButton("Atualizar Selecionado");
		btnAtualizarSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Funcionarios funcA = new Funcionarios();

				if ((textCPF.getText().isEmpty()) || (textFuncao.getText().isEmpty()) || (textNivel.getText().isEmpty())
						|| (textNome.getText().isEmpty()) || (textSobrenome.getText().isEmpty())
						|| (textSalario.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Algo está vazio");
					textCPF.setBorder(new RoundedBorder(Color.RED, 10));

					textFuncao.setBorder(new RoundedBorder(Color.RED, 10));

					textNivel.setBorder(new RoundedBorder(Color.RED, 10));

					textNome.setBorder(new RoundedBorder(Color.RED, 10));

					textSobrenome.setBorder(new RoundedBorder(Color.RED, 10));

					textSalario.setBorder(new RoundedBorder(Color.RED, 10));
				} else {
					int linha = table.getSelectedRow();
					funcA = Lista.get(linha);
					funcA.setCPF(textCPF.getText());
					funcA.setFuncao(textFuncao.getText());
					funcA.setNivelDeAcesso(Integer.valueOf(textNivel.getText()));
					funcA.setNome(textNome.getText());
					funcA.setSobrenome(textSobrenome.getText());
					funcA.setSalario(Float.valueOf(textSalario.getText()));

					ConfirmacaoADM telinha = new ConfirmacaoADM(Func, funcA, telaPrincipal, 2);
					telinha.setVisible(true);
					telinha.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//						Boolean foi=DAO.AtualizarFuncionarios(func);
//						
//						if(foi==true) {
//							JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
//						}

					atualizarJTable();
				}

			}
		});
		btnAtualizarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
		btnAtualizarSelecionado.setBackground(new Color(117, 187, 68));
		Principal.add(btnAtualizarSelecionado, "cell 5 9 4 1,alignx left");
		btnDeletarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
		btnDeletarSelecionado.setBackground(new Color(117, 187, 68));
		Principal.add(btnDeletarSelecionado, "cell 3 10 4 1,alignx right");

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
		lblInstagram.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Whatsapp.jpg")));

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
		lblTwitter.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/twitter.jpg")));
	}

	protected void atualizarJTable() {
		DefaultTableModel modelo1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Sobrenome", "Funcao", "CPF", "Salario" }));

		FuncionariosDAO funcDAO = FuncionariosDAO.getConexao();
		Lista = funcDAO.ListarFuncionarios();

		for (int i = 0; i < Lista.size(); i++) {

			Funcionarios p = Lista.get(i);
			modelo1.addRow(
					new Object[] { p.getNome(), p.getSobrenome(), p.getFuncao(), p.getCPF(), "R$ " + p.getSalario() });
		}

		table.setModel(modelo1);
	}

	@Override
	public void atualizarTabela() {
		// TODO Auto-generated method stub
		atualizarJTable();
	}
}
