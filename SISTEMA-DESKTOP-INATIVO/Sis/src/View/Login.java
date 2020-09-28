package View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField VlrUsuario;
	private JPasswordField VlrSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login Lframe = new Login();
					Lframe.setVisible(true);
					
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
		setResizable(false);
	
		setTitle("ENTRAR");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/MODIFICADO/User-Interface-Login-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 368, 359);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		
		JLabel Logo = new JLabel("\u00C1gora");
		Logo.setForeground(Color.DARK_GRAY);
		Logo.setFont(new Font("Wide Latin", Font.BOLD, 26));
		
		JLabel label_1 = new JLabel("LOGIN:");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		VlrUsuario = new JTextField();
		VlrUsuario.setColumns(10);
		
		JLabel label_2 = new JLabel("SENHA:");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		VlrSenha = new JPasswordField();
		
		JButton BtnCancelar = new JButton("Cancelar");
		BtnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		BtnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton BtnEntrar = new JButton("Entrar");
		BtnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			 
				if(validarCampos()) {
					 
					JOptionPane.showMessageDialog(null,"Logado com sucesso");
					ViewInicio frame = new ViewInicio();					 
					dispose();
					frame.setVisible(true);	
					
				
				}		else {
					JOptionPane.showMessageDialog(null, "Usuario ou senha incorretos");
				}
				 
			}
		});
			
		BtnEntrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(VlrUsuario, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addGap(20)
							.addComponent(VlrSenha, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(BtnCancelar, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(BtnEntrar, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
					.addGap(46))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(106, Short.MAX_VALUE)
					.addComponent(Logo, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(45)
					.addComponent(Logo, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(VlrUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(VlrSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(BtnCancelar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(BtnEntrar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	 
	public boolean validarCampos() {
		if(VlrUsuario.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(null,"Preencha o campo usuario");	
			VlrUsuario.requestFocus();
			return false;
		}
		else if(VlrSenha.getPassword().toString().trim().equals(""))	{
					JOptionPane.showMessageDialog(null,"Preencha o campo senha");
					VlrSenha.requestFocus();
					 return false;			 											
			 }	
		return true;		
	}
	
}	
	
	
	
	