package visao;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controle.Servicos.ServicosDAO;
import modelo.CurrentFunc;
import modelo.Funcionarios;
import modelo.Servicos;
import net.miginfocom.swing.MigLayout;
import utils.DefaultIconButton;
import utils.DefaultModal;
import utils.DefaultScreen;

public class TelaServicos extends JFrame {

	private static final long serialVersionUID = 1L;
 
 	JLabel lblQuantMassagem;
	JLabel lblQuantAlmoco;
	JLabel lblQuantFrigobar;
	JLabel lblQuantSauna;
	JLabel lblQuantShow;
	JLabel lblQuantAluguel;
	JLabel lblQuantPasseio;
	JLabel lblQuantTirolesa;
	protected JPanel contentPane;
	protected JPanel BarraLateral;
	protected JPanel BarraSuperior;
	protected JPanel BarraInferior;
 
	Funcionarios Func = CurrentFunc.getInstance().getLoggedInFuncionario();
	 
	public TelaServicos() {
		 screen();

		ServicosDAO dao = ServicosDAO.getInstancia();

		Servicos Massagem = new Servicos("Massagem", 80.00);
		// preencher os atributos dos objetos, Id não vai mais precisar
		Servicos Frigobar = new Servicos("Frigobar", 50.00);
		Servicos Sauna = new Servicos("Sauna", 60.00);
		Servicos Show = new Servicos("Show", 100.00);
		Servicos AluguelBike = new Servicos("Aluguel de Bicicleta", 70.00);
		Servicos Passeio = new Servicos("Passeio", 70.00);
		Servicos Tirolesa = new Servicos("Tirolesa", 90.00);
		Servicos Almoco = new Servicos("Almoço", 30.00);

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,growx,aligny center");
		Principal.setLayout(new MigLayout("", "[17.00][10:n][165.00][461.00,grow][186.00,center][10:n]",
				"[30:30:30,top][63.00,center][14.00][56.00][center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][50,grow,center][]"));

		JLabel lblNewLabel_1 = new JLabel("Serviços");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 36));
		Principal.add(lblNewLabel_1, "flowy,cell 1 1 2 1,alignx center,aligny center");

		JSeparator separator_1 = new JSeparator();
		Principal.add(separator_1, "cell 1 3 5 1,growx,aligny center");

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(192, 192, 192));
		separator.setForeground(new Color(192, 192, 192));
		Principal.add(separator, "cell 1 1 2 1,growx,aligny baseline");

		DefaultIconButton panel_5 = new DefaultIconButton("Ir para o carrinho");
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Carrinho c = new Carrinho();
				c.setLocationRelativeTo(null);

				c.setVisible(true);
				atualizarLabelQuantidadeItens();

			}
		});
		
		Principal.add(panel_5, "cell 4 1,growx,aligny center");

		JPanel panel_8 = new JPanel();
		Principal.add(panel_8, "cell 2 4 3 1,growx,aligny center");
		panel_8.setLayout(new MigLayout("",
				"[10:20:80,grow,left][27px,grow,fill][27px,grow][20px,grow,fill][24px,grow,fill][25,grow,fill][grow]",
				"[14px]"));

		JLabel lblNewLabel_9 = new JLabel("Nome");
		lblNewLabel_9.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel_8.add(lblNewLabel_9, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_10 = new JLabel("Preço");
		lblNewLabel_10.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel_8.add(lblNewLabel_10, "cell 2 0,alignx left,aligny top");

		JLabel lblNewLabel_11 = new JLabel("Tipo");
		lblNewLabel_11.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel_8.add(lblNewLabel_11, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_12 = new JLabel("Local");
		lblNewLabel_12.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel_8.add(lblNewLabel_12, "cell 4 0,alignx left,aligny top");

		JLabel lblNewLabel_13 = new JLabel("Quantidade");
		lblNewLabel_13.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panel_8.add(lblNewLabel_13, "cell 5 0,alignx left,aligny top");

		JPanel panel_8_2 = new JPanel();
		Principal.add(panel_8_2, "cell 2 6 3 1,grow");
		panel_8_2.setLayout(new MigLayout("",
				"[20:n,grow][100px:40,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_21 = new JLabel("Almoço");
		lblNewLabel_21.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_21, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_22 = new JLabel("R$ 43,99");
		lblNewLabel_22.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_22, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_23 = new JLabel("Alimentação");
		lblNewLabel_23.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_23, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_24 = new JLabel("Restaurante");
		lblNewLabel_24.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_2.add(lblNewLabel_24, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_25 = new JLabel("");

		lblNewLabel_25.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_25.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_2.add(lblNewLabel_25, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantAlmoco = new JLabel("0");
		lblQuantAlmoco.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_1 = new DefaultIconButton("Adicionar");
		panel_6_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Almoco.setQuantidade(Integer.parseInt(lblQuantAlmoco.getText()));

				if (Almoco.getQuantidade() > 0) {
					dao.inserirServico(Almoco);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}

			}
		});
		panel_6_1.setBackground(new Color(117, 187, 68));
		panel_8_2.add(panel_6_1, "cell 10 0,growx,aligny center");

		JPanel panel_8_8 = new JPanel();
		Principal.add(panel_8_8, "cell 2 5 3 1,grow");
		panel_8_8.setLayout(new MigLayout("",
				"[20:n,grow,left][100px:27,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186,grow,left][186,grow,left]",
				"[14px,grow]"));

		JLabel lblNewLabel_70 = new JLabel("Massagem");
		lblNewLabel_70.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_70, "cell 1 0,alignx left,aligny center");

		JLabel lblPrecoMassagem = new JLabel("R$ 80,00");
		lblPrecoMassagem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_8.add(lblPrecoMassagem, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_72 = new JLabel("Lazer");
		lblNewLabel_72.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_72, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_73 = new JLabel("Bloco A - Sala 2");
		lblNewLabel_73.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_8.add(lblNewLabel_73, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_74 = new JLabel("");

		lblNewLabel_74.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_74.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_8.add(lblNewLabel_74, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantMassagem = new JLabel("0");
		lblQuantMassagem.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_8.add(lblQuantMassagem, "cell 9 0 2 1,alignx left,aligny center");

		JLabel lblNewLabel_76 = new JLabel("");
		lblNewLabel_76.setForeground(new Color(0, 255, 0));
		lblNewLabel_76.setBackground(new Color(0, 128, 0));

		lblNewLabel_76.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_76.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Add_square_light.png")));
		panel_8_8.add(lblNewLabel_76, "cell 9 0 2 1,alignx right,aligny center");

		DefaultIconButton panel_6 = new DefaultIconButton("Adicionar");
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Massagem.setQuantidade(Integer.parseInt(lblQuantMassagem.getText()));

				if (Massagem.getQuantidade() > 0) {
					dao.inserirServico(Massagem);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}

			}
		});
		panel_6.setBackground(new Color(117, 187, 68));
		panel_8_8.add(panel_6, "cell 10 0 2 1,growx,aligny center");

		JPanel panel_8_1 = new JPanel();
		Principal.add(panel_8_1, "cell 2 7 3 1,grow");
		panel_8_1.setLayout(new MigLayout("",
				"[20:n,grow][100px:27,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_14 = new JLabel("Frigobar");
		lblNewLabel_14.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_14, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_15 = new JLabel("R$ 60,00");
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_15, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_16 = new JLabel("Alimentação");
		lblNewLabel_16.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_16, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_17 = new JLabel("Quarto");
		lblNewLabel_17.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_1.add(lblNewLabel_17, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_18 = new JLabel("");

		lblNewLabel_18.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_18.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_1.add(lblNewLabel_18, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantFrigobar = new JLabel("0");
		lblQuantFrigobar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_2 = new DefaultIconButton("Adicionar");
		panel_6_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Frigobar.setQuantidade(Integer.parseInt(lblQuantFrigobar.getText()));
				if (Frigobar.getQuantidade() > 0) {
					dao.inserirServico(Frigobar);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}
			}
		});
		panel_6_2.setBackground(new Color(117, 187, 68));
		panel_8_1.add(panel_6_2, "cell 10 0,growx,aligny center");

		JPanel panel_8_4 = new JPanel();
		Principal.add(panel_8_4, "cell 2 8 3 1,grow");
		panel_8_4.setLayout(new MigLayout("",
				"[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_38 = new JLabel("Sauna");
		lblNewLabel_38.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_38, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_39 = new JLabel("R$40,00");
		lblNewLabel_39.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_39, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_40 = new JLabel("Lazer");
		lblNewLabel_40.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_40, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_41 = new JLabel("Bloco D - Vestiario");
		lblNewLabel_41.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_4.add(lblNewLabel_41, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_42 = new JLabel("");

		lblNewLabel_42.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_42.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_4.add(lblNewLabel_42, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantSauna = new JLabel("0");
		lblQuantSauna.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_3 = new DefaultIconButton("Adicionar");
		panel_6_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Sauna.setQuantidade(Integer.parseInt(lblQuantSauna.getText()));
				if (Sauna.getQuantidade() > 0) {
					dao.inserirServico(Sauna);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}
			}
		});
		panel_6_3.setBackground(new Color(117, 187, 68));
		panel_8_4.add(panel_6_3, "cell 10 0,growx,aligny center");

		JPanel panel_8_3 = new JPanel();
		Principal.add(panel_8_3, "cell 2 9 3 1,grow");
		panel_8_3.setLayout(new MigLayout("",
				"[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_30 = new JLabel("Show");
		lblNewLabel_30.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_30, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_31 = new JLabel("R$ 90,00");
		lblNewLabel_31.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_31, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_32 = new JLabel("Lazer");
		lblNewLabel_32.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_32, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_33 = new JLabel("Auditório");
		lblNewLabel_33.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_3.add(lblNewLabel_33, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_34 = new JLabel("");

		lblNewLabel_34.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_34.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_3.add(lblNewLabel_34, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantShow = new JLabel("0");
		lblQuantShow.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_4 = new DefaultIconButton("Adicionar");
		panel_6_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Show.setQuantidade(Integer.parseInt(lblQuantShow.getText()));
				if (Show.getQuantidade() > 0) {
					dao.inserirServico(Show);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);
					;
				}
			}
		});
		panel_6_4.setBackground(new Color(117, 187, 68));
		panel_8_3.add(panel_6_4, "cell 10 0,growx,aligny center");

		JPanel panel_8_7 = new JPanel();
		Principal.add(panel_8_7, "cell 2 10 3 1,grow");
		panel_8_7.setLayout(new MigLayout("",
				"[20:n,grow][100:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_62 = new JLabel("Aluguel de bicicletas");
		lblNewLabel_62.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_62, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_63 = new JLabel("R$ 30,00");
		lblNewLabel_63.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_63, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_64 = new JLabel("Esporte");
		lblNewLabel_64.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_64, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_65 = new JLabel("Bloco B - Sala 2");
		lblNewLabel_65.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_7.add(lblNewLabel_65, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_66 = new JLabel("");

		lblNewLabel_66.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_66.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_7.add(lblNewLabel_66, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantAluguel = new JLabel("0");
		lblQuantAluguel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_5 = new DefaultIconButton("Adicionar");
		panel_6_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AluguelBike.setQuantidade(Integer.parseInt(lblQuantAluguel.getText()));
				if (AluguelBike.getQuantidade() > 0) {
					dao.inserirServico(AluguelBike);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}
			}
		});
		panel_6_5.setBackground(new Color(117, 187, 68));
		panel_8_7.add(panel_6_5, "cell 10 0,growx,aligny center");

		JPanel panel_8_9 = new JPanel();
		Principal.add(panel_8_9, "cell 2 11 3 1,grow");
		panel_8_9.setLayout(new MigLayout("",
				"[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_78 = new JLabel("Passeio Turistico");
		lblNewLabel_78.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_78, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_79 = new JLabel("R$ 20,00");
		lblNewLabel_79.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_79, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_80 = new JLabel("Lazer");
		lblNewLabel_80.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_80, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_81 = new JLabel("Mirante");
		lblNewLabel_81.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_9.add(lblNewLabel_81, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_82 = new JLabel("");

		lblNewLabel_82.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_82.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_9.add(lblNewLabel_82, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantPasseio = new JLabel("0");
		lblQuantPasseio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_6 = new DefaultIconButton("Adicionar");
		panel_6_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Passeio.setQuantidade(Integer.parseInt(lblQuantPasseio.getText()));
				if (Passeio.getQuantidade() > 0) {
					dao.inserirServico(Passeio);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}
			}
		});
		panel_6_6.setBackground(new Color(117, 187, 68));
		panel_8_9.add(panel_6_6, "cell 10 0,growx,aligny center");

		JPanel panel_8_10 = new JPanel();
		Principal.add(panel_8_10, "cell 2 12 3 1,grow");
		panel_8_10.setLayout(new MigLayout("",
				"[20:n,grow][100px:30,grow,fill][40:10][60:27,grow][80:40][70:20,grow,fill][50:n][110:24,grow,fill][50:n][186.00,grow,left][186.00,grow,left]",
				"[14px]"));

		JLabel lblNewLabel_86 = new JLabel("Tirolesa");
		lblNewLabel_86.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_86, "cell 1 0,alignx left,aligny center");

		JLabel lblNewLabel_87 = new JLabel("R$ 80,00");
		lblNewLabel_87.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_87, "cell 3 0,alignx left,aligny center");

		JLabel lblNewLabel_88 = new JLabel("Esporte");
		lblNewLabel_88.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_88, "cell 5 0,alignx left,aligny center");

		JLabel lblNewLabel_89 = new JLabel("Bloco C");
		lblNewLabel_89.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		panel_8_10.add(lblNewLabel_89, "cell 7 0,alignx left,aligny center");

		JLabel lblNewLabel_90 = new JLabel("");

		lblNewLabel_90.setFont(new Font("Dialog", Font.PLAIN, 16));
		lblNewLabel_90.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Group 8756.png")));
		panel_8_10.add(lblNewLabel_90, "flowx,cell 9 0 2 1,alignx left,aligny center");

		lblQuantTirolesa = new JLabel("0");
		lblQuantTirolesa.setFont(new Font("Segoe UI", Font.PLAIN, 16));
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

		DefaultIconButton panel_6_7 = new DefaultIconButton("Adicionar");
		panel_6_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Tirolesa.setQuantidade(Integer.parseInt(lblQuantTirolesa.getText()));
				if (Tirolesa.getQuantidade() > 0) {
					dao.inserirServico(Tirolesa);
					TelaSucesso c = new TelaSucesso("Adicionado com sucesso");
					c.setVisible(true);

				}
			}
		});
		panel_6_7.setBackground(new Color(117, 187, 68));
		panel_8_10.add(panel_6_7, "cell 10 0,growx,aligny center");

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
		contentPane.add(Principal, "cell 1 1,grow");

 	}
	public void screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1452, 756);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("insets 0, gap 0", "[200px:1064px:200][grow]", "[73:69px:73,grow,center][560px,grow][52px]"));

		DefaultModal BarraLateral = new DefaultModal();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_10);
		panel_10.setLayout(new MigLayout("", "[93px]", "[32px,grow]"));

		JLabel lblHome = new JLabel("Home");
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		panel_10.add(lblHome, "cell 0 0,alignx left,aligny center");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Home TelaHome = new Home();
				TelaHome.setVisible(true);
				TelaHome.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblHome.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Home.jpg")));

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_3);
		panel_3.setLayout(new MigLayout("", "[163px,grow,fill]", "[32px,grow,fill]"));

		JLabel lblNewLabel_15 = new JLabel("Funcionários");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_15, "cell 0 0,alignx left,aligny center");
		lblNewLabel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminFuncionarios TelaAdm = new AdminFuncionarios();
				TelaAdm.setVisible(true);
				TelaAdm.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_15.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/funcionarios.png")));
		lblNewLabel_15.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_5);
		panel_5.setLayout(new MigLayout("", "[123px,grow]", "[32px,grow]"));

		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblHospede, "cell 0 0,alignx left,aligny center");
		lblHospede.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeHospedes Chama = new TelaDeHospedes();
				Chama.setVisible(true);
				Chama.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblHospede.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblHospede.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Hospede.jpg")));

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_6);
		panel_6.setLayout(new MigLayout("", "[112px,grow]", "[32px,grow]"));

		JLabel lblNewLabel_19 = new JLabel("Quartos");
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.LEFT);
		panel_6.add(lblNewLabel_19, "cell 0 0,alignx left,aligny center");
		lblNewLabel_19.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Quartos2 q2 = new Quartos2();
				q2.setVisible(true);
				q2.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();

			}
		});
		lblNewLabel_19.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Quartos.jpg")));
		lblNewLabel_19.setFont(new Font("Segoe UI", Font.PLAIN, 24));

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_7);
		panel_7.setLayout(new MigLayout("", "[120px,grow]", "[32px,grow]"));

		JLabel lblQuartos = new JLabel("Reservas");
		lblQuartos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_7.add(lblQuartos, "cell 0 0,alignx left,aligny center");
		lblQuartos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeAcomodacoes TelaAco = new TelaDeAcomodacoes();
				TelaAco.setVisible(true);
				TelaAco.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblQuartos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblQuartos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/reserva.png")));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_8);
		panel_8.setLayout(new MigLayout("", "[115px,grow]", "[32px,grow]"));

		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setHorizontalAlignment(SwingConstants.LEFT);
		panel_8.add(lblServicos, "cell 0 0,alignx left,aligny center");
		lblServicos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaServicos TelaServ = new TelaServicos();
				TelaServ.setVisible(true);
				TelaServ.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblServicos.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblServicos.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Servicos.jpg")));

		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel_9);
		panel_9.setLayout(new MigLayout("", "[138px,grow]", "[32px,grow]"));

		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setHorizontalAlignment(SwingConstants.LEFT);
		panel_9.add(lblAtividades, "cell 0 0,alignx left,aligny center");
		lblAtividades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaAtividades TelaAtiv = new TelaAtividades();
				TelaAtiv.setVisible(true);
				TelaAtiv.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});
		lblAtividades.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		lblAtividades.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Atividades.jpg")));

		JLabel label = new JLabel("");
		BarraLateral.add(label);
		
		JLabel lblNewLabel_3 = new JLabel("");
		BarraLateral.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("");
		BarraLateral.add(lblNewLabel_4);
		
		JLabel lblNewLabel = new JLabel("");
		BarraLateral.add(lblNewLabel);

		JLabel label_1 = new JLabel("");
		BarraLateral.add(label_1);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel);
		panel.setLayout(new MigLayout("", "[][167.00,grow,center]", "[32.00,grow,center]"));

		JLabel lblNewLabel_5 = new JLabel("Sair");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login novoLogin = new Login();
				novoLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
				novoLogin.setVisible(true);
				dispose();

			}
		});
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Sair.png")));
		panel.add(lblNewLabel_5, "cell 0 0 2 1,alignx center,aligny center");

		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(203, 167, 58));

		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior
				.setLayout(new MigLayout("", "[200:209.00px:200][grow,center][50:40px:50,right]", "[29.00px,center]"));
		ImageIcon logoIcon = new ImageIcon(Quartos2.class.getResource("/visao/logoMedia.png"));

		Image logoImage = logoIcon.getImage().getScaledInstance(190, 55, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		logoIcon = new ImageIcon(logoImage);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_4, "cell 0 0,alignx left,aligny center");
		panel_4.setLayout(new MigLayout("", "[251.00px]", "[15][25]"));

		JLabel lblNewLabel_1 = new JLabel("Bem-Vindo(a),");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel_1, "cell 0 0,alignx left,aligny bottom");

		JLabel lblNome = new JLabel(Func.getNome());
		lblNome.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNome, "cell 0 1,alignx left,aligny top");
		lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 16));

		JPanel panel_11 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_11.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		BarraSuperior.add(panel_11, "flowx,cell 1 0");
		JLabel lblLogo = new JLabel("");
		lblLogo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		panel_11.add(lblLogo);
		lblLogo.setIcon(logoIcon);
		panel_11.setBackground(new Color(203, 167, 58));
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(203, 167, 58));
		BarraSuperior.add(panel_2, "cell 1 0,alignx center,aligny center");
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		BarraSuperior.add(lblNewLabel_2, "cell 2 0,alignx center");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Conta telaConta = new Conta();
				telaConta.setVisible(true);
				telaConta.setExtendedState(JFrame.MAXIMIZED_BOTH);

				dispose();
			}
		});

		ImageIcon contaIcon = new ImageIcon(Quartos2.class.getResource("/visao/contaAppBar.png"));
		Image contaImage = contaIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); // Ajuste o tamanho
																								// conforme necessário
		contaIcon = new ImageIcon(contaImage);
		lblNewLabel_2.setIcon(contaIcon);
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
		lblInstagram.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URL("https://www.instagram.com/stellar_.hotel?igsh=bDl2dmkwY2MzNHFy").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(lblInstagram, "cell 0 0");
		lblInstagram.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/instagram.png")));

		JLabel lblFacebook = new JLabel("");
		panel_1.add(lblFacebook, "cell 1 0");
		lblFacebook.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/Facebook.jpg")));

		JLabel lblTwitter = new JLabel("");
		lblTwitter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Desktop.getDesktop()
							.browse(new URL("https://x.com/Stellar1933323?t=sMKnmdFjz2z29kZNNmOY3g&s=09").toURI());
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel_1.add(lblTwitter, "cell 3 0");
		lblTwitter.setIcon(new ImageIcon(Quartos2.class.getResource("/visao/twitter.jpg")));
	}

	public void adicionarQuant(JLabel label) {
		String texto = label.getText();
		int valor = Integer.parseInt(texto);
		label.setText(String.valueOf(valor + 1));
	}

	public void diminuirQuant(JLabel label) {

		String texto = label.getText();
		int valor = Integer.parseInt(texto);
		if (valor > 0)
			label.setText(String.valueOf(valor - 1));

	}

	private void atualizarLabelQuantidadeItens() {
		lblQuantMassagem.setText("0");
		lblQuantAlmoco.setText("0");
		lblQuantFrigobar.setText("0");
		lblQuantSauna.setText("0");
		lblQuantShow.setText("0");
		lblQuantAluguel.setText("0");
		lblQuantPasseio.setText("0");
		lblQuantTirolesa.setText("0");

	}

}
