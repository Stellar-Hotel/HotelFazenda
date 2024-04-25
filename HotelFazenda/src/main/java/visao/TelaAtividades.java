package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class TelaAtividades extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private DefaultTableModel model1;
	private DefaultTableModel model2;

	private ArrayList<Atividades> ListaAtividades;
	private ArrayList<Atividades> ListaAtividadesinscritas;
	private JTextField textIdade;
	private JTextField textHorario;
	private JTextField textNomeatividade;
	private JTextField TextHorarioFim;
	private JTextField textData;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TelaAtividades frame = new TelaAtividades();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
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
	public TelaAtividades() {
		ListaAtividades = new ArrayList<Atividades>();
		ListaAtividadesinscritas = new ArrayList<Atividades>();

		model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade", "IdadeMinima", "Horario",
				"HorarioFim", "NomeAtividade", "Data", "IDFuncionario" }));

		model2 = (new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade" }));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]", "[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]",
				"[20px:20px:20px][40px][40px][40px][40px][40px][251.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 2,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAtividades.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 3,grow");

		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 4,grow");

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblServicos.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 5,grow");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 7,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNewLabel_2 = new JLabel("Erik Roncaglio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 1 1,aligny bottom");

		JLabel lblNewLabel_3 = new JLabel("erikroncaglio@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[:122.00px:122.00px,grow][][92.00][][:45px:45px,grow][grow][-47.00][36.00,grow][121px]", "[7.00][24.00][:29.00px:50px][][][][][][][][-21.00][][42.00][:-32.00px:10px,grow][-41.00][-25.00][:300px:300px][:90px:90px,grow][:75.00:75]"));
		
				JLabel lblNewLabel_1 = new JLabel("Atividades");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
				Principal.add(lblNewLabel_1, "cell 0 1,alignx center,aligny top");
				
				JScrollPane spTable = new JScrollPane();
				Principal.add(spTable, "cell 4 14 5 4,grow");
				
						table = new JTable(model1);
						spTable.setViewportView(table);
				
				JPanel panel_6 = new JPanel();
				panel_6.setBackground(new Color(255, 255, 255));
				panel_6.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

		                spTable.setViewportView(new JTable(model2));
		                atualizarJTable();
					}
				});
				Principal.add(panel_6, "flowy,cell 1 2");
				
						JLabel lblNewLabel_9 = new JLabel("Inscritos");
						panel_6.add(lblNewLabel_9);
								
								JSeparator separator_1 = new JSeparator();
								separator_1.setForeground(Color.LIGHT_GRAY);
								separator_1.setBackground(Color.LIGHT_GRAY);
								Principal.add(separator_1, "cell 1 6,growx,aligny top");
						
								
								JSeparator separator = new JSeparator();
								separator.setForeground(new Color(192, 192, 192));
								separator.setBackground(new Color(192, 192, 192));
								Principal.add(separator, "cell 0 6,growx,aligny top");
				
				


		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

                spTable.setViewportView(table);
                atualizarJTable();
			}
		});
		Principal.add(panel_5, "flowx,cell 0 2");
		
		
		
				JLabel lblNewLabel_7 = new JLabel("Todas as atividades");
				panel_5.add(lblNewLabel_7);
								
								JPanel panel_7 = new JPanel();
								Principal.add(panel_7, "cell 0 14 3 4,grow");
								panel_7.setLayout(new MigLayout("", "[102px][10px][][][86px,grow]", "[14px,grow][20px,grow][20px,grow][20px,grow][20px,grow][20px,grow][grow][][grow][grow][grow]"));
								
								JLabel lblNewLabel_10 = new JLabel("Cadastrar Atividade");
								panel_7.add(lblNewLabel_10, "cell 0 0 5 1,alignx center,aligny top");
								
								JLabel lblNewLabel_11 = new JLabel("Idade minima");
								panel_7.add(lblNewLabel_11, "cell 0 1,alignx left,aligny center");
								
								textIdade = new JTextField();
								panel_7.add(textIdade, "cell 2 1 3 1,growx,aligny center");
								textIdade.setColumns(10);
								
								JLabel lblNewLabel_12 = new JLabel("Horario");
								panel_7.add(lblNewLabel_12, "cell 0 2,alignx left,aligny center");
								
								textHorario = new JTextField();
								panel_7.add(textHorario, "cell 2 2 3 1,growx,aligny center");
								textHorario.setColumns(10);
								
								JLabel lblNewLabel_11_1 = new JLabel("HorarioFim");
								panel_7.add(lblNewLabel_11_1, "cell 0 3,alignx left,aligny center");
								
								TextHorarioFim = new JTextField();
								TextHorarioFim.setColumns(10);
								panel_7.add(TextHorarioFim, "cell 2 3 3 1,growx,aligny center");
								
								JLabel lblNewLabel_12_1 = new JLabel("Nome Atividade");
								panel_7.add(lblNewLabel_12_1, "cell 0 4,growx,aligny center");
								
								textNomeatividade = new JTextField();
								textNomeatividade.setColumns(10);
								panel_7.add(textNomeatividade, "cell 2 4 3 1,growx,aligny center");
								
								JLabel lblNewLabel_12_1_1 = new JLabel("Data");
								panel_7.add(lblNewLabel_12_1_1, "cell 0 5,growx,aligny center");
								
								textData = new JTextField();
								textData.setColumns(10);
								panel_7.add(textData, "cell 2 5 3 1,growx,aligny center");
								
								//				btnNewButton.setBorder(new RoundedBorder(Color.black, 10));


								JButton btnCadastrar = new JButton("Cadastrar");
								btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));

								btnCadastrar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										 
									
									
									if((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty()) || (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty()) || (textData.getText().isEmpty())) {
										JOptionPane.showMessageDialog(null, "ERRO");
									}else {  
										
											Integer Idade = Integer.valueOf(textIdade.getText());
						                    String Horario = textHorario.getText();
						                    String HorarioFim = TextHorarioFim.getText();
						                    String NomeAtividade = textNomeatividade.getText();
						                    
						                   
						                    
						                    

						                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

						                    Date data = null;

						                    try {
						                        data = new Date ( dateFormat.parse(textData.getText()).getTime());
						                    } catch (ParseException e1) {
						                        e1.printStackTrace();
						                    }
						                    Atividades ativ = new Atividades();

						                    						                    
						                    ativ.setIdadeMinima(Idade);
						                    ativ.setHorario(Horario);
						                    ativ.setHorarioFim(HorarioFim);
						                    ativ.setNomeAtividade(NomeAtividade);
						                    ativ.setData(data);
						                    Funcionarios Func=new Funcionarios();
						                    Func.setIdFuncionario(2);
						                    ativ.setFuncionario(Func);

										
										AtividadesDAO DAO = AtividadesDAO.getInstancia();
										int id = DAO.InserirAtividades(ativ);
										
										if(id>0) {
											JOptionPane.showMessageDialog(null, "Cadastro Efetuado com sucesso");
											atualizarJTable();
										}
										
									}
																													
									}
								});
								btnCadastrar.setForeground(new Color(255, 255, 255));
								btnCadastrar.setBackground(new Color(117, 187, 68));
								panel_7.add(btnCadastrar, "flowx,cell 2 6");
								
								JSeparator separator_2 = new JSeparator();
								separator_2.setForeground(Color.LIGHT_GRAY);
								separator_2.setBackground(Color.LIGHT_GRAY);
								panel_7.add(separator_2, "cell 0 7 5 1,growx");
								
								JLabel lblNewLabel_10_1_1 = new JLabel("Cadastrar Hospede");
								panel_7.add(lblNewLabel_10_1_1, "cell 2 8");
								
								JLabel lblNewLabel_11_2 = new JLabel("Cadastrar Hospede");
								panel_7.add(lblNewLabel_11_2, "cell 0 9");
								
								textField = new JTextField();
								panel_7.add(textField, "cell 2 9 3 1,growx,aligny center");
								
								textField.setColumns(10);
								
								JButton btnNewButton_1 = new JButton("Cadastrar");
								btnNewButton_1.setBorder(new RoundedBorder(Color.BLACK, 8));
								btnNewButton_1.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
								btnNewButton_1.setForeground(new Color(255, 255, 255));
								btnNewButton_1.setBackground(new Color(117, 187, 68));
								panel_7.add(btnNewButton_1, "cell 2 10");
								
								JButton btnAlterar = new JButton("Alterar");
								btnAlterar.setForeground(new Color(255, 255, 255));
								btnAlterar.setBackground(new Color(117, 187, 68));
								btnAlterar.setBorder(new RoundedBorder(Color.black, 8));
								btnAlterar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										

										if((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty()) || (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty()) || (textData.getText().isEmpty())) {
											JOptionPane.showMessageDialog(null, "ERRO");
										}
										else {  
											
												Integer Idade = Integer.valueOf(textIdade.getText());
							                    String Horario = textHorario.getText();
							                    String HorarioFim = TextHorarioFim.getText();
							                    String NomeAtividade = textNomeatividade.getText();
							                    
							                   
							                    
							                    

							                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

							                    Date data = null;

							                    try {
							                        data = new Date ( dateFormat.parse(textData.getText()).getTime());
							                    } catch (ParseException e1) {
							                        e1.printStackTrace();
							                    }
							                    Atividades ativ = new Atividades();

							                    						                    
							                    ativ.setIdadeMinima(Idade);
							                    ativ.setHorario(Horario);
							                    ativ.setHorarioFim(HorarioFim);
							                    ativ.setNomeAtividade(NomeAtividade);
							                    ativ.setData(data);
							                    Funcionarios Func=new Funcionarios();
							                    Func.setIdFuncionario(2);
							                    ativ.setFuncionario(Func);
							                    
							                    AtividadesDAO DAO = AtividadesDAO.getInstancia();
							                    DAO.AtualizarAtividades(ativ);
							                    
							                    int linha = table.getSelectedRow();
							                    
							                    if(linha <0) {
							                    	JOptionPane.showMessageDialog(null, "selecione uma linha");
							                    }else if(linha >= 0) {
							                    	DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							                    	//model1.setValueAt(Idade, linha, 0);
							                    	model1.setValueAt(Idade, linha, 1);
							                    	model1.setValueAt(Horario, linha, 2);
							                    	model1.setValueAt(HorarioFim, linha, 3);
							                    	model1.setValueAt(NomeAtividade, linha, 4);
							                    	model1.setValueAt(data, linha, 5);
							                
							                    }
							                    
							                    	
							                    							                    
									}
									}
								});
								panel_7.add(btnAlterar, "cell 2 6,growx");
								
								JButton btnExcluir = new JButton("Excluir");
								btnExcluir.setForeground(new Color(255, 255, 255));
								btnExcluir.setBackground(new Color(117, 187, 68));
								btnExcluir.setBorder(new RoundedBorder(Color.black, 8));
								btnExcluir.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										 AtividadesDAO DAO = AtividadesDAO.getInstancia();										 										
										int linha = table.getSelectedRow();
										Atividades ativ = new Atividades();
										ativ=ListaAtividades.get(linha);
										DAO.RemoverAtividades(ativ);
										atualizarJTable();
										

									}
								});
								panel_7.add(btnExcluir, "cell 2 6,growx");
								


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
		lblInstagram.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(TelaAtividades.class.getResource("/visao/twitter.jpg")));
		
		atualizarJTable();
		
	}

	protected void atualizarJTable() {
		DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade",
				"IdadeMinima", "Horario", "HorarioFim", "NomeAtividade", "Data"});

		AtividadesDAO AtivDAO = AtividadesDAO.getInstancia();
		ListaAtividades = AtivDAO.ListarAtividades();

		for (int i = 0; i < ListaAtividades.size(); i++) {
			Atividades p = ListaAtividades.get(i);
			modelo1.addRow(new Object[] { p.getIdAtividade(), p.getIdadeMinima(), p.getHorario(), p.getHorarioFim(),
					p.getNomeAtividade(), p.getData()});
		}

		table.setModel(modelo1);
	} 
}
