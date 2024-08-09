package visao.Atividade;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import com.google.protobuf.TextFormat.ParseException;

import controle.Arredondar.RoundedBorder;
import controle.Atividades.AtividadesDAO;
import modelo.Atividades;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import utils.DefaultIconButton;
import visao.Home;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;

public class AtividadesModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIdade;
	private JTextField textHorario;
	private JTextField textNomeatividade;
	private JTextField TextHorarioFim;
	private JTextField textData;
	private JTextField textCapacidade;
	private TelaAtividades telaAtividades;

	public AtividadesModal(Atividades atividade, TelaAtividades telaAtividades)
			throws java.text.ParseException, ParseException {
		this.telaAtividades = telaAtividades;
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 550);

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

		setContentPane(contentPane);

		MaskFormatter Data = null;
		Data = new MaskFormatter("##/##/####");
		Data.setAllowsInvalid(false);
		MaskFormatter Num = null;
		Num = new MaskFormatter("##");
		Num.setAllowsInvalid(false);
		MaskFormatter Idade = null;
		Idade = new MaskFormatter("##");
		Idade.setAllowsInvalid(false);
		MaskFormatter Horario = null;
		Horario = new MaskFormatter("##:##");
		Horario.setAllowsInvalid(false);
		contentPane.setLayout(null);
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(42, 34, 455, 484);
		contentPane.add(panel_7);

		JLabel lblNewLabel_10 = new JLabel("Cadastrar Atividade");
		lblNewLabel_10.setBounds(10, 11, 209, 34);
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.BOLD, 22));

		JLabel lblNewLabel_12_1_1_1_1_1_1 = new JLabel("Nome da Atividade");
		lblNewLabel_12_1_1_1_1_1_1.setBounds(27, 81, 359, 20);
		lblNewLabel_12_1_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		JLabel lblNewLabel_12_1_1_1 = new JLabel("Idade");
		lblNewLabel_12_1_1_1.setBounds(27, 234, 359, 20);
		lblNewLabel_12_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textIdade = new JFormattedTextField(Idade);
		textIdade.setBounds(27, 265, 189, 34);
		textIdade.setColumns(10);
		textIdade.setBorder(new RoundedBorder(Color.black, 10));
		textNomeatividade = new JTextField();
		textNomeatividade.setBounds(27, 112, 412, 34);
		textNomeatividade.setColumns(10);
		textNomeatividade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNomeatividade.getDocument()).setDocumentFilter(new LetterDocumentFilter());
		JLabel lblNewLabel_12_1_1_1_1_1_3 = new JLabel("Capacidade");
		lblNewLabel_12_1_1_1_1_1_3.setBounds(27, 158, 359, 20);
		lblNewLabel_12_1_1_1_1_1_3.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textCapacidade = new JFormattedTextField(Num);
		textCapacidade.setBounds(27, 189, 412, 34);
		textCapacidade.setColumns(10);
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1_2 = new JLabel("Data");
		lblNewLabel_12_1_1_1_1_1_2.setBounds(245, 234, 359, 20);
		lblNewLabel_12_1_1_1_1_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textData = new JFormattedTextField(Data);
		textData.setBounds(245, 265, 194, 34);
		textData.setColumns(10);
		textData.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1 = new JLabel("Horario");
		lblNewLabel_12_1_1_1_1.setBounds(27, 307, 359, 20);
		lblNewLabel_12_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textHorario = new JFormattedTextField(Horario);
		textHorario.setBounds(27, 338, 189, 34);
		textHorario.setColumns(10);
		textHorario.setBorder(new RoundedBorder(Color.black, 10));

		JLabel lblNewLabel_12_1_1_1_1_1 = new JLabel("Horario Fim");
		lblNewLabel_12_1_1_1_1_1.setBounds(245, 307, 141, 20);
		lblNewLabel_12_1_1_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		TextHorarioFim = new JFormattedTextField(Horario);
		TextHorarioFim.setBounds(245, 338, 194, 34);
		TextHorarioFim.setColumns(10);
		TextHorarioFim.setBorder(new RoundedBorder(Color.black, 10));

		if (atividade == null) {

			DefaultIconButton btnCadastrar = new DefaultIconButton("Cadastrar");
			btnCadastrar.setBounds(236, 405, 203, 40);
			btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));

			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
							|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
							|| (textData.getText().isEmpty()) || (textCapacidade.getText().isEmpty())) {

						TelaErro telaErro = new TelaErro("Textos vazios");
						telaErro.setVisible(true);

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
						} catch (java.text.ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
						if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {
							TelaErro telaErro = new TelaErro("Data invalida insira no formato HH:MM");
							telaErro.setVisible(true);
						} else {
							Atividades ativ = new Atividades();

							ativ.setIdadeMinima(Idade);
							ativ.setHorario(Horario);
							ativ.setHorarioFim(HorarioFim);
							ativ.setNomeAtividade(NomeAtividade);
							ativ.setData(data);
							ativ.setCapacidade(Capacidade);

							ativ.setFuncionario(Func);

							AtividadesDAO DAO = AtividadesDAO.getInstancia();
							int id = DAO.InserirAtividades(ativ);

							if (id > 0) {
								TelaSucesso c = new TelaSucesso("Sucesso");
								c.setVisible(true);
								dispose();

								telaAtividades.atualizarJTable();
								dispose();
							}
						}
					}
				}
			});
			// ((AbstractDocument) textHorario.getDocument()).setDocumentFilter(new
			// LetterDocumentFilter());

			btnCadastrar.setForeground(new Color(255, 255, 255));
			btnCadastrar.setBackground(new Color(117, 187, 68));
			panel_7.add(btnCadastrar);
		} else {
			textNomeatividade.setText(atividade.getNomeAtividade());
			textCapacidade.setText(String.valueOf(atividade.getCapacidade()));
			textHorario.setText(atividade.getHorario());
			TextHorarioFim.setText(atividade.getHorarioFim());
			textIdade.setText(String.valueOf(atividade.getIdadeMinima()));
			textData.setText(String.valueOf(atividade.getData()));

			DefaultIconButton btnAlterar = new DefaultIconButton("Alterar");
			btnAlterar.setBounds(236, 405, 203, 40);
			btnAlterar.setBackgroundColor(Color.ORANGE);
			btnAlterar.setHoverColor(Color.ORANGE.darker());
			btnAlterar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if ((textIdade.getText().isEmpty()) || (textHorario.getText().isEmpty())
							|| (TextHorarioFim.getText().isEmpty()) || (textNomeatividade.getText().isEmpty())
							|| (textData.getText().isEmpty() || (textCapacidade.getText().isEmpty()))) {
						TelaErro telaErro = new TelaErro("Campos vazios");
						telaErro.setVisible(true);

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
						} catch (java.text.ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String horarioPattern = "(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]";
						if (!Horario.matches(horarioPattern) || !HorarioFim.matches(horarioPattern)) {

							TelaErro telaErro = new TelaErro("Horario Invalido insira no formato HH:MM");
							telaErro.setVisible(true);

						} else {

							Atividades ativ = new Atividades();

							ativ.setIdadeMinima(Idade);
							ativ.setHorario(Horario);
							ativ.setHorarioFim(HorarioFim);
							ativ.setNomeAtividade(NomeAtividade);
							ativ.setData(data);
							ativ.setCapacidade(Capacidade);
							ativ.setFuncionario(Func);
							ativ.setIdAtividade(atividade.getIdAtividade());

							AtividadesDAO DAO = AtividadesDAO.getInstancia();
							DAO.AtualizarAtividades(ativ);

							TelaSucesso c = new TelaSucesso("Sucesso");
							c.setVisible(true);
							telaAtividades.atualizarJTable();
							dispose();
						}

					}
				}
			});
			panel_7.add(btnAlterar);
		}
		DefaultIconButton dfltcnbtnLimpar = new DefaultIconButton(
				new ImageIcon(Home.class.getResource("/visao/NaoVer.png")));
		dfltcnbtnLimpar.setIcon(new ImageIcon(AtividadesModal.class.getResource("/visao/rsz_1rsz_eraser256x239.png")));
		dfltcnbtnLimpar.setBounds(392, 61, 47, 40);
		dfltcnbtnLimpar.setBackgroundColor(new Color(0, 255, 255));
		dfltcnbtnLimpar.setHoverColor(new Color(0, 255, 255));
		dfltcnbtnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				textCapacidade.setText("");
				textData.setText("");
				textHorario.setText("");
				textIdade.setText("");
				textNomeatividade.setText("");
				TextHorarioFim.setText("");
			}
		});
		dfltcnbtnLimpar.setBorder(new RoundedBorder(Color.black, 10));
		dfltcnbtnLimpar.setBackground(new Color(117, 187, 68));
		panel_7.setLayout(null);
		panel_7.add(lblNewLabel_12_1_1_1_1_1_3);
		panel_7.add(textCapacidade);
		panel_7.add(lblNewLabel_10);
		panel_7.add(lblNewLabel_12_1_1_1_1_1_1);
		panel_7.add(textNomeatividade);
		panel_7.add(lblNewLabel_12_1_1_1);
		panel_7.add(textIdade);
		panel_7.add(dfltcnbtnLimpar);

		panel_7.add(textData);

		panel_7.add(lblNewLabel_12_1_1_1_1_1_2);
		panel_7.add(textHorario);
		panel_7.add(lblNewLabel_12_1_1_1_1);
		panel_7.add(lblNewLabel_12_1_1_1_1_1);
		panel_7.add(TextHorarioFim);

		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackgroundColor(Color.RED);
		btnNewButton.setHoverColor(Color.RED.darker());
		btnNewButton.setBounds(27, 405, 194, 40);
		panel_7.add(btnNewButton);

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
