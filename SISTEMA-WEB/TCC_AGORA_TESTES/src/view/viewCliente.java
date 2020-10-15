package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
 

import controller.controlClientes;
import model.TbCliente;

public class viewCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnome;
	private JTextField txtsobrenome;
	private JTextField txtrg;
	private JTextField txtcpf;
	private JTextField txtdtnasc;
	private JTextField txtpesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewCliente frame = new viewCliente();
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
	public viewCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 414);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlClientes control = new controlClientes();
			
				String nome = txtnome.getText();
				String sobrenome = txtsobrenome.getText();
				String rg = txtrg.getText();
				String cpf = txtcpf.getText(); 
				SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
				java.sql.Timestamp date= null;
				try {
					java.util.Date data = formato.parse(txtdtnasc.getText()); 
					date = new java.sql.Timestamp (data.getTime());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					e1.getCause();
					e1.getMessage();
				}
		//		JComboBox sexo = null;
		//		sexo = (JComboBox) sexo.getModel().getSelectedItem();
		//		String sex =  String.valueOf(sexo);
				
				/////////////////////// troca o sexo aqui em baixo
				try {
					if(control.controlCliente("I", nome, sobrenome, rg, cpf, date, "M")) {
						JOptionPane.showMessageDialog(null, "Dados salvos com sucesso");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(250, 272, 104, 23);
		panel.add(btnNewButton);
		
		txtnome = new JTextField();
		txtnome.setBounds(128, 68, 226, 20);
		panel.add(txtnome);
		txtnome.setColumns(10);
		
		txtsobrenome = new JTextField();
		txtsobrenome.setColumns(10);
		txtsobrenome.setBounds(128, 99, 226, 20);
		panel.add(txtsobrenome);
		
		txtrg = new JTextField();
		txtrg.setColumns(10);
		txtrg.setBounds(128, 130, 226, 20);
		panel.add(txtrg);
		
		txtcpf = new JTextField();
		txtcpf.setColumns(10);
		txtcpf.setBounds(128, 161, 226, 20);
		panel.add(txtcpf);
		
		txtdtnasc = new JTextField();
		txtdtnasc.setColumns(10);
		txtdtnasc.setBounds(128, 192, 226, 20);
		panel.add(txtdtnasc);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setBounds(38, 71, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblSobrenome = new JLabel("Sobrenome");
		lblSobrenome.setBounds(38, 102, 80, 14);
		panel.add(lblSobrenome);
		
		JLabel lblRg = new JLabel("RG");
		lblRg.setBounds(38, 133, 46, 14);
		panel.add(lblRg);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(38, 164, 46, 14);
		panel.add(lblCpf);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(38, 195, 89, 14);
		panel.add(lblDataNascimento);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setBounds(38, 226, 46, 14);
		panel.add(lblSexo);
		
		JComboBox sexo = new JComboBox();
		sexo.setModel(new DefaultComboBoxModel(new String[] {"SELECIONE", "MASCULINO", "FEMININO"}));
		sexo.addItem("Masculino");
		sexo.setBounds(128, 223, 226, 22);
		panel.add(sexo);
		
		txtpesquisa = new JTextField();
		txtpesquisa.setColumns(10);
		txtpesquisa.setBounds(38, 11, 182, 20);
		panel.add(txtpesquisa);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlClientes control = new controlClientes();
				String cpf = txtpesquisa.getText();
				try {
					if(controlClientes.getClientes(cpf) != null) {
						List<controlClientes> cliente = ((controlClientes) cliente).getClientes(cpf).getClass();
						
						txtnome.setText(getName()); 
						
					}
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnPesquisar.setBounds(250, 10, 104, 23);
		panel.add(btnPesquisar);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(128, 272, 104, 23);
		panel.add(btnAlterar);
	}
}
