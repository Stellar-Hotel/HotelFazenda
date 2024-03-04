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
import java.awt.geom.RoundRectangle2D;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDigiteASenha;
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
		contentPane.setLayout(new MigLayout("", "[1092px]", "[658px]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,alignx right,growy");
		panel.setLayout(new MigLayout("", "[84px][84px][210.00px][122.00][][119px][143px][115px][113px,right]", "[14px][81px][41.00][][3px][84.00][25px][31px][64.00][][25px][31px][14px][][][][][31px]"));
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao ");
		panel.add(lblNewLabel, "flowx,cell 0 0,growx,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("Entrar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 55));
		panel.add(lblNewLabel_2, "cell 0 1 2 1,alignx left,aligny bottom");
		
		JLabel lblNewLabel_3 = new JLabel("Digite seu nome de usuario ou endereço de e-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3, "cell 0 6 6 1,growx,aligny top");
		
		JLabel lblNewLabel_3_1 = new JLabel("Digite sua senha");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3_1, "cell 0 10 6 1,growx,aligny top");
		
		txtDigiteASenha = new JTextField();
		txtDigiteASenha.setText("Digite a senha");
		panel.add(txtDigiteASenha, "cell 0 7 8 1,grow");
		txtDigiteASenha.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1, "cell 0 11 8 1,grow");
		
		JLabel lblNewLabel_4 = new JLabel("Não possui uma conta?");
		panel.add(lblNewLabel_4, "cell 8 0,growx,aligny top");
		
		JLabel lblNewLabel_4_1 = new JLabel("Inscrever-se");
		lblNewLabel_4_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_4_1, "cell 8 1,growx,aligny top");
		
		JLabel lblNewLabel_4_2 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_4_2.setForeground(new Color(0, 128, 255));
		panel.add(lblNewLabel_4_2, "cell 7 12,growx,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("Hotel Fazenda");
		lblNewLabel_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_1, "cell 0 0,alignx left,aligny top");
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(117, 187, 68));
		btnNewButton.setForeground(new Color(0, 0, 0));
		panel.add(btnNewButton, "cell 3 15 2 1,grow");
	}
}
