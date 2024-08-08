package visao.Servico;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Hospede.HospedeDAO;
import controle.Servicos.ServicosDAO;
import controle.ServicosConsumidos.ServicosConsumidosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedes;
import modelo.Servicos;
import modelo.ServicosConsumidos;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultIconButton;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;

public class Carrinho extends JFrame {
	DecimalFormat formato = new DecimalFormat("#.##");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model1;
	private JTable table;
	private ArrayList<Servicos> listaServicos;
	private JTextField txtHospede;
	JLabel lblNewLabel_3;

	public Carrinho() {
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);

		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		MaskFormatter formatoCpf = null;

		try {
			formatoCpf = new MaskFormatter("###.###.###-##");
			formatoCpf.setPlaceholderCharacter('_');
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		listaServicos = new ArrayList<Servicos>();

 
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("",
				"[::15][][375px:n,grow][375px:n,grow][::30,grow][::30,grow][331.00,grow][15:n:15,grow]",
				"[10:n][35.00][26.00][29.00][13.00][][449.00,grow][42.00]"));

		JLabel lblNewLabel = new JLabel("Carrinho");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		contentPane.add(lblNewLabel, "cell 1 1 3 1");

		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 2 5 1,growx");

		JLabel lblNewLabel_2 = new JLabel("Itens do Carrinho");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_2, "cell 1 3 3 1");

		JPanel panel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setBackground(new Color(204, 204, 255));
		contentPane.add(panel, "cell 6 2 1 5,grow");
		panel.setLayout(new MigLayout("", "[100px:100px,grow][100:100,grow][100px:100,grow]",
				"[33px][36.00px][70px][34.00px][21.00px][32.00px][46.00px,grow][14px][14px][14px][18.00px][45.00px][21.00px][47.00px]"));

		JLabel lblNewLabel_5 = new JLabel("Pagamento");
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 26));
		panel.add(lblNewLabel_5, "cell 0 0 2 1,alignx left,aligny top");

		JLabel lblNewLabel_6 = new JLabel("Selecione o método de pagamento:");
		lblNewLabel_6.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_6, "cell 0 1 3 1,alignx left,growy");

		JPanel panel_1 = new JPanel();

		panel_1.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
		panel_1.setToolTipText("");
		panel_1.setBackground(new Color(255, 255, 255));
		panel.add(panel_1, "cell 0 2,grow");
		panel_1.setLayout(new MigLayout("", "[95.00px,grow]", "[75.00px,grow]"));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/nota.png")));
		panel_1.add(lblNewLabel_1, "cell 0 0,alignx center,aligny center");

		JLabel lblNewLabel_7 = new JLabel("Dinheiro");
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.add(lblNewLabel_7, "cell 0 3,alignx center,aligny center");

		JPanel panel_2 = new JPanel();

		panel_2.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		panel_2.setBackground(new Color(255, 255, 255));
		panel.add(panel_2, "cell 1 2,grow");
		panel_2.setLayout(new MigLayout("", "[102.00px,grow]", "[87.00px,grow]"));

		JLabel lblNewLabel_15 = new JLabel("");
		lblNewLabel_15.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/pix.png")));
		panel_2.add(lblNewLabel_15, "cell 0 0,alignx center,aligny center");

		JPanel panel_3 = new JPanel();

		panel_3.setBorder(new LineBorder(new Color(128, 0, 64), 2, true));
		panel_3.setBackground(new Color(255, 255, 255));
		panel.add(panel_3, "cell 2 2,grow");
		panel_3.setLayout(new MigLayout("", "[103.00px,grow]", "[88.00px,grow]"));

		JLabel lblNewLabel_16 = new JLabel("");
		lblNewLabel_16.setIcon(new ImageIcon(Carrinho.class.getResource("/visao/cartao.png")));
		panel_3.add(lblNewLabel_16, "cell 0 0,alignx center,aligny center");

		JLabel lblNewLabel_8 = new JLabel("Pix");
		lblNewLabel_8.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.add(lblNewLabel_8, "cell 1 3,alignx center,aligny center");

		JLabel lblNewLabel_9 = new JLabel("Cartão");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.add(lblNewLabel_9, "cell 2 3,alignx center,aligny center");

		JLabel lblNewLabel_10 = new JLabel("Total dos itens:");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_10, "cell 0 6 2 1,alignx left,aligny bottom");

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(204, 204, 255));
		panel.add(panel_5, "cell 0 7 3 1,growx,aligny center");
		panel_5.setLayout(new MigLayout("", "[60.00px][158.00,grow][100px:92.00px:100px]", "[20px]"));

		JLabel lblNewLabel_11 = new JLabel("Subtotal");
		panel_5.add(lblNewLabel_11, "cell 0 0,alignx left,aligny top");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		panel_5.add(separator_1, "cell 1 0,growx,aligny bottom");

		JLabel lblSubtotal = new JLabel("R$");
		panel_5.add(lblSubtotal, "cell 2 0,alignx left,aligny bottom");
		lblSubtotal.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(204, 204, 255));
		panel.add(panel_6, "cell 0 8 3 1,growx,aligny center");
		panel_6.setLayout(new MigLayout("", "[68.00px][152.00px,grow][100px:68.00:100px]", "[20px]"));

		JLabel lblNewLabel_11_1 = new JLabel("Desconto");
		panel_6.add(lblNewLabel_11_1, "cell 0 0,alignx left,aligny center");
		lblNewLabel_11_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(new Color(0, 0, 0));
		panel_6.add(separator_3, "cell 1 0,growx,aligny bottom");

		JLabel lblDesconto = new JLabel("R$");
		panel_6.add(lblDesconto, "cell 2 0,alignx left,aligny bottom");
		lblDesconto.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(204, 204, 255));
		panel.add(panel_7, "cell 0 9 3 1,growx,aligny center");
		panel_7.setLayout(new MigLayout("", "[40.00px][184.00px,grow][100px:n:100px]", "[20px]"));

		JLabel lblNewLabel_11_2 = new JLabel("Taxa");
		panel_7.add(lblNewLabel_11_2, "cell 0 0,alignx left,aligny top");
		lblNewLabel_11_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JSeparator separator_4 = new JSeparator();
		separator_4.setForeground(new Color(0, 0, 0));
		panel_7.add(separator_4, "cell 1 0,growx,aligny bottom");

		JLabel lblTaxa = new JLabel("R$");
		panel_7.add(lblTaxa, "cell 2 0,alignx left,aligny bottom");
		lblTaxa.setFont(new Font("Segoe UI", Font.PLAIN, 12));

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(new Color(0, 0, 0));
		separator_2.setBackground(new Color(0, 0, 0));
		panel.add(separator_2, "cell 0 10 3 1,growx,aligny bottom");

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel.add(lblTotal, "cell 0 11 2 1,grow");

		JLabel lblInformacao = new JLabel("");
		lblInformacao.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		panel.add(lblInformacao, "cell 0 12 3 1,grow");

		JLabel lblNewLabel_4 = new JLabel("CPF do hospede:");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel.add(lblNewLabel_4, "cell 0 4 2 1,alignx left,aligny bottom");

		txtHospede = new JTextField();
		txtHospede.setBackground(new Color(255, 255, 255));
		txtHospede = new JFormattedTextField(formatoCpf);
		panel.add(txtHospede, "cell 0 5 3 1,grow");
		txtHospede.setColumns(10);
		txtHospede.setBorder(new RoundedBorder(Color.black, 10));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(204, 204, 255));
		panel.add(panel_4, "cell 0 13 3 1,grow");
		panel_4.setLayout(new MigLayout("", "[403.00px,grow]", "[89.00px,grow]"));

		DefaultIconButton btnNewButton_3 = new DefaultIconButton("Finalizar compras");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String Documento = txtHospede.getText();
				HospedeDAO hDao = HospedeDAO.getInstancia();
				Hospedes hospede = hDao.buscarHospedePorCPF(Documento);
				if (hospede != null) {
					ServicosDAO dao = ServicosDAO.getInstancia();
					dao.limparServicos();
					TelaSucesso s = new TelaSucesso("Compra efetuada com sucesso!");
					s.setVisible(true);
					dispose();
				} else {

					TelaErro e1 = new TelaErro("Erro! Informe um hospede cadastrado.");
					e1.setVisible(true);
				}

			}
		});
		panel_4.add(btnNewButton_3, "cell 0 0,grow");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBorder(new RoundedBorder(Color.black, 8));
		btnNewButton_3.setBackground(new Color(117, 187, 68));

		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ServicosDAO Sdao = ServicosDAO.getInstancia();
				ServicosConsumidosDAO Cdao = ServicosConsumidosDAO.getInstancia();

				HospedeDAO Hdao = HospedeDAO.getInstancia();

				Hospedes h = Hdao.buscarHospedePorCPF(txtHospede.getText());

				;

				for (int i = 0; i < listaServicos.size(); i++) {
					ServicosConsumidos sc = new ServicosConsumidos();
					Servicos s = listaServicos.get(i);
					sc.setServico(s);
					sc.setHospede(h);

					Cdao.inserirServicoConsumido(sc);
				}

				Sdao.limparServicos();

				TelaServicos s = new TelaServicos();
				s.setVisible(true);
				dispose();

			}
		});
		JScrollPane cTable = new JScrollPane();

		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar as Compras");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBorder(new RoundedBorder(Color.black, 8));
		btnNewButton.setBackground(new Color(117, 187, 68));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				dispose();
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 2 7,growx,aligny center");

		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				double total = 0.00, subTotal = 0.00, desconto = 0.00, taxa = 0.00;

				for (int i = 0; i < listaServicos.size(); i++) {
					Servicos s = listaServicos.get(i);
					subTotal += s.getQuantidade() * s.getPrecoServico();
				}

				desconto = subTotal * 0.12;
				taxa = subTotal * 0.01;

				total = subTotal + taxa - desconto;

				lblSubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
				lblDesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
				lblTaxa.setText("R$ " + String.valueOf(formato.format(taxa)));
				lblTotal.setText("Total: R$ " + String.valueOf(formato.format(total)));
				lblInformacao.setText("*Desconto de 12% para pagamento por pix e 1% de taxa.");
			}
		});

		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double total = 0.00, subTotal = 0.00, desconto = 0.00, taxa = 0.00;

				for (int i = 0; i < listaServicos.size(); i++) {
					Servicos s = listaServicos.get(i);
					subTotal += s.getQuantidade() * s.getPrecoServico();
				}

				desconto = subTotal * 0.1;
				taxa = subTotal * 0.01;

				total = subTotal + taxa - desconto;

				lblSubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
				lblDesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
				lblTaxa.setText("R$ " + String.valueOf(formato.format(taxa)));
				lblTotal.setText("Total: R$ " + String.valueOf(formato.format(total)));
				lblInformacao.setText("*Desconto de 10% para pagamento em dinheiro e 1% de taxa.");

			}
		});
		panel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				double total = 0.00, subTotal = 0.00, desconto = 0.00, taxa = 0.00;

				for (int i = 0; i < listaServicos.size(); i++) {
					Servicos s = listaServicos.get(i);
					subTotal += s.getQuantidade() * s.getPrecoServico();
				}

				desconto = subTotal * 0.08;
				taxa = subTotal * 0.03;

				total = subTotal + taxa - desconto;

				lblSubtotal.setText("R$ " + String.valueOf(formato.format(subTotal)));
				lblDesconto.setText("R$ " + String.valueOf(formato.format(desconto)));
				lblTaxa.setText("R$ " + String.valueOf(formato.format(taxa)));
				lblTotal.setText("Total: R$ " + String.valueOf(formato.format(total)));
				lblInformacao.setText("*Desconto de 8% para pagamento em dinheiro e 3% de taxa.");
			}
		});

		model1 = (new DefaultTableModel(new Object[][] {},
				new String[] { "Produtos", "Preço", "Quantidade", "Sub-Total", "Ações" }));

		contentPane.add(cTable, "cell 2 6 2 1,grow");
		table = new CustomTable(model1);

		cTable.setViewportView(table);
		atualizarJTable();
		lblNewLabel_3 = new JLabel("Você tem " + listaServicos.size() + " itens no carrinho.");

		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		contentPane.add(lblNewLabel_3, "cell 1 4 3 1");

		DefaultIconButton btnNewButton_2 = new DefaultIconButton("Limpar carinho");
		btnNewButton_2.setBackgroundColor(Color.RED );
		btnNewButton_2.setHoverColor(Color.RED.darker());
		contentPane.add(btnNewButton_2, "cell 3 7,growx");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBorder(new RoundedBorder(Color.black, 8));
		btnNewButton_2.setBackground(new Color(204, 0, 0));
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				ServicosDAO dao = ServicosDAO.getInstancia();

				dao.limparServicos();
				listaServicos.clear();

				atualizarJTable();
				atualizarLabelQuantidadeItens();
			}
		});
	}

	private void atualizarLabelQuantidadeItens() {
		lblNewLabel_3.setText("Você tem " + listaServicos.size() + " itens no carrinho.");
	}

	protected void atualizarJTable() {

		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {
				try {
					Servicos servico = listaServicos.get(row);
					ServicosDAO dao = ServicosDAO.getInstancia();
					dao.removerServico(servico);
					atualizarJTable();
					TelaSucesso s = new TelaSucesso("Produto removido com sucesso!");
					s.setVisible(true);
				} catch (Exception exep) {
					TelaErro s = new TelaErro("Falha ao remover produto!");
					s.setVisible(true);
				}
			}

		};
		DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {},
				new String[] { "Produtos", "Preço", "Quantidade", "Sub-Total", "Ações" });

		ServicosDAO dao = ServicosDAO.getInstancia();

		listaServicos = dao.ListarServicos();

		for (int i = 0; i < listaServicos.size(); i++) {
			Servicos s = listaServicos.get(i);
			modelo1.addRow(new Object[] { s.getNomeServico(), s.getPrecoServico(), s.getQuantidade(),
					s.getPrecoServico() * s.getQuantidade() });
		}

		table.setModel(modelo1);

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
		table.getColumnModel().getColumn(4).setPreferredWidth(60);

	}
}
