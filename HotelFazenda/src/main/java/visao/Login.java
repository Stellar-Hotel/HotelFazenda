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
import java.awt.BorderLayout;

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
//					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		contentPane.setLayout(new MigLayout("", "[1080px,Grow]", "[720px,Grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,grow");
		panel.setLayout(new MigLayout("", "[grow][:84px:84px,grow][84px][210.00px,grow][122.00,grow][][119px][143px,grow][115px][113px,grow,right]", "[:14px:14px][58.00px,grow][][][3px][41.00,grow][38.00px][31px][:40px:40px,grow][grow][43.00px][31.00px][14px][grow][][][][31px]"));
		
		JLabel lblNewLabel = new JLabel("Bem vindo ao ");
		panel.add(lblNewLabel, "flowx,cell 1 0,growx,aligny top");
		
		JLabel lblNewLabel_2 = new JLabel("Entrar");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 55));
		panel.add(lblNewLabel_2, "cell 1 1 2 1,alignx left,aligny top");
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "cell 3 1 6 1,grow");
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Digite seu nome de usuario ou endereço de e-mail");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3, "cell 1 6 4 1,growx,aligny top");
		
		JLabel lblNewLabel_3_1 = new JLabel("Digite sua senha");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel.add(lblNewLabel_3_1, "cell 1 10 2 1,growx,aligny top");
		
		txtDigiteASenha = new JTextField();
		txtDigiteASenha.setText("Digite seu usuario\r\n");
		panel.add(txtDigiteASenha, "cell 1 7 8 1,grow");
		txtDigiteASenha.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel.add(textField_1, "cell 1 11 8 1,grow");
		
		JLabel lblNewLabel_4 = new JLabel("Não possui uma conta?");
		panel.add(lblNewLabel_4, "cell 9 0,alignx left,aligny top");
		
		JLabel lblNewLabel_4_1 = new JLabel("Inscrever-se");
		lblNewLabel_4_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_4_1, "cell 9 1,alignx left,aligny top");
		
		JLabel lblNewLabel_4_2 = new JLabel("Esqueceu sua senha?");
		lblNewLabel_4_2.setForeground(new Color(0, 128, 255));
		panel.add(lblNewLabel_4_2, "cell 8 12,growx,aligny top");
		
		JLabel lblNewLabel_1 = new JLabel("Hotel Fazenda");
		lblNewLabel_1.setForeground(new Color(117, 187, 68));
		panel.add(lblNewLabel_1, "cell 1 0,alignx left,aligny top");
		
		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBackground(new Color(117, 187, 68));
		btnNewButton.setForeground(new Color(0, 0, 0));
		panel.add(btnNewButton, "cell 4 15 2 1,grow");
	}
}
