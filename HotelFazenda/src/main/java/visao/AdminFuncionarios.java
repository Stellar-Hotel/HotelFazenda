package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Atualizavel.Atualizavel;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Usuarios;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultIconButton;
import utils.DefaultModal;
import visao.Atividade.TelaAtividades;
import visao.Reserva.TelaDeAcomodacoes;
import visao.Servico.TelaServicos;

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

	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;

	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

	public AdminFuncionarios() {
		screen();

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

		JPanel Principal = new JPanel();
		setTitle("AdminFuncionários");
		setBackground(new Color(250, 250, 250));
		Principal.setBorder(null);
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[:79.00:60,grow][:50:50,grow][30px,grow][30px][:49.00px:50,grow][-2.00][256.00,grow][253.00,grow]", "[40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px,grow,fill][40px]"));

		JLabel lblNewLabel_1 = new JLabel("Admin Funcionários");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		Principal.add(lblNewLabel_1, "cell 1 0 3 1,alignx left,aligny bottom");

		JLabel lblNewLabel_7 = new JLabel("Login:");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_7, "cell 0 1,alignx trailing");

		txtLogin = new JTextField();
//		txtLogin.setText("Login");
		txtLogin.setColumns(10);
		txtLogin.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtLogin, "cell 1 1,growx");

		JLabel lblNewLabel_9 = new JLabel("Senha:");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_9, "cell 2 1,alignx trailing");

		txtSenha = new JPasswordField();
		txtSenha.setToolTipText("");
//		txtSenha.setText("senha");
		txtSenha.setEchoChar('*');
		txtSenha.setColumns(10);
		txtSenha.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSenha, "cell 3 1 2 1,alignx center");

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
		Principal.add(lblNewLabel_2, "cell 5 1");

		JLabel lblNewLabel_10 = new JLabel("Nome:");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_10, "cell 0 2,alignx trailing");

		textNome = new JTextField();
		textNome.setBorder(new RoundedBorder(Color.black, 10));
//		textNome.setText("nome");
		textNome.setToolTipText("");
		Principal.add(textNome, "cell 1 2 5 1,growx");
		textNome.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 6 1 2 12,grow");

		JLabel lblNewLabel_11 = new JLabel("Sobrenome:");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_11, "cell 0 3,alignx trailing");

		textSobrenome = new JTextField();
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
//		textSobrenome.setText("sobrenome");
		Principal.add(textSobrenome, "cell 1 3 5 1,growx");
		textSobrenome.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Função:");
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_12, "cell 0 4,alignx trailing");

		textFuncao = new JTextField();
		textFuncao.setBorder(new RoundedBorder(Color.black, 10));
//		textFuncao.setText("funcao");
		Principal.add(textFuncao, "cell 1 4,growx");
		textFuncao.setColumns(10);

		textSalario = new JFormattedTextField(Num);
		textSalario.addFocusListener(new FocusAdapter() {
		});

		JLabel lblNewLabel_13 = new JLabel("Salário:");
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_13, "cell 2 4,alignx right");
		textSalario.setBorder(new RoundedBorder(Color.black, 10));
		textSalario.setText("salario");
		Principal.add(textSalario, "cell 3 4 3 1,growx");
		textSalario.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Email:");
		lblNewLabel_14.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_14, "cell 0 5,alignx trailing");

		txtEmail = new JTextField();
		// txtEmail.setText("email");
		txtEmail.setColumns(10);
		txtEmail.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtEmail, "cell 1 5 2 1,growx");

		AdminFuncionarios telaPrincipal = this;

		textNivel = new JFormattedTextField(Num1);
		textNivel.addFocusListener(new FocusAdapter() {

		});

		JLabel lblNewLabel_15 = new JLabel("Nível:");
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_15, "cell 3 5,alignx trailing");
		textNivel.setBorder(new RoundedBorder(Color.black, 10));
		textNivel.setText("nivel");
		Principal.add(textNivel, "cell 4 5 2 1,growx");
		textNivel.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("CPF");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_16, "cell 0 6,alignx trailing");

		textCPF = new JFormattedTextField(MCpf);
		textCPF.setBorder(new RoundedBorder(Color.black, 10));

		Principal.add(textCPF, "cell 1 6,growx");
		textCPF.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Pronomes:");
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_17, "cell 2 6 2 1,alignx right");

		txtPronome = new JFormattedTextField(mPron);
		txtPronome.setText("");
		txtPronome.setColumns(10);
		txtPronome.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtPronome, "cell 4 6 2 1,growx");

		JLabel lblNewLabel_18 = new JLabel("Telefone:");
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_18, "cell 0 7,alignx trailing");

		txtTelefone = new JFormattedTextField(mNum);
		txtTelefone.setText("telefone");
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtTelefone, "cell 1 7 2 1,growx");

		JLabel lblNewLabel_19 = new JLabel("Setor:");
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		Principal.add(lblNewLabel_19, "cell 3 7,alignx trailing");

		txtSetor = new JTextField();
//		txtSetor.setText("setor");
		txtSetor.setColumns(10);
		txtSetor.setBorder(new RoundedBorder(Color.black, 10));
		Principal.add(txtSetor, "cell 4 7 2 1,growx");

		model1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Sobrenome", "Funcao", "CPF", "Salario", "Ações" }));
		table = new CustomTable(model1);
		scrollPane.setViewportView(table);
		DefaultIconButton btnCadastrarNovo = new DefaultIconButton("Cadastrar Novo");
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
		btnCadastrarNovo.setBorder(new RoundedBorder(Color.black, 10));
		btnCadastrarNovo.setBackground(new Color(117, 187, 68));
		Principal.add(btnCadastrarNovo, "cell 0 10 2 1,alignx right");
		DefaultIconButton btnDeletarSelecionado = new DefaultIconButton("Deletar Selecionado");
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

		DefaultIconButton btnAtualizarSelecionado = new DefaultIconButton("Atualizar Selecionado");
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
		Principal.add(btnAtualizarSelecionado, "cell 2 10,alignx center");
		btnDeletarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
		btnDeletarSelecionado.setBackground(new Color(117, 187, 68));
		Principal.add(btnDeletarSelecionado, "cell 3 10 3 1,alignx left");
		
		DefaultIconButton dfltcnbtnLimparCampos = new DefaultIconButton("Atualizar Selecionado");
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
		dfltcnbtnLimparCampos.setText("Limpar campos");
		dfltcnbtnLimparCampos.setBorder(new RoundedBorder(Color.black, 10));
		dfltcnbtnLimparCampos.setBackground(new Color(117, 187, 68));
		Principal.add(dfltcnbtnLimparCampos, "cell 2 11,alignx center");

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

		atualizarJTable();
	}

	protected void atualizarJTable() {
		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {
				int linhaSelecionada = table.getSelectedRow();

			}

		};

		DefaultTableModel modelo1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Sobrenome", "Funcao", "CPF", "Salario", "Ações" }));

		FuncionariosDAO funcDAO = FuncionariosDAO.getConexao();
		Lista = funcDAO.ListarFuncionarios();

		for (int i = 0; i < Lista.size(); i++) {

			Funcionarios p = Lista.get(i);
			modelo1.addRow(
					new Object[] { p.getNome(), p.getSobrenome(), p.getFuncao(), p.getCPF(), "R$ " + p.getSalario() });
		}

		table.setModel(modelo1);

		TableActionCellRender cellRenderer = new TableActionCellRender(true, true); // Inicialmente nenhuma linha
																					// selecionada
		table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);

		// Adicionar um MouseListener à tabela para atualizar a linha selecionada
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				if (row >= 0) {
					cellRenderer.setSelectedRow(row);
					table.repaint(); // Repaint to apply the new row color
				}
			}
		});

		table.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event, true, true));
		table.setRowHeight(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(135);

	}

	@Override
	public void atualizarTabela() {
		// TODO Auto-generated method stub
		atualizarJTable();
	}

	public void screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("insets 0, gap 0", "[200px:1064px:200][grow]", "[73:69px:73,grow,center][560px,grow][52px]"));

		DefaultModal BarraLateral = new DefaultModal();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_10);
		panel_10.setLayout(new MigLayout("", "[93px]", "[32px,grow]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		panel_10.add(lblHome, "cell 0 0,alignx left,aligny center");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home();
				TelaHome.setVisible(true);
				TelaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblHome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Home.jpg")));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[163px,grow,fill]", "[32px,grow,fill]"));

		JLabel lblNewLabel_15 = new JLabel("Funcionários");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_15, "cell 0 0,alignx left,aligny center");
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm = new AdminFuncionarios();
				TelaAdm.setVisible(true);
				TelaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_15.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_5);
		panel_5.setLayout(new MigLayout("", "[123px,grow]", "[32px,grow]"));

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblHospede, "cell 0 0,alignx left,aligny center");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeHospedes Chama = new TelaDeHospedes();
				Chama.setVisible(true);
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblHospede.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHospede.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Hospede.jpg")));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_6);
		panel_6.setLayout(new MigLayout("", "[112px,grow]", "[32px,grow]"));

		JLabel lblNewLabel_19 = new JLabel("Quartos");
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblNewLabel_19, "cell 0 0,alignx left,aligny center");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Quartos2 q2 = new Quartos2();
				q2.setVisible(true);
				q2.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_19.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Quartos.jpg")));
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_7);
		panel_7.setLayout(new MigLayout("", "[120px,grow]", "[32px,grow]"));

		JLabel lblQuartos = new JLabel("Reservas");
		lblQuartos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblQuartos, "cell 0 0,alignx left,aligny center");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeAcomodacoes TelaAco = new TelaDeAcomodacoes();
				TelaAco.setVisible(true);
				TelaAco.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblQuartos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblQuartos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/reserva.png")));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_8);
		panel_8.setLayout(new MigLayout("", "[115px,grow]", "[32px,grow]"));

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_8.add(lblServicos, "cell 0 0,alignx left,aligny center");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaServicos TelaServ = new TelaServicos();
				TelaServ.setVisible(true);
				TelaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblServicos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblServicos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Servicos.jpg")));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_9);
		panel_9.setLayout(new MigLayout("", "[138px,grow]", "[32px,grow]"));

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(lblAtividades, "cell 0 0,alignx left,aligny center");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaAtividades TelaAtiv = new TelaAtividades();
				TelaAtiv.setVisible(true);
				TelaAtiv.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblAtividades.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblAtividades.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Atividades.jpg")));

		JLabel label = new JLabel("");
		BarraLateral.add(label);
		
		JLabel lblNewLabel_3 = new JLabel("");
		BarraLateral.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		BarraLateral.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("");
		BarraLateral.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		BarraLateral.add(label_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel);
		panel.setLayout(new MigLayout("", "[][167.00,grow,center]", "[32.00,grow,center]"));

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
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 0 2 1,alignx center,aligny center");

		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(203, 167, 58));

		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior
				.setLayout(new MigLayout("", "[200:209.00px:200][grow,center][50:40px:50,right]", "[29.00px,center]"));
		ImageIcon logoIcon = new ImageIcon(Quartos2.class.getResource("/visao/logoMedia.png"));

		Image logoImage = logoIcon.getImage().getScaledInstance(190, 55, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		logoIcon = new ImageIcon(logoImage);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_4, "cell 0 0,alignx left,aligny center");
		panel_4.setLayout(new MigLayout("", "[251.00px]", "[15][25]"));

		JLabel lblNewLabel_1 = new JLabel("Bem-Vindo(a),");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNome = new JLabel(Func.getNome());
		lblNome.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNome, "cell 0 1,alignx left,aligny top");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		BarraSuperior.add(panel_11, "flowx,cell 1 0");
		JLabel lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblLogo);
		lblLogo.setIcon(logoIcon);
		panel_11.setBackground(new Color(203, 167, 58));
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_2, "cell 1 0,alignx center,aligny center");
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		BarraSuperior.add(lblNewLabel_2, "cell 2 0,alignx center");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta();
				telaConta.setVisible(true);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});

		ImageIcon contaIcon = new ImageIcon(Quartos2.class.getResource("/visao/contaAppBar.png"));
		Image contaImage = contaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		contaIcon = new ImageIcon(contaImage);
		lblNewLabel_2.setIcon(contaIcon);
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
					Desktop.getDesktop()
							.browse(new URL("https://www.instagram.com/stellar_.hotel?igsh=bDl2dmkwY2MzNHFy").toURI());
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
		lblInstagram.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Facebook.jpg")));

		JLabel lblTwitter = new JLabel("");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URL("https://x.com/Stellar1933323?t=sMKnmdFjz2z29kZNNmOY3g&s=09").toURI());
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
		lblTwitter.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/twitter.jpg")));
	}
}
