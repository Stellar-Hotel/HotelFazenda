package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.Atividades.AtividadesDAO;
import controle.Quartos.QuartosDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import modelo.Quartos;
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
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaDeQuartos extends JFrame {

	
	private ArrayList<Quartos> ListaQuartos=new ArrayList<Quartos>();
	private DefaultTableModel model1;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				//try {

					//TelaDeQuartos frame = new TelaDeQuartos();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
					//frame.setVisible(true);
			//	} catch (Exception e) {
			//		e.printStackTrace();
		//		}
		//	}
	//	});
	//}
	
	protected void atualizarJTable(int x) {
		DefaultTableModel model1 = (new DefaultTableModel(new Object[][] {},
				new String[] {"Manutencao", "Preco da Diaria" }));

		model1.setRowCount(0);
		
		QuartosDAO QuartoDAO = QuartosDAO.getConexao();
		ListaQuartos = QuartoDAO.buscarQuartoPorNumero(x);
		
		for (int i = 0; i < ListaQuartos.size(); i++) {

			Quartos p = ListaQuartos.get(i);
			model1.addRow(new Object[] {p.getManutencao(), p.getPrecoDiaria() });
		}
		System.out.println(ListaQuartos.size());

		table.setModel(model1);
	}
	/**
	 * Create the frame.
	 */
	public TelaDeQuartos(int x, Funcionarios Func) {
		
		MasKFormatter Num = null;
		
		try {
			Num = new MasKFormatter("###.##");
			Num.setAllowsInvalid(false);
		}catch(ParseException e) {
			e.printStackTrace();
		}
		
		
		textField_1 = new JFormattedTextField(Num);
		
		
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
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px][211.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome=new Home(Func);
				TelaHome.setVisible(true);
				dispose();
			}
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 5,grow");

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
		lblAtividades.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 2,grow");

		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeAcomodacoes TelaAco=new TelaDeAcomodacoes(Func);
				TelaAco.setVisible(true);
				dispose();
			}
		});
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Quartos.jpg")));
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
		lblServicos.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 4,grow");
		
		JLabel lblNewLabel_15 = new JLabel("Funcionários");
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm=new AdminFuncionarios(Func);
				TelaAdm.setVisible(true);
				dispose();
				
			}
		});
		lblNewLabel_15.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_15, "cell 0 6");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 8,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Avatar.jpg")));
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
		lblNewLabel_5.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[201.00,grow][100px:74.00:150px,grow][:500.00:550px,grow]", "[][322.00,grow,fill]"));
		
		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][grow]"));
		
		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0,grow");
		panel_6.setLayout(new MigLayout("", "[][][100px:74.00,grow][grow]", "[][][][][][grow][][][][][][]"));
		
		JLabel lblNewLabel_7 = new JLabel("Reservar Quarto");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_6.add(lblNewLabel_7, "cell 0 0");
		
		JLabel lblNewLabel_9 = new JLabel("Hóspede");
		panel_6.add(lblNewLabel_9, "cell 0 2,alignx left");
		
		textField = new JTextField();
		panel_6.add(textField, "cell 2 2 2 1,growx");
		textField.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Data");
		panel_6.add(lblNewLabel_10, "cell 0 3,alignx left");
		
		textField_1 = new FormattedTextField();
		panel_6.add(textField_1, "cell 2 3 2 1,grow");
		textField_1.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, "cell 2 8,grow");
		
		JLabel lblNewLabel_13 = new JLabel("Forma de pagamento");
		panel_6.add(lblNewLabel_13, "cell 0 10,alignx left");
		
		JComboBox comboBox = new JComboBox();
		panel_6.add(comboBox, "cell 2 10 2 1,grow");
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 // Obtenha os valores inseridos pelo usuário nos campos de texto e na combobox
		        String manutencao = textField.getText();
		        String precoDiaria = textField_1.getText();
		        

		        // Valide se os campos obrigatórios foram preenchidos
		        if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
		            return;
		        }
		        Float precoDiariaDouble = Float.valueOf(precoDiaria);
		        
		        Quartos novoQuarto = new Quartos();
		        
		        QuartosDAO quartoDAO = QuartosDAO.getConexao();
		        
		        
		        novoQuarto.setManutencao(manutencao);
		        int tipoquarto = x;
				novoQuarto.setTipoQuarto(tipoquarto);
		        int maxpessoas = 0;
				novoQuarto.setMaxPessoas(maxpessoas);
		        String tipocama = "0";
				novoQuarto.setTipoCama(tipocama);
		        Boolean arcondicionado = false;
				novoQuarto.setArCondicionado(arcondicionado);
		        Boolean banheira = false;
				novoQuarto.setBanheira(banheira);
		        Boolean tv = false;
		        novoQuarto.setTV(tv);
		        Boolean frigobar = true;
				novoQuarto.setFrigobar(frigobar);
				novoQuarto.setPrecoDiaria(precoDiariaDouble);
		        
				int sucesso = quartoDAO.inserirQuarto(novoQuarto);
				
				
		        if (sucesso>0) {
		            JOptionPane.showMessageDialog(null, "Quarto cadastrado com sucesso!");
		            // Atualize a tabela de quartos para exibir o novo quarto cadastrado
		            atualizarJTable(x);
		        } else {
		            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o quarto. Por favor, tente novamente.");
		        }
		    }
		});
		
		JButton btnNewButton_1 = new JButton("Alterar");
		btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			        // Verifique se algum quarto está selecionado na tabela
					int selectedRow = table.getSelectedRow();
			        if (selectedRow == -1) {
			            JOptionPane.showMessageDialog(null, "Selecione um quarto para alterar.");
			            return;
			        }
			        
			        
			        
			        if (textField.getText().isEmpty() || textField_1.getText().isEmpty()) {
			            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios.");
			            return;
			        }
			        
			        
			        
			        QuartosDAO quartoDAO = QuartosDAO.getConexao();
			        
			        
			        Quartos quartoAtualizado = new Quartos();
			        
			        quartoAtualizado.setIdQuarto(ListaQuartos.get(selectedRow).getIdQuarto());
			        
			        String manutencao = textField.getText();
			        quartoAtualizado.setManutencao(manutencao);
			       
			        int tipoquarto = x;
			        quartoAtualizado.setTipoQuarto(tipoquarto);
			        
			        int maxpessoas = 0;
			        quartoAtualizado.setMaxPessoas(maxpessoas);
			        
			        String tipocama = "0";
			        quartoAtualizado.setTipoCama(tipocama);
			        
			        Boolean arcondicionado = false;
			        quartoAtualizado.setArCondicionado(arcondicionado);
			        
			        Boolean banheira = false;
			        quartoAtualizado.setBanheira(banheira);
			        
			        Boolean tv = false;
			        quartoAtualizado.setTV(tv);
			        
			        Boolean frigobar = false;
			        quartoAtualizado.setFrigobar(frigobar);
			        
			        String precoDiaria = textField_1.getText();
			        Float precoDiariaDouble = Float.valueOf(precoDiaria);
			        
			        quartoAtualizado.setPrecoDiaria(precoDiariaDouble);
			        
			        boolean sucesso = quartoDAO.atualizarQuarto(quartoAtualizado);

			        // Verifique se a alteração foi realizada com sucesso e exiba uma mensagem ao usuário
			        if (sucesso) {
			            JOptionPane.showMessageDialog(null, "Quarto alterado com sucesso!");
			            // Atualize a tabela de quartos para refletir a alteração
			            atualizarJTable(x);
			        } else {
			            JOptionPane.showMessageDialog(null, "Falha ao alterar o quarto. Por favor, tente novamente.");
			        }
			    }
		});
		panel_6.add(btnNewButton_1, "cell 0 11");
		
		JButton btnNewButton_2 = new JButton("Deletar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QuartosDAO DAO = QuartosDAO.getInstancia();										 										
				int linha = table.getSelectedRow();
				Quartos quarto = new Quartos();
				quarto=ListaQuartos.get(linha);
				DAO.removerQuarto(quarto);
				atualizarJTable(x);
				

			}
		});

		panel_6.add(btnNewButton_2, "cell 1 11");
		panel_6.add(btnNewButton, "cell 2 11,alignx right");
		
		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, "cell 0 1,grow");
		panel_7.setLayout(new MigLayout("", "[][][grow]", "[][][][][][grow]"));
		
		JLabel lblNewLabel_11 = new JLabel("Manutenção");
		panel_7.add(lblNewLabel_11, "cell 0 0");
		
		JComboBox comboBox_1 = new JComboBox();
		panel_7.add(comboBox_1, "cell 2 0,growx");
		
		JLabel lblNewLabel_12 = new JLabel("Reposição");
		panel_7.add(lblNewLabel_12, "cell 0 1");
		
		JComboBox comboBox_2 = new JComboBox();
		panel_7.add(comboBox_2, "cell 2 1,growx");
		
		JLabel lblNewLabel_14 = new JLabel("Observações");
		panel_7.add(lblNewLabel_14, "cell 0 3");
		
		textField_2 = new JTextField();
		panel_7.add(textField_2, "cell 2 3 1 3,grow");
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Quartos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0");
		
		model1 = new DefaultTableModel(new Object[][]{}, new String[]{"Manutencao",  "Preco da Diaria"});
	    
	    	    table = new JTable(model1);
	    	    
	    JScrollPane scrollPane1 = new JScrollPane(table);
	    atualizarJTable(x);
	    Principal.add(scrollPane1, "cell 2 1,grow");
		

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
		lblInstagram.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/twitter.jpg")));
	}
}