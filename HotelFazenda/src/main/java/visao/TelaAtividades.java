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

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultScreen;

public class TelaAtividades extends DefaultScreen {

	private static final long serialVersionUID = 1L;
 
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
	private JTextField textCapacidade;

	public TelaAtividades() {
		super();
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		MaskFormatter Data = null;
		try {
			Data = new MaskFormatter("##/##/####");
			Data.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Num = null;
		try {
			Num = new MaskFormatter("##");
			Num.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Idade = null;
		try {
			Idade = new MaskFormatter("##");
			Idade.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Horario = null;
		try {
			Horario = new MaskFormatter("##:##");
			Horario.setAllowsInvalid(false);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListaAtividades = new ArrayList<Atividades>();
		ListaAtividadesinscritas = new ArrayList<Atividades>();

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("",
				"[:100px:100px][74.00][92.00][][][:45px:45px,grow][grow][-47.00][36.00,grow][121px]",
				"[7.00][24.00][:29.00px:50px][][][][][][][][-21.00][][42.00][:-32.00px:10px,grow][-41.00][][-25.00][:300px:300px][:90px:90px,grow][:75.00:75]"));

		JLabel lblNewLabel_1 = new JLabel("Atividades");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 1,alignx center,aligny top");

		JScrollPane cTable = new JScrollPane();
		Principal.add(cTable, "cell 6 14 4 5,grow");

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));

		Principal.add(panel_6, "flowy,cell 1 2");

		JLabel lblNewLabel_9 = new JLabel("Inscritos");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 11));
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

		Principal.add(panel_5, "flowx,cell 0 2");

		JLabel lblNewLabel_7 = new JLabel("Todas as atividades");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_5.add(lblNewLabel_7);

		JPanel panel_7 = new JPanel();
		Principal.add(panel_7, "cell 0 15 5 1,grow");
		panel_7.setLayout(new MigLayout("", "[:110:110][:93.00:70][][:50:50][:90:90][]",
				"[14px,grow][20px,grow][20px,grow][][20px,grow][][][grow][][grow][grow][grow]"));

		JLabel lblNewLabel_10 = new JLabel("Cadastrar Atividade");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_10, "cell 0 0,alignx center,aligny top");

		JLabel lblNewLabel_12_1_1_1_1_1_1 = new JLabel("Nome da Atividade");
		lblNewLabel_12_1_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_1, "cell 0 1");

		textNomeatividade = new JTextField();
		textNomeatividade.setColumns(10);
		panel_7.add(textNomeatividade, "cell 1 1 2 1,growx,aligny center");
		textNomeatividade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNomeatividade.getDocument()).setDocumentFilter(new LetterDocumentFilter());

		JLabel lblNewLabel_12_1_1_1 = new JLabel("Idade");
		lblNewLabel_12_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1, "cell 3 1,alignx center");

		textIdade = new JFormattedTextField(Idade);
		panel_7.add(textIdade, "cell 4 1,growx,aligny center");
		textIdade.setColumns(10);
		textIdade.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1_3 = new JLabel("Capacidade");
		lblNewLabel_12_1_1_1_1_1_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_3, "cell 0 3,alignx center,aligny center");

		textCapacidade = new JFormattedTextField(Num);
		textCapacidade.setColumns(10);
		panel_7.add(textCapacidade, "cell 1 3 2 1,growx");
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1_2 = new JLabel("Data");
		lblNewLabel_12_1_1_1_1_1_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1_2, "cell 3 3,alignx center,aligny center");

		textData = new JFormattedTextField(Data);
		textData.setColumns(10);
		panel_7.add(textData, "cell 4 3,growx,aligny center");
		textData.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1 = new JLabel("Horario");
		lblNewLabel_12_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1, "cell 0 5,alignx center,aligny center");

		textHorario = new JFormattedTextField(Horario);
		panel_7.add(textHorario, "cell 1 5,growx,aligny center");
		textHorario.setColumns(10);
		textHorario.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1 = new JLabel("Horario Fim");
		lblNewLabel_12_1_1_1_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_12_1_1_1_1_1, "cell 2 5,alignx center");

		TextHorarioFim = new JFormattedTextField(Horario);
		TextHorarioFim.setColumns(10);
		panel_7.add(TextHorarioFim, "cell 3 5 2 1,growx,aligny center");
		TextHorarioFim.setBorder(new RoundedBorder(Color.black, 10));

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
						|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
						|| (textData.getText().isEmpty()) || (textCapacidade.getText().isEmpty())) {
					JOptionPane.showMessageDialog(null, "Textos vazios insira algo para adicionar!");
				} else {
					Integer Idade = Integer.valueOf(textIdade.getText());
					String Horario = textHorario.getText();
					String HorarioFim = TextHorarioFim.getText();
					String NomeAtividade = textNomeatividade.getText();
					int Capacidade = Integer.valueOf(textCapacidade.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dateFormat.setLenient(false);
					Date data = null;

					try {
						data = new Date(dateFormat.parse(textData.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data invalida.");
						return;
					}

					String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
					if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {
						JOptionPane.showMessageDialog(null, "Horário inválido. Insira no formato HH:mm");
					} else {
						Atividades ativ = new Atividades();

						ativ.setIdadeMinima(Idade);
						ativ.setHorario(Horario);
						ativ.setHorarioFim(HorarioFim);
						ativ.setNomeAtividade(NomeAtividade);
						ativ.setData(data);
						ativ.setCapacidade(Capacidade);
						System.out.println(Func.getIdFuncionario());
						ativ.setFuncionario(Func);

						AtividadesDAO DAO = AtividadesDAO.getInstancia();
						int id = DAO.InserirAtividades(ativ);

						if (id > 0) {
							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);
							atualizarJTable();
						}
					}
				}
			}
		});
		// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
		// LetterDocumentFilter());

		btnCadastrar.setForeground(new Color(255, 255, 255));
		btnCadastrar.setBackground(new Color(117, 187, 68));
		panel_7.add(btnCadastrar, "cell 1 7");

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setForeground(new Color(255, 255, 255));
		btnAlterar.setBackground(new Color(117, 187, 68));
		btnAlterar.setBorder(new RoundedBorder(Color.black, 8));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
						|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
						|| (textData.getText().isEmpty() || (textCapacidade.getText().isEmpty()))) {
					JOptionPane.showMessageDialog(null, "Textos vazios insira algo para atualizar");
				} else {

					Integer Idade = Integer.valueOf(textIdade.getText());
					String Horario = textHorario.getText();
					String HorarioFim = TextHorarioFim.getText();
					String NomeAtividade = textNomeatividade.getText();
					int Capacidade = Integer.valueOf(textCapacidade.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dateFormat.setLenient(false);
					Date data = null;

					try {
						data = new Date(dateFormat.parse(textData.getText()).getTime());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Data invalida.");
						return;
					}
					String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
					if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {
						JOptionPane.showMessageDialog(null, "Horário inválido. Insira no formato HH:mm");
					} else {

						int linha = table.getSelectedRow();

						Atividades ativ = new Atividades();

						ativ.setIdadeMinima(Idade);
						ativ.setHorario(Horario);
						ativ.setHorarioFim(HorarioFim);
						ativ.setNomeAtividade(NomeAtividade);
						ativ.setData(data);
						ativ.setCapacidade(Capacidade);
						ativ.setFuncionario(Func);
						ativ.setIdAtividade(ListaAtividades.get(linha).getIdAtividade());

						AtividadesDAO DAO = AtividadesDAO.getInstancia();
						DAO.AtualizarAtividades(ativ);
						if (linha < 0) {
							JOptionPane.showMessageDialog(null, "selecione uma linha");
						} else if (linha >= 0) {
							DefaultTableModel model1 = (DefaultTableModel) table.getModel();
							// model1.setValueAt(Idade, linha, 0);
							model1.setValueAt(Idade, linha, 1);
							model1.setValueAt(Horario, linha, 2);
							model1.setValueAt(HorarioFim, linha, 3);
							model1.setValueAt(NomeAtividade, linha, 4);
							model1.setValueAt(data, linha, 5);
							model1.setValueAt(Capacidade, linha, 6);

							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);

						}
					}

				}
			}
		});
		panel_7.add(btnAlterar, "flowx,cell 2 7,growx");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setForeground(new Color(255, 255, 255));
		btnExcluir.setBackground(new Color(117, 187, 68));
		btnExcluir.setBorder(new RoundedBorder(Color.black, 8));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AtividadesDAO DAO = AtividadesDAO.getInstancia();
				int linha = table.getSelectedRow();
				Atividades ativ = new Atividades();

				if (linha >= 0) {
					ativ = ListaAtividades.get(linha);
					DAO.RemoverAtividades(ativ);
					atualizarJTable();
					TelaSucesso c = new TelaSucesso("Atividade Exluída!");
					c.setVisible(true);
				} else if (linha <= 0) {
					JOptionPane.showMessageDialog(null, "selecione uma linha para excluir");
				}

			}
		});
		panel_7.add(btnExcluir, "cell 3 7,growx");

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.LIGHT_GRAY);
		separator_2.setBackground(Color.LIGHT_GRAY);
		panel_7.add(separator_2, "cell 0 8 6 1,growx");

		JLabel lblNewLabel_10_1_1 = new JLabel("Cadastrar Hospede");
		lblNewLabel_10_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		panel_7.add(lblNewLabel_10_1_1, "cell 2 9 2 1");
		// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
		// LetterDocumentFilter());

		JButton btnNewButton_1 = new JButton("Cadastrar");
		btnNewButton_1.setBorder(new RoundedBorder(Color.BLACK, 8));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TelaAtividadesHospedes chama = new TelaAtividadesHospedes(Func, ListaAtividades);
				chama.setVisible(true);

			}
		});

		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(117, 187, 68));
		panel_7.add(btnNewButton_1, "cell 2 11");

		table = new CustomTable(model1);
		cTable.setViewportView(table);

		model1 = (new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade", "IdadeMinima", "Horario",
				"HorarioFim", "NomeAtividade", "Data", "IDFuncionario", "Capacidade", "Ações" }));

		model2 = (new DefaultTableModel(new Object[][] {},
				new String[] { "IdAtividade", "Funcionario", "NomeAtividade" }));

		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cTable.setViewportView(table);
				atualizarJTable();
			}
		});

		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				cTable.setViewportView(new JTable(model2));
				atualizarJTable();
			}
		});

		atualizarJTable();
		setPrincipalPanel(Principal);

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
				AtividadesDAO DAO = AtividadesDAO.getInstancia();
				int linha = table.getSelectedRow();
				Atividades ativ = new Atividades();

				if (linha >= 0) {
					ativ = ListaAtividades.get(linha);
					DAO.RemoverAtividades(ativ);
					atualizarJTable();
					TelaSucesso c = new TelaSucesso("Atividade Exluída!");
					c.setVisible(true);
				} else if (linha <= 0) {
					JOptionPane.showMessageDialog(null, "selecione uma linha para excluir");
				}
			}

		};
		DefaultTableModel modelo1 = new DefaultTableModel(new Object[][] {}, new String[] { "IdAtividade",
				"IdadeMinima", "Horario", "HorarioFim", "NomeAtividade", "Data", "Capacidade", "Ações" });

		AtividadesDAO AtivDAO = AtividadesDAO.getInstancia();
		ListaAtividades = AtivDAO.ListarAtividades();

		for (int i = 0; i < ListaAtividades.size(); i++) {
			Atividades p = ListaAtividades.get(i);
			modelo1.addRow(new Object[] { p.getIdAtividade(), p.getIdadeMinima(), p.getHorario(), p.getHorarioFim(),
					p.getNomeAtividade(), p.getData(), p.getCapacidade() });
		}

		table.setModel(modelo1);

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
		table.getColumnModel().getColumn(7).setPreferredWidth(145);

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
