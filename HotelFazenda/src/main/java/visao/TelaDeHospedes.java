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
import utils.DefaultScreen;

public class TelaDeHospedes extends DefaultScreen {

	private static final long serialVersionUID = 1L;
	 
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
		super();
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

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
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 38));
		Principal.add(lblNewLabel_9, "cell 1 1 2 1");

		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 1 3 3 1,grow");
		panel_5.setLayout(new MigLayout("", "[:40:40][][56.00][32.00][:30:30][][82.00][44.00][:126.00px:100px]",
				"[][:15:15][29.00][11.00][:28.00px:100px][11.00][][11][][grow][][grow]"));

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
		((AbstractDocument) textSobrenome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

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
		((AbstractDocument) textNacionalidade.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra
																											// para
																											// somente
																											// caracteres

		textNacionalidade.setColumns(10);
		panel_5.add(textNacionalidade, "cell 2 6 2 1,growx");

		JLabel lblNewLabel_10_2_1_1 = new JLabel("Pronome");
		lblNewLabel_10_2_1_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_10_2_1_1, "cell 4 6,alignx left,aligny center");

		textPronome = new JTextField();
		textPronome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textPronome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

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
		Principal.add(scrollPane, "cell 7 3,grow");

		JButton btnCadastrar = new JButton("Cadastrar");
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

		JButton btnExcluir = new JButton("Excluir");
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

		JButton btnAtualizar = new JButton("Atualizar");
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

		Model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Sobrenome", "Data de Nascimento",
				"Documento", "Nacionalidade", "Pronome", "Email", "Acoes" });

		table = new CustomTable(Model);

		scrollPane.setViewportView(table);

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
