package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.Atividades.AtividadesDAO;
import controle.Hospede.HospedeDAO;
import modelo.Atividades;
import modelo.Funcionarios;
import modelo.Hospedes;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]", "[40px:49.00px:40px][469.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[20px:20px:20px][40px][40px][40px][40px][40px][40px,baseline][251.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home(Func);
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
				TelaAdm.setVisible(true);
				dispose();
			}
		});
		lblFuncionarios.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		BarraLateral.add(lblFuncionarios, "cell 0 6");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 8,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Avatar.jpg")));
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
		Principal.setLayout(new MigLayout("", "[][101.00][123.00][][][][][][][grow]", "[][][22.00][301.00][]"));
		
		JLabel lblNewLabel_9 = new JLabel("Hospede");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 38));
		Principal.add(lblNewLabel_9, "cell 1 1 2 1");
		
		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 1 3 3 1,grow");
		panel_5.setLayout(new MigLayout("", "[][][grow][]", "[][][][][][][][][][][]"));
		
		JLabel lblNewLabel_7 = new JLabel("Cadastrar hospede");
		panel_5.add(lblNewLabel_7, "cell 1 0");
		
		JLabel lblNewLabel_10 = new JLabel("Nome");
		panel_5.add(lblNewLabel_10, "cell 0 2");
		
		textNome = new JTextField();
		panel_5.add(textNome, "cell 1 2 2 1,growx");
		textNome.setColumns(10);
		
		JLabel lblNewLabel_10_1 = new JLabel("Sobrenome");
		panel_5.add(lblNewLabel_10_1, "cell 0 3");
		
		textSobrenome = new JTextField();
		textSobrenome.setColumns(10);
		panel_5.add(textSobrenome, "cell 1 3 2 1,growx");
		
		JLabel lblNewLabel_10_1_1 = new JLabel("Data Nascimento");
		panel_5.add(lblNewLabel_10_1_1, "cell 0 4");
		
		textNascimento = new JTextField();
		textNascimento.setColumns(10);
		panel_5.add(textNascimento, "cell 1 4 2 1,growx");
		
		JLabel lblNewLabel_10_1_1_1 = new JLabel("CPF");
		panel_5.add(lblNewLabel_10_1_1_1, "cell 0 5");
		
		textCPF = new JTextField();
		textCPF.setColumns(10);
		panel_5.add(textCPF, "cell 1 5 2 1,growx");
		
		JLabel lblNewLabel_10_1_1_1_1 = new JLabel("Nacionalidade");
		panel_5.add(lblNewLabel_10_1_1_1_1, "cell 0 6");
		
		textNacionalidade = new JTextField();
		textNacionalidade.setColumns(10);
		panel_5.add(textNacionalidade, "cell 1 6 2 1,growx");
		
		JLabel lblNewLabel_10_1_1_1_1_1 = new JLabel("Pronome");
		panel_5.add(lblNewLabel_10_1_1_1_1_1, "cell 0 7");
		
		textPronome = new JTextField();
		textPronome.setColumns(10);
		panel_5.add(textPronome, "cell 1 7 2 1,growx");
		
		JLabel lblNewLabel_10_1_1_1_1_1_1 = new JLabel("Email");
		panel_5.add(lblNewLabel_10_1_1_1_1_1_1, "cell 0 8");
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		panel_5.add(textEmail, "cell 1 8 2 1,growx");
		
	
		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 7 3 3 1,grow");

		
		Model = new DefaultTableModel(new Object[][] {}, new String[] {"Nome", "Sobrenome", "Data de Nascimento", "Documento", "Nacionalidade", "Pronome", "Email"});

		table = new JTable(Model);
		scrollPane.setViewportView(table);
		atualizarJTable();
		
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if((textNome.getText().isEmpty() || (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty() || textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty() || textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))){                    
		            JOptionPane.showMessageDialog(null, "textos vazios insira algo para adicionar");
		        } else {
		            String Nome = textNome.getText();
		            String Sobrenome = textSobrenome.getText();
		            String Documento = textCPF.getText();
		            String Nacionalidade = textNacionalidade.getText();
		            String Pronome = textPronome.getText();
		            String Email = textEmail.getText();
		            
		            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		            Date Nascimento = null;
		            try {
		                Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
		            } catch (ParseException e1) {
		                e1.printStackTrace();
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
		                JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso");
		                atualizarJTable();
		            }
		        }
		    } 
		});

		panel_5.add(btnCadastrar, "cell 0 10");
		
		JButton btnAtualizar = new JButton("Atualizar");
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
		            try {
		                Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
		            } catch (ParseException e1) {
		                e1.printStackTrace();
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
		            }
		        }
		    }
		});

		panel_5.add(btnAtualizar, "cell 1 10,growx");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//excluir
				HospedeDAO DAO = HospedeDAO.getInstancia();
				Hospedes hosp = new Hospedes();
				
				int linha = table.getSelectedRow();
				hosp = listahospedes.get(linha);
				DAO.removerHospede(hosp);
				atualizarJTable();
				
				
				
			}
		});
		btnExcluir.setToolTipText("");
		panel_5.add(btnExcluir, "cell 2 10");
		

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
		lblInstagram.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaDeHospedes.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
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

}
