package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.Atividades.AtividadesDAO;
import controle.AtividadesHospedes.AtividadesHospedesDAO;
import controle.Funcionarios.FuncionariosDAO;
import controle.Hospedagens.HospedagensDAO;
import controle.Hospede.HospedeDAO;
import controle.Quartos.QuartosDAO;
import modelo.Atividades;
import modelo.AtividadesHospedes;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Hospedagens;
import modelo.Hospedes;
import modelo.Quartos;
import net.miginfocom.swing.MigLayout;
import utils.DefaultScreen;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import java.awt.Canvas;
import javax.swing.JComboBox;

public class Home extends DefaultScreen {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;
	HospedagensDAO HgDao = HospedagensDAO.getInstancia();
	HospedeDAO HDao = HospedeDAO.getInstancia();
	AtividadesDAO ADao = AtividadesDAO.getInstancia();
	QuartosDAO QDao = QuartosDAO.getConexao();
	FuncionariosDAO FDao = FuncionariosDAO.getConexao();
	ArrayList<Hospedagens> listaHospedagens;
	ArrayList<Hospedes> listaHospedes;
	ArrayList<Atividades> listaAtividades;
	ArrayList<Quartos> listaQuartos;
	ArrayList<Funcionarios> listaFuncionarios;
	private int diasSelecionados = 3; // Valor padrão inicial

	ArrayList<String> listaImagens = new ArrayList<String>(Arrays.asList("/visao/logoGrande.png", "/visao/Whatsapp.jpg",
			"/visao/Facebook.jpg", "/visao/instagram.png"));
	int imageIndex = 0;
	private JLabel lblNewLabel_9;
	LocalDate hoje = LocalDate.now();
	JPanel mostrarAtividades = new JPanel() {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
			g2d.dispose();
		}
	};

 

	public void loadAtividades() {
		 
		ArrayList<Atividades> listaAtividades = ADao.ListarAtividades();
		ArrayList<Atividades> atividadesProximas = new ArrayList<>();

		LocalDate limite = hoje.plusDays(diasSelecionados); // Usa diasSelecionados

		for (Atividades atividade : listaAtividades) {
			LocalDate dataAtividade = atividade.getData().toLocalDate();

			if (!dataAtividade.isBefore(hoje) && !dataAtividade.isAfter(limite)) {
				atividadesProximas.add(atividade);
			}
		}

		atividadesProximas.sort(Comparator.comparing(Atividades::getHorario));
		mostrarAtividades.setBounds(1313, 174, 272, 673);

		mostrarAtividades.removeAll();

		for (Atividades atividade : atividadesProximas) {
			JLabel labelAtividade = new JLabel(
					atividade.getNomeAtividade() + " - " + atividade.getData() + " - " + atividade.getHorario());
			labelAtividade.setFont(new Font("Arial", Font.BOLD, 14));
			mostrarAtividades.add(labelAtividade);
		}

		mostrarAtividades.revalidate();
		mostrarAtividades.repaint();
	}

	public Home() {
		super( );
		Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();

		loadAtividades();
		loadInfos();
		HgDao.AtualizarSituacao();

		mostrarAtividades.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		 
		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
 
		JPanel panel_5 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
				g2d.dispose();
			}
		};
		panel_5.setBounds(108, 41, 1096, 167);
		 
		Principal.setLayout(null);
		Principal.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 5, 0, 0));

		JLabel lblQuarto = new JLabel("0");
		lblQuarto.setVerticalAlignment(SwingConstants.BOTTOM);
		lblQuarto.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblQuarto.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblAtividade = new JLabel("0");
		lblAtividade.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAtividade.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblAtividade.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHospedes = new JLabel("0");
		lblHospedes.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHospedes.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblHospedes.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblFunc = new JLabel("");
		lblFunc.setVerticalAlignment(SwingConstants.BOTTOM);
		lblFunc.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblFunc.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblHospedagem = new JLabel("");
		lblHospedagem.setVerticalAlignment(SwingConstants.BOTTOM);
		lblHospedagem.setFont(new Font("Trebuchet MS", Font.PLAIN, 34));
		lblHospedagem.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_2 = new JLabel("Quartos ");
		lblNewLabel_7_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7 = new JLabel("Atividades");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_4 = new JLabel("Hospedes");
		lblNewLabel_7_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_4.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_3 = new JLabel("Funcionários");
		lblNewLabel_7_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_3.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_7_1 = new JLabel("Hospedagens");
		lblNewLabel_7_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_21 = new JLabel("Atividades nos proximos ");
		lblNewLabel_21.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_21.setBounds(1307, 120, 177, 43);
		Principal.add(lblNewLabel_21);

		Principal.add(mostrarAtividades);

 

		loadInfos();
		lblQuarto.setText(String.valueOf(listaQuartos.size()));
		lblAtividade.setText(String.valueOf(listaAtividades.size()));
		lblHospedes.setText(String.valueOf(listaHospedes.size()));
		lblFunc.setText(String.valueOf(listaFuncionarios.size()));
		lblHospedagem.setText(String.valueOf(listaHospedagens.size()));

		panel_5.add(lblFunc);
		panel_5.add(lblQuarto);
		panel_5.add(lblAtividade);
		panel_5.add(lblHospedagem);
		panel_5.add(lblHospedes);
		panel_5.add(lblNewLabel_7_3);
		panel_5.add(lblNewLabel_7_2);
		panel_5.add(lblNewLabel_7);
		panel_5.add(lblNewLabel_7_1);
		panel_5.add(lblNewLabel_7_4);

		JLabel lblNewLabel_10 = new JLabel(" ");
		lblNewLabel_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (imageIndex > 0) {
					imageIndex -= 1;
					updateImage();

				}
			}
		});
		lblNewLabel_10.setIcon(new ImageIcon(Home.class.getResource("/visao/arrowBack.png")));
		lblNewLabel_10.setBounds(119, 509, 57, 26);
		Principal.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("seta direita");
		lblNewLabel_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (imageIndex < listaImagens.size()) {
					imageIndex += 1;
					updateImage();
				}
			}
		});
		lblNewLabel_11.setBounds(1082, 524, 107, 20);
		Principal.add(lblNewLabel_11);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(186, 322, 863, 525);
		Principal.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 1, 0, 0));

		lblNewLabel_9 = new JLabel("");
		panel_6.add(lblNewLabel_9);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);

		JComboBox comboBoxDias = new JComboBox();
		comboBoxDias.setBounds(1494, 126, 107, 34);
		Principal.add(comboBoxDias);
		updateImage();

		comboBoxDias.addItem("3 dias");
		comboBoxDias.addItem("7 dias");
		comboBoxDias.addItem("15 dias");
		comboBoxDias.addItem("30 dias");
		comboBoxDias.addItem("60 dias");

		comboBoxDias.addActionListener(e -> {
			switch ((String) comboBoxDias.getSelectedItem()) {
			case "3 dias":
				diasSelecionados = 3;
				break;
			case "7 dias":
				diasSelecionados = 7;
				break;
			case "15 dias":
				diasSelecionados = 15;
				break;
			case "30 dias":
				diasSelecionados = 30;
				break;
			case "60 dias":
				diasSelecionados = 60;
				break;
			}
			loadAtividades();
		});
		setPrincipalPanel(Principal);
	}

	public void loadInfos() {
		listaHospedagens = HgDao.ListarHospedagens();
		listaHospedes = HDao.ListarHospedes();
		listaAtividades = ADao.ListarAtividades();
		listaQuartos = QDao.ListarQuartos();
		listaFuncionarios = FDao.ListarFuncionarios();

	}

	private void updateImage() {
		lblNewLabel_9.setIcon(new ImageIcon(Home.class.getResource(listaImagens.get(imageIndex))));
	}
	
}
