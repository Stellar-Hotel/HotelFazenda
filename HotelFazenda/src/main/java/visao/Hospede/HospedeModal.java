package visao.Hospede;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.DocumentFilter.FilterBypass;

import controle.Arredondar.RoundedBorder;
import controle.Hospede.HospedeDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedes;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import visao.Hospede.TelaDeHospedes.LetterDocumentFilter;
import visao.ModaisDeAvisos.TelaErro;
import visao.ModaisDeAvisos.TelaSucesso;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class HospedeModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Principal;
	private TelaDeHospedes th;
	private JTextField textNome;
	private JTextField textSobrenome;
	private JTextField textNascimento;
	private JTextField textCPF;
	private JTextField textNacionalidade;
	private JTextField textPronome;
	private JTextField textEmail;

	public HospedeModal(TelaDeHospedes tela, Hospedes hospede) {
		this.th = tela;
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550, 440);

		Principal = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		Principal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		setContentPane(Principal);
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
		Principal.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 11, 534, 413);
		Principal.add(panel_5);

		JLabel lblNewLabel_7 = new JLabel("Cadastrar hospede");
		lblNewLabel_7.setBounds(10, 11, 267, 22);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.BOLD, 16));

		JLabel lblNewLabel_10 = new JLabel("Nome");
		lblNewLabel_10.setBounds(10, 69, 443, 18);
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textNome = new JTextField();
		textNome.setBounds(7, 98, 229, 34);
		textNome.setColumns(10);
		textNome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNome.getDocument()).setDocumentFilter(new LetterDocumentFilter());
		textNome.setColumns(10);

		JLabel lblNewLabel_10_1 = new JLabel("Sobrenome");
		lblNewLabel_10_1.setBounds(271, 69, 182, 18);
		lblNewLabel_10_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textSobrenome = new JTextField();
		textSobrenome.setBounds(271, 98, 245, 34);
		textSobrenome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textSobrenome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

		textSobrenome.setColumns(10);

		JLabel lblNewLabel_10_2 = new JLabel("Data de nascimento");
		lblNewLabel_10_2.setBounds(7, 138, 446, 18);
		lblNewLabel_10_2.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textNascimento = new JFormattedTextField(Data);
		textNascimento.setBounds(10, 167, 226, 34);
		textNascimento.setBorder(new RoundedBorder(Color.black, 10));

		textNascimento.setColumns(10);

		JLabel lblNewLabel_10_1_1 = new JLabel("Documento");
		lblNewLabel_10_1_1.setBounds(271, 138, 182, 18);
		lblNewLabel_10_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textCPF = new JTextField();
		textCPF.setBounds(271, 167, 245, 34);
		textCPF.setBorder(new RoundedBorder(Color.black, 10));

		textCPF.setColumns(10);

		JLabel lblNewLabel_10_2_1 = new JLabel("Nacionalidade");
		lblNewLabel_10_2_1.setBounds(10, 212, 443, 18);
		lblNewLabel_10_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textNacionalidade = new JTextField();
		textNacionalidade.setBounds(10, 240, 226, 34);
		textNacionalidade.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textNacionalidade.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra
																											// para
																											// somente
																											// caracteres

		textNacionalidade.setColumns(10);

		JLabel lblNewLabel_10_2_1_1 = new JLabel("Pronome");
		lblNewLabel_10_2_1_1.setBounds(271, 212, 182, 18);
		lblNewLabel_10_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textPronome = new JFormattedTextField(mPron);
		textPronome.setBounds(271, 240, 245, 34);
		textPronome.setBorder(new RoundedBorder(Color.black, 10));
		((AbstractDocument) textPronome.getDocument()).setDocumentFilter(new LetterDocumentFilter());// filtra para
																										// somente
																										// caracteres

		textPronome.setColumns(10);

		JLabel lblNewLabel_10_2_1_1_1 = new JLabel("Email");
		lblNewLabel_10_2_1_1_1.setBounds(10, 279, 443, 18);
		lblNewLabel_10_2_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));

		textEmail = new JTextField();
		textEmail.setBounds(10, 308, 506, 34);
		textEmail.setBorder(new RoundedBorder(Color.black, 10));

		textEmail.setColumns(10);

		DefaultIconButton dfltcnbtnLimpar_1 = new DefaultIconButton("Limpar");
		dfltcnbtnLimpar_1.setIcon(new ImageIcon(HospedeModal.class.getResource("/visao/rsz_1rsz_eraser256x239.png")));
		dfltcnbtnLimpar_1.setBackgroundColor(new Color(0, 255, 255));
		dfltcnbtnLimpar_1.setHoverColor(Color.cyan);
		dfltcnbtnLimpar_1.setBounds(461, 32, 55, 40);
		dfltcnbtnLimpar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textNome.setText(" ");
				textSobrenome.setText(" ");
				textCPF.setText(null);
				textNacionalidade.setText(" ");
				textNascimento.setText(null);
				textEmail.setText(null);
				textPronome.setText(null);

			}
		});

		if (hospede == null) {

			DefaultIconButton btnCadastrar = new DefaultIconButton("Cadastrar");
			btnCadastrar.setBounds(280, 353, 215, 42);
			btnCadastrar.setBackground(new Color(117, 187, 68));
			btnCadastrar.setBorder(new RoundedBorder(Color.BLACK, 8));
			btnCadastrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((textNome.getText().isEmpty()
							|| (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty()
									|| textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty()
									|| textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))) {
						TelaErro telaErro = new TelaErro("Campos vazios");
						telaErro.setVisible(true);
						// TelaErro telaErro = new TelaErro();
						// telaErro.setVisible(true);
					} else {
						String Nome = textNome.getText().trim();
						String Sobrenome = textSobrenome.getText().trim();
						String Documento = textCPF.getText();
						String Nacionalidade = textNacionalidade.getText().trim();
						String Pronome = textPronome.getText();
						String Email = textEmail.getText();

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date Nascimento = null;
						dateFormat.setLenient(false);

						try {
							Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
						} catch (ParseException e1) {
							TelaErro telaErro = new TelaErro("Data invalida");
							telaErro.setVisible(true);
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

							th.atualizarJTable();
							TelaSucesso c = new TelaSucesso("Hospede Cadastrado!");
							c.setVisible(true);
							dispose();
						}
					}
				}
			});
			panel_5.add(btnCadastrar);
		} else {

			textNome.setText(hospede.getNome());
			textSobrenome.setText(hospede.getSobrenome());
			textPronome.setText(hospede.getPronome());
			textNacionalidade.setText(hospede.getNacionalidade());
			textCPF.setText(hospede.getDocumento());
			textEmail.setText(hospede.getEmail());
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			Date nascimento = hospede.getDataNasc();
			String nascimentoStr = dateFormat.format(nascimento);
			textNascimento.setText(nascimentoStr);

			DefaultIconButton btnAtualizar = new DefaultIconButton("Atualizar");
			btnAtualizar.setBounds(280, 353, 215, 42);
			panel_5.add(btnAtualizar);
			btnAtualizar.setBackground(new Color(117, 187, 68));
			btnAtualizar.setBorder(new RoundedBorder(Color.BLACK, 8));
			btnAtualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if ((textNome.getText().isEmpty()
							|| (textSobrenome.getText().isEmpty() || textNascimento.getText().isEmpty()
									|| textCPF.getText().isEmpty() || textNacionalidade.getText().isEmpty()
									|| textPronome.getText().isEmpty() || textEmail.getText().isEmpty()))) {
						TelaErro telaErro = new TelaErro("Campos vazios");
						telaErro.setVisible(true);
					} else {
						String Nome = textNome.getText().trim();
						String Sobrenome = textSobrenome.getText().trim();
						String Documento = textCPF.getText();
						String Nacionalidade = textNacionalidade.getText().trim();
						String Pronome = textPronome.getText();
						String Email = textEmail.getText();

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						Date Nascimento = null;
						dateFormat.setLenient(false);

						try {
							Nascimento = new Date(dateFormat.parse(textNascimento.getText()).getTime());
						} catch (ParseException e1) {
							TelaErro telaErro = new TelaErro("Data invalida");
							telaErro.setVisible(true);
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
						hospede.setIdHospede(hospede.getIdHospede());
						HospedeDAO DAO = HospedeDAO.getInstancia();
						boolean result = DAO.atualizarHospede(hospede);
						if (result) {
							TelaSucesso c = new TelaSucesso("Hospede atualizado com sucesso!");
							c.setVisible(true);
						} else {
							TelaErro c2 = new TelaErro("Erro! Aconteceu um erro inesperado!");
							c2.setVisible(true);
						}

						th.atualizarJTable();
						dispose();

					}
				}
			});
		}

		dfltcnbtnLimpar_1.setText("");
		dfltcnbtnLimpar_1.setBorder(new RoundedBorder(Color.BLACK, 8));
		dfltcnbtnLimpar_1.setBackground(new Color(0, 255, 255));
		panel_5.setLayout(null);
		panel_5.add(lblNewLabel_7);
		panel_5.add(dfltcnbtnLimpar_1);
		panel_5.add(lblNewLabel_10);
		panel_5.add(textNome);
		panel_5.add(lblNewLabel_10_1);
		panel_5.add(textSobrenome);
		panel_5.add(lblNewLabel_10_2);
		panel_5.add(textNascimento);
		panel_5.add(lblNewLabel_10_1_1);
		panel_5.add(textCPF);
		panel_5.add(lblNewLabel_10_2_1);
		panel_5.add(textNacionalidade);
		panel_5.add(lblNewLabel_10_2_1_1);
		panel_5.add(textPronome);
		panel_5.add(lblNewLabel_10_2_1_1_1);
		panel_5.add(textEmail);

		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(30, 354, 207, 40);
		panel_5.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackgroundColor(Color.RED);
		btnNewButton.setHoverColor(Color.RED.darker());

	}

	class LetterDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException { // insertString: Este método é chamado quando uma inserção de texto é
												// feita em um documento de texto.

			if (string.matches("[a-zA-Z]*")) {
				super.insertString(fb, offset, string, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException { // Este método é chamado quando uma substituição de texto é feita em um
												// documento de texto.
			if (text != null && text.matches("[a-zA-Z ]+")) {
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
