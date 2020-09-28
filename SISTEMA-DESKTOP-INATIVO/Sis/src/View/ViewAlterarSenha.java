package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class ViewAlterarSenha extends JFrame {

	 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField VlrConfirmarSenha;
	private JPasswordField VlrSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAlterarSenha frame = new ViewAlterarSenha();
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
	public ViewAlterarSenha() {
		setTitle("Alterar senha");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewAlterarSenha.class.getResource("/ORIGINAL/Keys-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblConfirmarSenha = new JLabel("REPITA A SENHA:");
		lblConfirmarSenha.setBounds(41, 87, 147, 14);
		lblConfirmarSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton btnSalvar = new JButton("SALVAR");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(validarSenha()) {
				JOptionPane.showMessageDialog(null,"Senha salva com sucesso");
					limparCampos();
					}
			}
		});
		btnSalvar.setBounds(231, 149, 98, 23);
		btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		VlrConfirmarSenha = new JPasswordField();
		VlrConfirmarSenha.setBounds(199, 86, 168, 20);
		
		JLabel lblNovaSenha = new JLabel("NOVA SENHA:");
		lblNovaSenha.setBounds(41, 56, 132, 14);
		lblNovaSenha.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		VlrSenha = new JPasswordField();
		VlrSenha.setBounds(199, 55, 168, 20);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(56, 149, 109, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		contentPane.add(btnCancelar);
		contentPane.add(lblNovaSenha);
		contentPane.add(VlrSenha);
		contentPane.add(lblConfirmarSenha);
		contentPane.add(VlrConfirmarSenha);
		contentPane.add(btnSalvar);
	}

	public void limparCampos() {
		VlrConfirmarSenha.setText("");
		VlrSenha.setText("");
	}
	
	public boolean validarSenha() {
		if(VlrSenha.getPassword().toString().equals("")) {
			JOptionPane.showMessageDialog(null,"Digite a nova senha");
			VlrSenha.requestFocus();
			return false;	 
		}
		else if(VlrConfirmarSenha.getPassword().toString().equals("")) {
			JOptionPane.showMessageDialog(null,"Confirme a senha");
			VlrConfirmarSenha.requestFocus();
			return false;		 
		}
		if(VlrSenha.getPassword().toString().equals(VlrConfirmarSenha.getPassword().toString())) {		
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null,"Senhas não correpondem!");
			limparCampos();
			return false;
		}
	}
}
