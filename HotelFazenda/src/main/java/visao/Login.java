package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1080, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[14.00][10.00][200.00px,grow][117.00][310.00,grow][][-34.00][135.00][]", "[][][][][][][][32.00][25.00][][][][32.00][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao");
		contentPane.add(lblNewLabel, "flowx,cell 2 0");
		
		JLabel lblNewLabel_1 = new JLabel("Entrar");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 55));
		contentPane.add(lblNewLabel_1, "cell 2 1,alignx left,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("Digite seu nome ou endereço de e-mail");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 2 6");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 2 7 6 1,grow");
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Digite sua Senha");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_3, "cell 2 11");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		contentPane.add(textField_1, "cell 2 12 6 1,grow");
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.setBackground(new Color(117, 187, 68));
		contentPane.add(btnNewButton, "cell 3 15 1 2,grow");
		
		JLabel lblNewLabel_6 = new JLabel("Hotel Fazenda");
		lblNewLabel_6.setForeground(new Color(117, 187, 68));
		contentPane.add(lblNewLabel_6, "cell 2 0,alignx center");
	}

}
