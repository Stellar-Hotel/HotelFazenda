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

public class TelaServicos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		contentPane.setLayout(new MigLayout("", "[130.00,grow][918.00,grow]", "[30.00,grow][536.00,grow][50px:50px:50px,grow]"));
		
		JPanel BarraLateral = new JPanel();
		BarraLateral.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraLateral, "cell 0 1,grow");
		BarraLateral.setLayout(new GridLayout(15, 0, 0, 0));
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setBackground(new Color(0, 204, 0));
		lblHome.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Home.jpg")));
		BarraLateral.add(lblHome);
		
		JLabel lblHospede = new JLabel("Hospede");
		lblHospede.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Hospede.jpg")));
		BarraLateral.add(lblHospede);
		
		JLabel lblAtividades = new JLabel("Atividades");
		lblAtividades.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Atividades.jpg")));
		BarraLateral.add(lblAtividades);
		
		JLabel lblQuartos = new JLabel("Quartos");
		lblQuartos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Quartos.jpg")));
		BarraLateral.add(lblQuartos);
		
		JLabel lblServicos = new JLabel("Serviços");
		lblServicos.setIcon(new ImageIcon(TelaServicos.class.getResource("/visao/Servicos.jpg")));
		BarraLateral.add(lblServicos);
		
		JPanel BarraSuperior = new JPanel();
		BarraSuperior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraSuperior, "cell 0 0 2 1,grow");
		BarraSuperior.setLayout(new MigLayout("", "[46px]", "[14px]"));
		
		JLabel lblNewLabel = new JLabel("New label");
		BarraSuperior.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
		contentPane.add(Principal, "cell 1 1,grow");
		
		JPanel BarraInferior = new JPanel();
		BarraInferior.setBackground(new Color(255, 255, 255));
		contentPane.add(BarraInferior, "cell 0 2 2 1,grow");
		BarraInferior.setLayout(new MigLayout("", "[][][][][][][][][][][][][][][][][][][][][][][][][][][][][][]", "[]"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		BarraInferior.add(lblNewLabel_1, "cell 29 0");
	}
}
