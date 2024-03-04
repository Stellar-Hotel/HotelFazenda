package visao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class TelaDeCadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaDeCadastro frame = new TelaDeCadastro();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public TelaDeCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(
				new MigLayout("", "[65px,grow][][4px][405px][93px][28px][33px][301px,grow][115px,grow][97px]", "[14px][14px][94px][25px][20px][27px][20px][:20px:30px,grow][20px,grow][grow][23px][][][grow][][][][][][][][][]"));

		JLabel lblNewLabel = new JLabel("Bem-vindo ao");
		contentPane.add(lblNewLabel, "flowx,cell 0 0,alignx left,aligny top");

		JLabel lblNewLabel_7 = new JLabel("Já possui uma conta?");
		contentPane.add(lblNewLabel_7, "cell 9 0,growx,aligny top");

		JLabel lblNewLabel_8 = new JLabel("Entrar");
		lblNewLabel_8.setForeground(new Color(117, 187, 68));
		contentPane.add(lblNewLabel_8, "cell 9 1,alignx center,aligny top");

		JLabel lblNewLabel_1 = new JLabel("Inscrever-se");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		contentPane.add(lblNewLabel_1, "cell 0 2 4 1,alignx left,growy");

		JLabel lblNewLabel_2 = new JLabel("Digite seu nome de usuário");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 6,growx,aligny top");
				
				JLabel lblNewLabel_2_1 = new JLabel("Digite seu nome de usuário");
				lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				contentPane.add(lblNewLabel_2_1, "cell 7 6");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 7 4 1,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 7 7,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		textField_2 = new JTextField();
		panel_1.add(textField_2, BorderLayout.CENTER);
		textField_2.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, "cell 7 9,grow");
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		textField_4 = new JTextField();
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Digite seu nome");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3, "cell 0 11,grow");
		
				textField_1 = new JTextField();
				contentPane.add(textField_1, "cell 0 12 4 1,growx,aligny top");
				textField_1.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Digite sua senha");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_5, "cell 0 17,growx,aligny top");

		JLabel lblNewLabel_6 = new JLabel("Hotel Fazenda");
		lblNewLabel_6.setForeground(new Color(117, 187, 68));
		contentPane.add(lblNewLabel_6, "cell 0 0,alignx left,aligny top");
								
										textField_3 = new JTextField();
										contentPane.add(textField_3, "cell 0 18 4 1,growx,aligny top");
										textField_3.setColumns(10);
	}
}
