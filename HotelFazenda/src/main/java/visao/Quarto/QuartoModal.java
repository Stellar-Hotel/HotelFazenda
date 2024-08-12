package visao.Quarto;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import controle.Arredondar.RoundedBorder;
import controle.Quartos.QuartosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import visao.ModaisDeAvisos.TelaSucesso;
import javax.swing.JButton;

public class QuartoModal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel Principal;
	private Quartos2 quarto;
	Funcionarios Func2 = CurrentFunc.getInstance().getLoggedInFuncionario();
	private JTextField textCPF;
	private JTextField textChecki;
	private JTextField textChecko;
	private JTextField textTipo;
	private JTextField textCapacidade;
	private JTextField textCama;
	private JTextField textDiaria;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();
	private final ButtonGroup buttonGroup_4 = new ButtonGroup();
	private final ButtonGroup buttonGroup_5 = new ButtonGroup();

	public QuartoModal(Quartos2 q, Quartos quartoAtual) {
		this.quarto = q;

		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(370, 690);

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
 

		DecimalFormat formato = new DecimalFormat("#.##");

	 

		MaskFormatter formatoCpf = null;
		setContentPane(Principal);
		MaskFormatter Num1 = null;
		try {
			Num1 = new MaskFormatter("#");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MaskFormatter Num2 = null;
		try {
			Num2 = new MaskFormatter("##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MaskFormatter Num3 = null;
		try {
			Num3 = new MaskFormatter("####.##");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
		Principal.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 11, 350, 669);
		Principal.add(panel_5);
		panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][grow]"));

		JPanel panel_6 = new JPanel();
		panel_5.add(panel_6, "cell 0 0 2 2,grow");
		panel_6.setLayout(null);

		JLabel lblNewLabel_7 = new JLabel("Tipo: ");
		lblNewLabel_7.setBounds(10, 26, 143, 20);
		lblNewLabel_7.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_7);

		textCPF = new JTextField();

		textChecki = new JTextField();

		textChecko = new JTextField();

		textTipo = new JFormattedTextField(Num1);
		textTipo.setBounds(10, 52, 143, 34);
		textTipo.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textTipo);
		textTipo.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Situação: ");
		lblNewLabel_9.setBounds(10, 168, 259, 20);
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Capacidade: ");
		lblNewLabel_10.setBounds(10, 97, 143, 20);
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_10);

		textCapacidade = new JFormattedTextField(Num2);
		textCapacidade.setBounds(10, 123, 143, 34);
		textCapacidade.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCapacidade);
		textCapacidade.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Manutenção: ");
		lblNewLabel_11.setBounds(10, 238, 118, 20);
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Cama: ");
		lblNewLabel_12.setBounds(173, 26, 153, 20);
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_12);

		textCama = new JTextField();
		textCama.setBounds(173, 52, 153, 34);
		textCama.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textCama);
		textCama.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Frigobar: ");
		lblNewLabel_13.setBounds(10, 307, 93, 20);
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_13);

		JRadioButton rdbtnFrigoTem = new JRadioButton("Possui");
		buttonGroup.add(rdbtnFrigoTem);
		rdbtnFrigoTem.setBounds(10, 334, 65, 29);
		panel_6.add(rdbtnFrigoTem);
		rdbtnFrigoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnFrigoNaoTem = new JRadioButton("Não possui");
		buttonGroup.add(rdbtnFrigoNaoTem);
		rdbtnFrigoNaoTem.setBounds(97, 334, 93, 29);
		rdbtnFrigoNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_6.add(rdbtnFrigoNaoTem);

		JLabel lblNewLabel_14 = new JLabel("Ar Condicionado: ");
		lblNewLabel_14.setBounds(10, 381, 143, 20);
		lblNewLabel_14.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_14);

		JRadioButton rdbtnArTem = new JRadioButton("Possui");
		buttonGroup_2.add(rdbtnArTem);
		rdbtnArTem.setBounds(10, 479, 65, 29);
		panel_6.add(rdbtnArTem);
		rdbtnArTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnArNaoTem = new JRadioButton("Não possui");
		buttonGroup_2.add(rdbtnArNaoTem);
		rdbtnArNaoTem.setBounds(97, 479, 93, 29);
		rdbtnArNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_6.add(rdbtnArNaoTem);

		JLabel lblNewLabel_16 = new JLabel("Banheira: ");
		lblNewLabel_16.setBounds(10, 452, 81, 20);
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_16);

		JRadioButton rdbtnBanTem = new JRadioButton("Possui");
		buttonGroup_3.add(rdbtnBanTem);
		rdbtnBanTem.setBounds(10, 554, 65, 29);
		panel_6.add(rdbtnBanTem);
		rdbtnBanTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnBanNaoTem = new JRadioButton("Não possui");
		buttonGroup_3.add(rdbtnBanNaoTem);
		rdbtnBanNaoTem.setBounds(97, 554, 93, 29);
		rdbtnBanNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_6.add(rdbtnBanNaoTem);

		JLabel lblNewLabel_17 = new JLabel("Televisão: ");
		lblNewLabel_17.setBounds(10, 527, 101, 20);
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_17);

		JRadioButton rdbtnTvTem = new JRadioButton("Possui");
		buttonGroup_1.add(rdbtnTvTem);
		rdbtnTvTem.setBounds(10, 408, 65, 29);
		panel_6.add(rdbtnTvTem);
		rdbtnTvTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnTvNaoTem = new JRadioButton("Não possui");
		buttonGroup_1.add(rdbtnTvNaoTem);
		rdbtnTvNaoTem.setBounds(97, 408, 93, 29);
		rdbtnTvNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		panel_6.add(rdbtnTvNaoTem);

		JLabel lblNewLabel_18 = new JLabel("Diária: ");
		lblNewLabel_18.setBounds(173, 97, 153, 20);
		lblNewLabel_18.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		panel_6.add(lblNewLabel_18);

		textDiaria = new JFormattedTextField(Num3);
		textDiaria.setBounds(173, 123, 153, 34);
		textDiaria.setBorder(new RoundedBorder(Color.black, 10));
		panel_6.add(textDiaria);
		textDiaria.setColumns(10);

		JRadioButton rdbtnManuNaoTem = new JRadioButton("Não feita");
		buttonGroup_4.add(rdbtnManuNaoTem);
		rdbtnManuNaoTem.setBounds(97, 265, 81, 29);
		panel_6.add(rdbtnManuNaoTem);
		rdbtnManuNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnManuTem = new JRadioButton("Feita");
		buttonGroup_4.add(rdbtnManuTem);
		rdbtnManuTem.setBounds(10, 265, 55, 29);
		panel_6.add(rdbtnManuTem);
		rdbtnManuTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnSituNaoTem = new JRadioButton("Livre");
		rdbtnSituNaoTem.setBounds(10, 195, 55, 29);
		panel_6.add(rdbtnSituNaoTem);
		buttonGroup_5.add(rdbtnSituNaoTem);
		rdbtnSituNaoTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		JRadioButton rdbtnSituTem = new JRadioButton("Ocupado");
		rdbtnSituTem.setBounds(97, 195, 81, 29);
		panel_6.add(rdbtnSituTem);
		buttonGroup_5.add(rdbtnSituTem);
		rdbtnSituTem.setFont(new Font("Segoe UI", Font.PLAIN, 14));

		DefaultIconButton dfltcnbtnLimpar = new DefaultIconButton("Limpar");
		dfltcnbtnLimpar.setBounds(304, 0, 32, 28);
		panel_6.add(dfltcnbtnLimpar);
		dfltcnbtnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dfltcnbtnLimpar.setBackgroundColor(new Color(0, 255, 255));
		dfltcnbtnLimpar.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/rsz_1rsz_eraser256x239.png")));
		dfltcnbtnLimpar.setHoverColor(new Color(0, 255, 255));
		dfltcnbtnLimpar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textCama.setText(null);
				textCapacidade.setText(null);
				textChecki.setText(null);
				textChecko.setText(null);
				textCPF.setText(null);
				textDiaria.setText(null);
				textTipo.setText(null);

				buttonGroup.clearSelection();
				buttonGroup_1.clearSelection();
				buttonGroup_2.clearSelection();
				buttonGroup_3.clearSelection();
				buttonGroup_4.clearSelection();
				buttonGroup_5.clearSelection();
			}
		});
		dfltcnbtnLimpar.setText("");
		dfltcnbtnLimpar.setBorder(new RoundedBorder(Color.black, 10));
		dfltcnbtnLimpar.setBackground(new Color(0, 255, 255));
		dfltcnbtnLimpar.setHoverColor(new Color(0, 255, 255));
		DefaultIconButton btnNewButton = new DefaultIconButton("Voltar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBackgroundColor(Color.RED);
		btnNewButton.setHoverColor(Color.RED.darker());

		btnNewButton.setBounds(17, 600, 136, 44);
		panel_6.add(btnNewButton);

		if (quartoAtual == null) {
			DefaultIconButton btnSalvar = new DefaultIconButton("Cadastrar");
			btnSalvar.setBounds(173, 600, 136, 44);
		 
			btnSalvar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Quartos q = new Quartos();
					QuartosDAO dao = QuartosDAO.getConexao();

					if (rdbtnArTem.isSelected() || rdbtnArNaoTem.isSelected()) {
						q.setArCondicionado(rdbtnArTem.isSelected() ? true : false);
					}

					if (rdbtnBanTem.isSelected() || rdbtnBanNaoTem.isSelected()) {
						q.setBanheira(rdbtnBanTem.isSelected() ? true : false);
					}

					if (rdbtnFrigoTem.isSelected() || rdbtnFrigoNaoTem.isSelected()) {
						q.setFrigobar(rdbtnFrigoTem.isSelected() ? true : false);
					}

					if (rdbtnTvTem.isSelected() || rdbtnTvNaoTem.isSelected()) {
						q.setTV(rdbtnTvTem.isSelected() ? true : false);
					}

					if (rdbtnManuTem.isSelected() || rdbtnManuNaoTem.isSelected()) {
						q.setManutencao(String.valueOf(rdbtnManuTem.isSelected() ? 1 : 0));
					}

					if (rdbtnSituTem.isSelected() || rdbtnSituNaoTem.isSelected()) {
						q.setSituacao(rdbtnSituTem.isSelected() ? 2 : 1);
					}

					q.setMaxPessoas(Integer.valueOf(textCapacidade.getText().trim()));
					q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
					q.setTipoCama(textCama.getText());
					q.setTipoQuarto(Integer.valueOf(textTipo.getText()));
					dao.inserirQuarto(q);

					TelaSucesso s = new TelaSucesso("Quarto reservado com sucesso");
					s.setVisible(true);

					quarto.atualizarJTable();
				}
			});
			btnSalvar.setBorder(new RoundedBorder(Color.black, 10));
			btnSalvar.setBackground(new Color(117, 187, 68));
			panel_6.add(btnSalvar);
			dispose();
		} else {
			textCama.setText(quartoAtual.getTipoCama());
			textCapacidade.setText(String.valueOf(quartoAtual.getMaxPessoas()));
			textDiaria.setText(String.valueOf(quartoAtual.getPrecoDiaria()));
			textTipo.setText(String.valueOf(quartoAtual.getTipoQuarto()));

			// Frigobar
			if (quartoAtual.getFrigobar() == true) {
				rdbtnFrigoTem.setSelected(true);
			} else if (quartoAtual.getFrigobar() == false) {
				rdbtnFrigoNaoTem.setSelected(true);
			}

			// TV
			if (quartoAtual.getTV() == true) {
				rdbtnTvTem.setSelected(true);
			} else if (quartoAtual.getTV() == false) {
				rdbtnTvNaoTem.setSelected(true);
			}

			// Ar condicionado
			if (quartoAtual.getArCondicionado() == true) {
				rdbtnArTem.setSelected(true);
			} else if (quartoAtual.getArCondicionado() == false) {
				rdbtnArNaoTem.setSelected(true);
			}

			// Banheira
			if (quartoAtual.getBanheira() == true) {
				rdbtnBanTem.setSelected(true);
			} else if (quartoAtual.getBanheira() == false) {
				rdbtnBanNaoTem.setSelected(true);
			}

			if (quartoAtual.getManutencao().equals("1")) {
				rdbtnManuTem.setSelected(true);
			} else if (quartoAtual.getManutencao().equals("0")) {
				rdbtnManuNaoTem.setSelected(true);
			}
			if (quartoAtual.getSituacao() == 2) {
				rdbtnSituTem.setSelected(true);
			} else if (quartoAtual.getSituacao() == 1) {
				rdbtnSituNaoTem.setSelected(true);
			}
//						int IdQuarto = ListaQuartos.get(i).getIdQuarto();
//						int MaxPessoas = ListaQuartos.get(i).getMaxPessoas();
//						String TipoCama = ListaQuartos.get(i).getTipoCama();
//						String Manutencao = ListaQuartos.get(i).getManutencao();
//						Integer TipoQuarto = ListaQuartos.get(i).getTipoQuarto();
//						Boolean Frigobar = ListaQuartos.get(i).getFrigobar();
//						Boolean ArCondicionado = ListaQuartos.get(i).getArCondicionado();
//						Boolean Banheira = ListaQuartos.get(i).getBanheira();
//						Boolean TV = ListaQuartos.get(i).getTV();
//						Float PrecoDiaria = ListaQuartos.get(i).getPrecoDiaria();
			//
//						// Preenche os textfields com os dados recuperados
//						QuartoSelcionado.setIdQuarto(IdQuarto);
//						QuartoSelcionado.setMaxPessoas(MaxPessoas);
//						QuartoSelcionado.setTipoCama(TipoCama);
//						QuartoSelcionado.setManutencao(Manutencao);
//						QuartoSelcionado.setTipoQuarto(TipoQuarto);
//						QuartoSelcionado.setFrigobar(Frigobar);
//						QuartoSelcionado.setArCondicionado(ArCondicionado);
//						QuartoSelcionado.setBanheira(Banheira);
//						QuartoSelcionado.setTV(TV);
//						QuartoSelcionado.setPrecoDiaria(PrecoDiaria);

			DefaultIconButton btnAtualizar = new DefaultIconButton("Atualizar");
			
			 
			btnAtualizar.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Quartos q = new Quartos();
					QuartosDAO dao = QuartosDAO.getConexao();

					q.setIdQuarto(q.getIdQuarto());
					q.setMaxPessoas(Integer.valueOf(textCapacidade.getText().trim()));
//					q.setPrecoDiaria(Float.valueOf(textDiaria.getText()));
					q.setTipoCama(textCama.getText());
					q.setTipoQuarto(Integer.valueOf(textTipo.getText()));

					if (rdbtnArTem.isSelected() || rdbtnArNaoTem.isSelected()) {
						q.setArCondicionado(rdbtnArTem.isSelected() ? true : false);
					}
					if (rdbtnBanTem.isSelected() || rdbtnBanNaoTem.isSelected()) {
						q.setBanheira(rdbtnBanTem.isSelected() ? true : false);
					}
					if (rdbtnFrigoTem.isSelected() || rdbtnFrigoNaoTem.isSelected()) {
						q.setFrigobar(rdbtnFrigoTem.isSelected() ? true : false);
					}
					if (rdbtnTvTem.isSelected() || rdbtnTvNaoTem.isSelected()) {
						q.setTV(rdbtnTvTem.isSelected() ? true : false);
					}
					if (rdbtnManuTem.isSelected() || rdbtnManuNaoTem.isSelected()) {
						q.setManutencao(String.valueOf(rdbtnManuTem.isSelected() ? 1 : 0));
					}
					if (rdbtnSituTem.isSelected() || rdbtnSituNaoTem.isSelected()) {
						q.setSituacao(rdbtnSituTem.isSelected() ? 2 : 1);
					}
//						q.setArCondicionado(Boolean.valueOf(textAr.getText()));
//						q.setBanheira(Boolean.valueOf(textBanheira.getText()));
//						q.setFrigobar(Boolean.valueOf(textFrigobar.getText()));
//						q.setTV(Boolean.valueOf(textTv.getText()));
					dao.atualizarQuarto(q);
					quarto.atualizarJTable();
					 dispose();
				}
			});
		


		
		btnAtualizar.setBounds(173, 600, 136, 44);
		Principal.add(btnAtualizar);
		}
		
	}
}
