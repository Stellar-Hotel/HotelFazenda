package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[181.00][830.00,grow]", "[47.00,grow][571.00,grow][40.00,grow]"));
		
		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1 1 2,grow");
		BarraLateral.setLayout(new MigLayout("", "[131px,grow]", "[40px][40px][40px][40px][40px][251.00][98.00,grow]"));
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome, "cell 0 0,grow");
		
		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede, "cell 0 1,grow");
		
		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades, "cell 0 2,grow");
		
		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos, "cell 0 3,grow");
		
		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos, "cell 0 4,grow");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		BarraLateral.add(panel, "cell 0 6,grow");
		panel.setLayout(new MigLayout("", "[][]", "[][30.00][29.00][32.00]"));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Avatar.jpg")));
		panel.add(lblNewLabel_4, "cell 0 0 1 3");
		
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
		BarraSuperior.setLayout(new MigLayout("", "[63.00px][117.00][219.00][556.00][42.00]", "[14px,grow]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		BarraSuperior.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_6 = new JLabel("");
		BarraSuperior.add(lblNewLabel_6, "flowx,cell 2 0");
		lblNewLabel_6.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/search.png")));
		
		txtPesquisa = new JTextField();
		BarraSuperior.add(txtPesquisa, "cell 2 0,growx");
		txtPesquisa.setBackground(new Color(245, 245, 245));
		txtPesquisa.setText("Search");
		txtPesquisa.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/SinoNotificacao.jpg")));
		BarraSuperior.add(lblNewLabel_8, "cell 4 0,alignx center,aligny center");
		
		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		Principal.setLayout(new MigLayout("", "[][][]", "[][][][][][][][][322.00]"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/logoGrande.png")));
		Principal.add(lblNewLabel_1, "cell 0 0 3 9");
		
		JPanel BarraInferior = new JPanel();
		BarraInferior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraInferior, "cell 1 2,grow");
		BarraInferior.setLayout(new MigLayout("", "[][679.00,grow,center][center][center][center][]", "[42.00,grow,center]"));
		
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
	}
}
