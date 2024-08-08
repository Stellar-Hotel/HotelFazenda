package visao.Atividade;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controle.Combobox.RoundedComboBoxUI;
//import controle.Combobox.CustomArrowButton;

import controle.AtividadesHospedes.AtividadesHospedesDAO;
import controle.Hospede.HospedeDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.Funcionarios;
import modelo.Hospedes;
import raven.cell.CustomTable;
import raven.cell.TableActionCellEditor;
import raven.cell.TableActionCellRender;
import raven.cell.TableActionEvent;
import utils.DefaultIconButton;
import java.awt.Color;

public class TelaAtividadesHospedes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private CustomTable table;
	private DefaultTableModel model;
	private ArrayList<AtividadesHospedes> ListaatividadesHospedes;
	private JComboBox<Atividades> comboBox;
	private JTextField textField;

	public TelaAtividadesHospedes(Funcionarios Func, ArrayList<Atividades> ListaAtividades) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		ListaatividadesHospedes = new ArrayList<AtividadesHospedes>();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 672, 471);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Selecione a Atividade");
		lblNewLabel.setBounds(24, 13, 201, 31);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(270, 12, 369, 435);
		panel.add(scrollPane);

		// Configurar o modelo da tabela
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Atividade", "Hospede", "Ações" });
		table = new CustomTable(model);
		scrollPane.setViewportView(table);

		// Configurar o modelo do JComboBox e o renderizador
		DefaultComboBoxModel<Atividades> comboBoxModel = new DefaultComboBoxModel<>();
		for (Atividades atividade : ListaAtividades) {
			comboBoxModel.addElement(atividade);
		}

		comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setUI(new RoundedComboBoxUI(comboBox)); // Use RoundedComboBoxUI com a instância de JComboBox

		comboBox.setBounds(24, 55, 201, 22); // Ajuste o tamanho conforme necessário
		panel.add(comboBox);

		// Defina um renderizador personalizado para mostrar apenas o atributo desejado
		comboBox.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Atividades) {
					Atividades atividade = (Atividades) value;
					setText(atividade.getNomeAtividade()); // Substitua por qualquer método que retorne o atributo
															// desejado
				}
				return component;
			}
		});

		JLabel lblNewLabel_1 = new JLabel("CPF do hospede");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(24, 95, 201, 18);
		panel.add(lblNewLabel_1);

		DefaultIconButton btnAdicionar = new DefaultIconButton("Adicionar");
		btnAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String documento = textField.getText();
				HospedeDAO daoHospede = HospedeDAO.getInstancia();
				AtividadesHospedesDAO daoAtividadesHospedes = AtividadesHospedesDAO.getInstancia();
				AtividadesHospedes atividadesHospedes = new AtividadesHospedes();
				LocalDate dataHoje = LocalDate.now();

				Hospedes hospede = daoHospede.buscarHospedePorCPF(documento);
				if (hospede == null) {
					JOptionPane.showMessageDialog(null, "CPF não encontrado.");
					return;

				}

				Atividades ativ = (Atividades) comboBox.getSelectedItem();
				if (ativ == null) {
					JOptionPane.showMessageDialog(null, "Nenhuma atividade selecionada.");
					return;
				}

				int capacidade = ativ.getCapacidade();
				int countHospedes = daoAtividadesHospedes.contarHospedesNaAtividade(ativ.getIdAtividade());

				if (countHospedes >= capacidade) {
					JOptionPane.showMessageDialog(null, "Capacidade máxima atingida para esta atividade.");
					return;
				}

				LocalDate dataNascimento = convertToLocalDate(hospede.getDataNasc());
				int idade = calcularIdade(dataNascimento);
				System.out.println("Idade do hóspede: " + idade);

				if (idade < ativ.getIdadeMinima()) {
					JOptionPane.showMessageDialog(null, "Idade do hóspede menor que a idade mínima necessária.");
					return;
					
				}

				if (daoAtividadesHospedes.isHospedeRegisteredForActivity(hospede.getDocumento(),
						ativ.getIdAtividade())) {
					JOptionPane.showMessageDialog(null, "CPF já está vinculado a esta atividade.");
					return;

				}

				// Adicionar atividade para o hóspede
				atividadesHospedes.setHospede(hospede);
				atividadesHospedes.setAtividade(ativ);
				daoAtividadesHospedes.InserirAtividadesHospedes(atividadesHospedes);
				atualizarJTable();

			}

		});
		btnAdicionar.setBounds(123, 165, 117, 31);
		panel.add(btnAdicionar);

		textField = new JTextField();
		textField.setBounds(24, 124, 201, 22);
		panel.add(textField);
		textField.setColumns(10);

		 

		DefaultIconButton btnSair = new DefaultIconButton("Voltar");
		btnSair.setBackgroundColor(Color.RED);
		btnSair.setHoverColor( Color.RED.darker());
		btnSair.setFont(new Font("Segoe UI", Font.BOLD, 15));
	 
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 
				dispose();
			}
		});
		btnSair.setBounds(10, 165, 103, 31);
		panel.add(btnSair);

		// Atualizar a tabela
		atualizarJTable();
	}

	protected void atualizarJTable() {
		TableActionEvent event = new TableActionEvent() {

			@Override
			public void onEdit(int row) {
				System.out.println("Edit row : " + row);
			}

			@Override
			public void onDelete(int row) {
				AtividadesHospedesDAO DAO = AtividadesHospedesDAO.getInstancia();
		 
				AtividadesHospedes ativ = new AtividadesHospedes();

			 
					ativ = ListaatividadesHospedes.get(row);
					DAO.RemoverAtividadeHospede(ativ.getIdHospedeAtividade());
					atualizarJTable();
			 

			}

		};
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.setRowCount(0);

		AtividadesHospedesDAO hospedesatividadesDAO = AtividadesHospedesDAO.getInstancia();
		ListaatividadesHospedes = hospedesatividadesDAO.ListarAtividadesHospedes();

		for (AtividadesHospedes p : ListaatividadesHospedes) {
			model.addRow(new Object[] { p.getAtividade().getNomeAtividade(), p.getHospede().getNome() });
		}

		TableActionCellRender cellRenderer = new TableActionCellRender(false, true); // Inicialmente nenhuma linha
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

		table.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event, false, true));
		table.setRowHeight(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(130);

	}

	private int calcularIdade(LocalDate dataNascimento) {
		LocalDate hoje = LocalDate.now();
		if (dataNascimento != null) {
			return Period.between(dataNascimento, hoje).getYears();
		} else {
			throw new IllegalArgumentException("Data de nascimento não pode ser nula.");
		}
	}

	private LocalDate convertToLocalDate(Date sqlDate) {
		return sqlDate.toLocalDate();
	}
}
