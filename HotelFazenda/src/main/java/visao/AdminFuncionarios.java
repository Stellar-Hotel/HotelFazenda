package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import controle.Funcionarios.FuncionariosDAO;
import controle.Usuarios.UsuariosDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import modelo.Servicos;
import modelo.Usuarios;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminFuncionarios extends JFrame {

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
	private ArrayList<Funcionarios> Lista=new ArrayList<Funcionarios>();

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

	/**
	 * Create the frame.
	 */
	public AdminFuncionarios(Funcionarios Func) {
		
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
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px][251.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Home telaInicial=new Home(Func);
				telaInicial.setVisible(true);
				dispose();
				
			}
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 5,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				TelaAtividades TelaAtividades=new TelaAtividades(Func);
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
				
				TelaDeAcomodacoes TelaDeAcomodacoes=new TelaDeAcomodacoes(Func);
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
				
				TelaServicos telaServico=new TelaServicos(Func);
				
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 8,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNome = new JLabel("Erik Roncaglio");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNome, "cell 1 1,aligny bottom");
		lblNome.setText(Func.getNome()+" "+Func.getSobrenome());

		JLabel lblNewLabel_3 = new JLabel("erikroncaglio@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Login novoLogin=new Login();
				String op=JOptionPane.showInputDialog("Deseja mesmo sair?(S/N)");
				if(op.equals("S")||op.equals("s"))
				{
					novoLogin.setVisible(true);
					dispose();
				}
				
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
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[30px][30px][30px][30px][30px][30px][30px][grow][30px][30px,grow][30px][30px][30px][30px][30px,grow][30px]", "[40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px][40px,grow,fill]"));
		
		textNome = new JTextField();
		textNome.setBorder(new RoundedBorder(Color.black, 10));
		textNome.setText("nome");
		textNome.setToolTipText("");
		Principal.add(textNome, "cell 0 1 9 1,growx");
		textNome.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 9 1 7 11,alignx center,growy");
		
		model1=(new DefaultTableModel(new Object[][] {},new String[] {"Nome","Sobrenome","Funcao","CPF","Salario"}));
		table = new JTable(model1);
		scrollPane.setViewportView(table);
		atualizarJTable();
		
		textSobrenome = new JTextField();
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
		textSobrenome.setText("sobrenome");
		Principal.add(textSobrenome, "cell 0 2 9 1,growx");
		textSobrenome.setColumns(10);
		
		textFuncao = new JTextField();
		textFuncao.setBorder(new RoundedBorder(Color.black, 10));
		textFuncao.setText("funcao");
		Principal.add(textFuncao, "cell 0 3 6 1,growx");
		textFuncao.setColumns(10);
		
		textNivel = new JTextField();
		textNivel.setBorder(new RoundedBorder(Color.black, 10));
		textNivel.setText("nivel");
		Principal.add(textNivel, "cell 6 3 3 1,growx");
		textNivel.setColumns(10);
		
		textCPF = new JTextField();
		textCPF.setBorder(new RoundedBorder(Color.black, 10));
		textCPF.setText("cpf");
		Principal.add(textCPF, "cell 0 4 6 1,growx");
		textCPF.setColumns(10);
		
		textSalario = new JTextField();
		textSalario.setBorder(new RoundedBorder(Color.black, 10));
		textSalario.setText("salario");
		Principal.add(textSalario, "cell 6 4 3 1,growx");
		textSalario.setColumns(10);
		
		JButton btnDeletarSelecionado = new JButton("Deletar Selecionado");
		btnDeletarSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionariosDAO DAO= FuncionariosDAO.getConexao();
				Funcionarios func=new Funcionarios();
				
				int linha = table.getSelectedRow();
				func = Lista.get(linha);
				
				boolean foi=DAO.RemoverFuncionario(func);
				
				if(foi==true) {
					JOptionPane.showMessageDialog(null, "O funcionário selecionado foi deletado com sucesso!!");
				}
				
				atualizarJTable();
				
			}
		});
		JButton btnCadastrarNovo = new JButton("Cadastrar Novo");
		btnCadastrarNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionariosDAO funcionariosDAO = FuncionariosDAO.getConexao();
			    UsuariosDAO usuariosDAO = UsuariosDAO.getInstancia();

			    // Criação e insere oo usuário
			    Usuarios usuario= new Usuarios();
			    usuario.setLogin("a");
			    usuario.setSenha("b");
			    usuario.setTipo(true);

			    // Insere o usuário no banco
				usuario.setIdUsuario(usuariosDAO.inserirUsuario(usuario));

			    // Criação do funcionário
				if((textCPF.getText().isEmpty())||(textFuncao.getText().isEmpty())||(textNivel.getText().isEmpty())||(textNome.getText().isEmpty())
					||(textSobrenome.getText().isEmpty())||(textSalario.getText().isEmpty()))
				{
					JOptionPane.showMessageDialog(null, "Algo está vazio");
					textCPF.setText("CPF");
					textCPF.setBorder(new RoundedBorder(Color.RED, 10));
					
					textFuncao.setText("Funcao");
					textFuncao.setBorder(new RoundedBorder(Color.RED, 10));
					
					textNivel.setText("Nivel de Acesso");
					textNivel.setBorder(new RoundedBorder(Color.RED, 10));

					textNome.setText("Nome");
					textNome.setBorder(new RoundedBorder(Color.RED, 10));
					
					textSobrenome.setText("Sobrenome");
					textSobrenome.setBorder(new RoundedBorder(Color.RED, 10));
					
					textSalario.setText("Salario");
					textSalario.setBorder(new RoundedBorder(Color.RED, 10));
				}else {
					
					
					Funcionarios funcionario = new Funcionarios();
				    funcionario.setCPF(textCPF.getText());
				    funcionario.setFuncao(textFuncao.getText());
				    funcionario.setNivelDeAcesso(Integer.valueOf(textNivel.getText()));
				    funcionario.setNome(textNome.getText());
				    funcionario.setSobrenome(textSobrenome.getText());
				    funcionario.setSalario(Float.valueOf(textSalario.getText()));
				    funcionario.setUsuario(usuario); // Associa o usuário ao funcionário
				 // Insere o funcionário
					int i=funcionariosDAO.InserirFuncionario(funcionario);
					
					if(i>0)
					{
						JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
					}
					atualizarJTable();
				}
			}
		});
		btnCadastrarNovo.setBorder(new RoundedBorder(Color.black, 10));
		btnCadastrarNovo.setBackground(new Color(117, 187, 68));
		Principal.add(btnCadastrarNovo, "cell 2 6 3 1,alignx right");
		
		JButton btnAtualizarSelecionado = new JButton("Atualizar Selecionado");
		btnAtualizarSelecionado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionariosDAO DAO= FuncionariosDAO.getConexao();
				Funcionarios func=new Funcionarios();
				
				if((textCPF.getText().isEmpty())||(textFuncao.getText().isEmpty())||(textNivel.getText().isEmpty())||(textNome.getText().isEmpty())
						||(textSobrenome.getText().isEmpty())||(textSalario.getText().isEmpty()))
					{
						JOptionPane.showMessageDialog(null, "Algo está vazio");
						textCPF.setText("CPF");
						textCPF.setBorder(new RoundedBorder(Color.RED, 10));
						
						textFuncao.setText("Funcao");
						textFuncao.setBorder(new RoundedBorder(Color.RED, 10));
						
						textNivel.setText("Nivel de Acesso");
						textNivel.setBorder(new RoundedBorder(Color.RED, 10));

						textNome.setText("Nome");
						textNome.setBorder(new RoundedBorder(Color.RED, 10));
						
						textSobrenome.setText("Sobrenome");
						textSobrenome.setBorder(new RoundedBorder(Color.RED, 10));
						
						textSalario.setText("Salario");
						textSalario.setBorder(new RoundedBorder(Color.RED, 10));
					}else {
						int linha = table.getSelectedRow();
						func = Lista.get(linha);
						func.setCPF(textCPF.getText());
						func.setFuncao(textFuncao.getText());
						func.setNivelDeAcesso(Integer.valueOf(textNivel.getText()));
						func.setNome(textNome.getText());
						func.setSobrenome(textSobrenome.getText());
						func.setSalario(Float.valueOf(textSalario.getText()));
						
						
						Boolean foi=DAO.AtualizarFuncionarios(func);
						
						if(foi==true) {
							JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
						}
						
						atualizarJTable();
					}
				
				
			}
		});
		btnAtualizarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
		btnAtualizarSelecionado.setBackground(new Color(117, 187, 68));
		Principal.add(btnAtualizarSelecionado, "cell 5 6 4 1,alignx left");
		btnDeletarSelecionado.setBorder(new RoundedBorder(Color.black, 10));
		btnDeletarSelecionado.setBackground(new Color(117, 187, 68));
		Principal.add(btnDeletarSelecionado, "cell 3 7 4 1,alignx right");

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
		panel_1.add(lblInstagram, "cell 0 0");
		lblInstagram.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(AdminFuncionarios.class.getResource("/visao/twitter.jpg")));
	}
	protected void atualizarJTable() {
		DefaultTableModel modelo1 = (new DefaultTableModel(new Object[][] {},new String[] {"Nome","Sobrenome","Funcao","CPF","Salario"}));

		 FuncionariosDAO funcDAO=FuncionariosDAO.getConexao();
		 Lista = funcDAO.ListarFuncionarios();

		 for (int i = 0; i < Lista.size(); i++) {

		 Funcionarios p = Lista.get(i);
		 modelo1.addRow(new Object[] { p.getNome(),p.getSobrenome(),p.getFuncao(),p.getCPF(),"R$ "+p.getSalario() });
		}

		table.setModel(modelo1);
		}
}
