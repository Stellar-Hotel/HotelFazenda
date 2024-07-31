package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Funcionarios;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;


public class TelaDeAcomodacoes extends DefaultScreen {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtPesquisa;

 
	public TelaDeAcomodacoes(Funcionarios Func) {
		super(Func);

		JPanel Principal = new JPanel();
		Principal.setBackground(new Color(250, 250, 250));
 		Principal.setLayout(new MigLayout("", "[420.00px:n][177.00px:n,grow][50px:n]", "[44px][14px][14px][-1.00][392.00px][97.00,grow]"));
		
		JLabel lblNewLabel_1 = new JLabel("Acomodações");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		Principal.add(lblNewLabel_1, "cell 0 0,alignx left,aligny top");
		
		JLabel lblNewLabel_7 = new JLabel("Descubra nossas acomodações feitas sob medida para o seu conforto, ");
		lblNewLabel_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Principal.add(lblNewLabel_7, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNewLabel_9 = new JLabel("proporcionando ambientes aconchegantes e climatizados para que você desfrute de momentos relaxantes.");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Principal.add(lblNewLabel_9, "cell 0 2,alignx left,aligny top");
		
		JPanel panel_8 = new JPanel();
		Principal.add(panel_8, "cell 0 4,grow");
		panel_8.setLayout(new MigLayout("", "[640px]", "[422px][25px][14px]"));
		
		JPanel panel_11 = new JPanel();
		panel_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeQuartos telaDeQuartos = new TelaDeQuartos(1,Func);
			telaDeQuartos.setExtendedState(JFrame.MAXIMIZED_BOTH);
			telaDeQuartos.setVisible(true);
			dispose();
			}
		});
		
		panel_8.add(panel_11, "cell 0 0,grow");
		panel_11.setLayout(null);
		
		JLabel lblQuartoSimples = new JLabel("");
		lblQuartoSimples.setBounds(7, 7, 626, 408);
		lblQuartoSimples.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuartoSimples.setIcon(new ImageIcon(TelaDeAcomodacoes.class.getResource("/Imagens/QuartoSimples.jpg")));
		
		
		panel_11.add(lblQuartoSimples);
		
		JLabel lblNewLabel_16 = new JLabel("Apartamento Standard");
		lblNewLabel_16.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		panel_8.add(lblNewLabel_16, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNewLabel_10 = new JLabel("2-4 hóspedes · TV · ar-condicionado · 2 cama solteiro · 1 camas casal · 1 banheiro");
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_8.add(lblNewLabel_10, "cell 0 2,alignx left,aligny top");
		
		JPanel panel_9 = new JPanel();
		Principal.add(panel_9, "cell 1 4,grow");
		panel_9.setLayout(new MigLayout("", "[641px]", "[422px][25px][14px]"));
		
		JPanel panel_12 = new JPanel();
		panel_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeQuartos telaDeQuartos = new TelaDeQuartos(2,Func);
				telaDeQuartos.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaDeQuartos.setVisible(true);
				dispose();
				}
		});
		panel_9.add(panel_12, "cell 0 0,grow");
		panel_12.setLayout(null);
		
		JLabel lblQuartoMediano = new JLabel("");
		lblQuartoMediano.setBounds(7, 7, 571, 408);
		lblQuartoMediano.setIcon(new ImageIcon(TelaDeAcomodacoes.class.getResource("/Imagens/QuartoMedio.jpg")));
		
		panel_12.add(lblQuartoMediano);
		
		JLabel lblNewLabel_17 = new JLabel("Apartamento Master");
		lblNewLabel_17.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		panel_9.add(lblNewLabel_17, "cell 0 1,alignx left,aligny top");
		
		JLabel lblNewLabel_12 = new JLabel("2-6 hóspedes · TV · Frigobar · ar-condicionado · 2 cama solteiro · 2 camas casal · 2 banheiros");
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel_9.add(lblNewLabel_12, "cell 0 2,alignx left,aligny top");
		
		JPanel panel_10 = new JPanel();
		Principal.add(panel_10, "cell 2 4,grow");
		panel_10.setLayout(new MigLayout("", "[641px]", "[422px][25px][14px]"));
		
		JPanel panel_13 = new JPanel();
		panel_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaDeQuartos telaDeQuartos = new TelaDeQuartos(3,Func);
				telaDeQuartos.setExtendedState(JFrame.MAXIMIZED_BOTH);
				telaDeQuartos.setVisible(true);
				dispose();
				
				}
		});
		panel_10.add(panel_13, "cell 0 0,grow");
		panel_13.setLayout(null);
		
		JLabel lblQuartoBom = new JLabel("");
		lblQuartoBom.setBounds(7, 7, 627, 408);
		lblQuartoBom.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuartoBom.setIcon(new ImageIcon(TelaDeAcomodacoes.class.getResource("/Imagens/QuartoBom.jpg")));
		panel_13.add(lblQuartoBom);
		
		JLabel lblNewLabel_18 = new JLabel("Apartamento Deluxe");
		lblNewLabel_18.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_10.add(lblNewLabel_18, "cell 0 1,growx,aligny top");
		
		JLabel lblNewLabel_14 = new JLabel("4-8 hóspedes · Banheira · TV · Frigobar · ar-condicionado · 4 camas solteiro · 2 camas casal · 3 banheiros");
		lblNewLabel_14.setFont(new Font("Tahoma", Font.PLAIN, 10));
		panel_10.add(lblNewLabel_14, "cell 0 2,alignx left,aligny top");
		
		JPanel panel_7 = new JPanel();
		Principal.add(panel_7, "cell 0 5,grow");
		panel_7.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel panel_6 = new JPanel();
		Principal.add(panel_6, "cell 1 5,grow");
		panel_6.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel panel_5 = new JPanel();
		Principal.add(panel_5, "cell 2 5,grow");
		panel_5.setLayout(new MigLayout("", "[]", "[]"));
		

		
	}
}
