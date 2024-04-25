package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controle.Servicos.ServicosDAO;
import controle.ServicosConsumidos.ServicosConsumidosDAO;
import modelo.Hospedes;
import modelo.Servicos;
import modelo.ServicosConsumidos;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.util.ArrayList;

public class TelaServicos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					TelaServicos frame = new TelaServicos();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);// abre a tela em full screen
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaServicos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		ArrayList<Servicos> lista=new ArrayList<Servicos>();
		Servicos Massagem=new Servicos("Massagem", 80.00);
		//preencher os atributos dos objetos, Id não vai mais precisar
		Servicos Frigobar=new Servicos("Frigobar", 50.00);
		Servicos Sauna=new Servicos("Sauna", 60.00);
		Servicos Show=new Servicos("Show", 100.00);
		Servicos AluguelBike=new Servicos("Aluguel de Bicicleta", 70.00);
		Servicos Passeio=new Servicos("Passeio", 70.00);
		Servicos Tirolesa=new Servicos("Tirolesa", 90.00);
		Servicos Almoco = new Servicos("Almoço", 30.00);

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[200px:200px:200px][830.00,grow]",
				"[40px:49.00px:40px][571.00,grow,fill][60px:60px:60px]"));

		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]",
				"[20px:20px:20px][40px][40px][40px][40px][40px][251.00,grow][98.00]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 1,grow");

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblHospede.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 2,grow");

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblAtividades.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 3,grow");

		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblQuartos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 4,grow");

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblServicos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 5,grow");

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 7,growx,aligny baseline");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3,alignx center");

		JLabel lblNewLabel_2 = new JLabel("Erik Roncaglio");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblNewLabel_2, "cell 1 1,aligny bottom");

		JLabel lblNewLabel_3 = new JLabel("erikroncaglio@gmail.com");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel.add(lblNewLabel_3, "cell 1 2,aligny top");

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 3 2 1,alignx center,aligny top");

		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior.setLayout(new MigLayout("",
				"[40px:54.00px:40px][150.00][300px:360.00px:300px][grow][40px:40px:40px,right]", "[29.00px]"));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_3, "cell 0 0,grow");
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 0, 33, 31);
		lblNewLabel.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/logo.png")));
		panel_3.add(lblNewLabel);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_4, "cell 2 0,grow");
		panel_4.setLayout(new MigLayout("", "[20px][251.00px]", "[21px]"));

		JLabel lblNewLabel_6 = new JLabel("");
		panel_4.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
		lblNewLabel_6.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/search.png")));

		txtPesquisa = new JTextField();
		panel_4.add(txtPesquisa, "cell 1 0,growx,aligny top");
		txtPesquisa.setBackground(new Color(245, 245, 245));
		txtPesquisa.setText("Search");
		txtPesquisa.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		BarraSuperior.add(panel_2, "cell 4 0,grow");

		JLabel lblNewLabel_8 = new JLabel("");
		panel_2.add(lblNewLabel_8);
		lblNewLabel_8.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/SinoNotificacao.jpg")));

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,growx,aligny center");
		Principal.setLayout(new MigLayout("", "[17.00][10:n][165.00][461.00,grow][186.00,center][10:n]", "[30:30:30,top][63.00,center][14.00][56.00][center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][]"));

		JLabel lblNewLabel_1 = new JLabel("Serviços");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 36));
		Principal.add(lblNewLabel_1, "flowy,cell 1 1 2 1,alignx center,aligny center");

		JSeparator separator_1 = new JSeparator();
		Principal.add(separator_1, "cell 1 3 5 1,growx,aligny center");

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(192, 192, 192));
		separator.setForeground(new Color(192, 192, 192));
		Principal.add(separator, "cell 1 1 2 1,growx,aligny baseline");

		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				
//				ServicosConsumidosDAO dao = ServicosConsumidosDAO.getInstancia();
//				ServicosConsumidos serv = new ServicosConsumidos();
				
//				serv.getHospede()
				
//				dao.inserirServicoConsumido(serv);
				
				
				
				
				Carrinho c = new Carrinho(lista);
				c.setVisible(true);
				dispose();
				
			}
		});
		panel_5.setBackground(new Color(117, 187, 68));
		Principal.add(panel_5, "cell 4 1,growx,aligny center");
		
		JLabel lblNewLabel_19 = new JLabel("Ir para o Carrinho");
		panel_5.add(lblNewLabel_19);
		lblNewLabel_19.setForeground(new Color(255, 255, 255));
		lblNewLabel_19.setFont(new Font("Times New Roman", Font.PLAIN, 22));

		JPanel panel_8 = new JPanel();
		Principal.add(panel_8, "cell 2 4 3 1,growx,aligny center");
		panel_8.setLayout(new MigLayout("", "[10:20:80,grow,left][27px,grow,fill][27px,grow][20px,grow,fill][24px,grow,fill][25,grow,fill][grow]", "[14px]"));

		JLabel lblNewLabel_9 = new JLabel("Nome");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_8.add(lblNewLabel_9, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_10 = new JLabel("Preço");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_8.add(lblNewLabel_10, "cell 2 0,alignx left,aligny top");

		JLabel lblNewLabel_11 = new JLabel("Tipo");
		lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_8.add(lblNewLabel_11, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_12 = new JLabel("Local");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_8.add(lblNewLabel_12, "cell 4 0,alignx left,aligny top");

		JLabel lblNewLabel_13 = new JLabel("Quantidade");
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.BOLD, 16));
		panel_8.add(lblNewLabel_13, "cell 5 0,alignx left,aligny top");

		JPanel panel_8_2 = new JPanel();
		Principal.add(panel_8_2, "cell 2 6 3 1,grow");
		panel_8_2.setLayout(new MigLayout("", "[20:n,grow][100px:40,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_21 = new JLabel("Almoço");
		lblNewLabel_21.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_21, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_22 = new JLabel("R$ 43,99");
		lblNewLabel_22.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_22, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_23 = new JLabel("Alimentação");
		lblNewLabel_23.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_23, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_24 = new JLabel("Restaurante");
		lblNewLabel_24.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_24, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_25 = new JLabel("");
		
		lblNewLabel_25.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_25.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_2.add(lblNewLabel_25, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantAlmoco = new JLabel("0");
		lblQuantAlmoco.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_2.add(lblQuantAlmoco, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_27 = new JLabel("");
		lblNewLabel_27.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantAlmoco);
			}
		});
		lblNewLabel_27.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_27.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_2.add(lblNewLabel_27, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_1 = new JPanel();
		panel_6_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Almoco.setQuantidade(Integer.parseInt(lblQuantAlmoco.getText()));
				
				lista.add(Almoco);
			}
		});
		panel_6_1.setBackground(new Color(117, 187, 68));
		panel_8_2.add(panel_6_1, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_1 = new JLabel("Adicionar");
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_1.add(lblNewLabel_7_1);

		JPanel panel_8_8 = new JPanel();
		Principal.add(panel_8_8, "cell 2 5 3 1,grow");
		panel_8_8.setLayout(new MigLayout("", "[20:n,grow,left][100px:27,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186,grow,left][186,grow,left]", "[14px,grow]"));

		JLabel lblNewLabel_70 = new JLabel("Massagem");
		lblNewLabel_70.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_70, "cell 1 0,alignx left,aligny center");

		JLabel lblPrecoMassagem = new JLabel("R$ 80,00");
		lblPrecoMassagem.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_8.add(lblPrecoMassagem, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_72 = new JLabel("Lazer");
		lblNewLabel_72.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_72, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_73 = new JLabel("Bloco A - Sala 2");
		lblNewLabel_73.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_73, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_74 = new JLabel("");
		
		lblNewLabel_74.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_74.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_8.add(lblNewLabel_74, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantMassagem = new JLabel("0");
		lblQuantMassagem.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_8.add(lblQuantMassagem, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_76 = new JLabel("");
		lblNewLabel_76.setForeground(new Color(0, 255, 0));
		lblNewLabel_76.setBackground(new Color(0, 128, 0));
	
		lblNewLabel_76.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_76.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_8.add(lblNewLabel_76, "cell 9 0 2 1,alignx right,aligny center");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(117, 187, 68));
		panel_8_8.add(panel_6, "cell 10 0 2 1,growx,aligny center");
		
		JLabel lblNewLabel_7 = new JLabel("Adicionar");
		lblNewLabel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				Massagem.setQuantidade(Integer.parseInt(lblQuantMassagem.getText()));
				
				lista.add(Massagem);
				
				
//				
				
			}
		});
		panel_6.add(lblNewLabel_7);
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JPanel panel_8_1 = new JPanel();
		Principal.add(panel_8_1, "cell 2 7 3 1,grow");
		panel_8_1.setLayout(new MigLayout("", "[20:n,grow][100px:27,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186,grow,left]", "[14px]"));

		JLabel lblNewLabel_14 = new JLabel("Frigobar");
		lblNewLabel_14.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_14, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_15 = new JLabel("R$ 60,00");
		lblNewLabel_15.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_15, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_16 = new JLabel("Alimentação");
		lblNewLabel_16.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_16, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_17 = new JLabel("Quarto");
		lblNewLabel_17.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_17, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_18 = new JLabel("");
	
		lblNewLabel_18.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_18.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_1.add(lblNewLabel_18, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantFrigobar = new JLabel("0");
		lblQuantFrigobar.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_1.add(lblQuantFrigobar, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_20 = new JLabel("");
		lblNewLabel_20.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantFrigobar);
			}
		});
		lblNewLabel_20.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_20.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_1.add(lblNewLabel_20, "cell 9 0 2 1,alignx right,aligny center");
		lblNewLabel_18.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		lblQuantFrigobar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_20.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		
		JPanel panel_6_2 = new JPanel();
		panel_6_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
Almoco.setQuantidade(Integer.parseInt(lblQuantAlmoco.getText()));
				
				lista.add(Almoco);
			}
		});
		panel_6_2.setBackground(new Color(117, 187, 68));
		panel_8_1.add(panel_6_2, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_2 = new JLabel("Adicionar");
		lblNewLabel_7_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
Frigobar.setQuantidade(Integer.parseInt(lblQuantFrigobar.getText()));
				
				lista.add(Frigobar);
			}
		});
		panel_6_2.add(lblNewLabel_7_2);
		lblNewLabel_7_2.setForeground(Color.WHITE);
		lblNewLabel_7_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JPanel panel_8_4 = new JPanel();
		Principal.add(panel_8_4, "cell 2 8 3 1,grow");
		panel_8_4.setLayout(new MigLayout("", "[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_38 = new JLabel("Sauna");
		lblNewLabel_38.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_38, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_39 = new JLabel("R$40,00");
		lblNewLabel_39.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_39, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_40 = new JLabel("Lazer");
		lblNewLabel_40.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_40, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Bloco D - Vestiario");
		lblNewLabel_41.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_41, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_42 = new JLabel("");
		
		lblNewLabel_42.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_42.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_4.add(lblNewLabel_42, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantSauna = new JLabel("0");
		lblQuantSauna.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_4.add(lblQuantSauna, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_44 = new JLabel("");
		lblNewLabel_44.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantSauna);
			}
		});
		lblNewLabel_44.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_44.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_4.add(lblNewLabel_44, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_3 = new JPanel();
		panel_6_3.setBackground(new Color(117, 187, 68));
		panel_8_4.add(panel_6_3, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_3 = new JLabel("Adicionar");
		lblNewLabel_7_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
Sauna.setQuantidade(Integer.parseInt(lblQuantSauna.getText()));
				
				lista.add(Sauna);
			}
		});
		lblNewLabel_7_3.setForeground(Color.WHITE);
		lblNewLabel_7_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_3.add(lblNewLabel_7_3);

		JPanel panel_8_3 = new JPanel();
		Principal.add(panel_8_3, "cell 2 9 3 1,grow");
		panel_8_3.setLayout(new MigLayout("", "[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_30 = new JLabel("Show");
		lblNewLabel_30.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_30, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_31 = new JLabel("R$ 90,00");
		lblNewLabel_31.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_31, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_32 = new JLabel("Lazer");
		lblNewLabel_32.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_32, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_33 = new JLabel("Auditório");
		lblNewLabel_33.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_33, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_34 = new JLabel("");
	
		lblNewLabel_34.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_34.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_3.add(lblNewLabel_34, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantShow = new JLabel("0");
		lblQuantShow.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_3.add(lblQuantShow, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_36 = new JLabel("");
		lblNewLabel_36.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantShow);
			}
		});
		lblNewLabel_36.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_36.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_3.add(lblNewLabel_36, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_4 = new JPanel();
		panel_6_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
Show.setQuantidade(Integer.parseInt(lblQuantShow.getText()));
				
				lista.add(Show);
			}
		});
		panel_6_4.setBackground(new Color(117, 187, 68));
		panel_8_3.add(panel_6_4, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_4 = new JLabel("Adicionar");
		lblNewLabel_7_4.setForeground(Color.WHITE);
		lblNewLabel_7_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_4.add(lblNewLabel_7_4);

		JPanel panel_8_7 = new JPanel();
		Principal.add(panel_8_7, "cell 2 10 3 1,grow");
		panel_8_7.setLayout(new MigLayout("", "[20:n,grow][100:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_62 = new JLabel("Aluguel de bicicletas");
		lblNewLabel_62.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_62, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_63 = new JLabel("R$ 30,00");
		lblNewLabel_63.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_63, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_64 = new JLabel("Esporte");
		lblNewLabel_64.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_64, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_65 = new JLabel("Bloco B - Sala 2");
		lblNewLabel_65.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_65, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_66 = new JLabel("");
		
		lblNewLabel_66.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_66.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_7.add(lblNewLabel_66, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantAluguel = new JLabel("0");
		lblQuantAluguel.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_7.add(lblQuantAluguel, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_68 = new JLabel("");
		lblNewLabel_68.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantAluguel);
			}
		});
		lblNewLabel_68.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_68.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_7.add(lblNewLabel_68, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_5 = new JPanel();
		panel_6_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AluguelBike.setQuantidade(Integer.parseInt(lblQuantAluguel.getText()));
				
				lista.add(AluguelBike);
			}
		});
		panel_6_5.setBackground(new Color(117, 187, 68));
		panel_8_7.add(panel_6_5, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_5 = new JLabel("Adicionar");
		lblNewLabel_7_5.setForeground(Color.WHITE);
		lblNewLabel_7_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_5.add(lblNewLabel_7_5);

		JPanel panel_8_9 = new JPanel();
		Principal.add(panel_8_9, "cell 2 11 3 1,grow");
		panel_8_9.setLayout(new MigLayout("", "[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_78 = new JLabel("Passeio Turistico");
		lblNewLabel_78.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_78, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_79 = new JLabel("R$ 20,00");
		lblNewLabel_79.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_79, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_80 = new JLabel("Lazer");
		lblNewLabel_80.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_80, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_81 = new JLabel("Mirante");
		lblNewLabel_81.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_81, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_82 = new JLabel("");
		
		lblNewLabel_82.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_82.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_9.add(lblNewLabel_82, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantPasseio = new JLabel("0");
		lblQuantPasseio.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_9.add(lblQuantPasseio, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_84 = new JLabel("");
		lblNewLabel_84.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantPasseio);
			}
		});
		lblNewLabel_84.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_84.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_9.add(lblNewLabel_84, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_6 = new JPanel();
		panel_6_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Passeio.setQuantidade(Integer.parseInt(lblQuantPasseio.getText()));
				
				lista.add(Passeio);
				
			}
		});
		panel_6_6.setBackground(new Color(117, 187, 68));
		panel_8_9.add(panel_6_6, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_6 = new JLabel("Adicionar");
		lblNewLabel_7_6.setForeground(Color.WHITE);
		lblNewLabel_7_6.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_6.add(lblNewLabel_7_6);

		JPanel panel_8_10 = new JPanel();
		Principal.add(panel_8_10, "cell 2 12 3 1,grow");
		panel_8_10.setLayout(new MigLayout("", "[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]", "[14px]"));

		JLabel lblNewLabel_86 = new JLabel("Tirolesa");
		lblNewLabel_86.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_86, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_87 = new JLabel("R$ 80,00");
		lblNewLabel_87.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_87, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_88 = new JLabel("Esporte");
		lblNewLabel_88.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_88, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_89 = new JLabel("Bloco C");
		lblNewLabel_89.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_89, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_90 = new JLabel("");
		
		lblNewLabel_90.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_90.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_10.add(lblNewLabel_90, "flowx,cell 9 0 2 1,alignx left,aligny center");

		JLabel lblQuantTirolesa = new JLabel("0");
		lblQuantTirolesa.setFont(new Font("Dialog", Font.PLAIN, 16));
		panel_8_10.add(lblQuantTirolesa, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_92 = new JLabel("");
		lblNewLabel_92.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adicionarQuant(lblQuantTirolesa);
			}
		});
		lblNewLabel_92.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_92.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_10.add(lblNewLabel_92, "cell 9 0 2 1,alignx left,aligny center");
		
		JPanel panel_6_7 = new JPanel();
		panel_6_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				Tirolesa.setQuantidade(Integer.parseInt(lblQuantTirolesa.getText()));
				
				lista.add(Tirolesa);
				
			}
		});
		panel_6_7.setBackground(new Color(117, 187, 68));
		panel_8_10.add(panel_6_7, "cell 10 0,growx,aligny center");
		
		JLabel lblNewLabel_7_7 = new JLabel("Adicionar");
		lblNewLabel_7_7.setForeground(Color.WHITE);
		lblNewLabel_7_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		panel_6_7.add(lblNewLabel_7_7);

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
		panel_1.add(lblInstagram, "cell 0 0");
		lblInstagram.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Facebook.jpg")));

		JLabel lblWhatsapp = new JLabel("");
		panel_1.add(lblWhatsapp, "cell 2 0");
		lblWhatsapp.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Whatsapp.jpg")));

		JLabel lblTwitter = new JLabel("");
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/twitter.jpg")));
		lblNewLabel_25.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantAlmoco);
			}
		});
		
		lblNewLabel_74.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantMassagem);
			}
		});
		lblNewLabel_76.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			adicionarQuant(lblQuantMassagem);
			}
		});
		
		lblNewLabel_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantFrigobar);
			}
			
		});
		lblNewLabel_42.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantSauna);
			}
		});
		lblNewLabel_34.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantShow);
			}
		});
		lblNewLabel_66.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantAluguel);
			}
		});
		lblNewLabel_82.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantPasseio);
			}
		});
		
		lblNewLabel_90.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				diminuirQuant(lblQuantTirolesa);
			}
		});
	}
	public void adicionarQuant(JLabel label) {
	    String texto = label.getText();
	    int valor = Integer.parseInt(texto);
	    label.setText(String.valueOf(valor + 1));
	}
	public void diminuirQuant(JLabel label) {
		
	    String texto = label.getText();
	    int valor = Integer.parseInt(texto);
	    if(valor>0) 
	    label.setText(String.valueOf(valor - 1));
	    
	}
}
