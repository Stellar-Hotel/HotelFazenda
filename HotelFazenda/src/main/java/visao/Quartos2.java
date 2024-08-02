package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Quartos.QuartosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultScreen;

public class Quartos2 extends DefaultScreen {

	private ArrayList<Quartos> ListaQuartos;
	private DefaultTableModel model1;
	private static final long serialVersionUID = 1L;
 
	private JTable table;
	private JTextField textCPF;
	private JTextField textChecki;
	private JTextField textChecko;
	private JTextField textTipo;
	private JTextField textSituacao;
	private JTextField textCapacidade;
	private JTextField textManutencao;
	private JTextField textCama;
	private JTextField textFrigobar;
	private JTextField textAr;
	private JTextField textBanheira;
	private JTextField textTv;
	private JTextField textDiaria;

	protected void atualizarJTable() {

		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {

			}

		};

		DefaultTableModel model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "Tipo", "Situação",
				"Capacidade", "Manutenção", "Cama", "Frigobar", "Ar Condicionado", "Banheira", "TV", "Diária" }));

		model1.setRowCount(0);

		QuartosDAO QuartoDAO = QuartosDAO.getConexao();
		ListaQuartos = QuartoDAO.ListarQuartos();

		for (int i = 0; i < ListaQuartos.size(); i++) {

			Quartos p = ListaQuartos.get(i);
			model1.addRow(new Object[] { p.getTipoQuarto(), p.getSituacao(), p.getMaxPessoas(), p.getManutencao(),
					p.getTipoCama(), p.getFrigobar(), p.getArCondicionado(), p.getBanheira(), p.getTV(),
					"R$" + p.getPrecoDiaria() });
		}

		table.setModel(model1);
		TableActionCellRender cellRenderer = new TableActionCellRender(true, true); // Inicialmente nenhuma linha
																					// selecionada
		table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);

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

		table.setRowHeight(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(125);
	}

	/**
	 * Create the frame.
	 */
	public Quartos2() {
		super();
		Funcionarios Func2 = CurrentFunc.getInstance().getLoggedInFuncionario();

		setTitle("Tela de Quartos");

		Quartos quartos = new Quartos();

		ListaQuartos = new ArrayList<Quartos>();

		DecimalFormat formato = new DecimalFormat("#.##");

		Quartos QuartoSelcionado = new Quartos();

		MaskFormatter formatoCpf = null;

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
		Principal.setLayout(new MigLayout("", "[201.00,grow][100px:n,grow][100px:74.00:150px,grow][:500.00:550px,grow]",
				"[][322.00,grow,fill]"));

		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 0 1 2 1,grow");
		panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][grow]"));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0 2 2,grow");
		panel_6.setLayout(new MigLayout("", "[::100px,grow][100px:74.00:150px,grow][::100px,grow]",
				"[][][][][][][][][][][][grow][]"));

		JLabel lblNewLabel_7 = new JLabel("Tipo: ");
		panel_6.add(lblNewLabel_7, "cell 0 1,alignx trailing");

		textCPF = new JTextField();

		textChecki = new JTextField();

		textChecko = new JTextField();

		textTipo = new JTextField();
		textTipo.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textTipo, "cell 1 1 2 1,growx");
		textTipo.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Situação: ");
		panel_6.add(lblNewLabel_9, "cell 0 2,alignx trailing");

		textSituacao = new JTextField();
		textSituacao.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textSituacao, "cell 1 2 2 1,growx");
		textSituacao.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Capacidade: ");
		panel_6.add(lblNewLabel_10, "cell 0 3,alignx trailing");

		textCapacidade = new JTextField();
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCapacidade, "cell 1 3 2 1,growx");
		textCapacidade.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Manutenção: ");
		panel_6.add(lblNewLabel_11, "cell 0 4,alignx trailing");

		textManutencao = new JTextField();
		textManutencao.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textManutencao, "cell 1 4 2 1,growx");
		textManutencao.setColumns(10);

		JLabel lblNewLabel_12 = new JLabel("Cama: ");
		panel_6.add(lblNewLabel_12, "cell 0 5,alignx trailing");

		textCama = new JTextField();
		textCama.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCama, "cell 1 5 2 1,growx");
		textCama.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Frigobar: ");
		panel_6.add(lblNewLabel_13, "cell 0 6,alignx trailing");

		textFrigobar = new JTextField();
		textFrigobar.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textFrigobar, "cell 1 6 2 1,growx");
		textFrigobar.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Ar Condicionado: ");
		panel_6.add(lblNewLabel_14, "cell 0 7,alignx trailing");

		textAr = new JTextField();
		textAr.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textAr, "cell 1 7 2 1,growx");
		textAr.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Banheira: ");
		panel_6.add(lblNewLabel_16, "cell 0 8,alignx trailing");

		textBanheira = new JTextField();
		textBanheira.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textBanheira, "cell 1 8 2 1,growx");
		textBanheira.setColumns(10);

		JLabel lblNewLabel_17 = new JLabel("Televisão: ");
		panel_6.add(lblNewLabel_17, "cell 0 9,alignx trailing");

		textTv = new JTextField();
		textTv.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textTv, "cell 1 9 2 1,growx");
		textTv.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Diária: ");
		panel_6.add(lblNewLabel_18, "cell 0 10,alignx trailing");

		textDiaria = new JTextField();
		textDiaria.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textDiaria, "cell 1 10 2 1,growx");
		textDiaria.setColumns(10);

		JButton btnSalvar = new JButton("Inserir");
		btnSalvar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				q.setArCondicionado(Boolean.valueOf(textAr.getText()));
				q.setBanheira(Boolean.valueOf(textBanheira.getText()));
				q.setFrigobar(Boolean.valueOf(textFrigobar.getText()));
				q.setTV(Boolean.valueOf(textTv.getText()));
				q.setManutencao(textManutencao.getText());
				q.setMaxPessoas(Integer.valueOf(textCapacidade.getText()));
				q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
				q.setSituacao(Integer.valueOf(textSituacao.getText()));
				q.setTipoCama(textCama.getText());
				q.setTipoQuarto(Integer.valueOf(textTipo.getText()));
				dao.inserirQuarto(q);
				atualizarJTable();
			}
		});
		btnSalvar.setForeground(Color.BLACK);
		btnSalvar.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnSalvar.setBorder(new RoundedBorder(Color.black, 10));
		btnSalvar.setBackground(new Color(117, 187, 68));
		panel_6.add(btnSalvar, "cell 0 12,alignx center");

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				int linha = table.getSelectedRow();

				q.setIdQuarto(ListaQuartos.get(linha).getIdQuarto());
				q.setArCondicionado(Boolean.valueOf(textAr.getText()));
				q.setBanheira(Boolean.valueOf(textBanheira.getText()));
				q.setFrigobar(Boolean.valueOf(textFrigobar.getText()));
				q.setManutencao(textManutencao.getText());
				q.setMaxPessoas(Integer.valueOf(textCapacidade.getText()));
				q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
				q.setSituacao(Integer.valueOf(textSituacao.getText()));
				q.setTipoCama(textCama.getText());
				q.setTipoQuarto(Integer.valueOf(textTipo.getText()));
				q.setTV(Boolean.valueOf(textTv.getText()));
				dao.atualizarQuarto(q);
				atualizarJTable();
			}
		});
		btnAtualizar.setBorder(new RoundedBorder(Color.black, 10));
		btnAtualizar.setBackground(new Color(117, 187, 68));
		panel_6.add(btnAtualizar, "cell 1 12,alignx center");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Quartos q = new Quartos();
				QuartosDAO dao = QuartosDAO.getConexao();
				int linha = table.getSelectedRow();

				q.setIdQuarto(ListaQuartos.get(linha).getIdQuarto());
				dao.removerQuarto(q);
				atualizarJTable();
			}
		});
		btnExcluir.setBorder(new RoundedBorder(Color.black, 10));
		btnExcluir.setBackground(new Color(117, 187, 68));
		panel_6.add(btnExcluir, "cell 2 12");

		JLabel lblNewLabel_1 = new JLabel("Quartos");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0");

		model1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Número do quarto", "Situação do quarto", "Ações" });

		table = new CustomTable(model1);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!e.getValueIsAdjusting()) {
					int i = table.getSelectedRow();
					if (i != -1) { // Verifica se alguma linha foi selecionada
						textAr.setText(String.valueOf(ListaQuartos.get(i).getArCondicionado()));
						textBanheira.setText(String.valueOf(ListaQuartos.get(i).getBanheira()));
						textCama.setText(ListaQuartos.get(i).getTipoCama());
						textCapacidade.setText(String.valueOf(ListaQuartos.get(i).getMaxPessoas()));
						textDiaria.setText(String.valueOf(ListaQuartos.get(i).getPrecoDiaria()));
						textFrigobar.setText(String.valueOf(ListaQuartos.get(i).getFrigobar()));
						textManutencao.setText(String.valueOf(ListaQuartos.get(i).getManutencao()));
						textSituacao.setText(String.valueOf(ListaQuartos.get(i).getSituacao()));
						textTipo.setText(String.valueOf(ListaQuartos.get(i).getTipoQuarto()));
						textTv.setText(String.valueOf(ListaQuartos.get(i).getTV()));
					}
				}
			}

		});
		JScrollPane scrollPane1 = new JScrollPane(table);
		atualizarJTable();
		Principal.add(scrollPane1, "cell 3 1,grow");

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

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Whatsapp.jpg")));

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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (!event.getValueIsAdjusting()) {
					int i = table.getSelectedRow();
					if (i != -1) { // Verifica se alguma linha foi selecionada
						// Recupera os dados da linha selecionada
						int IdQuarto = ListaQuartos.get(i).getIdQuarto();
						int MaxPessoas = ListaQuartos.get(i).getMaxPessoas();
						String TipoCama = ListaQuartos.get(i).getTipoCama();
						String Manutencao = ListaQuartos.get(i).getManutencao();
						Integer TipoQuarto = ListaQuartos.get(i).getTipoQuarto();
						Boolean Frigobar = ListaQuartos.get(i).getFrigobar();
						Boolean ArCondicionado = ListaQuartos.get(i).getArCondicionado();
						Boolean Banheira = ListaQuartos.get(i).getBanheira();
						Boolean TV = ListaQuartos.get(i).getTV();
						Float PrecoDiaria = ListaQuartos.get(i).getPrecoDiaria();

						// Preenche os textfields com os dados recuperados
						QuartoSelcionado.setIdQuarto(IdQuarto);
						QuartoSelcionado.setMaxPessoas(MaxPessoas);
						QuartoSelcionado.setTipoCama(TipoCama);
						QuartoSelcionado.setManutencao(Manutencao);
						QuartoSelcionado.setTipoQuarto(TipoQuarto);
						QuartoSelcionado.setFrigobar(Frigobar);
						QuartoSelcionado.setArCondicionado(ArCondicionado);
						QuartoSelcionado.setBanheira(Banheira);
						QuartoSelcionado.setTV(TV);
						QuartoSelcionado.setPrecoDiaria(PrecoDiaria);

					}
				}
			}
		});
		setPrincipalPanel(Principal);
	}
}
