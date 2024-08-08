package visao;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TelaAtividadesHospedes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private ArrayList<AtividadesHospedes> ListaatividadesHospedes;
	private JComboBox<Atividades> comboBox;
	private JTextField textField;

	public TelaAtividadesHospedes(Funcionarios Func, ArrayList<Atividades> ListaAtividades) {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 600);
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
		panel.setBounds(10, 11, 643, 670);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome da Atividade");
		lblNewLabel.setBounds(24, 13, 201, 31);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		panel.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 120, 590, 543);
		panel.add(scrollPane);

		// Configurar o modelo da tabela
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Atividade", "Hospede" });
		table = new JTable(model);
		scrollPane.setViewportView(table);

		// Configurar o modelo do JComboBox e o renderizador
		DefaultComboBoxModel<Atividades> comboBoxModel = new DefaultComboBoxModel<>();
		for (Atividades atividade : ListaAtividades) {
			comboBoxModel.addElement(atividade);
		}

		comboBox = new JComboBox<>(comboBoxModel);
		comboBox.setUI(new RoundedComboBoxUI(comboBox)); // Use RoundedComboBoxUI com a instância de JComboBox

		comboBox.setBounds(250, 22, 300, 22); // Ajuste o tamanho conforme necessário
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

		JLabel lblNewLabel_1 = new JLabel("Documento");
		lblNewLabel_1.setBounds(24, 69, 76, 14);
		panel.add(lblNewLabel_1);

		JButton btnAdicionar = new JButton("Adicionar");
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

				}

				Atividades ativ = (Atividades) comboBox.getSelectedItem();
				if (ativ == null) {
					JOptionPane.showMessageDialog(null, "Nenhuma atividade selecionada.");

				}

				int capacidade = ativ.getCapacidade();
				int countHospedes = daoAtividadesHospedes.contarHospedesNaAtividade(ativ.getIdAtividade());

				if (countHospedes >= capacidade) {
					JOptionPane.showMessageDialog(null, "Capacidade máxima atingida para esta atividade.");

				}

				LocalDate dataNascimento = convertToLocalDate(hospede.getDataNasc());
				int idade = calcularIdade(dataNascimento);
				System.out.println("Idade do hóspede: " + idade);

				if (idade < ativ.getIdadeMinima()) {
					JOptionPane.showMessageDialog(null, "Idade do hóspede menor que a idade mínima necessária.");

				}

				if (daoAtividadesHospedes.isHospedeRegisteredForActivity(hospede.getDocumento(),
						ativ.getIdAtividade())) {
					JOptionPane.showMessageDialog(null, "CPF já está vinculado a esta atividade.");

				}

				// Adicionar atividade para o hóspede
				atividadesHospedes.setHospede(hospede);
				atividadesHospedes.setAtividade(ativ);
				daoAtividadesHospedes.InserirAtividadesHospedes(atividadesHospedes);
				atualizarJTable();

			}

		});
		btnAdicionar.setBounds(250, 65, 89, 23);
		panel.add(btnAdicionar);

		textField = new JTextField();
		textField.setBounds(94, 66, 131, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Excluir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AtividadesHospedesDAO DAO = AtividadesHospedesDAO.getInstancia();
				int linha = table.getSelectedRow();
				AtividadesHospedes ativ = new AtividadesHospedes();

				if (linha >= 0) {
					ativ = ListaatividadesHospedes.get(linha);
					DAO.RemoverAtividadeHospede(ativ.getIdHospedeAtividade());
					atualizarJTable();
				}

			}
		});
		btnNewButton.setBounds(360, 65, 89, 23);
		panel.add(btnNewButton);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAtividades chama = new TelaAtividades();
				chama.setVisible(true);
				dispose();
			}
		});
		btnSair.setBounds(560, 22, 54, 23);
		panel.add(btnSair);

		// Atualizar a tabela
		atualizarJTable();
	}

	protected void atualizarJTable() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		model.setRowCount(0);

		AtividadesHospedesDAO hospedesatividadesDAO = AtividadesHospedesDAO.getInstancia();
		ListaatividadesHospedes = hospedesatividadesDAO.ListarAtividadesHospedes();

		for (AtividadesHospedes p : ListaatividadesHospedes) {
			model.addRow(new Object[] { p.getAtividade().getNomeAtividade(), p.getHospede().getNome() });
		}
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
