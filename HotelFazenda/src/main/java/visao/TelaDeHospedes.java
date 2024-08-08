package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Hospede.HospedeDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedes;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultIconButton;
import utils.DefaultModal;
import utils.DefaultScreen;
import visao.Atividade.TelaAtividades;
import visao.ModaisDeAvisos.TelaSucesso;
import visao.Reserva.TelaDeAcomodacoes;
import visao.Servico.TelaServicos;

public class TelaDeHospedes extends JFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;

	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
	private JTable table;
	private JTextField textNome;
	private JTextField textSobrenome;
	private JTextField textNascimento;
	private JTextField textCPF;
	private JTextField textNacionalidade;
	private JTextField textPronome;
	private JTextField textEmail;
	private DefaultTableModel Model;
	private ArrayList<Hospedes> listahospedes = new ArrayList<Hospedes>();

	public TelaDeHospedes() {
		screen();

		MaskFormatter Data = null;
		MaskFormatter Num = null;
		MaskFormatter formatter = null, mPron = null;

		try {
			mPron = new MaskFormatter("UUU/UUUU");
			mPron.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Num = new MaskFormatter("##");
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

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[][101.00][123.00][:184.00:230.00][:-38.00:15][:10:10][13.00][grow]",
				"[][][:50:50,grow][303.00][31.00][grow,fill]"));

		JLabel lblNewLabel_9 = new JLabel("Hospede");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 38));
		Principal.add(lblNewLabel_9, "cell 1 1 2 1");

		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 1 3 3 1,grow");
		panel_5.setLayout(new MigLayout("", "[:40:40][][56.00][32.00][:30:30][][82.00][44.00][:126.00px:100px]", "[][:15:15][29.00][11.00][:28.00px:100px][11.00][][11][][grow][][][grow]"));

		JLabel lblNewLabel_7 = new JLabel("Cadastrar hospede");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_5.add(lblNewLabel_7, "cell 3 0 2 1");

		JLabel lblNewLabel_10 = new JLabel("Nome");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10, "cell 0 2,alignx left");

		textNome = new JTextField();
		textNome.setColumns(10);
		textNome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNome.getDocument()).setDocumentFilter(new LetterDocumentFilter());

		panel_5.add(textNome, "flowx,cell 1 2 3 1,growx");
		textNome.setColumns(10);

		JLabel lblNewLabel_10_1 = new JLabel("Sobrenome");
		lblNewLabel_10_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_1, "cell 4 2,alignx left,aligny center");

		textSobrenome = new JTextField();
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textSobrenome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

		textSobrenome.setColumns(10);
		panel_5.add(textSobrenome, "cell 6 2 3 1,growx");

		JLabel lblNewLabel_10_2 = new JLabel("data de nascimento");
		lblNewLabel_10_2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_2, "cell 0 4 2 1,alignx left,aligny center");

		textNascimento = new JFormattedTextField(Data);
		textNascimento.setBorder(new RoundedBorder(Color.black, 10));

		textNascimento.setColumns(10);
		panel_5.add(textNascimento, "cell 2 4 2 1,growx");

		JLabel lblNewLabel_10_1_1 = new JLabel("Documento");
		lblNewLabel_10_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_1_1, "cell 4 4,alignx left,aligny center");

		textCPF = new JTextField();
		textCPF.setBorder(new RoundedBorder(Color.black, 10));

		textCPF.setColumns(10);
		panel_5.add(textCPF, "cell 6 4 3 1,growx");

		JLabel lblNewLabel_10_2_1 = new JLabel("Nacionalidade");
		lblNewLabel_10_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_2_1, "cell 0 6 2 1,alignx left,aligny center");

		textNacionalidade = new JTextField();
		textNacionalidade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNacionalidade.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra
																											// para
																											// somente
																											// caracteres

		textNacionalidade.setColumns(10);
		panel_5.add(textNacionalidade, "cell 2 6 2 1,growx");

		JLabel lblNewLabel_10_2_1_1 = new JLabel("Pronome");
		lblNewLabel_10_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_2_1_1, "cell 4 6,alignx left,aligny center");

		textPronome = new JFormattedTextField(mPron);
		textPronome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textPronome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

		textPronome.setColumns(10);
		panel_5.add(textPronome, "cell 6 6 3 1,growx");

		JLabel lblNewLabel_10_2_1_1_1 = new JLabel("Email");
		lblNewLabel_10_2_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		panel_5.add(lblNewLabel_10_2_1_1_1, "cell 0 8,alignx left");

		textEmail = new JTextField();
		textEmail.setBorder(new RoundedBorder(Color.black, 10));

		textEmail.setColumns(10);
		panel_5.add(textEmail, "cell 1 8 8 1,growx");

		JScrollPane scrollPane = new JScrollPane();
		Principal.add(scrollPane, "cell 7 3,grow");

		DefaultIconButton btnCadastrar = new DefaultIconButton("Cadastrar");
		btnCadastrar.setBackground(new Color(117, 187, 68));
		btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((textNome.getText().isEmpty()
						|| (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty()
								|| textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty()
								|| textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))) {
					JOptionPane.showMessageDialog(null, "Erro adicione textos para cadastrar um hospede");

					// TelaErro telaErro = new TelaErro();
					// telaErro.setVisible(true);
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

		DefaultIconButton btnExcluir = new DefaultIconButton("Excluir");
		btnExcluir.setBackground(new Color(117, 187, 68));
		btnExcluir.setBorder(new RoundedBorder(Color.BLACK, 8));

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// excluir
				HospedeDAO DAO = HospedeDAO.getInstancia();
				Hospedes hosp = new Hospedes();

				int linha = table.getSelectedRow();
				if (linha >= 0) {
					hosp = listahospedes.get(linha);
					DAO.removerHospede(hosp);
					atualizarJTable();
					TelaSucesso c = new TelaSucesso("Excluído com sucesso!");
					c.setVisible(true);
				} else if (linha <= 0) {
					JOptionPane.showMessageDialog(null, "selecione uma linha para excluir");
				}

			}
		});

		DefaultIconButton btnAtualizar = new DefaultIconButton("Atualizar");
		btnAtualizar.setBackground(new Color(117, 187, 68));
		btnAtualizar.setBorder(new RoundedBorder(Color.BLACK, 8));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((textNome.getText().isEmpty()
						|| (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty()
								|| textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty()
								|| textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))) {
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
					int linha = table.getSelectedRow();

					Hospedes hospede = new Hospedes();
					hospede.setNome(Nome);
					hospede.setSobrenome(Sobrenome);
					hospede.setDataNasc(Nascimento);
					hospede.setNacionalidade(Nacionalidade);
					hospede.setEmail(Email);
					hospede.setPronome(Pronome);
					hospede.setDocumento(Documento);
					hospede.setIdHospede(listahospedes.get(linha).getIdHospede());
					HospedeDAO DAO = HospedeDAO.getInstancia();

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
						DAO.atualizarHospede(hospede);
						TelaSucesso c = new TelaSucesso("Atualizado com sucesso!");
						c.setVisible(true);

					}
				}
			}
		});

		panel_5.add(btnAtualizar, "cell 4 10 2 1,alignx center");
		btnExcluir.setToolTipText("");
		panel_5.add(btnExcluir, "cell 8 10,alignx center");
		
		DefaultIconButton dfltcnbtnLimpar_1 = new DefaultIconButton("Limpar");
		dfltcnbtnLimpar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setText("");
				textSobrenome.setText(null);
				textCPF.setText(null);
				textNacionalidade.setText(null);
				textNascimento.setText(null);
				textEmail.setText(null);
				textPronome.setText(null);
			}
		});
		dfltcnbtnLimpar_1.setText("Limpar");
		dfltcnbtnLimpar_1.setBorder(new RoundedBorder(Color.BLACK, 8));
		dfltcnbtnLimpar_1.setBackground(new Color(117, 187, 68));
		panel_5.add(dfltcnbtnLimpar_1, "cell 4 11 2 1,alignx center");
		
		

		Model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Sobrenome", "Data de Nascimento",
				"Documento", "Nacionalidade", "Pronome", "Email", "Acoes" });

		table = new CustomTable(Model);

		scrollPane.setViewportView(table);

		atualizarJTable();
		contentPane.add(Principal, "cell 1 1,grow");

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
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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
		
		DefaultIconButton dfltcnbtnLimpar = new DefaultIconButton("Limpar");
		dfltcnbtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCPF.setText(null);
				textEmail.setText(null);
				textNacionalidade.setText(null);
				textNascimento.setText(null);
				textNome.setText(null);
				textPronome.setText(null);
				textSobrenome.setText(null);
			}
		});
		dfltcnbtnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textCPF.setText(null);
				textEmail.setText(null);
				textNacionalidade.setText(null);
				textNascimento.setText(null);
				textNome.setText(null);
				textPronome.setText(null);
				textSobrenome.setText(null);
			}
		});

		
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
				// Obter o hospede da linha selecionada
				Hospedes hospede = listahospedes.get(row);

				// Excluir o hospede do banco de dados
				HospedeDAO hospedeDAO = HospedeDAO.getInstancia();
				hospedeDAO.removerHospede(hospede);

				// Atualizar a tabela
				atualizarJTable();
			}

		};
		DefaultTableModel Model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Sobrenome",
				"Data de Nascimento", "Documento", "Nacionalidade", "Pronome", "Email", "Ações" });

		HospedeDAO hospedeDAO = HospedeDAO.getInstancia();
		listahospedes = hospedeDAO.ListarHospedes();

		for (int i = 0; i < listahospedes.size(); i++) {
			Hospedes h = listahospedes.get(i);
			Model.addRow(new Object[] { h.getNome(), h.getSobrenome(), h.getDataNasc(), h.getDocumento(),
					h.getNacionalidade(), h.getPronome(), h.getEmail() });
		}
		table.setModel(Model);

		TableActionCellRender cellRenderer = new TableActionCellRender(true, true); // Inicialmente nenhuma linha
																					// selecionada
		table.getColumnModel().getColumn(7).setCellRenderer(cellRenderer);

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

		table.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event, true, true));
		table.setRowHeight(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(155);
	}

	class LetterDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException { // insertString: Este método é chamado quando uma inserção de texto é
												// feita em um documento de texto.

			if (string != null && string.matches("[a-zA-Z]+")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException { // Este método é chamado quando uma substituição de texto é feita em um
												// documento de texto.
			if (text != null && text.matches("[a-zA-Z]+")) {
				super.replace(fb, offset, length, text, attrs);
			}
		}

		@Override
		public void remove(FilterBypass fb, int offset, int length) throws BadLocationException { // Este método é
																									// chamado quando
																									// uma remoção de
																									// texto é feita em
																									// um documento de
																									// texto.
			super.remove(fb, offset, length);
		}
	}
}
