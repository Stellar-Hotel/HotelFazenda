package visao;

import java.awt.Color;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Hospede.HospedeDAO;
import modelo.Funcionarios;
import modelo.Hospedes;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class TelaDeHospedes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	private JTable table;
	private JTextField textNome;
	private JTextField textSobrenome;
	private JTextField textNascimento;
	private JTextField textCPF;
	private JTextField textNacionalidade;
	private JTextField textPronome;
	private JTextField textEmail;
	private DefaultTableModel Model;
	private ArrayList <Hospedes> listahospedes = new ArrayList<Hospedes>();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					TelaDeHospedes frame = new TelaDeHospedes();
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
	public TelaDeHospedes(Funcionarios Func) {
		
		MaskFormatter Data = null;
		MaskFormatter Num = null;
		MaskFormatter formatter = null;
		
	
		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Num=new MaskFormatter("##");
			Num.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	    formatter = new MaskFormatter("UUUUUUUUUU"); // 'U' accepts uppercase and lowercase letters
	    formatter.setPlaceholderCharacter('_');
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]", "[40px:49.00px:40px][469.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px,baseline][40px][211.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home(Func);
				TelaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaHome.setVisible(true);
				dispose();
			}
			
		});
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");
		
				JLabel lblAtividades = new JLabel("Atividades");
				lblAtividades.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TelaAtividades TelaAtiv=new TelaAtividades(Func);
						TelaAtiv.setExtendedState(JFrame.MAXIMIZED_BOTH);
						TelaAtiv.setVisible(true);
						dispose();
					}
				});
				lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblAtividades.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Atividades.jpg")));
				BarraLateral.add(lblAtividades, "cell 0 2,grow");
		
				JLabel lblQuartos = new JLabel("Quartos");
				lblQuartos.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						TelaDeAcomodacoes TelaDeAcomodacoes=new TelaDeAcomodacoes(Func);
						TelaDeAcomodacoes.setExtendedState(JFrame.MAXIMIZED_BOTH);
						TelaDeAcomodacoes.setVisible(true);
						dispose();
					}
				});
				lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblQuartos.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Quartos.jpg")));
				BarraLateral.add(lblQuartos, "cell 0 3,grow");
				
						JLabel lblServicos = new JLabel("Serviços");
						lblServicos.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent e) {
								TelaServicos TelaServ=new TelaServicos(Func);
								TelaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);
								TelaServ.setVisible(true);
								dispose();
								
							}
						});
						lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
						lblServicos.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Servicos.jpg")));
						BarraLateral.add(lblServicos, "cell 0 4,grow");
		
				JLabel lblHospede = new JLabel("Hospede");
				lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
				lblHospede.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Hospede.jpg")));
				BarraLateral.add(lblHospede, "cell 0 5,grow");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/funcionarios.png")));
		BarraLateral.add(lblNewLabel_1, "flowx,cell 0 6");
		
		JLabel lblFuncionarios = new JLabel("Funcionarios");
		lblFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm=new AdminFuncionarios(Func);
				TelaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);
				TelaAdm.setVisible(true);
				dispose();
			}
		});
		lblFuncionarios.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblFuncionarios, "cell 0 6");
		
		JLabel lblNewLabel_2_1 = new JLabel("Conta");
		lblNewLabel_2_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta=new Conta(Func);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaConta.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_2_1.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/conta.png")));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblNewLabel_2_1, "cell 0 7");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 9,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNewLabel_2 = new JLabel("Erik Roncaglio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 1 1,aligny bottom");

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
		lblNewLabel_5.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Sair.png")));
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
		lblNewLabel.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/search.png")));

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
		lblNewLabel_8.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[][101.00][123.00][:230.00:230.00][:15:15][:10:10][][][-24.00][grow]", "[][][:50:50,grow][303.00][31.00][grow,fill]"));
		
		JLabel lblNewLabel_9 = new JLabel("Hospede");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 38));
		Principal.add(lblNewLabel_9, "cell 1 1 2 1");
		
		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 1 3 3 1,grow");
		panel_5.setLayout(new MigLayout("", "[:40:40][][56.00][32.00][:30:30][][82.00][44.00][:126.00px:100px]", "[][:15:15][29.00][11.00][:28.00px:100px][11.00][][11][][grow][][grow]"));
		
		JLabel lblNewLabel_7 = new JLabel("Cadastrar hospede");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_7, "cell 3 0 2 1");
		
		JLabel lblNewLabel_10 = new JLabel("Nome");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10, "cell 0 2,alignx left");
        
        textNome = new JTextField();
        textNome.setColumns(10);
        textNome.setBorder(new RoundedBorder(Color.black, 10));
        ((AbstractDocument) textNome.getDocument()).setDocumentFilter(new LetterDocumentFilter());
        
				panel_5.add(textNome, "flowx,cell 1 2 3 1,growx");
				textNome.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Sobrenome");
		lblNewLabel_10_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_1, "cell 4 2,alignx left,aligny center");
		
		textSobrenome = new JTextField();
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textSobrenome.getDocument()).setDocumentFilter(new LetterDocumentFilter());//filtra para somente caracteres

		
				textSobrenome.setColumns(10);
				panel_5.add(textSobrenome, "cell 6 2 3 1,growx");
		
		JLabel lblNewLabel_10_2 = new JLabel("data de nascimento");
		lblNewLabel_10_2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_2, "cell 0 4 2 1,alignx left,aligny center");
		
		textNascimento = new JFormattedTextField(Data);
		textNascimento.setBorder(new RoundedBorder(Color.black, 10));
		
				textNascimento.setColumns(10);
				panel_5.add(textNascimento, "cell 2 4 2 1,growx");
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Documento");
		lblNewLabel_10_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_1_1, "cell 4 4,alignx left,aligny center");
		
		textCPF = new JTextField();
		textCPF.setBorder(new RoundedBorder(Color.black, 10));
		
				textCPF.setColumns(10);
				panel_5.add(textCPF, "cell 6 4 3 1,growx");
		
		JLabel lblNewLabel_10_2_1 = new JLabel("Nacionalidade");
		lblNewLabel_10_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_2_1, "cell 0 6 2 1,alignx left,aligny center");
		
		textNacionalidade = new JTextField();
		textNacionalidade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNacionalidade.getDocument()).setDocumentFilter(new LetterDocumentFilter());//filtra para somente caracteres
		
		textNacionalidade.setColumns(10);
		panel_5.add(textNacionalidade, "cell 2 6 2 1,growx");
		
		JLabel lblNewLabel_10_2_1_1 = new JLabel("Pronome");
		lblNewLabel_10_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_2_1_1, "cell 4 6,alignx left,aligny center");
		
		textPronome = new JTextField();
		textPronome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textPronome.getDocument()).setDocumentFilter(new LetterDocumentFilter());//filtra para somente caracteres

				textPronome.setColumns(10);
				panel_5.add(textPronome, "cell 6 6 3 1,growx");
		
		JLabel lblNewLabel_10_2_1_1_1 = new JLabel("Email");
		lblNewLabel_10_2_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_2_1_1_1, "cell 0 8,alignx left");
		
		textEmail = new JTextField();
		textEmail.setBorder(new RoundedBorder(Color.black, 10));

		textEmail.setColumns(10);
		panel_5.add(textEmail, "cell 1 8 8 1,growx");
		
	
		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 5 3 5 1,grow");

		
		Model = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "Sobrenome", "Data de Nascimento", "Documento", "Nacionalidade", "Pronome", "Email"});

		table = new JTable(Model);
		scrollPane.setViewportView(table);
		atualizarJTable();
		
								
								
								JButton btnCadastrar = new JButton("Cadastrar");
								btnCadastrar.setBackground(new Color(117, 187, 68));
								btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));
								btnCadastrar.addActionListener(new ActionListener() {
								    public void actionPerformed(ActionEvent e) {
								        if((textNome.getText().isEmpty() || (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty() || textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty() || textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))){                    
								        	JOptionPane.showMessageDialog(null, "Erro adicione textos para cadastrar um hospede");
								        	
								        	//TelaErro telaErro = new TelaErro();
								            //telaErro.setVisible(true);
											} else {
								            String Nome = textNome.getText();
								            String Sobrenome = textSobrenome.getText();
								            String Documento = textCPF.getText();
								            String Nacionalidade = textNacionalidade.getText();
								            String Pronome = textPronome.getText();
								            String Email = textEmail.getText();
								            
								            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
								            Date Nascimento = null;
										    dateFormat.setLenient(false);

								            try {
								                Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
								            } catch (ParseException e1) {
								            	JOptionPane.showMessageDialog(null, "Data invalida.");
										        return;
								            }
								            
								            Hospedes hospede = new Hospedes();
								            hospede.setNome(Nome);
								            hospede.setSobrenome(Sobrenome);
								            hospede.setDataNasc(Nascimento);
								            hospede.setNacionalidade(Nacionalidade);
								            hospede.setEmail(Email);
								            hospede.setPronome(Pronome);
								            hospede.setDocumento(Documento);
								    
								            HospedeDAO DAO = HospedeDAO.getInstancia();
								            int id = DAO.inserirHospede(hospede);
								            if (id > 0) {
								            	
								                atualizarJTable();
												TelaSucesso c = new TelaSucesso("Hospede Cadastrado!");
												c.setVisible(true);
										
								            }
								        }
								    } 
								});
								
										panel_5.add(btnCadastrar, "cell 1 10,alignx center");
												
												JButton btnExcluir = new JButton("Excluir");
												btnExcluir.setBackground(new Color(117, 187, 68));
												btnExcluir.setBorder(new RoundedBorder(Color.BLACK, 8));
												
														btnExcluir.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																
																//excluir
																HospedeDAO DAO = HospedeDAO.getInstancia();
																Hospedes hosp = new Hospedes();
																
																int linha = table.getSelectedRow();
																if(linha>=0) {
																	hosp = listahospedes.get(linha);
																	DAO.removerHospede(hosp);
																	atualizarJTable();
																	TelaSucesso c = new TelaSucesso("Excluído com sucesso!");
																	c.setVisible(true);
																}else if(linha<=0) {
																	JOptionPane.showMessageDialog(null, "selecione uma linha para excluir");
																}
																
																
															}
														});
														
														JButton btnAtualizar = new JButton("Atualizar");
														btnAtualizar.setBackground(new Color(117, 187, 68));
														btnAtualizar.setBorder(new RoundedBorder(Color.BLACK, 8));
														btnAtualizar.addActionListener(new ActionListener() {
														    public void actionPerformed(ActionEvent e) {
														        if((textNome.getText().isEmpty() || (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty() || textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty() || textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))){                    
														            JOptionPane.showMessageDialog(null, "insira algo");
														        } else {
														            String Nome = textNome.getText();
														            String Sobrenome = textSobrenome.getText();
														            String Documento = textCPF.getText();
														            String Nacionalidade = textNacionalidade.getText();
														            String Pronome = textPronome.getText();
														            String Email = textEmail.getText();
														            
														            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
														            Date Nascimento = null;
																    dateFormat.setLenient(false);

														            try {
														                Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
														            } catch (ParseException e1) {
														            	JOptionPane.showMessageDialog(null, "Data invalida.");
																        return;
														            }
														            
														            Hospedes hospede = new Hospedes();
														            hospede.setNome(Nome);
														            hospede.setSobrenome(Sobrenome);
														            hospede.setDataNasc(Nascimento);
														            hospede.setNacionalidade(Nacionalidade);
														            hospede.setEmail(Email);
														            hospede.setPronome(Pronome);
														            hospede.setDocumento(Documento);
														            
														            HospedeDAO DAO = HospedeDAO.getInstancia();
														            DAO.atualizarHospede(hospede);
														            
														            int linha = table.getSelectedRow();
														            if (linha < 0) {
														                JOptionPane.showMessageDialog(null, "Selecione uma linha");
														            } else if (linha >= 0) {
														                DefaultTableModel Model = (DefaultTableModel) table.getModel();
														                Model.setValueAt(Nome, linha, 0);
														                Model.setValueAt(Sobrenome, linha, 1);
														                Model.setValueAt(Nascimento, linha, 2);
														                Model.setValueAt(Documento, linha, 3);
														                Model.setValueAt(Nacionalidade, linha, 4);
														                Model.setValueAt(Pronome, linha, 5);
														                Model.setValueAt(Email, linha, 6);
														                TelaSucesso c = new TelaSucesso("Atualizado com sucesso!");
																		c.setVisible(true);
																
														            }
														        }
														    }
														});
														
																panel_5.add(btnAtualizar, "cell 4 10 2 1,alignx center");
														btnExcluir.setToolTipText("");
														panel_5.add(btnExcluir, "cell 8 10,alignx center");
		

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
		lblInstagram.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Whatsapp.jpg")));

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
		lblTwitter.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/twitter.jpg")));
	
		atualizarJTable();
		
	}
	protected void atualizarJTable() {
	    DefaultTableModel Model = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "Sobrenome", "Data de Nascimento", "Documento", "Nacionalidade", "Pronome", "Email"});

	    HospedeDAO hospedeDAO = HospedeDAO.getInstancia();
	    listahospedes = hospedeDAO.ListarHospedes();

	    for (int i = 0; i < listahospedes.size(); i++) {
	        Hospedes h = listahospedes.get(i);
	        Model.addRow(new Object[] { h.getNome(), h.getSobrenome(), h.getDataNasc(), h.getDocumento(), h.getNacionalidade(), h.getPronome(), h.getEmail()});
	    }
	    table.setModel(Model);
	}
	class LetterDocumentFilter extends DocumentFilter {
	    @Override
	    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException { //insertString: Este método é chamado quando uma inserção de texto é feita em um documento de texto.


	        if (string != null && string.matches("[a-zA-Z]+")) {
	            super.insertString(fb, offset, string, attr);
	        }
	    }

	    @Override
	    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException { //Este método é chamado quando uma substituição de texto é feita em um documento de texto.
	        if (text != null && text.matches("[a-zA-Z]+")) {
	            super.replace(fb, offset, length, text, attrs);
	        }
	    }

	    @Override
	    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException { //Este método é chamado quando uma remoção de texto é feita em um documento de texto.
	        super.remove(fb, offset, length);
	    }
	}
}


