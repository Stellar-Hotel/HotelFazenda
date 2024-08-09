package visao.Reserva;

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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Combobox.RoundedComboBoxUI;
import controle.Hospedagens.HospedagensDAO;
import controle.Hospede.HospedeDAO;
import controle.Quartos.QuartosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultIconButton;
import utils.DefaultModal;
import visao.Conta;
import visao.Home;
import visao.Login;
import visao.Quartos2;
import visao.Atividade.TelaAtividades;
import visao.Funcionario.AdminFuncionarios;
import visao.Hospede.TelaDeHospedes;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;
import visao.Servico.TelaServicos;

public class TelaDeQuartos extends JFrame {

	private ArrayList<Hospedagens> ListaHospedagens;
	private ArrayList<Quartos> ListaQuartos;
	private DefaultTableModel model1;
	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private CustomTable table;
	private JTextField textCPF;
	private JTextField textChecki;
	private JTextField textChecko;
	private ArrayList<Integer> listaCombobox = new ArrayList<Integer>();

	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;

	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

	protected void atualizarJTable(int tipoQuarto) {

		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {
				HospedagensDAO DAO = HospedagensDAO.getInstancia();

				Hospedagens hg = new Hospedagens();

				hg = ListaHospedagens.get(row);
				DAO.RemoverHospedagem(hg);
				atualizarJTable(tipoQuarto);
				TelaSucesso c = new TelaSucesso("Atividade Exluída!");
				c.setVisible(true);

			}

		};

		QuartosDAO Qdao = QuartosDAO.getConexao();
		ListaQuartos = Qdao.ListarQuartos();

		for (Quartos q : ListaQuartos) {
			if (q.getTipoQuarto() == tipoQuarto && q.getSituacao() == 1) {
				listaCombobox.add(q.getIdQuarto());
			}
		}

		DefaultTableModel model1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Hospede", "Checkin", "Checkout", "Ações" }));

		model1.setRowCount(0);

		HospedagensDAO HospedagenDAO = HospedagensDAO.getInstancia();
		ListaHospedagens = HospedagenDAO.ListarHospedagens();

		for (Hospedagens p : ListaHospedagens) {

			model1.addRow(new Object[] { p.getQuarto().getIdQuarto(), p.getHospede().getNome(), p.getCheckin(),
					p.getCheckout() });
		}

		table.setModel(model1);

		TableActionCellRender cellRenderer = new TableActionCellRender(false, true); // Inicialmente nenhuma linha
		// selecionada
		table.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);

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

		table.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event, false, true));
		table.setRowHeight(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(110);

	}

	public TelaDeQuartos(int x) {
		screen();
		setTitle("Tela de Quartos");

		Quartos quartos = new Quartos();

		ListaHospedagens = new ArrayList<Hospedagens>();

		DecimalFormat formato = new DecimalFormat("#.##");

		Quartos QuartoSelcionado = new Quartos();

		MaskFormatter formatoCpf = null;
		model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Hospede", "Checkin", "Checkout", "Ações" });

		table = new CustomTable(model1);
		try {
			formatoCpf = new MaskFormatter("###.###.###-##");
			formatoCpf.setPlaceholderCharacter('_');
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MaskFormatter Data = null;
		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(
				new MigLayout("", "[201.00,grow][70:74.00:70,grow][:500.00:550px,grow]", "[][322.00,grow,fill]"));

		DefaultModal panel_5 = new DefaultModal();
		Principal.add(panel_5, "cell 0 1,grow");
		panel_5.setLayout(new MigLayout("", "[100px,grow][100px,grow][100px,grow]", "[grow][grow][100px,grow][40px]"));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0 3 1,grow");
		panel_6.setLayout(
				new MigLayout("", "[grow][100px:74.00,grow][grow]", "[][grow][][][][][grow][grow][][][][][]"));

		JLabel lblNewLabel_7 = new JLabel("Reservar Quarto");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		panel_6.add(lblNewLabel_7, "cell 0 0,aligny top");

		JPanel panel_12 = new JPanel();
		panel_6.add(panel_12, "cell 0 1 2 1,grow");

		JLabel lblNewLabel_9 = new JLabel("CPF do hóspede");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_9, "cell 0 2,alignx left,aligny bottom");

		textCPF = new JTextField();
		textCPF = new JFormattedTextField(formatoCpf);
		panel_6.add(textCPF, "cell 1 2 2 1,growx");
		textCPF.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Data checkin");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_10, "cell 0 3,alignx left,aligny bottom");

		textChecki = new JTextField();
		textChecki = new JFormattedTextField(Data);
		panel_6.add(textChecki, "cell 1 3 2 1,grow");
		textChecki.setColumns(10);

		JLabel lblNewLabel_21 = new JLabel("Data checkout");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_21, "cell 0 4,alignx left,aligny bottom");

		textChecko = new JTextField();
		textChecko = new JFormattedTextField(Data);
		panel_6.add(textChecko, "cell 1 4 2 1,growx");
		textChecko.setColumns(10);

		JLabel lblNewLabel_19 = new JLabel("Quartos disponíveis");
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_19, "cell 0 5,alignx left");

		atualizarJTable(x);

		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
		for (Integer valor : listaCombobox) {
			model.addElement(valor);

		}
		JComboBox comboBox = new JComboBox<>(model);
		comboBox.setUI(new RoundedComboBoxUI(comboBox));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel_6.add(comboBox, "cell 1 5 2 1,growx");

		JLabel lblNewLabel_13 = new JLabel("Forma de pagamento:");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		panel_6.add(lblNewLabel_13, "cell 0 6,alignx left,aligny bottom");

		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, "cell 0 7 3 4,grow");
		panel_8.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow]"));

		JPanel panel_13 = new JPanel();

		panel_13.setBackground(new Color(255, 255, 255));
		panel_13.setBorder(new LineBorder(Color.GREEN));
		panel_8.add(panel_13, "cell 0 0,grow");
		panel_13.setLayout(new MigLayout("", "[32px,grow]", "[32px,grow]"));

		JLabel lblNewLabel_16 = new JLabel("");

		lblNewLabel_16.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_16.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/nota.png")));
		panel_13.add(lblNewLabel_16, "cell 0 0,alignx center,aligny center");

		JPanel panel_14 = new JPanel();

		panel_14.setBackground(new Color(255, 255, 255));
		panel_14.setBorder(new LineBorder(Color.CYAN));
		panel_8.add(panel_14, "cell 1 0,grow");

		JLabel lblNewLabel_17 = new JLabel("");

		panel_14.setLayout(new MigLayout("", "[48px,grow]", "[48px,grow]"));
		lblNewLabel_17.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/pix.png")));
		panel_14.add(lblNewLabel_17, "cell 0 0,alignx center,aligny center");

		JPanel panel_15 = new JPanel();

		panel_15.setBackground(new Color(255, 255, 255));
		panel_15.setBorder(new LineBorder(Color.RED));
		panel_8.add(panel_15, "cell 2 0,grow");
		panel_15.setLayout(new MigLayout("", "[50px,grow]", "[50px,grow]"));

		JLabel lblNewLabel_18 = new JLabel("");

		lblNewLabel_18.setIcon(new ImageIcon(TelaDeQuartos.class.getResource("/visao/cartao.png")));
		panel_15.add(lblNewLabel_18, "cell 0 0,alignx center,aligny center");

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, "cell 0 1 3 1,alignx left,growy");
		panel_7.setLayout(new MigLayout("", "[grow][][grow][][]", "[grow][][][grow][][grow]"));

		JLabel lblNewLabel_11 = new JLabel("Subtotal:");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_11, "cell 0 0,alignx left,aligny bottom");

		JLabel lblsubtotal = new JLabel("");
		lblsubtotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lblsubtotal, "cell 1 0");

		JPanel panel_11 = new JPanel();
		panel_7.add(panel_11, "cell 2 0 3 3,grow");

		JLabel lbldesconto = new JLabel("");
		lbldesconto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lbldesconto, "cell 1 1");

		JLabel lbltotal = new JLabel("");
		lbltotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_7.add(lbltotal, "cell 1 2");

		JLabel lblNewLabel_12 = new JLabel("Desconto:");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_12, "cell 0 1,alignx left,aligny top");

		JLabel lblNewLabel_14 = new JLabel("Total:");
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 19));
		panel_7.add(lblNewLabel_14, "cell 0 2,alignx left,aligny top");

		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeAcomodacoes t = new TelaDeAcomodacoes();
				t.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setHoverColor(Color.RED.darker());
		btnNewButton.setBackgroundColor(Color.RED);
		panel_5.add(btnNewButton, "cell 0 2,growx");
				
						DefaultIconButton btnNewButton_3 = new DefaultIconButton("Efetuar reserva");
						panel_5.add(btnNewButton_3, "cell 1 2,growx");
						btnNewButton_3.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								Object pos = comboBox.getSelectedItem();
								String cpf = textCPF.getText();

								Date checkin = null;
								Date checkout = null;
								Date reservacheckin = null;
								Date reservacheckout = null;
								QuartosDAO qDao = QuartosDAO.getConexao();
								Quartos quarto = qDao.buscarQuartoPorId((Integer) comboBox.getSelectedItem());
								HospedeDAO hospedeDAO = HospedeDAO.getInstancia();
								Hospedes hospede = hospedeDAO.buscarHospedePorCPF(cpf);

								if (textCPF.getText().isEmpty() || textChecki.getText().isEmpty() || textChecko.getText().isEmpty()) {

									TelaErro telaErro = new TelaErro("Campos vazios");
									telaErro.setVisible(true);
									return;
								}

								if (hospede == null) {
									TelaErro telaErro = new TelaErro("Hospede não encontrado");
									telaErro.setVisible(true);
									return;
								}

								try {
									checkin = new Date(dateFormat.parse(textChecki.getText()).getTime());
									checkout = new Date(dateFormat.parse(textChecko.getText()).getTime());

									// Check if the provided check-in and check-out dates are valid
									if (checkin.after(checkout)) {
										// Handle invalid dates (check-in should be before check-out)
										TelaErro erro = new TelaErro("Data de check-in deve ser anterior à data de check-out.");
										erro.setVisible(true);
										return;
									}

									// Loop through existing reservations to check for overlaps
									for (Hospedagens hg : ListaHospedagens) {
										if (hg.getQuarto().getIdQuarto() == quarto.getIdQuarto()) {
											reservacheckin = hg.getCheckin();
											reservacheckout = hg.getCheckout();

											// Check for overlap
											if ((checkin.before(reservacheckout) && checkin.after(reservacheckin))
													|| (checkout.before(reservacheckout) && checkout.after(reservacheckin))
													|| (checkin.equals(reservacheckin) || checkin.equals(reservacheckout)
															|| checkout.equals(reservacheckin) || checkout.equals(reservacheckout))) {
												// Overlap detected
												TelaErro erro = new TelaErro("Já existe uma reserva nas datas selecionadas.");
												erro.setVisible(true);
												return;
											}

										}
									}
								} catch (ParseException e1) {
									e1.printStackTrace();
									TelaErro erro = new TelaErro("ocorreu um erro inesperado");
									erro.setVisible(true);
									return;
								}

								HospedagensDAO hospedagensDAO = HospedagensDAO.getInstancia();
								Hospedagens hospedagem = new Hospedagens();
								hospedagem.setCheckin(checkin);
								hospedagem.setCheckout(checkout);
								hospedagem.setHospde(hospede);
								hospedagem.setQuarto(quarto);
								hospedagensDAO.InserirHospedagem(hospedagem);
								atualizarJTable(x);
								TelaSucesso s = new TelaSucesso("Reserva efetuada com sucesso!");
								s.setVisible(true);

							}

						});
						
								DefaultIconButton dfltcnbtnLimpar = new DefaultIconButton("Atualizar");
								dfltcnbtnLimpar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
									}
								});
								panel_5.add(dfltcnbtnLimpar, "cell 2 2,growx,aligny center");
								dfltcnbtnLimpar.addMouseListener(new MouseAdapter() {
									@Override
									public void mouseClicked(MouseEvent e) {
										textChecki.setText(null);
										textChecko.setText(null);
										textCPF.setText(null);
									}
								});
								dfltcnbtnLimpar.setText("Limpar");
								dfltcnbtnLimpar.setBorder(new RoundedBorder(Color.BLACK, 8));
								dfltcnbtnLimpar.setBackground(new Color(117, 187, 68));

		JLabel lblNewLabel_1 = new JLabel("Quartos");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0");

		JScrollPane scrollPane1 = new JScrollPane(table);
		atualizarJTable(x);
		Principal.add(scrollPane1, "cell 2 1,grow");

		lblNewLabel_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.08;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});

		lblNewLabel_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.05;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});

		panel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.03;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}

			}
		});
		panel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.05;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}

		});
		panel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.08;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}

			}
		});
		lblNewLabel_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Object pos = comboBox.getSelectedItem();
				if (pos == comboBox.getSelectedItem()) {
					double subTotal = 0.0;
					double desconto = 0.0;
					double total = 0.0;

					for (int i = 0; i < ListaQuartos.size(); i++) {
						Quartos quarto = ListaQuartos.get((int) pos);
						subTotal = quarto.getPrecoDiaria() * obterDias();
					}

					desconto = subTotal * 0.03;
					total = subTotal - desconto;

					lblsubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
					lbldesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
					lbltotal.setText("R$ " + String.valueOf(formato.format(total)));
				}
			}
		});
		contentPane.add(Principal, "cell 1 1,grow");

	}

	public Integer obterDias() {
		Date checkin = null;
		Date checkout = null;
		Integer dias = null; // Use Integer ao invés de int para lidar com o retorno nulo

		try {
			// Parse the dates from text fields
			checkin = new Date(dateFormat.parse(textChecki.getText()).getTime());
			checkout = new Date(dateFormat.parse(textChecko.getText()).getTime());

			// Calculate the difference in milliseconds
			long diffInMillis = checkout.getTime() - checkin.getTime();

			// Convert milliseconds to days
			dias = (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

		} catch (ParseException e1) {
			e1.printStackTrace();
			TelaErro erro = new TelaErro("Ocorreu um erro inesperado");
			erro.setVisible(true);
		}

		return dias;
	}

	public void screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("insets 0, gap 0", "[200px:1064px:200][grow]",
				"[73:69px:73,grow,center][560px,grow][52px]"));

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
	}
}
